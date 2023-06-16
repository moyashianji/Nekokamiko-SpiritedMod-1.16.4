package com.example.nekokamiko.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.EnumSet;
@Mod.EventBusSubscriber(modid = "nekokamiko")

public class ChaseEntity extends TameableEntity {
    private static PlayerEntity targetPlayer;
    private int attackTimer;

    public ChaseEntity(EntityType<? extends ChaseEntity> entityType, World world) {
        super(entityType, world);

    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 60)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 100.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5F)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 6);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0, 5.0f, 2.0f, true));
        this.goalSelector.addGoal(3, new FloatGoal(this));
    }

    private class FloatGoal extends Goal {
        private final ChaseEntity chaseEntity;

        public FloatGoal(ChaseEntity chaseEntity) {
            this.chaseEntity = chaseEntity;
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean shouldExecute() {
            return !chaseEntity.onGround && !chaseEntity.isInWater();
        }

        @Override
        public void startExecuting() {
            chaseEntity.getNavigator().clearPath();
        }

        @Override
        public void tick() {
            if (targetPlayer == null) {

                return;
            }
            double distance = 100.0; // プレイヤーとの距離の閾値
            double teleportDistance = 5.0; // テレポートする距離

            if (chaseEntity.getDistanceSq(targetPlayer) > distance * distance) {
                Vector3d targetPos = targetPlayer.getPositionVec().subtract(targetPlayer.getLookVec().scale(teleportDistance));
                BlockPos newPos = new BlockPos(targetPos.x, targetPos.y, targetPos.z);
                if (isBlockPassable(newPos)) {
                    chaseEntity.setPosition(targetPos.x, targetPos.y, targetPos.z);
                }
            }

            double targetY = targetPlayer.getPosY() + 1.0; // プレイヤーの高さに微調整
            double deltaY = targetY - chaseEntity.getPosY();

            // 浮き上がる速度の調整
            double motionY = deltaY * 0.1;
            if (Math.abs(motionY) < 0.01) {
                chaseEntity.setMotion(chaseEntity.getMotion().mul(1.0, 0.0, 1.0));
                return;
            }

            // 前フレームからの変位に基づいて新しい位置を設定
            double newY = chaseEntity.getPosY() + motionY;
            BlockPos newPos = new BlockPos(chaseEntity.getPosX(), newY, chaseEntity.getPosZ());
            if (isBlockPassable(newPos)) {
                chaseEntity.setPosition(chaseEntity.getPosX(), newY, chaseEntity.getPosZ());

                // Y方向の速度が安定するように落下速度を設定
                chaseEntity.setMotion(chaseEntity.getMotion().mul(1.0, 0.9, 1.0));

                // プレイヤーの方を向く
                double lookX = targetPlayer.getPosX() - chaseEntity.getPosX();
                double lookZ = targetPlayer.getPosZ() - chaseEntity.getPosZ();
                double lookYaw = MathHelper.atan2(lookZ, lookX) * (180.0 / Math.PI) - 90.0;
                chaseEntity.rotationYaw = (float) lookYaw;
                chaseEntity.rotationYawHead = chaseEntity.rotationYaw;
            }
            if (targetPlayer != null) {
                double distanceSq = (targetPlayer.getPosX() - chaseEntity.getPosX()) * (targetPlayer.getPosX() - chaseEntity.getPosX())
                        + (targetPlayer.getPosZ() - chaseEntity.getPosZ()) * (targetPlayer.getPosZ() - chaseEntity.getPosZ());

                if (distanceSq >= 40000.0) { // Distance of 10 blocks squared
                    // Calculate the position five blocks behind the player
                    Vector3d targetPos = targetPlayer.getPositionVec().subtract(targetPlayer.getLookVec().scale(5.0));
                    BlockPos newwPos = new BlockPos(targetPos.x, targetPos.y, targetPos.z);
                    if (isBlockPassable(newwPos)) {
                        chaseEntity.setPosition(targetPos.x, targetPos.y, targetPos.z);
                    }
                } else if (distanceSq >= 100.0) {
                    double speed = 1.05;
                    chaseEntity.getNavigator().tryMoveToEntityLiving(targetPlayer, speed);
                } else if (distanceSq >= 25.0) { // Distance of 5 blocks squared
                    double speed = 1.0; // Movement speed
                    chaseEntity.getNavigator().tryMoveToEntityLiving(targetPlayer, speed);
                } else {
                    chaseEntity.getNavigator().clearPath();
                }
            }
        }


        private boolean isBlockPassable(BlockPos pos) {
            return world.isAirBlock(pos) || world.getBlockState(pos).getCollisionShape(world, pos).isEmpty();
        }
    }

    public static void setTargetPlayer(PlayerEntity player) {
        targetPlayer = player;
    }

    @Override
    public void tick() {
        super.tick();


        if (targetPlayer == null || !targetPlayer.isAlive()  || targetPlayer.removed) {
            this.remove();
            return;
        }
        double followerX = this.getPosX();
        double followerZ = this.getPosZ();


        if (targetPlayer != null) {
            double distanceSq = (targetPlayer.getPosX() - followerX) * (targetPlayer.getPosX() - followerX)
                    + (targetPlayer.getPosZ() - followerZ) * (targetPlayer.getPosZ() - followerZ);

            if (distanceSq >= 40000.0) { // Distance of 10 blocks squared
                // Calculate the position five blocks behind the player
                Vector3d targetPos = targetPlayer.getPositionVec().subtract(targetPlayer.getLookVec().scale(5.0));
                BlockPos newPos = new BlockPos(targetPos.x, targetPos.y, targetPos.z);
                if (isBlockPassable(newPos)) {
                    this.setPosition(targetPos.x, targetPos.y, targetPos.z);
                }
            } else if (distanceSq >= 100.0) {
                double speed = 1.05;
                this.getNavigator().tryMoveToEntityLiving(targetPlayer, speed);
            } else if (distanceSq >= 25.0) { // Distance of 5 blocks squared
                double speed = 1.0; // Movement speed
                this.getNavigator().tryMoveToEntityLiving(targetPlayer, speed);
            } else {
                this.getNavigator().clearPath();
            }
        }
    }

    private boolean isBlockPassable(BlockPos pos) {
        return world.isAirBlock(pos) || world.getBlockState(pos).getCollisionShape(world, pos).isEmpty();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return false; // ダメージを受けないように常にfalseを返す
    }

    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 4) {
            this.attackTimer = 10;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false; // ダメージを受けないように常にfalseを返す
    }


}