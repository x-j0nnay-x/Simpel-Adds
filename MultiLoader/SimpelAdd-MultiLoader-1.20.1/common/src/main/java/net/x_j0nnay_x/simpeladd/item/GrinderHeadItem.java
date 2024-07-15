package net.x_j0nnay_x.simpeladd.item;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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

    public ItemStack getRecipeRemainder(ItemStack itemStack) {
        if (itemStack.getDamageValue() > this.MaxUses) {
            return itemStack;
        }
        ItemStack ret = new ItemStack(this);
        ret.setDamageValue(itemStack.getDamageValue() + 1);
        return ret;
    }


    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {

        if (itemStack.getDamageValue() > this.MaxUses) {
            return itemStack;
        }
        ItemStack ret = new ItemStack(this);
        ret.setDamageValue(itemStack.getDamageValue() + 1);
        return ret;

    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return this.MaxUses;
    }
}