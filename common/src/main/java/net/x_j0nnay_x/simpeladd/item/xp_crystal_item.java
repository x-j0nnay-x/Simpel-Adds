package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

public class xp_crystal_item extends Item {
    int stored_XP_level;
    float stored_XP_progress;

    public xp_crystal_item() {
        super(new Properties());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (stored_XP_progress >= 1.0f) {
            stored_XP_level += 1;
            stored_XP_progress -= 1.0f;
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack crystalMain = player.getMainHandItem();
        ItemStack crystalOff = player.getOffhandItem();
        if (!level.isClientSide) {
            if (player.isCrouching()) {
                if (crystalMain.getItem() == this) {
                    stored_XP_level += player.experienceLevel;
                    stored_XP_progress +=  player.experienceProgress;
                    player.giveExperienceLevels(-player.experienceLevel);
                    player.experienceProgress = 0;
                }
                if (crystalOff.getItem() == this && stored_XP_level > 0) {
                    player.giveExperienceLevels(stored_XP_level);
                    stored_XP_level = 0;
                }

            }
            if (!player.isCrouching()) {
                if (crystalOff.getItem() == this && stored_XP_level > 0) {
                    player.giveExperienceLevels(1);
                    stored_XP_level -= 1;
                }
                if (crystalMain.getItem() == this && stored_XP_level > 10) {
                    player.giveExperienceLevels(10);
                    stored_XP_level -= 10;
                }
            }
        }
            return super.use(level, player, usedHand);

    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return stored_XP_level > 0 || stored_XP_progress > 0.0f;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return ChatFormatting.GREEN.getColor();
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if(stored_XP_progress > 0.0f){
            return true;
        }
        return false;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(stored_XP_progress * 10);

    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        String xpP = String.valueOf(Math.round(stored_XP_progress * 100));
        String xpL = String.valueOf(stored_XP_level);
        String titleP = Component.translatable("item.simpeladdmod.xp_crystal.xp_progress").getString();
        tooltipComponents.add(Component.literal(titleP + xpP + "%"));
        String title = Component.translatable("item.simpeladdmod.xp_crystal.xp").getString();
        tooltipComponents.add(Component.literal(title + xpL));
        tooltipComponents.add(Component.translatable("item.simpeladdmod.xp_crystal.tooltipcrouch"));
        tooltipComponents.add(Component.translatable("item.simpeladdmod.xp_crystal.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
