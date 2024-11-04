package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class GrinderHeadItem extends Item {


    public GrinderHeadItem(int maxuses) {
        super(new Item.Properties()
                .stacksTo(1)
                .durability(maxuses));
    }

    public static ItemStack damageItem(ItemStack itemstack) {
        ItemStack grinder = itemstack.copy();
        grinder.setDamageValue(grinder.getDamageValue() + 1);
        if(grinder.getDamageValue() >= grinder.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return grinder;
    }

    @Override
    public boolean isValidRepairItem(ItemStack $$0, ItemStack $$1) {
        return $$1.is(Items.FLINT);
    }

}