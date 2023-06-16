package com.example.nekokamiko.item.tool;

import com.example.nekokamiko.main.Nekokamiko;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import static com.example.nekokamiko.random.RandomInt.randomEvent;

public class ItemNekoSword extends SwordItem {

    public ItemNekoSword() {
        super(NekoList.NEKO, 3, -2.4F, new Properties().group(Nekokamiko.NEKO_TAB).rarity(Rarity.EPIC).isImmuneToFire());//sttackspeed = 4+ツールの攻撃速度
    //isimmuneはマグマでも燃えない
    }


    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        attacker.addPotionEffect((new EffectInstance(Effects.REGENERATION,2,6)));

        Vector3d vector3d = attacker.getLook(1f);//自分の視線

        target.setMotion(target.getMotion().add(vector3d.x, (double)1.2F, vector3d.z));
        stack.damageItem(1, attacker, (p_220045_0_) -> {
            p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        return true;
    }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        ItemStack itemStack = playerIn.getHeldItem(handIn);

        Vector3d vector3d = playerIn.getLook(1.0F);

        FireballEntity fireballentity = new FireballEntity(worldIn, playerIn, vector3d.x, vector3d.y, vector3d.z);
        fireballentity.setPosition(playerIn.getPosX() + vector3d.x, playerIn.getPosY() +1, playerIn.getPosZ() + vector3d.z);
        fireballentity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3F, 0F);




        randomEvent(playerIn,worldIn,100);
        worldIn.addEntity(fireballentity);

        itemStack.damageItem(2, playerIn, (p_220045_0_) -> {
            p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });

        return ActionResult.resultConsume(itemStack);
    }
    }
