package com.example.nekokamiko.timer;

import com.example.nekokamiko.event.GoldEvent;
import com.example.nekokamiko.random.RandomInt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.nekokamiko.event.GoldEvent.goldEvent;


public class TimeEvent {
    private static final int INITIAL_COUNT = 30; // カウントダウンの初期値
    private static final int COUNTDOWN_INTERVAL = 1000; // カウントダウンの間隔（ミリ秒）
    private static RandomInt randomint;

    private static int count; // カウントダウンの現在の値
    private static Timer timer;

    public static void timerti(PlayerEntity playerIn, World worldIn) {
        count = INITIAL_COUNT;
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (count > 0) {
                    System.out.println("Count: " + count);
                    count--;

                } else {
                    goldEvent(worldIn, playerIn, 15);

                    System.out.println("Countdown Reset");
                    resetCountdown();
                }
            }
        }, 0, COUNTDOWN_INTERVAL);
    }

    private static void resetCountdown() {
        count = INITIAL_COUNT;
    }
}

