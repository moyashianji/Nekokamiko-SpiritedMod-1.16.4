package com.example.nekokamiko.event;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.Random;

public class GoldEvent {

    private static final int SPAWN_RADIUS = 3;
    public static void goldEvent(World world, PlayerEntity playerIn,int scatterCount){

        for(int i = 0; i< scatterCount; i++){


            Random random = new Random();
            double angle = Math.toRadians(random.nextDouble()*360);

            double x = playerIn.getPosX() + (SPAWN_RADIUS * Math.cos(angle));
            double y = playerIn.getPosY();
            double z = playerIn.getPosZ() + (SPAWN_RADIUS * Math.sin(angle));

            ItemStack gold = new ItemStack(Items.GOLD_NUGGET);
            gold.setDisplayName(new TranslationTextComponent("カオナシの金"));

            ItemEntity itemEntity = new ItemEntity(world, x,y,z,gold);

            world.addEntity(itemEntity);

        }
    }

}
