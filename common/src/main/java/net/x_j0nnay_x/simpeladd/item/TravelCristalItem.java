package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModTags;

public class TravelCristalItem extends Item {
    public TravelCristalItem() {
        super(new Properties());
    }



    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        if(!level.isClientSide()) {
            if(player.isCrouching()){
                    BlockPos blockPos = context.getClickedPos();
                    BlockState blockState = level.getBlockState(blockPos);
                    ItemStack itemStack1 = new ItemStack(blockState.getBlock().asItem(), 1);
                    if(!blockState.isAir() && !blockState.is(ModTags.Blocks.TRAVILGEM_BAN)) {
                        level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                        player.drop(itemStack1, false);
                        context.getItemInHand().shrink(1);
                    }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
