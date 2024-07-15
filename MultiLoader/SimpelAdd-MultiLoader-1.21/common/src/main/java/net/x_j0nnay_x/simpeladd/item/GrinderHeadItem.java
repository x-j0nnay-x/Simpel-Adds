package net.x_j0nnay_x.simpeladd.item;


import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GrinderHeadItem extends Item {

    int MaxUses;
    public GrinderHeadItem(int maxuses){

        super(new Properties()
                .stacksTo(1)
                .durability(maxuses));
        this.MaxUses = maxuses;

    }

    @Override
    public boolean hasCraftingRemainingItem() {
        return true;
    }



    @Override
    public void onCraftedBy(ItemStack itemstack, Level pLevel, Player pPlayer) {
        getCraftingRemainingItem(itemstack);
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
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return this.MaxUses;
    }


}