package com.example.nekokamiko.regi;

import com.example.nekokamiko.block.BlockNekoBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class NekoModTab2 extends ItemGroup{

    public NekoModTab2() {
        super("nekokamiko");
    }

    @Override
    public ItemStack createIcon(){

        ItemStack itemStack = new ItemStack(NekoBlocks.HEATED_COAL_BLOCK.get());
        return itemStack;

    }
}
