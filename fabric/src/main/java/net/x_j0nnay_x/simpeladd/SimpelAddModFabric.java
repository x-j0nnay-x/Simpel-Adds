package net.x_j0nnay_x.simpeladd;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.x_j0nnay_x.simpeladd.core.*;
import net.x_j0nnay_x.simpeladd.network.FabricSlotChangePacket;
import net.x_j0nnay_x.simpeladd.screens.*;
import net.x_j0nnay_x.simpeladd.worldgen.ModWorldGenerationFabric;
import org.slf4j.Logger;

public class SimpelAddModFabric implements ModInitializer, ClientModInitializer {

    @Override
    public void onInitialize() {
        ModBlockRegFabric.registerBlocks();
        ModItemRegFabric.registerItems();
        ModCreativeTabFabric.registerTab();
        ModBlockEntitiesFabric.registerBlockEntities();
        ModWorldGenerationFabric.generateModWorldGen();
        ModMenuTypeFabric.registerScreenHandlers();
        ModRecipesRegFabric.registerRecipes();
        NetworkInit();
        FuelRegistry.INSTANCE.add(ModItemRegFabric.FULECHUNKS, 200);
        SimpelAddMod.init();
    }

    public void NetworkInit(){
        PayloadTypeRegistry.playC2S().register(FabricSlotChangePacket.TYPE, FabricSlotChangePacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(FabricSlotChangePacket.TYPE, FabricSlotChangePacket::receive);
    }

    @Override
    public void onInitializeClient() {
        ModScreensFabric.registerScreens();
    }
}

