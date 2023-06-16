package com.example.nekokamiko.entity.render;

import com.example.nekokamiko.entity.ChaseEntity;
import com.example.nekokamiko.entity.NekoEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

public class ChaseEntityType {

    public static final DeferredRegister<EntityType<?>> CHASE_REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, "nekokamiko");

    public static final RegistryObject<EntityType<ChaseEntity>> CHASE_ENTITY = chaseentity("chase_mob",ChaseEntity::new);

    public static  <T extends Entity>RegistryObject<EntityType<T>> chaseentity(String id, BiFunction<EntityType<T>, World, T> function){

        EntityType<T> type = EntityType
                .Builder
                .create(function::apply,
                        EntityClassification.MISC)
                .size(0.5F,1.6F)
                .trackingRange(32)
                .build(id);


        return CHASE_REGISTER.register(id, ()->type);
    }

}

