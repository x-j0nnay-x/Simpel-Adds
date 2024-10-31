package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import java.util.function.Supplier;

public class ModItemRegNeoForge {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SimpelAddModNeoForge.MODID);
//wings
    public static final Supplier<Item> SIMPEL_ELITRA_HALF = ITEMS.register("simpel_elitra_half", () -> ModItems.SIMPEL_ELITRA_HALF);
    public static final Supplier<Item> SIMPEL_ELITRA_PART1 = ITEMS.register("simpel_elitra_part1", () -> ModItems.SIMPEL_ELITRA_PART1);
    public static final Supplier<Item> SIMPEL_ELITRA_PART2 = ITEMS.register("simpel_elitra_part2", () -> ModItems.SIMPEL_ELITRA_PART2);
//grind head
    public static final Supplier<Item> GRINDERHEAD = ITEMS.register("grinderhead", () -> ModItems.GRINDERHEAD);
    public static final Supplier<Item> GRINDERHEADNEHTERITE = ITEMS.register("grinderhead_netherite", () -> ModItems.GRINDERHEADNEHTERITE);
    public static final Supplier<Item> GRINDERHEADUNOBTIANIUM = ITEMS.register("grinderhead_unobtanium", () -> ModItems.GRINDERHEADUNOBTIANIUM);
//random
    public static final Supplier<Item> WOODFIBER = ITEMS.register("woodfiber", () -> ModItems.WOODFIBER);
    public static final Supplier<Item> FLESH = ITEMS.register("flesh", () -> ModItems.FLESH);
    public static final Supplier<Item> NEHTERITE_SHARD = ITEMS.register("netherite_shard", () -> ModItems.NEHTERITE_SHARD);
    public static final Supplier<Item> NEHTERITE_SHARD_RAW = ITEMS.register("netherite_shard_raw", () -> ModItems.NEHTERITE_SHARD_RAW);
    public static final Supplier<Item> OBSIDAININGOT = ITEMS.register("obsidianingot", () -> ModItems.OBSIDAININGOT);
    public static final Supplier<Item> UNOBTIANIUMSCRAP = ITEMS.register("unobtanium_scrap", () -> ModItems.UNOBTIANIUMSCRAP);
    public static final Supplier<Item> REPAIRTOOL = ITEMS.register("repairtool", ()-> ModItems.REPAIRTOOL);
    public static final Supplier<Item> FIREPROOFTOOL = ITEMS.register("fireprooftool", ()-> ModItems.FIREPROOFTOOL);
    public static final Supplier<Item> FEEDINGTOOL = ITEMS.register("feedingtool", ()-> ModItems.FEEDINGTOOL);
//upgrades
    public static final Supplier<Item> SPEEDUPGRADE_1 = ITEMS.register("speedupgrade_1", () -> ModItems.SPEEDUPGRADE_1);
    public static final Supplier<Item> SPEEDUPGRADE_2 = ITEMS.register("speedupgrade_2", () -> ModItems.SPEEDUPGRADE_2);
    public static final Supplier<Item> SPEEDUPGRADE_3 = ITEMS.register("speedupgrade_3", () -> ModItems.SPEEDUPGRADE_3);
    public static final Supplier<Item> BOOSTUPGRADE = ITEMS.register("boostupgrade", () -> ModItems.BOOSTUPGRADE);
    public static final Supplier<Item> XPBOOSTUPGRADE = ITEMS.register("xpboostupgrade", () -> ModItems.XPBOOSTUPGRADE);
//sandwiches
    public static final Supplier<Item> SANDWICH_BEEF = ITEMS.register("sandwich_beef", () -> ModItems.SANDWICH_BEEF);
    public static final Supplier<Item> SANDWICH_MUT = ITEMS.register("sandwich_mut", () -> ModItems.SANDWICH_MUT);
    public static final Supplier<Item> SANDWICH_PORK = ITEMS.register("sandwich_pork", () -> ModItems.SANDWICH_PORK);
    public static final Supplier<Item> SANDWICH_CHECKIN = ITEMS.register("sandwich_checkin", () -> ModItems.SANDWICH_CHECKIN);
    public static final Supplier<Item> SANDWICH_VEG = ITEMS.register("sandwich_veg", () -> ModItems.SANDWICH_VEG);
    public static final Supplier<Item> SANDWICH_MEET_LOVE = ITEMS.register("sandwich_meet_love", () -> ModItems.SANDWICH_MEET_LOVE);
    public static final Supplier<Item> SANDWICH_MEET_LOVE_VEG = ITEMS.register("sandwich_meet_love_veg", () -> ModItems.SANDWICH_MEET_LOVE_VEG);
