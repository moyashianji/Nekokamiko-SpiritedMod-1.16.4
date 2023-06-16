package com.example.nekokamiko.event;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.example.nekokamiko.event.FireEvent.fireEvent;

public class PrimedEvent {

    public static void placeBlock(World world, BlockPos pos, Block block){

        world.setBlockState(pos, block.getDefaultState(),3);
    }

    public static void explosionEvent(PlayerEntity playerIn){

        World worldin = playerIn.getEntityWorld();

        TNTEntity tntEntity1 = new TNTEntity(worldin,playerIn.getPosX()+1, playerIn.getPosY()+10, playerIn.getPosZ(),null);
        TNTEntity tntEntity2 = new TNTEntity(worldin,playerIn.getPosX()-1, playerIn.getPosY()+10, playerIn.getPosZ(),null);
        TNTEntity tntEntity3 = new TNTEntity(worldin,playerIn.getPosX(), playerIn.getPosY()+10, playerIn.getPosZ()+1,null);
        TNTEntity tntEntity4 = new TNTEntity(worldin,playerIn.getPosX(), playerIn.getPosY()+10, playerIn.getPosZ()-1,null);


        worldin.addEntity(tntEntity1);
        worldin.addEntity(tntEntity2);
        worldin.addEntity(tntEntity3);
        worldin.addEntity(tntEntity4);

    }


}
