package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModTags;


import static net.minecraft.world.level.block.SweetBerryBushBlock.AGE;

public class SimpelFarmLand extends FarmBlock {

    int counter = 0;

    public SimpelFarmLand() {
        super(Properties.copy(Blocks.FARMLAND)
                .randomTicks()
        );

    }



    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        super.canSurvive(blockState, levelReader, blockPos);
        BlockState blockstate = levelReader.getBlockState(blockPos.above());
        if (blockstate.is(ModTags.Blocks.PLANTABLECROPS))
            return true;
        return !blockstate.isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
    }

    @Override
    public void fallOn(Level $$0, BlockState $$1, BlockPos $$2, Entity $$3, float $$4) {
        $$3.causeFallDamage($$4, 1.0F, $$3.damageSources().fall());
    }


    @Override
    public void tick(BlockState $$0, ServerLevel $$1, BlockPos $$2, RandomSource $$3) {
        super.tick($$0, $$1, $$2, $$3);
        counter ++;
        if (counter > 20) {
            growBlocks($$1, $$2);
            counter = 0;
        }
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        super.randomTick(blockState, level, blockPos, randomSource);
         for (int i = 0; i < 2; i++) {
            growBlocks(level, blockPos);
        }
    }


    public static void growBlocks(ServerLevel level, BlockPos blockPos){
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.getBlock() instanceof SimpelFarmLand) {
            BlockPos cropPos = blockPos.above();
            BlockState crop = level.getBlockState(cropPos);
            BlockState crop1 = null;
            if (crop.getBlock() instanceof CropBlock cropblock) {
                if (!cropblock.isMaxAge(crop)) {
                    crop1 = cropblock.getStateForAge(cropblock.getAge(crop) + 1);
                }
            } else if (crop.getBlock() instanceof StemBlock) {
                int stemBlock = crop.getValue(StemBlock.AGE);
                if (stemBlock < 7) {
                    crop1 = crop.setValue(StemBlock.AGE, stemBlock + 1);
                }
            } else if (crop.is(Blocks.SWEET_BERRY_BUSH) || crop.is(Blocks.NETHER_WART)) {
                int bushBlock = crop.getValue(AGE);
                if (bushBlock < 3) {
                    crop1 = crop.setValue(AGE, bushBlock + 1);
                }
            } else if (crop.getBlock() instanceof SugarCaneBlock || crop.getBlock() instanceof CactusBlock) {
                BlockPos blockpos = cropPos.above();
                int otherCrops = crop.getValue(AGE);
                if (otherCrops == 15) {
                    level.setBlockAndUpdate(blockpos, crop.getBlock().defaultBlockState());
                    BlockState blockstate = crop.setValue(AGE, Integer.valueOf(0));
                    level.setBlock(cropPos, blockstate, 4);
                    level.neighborChanged(blockstate, blockpos, crop.getBlock(), cropPos, false);
                } else {
                    level.setBlock(cropPos, crop.setValue(AGE, Integer.valueOf(otherCrops + 1)), 4);
                }
            }
            if (crop1 != null) {
                level.setBlockAndUpdate(cropPos, crop1);
            }
        }
    }
}
