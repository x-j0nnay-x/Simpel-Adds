package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomeWand extends Item {

    private ResourceKey teleportLevel;
    private int cooldown;
    private int maxcooldown = 240;



    public HomeWand(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void inventoryTick(ItemStack $$0, Level $$1, Entity $$2, int $$3, boolean $$4) {
        if (!$$1.isClientSide) {
            if ($$0.getOrCreateTag().getBoolean("oncooldown")) {
                this.cooldown++;
                if (this.cooldown >= maxcooldown) {
                    $$0.getOrCreateTag().putBoolean("oncooldown", false);
                    this.cooldown = 0;
                }
            }
        }
        super.inventoryTick($$0, $$1, $$2, $$3, $$4);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            ItemStack itemStack = player.getItemInHand(hand);
            if (player.isCrouching()) {
                if (!itemStack.getOrCreateTag().getBoolean("homeSet")) {
                    setHome(itemStack, player);
                    player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeset"), true);
                } else {
                    clearHome(itemStack);
                    player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeclear"), true);
                }
            } else {
                if (itemStack.getOrCreateTag().getBoolean("homeSet")) {
                    tryTellaport(player, itemStack);
                } else {
                    player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeless"), true);
                }
            }
        }
        return super.use(level, player, hand);
    }

    public void tryTellaport(Player player, ItemStack itemStack) {
        Level level = player.level();
        ResourceKey<Level> dim = level.dimension();
        if (dim.location().toString().matches(itemStack.getOrCreateTag().getString("homeDim"))) {
            if (!itemStack.getOrCreateTag().getBoolean("oncooldown")) {
                itemStack.getOrCreateTag().putBoolean("oncooldown", true);
                player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                player.teleportTo(itemStack.getOrCreateTag().getDouble("homeX"), itemStack.getOrCreateTag().getDouble("homeY"), itemStack.getOrCreateTag().getDouble("homeZ"));
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.use"), true);
            }
            if (itemStack.getOrCreateTag().getBoolean("oncooldown")) {
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.cooldown"), true);
            }
        } else {
            if(dim.location().getNamespace().matches(itemStack.getOrCreateTag().getString("homeNameSpace"))) {
                changeDim(player, itemStack);
            }else{
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.wrongdim"), true);
            }
        }
    }

    public void changeDim(Player player, ItemStack itemStack){
        Level level = player.level();
        String homeDim = itemStack.getOrCreateTag().getString("homeDim");
        String dimNameSpace = itemStack.getOrCreateTag().getString("homeNameSpace");
        String dimName = homeDim.replace(dimNameSpace + ":", "");
        ResourceLocation dimReLocation = level.dimensionTypeId().location().withPath(dimName);
        ResourceKey<Level> dim = ResourceKey.create(Registries.DIMENSION, dimReLocation);
        ServerLevel servelLevel = level.getServer().getLevel(dim);
        player.teleportTo(servelLevel, itemStack.getOrCreateTag().getDouble("homeX"), itemStack.getOrCreateTag().getDouble("homeY"), itemStack.getOrCreateTag().getDouble("homeZ"), RelativeMovement.ALL , 0.0f, 0.0f);
    }

    public void clearHome(ItemStack itemStack) {
        itemStack.getOrCreateTag().putBoolean("homeSet", false);
        itemStack.getOrCreateTag().remove("homeX");
        itemStack.getOrCreateTag().remove("homeY");
        itemStack.getOrCreateTag().remove("homeZ");
        itemStack.getOrCreateTag().remove("homeDim");
    }

    public void setHome(ItemStack itemStack, Player player) {
        Level level = player.level();
        if (!player.onGround()) {
            return;
        }
        itemStack.getOrCreateTag().putDouble("homeX", player.getX());
        itemStack.getOrCreateTag().putDouble("homeY", player.getY());
        itemStack.getOrCreateTag().putDouble("homeZ", player.getZ());
        itemStack.getOrCreateTag().putString("homeDim", level.dimension().location().toString());
        itemStack.getOrCreateTag().putString("homeNameSpace", level.dimension().location().getNamespace());
        itemStack.getOrCreateTag().putBoolean("homeSet", true);
    }



    @Override
    public void appendHoverText(ItemStack $$0, @Nullable Level $$1, List<Component> $$2, TooltipFlag $$3) {
        if($$0.getOrCreateTag().getBoolean("homeSet")) {
            String title = Component.translatable("item.simpeladdmod.homewand.homelocal").getString();
            $$2.add(Component.literal(title +"X " + (int) $$0.getOrCreateTag().getDouble("homeX") + " : Y " + (int) $$0.getOrCreateTag().getDouble("homeY") + " : Z " + (int) $$0.getOrCreateTag().getDouble("homeZ")));
            $$2.add(Component.literal($$0.getOrCreateTag().getString("homeDim")));
            if($$0.getOrCreateTag().getBoolean("oncooldown")) {
                $$2.add(Component.translatable("item.simpeladdmod.homewand.cooldown"));
            }
        } else {
            $$2.add(Component.translatable("item.simpeladdmod.homewand.homeless"));
        }
        super.appendHoverText($$0, $$1, $$2, $$3);
    }
}
