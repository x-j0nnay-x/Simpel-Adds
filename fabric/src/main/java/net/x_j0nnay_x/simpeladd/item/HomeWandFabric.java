package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModDataComponentTypesFabric;


import java.util.List;

public class HomeWandFabric extends Item {

    private int cooldown;
    private boolean oncooldown;
    private int maxcooldown = 240;


    public HomeWandFabric(int maxUese) {
        super(new Item.Properties()
                .durability(maxUese)
        );
    }

    public boolean isUseable(ItemStack itemstack) {
        return itemstack.getDamageValue() < itemstack.getMaxDamage();
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
                if (itemStack.getComponents().get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS) == null) {
                    setData(player);
                    player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeset"), true);
                }else{
                    removeData(player);
                    player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeclear"), true);
                }
            }
            if (!this.oncooldown && !player.isCrouching()) {
                if (isUseable(itemStack)) {
                    if (itemStack.getComponents().get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS) != null && itemStack.getComponents().get(DataComponents.CUSTOM_DATA) != null) {

                        if (itemStack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag().getString("dim").toString().matches(level.dimension().location().getPath().toString())) {
                            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                            tellaport(player);
                            return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
                        } else {
                            if (itemStack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag().getString("dim").toString().matches("overworld") ||
                                    itemStack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag().getString("dim").toString().matches("the_nether") ||
                                    itemStack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag().getString("dim").toString().matches("the_end")) {
                                itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                                changeDim(player);
                                return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
                            } else {
                                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.wrongdim"), true);
                            }
                            return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
                        }

                    } else {
                        player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeless"), true);
                    }
                }
            }
            if (this.oncooldown) {
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.cooldown"), true);
            }
            if (!isUseable(itemStack)) {
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.repair"), true);
            }
        }
        return super.use(level, player, hand);
    }
    private void setData(Player player){
        ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        itemStack.set(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS, new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()));
        itemStack.set(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag() {{
            putString("dim", player.level().dimension().location().getPath().toString());
        }}));
    }
    private void removeData(Player player){
        ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        itemStack.remove(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS);
        itemStack.remove(DataComponents.CUSTOM_DATA);
    }

    private void tellaport(Player player) {
        ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        double X = (double) itemStack.getComponents().get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS).getX() + 0.5;
        double Y = (double) itemStack.getComponents().get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS).getY();
        double Z = (double) itemStack.getComponents().get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS).getZ() + 0.5;
        player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
        player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.use"), true);
        this.oncooldown = true;
        player.teleportTo( X, Y, Z);


    }
    private void changeDim(Player player) {
        ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        Level level = player.level();
        double X = (double) itemStack.getComponents().get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS).getX() + 0.5;
        double Y = (double) itemStack.getComponents().get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS).getY();
        double Z = (double) itemStack.getComponents().get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS).getZ() + 0.5;
        String homeDim = itemStack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag().getString("dim").toString();
        ResourceLocation dimReLocation = level.dimension().location().withPath(homeDim);
        ResourceKey<Level> dim = ResourceKey.create(Registries.DIMENSION, dimReLocation);
        ServerLevel servelLevel = level.getServer().getLevel(dim);
        this.oncooldown = true;
        player.teleportTo(servelLevel, X, Y, Z, RelativeMovement.ALL , 0.0f, 0.0f);
    }

    @Override
    public void appendHoverText(ItemStack $$0, TooltipContext $$1, List<Component> $$2, TooltipFlag $$3) {
        if($$0.getComponents().get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS) != null){
            String title = Component.translatable("item.simpeladdmod.homewand.homelocal").getString();
            $$2.add(Component.literal(title + $$0.get(ModDataComponentTypesFabric.HOMEWAND_COMPNENTS)));
            if($$0.getComponents().get(DataComponents.CUSTOM_DATA) != null) {
                $$2.add(Component.literal($$0.get(DataComponents.CUSTOM_DATA).copyTag().getString("dim").toString()));
            }
            if(this.oncooldown) {
                $$2.add(Component.translatable("item.simpeladdmod.homewand.cooldown"));
            }
        } else {
            $$2.add(Component.translatable("item.simpeladdmod.homewand.homeless"));
        }
        super.appendHoverText($$0, $$1, $$2, $$3);
    }
}
