package net.x_j0nnay_x.simpeladd.item;



import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.x_j0nnay_x.simpeladd.core.ModTags;

import javax.annotation.Nullable;


public class GrinderHeadItem extends Item{

    int MaxUses;

    public GrinderHeadItem(int maxuses) {
        super(new Properties()
                .stacksTo(1)
                .durability(maxuses));
        this.MaxUses = maxuses;
    }

    public static ItemStack brakeItem(ItemStack itemstack) {
        ItemStack retval = itemstack.copy();
        retval.setDamageValue(retval.getDamageValue() + 1);
        if (retval.getDamageValue() >= retval.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return retval;
    }

    public static ItemStack getRemainderItem(ItemStack itemStack) {
        // return getContainerItem(itemStack);
        return  brakeItem(itemStack);
    }

    @Override
    public boolean isValidRepairItem(ItemStack $$0, ItemStack $$1) {
        return $$1.is(Items.FLINT);
    }




}