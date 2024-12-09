package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;

public class ModCreativeTabForge {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpelAddMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SIMPEL_TAB = CREATIVE_MODE_TABS.register(ModNames.CREATIVETAB,
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItemRegForge.GRINDERHEAD.get()))
                    .title(Component.translatable("creativetab.simpel_tab"))
                    .displayItems((pParameters, entries) -> {
                        //items grind heads
                        entries.accept(ModItemRegForge.GRINDERHEAD.get());
                        entries.accept(ModItemRegForge.GRINDERHEADNETHERITE.get());
                        entries.accept(ModItemRegForge.GRINDERHEADUNOBTIANIUM.get());
                        //items random
                        entries.accept(ModItemRegForge.WOODFIBER.get());
                        entries.accept(ModItemRegForge.FLESH.get());
                        entries.accept(ModItemRegForge.OBSIDAININGOT.get());
                        entries.accept(ModItemRegForge.NEHTERITE_SHARD.get());
                        entries.accept(ModItemRegForge.DIAMOND_SHARD.get());
                        entries.accept(ModItemRegForge.EMERALD_SHARD.get());
                        entries.accept(ModItemRegForge.NEHTERITE_SHARD_RAW.get());
                        entries.accept(ModItemRegForge.DIAMOND_SHARD_RAW.get());
                        entries.accept(ModItemRegForge.EMERALD_SHARD_RAW.get());
                        entries.accept(ModItemRegForge.UNOBTIANIUMSCRAP.get());
                        entries.accept(ModItemRegForge.REPAIRTOOL.get());
                        entries.accept(ModItemRegForge.FIREPROOFTOOL.get());
                        entries.accept(ModItemRegForge.FEEDINGTOOL.get());
                        entries.accept(ModItemRegForge.GROWSTAFF.get());
                        entries.accept(ModItemRegForge.FUELCHUNKS.get());
                        entries.accept(ModItemRegForge.HOMEWAND.get());
                        entries.accept(ModItemRegForge.HOMECRYSTAL.get());
                        //items upgrade
                        entries.accept(ModItemRegForge.SPEEDUPGRADE_1.get());
                        entries.accept(ModItemRegForge.SPEEDUPGRADE_2.get());
                        entries.accept(ModItemRegForge.SPEEDUPGRADE_3.get());
                        entries.accept(ModItemRegForge.SPEEDUPGRADE_4.get());
                        entries.accept(ModItemRegForge.BOOSTUPGRADE.get());
                        entries.accept(ModItemRegForge.XPBOOSTUPGRADE.get());
                        //items sandwich
                        entries.accept(ModItemRegForge.SANDWICH_MUT.get());
                        entries.accept(ModItemRegForge.SANDWICH_BEEF.get());
                        entries.accept(ModItemRegForge.SANDWICH_PORK.get());
                        entries.accept(ModItemRegForge.SANDWICH_CHECKIN.get());
                        entries.accept(ModItemRegForge.SANDWICH_VEG.get());
                        entries.accept(ModItemRegForge.SANDWICH_MEET_LOVE.get());
                        entries.accept(ModItemRegForge.SANDWICH_MEET_LOVE_VEG.get());
                        //items template
                        entries.accept(ModItemRegForge.BLANKUPGRADE.get());
                        entries.accept(ModItemRegForge.BLANKUPGRADE_RAW.get());
                        entries.accept(ModItemRegForge.OBSIDIANUPGRADE_SMITHING.get());
                        entries.accept(ModItemRegForge.UNOBTANIUMUPGRADE_SMITHING.get());
                        //items dust
                        entries.accept(ModItemRegForge.GOLDDUST.get());
                        entries.accept(ModItemRegForge.IRONDUST.get());
                        entries.accept(ModItemRegForge.COPPERDUST.get());
                        entries.accept(ModItemRegForge.NETHERITEDUST.get());
                        entries.accept(ModItemRegForge.NEHTERITE_SHARD_DUST.get());
                        entries.accept(ModItemRegForge.DIAMOND_SHARD_DUST.get());
                        entries.accept(ModItemRegForge.EMERALD_SHARD_DUST.get());
                        entries.accept(ModItemRegForge.UNOBTIANIUMDUST.get());
                        entries.accept(ModItemRegForge.OBSIDAINDUST.get());
                        //items elytra
                        entries.accept(ModItemRegForge.SIMPEL_ELITRA_HALF.get());
                        entries.accept(ModItemRegForge.SIMPEL_ELITRA_PART1.get());
                        entries.accept(ModItemRegForge.SIMPEL_ELITRA_PART2.get());
                        //items obsidian tire
                        entries.accept(ModItemRegForge.OBSIDAINAXE.get());
                        entries.accept(ModItemRegForge.OBSIDIANHOE.get());
                        entries.accept(ModItemRegForge.OBSIDIANSWORD.get());
                        entries.accept(ModItemRegForge.OBSIDIANPICKAXE.get());
                        entries.accept(ModItemRegForge.OBSIDIANSPADE.get());
                        entries.accept(ModItemRegForge.OBSIDIANHELMET.get());
                        entries.accept(ModItemRegForge.OBSIDIANCHEST.get());
                        entries.accept(ModItemRegForge.OBSIDIANLEGS.get());
                        entries.accept(ModItemRegForge.OBSIDIANBOOTS.get());
                        //items Obsidirite tire
                        entries.accept(ModItemRegForge.OBSIDIRITEAXE.get());
                        entries.accept(ModItemRegForge.OBSIDIRITEHOE.get());
                        entries.accept(ModItemRegForge.OBSIDIRITESWORD.get());
                        entries.accept(ModItemRegForge.OBSIDIRITEPICKAXE.get());
                        entries.accept(ModItemRegForge.OBSIDIRITESPADE.get());
                        entries.accept(ModItemRegForge.OBSIDIRITEHELMET.get());
                        entries.accept(ModItemRegForge.OBSIDIRITECHEST.get());
                        entries.accept(ModItemRegForge.OBSIDIRITELEGS.get());
                        entries.accept(ModItemRegForge.OBSIDIRITEBOOTS.get());
                        //items Unobtiannetherite tire
                        entries.accept(ModItemRegForge.UNOBTIANNETHERITEAXE.get());
                        entries.accept(ModItemRegForge.UNOBTIANNETHERITEHOE.get());
                        entries.accept(ModItemRegForge.UNOBTIANNETHERITESWORD.get());
                        entries.accept(ModItemRegForge.UNOBTIANNETHERITEPICKAXE.get());
                        entries.accept(ModItemRegForge.UNOBTIANNETHERITESPADE.get());
                        entries.accept(ModItemRegForge.UNOBTIANNETHERITEHELMET.get());
                        entries.accept(ModItemRegForge.UNOBTIANNETHERITECHEST.get());
                        entries.accept(ModItemRegForge.UNOBTIANNETHERITELEGS.get());
                        entries.accept(ModItemRegForge.UNOBTIANNETHERITEBOOTS.get());
                        //items UnObtianObsidirtie tire
                        entries.accept(ModItemRegForge.UNOBTIANOBSIDIRITEAXE.get());
                        entries.accept(ModItemRegForge.UNOBTIANOBSIDIRITEHOE.get());
                        entries.accept(ModItemRegForge.UNOBTIANOBSIDIRITESWORD.get());
                        entries.accept(ModItemRegForge.UNOBTIANOBSIDIRITEPICKAXE.get());
                        entries.accept(ModItemRegForge.UNOBTIANOBSIDIRITESPADE.get());
                        entries.accept(ModItemRegForge.UNOBTIANOBSIDIRITEHELMET.get());
                        entries.accept(ModItemRegForge.UNOBTIANOBSIDIRITECHEST.get());
                        entries.accept(ModItemRegForge.UNOBTIANOBSIDIRITELEGS.get());
                        entries.accept(ModItemRegForge.UNOBTIANOBSIDIRITEBOOTS.get());
                        //blocks
                        //ores
                        entries.accept(ModBlockRegForge.DEEPSLATE_DEBRI_ORE.get());
                        entries.accept(ModBlockRegForge.NETHERRACK_DEBRI_ORE.get());
                        entries.accept(ModBlockRegForge.NETHERRACK_COAL_ORE.get());
                        entries.accept(ModBlockRegForge.NETHERRACK_COPPER_ORE.get());
                        entries.accept(ModBlockRegForge.NETHERRACK_IRON_ORE.get());
                        entries.accept(ModBlockRegForge.NETHERRACK_GOLD_ORE.get());
                        entries.accept(ModBlockRegForge.NETHERRACK_DIAMOND_ORE.get());
                        entries.accept(ModBlockRegForge.NETHERRACK_EMERALD_ORE.get());
                        entries.accept(ModBlockRegForge.NETHERRACK_LAPIS_ORE.get());
                        entries.accept(ModBlockRegForge.NETHERRACK_REDSTONE_ORE.get());
                        entries.accept(ModBlockRegForge.END_DEBRI_ORE.get());
                        entries.accept(ModBlockRegForge.END_COAL_ORE.get());
                        entries.accept(ModBlockRegForge.END_COPPER_ORE.get());
                        entries.accept(ModBlockRegForge.END_IRON_ORE.get());
                        entries.accept(ModBlockRegForge.END_GOLD_ORE.get());
                        entries.accept(ModBlockRegForge.END_DIAMOND_ORE.get());
                        entries.accept(ModBlockRegForge.END_EMERALD_ORE.get());
                        entries.accept(ModBlockRegForge.END_LAPIS_ORE.get());
                        entries.accept(ModBlockRegForge.END_REDSTONE_ORE.get());
                        entries.accept(ModBlockRegForge.UNOBTANIUM_ORE.get());
                        //entities
                        entries.accept(ModBlockRegForge.BLOCK_FACTORY.get());
                        entries.accept(ModBlockRegForge.CHILLER.get());
                        entries.accept(ModBlockRegForge.GRINDER_BLOCK.get());
                        entries.accept(ModBlockRegForge.GRINDER_BLOCK_UP.get());
                        entries.accept(ModBlockRegForge.UPGRADED_FURNACE.get());
                        entries.accept(ModBlockRegForge.NETHERITE_CRAFTER.get());
                        entries.accept(ModBlockRegForge.GRIND_FACTORY_BLOCK.get());
                        entries.accept(ModBlockRegForge.TICK_ACCELERATOR.get());
                        entries.accept(ModBlockRegForge.TOOLREPAIR.get());
                        //other
                        entries.accept(ModBlockRegForge.SIMPEL_FARM_LAND.get());
                        entries.accept(ModBlockRegForge.CHUNK_TOURCH.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        SimpelAddMod.modtabRegText();
        CREATIVE_MODE_TABS.register(eventBus);
    }


}
