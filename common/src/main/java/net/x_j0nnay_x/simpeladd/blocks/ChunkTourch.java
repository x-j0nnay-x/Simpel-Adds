package net.x_j0nnay_x.simpeladd.blocks;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChunkTourch extends FaceAttachedHorizontalDirectionalBlock {

    public static final MapCodec<ChunkTourch> CODEC = RecordCodecBuilder.mapCodec(($$0) -> $$0.group(BlockSetType.CODEC.fieldOf("block_set_type").forGetter(($$0x) -> $$0x.type), Codec.intRange(1, 1024).fieldOf("ticks_to_stay_pressed").forGetter(($$0x) -> $$0x.ticksToStayPressed), propertiesCodec()).apply($$0, ChunkTourch::new));


    protected static final VoxelShape CEILING_AABB_X;
    protected static final VoxelShape CEILING_AABB_Z;
    protected static final VoxelShape FLOOR_AABB_X;
    protected static final VoxelShape FLOOR_AABB_Z;
    protected static final VoxelShape NORTH_AABB;
    protected static final VoxelShape SOUTH_AABB;
    protected static final VoxelShape WEST_AABB;
    protected static final VoxelShape EAST_AABB;
    protected final ParticleOptions flameParticle;
    private final BlockSetType type;
    private final int ticksToStayPressed;


    public ChunkTourch(BlockSetType $$0, int $$1, Properties $$2) {
        super($$2);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL));
        this.flameParticle = ParticleTypes.FLAME;
        this.type = $$0;
        this.ticksToStayPressed = $$1;

    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        Direction direction = (Direction)state.getValue(FACING);
        switch ((AttachFace)state.getValue(FACE)) {
            case FLOOR:
                if (direction.getAxis() == Direction.Axis.X) {
                    return FLOOR_AABB_X;
                }

                return FLOOR_AABB_Z;
            case WALL:
                VoxelShape var10000;
                switch (direction) {
                    case EAST:
                        var10000 = EAST_AABB;
                        break;
                    case WEST:
                        var10000 = WEST_AABB;
                        break;
                    case SOUTH:
                        var10000 = SOUTH_AABB;
                        break;
                    case NORTH:
                    case UP:
                    case DOWN:
                        var10000 = NORTH_AABB;
                        break;
                    default:
                        throw new IncompatibleClassChangeError();
                }

                return var10000;
            case CEILING:
            default:
                if (direction.getAxis() == Direction.Axis.X) {
                    return  CEILING_AABB_X;
                } else {
                    return  CEILING_AABB_Z;
                }
        }
    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        Direction direction = (Direction)blockState.getValue(FACING);
        AttachFace attachFace = (AttachFace)blockState.getValue(FACE);
        if(attachFace == AttachFace.WALL) {
            if (direction == Direction.NORTH) {
                double d = (double) blockPos.getX() + 0.5;
                double e = (double) blockPos.getY() + 0.6;
                double f = (double) blockPos.getZ() + 0.8;
                level.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
                level.addParticle(this.flameParticle, d, e, f, 0.0, 0.0, 0.0);
            }
            if (direction == Direction.EAST) {
                double d = (double) blockPos.getX() + 0.2;
                double e = (double) blockPos.getY() + 0.6;
                double f = (double) blockPos.getZ() + 0.5;
                level.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
                level.addParticle(this.flameParticle, d, e, f, 0.0, 0.0, 0.0);
            }
            if (direction == Direction.SOUTH) {
                double d = (double) blockPos.getX() + 0.5;
                double e = (double) blockPos.getY() + 0.6;
                double f = (double) blockPos.getZ() + 0.2;
                level.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
                level.addParticle(this.flameParticle, d, e, f, 0.0, 0.0, 0.0);
            }
            if (direction == Direction.WEST) {
                double d = (double) blockPos.getX() + 0.8;
                double e = (double) blockPos.getY() + 0.6;
                double f = (double) blockPos.getZ() + 0.5;
                level.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
                level.addParticle(this.flameParticle, d, e, f, 0.0, 0.0, 0.0);
            }
        }
        if(attachFace == AttachFace.FLOOR) {
            double d = (double) blockPos.getX() + 0.5;
            double e = (double) blockPos.getY() + 0.2;
            double f = (double) blockPos.getZ() + 0.5;
            level.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            level.addParticle(this.flameParticle, d, e, f, 0.0, 0.0, 0.0);
        }
        if(attachFace == AttachFace.CEILING) {
            double d = (double) blockPos.getX() + 0.5;
            double e = (double) blockPos.getY() + 0.8;
            double f = (double) blockPos.getZ() + 0.5;
            level.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            level.addParticle(this.flameParticle, d, e, f, 0.0, 0.0, 0.0);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51101_) {
        p_51101_.add(FACING,  FACE);
    }

    static {
        CEILING_AABB_X = Block.box(6.0, 14.0, 5.0, 10.0, 16.0, 11.0);
        CEILING_AABB_Z = Block.box(5.0, 14.0, 6.0, 11.0, 16.0, 10.0);
        FLOOR_AABB_X = Block.box(6.0, 0.0, 5.0, 10.0, 2.0, 11.0);
        FLOOR_AABB_Z = Block.box(5.0, 0.0, 6.0, 11.0, 2.0, 10.0);
        NORTH_AABB = Block.box(5.0, 6.0, 14.0, 11.0, 10.0, 16.0);
        SOUTH_AABB = Block.box(5.0, 6.0, 0.0, 11.0, 10.0, 2.0);
        WEST_AABB = Block.box(14.0, 6.0, 5.0, 16.0, 10.0, 11.0);
        EAST_AABB = Block.box(0.0, 6.0, 5.0, 2.0, 10.0, 11.0);
    }

    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
