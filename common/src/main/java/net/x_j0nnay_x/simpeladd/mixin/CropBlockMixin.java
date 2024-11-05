package net.x_j0nnay_x.simpeladd.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.core.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public class CropBlockMixin {

    @Inject(method = "mayPlaceOn", at = @At("HEAD"), cancellable = true)
    protected void mayPlaceOn(BlockState $$0, BlockGetter $$1, BlockPos $$2, CallbackInfoReturnable<Boolean> cir) {
        if ($$0.is(ModBlocks.SIMPELFARMLAND)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getGrowthSpeed", at = @At("HEAD"), cancellable = true)
    private static void getGrowthSpeed(Block $$0, BlockGetter $$1, BlockPos $$2, CallbackInfoReturnable<Float> cir) {
        float flot1 = 1.0F;
        BlockPos blockPos = $$2.below();
        for (int i1 = -1; i1 <= 1; ++i1) {
            for (int i2 = -1; i2 <= 1; ++i2) {
                float flot2 = 0.0F;
                BlockState $$8 = $$1.getBlockState(blockPos.offset(i1, 0, i2));
                if ($$8.is(ModBlocks.SIMPELFARMLAND)) {
                    flot2 = 3.0F;
                }

                if (i1 != 0 || i2 != 0) {
                    flot2 /= 4.0F;
                }

                flot1 += flot2;
            }
        }
    }


}