//templates
    public static final Supplier<Item> BLANKUPGRADE = ITEMS.register("blankupgrade", () -> ModItems.BLANKUPGRADE);
    public static final Supplier<Item> BLANKUPGRADE_RAW = ITEMS.register("blankupgrade_raw", () -> ModItems.BLANKUPGRADE_RAW);
    public static final Supplier<Item> OBSIDIANUPGRADE_SMITHING = ITEMS.register("obsidian_upgrade_smithing", () -> ModItems.OBSIDIANUPGRADE_SMITHING);
    public static final Supplier<Item> UNOBTANIUMUPGRADE_SMITHING = ITEMS.register("unobtanium_upgrade_smithing", () -> ModItems.UNOBTANIUMUPGRADE_SMITHING);
//dusts
    public static final Supplier<Item> COPPERDUST = ITEMS.register("copperdust", () -> ModItems.COPPERDUST);
    public static final Supplier<Item> IRONDUST = ITEMS.register("irondust", () -> ModItems.IRONDUST);
    public static final Supplier<Item> GOLDDUST = ITEMS.register("golddust", () -> ModItems.GOLDDUST);
    public static final Supplier<Item> NETHERITEDUST = ITEMS.register("netheritedust", () -> ModItems.NETHERITEDUST);
    public static final Supplier<Item> NEHTERITE_SHARD_DUST = ITEMS.register("netherite_shard_dust", () -> ModItems.NEHTERITE_SHARD_DUST);
    public static final Supplier<Item> OBSIDAINDUST = ITEMS.register("obsidiandust", () -> ModItems.OBSIDAINDUST);
    public static final Supplier<Item> UNOBTIANIUMDUST = ITEMS.register("unobtaniumdust", () -> ModItems.UNOBTIANIUMDUST);
//obsidian tear
    public static final Supplier<Item> OBSIDIANSWORD = ITEMS.register("obsidiansword", () -> ModItems.OBSIDIANSWORD);
    public static final Supplier<Item> OBSIDIANPICKAXE = ITEMS.register("obsidianpickaxe", () -> ModItems.OBSIDIANPICKAXE);
    public static final Supplier<Item> OBSIDAINAXE = ITEMS.register("obsidianaxe", () -> ModItems.OBSIDAINAXE);
    public static final Supplier<Item> OBSIDIANSPADE = ITEMS.register("obsidianspade", () -> ModItems.OBSIDIANSPADE);
    public static final Supplier<Item> OBSIDIANHOE = ITEMS.register("obsidianhoe", () -> ModItems.OBSIDIANHOE);
    public static final Supplier<Item> OBSIDIANHELMET = ITEMS.register("obsidianhelmit", () -> ModItems.OBSIDIANHELMET);
    public static final Supplier<Item> OBSIDIANCHEST = ITEMS.register("obsidianchest", () -> ModItems.OBSIDIANCHEST);
    public static final Supplier<Item> OBSIDIANLEGS = ITEMS.register("obsidianlegs", () -> ModItems.OBSIDIANLEGS);
    public static final Supplier<Item> OBSIDIANBOOTS = ITEMS.register("obsidianboots", () -> ModItems.OBSIDIANBOOTS);
