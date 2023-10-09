package net.x_j0nnay_x.simpeladdmod.screen.client.procedures.BlockFactory;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
public class BlockFactoryProgress {
    public static boolean progress1(LevelAccessor world, double x, double y, double z) {
        double progress = 0;
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") < 6 && new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") > 0) {
            return true;
        }
        return false;
    }
    public static boolean progress2(LevelAccessor world, double x, double y, double z) {
        double progress = 0;
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") < 12 && new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") >= 6) {
            return true;
        }
        return false;
    }
    public static boolean progress3(LevelAccessor world, double x, double y, double z) {
        double progress = 0;
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") < 18 && new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") >= 12) {
            return true;
        }
        return false;
    }
    public static boolean progress4(LevelAccessor world, double x, double y, double z) {
        double progress = 0;
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") < 24 && new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") >= 18) {
            return true;
        }
        return false;
    }
    public static boolean progress5(LevelAccessor world, double x, double y, double z) {
        double progress = 0;
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") <= 30 && new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Craft") >= 24) {
            return true;
        }
        return false;
    }
}
