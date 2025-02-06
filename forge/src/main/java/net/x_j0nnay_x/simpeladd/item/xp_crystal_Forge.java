package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModDataComponentTypesForge;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

public class xp_crystal_Forge extends Item {


    public xp_crystal_Forge() {
        super(new Properties()
                .stacksTo(1)
                .fireResistant()
        );
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(stack.get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()) != null) {
            if (stack.get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()).floatValue() >= 1.0f) {
                levelUP(stack);
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack crystalMain = player.getMainHandItem();
        ItemStack crystalOff = player.getOffhandItem();
        ItemStack itemStack = crystalMain.getItem() == this ? crystalMain : crystalOff;

        if(itemStack.get(DataComponents.CUSTOM_DATA) == null){
            setPlayerData(player, itemStack);
        }
        if(player.getUUID().equals(itemStack.get(DataComponents.CUSTOM_DATA).copyTag().getUUID("PlayerUUID"))) {
            if (!level.isClientSide) {
                if (player.isCrouching()) {
                    if (crystalMain.getItem() == this) {
                        addLevel(itemStack, player);
                        addProgress(itemStack, player);
                    }
                    if (crystalOff.getItem() == this) {
                        removeAll(itemStack, player);
                    }
                }
                if (!player.isCrouching()) {
                    if (crystalOff.getItem() == this) {
                        removeOneorTen(itemStack, player, 1);
                    }
                    if (crystalMain.getItem() == this) {
                        removeOneorTen(itemStack, player, 10);
                    }
                }
            }
        }
            return super.use(level, player, usedHand);
    }
    private void setPlayerData(Player player,  ItemStack itemStack){
        itemStack.set(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag(){{
            putUUID("PlayerUUID", player.getUUID());
            putString("PlayerName", player.getName().getString());
        }}));
    }

    private void  addLevel(ItemStack itemStack, Player player){
        if(itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()) != null){
            itemStack.set(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get(), itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()).intValue() + player.experienceLevel);
            player.giveExperienceLevels(-player.experienceLevel);
        }else {
            itemStack.set(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get(), player.experienceLevel);
            player.giveExperienceLevels(-player.experienceLevel);
        }
    }

    private void addProgress(ItemStack itemStack, Player player){
        if(itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()) != null){
            itemStack.set(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get(), itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()).floatValue() + player.experienceProgress);
            player.experienceProgress = 0;
        }else {
            itemStack.set(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get(), player.experienceProgress);
            player.experienceProgress = 0;
        }
    }

    private void levelUP(ItemStack itemStack){
        itemStack.set(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get(), itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()).intValue() + 1);
        itemStack.set(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get(), itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()).floatValue() - 1.0f);
    }

    private void removeAll(ItemStack itemStack, Player player){
        if(itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()) != null){
            player.giveExperienceLevels(itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()).intValue());
            itemStack.set(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get(), 0);
        }
    }

    private void removeOneorTen(ItemStack itemStack, Player player, int amount){
        if(itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()) != null && itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()).intValue() >= amount){
            player.giveExperienceLevels(amount);
            itemStack.set(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get(), itemStack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()).intValue() - amount);
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()) != null || stack.get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()) != null;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return ChatFormatting.GREEN.getColor();
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if(stack.get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()) != null){
            if(stack.get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()).floatValue() > 0.0f){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
      if(stack.get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()) != null) {
          return Math.round(stack.get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()).floatValue() * 10);
      }
     return super.getBarWidth(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(stack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()) != null){
            NumberFormat format = NumberFormat.getInstance();
            String xpL = format.format(stack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_LEVEL.get()).intValue());
            String title = Component.translatable("item.simpeladdmod.xp_crystal.xp").getString();
            tooltipComponents.add(Component.literal(title + xpL));
        }
        if(stack.getComponents().get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()) != null){
            String xpP = String.valueOf(Math.round(stack.get(ModDataComponentTypesForge.XP_CRYSTAL_PROGRESS.get()).floatValue() * 100));
            String titleP = Component.translatable("item.simpeladdmod.xp_crystal.xp_progress").getString();
            tooltipComponents.add(Component.literal(titleP + xpP + "%"));
        }
        if(stack.get(DataComponents.CUSTOM_DATA) != null){
            String owner = Component.translatable("item.simpeladdmod.xp_crystal.owner").getString();
            String ownerName = stack.get(DataComponents.CUSTOM_DATA).copyTag().getString("PlayerName");
            tooltipComponents.add(Component.literal(owner + ownerName));
        }
        tooltipComponents.add(Component.translatable("item.simpeladdmod.xp_crystal.tooltipcrouch"));
        tooltipComponents.add(Component.translatable("item.simpeladdmod.xp_crystal.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
