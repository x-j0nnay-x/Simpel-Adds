package net.x_j0nnay_x.simpeladd.item;


import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModItems;

import java.util.List;
import java.util.Objects;

import static javax.swing.text.StyleConstants.getComponent;

public class GrinderHeadItem extends Item{

    int MaxUses;
    int Uses;
    public GrinderHeadItem(int maxuses) {
        super(new Item.Properties()
                .stacksTo(1)
                .durability(maxuses));
        this.MaxUses = maxuses;

    }

    public ItemStack getContainerItem(ItemStack itemstack) {
        ItemStack item = itemstack.copy();
        item.setDamageValue(item.getDamageValue() + 1);
        return item;
    }


    @Override
    public boolean isValidRepairItem(ItemStack $$0, ItemStack $$1) {
        return $$1.is(Items.FLINT);
    }



    public ItemStack getRecipeRemainder(ItemStack itemStack) {
        if (itemStack.getDamageValue() > itemStack.getMaxDamage()) {
            return itemStack;
        }
        return getContainerItem(itemStack);

    }


    @Override
    public boolean hasCraftingRemainingItem() {
        return true;
    }

    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        if (itemStack.getDamageValue() > itemStack.getMaxDamage()) {
            return itemStack;
        }
        return getContainerItem(itemStack);
    }





}