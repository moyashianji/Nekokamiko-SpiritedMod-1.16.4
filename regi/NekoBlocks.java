package com.example.nekokamiko.regi;


import com.example.nekokamiko.block.BlockCheatGlass;
import com.example.nekokamiko.block.BlockNekoBlock;
import com.example.nekokamiko.block.BlockNekoGlass;
import com.example.nekokamiko.main.Nekokamiko;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class NekoBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,Nekokamiko.MOD_ID);


    public static final RegistryObject<Block> HEATED_COAL_BLOCK = BLOCKS.register("heated_coal_block", () -> new BlockNekoBlock());
    public static final RegistryObject<Block> BLOCK_NEKO_GLASS = BLOCKS.register("block_neko_glass",()-> new BlockNekoGlass());
    public static final RegistryObject<Block> BLOCK_CHEAT_GLASS = BLOCKS.register("block_cheat_glass", ()-> new BlockCheatGlass());


}
