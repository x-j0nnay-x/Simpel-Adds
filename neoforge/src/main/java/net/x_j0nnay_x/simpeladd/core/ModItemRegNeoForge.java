package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.SimpelAddModNeoForge;
import net.x_j0nnay_x.simpeladd.item.HomeWandNeoForge;

import java.util.function.Supplier;

public class ModItemRegNeoForge {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SimpelAddMod.MOD_ID);
    //wings
    public static final Supplier<Item> SIMPEL_ELITRA_HALF = ITEMS.register(ModNames.Items.SIMPEL_ELITRA_HALF, () -> ModItems.SIMPEL_ELITRA_HALF);
    public static final Supplier<Item> SIMPEL_ELITRA_PART1 = ITEMS.register(ModNames.Items.SIMPEL_ELITRA_PART1, () -> ModItems.SIMPEL_ELITRA_PART1);
    public static final Supplier<Item> SIMPEL_ELITRA_PART2 = ITEMS.register(ModNames.Items.SIMPEL_ELITRA_PART2, () -> ModItems.SIMPEL_ELITRA_PART2);
    //grind head
    public static final Supplier<Item> GRINDERHEAD = ITEMS.register(ModNames.Items.GRINDERHEAD, () -> ModItems.GRINDERHEAD);
    public static final Supplier<Item> GRINDERHEADNETHERITE = ITEMS.register(ModNames.Items.GRINDERHEADNETHERITE, () -> ModItems.GRINDERHEADNEHTERITE);
    public static final Supplier<Item> GRINDERHEADUNOBTIANIUM = ITEMS.register(ModNames.Items.GRINDERHEADUNOBTIANIUM, () -> ModItems.GRINDERHEADUNOBTIANIUM);
    //random
    public static final Supplier<Item> WOODFIBER = ITEMS.register(ModNames.Items.WOODFIBER, () -> ModItems.WOODFIBER);
    public static final Supplier<Item> FLESH = ITEMS.register(ModNames.Items.FLESH, () -> ModItems.FLESH);
    public static final Supplier<Item> OBSIDAININGOT = ITEMS.register(ModNames.Items.OBSIDAININGOT, () -> ModItems.OBSIDAININGOT);
    public static final Supplier<Item> NEHTERITE_SHARD = ITEMS.register(ModNames.Items.NEHTERITE_SHARD, () -> ModItems.NEHTERITE_SHARD);
    public static final Supplier<Item> NEHTERITE_SHARD_RAW = ITEMS.register(ModNames.Items.NEHTERITE_SHARD_RAW, () -> ModItems.NEHTERITE_SHARD_RAW);
    public static final Supplier<Item> DIAMOND_SHARD = ITEMS.register(ModNames.Items.DIAMOND_SHARD, () -> ModItems.DIAMOND_SHARD);
    public static final Supplier<Item> DIAMOND_SHARD_RAW = ITEMS.register(ModNames.Items.DIAMOND_SHARD_RAW, () -> ModItems.DIAMOND_SHARD_RAW);
    public static final Supplier<Item> EMERALD_SHARD = ITEMS.register(ModNames.Items.EMERALD_SHARD, () -> ModItems.EMERALD_SHARD);
    public static final Supplier<Item> EMERALD_SHARD_RAW = ITEMS.register(ModNames.Items.EMERALD_SHARD_RAW, () -> ModItems.EMERALD_SHARD_RAW);
    public static final Supplier<Item> UNOBTIANIUMSCRAP = ITEMS.register(ModNames.Items.UNOBTIANIUMSCRAP, () -> ModItems.UNOBTIANIUMSCRAP);
    public static final Supplier<Item> REPAIRTOOL = ITEMS.register(ModNames.Items.REPAIRTOOL, () -> ModItems.REPAIRTOOL);
    public static final Supplier<Item> FIREPROOFTOOL = ITEMS.register(ModNames.Items.FIREPROOFTOOL, () -> ModItems.FIREPROOFTOOL);
    public static final Supplier<Item> FEEDINGTOOL = ITEMS.register(ModNames.Items.FEEDINGTOOL, () -> ModItems.FEEDINGTOOL);
    public static final Supplier<Item> GROWSTAFF = ITEMS.register(ModNames.Items.GROWSTAFF, () -> ModItems.GROWSTAFF);
    public static final Supplier<Item> FUELCHUNKS = ITEMS.register(ModNames.Items.FUELCHUNKS, () -> ModItems.FUELCHUNKS);
    public static final Supplier<Item> HOMEWAND = ITEMS.register(ModNames.Items.HOMEWAND, () -> new HomeWandNeoForge(new Item.Properties()));
    public static final Supplier<Item> HOMECRYSTAL = ITEMS.register(ModNames.Items.HOMECRYSTAL, () -> ModItems.HOMECRYSTAL);
    //upgrades
    public static final Supplier<Item> SPEEDUPGRADE_1 = ITEMS.register(ModNames.Items.SPEEDUPGRADE_1, () -> ModItems.SPEEDUPGRADE_1);
    public static final Supplier<Item> SPEEDUPGRADE_2 = ITEMS.register(ModNames.Items.SPEEDUPGRADE_2, () -> ModItems.SPEEDUPGRADE_2);
    public static final Supplier<Item> SPEEDUPGRADE_3 = ITEMS.register(ModNames.Items.SPEEDUPGRADE_3, () -> ModItems.SPEEDUPGRADE_3);
    public static final Supplier<Item> SPEEDUPGRADE_4 = ITEMS.register(ModNames.Items.SPEEDUPGRADE_4, () -> ModItems.SPEEDUPGRADE_4);
    public static final Supplier<Item> BOOSTUPGRADE = ITEMS.register(ModNames.Items.BOOSTUPGRADE, () -> ModItems.BOOSTUPGRADE);
    public static final Supplier<Item> XPBOOSTUPGRADE = ITEMS.register(ModNames.Items.XPBOOSTUPGRADE, () -> ModItems.XPBOOSTUPGRADE);
    //sandwiches
    public static final Supplier<Item> SANDWICH_BEEF = ITEMS.register(ModNames.Items.SANDWICH_BEEF, () -> ModItems.SANDWICH_BEEF);
    public static final Supplier<Item> SANDWICH_MUT = ITEMS.register(ModNames.Items.SANDWICH_MUT, () -> ModItems.SANDWICH_MUT);
    public static final Supplier<Item> SANDWICH_PORK = ITEMS.register(ModNames.Items.SANDWICH_PORK, () -> ModItems.SANDWICH_PORK);
    public static final Supplier<Item> SANDWICH_CHECKIN = ITEMS.register(ModNames.Items.SANDWICH_CHECKIN, () -> ModItems.SANDWICH_CHECKIN);
    public static final Supplier<Item> SANDWICH_VEG = ITEMS.register(ModNames.Items.SANDWICH_VEG, () -> ModItems.SANDWICH_VEG);
    public static final Supplier<Item> SANDWICH_MEET_LOVE = ITEMS.register(ModNames.Items.SANDWICH_MEET_LOVE, () -> ModItems.SANDWICH_MEET_LOVE);
    public static final Supplier<Item> SANDWICH_MEET_LOVE_VEG = ITEMS.register(ModNames.Items.SANDWICH_MEET_LOVE_VEG, () -> ModItems.SANDWICH_MEET_LOVE_VEG);
    //Templates
    public static final Supplier<Item> BLANKUPGRADE = ITEMS.register(ModNames.Items.BLANKUPGRADE, () -> ModItems.BLANKUPGRADE);
    public static final Supplier<Item> BLANKUPGRADE_RAW = ITEMS.register(ModNames.Items.BLANKUPGRADE_RAW, () -> ModItems.BLANKUPGRADE_RAW);
    public static final Supplier<Item> OBSIDIANUPGRADE_SMITHING = ITEMS.register(ModNames.Items.OBSIDIANUPGRADE_SMITHING, () -> ModItems.OBSIDIANUPGRADE_SMITHING);
    public static final Supplier<Item> UNOBTANIUMUPGRADE_SMITHING = ITEMS.register(ModNames.Items.UNOBTANIUMUPGRADE_SMITHING, () -> ModItems.UNOBTANIUMUPGRADE_SMITHING);
    //dusts
    public static final Supplier<Item> COPPERDUST = ITEMS.register(ModNames.Items.COPPERDUST, () -> ModItems.COPPERDUST);
    public static final Supplier<Item> IRONDUST = ITEMS.register(ModNames.Items.IRONDUST, () -> ModItems.IRONDUST);
    public static final Supplier<Item> GOLDDUST = ITEMS.register(ModNames.Items.GOLDDUST, () -> ModItems.GOLDDUST);
    public static final Supplier<Item> NETHERITEDUST = ITEMS.register(ModNames.Items.NETHERITEDUST, () -> ModItems.NETHERITEDUST);
    public static final Supplier<Item> NEHTERITE_SHARD_DUST = ITEMS.register(ModNames.Items.NEHTERITE_SHARD_DUST, () -> ModItems.NEHTERITE_SHARD_DUST);
    public static final Supplier<Item> DIAMOND_SHARD_DUST = ITEMS.register(ModNames.Items.DIAMOND_SHARD_DUST, () -> ModItems.DIAMOND_SHARD_DUST);
    public static final Supplier<Item> EMERALD_SHARD_DUST = ITEMS.register(ModNames.Items.EMERALD_SHARD_DUST, () -> ModItems.EMERALD_SHARD_DUST);
    public static final Supplier<Item> OBSIDAINDUST = ITEMS.register(ModNames.Items.OBSIDAINDUST, () -> ModItems.OBSIDAINDUST);
    public static final Supplier<Item> UNOBTIANIUMDUST = ITEMS.register(ModNames.Items.UNOBTIANIUMDUST, () -> ModItems.UNOBTIANIUMDUST);
    //obsidian tear
    public static final Supplier<Item> OBSIDIANSWORD = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.SWORD), () -> ModItems.OBSIDIANSWORD);
    public static final Supplier<Item> OBSIDIANPICKAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.PICKAXE), () -> ModItems.OBSIDIANPICKAXE);
    public static final Supplier<Item> OBSIDAINAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.AXE), () -> ModItems.OBSIDAINAXE);
    public static final Supplier<Item> OBSIDIANSPADE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.SHOVEL), () -> ModItems.OBSIDIANSPADE);
    public static final Supplier<Item> OBSIDIANHOE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.HOE), () -> ModItems.OBSIDIANHOE);
    public static final Supplier<Item> OBSIDIANHELMET = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.HELMET), () -> ModItems.OBSIDIANHELMET);
    public static final Supplier<Item> OBSIDIANCHEST = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.CHESTPLATE), () -> ModItems.OBSIDIANCHEST);
    public static final Supplier<Item> OBSIDIANLEGS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.LEGGINGS), () -> ModItems.OBSIDIANLEGS);
    public static final Supplier<Item> OBSIDIANBOOTS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.BOOTS), () -> ModItems.OBSIDIANBOOTS);
    //obsidirite tear
    public static final Supplier<Item> OBSIDIRITESWORD = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.SWORD), () -> ModItems.OBSIDIRITESWORD);
    public static final Supplier<Item> OBSIDIRITEPICKAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.PICKAXE), () -> ModItems.OBSIDIRITEPICKAXE);
    public static final Supplier<Item> OBSIDIRITEAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.AXE), () -> ModItems.OBSIDIRITEAXE);
    public static final Supplier<Item> OBSIDIRITESPADE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.SHOVEL), () -> ModItems.OBSIDIRITESPADE);
    public static final Supplier<Item> OBSIDIRITEHOE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.HOE), () -> ModItems.OBSIDIRITEHOE);
    public static final Supplier<Item> OBSIDIRITEHELMET = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.HELMET), () -> ModItems.OBSIDIRITEHELMET);
    public static final Supplier<Item> OBSIDIRITECHEST = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.CHESTPLATE), () -> ModItems.OBSIDIRITECHEST);
    public static final Supplier<Item> OBSIDIRITELEGS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.LEGGINGS), () -> ModItems.OBSIDIRITELEGS);
    public static final Supplier<Item> OBSIDIRITEBOOTS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.BOOTS), () -> ModItems.OBSIDIRITEBOOTS);
    //netherite boosted
    public static final Supplier<Item> UNOBTIANNETHERITESWORD = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.SWORD), () -> ModItems.UNOBTIANNETHERITESWORD);
    public static final Supplier<Item> UNOBTIANNETHERITEPICKAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.PICKAXE), () -> ModItems.UNOBTIANNETHERITEPICKAXE);
    public static final Supplier<Item> UNOBTIANNETHERITEAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.AXE), () -> ModItems.UNOBTIANNETHERITEAXE);
    public static final Supplier<Item> UNOBTIANNETHERITESPADE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.SHOVEL), () -> ModItems.UNOBTIANNETHERITESPADE);
    public static final Supplier<Item> UNOBTIANNETHERITEHOE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.HOE), () -> ModItems.UNOBTIANNETHERITEHOE);
    public static final Supplier<Item> UNOBTIANNETHERITEHELMET = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.HELMET), () -> ModItems.UNOBTIANNETHERITEHELMET);
    public static final Supplier<Item> UNOBTIANNETHERITECHEST = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.CHESTPLATE), () -> ModItems.UNOBTIANNETHERITECHEST);
    public static final Supplier<Item> UNOBTIANNETHERITELEGS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.LEGGINGS), () -> ModItems.UNOBTIANNETHERITELEGS);
    public static final Supplier<Item> UNOBTIANNETHERITEBOOTS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.BOOTS), () -> ModItems.UNOBTIANNETHERITEBOOTS);
    //obsidirite boosted
    public static final Supplier<Item> UNOBTIANOBSIDIRITESWORD = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.SWORD), () -> ModItems.UNOBTIANOBSIDIRITESWORD);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEPICKAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.PICKAXE), () -> ModItems.UNOBTIANOBSIDIRITEPICKAXE);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.AXE), () -> ModItems.UNOBTIANOBSIDIRITEAXE);
    public static final Supplier<Item> UNOBTIANOBSIDIRITESPADE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.SHOVEL), () -> ModItems.UNOBTIANOBSIDIRITESPADE);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEHOE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.HOE), () -> ModItems.UNOBTIANOBSIDIRITEHOE);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEHELMET = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.HELMET), () -> ModItems.UNOBTIANOBSIDIRITEHELMET);
    public static final Supplier<Item> UNOBTIANOBSIDIRITECHEST = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.CHESTPLATE), () -> ModItems.UNOBTIANOBSIDIRITECHEST);
    public static final Supplier<Item> UNOBTIANOBSIDIRITELEGS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.LEGGINGS), () -> ModItems.UNOBTIANOBSIDIRITELEGS);
    public static final Supplier<Item> UNOBTIANOBSIDIRITEBOOTS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.BOOTS), () -> ModItems.UNOBTIANOBSIDIRITEBOOTS);

    public static void register(IEventBus eventBus) {
        SimpelAddMod.modItemRegText();
        ITEMS.register(eventBus);
    }

}