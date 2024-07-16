package net.x_j0nnay_x.simpeladd.mixin;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {

        SimpelAddMod.LOG.info("This line is printed by an example mod mixin from NeoForge!");
        SimpelAddMod.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}