package com.example.nekokamiko.block;

import net.minecraft.block.GlassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockNekoGlass extends GlassBlock {

    public BlockNekoGlass(){
        super(Properties
                .create(Material.GLASS)
                .sound(SoundType.GLASS)
                .hardnessAndResistance(2F, 150F)
                .harvestLevel(1)
                .setRequiresTool()
                .notSolid()
        );
    }
}
