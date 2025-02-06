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
        ItemStack grinder = itemstack.copy();
        grinder.setDamageValue(grinder.getDamageValue() + 1);
        if (grinder.getDamageValue() >= grinder.getMaxDamage()) {
            if(grinder.is(ModItems.GRINDERHEADNEHTERITE)){
                return GrinderHeadItem_Broken.getNewDefaultInstance(1);
            }else if(grinder.is(ModItems.GRINDERHEADUNOBTIANIUM)){
                return GrinderHeadItem_Broken.getNewDefaultInstance(2);
            }else{
                return GrinderHeadItem_Broken.getNewDefaultInstance(0);
            }
        }
        return grinder;
    }




    @Override
    public boolean isValidRepairItem(ItemStack $$0, ItemStack $$1) {
        return $$1.is(Items.FLINT);
    }
}