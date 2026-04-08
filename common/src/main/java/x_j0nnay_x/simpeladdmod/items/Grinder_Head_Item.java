package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import x_j0nnay_x.simpeladdmod.init.Mod_Items;

public class Grinder_Head_Item extends Item{

    public Grinder_Head_Item(Item.Properties properties) {
        super(properties);
    }

    public static ItemStack brakeItem(ItemStack itemstack) {
        ItemStack grinder = itemstack.copy();
        grinder.setDamageValue(grinder.getDamageValue() + 1);
        if (grinder.getDamageValue() >= grinder.getMaxDamage()) {
            if(grinder.is(Mod_Items.GRINDER_HEAD_NEHTERITE.get())){
                return Grinder_Head_Item_Broken.getNewDefaultInstance(1);
            }else if(grinder.is(Mod_Items.GRINDER_HEAD_UNOBTIANIUM.get())){
                return Grinder_Head_Item_Broken.getNewDefaultInstance(2);
            }else{
                return Grinder_Head_Item_Broken.getNewDefaultInstance(0);
            }
        }
        return grinder;
    }
}