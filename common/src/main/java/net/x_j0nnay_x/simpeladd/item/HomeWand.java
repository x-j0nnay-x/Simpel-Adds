package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomeWand extends Item {

    private int cooldown;
    private int maxcooldown = 240;
    private int X;
    private int Y;
    private int Z;

    public HomeWand(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack $$0, Level $$1, Entity $$2, int $$3, boolean $$4) {
        setIntValues($$0);
        if($$0.getOrCreateTag().getBoolean("oncooldown") == true) {
            this.cooldown ++;
            if(this.cooldown == 10) {
                $$0.getOrCreateTag().putBoolean("woosh", false);
            }
            if(this.cooldown >= this.maxcooldown) {
                $$0.getOrCreateTag().putBoolean("oncooldown", false);
                this.cooldown = 0;
            }
        }

        super.inventoryTick($$0, $$1, $$2, $$3, $$4);
    }
    private void setIntValues(ItemStack itemStack){
        this.X = (int) itemStack.getOrCreateTag().getDouble("homeX");
        this.Y = (int) itemStack.getOrCreateTag().getDouble("homeY");
        this.Z = (int) itemStack.getOrCreateTag().getDouble("homeZ");
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack  = player.getItemInHand(hand);
        if(player.isCrouching()) {
            if(!itemStack.getOrCreateTag().getBoolean("homeSet")) {
                setHome(itemStack, player);
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeset"), true);
            }else{
                clearHome(itemStack);
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeclear"), true);
            }
        }else {
            tryTellaport(player, itemStack);
        }
        return super.use(level, player, hand);
    }

    public void tryTellaport(Player player, ItemStack itemStack) {
        if (!itemStack.getOrCreateTag().getBoolean("oncooldown") && itemStack.getOrCreateTag().getBoolean("homeSet")) {
            itemStack.getOrCreateTag().putBoolean("oncooldown", true);
            itemStack.getOrCreateTag().putBoolean("woosh", true);
            player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            player.teleportTo(itemStack.getOrCreateTag().getDouble("homeX"), itemStack.getOrCreateTag().getDouble("homeY"), itemStack.getOrCreateTag().getDouble("homeZ"));
            player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.use"), true);
        } if(itemStack.getOrCreateTag().getBoolean("oncooldown") && !itemStack.getOrCreateTag().getBoolean("woosh"))
            if (!itemStack.getOrCreateTag().getBoolean("homeSet")) {
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeless"), true);
            } else {
                player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.cooldown"), true);
            }
            if (!itemStack.getOrCreateTag().getBoolean("homeSet")) {
            player.displayClientMessage(Component.translatable("item.simpeladdmod.homewand.homeless"), true);
            }
        return;
    }



    public void clearHome(ItemStack itemStack) {
        itemStack.getOrCreateTag().putBoolean("homeSet", false);
        itemStack.getOrCreateTag().remove("homeX");
        itemStack.getOrCreateTag().remove("homeY");
        itemStack.getOrCreateTag().remove("homeZ");
    }

    public void setHome(ItemStack itemStack, Player player) {
        if(!player.onGround()) {
            return;
        }
            itemStack.getOrCreateTag().putDouble("homeX", player.getX());
            itemStack.getOrCreateTag().putDouble("homeY", player.getY());
            itemStack.getOrCreateTag().putDouble("homeZ", player.getZ());
            itemStack.getOrCreateTag().putBoolean("homeSet", true);

    }


    @Override
    public void appendHoverText(ItemStack $$0, @Nullable Level $$1, List<Component> $$2, TooltipFlag $$3) {
        if($$0.getOrCreateTag().getBoolean("homeSet") == true) {
            String title = Component.translatable("item.simpeladdmod.homewand.homelocal").getString();
            $$2.add(Component.literal(title +"X " + this.X + " : Y " + this.Y + " : Z " + this.Z));
            if($$0.getOrCreateTag().getBoolean("oncooldown") == true) {
                $$2.add(Component.translatable("item.simpeladdmod.homewand.cooldown"));
            }
        } else {
            $$2.add(Component.translatable("item.simpeladdmod.homewand.homeless"));
        }
        super.appendHoverText($$0, $$1, $$2, $$3);
    }
}
