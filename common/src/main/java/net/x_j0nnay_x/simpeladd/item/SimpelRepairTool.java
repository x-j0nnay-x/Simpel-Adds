package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import org.jetbrains.annotations.NotNull;

public class SimpelRepairTool extends Item {
    int ticks;
    int damagevalue;

    public SimpelRepairTool(int uses) {
        super(new Properties().stacksTo(1).durability(uses).fireResistant());
    }
    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull Entity entity, int i, boolean bl){
        if(level.isClientSide()) return;
        ticks++;
        if (entity instanceof Player player) {
                if(ticks == 100) {
                    ticks = 0;
                    if(isRepairing(player.getInventory())){
                        if(itemStack.getDamageValue() < itemStack.getMaxDamage()){
                            repairAllItems(player.getInventory());
                            itemStack.setDamageValue(itemStack.getDamageValue() + damagevalue);
                        }
                        damagevalue = 0;
                    }
                }
        }
    }

    public boolean canRepair(ItemStack stack){
        if (stack.is(ModTags.Items.NOTREPAIRABLE)) return false;
        if(stack.isEmpty()) return false;
        if(!stack.isDamageableItem()) return false;
        return stack.getDamageValue() > 0;
    }

    private boolean isRepairing(Inventory inv){
        for (int i = 0; i < inv.getContainerSize(); i++){
            ItemStack invStack = inv.getItem(i);
            if (canRepair(invStack))
            {
                return true;
            }
        }
       return false;
    }

    private void repairAllItems(Inventory inv){
        for (int i = 0; i < inv.getContainerSize(); i++){
            ItemStack invStack = inv.getItem(i);
            if (canRepair(invStack)) {
                damagevalue ++;
                invStack.setDamageValue(invStack.getDamageValue() - 4);
            }
        }
    }
    @Override
    public boolean isValidRepairItem(ItemStack $$0, ItemStack $$1) {
        return $$1.is(ModItems.UNOBTIANIUMSCRAP);
    }
}
