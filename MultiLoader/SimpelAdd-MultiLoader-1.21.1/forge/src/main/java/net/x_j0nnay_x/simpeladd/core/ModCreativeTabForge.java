package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;

public class ModCreativeTabForge {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpelAddModForge.MODID);

    public static final RegistryObject<CreativeModeTab> SIMPEL_TAB = CREATIVE_MODE_TABS.register("simpel_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItemRegForge.GRINDERHEAD.get()))
                    .title(Component.translatable("creativetab.simpel_tab"))
                    .displayItems((pParameters, pOutput) -> {

                    //items grind heads
                        pOutput.accept(ModItemRegForge.GRINDERHEAD.get());
                        pOutput.accept(ModItemRegForge.GRINDERHEADNEHTERITE.get());
                        pOutput.accept(ModItemRegForge.GRINDERHEADUNOBTIANIUM.get());
                    //items random
                        pOutput.accept(ModItemRegForge.GOLDSTICK.get());
                        pOutput.accept(ModItemRegForge.WOODFIBER.get());
                        pOutput.accept(ModItemRegForge.FLESH.get());
                        pOutput.accept(ModItemRegForge.NEHTERITE_SHARD.get());
                        pOutput.accept(ModItemRegForge.NEHTERITE_SHARD_RAW.get());
                        pOutput.accept(ModItemRegForge.OBSIDAININGOT.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANIUMSCRAP.get());
                    //items upgrade
                        pOutput.accept(ModItemRegForge.SPEEDUPGRADE_1.get());
                        pOutput.accept(ModItemRegForge.SPEEDUPGRADE_2.get());
                        pOutput.accept(ModItemRegForge.SPEEDUPGRADE_3.get());
                        pOutput.accept(ModItemRegForge.BOOSTUPGRADE.get());
                        pOutput.accept(ModItemRegForge.XPBOOSTUPGRADE.get());
                    //items sandwich
                        pOutput.accept(ModItemRegForge.SANDWICH_MUT.get());
                        pOutput.accept(ModItemRegForge.SANDWICH_BEEF.get());
                        pOutput.accept(ModItemRegForge.SANDWICH_PORK.get());
                        pOutput.accept(ModItemRegForge.SANDWICH_CHECKIN.get());
                        pOutput.accept(ModItemRegForge.SANDWICH_VEG.get());
                        pOutput.accept(ModItemRegForge.SANDWICH_MEET_LOVE.get());
                        pOutput.accept(ModItemRegForge.SANDWICH_MEET_LOVE_VEG.get());
                    //items template
                        pOutput.accept(ModItemRegForge.BLANKUPGRADE.get());
                        pOutput.accept(ModItemRegForge.BLANKUPGRADE_RAW.get());
                        pOutput.accept(ModItemRegForge.OBSIDIANUPGRADE_SMITHING.get());
                        pOutput.accept(ModItemRegForge.UNOBTANIUMUPGRADE_SMITHING.get());
                    //items dust
                        pOutput.accept(ModItemRegForge.GOLDDUST.get());
                        pOutput.accept(ModItemRegForge.IRONDUST.get());
                        pOutput.accept(ModItemRegForge.COPPERDUST.get());
                        pOutput.accept(ModItemRegForge.NETHERITEDUST.get());
                        pOutput.accept(ModItemRegForge.NEHTERITE_SHARD_DUST.get());
                        pOutput.accept(ModItemRegForge.OBSIDAINDUST.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANIUMDUST.get());
                    //items elytra
                        pOutput.accept(ModItemRegForge.SIMPEL_ELITRA_HALF.get());
                        pOutput.accept(ModItemRegForge.SIMPEL_ELITRA_PART1.get());
                        pOutput.accept(ModItemRegForge.SIMPEL_ELITRA_PART2.get());
                    //items obsidian tire
                        pOutput.accept(ModItemRegForge.OBSIDAINAXE.get());
                        pOutput.accept(ModItemRegForge.OBSIDIANHOE.get());
                        pOutput.accept(ModItemRegForge.OBSIDIANSWORD.get());
                        pOutput.accept(ModItemRegForge.OBSIDIANPICKAXE.get());
                        pOutput.accept(ModItemRegForge.OBSIDIANSPADE.get());
                        pOutput.accept(ModItemRegForge.OBSIDIANHELMET.get());
                        pOutput.accept(ModItemRegForge.OBSIDIANCHEST.get());
                        pOutput.accept(ModItemRegForge.OBSIDIANLEGS.get());
                        pOutput.accept(ModItemRegForge.OBSIDIANBOOTS.get());
                    //items Obsidirite tire
                        pOutput.accept(ModItemRegForge.OBSIDIRITEAXE.get());
                        pOutput.accept(ModItemRegForge.OBSIDIRITEHOE.get());
                        pOutput.accept(ModItemRegForge.OBSIDIRITESWORD.get());
                        pOutput.accept(ModItemRegForge.OBSIDIRITEPICKAXE.get());
                        pOutput.accept(ModItemRegForge.OBSIDIRITESPADE.get());
                        pOutput.accept(ModItemRegForge.OBSIDIRITEHELMET.get());
                        pOutput.accept(ModItemRegForge.OBSIDIRITECHEST.get());
                        pOutput.accept(ModItemRegForge.OBSIDIRITELEGS.get());
                        pOutput.accept(ModItemRegForge.OBSIDIRITEBOOTS.get());
                    //items Unobtiannetherite tire
                        pOutput.accept(ModItemRegForge.UNOBTIANNETHERITEAXE.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANNETHERITEHOE.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANNETHERITESWORD.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANNETHERITEPICKAXE.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANNETHERITESPADE.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANNETHERITEHELMET.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANNETHERITECHEST.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANNETHERITELEGS.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANNETHERITEBOOTS.get());
                    //items UnObtianObsidirtie tire
                        pOutput.accept(ModItemRegForge.UNOBTIANOBSIDIRITEAXE.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANOBSIDIRITEHOE.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANOBSIDIRITESWORD.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANOBSIDIRITEPICKAXE.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANOBSIDIRITESPADE.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANOBSIDIRITEHELMET.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANOBSIDIRITECHEST.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANOBSIDIRITELEGS.get());
                        pOutput.accept(ModItemRegForge.UNOBTIANOBSIDIRITEBOOTS.get());
                    //blocks
                        pOutput.accept(ModBlockRegForge.DEEPSLATE_DEBRI_ORE.get());
                        pOutput.accept(ModBlockRegForge.UNOBTANIUM_ORE.get());
                        pOutput.accept(ModBlockRegForge.GRINDER_BLOCK.get());
                        pOutput.accept(ModBlockRegForge.GRINDER_BLOCK_UP.get());
                        pOutput.accept(ModBlockRegForge.BLOCK_FACTORY.get());
                        pOutput.accept(ModBlockRegForge.CHILLER.get());
                        pOutput.accept(ModBlockRegForge.NETHERITE_CRAFTER.get());
                        pOutput.accept(ModBlockRegForge.UPGRADED_FURNACE.get());
                        pOutput.accept(ModBlockRegForge.GRIND_FACTORY_BLOCK.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static void registerCreativeTab(){
        SimpelAddModForge.LOGGER.info("Registering Creative Tab For " + SimpelAddModForge.MODID);
    }
}
