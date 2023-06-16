package com.example.nekokamiko.item.tool;

import com.example.nekokamiko.main.Nekokamiko;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;


public class ItemNekoPickaxe extends PickaxeItem {


    public ItemStack goldstack = new ItemStack(Items.GOLD_NUGGET);
    //ピッケルのコンストラクタ
    public ItemNekoPickaxe() {
        super(NekoList.NEKO, 1, -2.8F, new Properties().group(Nekokamiko.NEKO_TAB).rarity(Rarity.EPIC).isImmuneToFire());

    }
    //もし右クリックしたらイベント　そしてもしスニーク状態だったらmodeを変更するmodechangeメソッドを呼び出す
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        //lavaEvent(playerIn);
        //goldEvent(worldIn, playerIn,  100);
        //damageEvent(playerIn);
        //explosionEvent(playerIn);


        playerIn.attackEntityFrom(DamageSource.GENERIC, 5);
        if(playerIn.isSneaking()){
            playerIn.setActiveHand(handIn);
            modeChange(stack);
            playerIn.sendStatusMessage(new StringTextComponent("Mode:" + modeName(stack)), true );//falseだとメッセージバーが消えなくなる
            return ActionResult.resultConsume(stack);
        }
        return ActionResult.resultPass(stack);
    }

    //モードをチェンジするメソッド
    public void modeChange(ItemStack stack){

        //modenpdがあるかを調べる
        if (stack.getTag() == null){//nbtがなければ新しくnbtついかする
            stack.setTag(new CompoundNBT());
        }
        stack.getTag().putInt("mode", modeInt(stack) < 1 ? modeInt(stack) + 1 : 0 );//はてなはもーどが１より小さいですか？っていう意味 はいならプラス１、違うなら０になるという意味
    }

    //nbtの番号取得
    public int modeInt(ItemStack stack){

        if (stack.getTag()==null){
            return 0;
        }
        return stack.getTag().getInt("mode");
    }

    //採掘速度上昇　０と１の場合の分岐
    public float getDestroySpeed(ItemStack stack, BlockState state) {

        switch(modeInt(stack)){

            case 0 : return 12F;
            case 1 : return 45F;

        }
        return 0;
    }
    //modeの名前設定
    public String modeName(ItemStack stack){

        switch (modeInt(stack)){
            case 0 : return "Normal Mode";
            case 1 : return "tactical mode";
        }
        return null;
    }

    //アイテムにメッセージつける
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Mode:" + modeName(stack)));

    }

}
