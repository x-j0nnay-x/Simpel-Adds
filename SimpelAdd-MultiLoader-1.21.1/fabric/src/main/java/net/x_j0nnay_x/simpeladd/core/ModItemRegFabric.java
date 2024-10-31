package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.x_j0nnay_x.simpeladd.SimpelAddModFabric;

public class ModItemRegFabric {
//wings
    public static final Item SIMPEL_ELITRA_HALF = registerItem("simpel_elitra_half", ModItems.SIMPEL_ELITRA_HALF);
    public static final Item SIMPEL_ELITRA_PART1 = registerItem("simpel_elitra_part1", ModItems.SIMPEL_ELITRA_PART1);
    public static final Item SIMPEL_ELITRA_PART2 = registerItem("simpel_elitra_part2", ModItems.SIMPEL_ELITRA_PART2);
//grind head
    public static final Item GRINDERHEAD = registerItem("grinderhead", ModItems.GRINDERHEAD);
    public static final Item GRINDERHEADNETHERITE = registerItem("grinderhead_netherite", ModItems.GRINDERHEADNEHTERITE);
    public static final Item GRINDERHEADUNOBTIANIUM = registerItem("grinderhead_unobtanium", ModItems.GRINDERHEADUNOBTIANIUM);
//random
    public static final Item WOODFIBER = registerItem("woodfiber", ModItems.WOODFIBER);
    public static final Item FLESH = registerItem("flesh", ModItems.FLESH);
    public static final Item OBSIDAININGOT = registerItem("obsidianingot", ModItems.OBSIDAININGOT);
    public static final Item NEHTERITE_SHARD = registerItem("netherite_shard", ModItems.NEHTERITE_SHARD);
    public static final Item NEHTERITE_SHARD_RAW = registerItem("netherite_shard_raw", ModItems.NEHTERITE_SHARD_RAW);
    public static final Item UNOBTIANIUMSCRAP = registerItem("unobtanium_scrap", ModItems.UNOBTIANIUMSCRAP);
    public static final Item REPAIRTOOL = registerItem("repairtool", ModItems.REPAIRTOOL);
    public static final Item FIREPROOFTOOL = registerItem("fireprooftool", ModItems.FIREPROOFTOOL);
    public static final Item FEEDINGTOOL = registerItem("feedingtool", ModItems.FEEDINGTOOL);
//upgrades
    public static final Item SPEEDUPGRADE_1 = registerItem("speedupgrade_1", ModItems.SPEEDUPGRADE_1);
    public static final Item SPEEDUPGRADE_2 = registerItem("speedupgrade_2", ModItems.SPEEDUPGRADE_2);
    public static final Item SPEEDUPGRADE_3 = registerItem("speedupgrade_3", ModItems.SPEEDUPGRADE_3);
    public static final Item BOOSTUPGRADE = registerItem("boostupgrade", ModItems.BOOSTUPGRADE);
    public static final Item XPBOOSTUPGRADE = registerItem("xpboostupgrade", ModItems.XPBOOSTUPGRADE);
//sandwiches
    public static final Item SANDWICH_BEEF = registerItem("sandwich_beef", ModItems.SANDWICH_BEEF);
    public static final Item SANDWICH_MUT = registerItem("sandwich_mut", ModItems.SANDWICH_MUT);
    public static final Item SANDWICH_PORK = registerItem("sandwich_pork", ModItems.SANDWICH_PORK);
    public static final Item SANDWICH_CHECKIN = registerItem("sandwich_checkin", ModItems.SANDWICH_CHECKIN);
    public static final Item SANDWICH_VEG = registerItem("sandwich_veg", ModItems.SANDWICH_VEG);
    public static final Item SANDWICH_MEET_LOVE = registerItem("sandwich_meet_love", ModItems.SANDWICH_MEET_LOVE);
    public static final Item SANDWICH_MEET_LOVE_VEG = registerItem("sandwich_meet_love_veg", ModItems.SANDWICH_MEET_LOVE_VEG);
//upgrades
    public static final Item BLANKUPGRADE = registerItem("blankupgrade", ModItems.BLANKUPGRADE);
    public static final Item BLANKUPGRADE_RAW = registerItem("blankupgrade_raw", ModItems.BLANKUPGRADE_RAW);
    public static final Item OBSIDIANUPGRADE_SMITHING = registerItem("obsidian_upgrade_smithing", ModItems.OBSIDIANUPGRADE_SMITHING);
    public static final Item UNOBTANIUMUPGRADE_SMITHING = registerItem("unobtanium_upgrade_smithing", ModItems.UNOBTANIUMUPGRADE_SMITHING);
//dusts
    public static final Item COPPERDUST = registerItem("copperdust", ModItems.COPPERDUST);
    public static final Item IRONDUST = registerItem("irondust", ModItems.IRONDUST);
    public static final Item GOLDDUST = registerItem("golddust", ModItems.GOLDDUST);
    public static final Item NETHERITEDUST = registerItem("netheritedust", ModItems.NETHERITEDUST);
    public static final Item NEHTERITE_SHARD_DUST = registerItem("netherite_shard_dust", ModItems.NEHTERITE_SHARD_DUST);
    public static final Item OBSIDAINDUST = registerItem("obsidiandust", ModItems.OBSIDAINDUST);
    public static final Item UNOBTIANIUMDUST = registerItem("unobtaniumdust", ModItems.UNOBTIANIUMDUST);
//obsidian tear
    public static final Item OBSIDIANSWORD = registerItem("obsidiansword", ModItems.OBSIDIANSWORD);
    public static final Item OBSIDIANPICKAXE = registerItem("obsidianpickaxe", ModItems.OBSIDIANPICKAXE);
    public static final Item OBSIDAINAXE = registerItem("obsidianaxe", ModItems.OBSIDAINAXE);
    public static final Item OBSIDIANSPADE = registerItem("obsidianspade", ModItems.OBSIDIANSPADE);
    public static final Item OBSIDIANHOE = registerItem("obsidianhoe", ModItems.OBSIDIANHOE);
    public static final Item OBSIDIANHELMET = registerItem("obsidianhelmit", ModItems.OBSIDIANHELMET);
    public static final Item OBSIDIANCHEST = registerItem("obsidianchest", ModItems.OBSIDIANCHEST);
    public static final Item OBSIDIANLEGS = registerItem("obsidianlegs", ModItems.OBSIDIANLEGS);
    public static final Item OBSIDIANBOOTS = registerItem("obsidianboots", ModItems.OBSIDIANBOOTS);
//obsidirite tear
    public static final Item OBSIDIRITESWORD = registerItem("obsidiritesword", ModItems.OBSIDIRITESWORD);
    public static final Item OBSIDIRITEPICKAXE = registerItem("obsidiritepickaxe", ModItems.OBSIDIRITEPICKAXE);
    public static final Item OBSIDIRITEAXE = registerItem("obsidiriteaxe", ModItems.OBSIDIRITEAXE);
    public static final Item OBSIDIRITESPADE = registerItem("obsidiritespade", ModItems.OBSIDIRITESPADE);
    public static final Item OBSIDIRITEHOE = registerItem("obsidiritehoe", ModItems.OBSIDIRITEHOE);
    public static final Item OBSIDIRITEHELMET = registerItem("obsidiritehelmit", ModItems.OBSIDIRITEHELMET);
    public static final Item OBSIDIRITECHEST = registerItem("obsidiritechest", ModItems.OBSIDIRITECHEST);
    public static final Item OBSIDIRITELEGS = registerItem("obsidiritelegs", ModItems.OBSIDIRITELEGS);
    public static final Item OBSIDIRITEBOOTS = registerItem("obsidiriteboots", ModItems.OBSIDIRITEBOOTS);
//netherite boosted
    public static final Item UNOBTIANNETHERITESWORD = registerItem("unobtainnetheritesword", ModItems.UNOBTIANNETHERITESWORD);
    public static final Item UNOBTIANNETHERITEPICKAXE = registerItem("unobtainnetheritepickaxe", ModItems.UNOBTIANNETHERITEPICKAXE);
    public static final Item UNOBTIANNETHERITEAXE = registerItem("unobtainnetheriteaxe", ModItems.UNOBTIANNETHERITEAXE);
    public static final Item UNOBTIANNETHERITESPADE = registerItem("unobtainnetheritespade", ModItems.UNOBTIANNETHERITESPADE);
    public static final Item UNOBTIANNETHERITEHOE = registerItem("unobtainnetheritehoe", ModItems.UNOBTIANNETHERITEHOE);
    public static final Item UNOBTIANNETHERITEHELMET = registerItem("unobtainnetheritehelmit", ModItems.UNOBTIANNETHERITEHELMET);
    public static final Item UNOBTIANNETHERITECHEST = registerItem("unobtainnetheritechest", ModItems.UNOBTIANNETHERITECHEST);
    public static final Item UNOBTIANNETHERITELEGS = registerItem("unobtainnetheritelegs", ModItems.UNOBTIANNETHERITELEGS);
    public static final Item UNOBTIANNETHERITEBOOTS = registerItem("unobtainnetheriteboots", ModItems.UNOBTIANNETHERITEBOOTS);
//obsidirite boosted
    public static final Item UNOBTIANOBSIDIRITESWORD = registerItem("unobtainobsidiritesword", ModItems.UNOBTIANOBSIDIRITESWORD);
    public static final Item UNOBTIANOBSIDIRITEPICKAXE = registerItem("unobtainobsidiritepickaxe", ModItems.UNOBTIANOBSIDIRITEPICKAXE);
    public static final Item UNOBTIANOBSIDIRITEAXE = registerItem("unobtainobsidiriteaxe", ModItems.UNOBTIANOBSIDIRITEAXE);
    public static final Item UNOBTIANOBSIDIRITESPADE = registerItem("unobtainobsidiritespade", ModItems.UNOBTIANOBSIDIRITESPADE);
    public static final Item UNOBTIANOBSIDIRITEHOE = registerItem("unobtainobsidiritehoe", ModItems.UNOBTIANOBSIDIRITEHOE);
    public static final Item UNOBTIANOBSIDIRITEHELMET = registerItem("unobtainobsidiritehelmit", ModItems.UNOBTIANOBSIDIRITEHELMET);
    public static final Item UNOBTIANOBSIDIRITECHEST = registerItem("unobtainobsidiritechest", ModItems.UNOBTIANOBSIDIRITECHEST);
    public static final Item UNOBTIANOBSIDIRITELEGS = registerItem("unobtainobsidiritelegs", ModItems.UNOBTIANOBSIDIRITELEGS);
    public static final Item UNOBTIANOBSIDIRITEBOOTS = registerItem("unobtainobsidiriteboots", ModItems.UNOBTIANOBSIDIRITEBOOTS);

    public static void registerModItems(){
        SimpelAddModFabric.LOGGER.info("Registrering Mod Items for " + SimpelAddModFabric.MODID);
    }
    private static Item registerItem(String name, Item item){
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(SimpelAddModFabric.MODID, name), item);
    }

}
