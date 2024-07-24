package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;

import java.util.function.Supplier;


public class ModCreativeTabNeoForge {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpelAddModNeoForge.MODID);

    public static final Supplier<CreativeModeTab> SIMPEL_TAB = CREATIVE_MODE_TABS.register("simpel_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItemRegNeoForge.GRINDERHEAD.get()))
                    .title(Component.translatable("creativetab.simpel_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        //items
                        pOutput.accept(ModItemRegNeoForge.GRINDERHEAD.get());
                        pOutput.accept(ModItemRegNeoForge.GRINDERHEADNEHTERITE.get());
                        pOutput.accept(ModItemRegNeoForge.GRINDERHEADUNOBTIANIUM.get());

                        pOutput.accept(ModItemRegNeoForge.GOLDSTICK.get());
                        pOutput.accept(ModItemRegNeoForge.WOODFIBER.get());
                        pOutput.accept(ModItemRegNeoForge.FLESH.get());

                        pOutput.accept(ModItemRegNeoForge.SPEEDUPGRADE_1.get());
                        pOutput.accept(ModItemRegNeoForge.SPEEDUPGRADE_2.get());
                        pOutput.accept(ModItemRegNeoForge.SPEEDUPGRADE_3.get());
                        pOutput.accept(ModItemRegNeoForge.BOOSTUPGRADE.get());

                        pOutput.accept(ModItemRegNeoForge.SANDWICH_MUT.get());
                        pOutput.accept(ModItemRegNeoForge.SANDWICH_BEEF.get());
                        pOutput.accept(ModItemRegNeoForge.SANDWICH_PORK.get());
                        pOutput.accept(ModItemRegNeoForge.SANDWICH_CHECKIN.get());
                        pOutput.accept(ModItemRegNeoForge.SANDWICH_VEG.get());
                        pOutput.accept(ModItemRegNeoForge.SANDWICH_MEET_LOVE.get());
                        pOutput.accept(ModItemRegNeoForge.SANDWICH_MEET_LOVE_VEG.get());

                        pOutput.accept(ModItemRegNeoForge.BLANKUPGRADE.get());
                        pOutput.accept(ModItemRegNeoForge.BLANKUPGRADE_RAW.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIANUPGRADE_SMITHING.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTANIUMUPGRADE_SMITHING.get());

                        pOutput.accept(ModItemRegNeoForge.GOLDDUST.get());
                        pOutput.accept(ModItemRegNeoForge.IRONDUST.get());
                        pOutput.accept(ModItemRegNeoForge.COPPERDUST.get());
                        pOutput.accept(ModItemRegNeoForge.NETHERITEDUST.get());
                        pOutput.accept(ModItemRegNeoForge.NEHTERITE_SHARD_DUST.get());

                        pOutput.accept(ModItemRegNeoForge.SIMPEL_ELITRA_HALF.get());
                        pOutput.accept(ModItemRegNeoForge.SIMPEL_ELITRA_PART1.get());
                        pOutput.accept(ModItemRegNeoForge.SIMPEL_ELITRA_PART2.get());

                        pOutput.accept(ModItemRegNeoForge.OBSIDAININGOT.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDAINDUST.get());

                        pOutput.accept(ModItemRegNeoForge.NEHTERITE_SHARD.get());
                        pOutput.accept(ModItemRegNeoForge.NEHTERITE_SHARD_RAW.get());

                        pOutput.accept(ModItemRegNeoForge.UNOBTIANIUMDUST.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANIUMSCRAP.get());

                        pOutput.accept(ModItemRegNeoForge.OBSIDAINAXE.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIANHOE.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIANSWORD.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIANPICKAXE.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIANSPADE.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIANHELMET.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIANCHEST.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIANLEGS.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIANBOOTS.get());

                        pOutput.accept(ModItemRegNeoForge.OBSIDIRITEAXE.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIRITEHOE.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIRITESWORD.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIRITEPICKAXE.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIRITESPADE.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIRITEHELMET.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIRITECHEST.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIRITELEGS.get());
                        pOutput.accept(ModItemRegNeoForge.OBSIDIRITEBOOTS.get());

                        pOutput.accept(ModItemRegNeoForge.UNOBTIANNETHERITEAXE.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANNETHERITEHOE.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANNETHERITESWORD.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANNETHERITEPICKAXE.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANNETHERITESPADE.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANNETHERITEHELMET.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANNETHERITECHEST.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANNETHERITELEGS.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANNETHERITEBOOTS.get());


                        pOutput.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEAXE.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEHOE.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITESWORD.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEPICKAXE.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITESPADE.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEHELMET.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITECHEST.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITELEGS.get());
                        pOutput.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEBOOTS.get());


                        //block
                        pOutput.accept(ModBlockRegNeoForge.DEEPSLATE_DEBRI_ORE.get());
                        pOutput.accept(ModBlockRegNeoForge.UNOBTANIUM_ORE.get());
                        pOutput.accept(ModBlockRegNeoForge.GRINDER_BLOCK.get());
                        pOutput.accept(ModBlockRegNeoForge.GRINDER_BLOCK_UP.get());
                        pOutput.accept(ModBlockRegNeoForge.BLOCK_FACTORY.get());
                        pOutput.accept(ModBlockRegNeoForge.CHILLER.get());
                        pOutput.accept(ModBlockRegNeoForge.NETHERITE_CRAFTER.get());
                        pOutput.accept(ModBlockRegNeoForge.UPGRADED_FURNACE.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
    public static void registerCreativeTab(){
        SimpelAddModNeoForge.LOGGER.info("Registering Creative Tab For " + SimpelAddModNeoForge.MODID);
    }
}
