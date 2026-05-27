package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.init.Mod_Tags;

import java.util.function.Consumer;

public class Repair_Gem extends Item implements ICurioItem {

    public int damagedValue;
    int ticks;

    public Repair_Gem(Properties properties) {
        super(properties);
    }


    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if(owner instanceof Player player) {
            tickSteps(player, itemStack);
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player player) {
            tickSteps(player, stack);
        }
    }

    private void tickSteps(Player player, ItemStack stack){
        ticks++;
        if(ticks >= 100){
            repair_Items(player, stack);
        }
    }

    private void repair_Items(Player player, ItemStack stack){
        if(isRepairing(player.getInventory())){
            if(stack.getDamageValue() < stack.getMaxDamage()){
                repairAllItems(player.getInventory());
                brakeItem(stack, damagedValue);
            }
            damagedValue =0;
        }
    }
    public boolean canRepair(ItemStack stack){
        if (stack.is(Mod_Tags.Items.REPAIR_NOT_ALLOWED)) return false;
        if(stack.is(Mod_Registrys.Mod_Items.REPAIR_GEM.get())) return false;
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
                damagedValue ++;
                invStack.setDamageValue(invStack.getDamageValue() - 4);
            }
        }
    }
    public static ItemStack brakeItem(ItemStack itemstack, int damage) {
        if(canUse(itemstack)){
            itemstack.setDamageValue(itemstack.getDamageValue() + damage);
        }
        return itemstack;
    }

    public static boolean canUse(ItemStack itemstack) {
        return itemstack.getDamageValue() < itemstack.getMaxDamage();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(!canUse(itemStack)) {
            builder.accept(Component.translatable("tooltip.simpeladdmod.broken_item"));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
