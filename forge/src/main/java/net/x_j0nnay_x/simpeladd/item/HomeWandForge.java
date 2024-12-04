package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModDataComponentTypesForge;

import java.util.List;

public class HomeWandForge extends Item {

    private int cooldown;
    private boolean oncooldown;
    private int maxcooldown = 240;
    int X;
    int Y;
    int Z;

    public HomeWandForge(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack $$0, Level $$1, Entity $$2, int $$3, boolean $$4) {
        if(this.oncooldown) {
            this.cooldown ++;
            if(this.cooldown >= this.maxcooldown) {
                this.oncooldown = false;
                this.cooldown = 0;
            }
        }

        super.inventoryTick($$0, $$1, $$2, $$3, $$4);
    }

    @Override
    public InteractionResult useOn(UseOnContext $$0) {
        ItemStack itemStack = $$0.getItemInHand();
        Player player = $$0.getPlayer();

        return super.useOn($$0);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide) {
            ItemStack itemStack = player.getItemInHand(hand);
            if (player.isCrouching()) {
                if (itemStack.getComponents().get(ModDataComponentTypesForge.HOMEWAND_COMPNENTS.get()) == null) {
                    itemStack.set(ModDataComponentTypesForge.HOMEWAND_COMPNENTS.get(), new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()));
                    player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeset"), true);
                }else{
                    itemStack.remove(ModDataComponentTypesForge.HOMEWAND_COMPNENTS.get());
                    player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeclear"), true);
                }
            }
            if (!this.oncooldown && !player.isCrouching()) {
                if(itemStack.getComponents().get(ModDataComponentTypesForge.HOMEWAND_COMPNENTS.get()) != null) {
                    tellaport(player, itemStack);
                    return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
                }else {
                    player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeless"), true);
                }
            }
            if (this.oncooldown) {
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.cooldown"), true);
            }
        }
        return super.use(level, player, hand);
    }

    private void tellaport(Player player, ItemStack itemStack) {

        double X = (double) itemStack.getComponents().get(ModDataComponentTypesForge.HOMEWAND_COMPNENTS.get()).getX() + 0.5;
        double Y = (double) itemStack.getComponents().get(ModDataComponentTypesForge.HOMEWAND_COMPNENTS.get()).getY();
        double Z = (double) itemStack.getComponents().get(ModDataComponentTypesForge.HOMEWAND_COMPNENTS.get()).getZ() + 0.5;
        player.teleportTo( X, Y, Z);
        player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
        player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.use"), true);
        this.oncooldown = true;

    }


    @Override
    public void appendHoverText(ItemStack $$0, TooltipContext $$1, List<Component> $$2, TooltipFlag $$3) {
        if($$0.getComponents().get(ModDataComponentTypesForge.HOMEWAND_COMPNENTS.get()) != null){
            String title = Component.translatable("item.simpeladdmod.homewand.homelocal").getString();
            $$2.add(Component.literal(title + $$0.get(ModDataComponentTypesForge.HOMEWAND_COMPNENTS.get())));
            if(this.oncooldown) {
                $$2.add(Component.translatable("item.simpeladdmod.homewand.cooldown"));
            }
        } else {
            $$2.add(Component.translatable("item.simpeladdmod.homewand.homeless"));
        }
        super.appendHoverText($$0, $$1, $$2, $$3);
    }
}
