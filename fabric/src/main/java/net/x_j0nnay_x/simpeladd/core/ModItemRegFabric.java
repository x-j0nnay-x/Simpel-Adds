package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.item.HomeWandFabric;

public class ModItemRegFabric {
    //wings
    public static final Item SIMPEL_ELITRA_HALF = registerItem(ModNames.Items.SIMPEL_ELITRA_HALF, ModItems.SIMPEL_ELITRA_HALF);
    public static final Item SIMPEL_ELITRA_PART1 = registerItem(ModNames.Items.SIMPEL_ELITRA_PART1, ModItems.SIMPEL_ELITRA_PART1);
    public static final Item SIMPEL_ELITRA_PART2 = registerItem(ModNames.Items.SIMPEL_ELITRA_PART2, ModItems.SIMPEL_ELITRA_PART2);
    //grind head
    public static final Item GRINDERHEAD = registerItem(ModNames.Items.GRINDERHEAD, ModItems.GRINDERHEAD);
    public static final Item GRINDERHEADNETHERITE = registerItem(ModNames.Items.GRINDERHEADNETHERITE, ModItems.GRINDERHEADNEHTERITE);
    public static final Item GRINDERHEADUNOBTIANIUM = registerItem(ModNames.Items.GRINDERHEADUNOBTIANIUM, ModItems.GRINDERHEADUNOBTIANIUM);
    //random
    public static final Item WOODFIBER = registerItem(ModNames.Items.WOODFIBER, ModItems.WOODFIBER);
    public static final Item FLESH = registerItem(ModNames.Items.FLESH, ModItems.FLESH);
    public static final Item OBSIDAININGOT = registerItem(ModNames.Items.OBSIDAININGOT, ModItems.OBSIDAININGOT);
    public static final Item NEHTERITE_SHARD = registerItem(ModNames.Items.NEHTERITE_SHARD, ModItems.NEHTERITE_SHARD);
    public static final Item DIAMOND_SHARD = registerItem(ModNames.Items.DIAMOND_SHARD, ModItems.DIAMOND_SHARD);
    public static final Item EMERALD_SHARD = registerItem(ModNames.Items.EMERALD_SHARD, ModItems.EMERALD_SHARD);
    public static final Item NEHTERITE_SHARD_RAW = registerItem(ModNames.Items.NEHTERITE_SHARD_RAW, ModItems.NEHTERITE_SHARD_RAW);
    public static final Item DIAMOND_SHARD_RAW = registerItem(ModNames.Items.DIAMOND_SHARD_RAW, ModItems.DIAMOND_SHARD_RAW);
    public static final Item EMERALD_SHARD_RAW = registerItem(ModNames.Items.EMERALD_SHARD_RAW, ModItems.EMERALD_SHARD_RAW);
    public static final Item UNOBTIANIUMSCRAP = registerItem(ModNames.Items.UNOBTIANIUMSCRAP, ModItems.UNOBTIANIUMSCRAP);
    public static final Item REPAIRTOOL = registerItem(ModNames.Items.REPAIRTOOL, ModItems.REPAIRTOOL);
    public static final Item FIREPROOFTOOL = registerItem(ModNames.Items.FIREPROOFTOOL, ModItems.FIREPROOFTOOL);
    public static final Item FEEDINGTOOL = registerItem(ModNames.Items.FEEDINGTOOL, ModItems.FEEDINGTOOL);
    public static final Item GROWSTAFF = registerItem(ModNames.Items.GROWSTAFF, ModItems.GROWSTAFF);
    public static final Item FUELCHUNKS = registerItem(ModNames.Items.FUELCHUNKS, ModItems.FUELCHUNKS);
    public static final Item HOMEWAND = registerItem(ModNames.Items.HOMEWAND, new HomeWandFabric(ModItems.HOMEWAND_USES));
    public static final Item HOMECRYSTAL = registerItem(ModNames.Items.HOMECRYSTAL, ModItems.HOMECRYSTAL);
    public static final Item XPCRYSTAL = registerItem(ModNames.Items.XPCRYSTAL, ModItems.XPCRYSTAL);
    //upgrades
    public static final Item SPEEDUPGRADE_1 = registerItem(ModNames.Items.SPEEDUPGRADE_1, ModItems.SPEEDUPGRADE_1);
    public static final Item SPEEDUPGRADE_2 = registerItem(ModNames.Items.SPEEDUPGRADE_2, ModItems.SPEEDUPGRADE_2);
    public static final Item SPEEDUPGRADE_3 = registerItem(ModNames.Items.SPEEDUPGRADE_3, ModItems.SPEEDUPGRADE_3);
    public static final Item SPEEDUPGRADE_4 = registerItem(ModNames.Items.SPEEDUPGRADE_4, ModItems.SPEEDUPGRADE_4);
    public static final Item BOOSTUPGRADE = registerItem(ModNames.Items.BOOSTUPGRADE, ModItems.BOOSTUPGRADE);
    public static final Item XPBOOSTUPGRADE = registerItem(ModNames.Items.XPBOOSTUPGRADE, ModItems.XPBOOSTUPGRADE);
    //sandwiches
    public static final Item SANDWICH_BEEF = registerItem(ModNames.Items.SANDWICH_BEEF, ModItems.SANDWICH_BEEF);
    public static final Item SANDWICH_MUT = registerItem(ModNames.Items.SANDWICH_MUT, ModItems.SANDWICH_MUT);
    public static final Item SANDWICH_PORK = registerItem(ModNames.Items.SANDWICH_PORK, ModItems.SANDWICH_PORK);
    public static final Item SANDWICH_CHECKIN = registerItem(ModNames.Items.SANDWICH_CHECKIN, ModItems.SANDWICH_CHECKIN);
    public static final Item SANDWICH_VEG = registerItem(ModNames.Items.SANDWICH_VEG, ModItems.SANDWICH_VEG);
    public static final Item SANDWICH_MEET_LOVE = registerItem(ModNames.Items.SANDWICH_MEET_LOVE, ModItems.SANDWICH_MEET_LOVE);
    public static final Item SANDWICH_MEET_LOVE_VEG = registerItem(ModNames.Items.SANDWICH_MEET_LOVE_VEG, ModItems.SANDWICH_MEET_LOVE_VEG);
    //Templates
    public static final Item BLANKUPGRADE = registerItem(ModNames.Items.BLANKUPGRADE, ModItems.BLANKUPGRADE);
    public static final Item BLANKUPGRADE_RAW = registerItem(ModNames.Items.BLANKUPGRADE_RAW, ModItems.BLANKUPGRADE_RAW);
    public static final Item OBSIDIANUPGRADE_SMITHING = registerItem(ModNames.Items.OBSIDIANUPGRADE_SMITHING, ModItems.OBSIDIANUPGRADE_SMITHING);
    public static final Item UNOBTANIUMUPGRADE_SMITHING = registerItem(ModNames.Items.UNOBTANIUMUPGRADE_SMITHING, ModItems.UNOBTANIUMUPGRADE_SMITHING);
    //dusts
    public static final Item COPPERDUST = registerItem(ModNames.Items.COPPERDUST, ModItems.COPPERDUST);
    public static final Item IRONDUST = registerItem(ModNames.Items.IRONDUST, ModItems.IRONDUST);
    public static final Item GOLDDUST = registerItem(ModNames.Items.GOLDDUST, ModItems.GOLDDUST);
    public static final Item NETHERITEDUST = registerItem(ModNames.Items.NETHERITEDUST, ModItems.NETHERITEDUST);
    public static final Item NEHTERITE_SHARD_DUST = registerItem(ModNames.Items.NEHTERITE_SHARD_DUST, ModItems.NEHTERITE_SHARD_DUST);
    public static final Item DIAMOND_SHARD_DUST = registerItem(ModNames.Items.DIAMOND_SHARD_DUST, ModItems.DIAMOND_SHARD_DUST);
    public static final Item EMERALD_SHARD_DUST = registerItem(ModNames.Items.EMERALD_SHARD_DUST, ModItems.EMERALD_SHARD_DUST);
    public static final Item OBSIDAINDUST = registerItem(ModNames.Items.OBSIDAINDUST, ModItems.OBSIDAINDUST);
    public static final Item UNOBTIANIUMDUST = registerItem(ModNames.Items.UNOBTIANIUMDUST, ModItems.UNOBTIANIUMDUST);
    //obsidian tear
    public static final Item OBSIDIANSWORD = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.SWORD), ModItems.OBSIDIANSWORD);
    public static final Item OBSIDIANPICKAXE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.PICKAXE), ModItems.OBSIDIANPICKAXE);
    public static final Item OBSIDAINAXE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.AXE), ModItems.OBSIDAINAXE);
    public static final Item OBSIDIANSPADE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.SHOVEL), ModItems.OBSIDIANSPADE);
    public static final Item OBSIDIANHOE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.HOE), ModItems.OBSIDIANHOE);
    public static final Item OBSIDIANHELMET = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.HELMET), ModItems.OBSIDIANHELMET);
    public static final Item OBSIDIANCHEST = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.CHESTPLATE), ModItems.OBSIDIANCHEST);
    public static final Item OBSIDIANLEGS = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.LEGGINGS), ModItems.OBSIDIANLEGS);
    public static final Item OBSIDIANBOOTS = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.BOOTS), ModItems.OBSIDIANBOOTS);
    //obsidirite tear
    public static final Item OBSIDIRITESWORD = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.SWORD), ModItems.OBSIDIRITESWORD);
    public static final Item OBSIDIRITEPICKAXE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.PICKAXE), ModItems.OBSIDIRITEPICKAXE);
    public static final Item OBSIDIRITEAXE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.AXE), ModItems.OBSIDIRITEAXE);
    public static final Item OBSIDIRITESPADE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.SHOVEL), ModItems.OBSIDIRITESPADE);
    public static final Item OBSIDIRITEHOE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.HOE), ModItems.OBSIDIRITEHOE);
    public static final Item OBSIDIRITEHELMET = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.HELMET), ModItems.OBSIDIRITEHELMET);
    public static final Item OBSIDIRITECHEST = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.CHESTPLATE), ModItems.OBSIDIRITECHEST);
    public static final Item OBSIDIRITELEGS = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.LEGGINGS), ModItems.OBSIDIRITELEGS);
    public static final Item OBSIDIRITEBOOTS = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.BOOTS), ModItems.OBSIDIRITEBOOTS);
    //netherite boosted
    public static final Item UNOBTIANNETHERITESWORD = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.SWORD), ModItems.UNOBTIANNETHERITESWORD);
    public static final Item UNOBTIANNETHERITEPICKAXE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.PICKAXE), ModItems.UNOBTIANNETHERITEPICKAXE);
    public static final Item UNOBTIANNETHERITEAXE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.AXE), ModItems.UNOBTIANNETHERITEAXE);
    public static final Item UNOBTIANNETHERITESPADE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.SHOVEL), ModItems.UNOBTIANNETHERITESPADE);
    public static final Item UNOBTIANNETHERITEHOE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.HOE), ModItems.UNOBTIANNETHERITEHOE);
    public static final Item UNOBTIANNETHERITEHELMET = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.HELMET), ModItems.UNOBTIANNETHERITEHELMET);
    public static final Item UNOBTIANNETHERITECHEST = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.CHESTPLATE), ModItems.UNOBTIANNETHERITECHEST);
    public static final Item UNOBTIANNETHERITELEGS = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.LEGGINGS), ModItems.UNOBTIANNETHERITELEGS);
    public static final Item UNOBTIANNETHERITEBOOTS = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.BOOTS), ModItems.UNOBTIANNETHERITEBOOTS);
    //obsidirite boosted
    public static final Item UNOBTIANOBSIDIRITESWORD = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.SWORD), ModItems.UNOBTIANOBSIDIRITESWORD);
    public static final Item UNOBTIANOBSIDIRITEPICKAXE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.PICKAXE), ModItems.UNOBTIANOBSIDIRITEPICKAXE);
    public static final Item UNOBTIANOBSIDIRITEAXE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.AXE), ModItems.UNOBTIANOBSIDIRITEAXE);
    public static final Item UNOBTIANOBSIDIRITESPADE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.SHOVEL), ModItems.UNOBTIANOBSIDIRITESPADE);
    public static final Item UNOBTIANOBSIDIRITEHOE = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.HOE), ModItems.UNOBTIANOBSIDIRITEHOE);
    public static final Item UNOBTIANOBSIDIRITEHELMET = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.HELMET), ModItems.UNOBTIANOBSIDIRITEHELMET);
    public static final Item UNOBTIANOBSIDIRITECHEST = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.CHESTPLATE), ModItems.UNOBTIANOBSIDIRITECHEST);
    public static final Item UNOBTIANOBSIDIRITELEGS = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.LEGGINGS), ModItems.UNOBTIANOBSIDIRITELEGS);
    public static final Item UNOBTIANOBSIDIRITEBOOTS = registerItem(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.BOOTS), ModItems.UNOBTIANOBSIDIRITEBOOTS);

    private static Item registerItem(String name, Item item){
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, name), item);
    }

    public static void registerItems() {
        SimpelAddMod.modItemRegText();
    }
}
