package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.blocks.SimpelFarmLand;
import net.x_j0nnay_x.simpeladd.core.ModBlocks;
import java.util.Map;


public class GrowStaff extends Item {

    private static final Map<Block, Block> FARMLAND_MAP =
            Map.of(
                    Blocks.FARMLAND, ModBlocks.SIMPELFARMLAND
            );

    public GrowStaff(int maxuses){
        super(new Item.Properties()
                .stacksTo(1)
                .durability(maxuses)
    );
    }
    public static ItemStack damageItem(ItemStack itemstack) {
        ItemStack growstaf = itemstack.copy();
        if(growstaf.getDamageValue() > growstaf.getMaxDamage()){
            return itemstack;
        }else {
            growstaf.setDamageValue(growstaf.getDamageValue() + 1);
            return growstaf;
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide) {
            ItemStack staf = context.getItemInHand();
            BlockPos blockPos = context.getClickedPos();
            BlockState blockState = context.getLevel().getBlockState(blockPos);
            Block block = blockState.getBlock();
            if(staf.getDamageValue() > staf.getMaxDamage()){
                return InteractionResult.FAIL;
            }
            if (block instanceof CropBlock) {
                CropBlock cropBlock = (CropBlock) block;
                if (cropBlock.isMaxAge(blockState)) {
                    return InteractionResult.FAIL;
                }
                cropBlock.growCrops((ServerLevel) context.getLevel(), blockPos, blockState);
                level.playSound(context.getPlayer(), blockPos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
                staf.setDamageValue(staf.getDamageValue() + 1);
                return InteractionResult.SUCCESS;
            }
/*
            if(block instanceof FarmBlock && block != ModBlocks.SIMPELFARMLAND){
                level.setBlockAndUpdate(blockPos, FARMLAND_MAP.get(block).defaultBlockState());
                level.playSound(context.getPlayer(), blockPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0f, 1.0f);
                staf.setDamageValue(staf.getDamageValue() + 1);
                return InteractionResult.SUCCESS;
            }
*/

        }
        return InteractionResult.PASS;
    }

}
