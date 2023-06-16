package com.example.nekokamiko.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockNekoBlock extends Block {

    public BlockNekoBlock(){

        super(Properties
                .create(Material.IRON)
                .setRequiresTool()
                .hardnessAndResistance(5,6)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .sound(SoundType.NETHER_BRICK)
        );


    }

}