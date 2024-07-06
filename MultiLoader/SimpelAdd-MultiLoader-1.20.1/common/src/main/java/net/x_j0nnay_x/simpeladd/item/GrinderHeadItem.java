package net.x_j0nnay_x.simpeladd.item;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GrinderHeadItem extends Item {

    int MaxUses;
    public GrinderHeadItem(int maxuses){

        super(new Item.Properties()
                .stacksTo(1)
                .durability(maxuses));
        this.MaxUses = maxuses;

    }

    @Override
    public boolean hasCraftingRemainingItem() {
        return true;
    }


    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {

        if (itemstack.getDamageValue() > this.MaxUses) {
            return itemstack;
        }
        ItemStack ret = new ItemStack(this);
        ret.setDamageValue(itemstack.getDamageValue() + 1);
        return ret;

    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return this.MaxUses;
    }
}