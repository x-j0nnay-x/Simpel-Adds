package net.x_j0nnay_x.simpeladd.core;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;

public class ModCreativeTabFabric {

    public static void registerTab()
    {
        SimpelAddMod.modtabRegText();

        ResourceKey<CreativeModeTab> tab = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, ModNames.CREATIVETAB));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, tab, FabricItemGroup.builder()
                .icon(ModItemRegFabric.GRINDERHEAD::getDefaultInstance)
                .title(Component.translatable("creativetab.simpel_tab"))
                .displayItems((context, entries) ->
                {
                //items grind heads
                        entries.accept(ModItemRegFabric.GRINDERHEAD);
                        entries.accept(ModItemRegFabric.GRINDERHEADNETHERITE);
                        entries.accept(ModItemRegFabric.GRINDERHEADUNOBTIANIUM);
                //items random
                        entries.accept(ModItemRegFabric.WOODFIBER);
                        entries.accept(ModItemRegFabric.FLESH);
                        entries.accept(ModItemRegFabric.OBSIDAININGOT);
                        entries.accept(ModItemRegFabric.NEHTERITE_SHARD);
                        entries.accept(ModItemRegFabric.NEHTERITE_SHARD_RAW);
                        entries.accept(ModItemRegFabric.UNOBTIANIUMSCRAP);
                        entries.accept(ModItemRegFabric.REPAIRTOOL);
                        entries.accept(ModItemRegFabric.FIREPROOFTOOL);
                        entries.accept(ModItemRegFabric.FEEDINGTOOL);
                        entries.accept(ModItemRegFabric.GROWSTAFF);
                        entries.accept(ModItemRegFabric.FULECHUNKS);
                //items upgrade
                        entries.accept(ModItemRegFabric.SPEEDUPGRADE_1);
                        entries.accept(ModItemRegFabric.SPEEDUPGRADE_2);
                        entries.accept(ModItemRegFabric.SPEEDUPGRADE_3);
                        entries.accept(ModItemRegFabric.SPEEDUPGRADE_4);
                        entries.accept(ModItemRegFabric.BOOSTUPGRADE);
                        entries.accept(ModItemRegFabric.XPBOOSTUPGRADE);
                //items sandwich
                        entries.accept(ModItemRegFabric.SANDWICH_MUT);
                        entries.accept(ModItemRegFabric.SANDWICH_BEEF);
                        entries.accept(ModItemRegFabric.SANDWICH_PORK);
                        entries.accept(ModItemRegFabric.SANDWICH_CHECKIN);
                        entries.accept(ModItemRegFabric.SANDWICH_VEG);
                        entries.accept(ModItemRegFabric.SANDWICH_MEET_LOVE);
                        entries.accept(ModItemRegFabric.SANDWICH_MEET_LOVE_VEG);
                //items template
                        entries.accept(ModItemRegFabric.BLANKUPGRADE);
                        entries.accept(ModItemRegFabric.BLANKUPGRADE_RAW);
                        entries.accept(ModItemRegFabric.OBSIDIANUPGRADE_SMITHING);
                        entries.accept(ModItemRegFabric.UNOBTANIUMUPGRADE_SMITHING);
                //items dust
                        entries.accept(ModItemRegFabric.GOLDDUST);
                        entries.accept(ModItemRegFabric.IRONDUST);
                        entries.accept(ModItemRegFabric.COPPERDUST);
                        entries.accept(ModItemRegFabric.NETHERITEDUST);
                        entries.accept(ModItemRegFabric.NEHTERITE_SHARD_DUST);
                        entries.accept(ModItemRegFabric.UNOBTIANIUMDUST);
                        entries.accept(ModItemRegFabric.OBSIDAINDUST);
                //items elytra
                        entries.accept(ModItemRegFabric.SIMPEL_ELITRA_HALF);
                        entries.accept(ModItemRegFabric.SIMPEL_ELITRA_PART1);
                        entries.accept(ModItemRegFabric.SIMPEL_ELITRA_PART2);
                //items obsidian tire
                        entries.accept(ModItemRegFabric.OBSIDAINAXE);
                        entries.accept(ModItemRegFabric.OBSIDIANHOE);
                        entries.accept(ModItemRegFabric.OBSIDIANSWORD);
                        entries.accept(ModItemRegFabric.OBSIDIANPICKAXE);
                        entries.accept(ModItemRegFabric.OBSIDIANSPADE);
                        entries.accept(ModItemRegFabric.OBSIDIANHELMET);
                        entries.accept(ModItemRegFabric.OBSIDIANCHEST);
                        entries.accept(ModItemRegFabric.OBSIDIANLEGS);
                        entries.accept(ModItemRegFabric.OBSIDIANBOOTS);
                //items Obsidirite tire
                        entries.accept(ModItemRegFabric.OBSIDIRITEAXE);
                        entries.accept(ModItemRegFabric.OBSIDIRITEHOE);
                        entries.accept(ModItemRegFabric.OBSIDIRITESWORD);
                        entries.accept(ModItemRegFabric.OBSIDIRITEPICKAXE);
                        entries.accept(ModItemRegFabric.OBSIDIRITESPADE);
                        entries.accept(ModItemRegFabric.OBSIDIRITEHELMET);
                        entries.accept(ModItemRegFabric.OBSIDIRITECHEST);
                        entries.accept(ModItemRegFabric.OBSIDIRITELEGS);
                        entries.accept(ModItemRegFabric.OBSIDIRITEBOOTS);
                //items Unobtiannetherite tire
                        entries.accept(ModItemRegFabric.UNOBTIANNETHERITEAXE);
                        entries.accept(ModItemRegFabric.UNOBTIANNETHERITEHOE);
                        entries.accept(ModItemRegFabric.UNOBTIANNETHERITESWORD);
                        entries.accept(ModItemRegFabric.UNOBTIANNETHERITEPICKAXE);
                        entries.accept(ModItemRegFabric.UNOBTIANNETHERITESPADE);
                        entries.accept(ModItemRegFabric.UNOBTIANNETHERITEHELMET);
                        entries.accept(ModItemRegFabric.UNOBTIANNETHERITECHEST);
                        entries.accept(ModItemRegFabric.UNOBTIANNETHERITELEGS);
                        entries.accept(ModItemRegFabric.UNOBTIANNETHERITEBOOTS);
                //items UnObtianObsidirtie tire
                        entries.accept(ModItemRegFabric.UNOBTIANOBSIDIRITEAXE);
                        entries.accept(ModItemRegFabric.UNOBTIANOBSIDIRITEHOE);
                        entries.accept(ModItemRegFabric.UNOBTIANOBSIDIRITESWORD);
                        entries.accept(ModItemRegFabric.UNOBTIANOBSIDIRITEPICKAXE);
                        entries.accept(ModItemRegFabric.UNOBTIANOBSIDIRITESPADE);
                        entries.accept(ModItemRegFabric.UNOBTIANOBSIDIRITEHELMET);
                        entries.accept(ModItemRegFabric.UNOBTIANOBSIDIRITECHEST);
                        entries.accept(ModItemRegFabric.UNOBTIANOBSIDIRITELEGS);
                        entries.accept(ModItemRegFabric.UNOBTIANOBSIDIRITEBOOTS);
                 //blocks
                        entries.accept(ModBlockRegFabric.DEEPSLATE_DEBRI_ORE);
                        entries.accept(ModBlockRegFabric.UNOBTANIUM_ORE);
                        entries.accept(ModBlockRegFabric.BLOCK_FACTORY);
                        entries.accept(ModBlockRegFabric.CHILLER);
                        entries.accept(ModBlockRegFabric.GRINDER_BLOCK);
                        entries.accept(ModBlockRegFabric.GRINDER_BLOCK_UP);
                        entries.accept(ModBlockRegFabric.UPGRADED_FURNACE);
                        entries.accept(ModBlockRegFabric.NETHERITE_CRAFTER);
                        entries.accept(ModBlockRegFabric.GRIND_FACTORY_BLOCK);
                        entries.accept(ModBlockRegFabric.SIMPEL_FARM_LAND);
                    }).build());
    }


}
