package com.example.nekokamiko.random;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.*;
import net.minecraft.world.World;

import java.util.Random;
import java.util.UUID;

import static com.example.nekokamiko.event.DamageEvent.damageEvent;
import static com.example.nekokamiko.event.GoldEvent.goldEvent;
import static com.example.nekokamiko.event.LavaEvent.lavaEvent;
import static com.example.nekokamiko.event.PrimedEvent.explosionEvent;
import static com.example.nekokamiko.event.WaterEvent.waterEvent;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class RandomInt {




    public static void randomEvent(PlayerEntity playerIn, World world, int scatterCount) {


        Random random = new Random();
        int min = 1;
        int max = 4;

        int randomEventNumber = random.nextInt(max - min + 1) + min;

        switch (randomEventNumber) {

            case 1:
                damageEvent(playerIn);
                System.out.println("AAAA1");
                break;
            case 2:
                lavaEvent(playerIn);
                System.out.println("CCCC3");
                break;
            case 3:
                waterEvent(playerIn);
                System.out.println("DDDD4");
                break;
            case 4:
                explosionEvent(playerIn);
                System.out.println("EEEE5");
                break;

            default:

        }
    }
}
