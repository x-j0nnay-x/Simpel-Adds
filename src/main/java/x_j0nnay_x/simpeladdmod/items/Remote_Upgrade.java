package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;

import java.util.List;
import java.util.function.Consumer;

public class Remote_Upgrade extends Item {

    public Remote_Upgrade(Properties properties) {
        super(properties.stacksTo(1));
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        BlockPos blockPos = context.getClickedPos();
        if(!player.isCrouching()){
           context.getItemInHand().remove(Mod_Registrys.Mod_Data_Componet.COORDINATES.get());
            return InteractionResult.SUCCESS;
        }else {
            context.getItemInHand().set(Mod_Registrys.Mod_Data_Componet.COORDINATES.get(), context.getClickedPos());
            return InteractionResult.SUCCESS;
        }
    }



    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("tooltip.simpeladdmod.remote_upgrade"));
       if(itemStack.get(Mod_Registrys.Mod_Data_Componet.COORDINATES) != null){
           builder.accept(Component.literal("" + itemStack.get(Mod_Registrys.Mod_Data_Componet.COORDINATES)));
       }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
