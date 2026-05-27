package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import x_j0nnay_x.simpeladdmod.block.Simpel_Farm_Land;
import x_j0nnay_x.simpeladdmod.init.Mod_Config;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import java.util.Map;
import java.util.function.Consumer;

public class Grow_Staff_Item extends Item {

    private static final Map<Block, Block> FARMLAND_MAP =
            Map.of(
                    Blocks.FARMLAND, Mod_Registrys.Mod_Blocks.SIMPEL_FARM_LAND.get()
            );

    public Grow_Staff_Item(Properties properties){
        super(properties
                .stacksTo(1)
                .durability(Mod_Config.GROWSTAFF_USES.getAsInt())
    );
    }

    public static ItemStack brakeItem(ItemStack itemstack) {
        if(canUse(itemstack)){
            itemstack.setDamageValue(itemstack.getDamageValue() + 1);
        }
        return itemstack;
    }



    public static boolean canUse(ItemStack itemstack) {
        return itemstack.getDamageValue() < itemstack.getMaxDamage();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        int blockValue;
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            ItemStack staf = context.getItemInHand();
            BlockPos blockPos = context.getClickedPos();
            BlockState blockState = context.getLevel().getBlockState(blockPos);
            Block block = blockState.getBlock();
            if(canUse(staf) == false){
                return InteractionResult.FAIL;
            }
            if(block instanceof SaplingBlock){
                SaplingBlock saplingBlock = (SaplingBlock) block;
                saplingBlock.performBonemeal((ServerLevel) level, level.getRandom(), blockPos, blockState);
                brakeItem(staf);
                return InteractionResult.SUCCESS;
            }
            if (block instanceof CropBlock) {
                CropBlock cropBlock = (CropBlock) block;
                if (cropBlock.isMaxAge(blockState)) {
                    return InteractionResult.FAIL;
                }
                cropBlock.growCrops((ServerLevel) context.getLevel(), blockPos, blockState);
                level.playSound(context.getPlayer(), blockPos, SoundEvents.BONE_MEAL_USE, SoundSource.PLAYERS, 1.0f, 1.0f);
                brakeItem(staf);
                return InteractionResult.SUCCESS;
            }
            if(block instanceof FarmlandBlock && block != Mod_Registrys.Mod_Blocks.SIMPEL_FARM_LAND.get()){
                blockValue = blockState.getValue(FarmlandBlock.MOISTURE);
                level.setBlockAndUpdate(blockPos, FARMLAND_MAP.get(block).defaultBlockState().setValue(Simpel_Farm_Land.MOISTURE, blockValue));
                level.playSound(context.getPlayer(), blockPos, SoundEvents.HOE_TILL, SoundSource.PLAYERS, 1.0f, 1.0f);
                brakeItem(staf);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(!canUse(itemStack)) {
            builder.accept(Component.translatable("tooltip.simpeladdmod.broken_item"));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

}
