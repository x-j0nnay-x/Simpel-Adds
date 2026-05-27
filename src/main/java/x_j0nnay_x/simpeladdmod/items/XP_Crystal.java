package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;

import java.util.UUID;
import java.util.function.Consumer;

public class XP_Crystal extends Item {

    public XP_Crystal(Properties properties) {
        super(properties.stacksTo(1).fireResistant());
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack crystalMain = player.getMainHandItem();
        ItemStack crystalOff = player.getOffhandItem();
        ItemStack itemStack = crystalMain.getItem() == this ? crystalMain : crystalOff;
            if (!level.isClientSide()) {
                if (player.isCrouching()) {
                    if (crystalMain.getItem() == this) {
                        setData(player, itemStack);
                       return InteractionResult.SUCCESS;
                    }
                    if (crystalOff.getItem() == this) {
                        giveXP(itemStack, player, 0, true);
                        return InteractionResult.SUCCESS;
                    }
                }
                if (!player.isCrouching()) {
                    if (crystalOff.getItem() == this) {
                        giveXP(itemStack, player, 1, false);
                        return InteractionResult.SUCCESS;
                    }
                    if (crystalMain.getItem() == this) {
                        giveXP(itemStack, player, 10, false);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        return InteractionResult.FAIL;
    }
    private int getStoredLevels(ItemStack stack) {
        Integer levels = stack.getComponents().get(Mod_Registrys.Mod_Data_Componet.XP_LEVELS.get());
        return levels == null ? 0 : levels;
    }

    private float getStoredProgress(ItemStack stack) {
        Float progress = stack.getComponents().get(Mod_Registrys.Mod_Data_Componet.XP_PERCENT.get());
        return progress == null ? 0.0f : progress;
    }

    private boolean hasData(ItemStack stack) {
        return getStoredLevels(stack) > 0 || getStoredProgress(stack) > 0.0f;
    }

    private void setData(Player player,  ItemStack itemStack){
        int storedlevel = getStoredLevels(itemStack);
        float storedprogress = getStoredProgress(itemStack);

        if (itemStack.getComponents().get(Mod_Registrys.Mod_Data_Componet.USER_UUID.get()) == null) {
            itemStack.set(Mod_Registrys.Mod_Data_Componet.USER_UUID.get(), player.getUUID());
        }
        if (itemStack.getComponents().get(Mod_Registrys.Mod_Data_Componet.USER_NAME.get()) == null) {
            itemStack.set(Mod_Registrys.Mod_Data_Componet.USER_NAME.get(), player.getName().getString());
        }

        int addedlevel = storedlevel + player.experienceLevel;
        float addedprogress = storedprogress + player.experienceProgress;
        int newLevels = getLevels(addedlevel, addedprogress);
        float newProgress = getProgress(addedprogress);

        itemStack.remove(Mod_Registrys.Mod_Data_Componet.XP_LEVELS.get());
        itemStack.remove(Mod_Registrys.Mod_Data_Componet.XP_PERCENT.get());

        if (newLevels > 0) {
            itemStack.set(Mod_Registrys.Mod_Data_Componet.XP_LEVELS.get(), newLevels);
        }
        if (newProgress > 0.0f) {
            itemStack.set(Mod_Registrys.Mod_Data_Componet.XP_PERCENT.get(), newProgress);
        }

        player.giveExperienceLevels(-player.experienceLevel);
        player.experienceProgress = 0.0f;
    }

    private void giveXP(ItemStack itemStack, Player player, int amount, boolean all){
        UUID playerUUID = itemStack.getComponents().get(Mod_Registrys.Mod_Data_Componet.USER_UUID.get());
        if (playerUUID == null || !playerUUID.equals(player.getUUID())) {
            return;
        }

        int storedlevel = getStoredLevels(itemStack);
        float storedprogress = getStoredProgress(itemStack);
        if (storedlevel <= 0 && storedprogress <= 0.0f) {
            return;
        }

        if (all) {
            player.giveExperienceLevels(storedlevel);
            if (storedprogress > 0.0f) {
                int progressPoints = Math.max(1, Math.round(player.getXpNeededForNextLevel() * storedprogress));
                player.giveExperiencePoints(progressPoints);
            }

            itemStack.remove(Mod_Registrys.Mod_Data_Componet.XP_LEVELS.get());
            itemStack.remove(Mod_Registrys.Mod_Data_Componet.XP_PERCENT.get());
            return;
        }

        if (storedlevel >= amount) {
            player.giveExperienceLevels(amount);
            int newLevel = storedlevel - amount;

            if (newLevel > 0) {
                itemStack.set(Mod_Registrys.Mod_Data_Componet.XP_LEVELS.get(), newLevel);
            } else {
                itemStack.remove(Mod_Registrys.Mod_Data_Componet.XP_LEVELS.get());
            }

            if (storedprogress > 0.0f) {
                itemStack.set(Mod_Registrys.Mod_Data_Componet.XP_PERCENT.get(), storedprogress);
            } else {
                itemStack.remove(Mod_Registrys.Mod_Data_Componet.XP_PERCENT.get());
            }
        }
    }



    private int getLevels(int level, float progress){
        if(progress >= 1.0f){
            return level + 1;
        }else{
            return level;
        }
    }
    private float getProgress(float progress){
        if(progress >= 1.0f){
            return progress - 1.0f;
        }else{
            return progress;
        }
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return getStoredLevels(itemStack) > 0 || getStoredProgress(itemStack) > 0.0f;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return ChatFormatting.GREEN.getColor();
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getStoredProgress(stack) > 0.0f;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        float progress = getStoredProgress(stack);
        if (progress > 0.0f) {
            return Math.round(progress * 10);
        }
        return super.getBarWidth(stack);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(hasData(itemStack)) {
            String xplevel = String.valueOf(getStoredLevels(itemStack));
            String xptitle = Component.translatable("tooltip.simpeladdmod.xp_crystal.xp").getString();
            builder.accept(Component.literal(xptitle + xplevel));
            String xpP = String.valueOf(Math.round(getStoredProgress(itemStack) * 100));
            String titleP = Component.translatable("tooltip.simpeladdmod.xp_crystal.xp_progress").getString();
            builder.accept(Component.literal(titleP + xpP + "%"));
            String owner = Component.translatable("tooltip.simpeladdmod.xp_crystal.owner").getString();
            String ownerName = itemStack.getComponents().get(Mod_Registrys.Mod_Data_Componet.USER_NAME.get());
            builder.accept(Component.literal(owner + (ownerName == null ? "Unknown" : ownerName)));
        }
        builder.accept(Component.translatable("tooltip.simpeladdmod.xp_crystal.tooltipcrouch"));
        builder.accept(Component.translatable("tooltip.simpeladdmod.xp_crystal.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
