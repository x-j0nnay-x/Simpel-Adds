package x_j0nnay_x.simpeladdmod.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TriState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import x_j0nnay_x.simpeladdmod.init.Mod_Tags;
import java.util.Random;
import static net.minecraft.world.level.block.SweetBerryBushBlock.AGE;

public class Simpel_Farm_Land extends FarmlandBlock {

    public Simpel_Farm_Land(Properties properties) {
        super(properties
                .sound(SoundType.GRASS)
                .strength(3.0f)
                .randomTicks()
        );

    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        super.canSurvive(blockState, levelReader, blockPos);
        BlockState blockstate = levelReader.getBlockState(blockPos.above());
        if (blockstate.is(Mod_Tags.Blocks.PLANTABLE_CROPS))
            return true;
        return !blockstate.isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
        if (facing != Direction.UP) return TriState.FALSE;
        if (plant.getBlock() instanceof CactusBlock)
            return TriState.TRUE;

        if (plant.getBlock() instanceof SugarCaneBlock)
            return TriState.TRUE;

        if (plant.getBlock() == Blocks.BAMBOO || plant.getBlock() == Blocks.BAMBOO_SAPLING)
            return TriState.TRUE;

        if (plant.getBlock() == Blocks.NETHER_WART)
            return TriState.TRUE;

        if (plant.getBlock() instanceof VegetationBlock)
            return TriState.TRUE;

        return TriState.DEFAULT;
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
       entity.causeFallDamage(fallDistance, 1.0F, entity.damageSources().fall());
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
        }
    }

    public void turnToFarmLand(Level level, BlockPos blockPos){
        int blockValue = level.getBlockState(blockPos).getValue(MOISTURE);
        Random rand = new Random();
        int outOfChance = 75;
        int chance = rand.nextInt(outOfChance);
        if(chance == 0) {
            level.setBlockAndUpdate(blockPos, Blocks.FARMLAND.defaultBlockState().setValue(FarmlandBlock.MOISTURE, blockValue));
        }
    }

    public void growBlocks(ServerLevel level, BlockPos blockPos){
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.getBlock() instanceof Simpel_Farm_Land) {
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
