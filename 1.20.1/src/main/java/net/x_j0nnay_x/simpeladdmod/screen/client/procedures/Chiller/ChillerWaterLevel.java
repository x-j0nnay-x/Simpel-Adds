package net.x_j0nnay_x.simpeladdmod.screen.client.procedures.Chiller;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
public class ChillerWaterLevel {
    public static boolean level1(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "WaterLevel") == 1) {
            return true;
        }
        return false;
    }
    public static boolean level2(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "WaterLevel") == 2) {
            return true;
        }
        return false;
    }
    public static boolean level3(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "WaterLevel") == 3) {
            return true;
        }
        return false;
    }
    public static boolean level4(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "WaterLevel") == 4) {
            return true;
        }
        return false;
    }
    public static boolean level5(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "WaterLevel") == 5) {
            return true;
        }
        return false;
    }
    public static boolean level6(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "WaterLevel") == 6) {
            return true;
        }
        return false;
    }
}
