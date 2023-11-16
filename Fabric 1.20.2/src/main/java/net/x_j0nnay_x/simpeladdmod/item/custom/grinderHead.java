package net.x_j0nnay_x.simpeladdmod.item.custom;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class grinderHead extends Item {

    int MaxUses;
    public grinderHead(int maxuses){

        super(new Item.Settings()
                .maxCount(1)
                .maxDamage(maxuses));

        this.MaxUses = maxuses;

    }

    @Override
    public boolean hasRecipeRemainder() {
        return true;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        if(stack.getDamage() > this.MaxUses){
            return stack;
        }
        ItemStack ret = new ItemStack(this);
        ret.setDamage(stack.getDamage() + 1);
        return ret;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return this.MaxUses;
    }

}