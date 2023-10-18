package net.x_j0nnay_x.simpeladdmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class grinderHead extends Item {

    int MaxUses;
    public grinderHead(int maxuses){

        super(new Item.Properties()
                .stacksTo(1)
                .durability(maxuses));
        this.MaxUses = maxuses;

    }

    @Override
    public boolean hasCraftingRemainingItem() {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {

        if (itemstack.getDamageValue() > this.MaxUses) {
            return itemstack;
        }
        ItemStack ret = new ItemStack(this);
        ret.setDamageValue(itemstack.getDamageValue() + 1);
        return ret;

        //  return new ItemStack(this);
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return this.MaxUses;
    }
}