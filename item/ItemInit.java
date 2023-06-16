package com.example.nekokamiko.item;

import com.example.nekokamiko.item.tool.ItemNekoPickaxe;
import com.example.nekokamiko.item.tool.ItemNekoSword;
import com.example.nekokamiko.main.Nekokamiko;
import com.example.nekokamiko.regi.NekoBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.item.Item.Properties;
public class ItemInit {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,Nekokamiko.MOD_ID);

    public static final RegistryObject<Item> HEATED_COAL_BLOCK = ITEMS.register("heated_coal_block",
            () -> new BlockItem(NekoBlocks.HEATED_COAL_BLOCK.get(),new Properties().group(Nekokamiko.NEKO_TAB)));

    public static final RegistryObject<Item> BLOCK_NEKO_GLASS = ITEMS.register("block_neko_glass",
            () -> new BlockItem(NekoBlocks.BLOCK_NEKO_GLASS.get(),new Properties().group(Nekokamiko.NEKO_TAB)));

    public static final RegistryObject<Item> BLOCK_CHEAT_GLASS = ITEMS.register("block_cheat_glass",
            () -> new BlockItem(NekoBlocks.BLOCK_CHEAT_GLASS.get(), new Properties().group(Nekokamiko.NEKO_TAB)));

    public static final RegistryObject<Item> NEKO_SWORD = ITEMS.register("neko_sword",
            () -> new ItemNekoSword());

    public static final RegistryObject<Item> NEKO_PICKAXE = ITEMS.register("neko_pickaxe",
            () -> new ItemNekoPickaxe());




}



