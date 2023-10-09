package net.x_j0nnay_x.simpeladdmod.screen.client.procedures.Grinder;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
public class GrinderProgressProcedure {
    public static boolean progress1(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") == 1) {
            return true;
        }
        return false;
    }
    public static boolean progress2(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") == 2) {
            return true;
        }
        return false;
    }
    public static boolean progress3(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") == 3) {
            return true;
        }
        return false;
    }
    public static boolean progress4(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") == 4) {
            return true;
        }
        return false;
    }
    public static boolean progress5(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") == 5) {
            return true;
        }
        return false;
    }
    public static boolean progress6(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") == 6) {
            return true;
        }
        return false;
    }
    public static boolean progress7(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") == 7) {
            return true;
        }
        return false;
    }
    public static boolean progress8(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") == 8) {
            return true;
        }
        return false;
    }
    public static boolean progress9(LevelAccessor world, double x, double y, double z) {
        if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") >= 9) {
            return true;
        }
        return false;
    }
}
