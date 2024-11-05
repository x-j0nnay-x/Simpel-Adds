package net.x_j0nnay_x.simpeladd.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.x_j0nnay_x.simpeladd.blocks.SimpelFarmLand;
import net.x_j0nnay_x.simpeladd.core.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherWartBlock.class)
public class NetherWartMixin {

    @Inject(method = "mayPlaceOn", at = @At("HEAD"), cancellable = true)
    protected void mayPlaceOn(BlockState $$0, BlockGetter $$1, BlockPos $$2, CallbackInfoReturnable<Boolean> cir) {
//        if($$0.is(ModBlocks.SIMPELFARMLAND)){
//            cir.setReturnValue(true);
//        }
        if ($$0.getBlock() instanceof SimpelFarmLand){
            cir.setReturnValue(true);
        }
    }


}
