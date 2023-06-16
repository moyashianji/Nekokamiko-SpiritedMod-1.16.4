package com.example.nekokamiko.event;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireEvent {


    public static void placeBlock(World world, BlockPos pos, Block block){

        world.setBlockState(pos, block.getDefaultState(),5);
    }

    public static void fireEvent(PlayerEntity playerIn){

        BlockPos position1 = new BlockPos(playerIn.getPosX()+1, playerIn.getPosY()+1, playerIn.getPosZ());
        BlockPos position2 = new BlockPos(playerIn.getPosX()-1, playerIn.getPosY()+1, playerIn.getPosZ());
        BlockPos position3 = new BlockPos(playerIn.getPosX(), playerIn.getPosY()+1, playerIn.getPosZ()+1);
        BlockPos position4 = new BlockPos(playerIn.getPosX(), playerIn.getPosY()+1, playerIn.getPosZ()-1);

        World worldin = playerIn.getEntityWorld();
        Block block = Blocks.FIRE;




        placeBlock(worldin, position1,block);
        placeBlock(worldin, position2,block);
        placeBlock(worldin, position3,block);
        placeBlock(worldin, position4,block);

    }

}
