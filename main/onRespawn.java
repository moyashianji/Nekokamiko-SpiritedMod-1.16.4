package com.example.nekokamiko.main;

import com.example.nekokamiko.entity.ChaseEntity;
import com.example.nekokamiko.entity.render.ChaseEntityType;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = "nekokamiko", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class onRespawn {

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        ChaseEntity followerEntity = ChaseEntityType.CHASE_ENTITY.get().create(event.getPlayer().world);

        followerEntity.setTargetPlayer(event.getPlayer());
        followerEntity.setPosition(event.getPlayer().getPosX(), event.getPlayer().getPosY(), event.getPlayer().getPosZ());

        event.getPlayer().world.addEntity(followerEntity);
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(YourEventHandler.class);
    }

}