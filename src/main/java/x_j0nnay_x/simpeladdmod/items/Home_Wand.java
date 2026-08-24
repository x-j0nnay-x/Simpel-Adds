package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.init.Mod_Config;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;

import java.util.EnumSet;
import java.util.function.Consumer;

public class Home_Wand extends Item {
    private static final int MAX_COOLDOWN = 240;

    public Home_Wand(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        super.inventoryTick(itemStack, level, owner, slot);

        Integer cooldown = itemStack.get(Mod_Registrys.Mod_Data_Componet.HOME_WAND_COOLDOWN.get());
        if (cooldown != null) {
            if (cooldown <= 1) {
                itemStack.remove(Mod_Registrys.Mod_Data_Componet.HOME_WAND_COOLDOWN.get());
            } else {
                itemStack.set(Mod_Registrys.Mod_Data_Componet.HOME_WAND_COOLDOWN.get(), cooldown - 1);
            }
        }
    }

    public boolean isUseable(ItemStack itemstack) {
        return itemstack.getDamageValue() < itemstack.getMaxDamage();
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            if (player.isCrouching()) {
                if (!hasData(itemStack)) {
                    setData(player, level);
                    player.sendOverlayMessage(Component.translatable("player_message.simpeladdmod.homewand.homeset"));
                } else {
                    removeData(itemStack);
                    player.sendOverlayMessage(Component.translatable("player_message.simpeladdmod.homewand.homeclear"));
                }
                return InteractionResult.SUCCESS;
            }

            if (isOnCooldown(itemStack)) {
                player.sendOverlayMessage(Component.translatable("player_message.simpeladdmod.homewand.cooldown"));
                return InteractionResult.SUCCESS;
            }

            if (!isUseable(itemStack)) {
                player.sendOverlayMessage(Component.translatable("tooltip.simpeladdmod.broken_item"));
                return InteractionResult.SUCCESS;
            }

            if (!hasData(itemStack)) {
                player.sendOverlayMessage(Component.translatable("player_message.simpeladdmod.homewand.homeless"));
                return InteractionResult.SUCCESS;
            }

            ResourceKey<Level> savedDimension = itemStack.get(Mod_Registrys.Mod_Data_Componet.DIMENSION.get());
            if (savedDimension != null && savedDimension.equals(level.dimension())) {
                itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                tellaport(player, itemStack);
            } else {
                itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                changeDim(player, itemStack);
            }
            brakeItem(itemStack, Mod_Config.HOME_WAND_BRAKES.getAsBoolean());
            return InteractionResult.SUCCESS;
        }

        return super.use(level, player, hand);
    }

    private boolean hasData(ItemStack stack) {
        return stack.get(Mod_Registrys.Mod_Data_Componet.COORDINATES.get()) != null
                && stack.get(Mod_Registrys.Mod_Data_Componet.DIMENSION.get()) != null;
    }

    private boolean isOnCooldown(ItemStack stack) {
        Integer cooldown = stack.get(Mod_Registrys.Mod_Data_Componet.HOME_WAND_COOLDOWN.get());
        return cooldown != null && cooldown > 0;
    }

    private void setData(Player player, Level level) {
        ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        BlockPos blockPos = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
        itemStack.set(Mod_Registrys.Mod_Data_Componet.COORDINATES.get(), blockPos);
        itemStack.set(Mod_Registrys.Mod_Data_Componet.DIMENSION.get(), level.dimension());
    }

    private void removeData(ItemStack itemStack) {
        itemStack.remove(Mod_Registrys.Mod_Data_Componet.COORDINATES.get());
        itemStack.remove(Mod_Registrys.Mod_Data_Componet.DIMENSION.get());
        itemStack.remove(Mod_Registrys.Mod_Data_Componet.HOME_WAND_COOLDOWN.get());
    }

    private void tellaport(Player player, ItemStack itemStack) {
        BlockPos blockPos = itemStack.get(Mod_Registrys.Mod_Data_Componet.COORDINATES.get());
        if (blockPos == null) {
            player.sendOverlayMessage(Component.translatable("player_message.simpeladdmod.homewand.homeless"));
            return;
        }

        player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
        itemStack.set(Mod_Registrys.Mod_Data_Componet.HOME_WAND_COOLDOWN.get(), MAX_COOLDOWN);
        double centXpos = blockPos.getCenter().x();
        double centzpos = blockPos.getCenter().z();
        double yPos = blockPos.getY() + 0.02;
        player.teleportTo(centXpos, yPos, centzpos);
        clearTeleportFallState(player);
    }

    private void changeDim(Player player, ItemStack itemStack) {
        Level level = player.level();
        BlockPos blockPos = itemStack.get(Mod_Registrys.Mod_Data_Componet.COORDINATES.get());
        ResourceKey<Level> targetDimension = itemStack.get(Mod_Registrys.Mod_Data_Componet.DIMENSION.get());
        if (blockPos == null || targetDimension == null || level.getServer() == null) {
            player.sendOverlayMessage(Component.translatable("player_message.simpeladdmod.homewand.homeless"));
            return;
        }

        ServerLevel serverLevel = level.getServer().getLevel(targetDimension);
        if (serverLevel == null) {
            player.sendOverlayMessage(Component.translatable("player_message.simpeladdmod.homewand.homeless"));
            return;
        }

        itemStack.set(Mod_Registrys.Mod_Data_Componet.HOME_WAND_COOLDOWN.get(), MAX_COOLDOWN);
        double centXpos = blockPos.getCenter().x();
        double centzpos = blockPos.getCenter().z();
        double yPos = blockPos.getY() + 0.02;
        player.teleportTo(serverLevel, centXpos, yPos, centzpos, EnumSet.noneOf(Relative.class), player.getYRot(), player.getXRot(), false);
        clearTeleportFallState(player);
    }

    private void clearTeleportFallState(Player player) {
        player.resetFallDistance();
        player.setDeltaMovement(player.getDeltaMovement().x, 0.0D, player.getDeltaMovement().z);
    }

    private Component getDimensionName(ResourceKey<Level> dimension) {
        return Component.literal(dimension.identifier().toString());
    }

    public static ItemStack brakeItem(ItemStack itemstack, boolean canBrake) {
        if(canBrake){
            itemstack.setDamageValue(itemstack.getDamageValue() + 1);
        }
        if(canUse(itemstack) && !canBrake){
            itemstack.setDamageValue(itemstack.getDamageValue() + 1);
        }
        return itemstack;
    }

    public static boolean canUse(ItemStack itemstack) {
        return itemstack.getDamageValue() < itemstack.getMaxDamage();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if (!isUseable(itemStack)) {
            builder.accept(Component.translatable("tooltip.simpeladdmod.broken_item"));
        }

        BlockPos location = itemStack.get(Mod_Registrys.Mod_Data_Componet.COORDINATES.get());
        if (location != null) {
            builder.accept(Component.translatable("tooltip.simpeladdmod.homewand.location", location.getX(), location.getY(), location.getZ()));
        }

        ResourceKey<Level> dimension = itemStack.get(Mod_Registrys.Mod_Data_Componet.DIMENSION.value());
        if (dimension != null) {
            builder.accept(Component.translatable("tooltip.simpeladdmod.homewand.dimension", getDimensionName(dimension)));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
