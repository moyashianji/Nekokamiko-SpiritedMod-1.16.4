package com.example.nekokamiko.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import com.example.nekokamiko.entity.NekoEntity;

import javax.annotation.Nullable;
//モブの基本情報設定
public class NekoEntity extends AbstractVillagerEntity {

    private int attackTimer;
    public NekoEntity(EntityType<? extends NekoEntity> type, World worldIn) {
        super(type, worldIn);
    }

    //モブのステータス設定
    public static AttributeModifierMap.MutableAttribute registerAttributes(){
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 64)//認識の距離　どれくらいの範囲で認識されるか
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5F)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE,10)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 6);
    }
    //braingaが生活感のある村人で、goalが野生のもぶ
    //モブの特徴設定
    @Override
    protected void registerGoals(){//優先度１、２なら１のほうが優先される、swimgoaがゴールもクラスを作ってある
        this.goalSelector.addGoal(0,new SwimGoal(this));
        this.goalSelector.addGoal(1,new LookAtCustomerGoal(this));
        //
        //プライオリティはかぶっても大丈夫どっちかそれの状況に合わせて動く
        //
        this.goalSelector.addGoal(1,new LeapAtTargetGoal(this,0.4F));//攻撃するときにとびかかって攻撃する
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1.0F,true));//MeleeAttackGoalは、モブのAI（人工知能）によって制御され、近接攻撃のロジックを提供します。モブがプレイヤーに対して攻撃を行うためには、このGoalが設定されている必要があります。また、攻撃対象の判定や攻撃の効果は、MeleeAttackGoalによって制御されます。
        //useLongmemory
        //
        //マイクラのuseLongMemoryは、エンティティ（モブやモンスター）の行動に関する属性の一つです。
        // useLongMemoryが有効になっている場合、エンティティは長期的な記憶を持ち、目標を追い続けることができます。
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.9,32));
        //
        //MoveTowardsTargetGoalでは、エンティティが目標に向かって移動するための設定や条件が定義されます。具体的には、以下のような機能があります：
        //移動速度の設定: エンティティが目標に向かって移動する際の速度を設定することができます。速度の設定は、追跡の速さやバランスの調整に役立ちます。
        //追跡範囲の設定: エンティティが目標を追跡する範囲を設定することができます。追跡範囲内に目標が存在する場合にのみ、エンティティは移動を開始します。
        //目標の更新と再設定: エンティティが目標を追いかける際に、目標が変化した場合には新しい目標に向かって移動するように設定することができます。
        // これにより、エンティティが目標の変更に迅速に対応することができます。
        //
        this.goalSelector.addGoal(3, new ReturnToVillageGoal(this,0.6,false));
        //
        //ReturnToVillageGoalは、村人が外部での活動や移動後に、自身が所属する村へと帰還するために使用されます。村人は安全な場所である村に戻ることで、危険や敵からの保護を求めることができます。
        //このゴールは、以下のような機能や設定があります：
        //村の位置の設定: ReturnToVillageGoalでは、村の位置を特定するための情報を指定する必要があります。村の位置は、村人が戻るべき目標地点となります。
        //村への移動: ゴールがアクティブになった場合、村人は村の位置に向かって移動を開始します。経路探索アルゴリズムを使用して、最適な経路を計算し、村に向かって進みます。
        //村に到着後の行動: 村人が村に到着した場合、その後の行動を設定することも可能です。例えば、村の中心に留まる、特定の場所に移動する、他の行動目標に切り替えるなどが考えられます。
        //
        this.goalSelector.addGoal(5, new PatrolVillageGoal(this, 0.6));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));//六マス近づくとプレイヤーを見てくる
        //
        //マイクラのLookAtGoalは、エンティティ（モブやモンスター）が特定の目標に向かって視線を向けるための行動目標です。
        // このゴールを使用することで、エンティティは目標の方向を注視することができます。
        //
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        //
        //マイクラのLookRandomlyGoalは、エンティティ（モブやモンスター）がランダムな方向を見るための行動目標です。
        // このゴールを使用することで、エンティティはランダムに周囲を見回すことができます。
        //
        this.goalSelector.addGoal(9, new WaterAvoidingRandomWalkingGoal(this,1.0F));
        //
        //マイクラのWaterAvoidingRandomWalkingGoalは、エンティティ（モブやモンスター）が水を避けつつランダムに歩くための行動目標です。
        // このゴールを使用することで、エンティティはランダムな方向に歩き回る際に、水や水源ブロックを避けるようになります。
        //
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, false));//モンスターが近づいたら稼働する
        //
        //NearestAttackableTargetGoalは、マイクラのエンティティ（モブやモンスター）が最も近い攻撃可能なターゲットを見つけ、そのターゲットに向かって攻撃するための行動目標です。
        // このゴールを使用することで、エンティティは自身に攻撃可能な対象が近くに存在する場合に、その対象に対して攻撃を行うようになります。
        //

    }

    //
    //livingTick は、Minecraft（マインクラフト）のエンティティ（Entity）クラスやそのサブクラスに存在するメソッドです。
    // このメソッドは、エンティティが毎ゲームTickごとに実行される処理を行うために使用されます。
    //livingTick メソッドは、エンティティがゲーム内で生存している間に実行される処理をカスタマイズするためにオーバーライドされます。
    // 通常、このメソッド内でエンティティの振る舞い、移動、AI、パーティクルの生成、ブロックとの相互作用など、エンティティに関連する各種の処理を記述します。
    //
    @Override
    public void livingTick() {
        super.livingTick();
        if(this.attackTimer > 0){

            --attackTimer;

        }
    }

    //自分が攻撃したときに呼び出されるメソッド　相手を吹っ飛ばした買ったりするとここに書けばいい
    @Override
    public boolean attackEntityAsMob(Entity entityIn) {

        //エンティティの状態を登録 エンティティの状態を４の状態にする　
        this.world.setEntityState(this, (byte) 4);
        //
        //attackEntityFrom は、Minecraft（マインクラフト）のゲーム内で使用されるメソッドの1つです。このメソッドは、プレイヤーやエンティティが別のエンティティに対して攻撃を行う際に使用されます。
        //attackEntityFrom メソッドは、以下のような形式で使用されます：
        //boolean attackEntityFrom(DamageSource source, float amount);
        //source: 攻撃の元となる DamageSource オブジェクトです。例えば、プレイヤーの攻撃、モブの攻撃、環境のダメージ（落下や火など）などが DamageSource として渡されます。
        //amount: 攻撃のダメージ量を表す浮動小数点数です。通常、0より大きい値が指定されます。
        //
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 10F);
        //敵モブが攻撃されたら
        return flag;
    }

    @Override
    public void handleStatusUpdate(byte id) {
        if(id == 4){
            this.attackTimer = 10;//0.5s
        }else {
            super.handleStatusUpdate(id);
        }
    }

    //ほかのクラスでも扱えるようにする
    public int getAttackTimer(){
        return this.attackTimer;
    }

    @Override
    protected void onVillagerTrade(MerchantOffer offer) {

    }

    @Override
    protected void populateTradeData() {

    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }
}
