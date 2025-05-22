package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.item.*;
import net.x_j0nnay_x.simpeladd.core.ModItems;

public class GrinderHeadItem extends Item{


    public GrinderHeadItem(int maxuses) {
        super(new Properties()
                .stacksTo(1)
                .durability(maxuses));
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
        return  brakeItem(itemStack);
    }

    @Override
    public boolean isValidRepairItem(ItemStack $$0, ItemStack $$1) {
        return $$1.is(Items.FLINT);
    }
}