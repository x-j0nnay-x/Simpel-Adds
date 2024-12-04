package net.x_j0nnay_x.simpeladd.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

public class SimpelXPBlockBlockState extends DropExperienceBlock {
    public static final BooleanProperty LIT;

    public SimpelXPBlockBlockState(IntProvider $$1, Properties $$0) {
        super($$1, $$0);
        this.registerDefaultState((BlockState)this.defaultBlockState().setValue(LIT, false));
    }
    public void attack(BlockState $$0, Level $$1, BlockPos $$2, Player $$3) {
        interact($$0, $$1, $$2);
        super.attack($$0, $$1, $$2, $$3);
    }

    public void stepOn(Level $$0, BlockPos $$1, BlockState $$2, Entity $$3) {
        if (!$$3.isSteppingCarefully()) {
            interact($$2, $$0, $$1);
        }

        super.stepOn($$0, $$1, $$2, $$3);
    }

    public InteractionResult use(BlockState $$0, Level $$1, BlockPos $$2, Player $$3, InteractionHand $$4, BlockHitResult $$5) {
        if ($$1.isClientSide) {
            spawnParticles($$1, $$2);
        } else {
            interact($$0, $$1, $$2);
        }

        ItemStack $$6 = $$3.getItemInHand($$4);
        return $$6.getItem() instanceof BlockItem && (new BlockPlaceContext($$3, $$4, $$6, $$5)).canPlace() ? InteractionResult.PASS : InteractionResult.SUCCESS;
    }

    private static void interact(BlockState $$0, Level $$1, BlockPos $$2) {
        spawnParticles($$1, $$2);
        if (!(Boolean)$$0.getValue(LIT)) {
            $$1.setBlock($$2, (BlockState)$$0.setValue(LIT, true), 3);
        }

    }

    public boolean isRandomlyTicking(BlockState $$0) {
        return (Boolean)$$0.getValue(LIT);
    }

    public void randomTick(BlockState $$0, ServerLevel $$1, BlockPos $$2, RandomSource $$3) {
        if ((Boolean)$$0.getValue(LIT)) {
            $$1.setBlock($$2, (BlockState)$$0.setValue(LIT, false), 3);
        }

    }

    protected void spawnAfterBreak(BlockState $$0, ServerLevel $$1, BlockPos $$2, ItemStack $$3, boolean $$4) {
        super.spawnAfterBreak($$0, $$1, $$2, $$3, $$4);

    }

    public void animateTick(BlockState $$0, Level $$1, BlockPos $$2, RandomSource $$3) {
        if ((Boolean)$$0.getValue(LIT)) {
            spawnParticles($$1, $$2);
        }

    }

    private static void spawnParticles(Level $$0, BlockPos $$1) {
        double $$2 = 0.5625;
        RandomSource $$3 = $$0.random;
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction $$4 = var5[var7];
            BlockPos $$5 = $$1.relative($$4);
            if (!$$0.getBlockState($$5).isSolidRender($$0, $$5)) {
                Direction.Axis $$6 = $$4.getAxis();
                double $$7 = $$6 == Direction.Axis.X ? 0.5 + 0.5625 * (double)$$4.getStepX() : (double)$$3.nextFloat();
                double $$8 = $$6 == Direction.Axis.Y ? 0.5 + 0.5625 * (double)$$4.getStepY() : (double)$$3.nextFloat();
                double $$9 = $$6 == Direction.Axis.Z ? 0.5 + 0.5625 * (double)$$4.getStepZ() : (double)$$3.nextFloat();
                $$0.addParticle(DustParticleOptions.REDSTONE, (double)$$1.getX() + $$7, (double)$$1.getY() + $$8, (double)$$1.getZ() + $$9, 0.0, 0.0, 0.0);
            }
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> $$0) {
        $$0.add(new Property[]{LIT});
    }

    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}
