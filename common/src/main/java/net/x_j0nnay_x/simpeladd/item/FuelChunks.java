package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.blocks.ChunkTourch;
import net.x_j0nnay_x.simpeladd.core.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class FuelChunks extends Item {

    public FuelChunks(Properties $$0) {
        super($$0);
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        BlockPlaceContext blockPlaceContext = new BlockPlaceContext(context);
        Direction direction = context.getClickedFace();
        if (!level.isClientSide()) {
                if (direction == Direction.NORTH) {
                    Block block = level.getBlockState(pos.north()).getBlock();
                    if(block instanceof AirBlock) {
                        level.setBlock(pos.north(), ModBlocks.CHUNKTOURCH.getStateForPlacement(blockPlaceContext), 0);
                    }
                }
                if (direction == Direction.SOUTH) {
                    Block block = level.getBlockState(pos.south()).getBlock();
                    if(block instanceof AirBlock) {
                        level.setBlock(pos.south(), ModBlocks.CHUNKTOURCH.getStateForPlacement(blockPlaceContext), 0);
                    }
                }
                if (direction == Direction.EAST) {
                    Block block = level.getBlockState(pos.east()).getBlock();
                    if(block instanceof AirBlock) {
                        level.setBlock(pos.east(), ModBlocks.CHUNKTOURCH.getStateForPlacement(blockPlaceContext), 0);
                    }
                }
                if (direction == Direction.WEST) {
                    Block block = level.getBlockState(pos.west()).getBlock();
                    if(block instanceof AirBlock) {
                        level.setBlock(pos.west(), ModBlocks.CHUNKTOURCH.getStateForPlacement(blockPlaceContext), 0);
                    }
                }
                if (direction == Direction.UP) {
                    Block block = level.getBlockState(pos.above()).getBlock();
                    if(block instanceof AirBlock) {
                        level.setBlock(pos.above(), ModBlocks.CHUNKTOURCH.getStateForPlacement(blockPlaceContext), 0);
                    }
                }
                if (direction == Direction.DOWN) {
                    Block block = level.getBlockState(pos.below()).getBlock();
                    if(block instanceof AirBlock) {
                        level.setBlock(pos.below(), ModBlocks.CHUNKTOURCH.getStateForPlacement(blockPlaceContext), 0);
                    }
                }
                context.getItemInHand().shrink(1);
                return InteractionResult.SUCCESS;

        }
        return InteractionResult.PASS;
    }

    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 200;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("block.simpeladdmod.chunk_torch.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }
}
