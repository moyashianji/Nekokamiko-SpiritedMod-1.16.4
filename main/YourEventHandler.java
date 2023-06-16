package com.example.nekokamiko.main;

import com.example.nekokamiko.entity.ChaseEntity;
import com.example.nekokamiko.entity.render.ChaseEntityType;
import com.example.nekokamiko.timer.TimeEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "nekokamiko", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class YourEventHandler {



    public static TimeEvent timee;
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {

        timee = new TimeEvent();
        System.out.println("aaaaaaaaaaaaaaaaaaaaiaiaiaia");
        System.out.println(event.getPlayer().getUniqueID());



        String message = "Nekokamiko";
        StringTextComponent textComponent = new StringTextComponent(message);
        event.getPlayer().sendMessage(textComponent,event.getPlayer().getUniqueID());

        ChaseEntity.setTargetPlayer(event.getPlayer());

        ChaseEntity followerEntity = ChaseEntityType.CHASE_ENTITY.get().create(event.getPlayer().world);

        followerEntity.setTargetPlayer(event.getPlayer());
        followerEntity.setPosition(event.getPlayer().getPosX(), event.getPlayer().getPosY(), event.getPlayer().getPosZ());

        event.getPlayer().world.addEntity(followerEntity);
        timee.timerti(event.getPlayer(), event.getPlayer().world);

    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(YourEventHandler.class);
    }


}