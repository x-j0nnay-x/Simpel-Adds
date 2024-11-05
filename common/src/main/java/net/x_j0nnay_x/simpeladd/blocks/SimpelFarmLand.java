package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModTags;

import java.util.Random;

import static net.minecraft.world.level.block.SweetBerryBushBlock.AGE;

public class SimpelFarmLand extends FarmBlock {


    public SimpelFarmLand() {
        super(Properties.of()
                .sound(SoundType.GRASS)
                .strength(3.0f)
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
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        super.randomTick(blockState, level, blockPos, randomSource);
        for (int i = 0; i < 3; i++) {
            growBlocks(level, blockPos);
          //  turnToFarmLand(level, blockPos);
        }
    }

    public void turnToFarmLand(Level level, BlockPos blockPos){
        Random rand = new Random();
        int outOfChance = 75;
        int chance = rand.nextInt(outOfChance);
        if(chance == 0) {
            level.setBlockAndUpdate(blockPos, Blocks.FARMLAND.defaultBlockState());
        }
    }

    public void growBlocks(ServerLevel level, BlockPos blockPos){
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.getBlock() instanceof SimpelFarmLand) {
            BlockPos cropPos = blockPos.above();
            BlockState crop = level.getBlockState(cropPos);
            BlockState crop1 = null;
            if (crop.getBlock() instanceof CropBlock cropblock) {
                if (!cropblock.isMaxAge(crop)) {
                    this.turnToFarmLand(level, blockPos);
                    crop1 = cropblock.getStateForAge(cropblock.getAge(crop) + 1);
                }
            }  else if (crop.is(Blocks.SWEET_BERRY_BUSH) || crop.is(Blocks.NETHER_WART)) {
                int bushBlock = crop.getValue(AGE);
                if (bushBlock < 3) {
                    this.turnToFarmLand(level, blockPos);
                    crop1 = crop.setValue(AGE, bushBlock + 1);
                }
            }
            if (crop1 != null) {
                level.setBlockAndUpdate(cropPos, crop1);
            }
        }
    }

}
