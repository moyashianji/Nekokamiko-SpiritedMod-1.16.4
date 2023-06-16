package com.example.nekokamiko.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class DamageEvent {

    public static void damageEvent(PlayerEntity playerIn){
        playerIn.attackEntityFrom(DamageSource.GENERIC, 5);

    }
}
