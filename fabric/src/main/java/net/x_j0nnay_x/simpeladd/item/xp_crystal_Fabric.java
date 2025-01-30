package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModDataComponentTypesFabric;

import java.util.List;

public class xp_crystal_Fabric extends Item {


    public xp_crystal_Fabric() {
        super(new Properties()
                .stacksTo(1)
                .fireResistant()
        );

    }





    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(stack.get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS) != null) {
            if (stack.get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS).floatValue() >= 1.0f) {
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
            return super.use(level, player, usedHand);

    }


    private void  addLevel(ItemStack itemStack, Player player){
        if(itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL) != null){
            itemStack.set(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL, itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL).intValue() + player.experienceLevel);
            player.giveExperienceLevels(-player.experienceLevel);
        }else {
            itemStack.set(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL, player.experienceLevel);
            player.giveExperienceLevels(-player.experienceLevel);
        }
    }
    private void addProgress(ItemStack itemStack, Player player){
        if(itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS) != null){
            itemStack.set(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS, itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS).floatValue() + player.experienceProgress);
            player.experienceProgress = 0;
        }else {
            itemStack.set(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS, player.experienceProgress);
            player.experienceProgress = 0;
        }
    }

    private void levelUP(ItemStack itemStack){
        itemStack.set(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL, itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL).intValue() + 1);
        itemStack.set(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS, itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS).floatValue() - 1.0f);
    }

    private void removeAll(ItemStack itemStack, Player player){
        if(itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL) != null){
            player.giveExperienceLevels(itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL).intValue());
            itemStack.set(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL, 0);
        }

    }

    private void removeOneorTen(ItemStack itemStack, Player player, int amount){
        if(itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL) != null && itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL).intValue() >= amount){
            player.giveExperienceLevels(amount);
            itemStack.set(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL, itemStack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL).intValue() - amount);
        }

    }


    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL) != null || stack.get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS) != null;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return ChatFormatting.GREEN.getColor();
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if(stack.get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS) != null){
            if(stack.get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS).floatValue() > 0.0f){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
      if(stack.get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS) != null) {
          return Math.round(stack.get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS).floatValue() * 10);
      }
     return super.getBarWidth(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(stack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL) != null){
            String xpL = String.valueOf(stack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_LEVEL).intValue());
            String title = Component.translatable("item.simpeladdmod.xp_crystal.xp").getString();
            tooltipComponents.add(Component.literal(title + xpL));
        }
        if(stack.getComponents().get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS) != null){
            String xpP = String.valueOf(Math.round(stack.get(ModDataComponentTypesFabric.XP_CRYSTAL_PROGRESS).floatValue() * 100));
            String titleP = Component.translatable("item.simpeladdmod.xp_crystal.xp_progress").getString();
            tooltipComponents.add(Component.literal(titleP + xpP + "%"));
        }
        tooltipComponents.add(Component.translatable("item.simpeladdmod.xp_crystal.tooltipcrouch"));
        tooltipComponents.add(Component.translatable("item.simpeladdmod.xp_crystal.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
