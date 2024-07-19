package net.x_j0nnay_x.simpeladd.item;


import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.x_j0nnay_x.simpeladd.core.ModItems;

public class GrinderHeadItem extends Item {

    int MaxUses;
    public GrinderHeadItem(int maxuses) {
        super(new Item.Properties()
                .stacksTo(1)
                .durability(maxuses));
        this.MaxUses = maxuses;
    }

    public ItemStack getContainerItem(ItemStack itemstack) {
        ItemStack item = itemstack.copy();
        item.setDamageValue(item.getDamageValue() + 1);
        return item;
    }


    @Override
    public boolean isValidRepairItem(ItemStack $$0, ItemStack $$1) {
        return $$1.is(Items.FLINT);
    }



    public ItemStack getRecipeRemainder(ItemStack itemStack) {
        if (itemStack.getDamageValue() > itemStack.getMaxDamage()) {
            return itemStack;
        }
        return getContainerItem(itemStack);

    }


    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        if (itemStack.getDamageValue() > itemStack.getMaxDamage()) {
            return itemStack;
        }
        return getContainerItem(itemStack);
    }
}