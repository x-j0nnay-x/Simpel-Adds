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
                    .displayItems((pParameters, entries) -> {
                    //items grind head
                        entries.accept(ModItemRegNeoForge.GRINDERHEAD.get());
                        entries.accept(ModItemRegNeoForge.GRINDERHEADNEHTERITE.get());
                        entries.accept(ModItemRegNeoForge.GRINDERHEADUNOBTIANIUM.get());
                    //items random
                        entries.accept(ModItemRegNeoForge.WOODFIBER.get());
                        entries.accept(ModItemRegNeoForge.FLESH.get());
                        entries.accept(ModItemRegNeoForge.OBSIDAININGOT.get());
                        entries.accept(ModItemRegNeoForge.NEHTERITE_SHARD.get());
                        entries.accept(ModItemRegNeoForge.NEHTERITE_SHARD_RAW.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANIUMSCRAP.get());
                        entries.accept(ModItemRegNeoForge.REPAIRTOOL.get());
                        entries.accept(ModItemRegNeoForge.FIREPROOFTOOL.get());
                        entries.accept(ModItemRegNeoForge.FEEDINGTOOL.get());
                        entries.accept(ModItemRegNeoForge.GROWSTAFF.get());
                    //items upgrade
                        entries.accept(ModItemRegNeoForge.SPEEDUPGRADE_1.get());
                        entries.accept(ModItemRegNeoForge.SPEEDUPGRADE_2.get());
                        entries.accept(ModItemRegNeoForge.SPEEDUPGRADE_3.get());
                        entries.accept(ModItemRegNeoForge.BOOSTUPGRADE.get());
                        entries.accept(ModItemRegNeoForge.XPBOOSTUPGRADE.get());
                    //items sandwich
                        entries.accept(ModItemRegNeoForge.SANDWICH_MUT.get());
                        entries.accept(ModItemRegNeoForge.SANDWICH_BEEF.get());
                        entries.accept(ModItemRegNeoForge.SANDWICH_PORK.get());
                        entries.accept(ModItemRegNeoForge.SANDWICH_CHECKIN.get());
                        entries.accept(ModItemRegNeoForge.SANDWICH_VEG.get());
                        entries.accept(ModItemRegNeoForge.SANDWICH_MEET_LOVE.get());
                        entries.accept(ModItemRegNeoForge.SANDWICH_MEET_LOVE_VEG.get());
                    //items templates
                        entries.accept(ModItemRegNeoForge.BLANKUPGRADE.get());
                        entries.accept(ModItemRegNeoForge.BLANKUPGRADE_RAW.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIANUPGRADE_SMITHING.get());
                        entries.accept(ModItemRegNeoForge.UNOBTANIUMUPGRADE_SMITHING.get());
                    //items dust
                        entries.accept(ModItemRegNeoForge.GOLDDUST.get());
                        entries.accept(ModItemRegNeoForge.IRONDUST.get());
                        entries.accept(ModItemRegNeoForge.COPPERDUST.get());
                        entries.accept(ModItemRegNeoForge.NETHERITEDUST.get());
                        entries.accept(ModItemRegNeoForge.NEHTERITE_SHARD_DUST.get());
                        entries.accept(ModItemRegNeoForge.OBSIDAINDUST.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANIUMDUST.get());
                    //items elytra
                        entries.accept(ModItemRegNeoForge.SIMPEL_ELITRA_HALF.get());
                        entries.accept(ModItemRegNeoForge.SIMPEL_ELITRA_PART1.get());
                        entries.accept(ModItemRegNeoForge.SIMPEL_ELITRA_PART2.get());
                    //items obsidian tire
                        entries.accept(ModItemRegNeoForge.OBSIDAINAXE.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIANHOE.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIANSWORD.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIANPICKAXE.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIANSPADE.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIANHELMET.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIANCHEST.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIANLEGS.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIANBOOTS.get());
                    //items Obsidirite tire
                        entries.accept(ModItemRegNeoForge.OBSIDIRITEAXE.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIRITEHOE.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIRITESWORD.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIRITEPICKAXE.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIRITESPADE.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIRITEHELMET.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIRITECHEST.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIRITELEGS.get());
                        entries.accept(ModItemRegNeoForge.OBSIDIRITEBOOTS.get());
                    //items Unobtiannetherite tire
                        entries.accept(ModItemRegNeoForge.UNOBTIANNETHERITEAXE.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANNETHERITEHOE.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANNETHERITESWORD.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANNETHERITEPICKAXE.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANNETHERITESPADE.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANNETHERITEHELMET.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANNETHERITECHEST.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANNETHERITELEGS.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANNETHERITEBOOTS.get());
                    //items UnObtianObsidirtie tire
                        entries.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEAXE.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEHOE.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITESWORD.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEPICKAXE.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITESPADE.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEHELMET.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITECHEST.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITELEGS.get());
                        entries.accept(ModItemRegNeoForge.UNOBTIANOBSIDIRITEBOOTS.get());
                    //block
                        entries.accept(ModBlockRegNeoForge.DEEPSLATE_DEBRI_ORE.get());
                        entries.accept(ModBlockRegNeoForge.UNOBTANIUM_ORE.get());
                        entries.accept(ModBlockRegNeoForge.GRINDER_BLOCK.get());
                        entries.accept(ModBlockRegNeoForge.GRINDER_BLOCK_UP.get());
                        entries.accept(ModBlockRegNeoForge.BLOCK_FACTORY.get());
                        entries.accept(ModBlockRegNeoForge.CHILLER.get());
                        entries.accept(ModBlockRegNeoForge.NETHERITE_CRAFTER.get());
                        entries.accept(ModBlockRegNeoForge.UPGRADED_FURNACE.get());
                        entries.accept(ModBlockRegNeoForge.GRIND_FACTORY_BLOCK.get());
                        entries.accept(ModBlockRegNeoForge.SIMPEL_FARM_LAND.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static void registerCreativeTab(){
        SimpelAddModNeoForge.LOGGER.info("Registering Creative Tab For " + SimpelAddModNeoForge.MODID);
    }
}
