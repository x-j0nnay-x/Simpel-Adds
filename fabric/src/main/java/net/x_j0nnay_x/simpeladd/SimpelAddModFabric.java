package net.x_j0nnay_x.simpeladd;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.screens.MenuScreens;
import net.x_j0nnay_x.simpeladd.core.*;
import net.x_j0nnay_x.simpeladd.network.FabricSlotChangePacket;
import net.x_j0nnay_x.simpeladd.screens.*;
import net.x_j0nnay_x.simpeladd.worldgen.ModWorldGenerationFabric;
import org.slf4j.Logger;

public class SimpelAddModFabric implements ModInitializer, ClientModInitializer {

    public static final String MODID = SimpelAddMod.MOD_ID;
    public static final Logger LOGGER = SimpelAddMod.LOG;


    @Override
    public void onInitialize() {
        ModCreativeTabFabric.registerCreativeTab();
        ModCreativeTabFabric.registerTab();
        ModItemRegFabric.registerModItems();
        ModBlockRegFabric.registerModBlocks();
        ModBlockEntitiesFabric.registerBlockEntities();
        ModWorldGenerationFabric.generateModWorldGen();
        ModMenuTypeFabric.registerScreenHandlers();
        ModRecipesRegFabric.registerRecipes();
        NetworkInit();
        LOGGER.info("Hello Fabric world!");
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

