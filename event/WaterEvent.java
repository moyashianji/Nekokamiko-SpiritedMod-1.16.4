package com.example.nekokamiko.event;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterEvent {

    public static void placeBlock(World world, BlockPos pos, Block block){

        world.setBlockState(pos, block.getDefaultState(),3);
    }

    public static void waterEvent(PlayerEntity playerIn){

        BlockPos position = new BlockPos(playerIn.getPosX(), playerIn.getPosY()-2, playerIn.getPosZ());
        World worldin = playerIn.getEntityWorld();
        Block block = Blocks.WATER;


        placeBlock(worldin, position,block);

    }

}