//obsidirite tear
    public static final Supplier<Item> OBSIDIRITESWORD = ITEMS.register("obsidiritesword", () -> ModItems.OBSIDIRITESWORD);
    public static final Supplier<Item> OBSIDIRITEPICKAXE = ITEMS.register("obsidiritepickaxe", () -> ModItems.OBSIDIRITEPICKAXE);
    public static final Supplier<Item> OBSIDIRITEAXE = ITEMS.register("obsidiriteaxe", () -> ModItems.OBSIDIRITEAXE);
    public static final Supplier<Item> OBSIDIRITESPADE = ITEMS.register("obsidiritespade", () -> ModItems.OBSIDIRITESPADE);
    public static final Supplier<Item> OBSIDIRITEHOE = ITEMS.register("obsidiritehoe", () -> ModItems.OBSIDIRITEHOE);
    public static final Supplier<Item> OBSIDIRITEHELMET = ITEMS.register("obsidiritehelmit", () -> ModItems.OBSIDIRITEHELMET);
    public static final Supplier<Item> OBSIDIRITECHEST = ITEMS.register("obsidiritechest", () -> ModItems.OBSIDIRITECHEST);
    public static final Supplier<Item> OBSIDIRITELEGS = ITEMS.register("obsidiritelegs", () -> ModItems.OBSIDIRITELEGS);
    public static final Supplier<Item> OBSIDIRITEBOOTS = ITEMS.register("obsidiriteboots", () -> ModItems.OBSIDIRITEBOOTS);
//netherite boosted
    public static final Supplier<Item> UNOBTIANNETHERITESWORD = ITEMS.register("unobtainnetheritesword", () -> ModItems.UNOBTIANNETHERITESWORD);
    public static final Supplier<Item> UNOBTIANNETHERITEPICKAXE = ITEMS.register("unobtainnetheritepickaxe", () -> ModItems.UNOBTIANNETHERITEPICKAXE);
    public static final Supplier<Item> UNOBTIANNETHERITEAXE = ITEMS.register("unobtainnetheriteaxe", () -> ModItems.UNOBTIANNETHERITEAXE);
    public static final Supplier<Item> UNOBTIANNETHERITESPADE = ITEMS.register("unobtainnetheritespade", () -> ModItems.UNOBTIANNETHERITESPADE);
    public static final Supplier<Item> UNOBTIANNETHERITEHOE = ITEMS.register("unobtainnetheritehoe", () -> ModItems.UNOBTIANNETHERITEHOE);
    public static final Supplier<Item> UNOBTIANNETHERITEHELMET = ITEMS.register("unobtainnetheritehelmit", () -> ModItems.UNOBTIANNETHERITEHELMET);
    public static final Supplier<Item> UNOBTIANNETHERITECHEST = ITEMS.register("unobtainnetheritechest", () -> ModItems.UNOBTIANNETHERITECHEST);
    public static final Supplier<Item> UNOBTIANNETHERITELEGS = ITEMS.register("unobtainnetheritelegs", () -> ModItems.UNOBTIANNETHERITELEGS);
    public static final Supplier<Item> UNOBTIANNETHERITEBOOTS = ITEMS.register("unobtainnetheriteboots", () -> ModItems.UNOBTIANNETHERITEBOOTS);
//obsidirite boosted
    public static final Supplier<Item> UNOBTIANOBSIDIRITESWORD = ITEMS.register("unobtainobsidiritesword", () -> ModItems.UNOBTIANOBSIDIRITESWORD);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEPICKAXE = ITEMS.register("unobtainobsidiritepickaxe", () -> ModItems.UNOBTIANOBSIDIRITEPICKAXE);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEAXE = ITEMS.register("unobtainobsidiriteaxe", () -> ModItems.UNOBTIANOBSIDIRITEAXE);
    public static final Supplier<Item> UNOBTIANOBSIDIRITESPADE = ITEMS.register("unobtainobsidiritespade", () -> ModItems.UNOBTIANOBSIDIRITESPADE);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEHOE = ITEMS.register("unobtainobsidiritehoe", () -> ModItems.UNOBTIANOBSIDIRITEHOE);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEHELMET = ITEMS.register("unobtainobsidiritehelmit", () -> ModItems.UNOBTIANOBSIDIRITEHELMET);
    public static final Supplier<Item> UNOBTIANOBSIDIRITECHEST = ITEMS.register("unobtainobsidiritechest", () -> ModItems.UNOBTIANOBSIDIRITECHEST);
    public static final Supplier<Item> UNOBTIANOBSIDIRITELEGS = ITEMS.register("unobtainobsidiritelegs", () -> ModItems.UNOBTIANOBSIDIRITELEGS);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEBOOTS = ITEMS.register("unobtainobsidiriteboots", () -> ModItems.UNOBTIANOBSIDIRITEBOOTS);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void registerModItems(){
        SimpelAddModNeoForge.LOGGER.info("Registering Mod Items for " + SimpelAddModNeoForge.MODID);
    }
}