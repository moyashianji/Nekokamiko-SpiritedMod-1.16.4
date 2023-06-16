package com.example.nekokamiko.item.tool;

import com.example.nekokamiko.item.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum NekoList implements IItemTier {

    NEKO(4,5000,12F,6F,50,Ingredient.fromItems(ItemInit.HEATED_COAL_BLOCK.get()));
    private final int maxUses, harvestLevel, enchantability;
    private final float efficiency, attackDamage;
    private final Ingredient repairMaterial;

    private NekoList(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Ingredient repairMaterial){

        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;

    }
    public int getMaxUses() {
        return this.maxUses;
    }

    public float getEfficiency() {
        return this.efficiency;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial;
    }

}
