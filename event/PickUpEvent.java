package com.example.nekokamiko.event;

import com.example.nekokamiko.main.YourEventHandler;
import com.example.nekokamiko.random.RandomInt;
import com.example.nekokamiko.timer.TimeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.nekokamiko.main.YourEventHandler.timee;

@Mod.EventBusSubscriber(modid = "nekokamiko")
public class PickUpEvent {

    private static Item item;
    private static RandomInt randomint;
    @SubscribeEvent
    public static void onItemPickup(PlayerEvent.ItemPickupEvent event) {

        ItemStack gold = new ItemStack(Items.GOLD_NUGGET);
        gold.setDisplayName(new TranslationTextComponent("カオナシの金"));

        // プレイヤーのインベントリを取得します
        PlayerEntity player = event.getPlayer();
        IInventory playerInventoryy = player.inventory;

        for (int slot = 0; slot < playerInventoryy.getSizeInventory(); slot++) {
            ItemStack stack = playerInventoryy.getStackInSlot(slot);
            item = stack.getItem();
            // インベントリ内の各スロットにあるアイテムを取得します
            // ここでアイテムに対する処理を行います
            // 例えば、特定の名前のアイテムを判定する処理などを記述します

            if (event.getPlayer() != null &&
                    item == Items.GOLD_NUGGET) {

                if (stack.getDisplayName().getString().equals("カオナシの金")) {
                    System.out.println("Pickup gold");
                    randomint.randomEvent(event.getPlayer(), event.getPlayer().world, 15);


                    removeItemsFromInventory(player, "カオナシの金"); // プレイヤーのインベントリからアイテムを削除します
                    removeGoldNuggets(event.getEntity().world, event.getPlayer());


                }
            }
        }
    }


    public static void register() {
        MinecraftForge.EVENT_BUS.register(PickUpEvent.class);
    }

    //インベントリから金ナゲット消す
    private static void removeItemsFromInventory(PlayerEntity player, String itemName) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() == Items.GOLD_NUGGET && stack.getDisplayName().getString().equals(itemName)) {
                player.inventory.setInventorySlotContents(i, ItemStack.EMPTY); // アイテムを空のスタックに置き換えます
            }
        }
    }


    //ワールドプレイヤーの五百麻酔内にある金ナゲット消す
    private static void removeGoldNuggets(World world, PlayerEntity player) {
        List<ItemEntity> itemEntities = world.getEntitiesWithinAABB(ItemEntity.class,
                new AxisAlignedBB(player.getPosX() - 500, player.getPosY() - 500, player.getPosZ() - 500,
                        player.getPosX() + 500, player.getPosY() + 500, player.getPosZ() + 500));
        // プレイヤーの周囲5ブロック以内にあるすべてのItemEntityを取得します

        for (ItemEntity itemEntity : itemEntities) {
            ItemStack itemStack = itemEntity.getItem();
            if (itemStack.getItem() == Items.GOLD_NUGGET && itemStack.getDisplayName().getString().equals("カオナシの金")) {
                itemEntity.remove(); // ItemEntityを削除します
            }
        }
    }
}

