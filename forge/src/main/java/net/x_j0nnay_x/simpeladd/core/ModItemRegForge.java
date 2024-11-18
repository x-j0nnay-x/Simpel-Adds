package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;

public class ModItemRegForge {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SimpelAddModForge.MODID);
    //wings
    public static final RegistryObject<Item> SIMPEL_ELITRA_HALF = ITEMS.register(ModNames.Items.SIMPEL_ELITRA_HALF, () -> ModItems.SIMPEL_ELITRA_HALF);
    public static final RegistryObject<Item> SIMPEL_ELITRA_PART1 = ITEMS.register(ModNames.Items.SIMPEL_ELITRA_PART1, () -> ModItems.SIMPEL_ELITRA_PART1);
    public static final RegistryObject<Item> SIMPEL_ELITRA_PART2 = ITEMS.register(ModNames.Items.SIMPEL_ELITRA_PART2, () -> ModItems.SIMPEL_ELITRA_PART2);
    //grind head
    public static final RegistryObject<Item> GRINDERHEAD = ITEMS.register(ModNames.Items.GRINDERHEAD, () -> ModItems.GRINDERHEAD);
    public static final RegistryObject<Item> GRINDERHEADNETHERITE = ITEMS.register(ModNames.Items.GRINDERHEADNETHERITE, () -> ModItems.GRINDERHEADNEHTERITE);
    public static final RegistryObject<Item> GRINDERHEADUNOBTIANIUM = ITEMS.register(ModNames.Items.GRINDERHEADUNOBTIANIUM, () -> ModItems.GRINDERHEADUNOBTIANIUM);
    //random
    public static final RegistryObject<Item> WOODFIBER = ITEMS.register(ModNames.Items.WOODFIBER, () -> ModItems.WOODFIBER);
    public static final RegistryObject<Item> FLESH = ITEMS.register(ModNames.Items.FLESH, () -> ModItems.FLESH);
    public static final RegistryObject<Item> OBSIDAININGOT = ITEMS.register(ModNames.Items.OBSIDAININGOT, () -> ModItems.OBSIDAININGOT);
    public static final RegistryObject<Item> NEHTERITE_SHARD = ITEMS.register(ModNames.Items.NEHTERITE_SHARD, () -> ModItems.NEHTERITE_SHARD);
    public static final RegistryObject<Item> NEHTERITE_SHARD_RAW = ITEMS.register(ModNames.Items.NEHTERITE_SHARD_RAW, () -> ModItems.NEHTERITE_SHARD_RAW);
    public static final RegistryObject<Item> UNOBTIANIUMSCRAP = ITEMS.register(ModNames.Items.UNOBTIANIUMSCRAP, () -> ModItems.UNOBTIANIUMSCRAP);
    public static final RegistryObject<Item> REPAIRTOOL = ITEMS.register(ModNames.Items.REPAIRTOOL, () -> ModItems.REPAIRTOOL);
    public static final RegistryObject<Item> FIREPROOFTOOL = ITEMS.register(ModNames.Items.FIREPROOFTOOL, () -> ModItems.FIREPROOFTOOL);
    public static final RegistryObject<Item> FEEDINGTOOL = ITEMS.register(ModNames.Items.FEEDINGTOOL, () -> ModItems.FEEDINGTOOL);
    public static final RegistryObject<Item> GROWSTAFF = ITEMS.register(ModNames.Items.GROWSTAFF, () -> ModItems.GROWSTAFF);
    public static final RegistryObject<Item> FUELCHUNKS = ITEMS.register(ModNames.Items.FULECHUNKS, () -> ModItems.FULECHUNKS);
    //upgrades
    public static final RegistryObject<Item> SPEEDUPGRADE_1 = ITEMS.register(ModNames.Items.SPEEDUPGRADE_1, () -> ModItems.SPEEDUPGRADE_1);
    public static final RegistryObject<Item> SPEEDUPGRADE_2 = ITEMS.register(ModNames.Items.SPEEDUPGRADE_2, () -> ModItems.SPEEDUPGRADE_2);
    public static final RegistryObject<Item> SPEEDUPGRADE_3 = ITEMS.register(ModNames.Items.SPEEDUPGRADE_3, () -> ModItems.SPEEDUPGRADE_3);
    public static final RegistryObject<Item> SPEEDUPGRADE_4 = ITEMS.register(ModNames.Items.SPEEDUPGRADE_4, () -> ModItems.SPEEDUPGRADE_4);
    public static final RegistryObject<Item> BOOSTUPGRADE = ITEMS.register(ModNames.Items.BOOSTUPGRADE, () -> ModItems.BOOSTUPGRADE);
    public static final RegistryObject<Item> XPBOOSTUPGRADE = ITEMS.register(ModNames.Items.XPBOOSTUPGRADE, () -> ModItems.XPBOOSTUPGRADE);
    //sandwiches
    public static final RegistryObject<Item> SANDWICH_BEEF = ITEMS.register(ModNames.Items.SANDWICH_BEEF, () -> ModItems.SANDWICH_BEEF);
    public static final RegistryObject<Item> SANDWICH_MUT = ITEMS.register(ModNames.Items.SANDWICH_MUT, () -> ModItems.SANDWICH_MUT);
    public static final RegistryObject<Item> SANDWICH_PORK = ITEMS.register(ModNames.Items.SANDWICH_PORK, () -> ModItems.SANDWICH_PORK);
    public static final RegistryObject<Item> SANDWICH_CHECKIN = ITEMS.register(ModNames.Items.SANDWICH_CHECKIN, () -> ModItems.SANDWICH_CHECKIN);
    public static final RegistryObject<Item> SANDWICH_VEG = ITEMS.register(ModNames.Items.SANDWICH_VEG, () -> ModItems.SANDWICH_VEG);
    public static final RegistryObject<Item> SANDWICH_MEET_LOVE = ITEMS.register(ModNames.Items.SANDWICH_MEET_LOVE, () -> ModItems.SANDWICH_MEET_LOVE);
    public static final RegistryObject<Item> SANDWICH_MEET_LOVE_VEG = ITEMS.register(ModNames.Items.SANDWICH_MEET_LOVE_VEG, () -> ModItems.SANDWICH_MEET_LOVE_VEG);
    //Templates
    public static final RegistryObject<Item> BLANKUPGRADE = ITEMS.register(ModNames.Items.BLANKUPGRADE, () -> ModItems.BLANKUPGRADE);
    public static final RegistryObject<Item> BLANKUPGRADE_RAW = ITEMS.register(ModNames.Items.BLANKUPGRADE_RAW, () -> ModItems.BLANKUPGRADE_RAW);
    public static final RegistryObject<Item> OBSIDIANUPGRADE_SMITHING = ITEMS.register(ModNames.Items.OBSIDIANUPGRADE_SMITHING, () -> ModItems.OBSIDIANUPGRADE_SMITHING);
    public static final RegistryObject<Item> UNOBTANIUMUPGRADE_SMITHING = ITEMS.register(ModNames.Items.UNOBTANIUMUPGRADE_SMITHING, () -> ModItems.UNOBTANIUMUPGRADE_SMITHING);
    //dusts
    public static final RegistryObject<Item> COPPERDUST = ITEMS.register(ModNames.Items.COPPERDUST, () -> ModItems.COPPERDUST);
    public static final RegistryObject<Item> IRONDUST = ITEMS.register(ModNames.Items.IRONDUST, () -> ModItems.IRONDUST);
    public static final RegistryObject<Item> GOLDDUST = ITEMS.register(ModNames.Items.GOLDDUST, () -> ModItems.GOLDDUST);
    public static final RegistryObject<Item> NETHERITEDUST = ITEMS.register(ModNames.Items.NETHERITEDUST, () -> ModItems.NETHERITEDUST);
    public static final RegistryObject<Item> NEHTERITE_SHARD_DUST = ITEMS.register(ModNames.Items.NEHTERITE_SHARD_DUST, () -> ModItems.NEHTERITE_SHARD_DUST);
    public static final RegistryObject<Item> OBSIDAINDUST = ITEMS.register(ModNames.Items.OBSIDAINDUST, () -> ModItems.OBSIDAINDUST);
    public static final RegistryObject<Item> UNOBTIANIUMDUST = ITEMS.register(ModNames.Items.UNOBTIANIUMDUST, () -> ModItems.UNOBTIANIUMDUST);
    //obsidian tear
    public static final RegistryObject<Item> OBSIDIANSWORD = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.SWORD), () -> ModItems.OBSIDIANSWORD);
    public static final RegistryObject<Item> OBSIDIANPICKAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.PICKAXE), () -> ModItems.OBSIDIANPICKAXE);
    public static final RegistryObject<Item> OBSIDAINAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.AXE), () -> ModItems.OBSIDAINAXE);
    public static final RegistryObject<Item> OBSIDIANSPADE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.SHOVEL), () -> ModItems.OBSIDIANSPADE);
    public static final RegistryObject<Item> OBSIDIANHOE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.HOE), () -> ModItems.OBSIDIANHOE);
    public static final RegistryObject<Item> OBSIDIANHELMET = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.HELMET), () -> ModItems.OBSIDIANHELMET);
    public static final RegistryObject<Item> OBSIDIANCHEST = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.CHESTPLATE), () -> ModItems.OBSIDIANCHEST);
    public static final RegistryObject<Item> OBSIDIANLEGS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.LEGGINGS), () -> ModItems.OBSIDIANLEGS);
    public static final RegistryObject<Item> OBSIDIANBOOTS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIAN, ModNames.Tools.BOOTS), () -> ModItems.OBSIDIANBOOTS);
    //obsidirite tear
    public static final RegistryObject<Item> OBSIDIRITESWORD = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.SWORD), () -> ModItems.OBSIDIRITESWORD);
    public static final RegistryObject<Item> OBSIDIRITEPICKAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.PICKAXE), () -> ModItems.OBSIDIRITEPICKAXE);
    public static final RegistryObject<Item> OBSIDIRITEAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.AXE), () -> ModItems.OBSIDIRITEAXE);
    public static final RegistryObject<Item> OBSIDIRITESPADE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.SHOVEL), () -> ModItems.OBSIDIRITESPADE);
    public static final RegistryObject<Item> OBSIDIRITEHOE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.HOE), () -> ModItems.OBSIDIRITEHOE);
    public static final RegistryObject<Item> OBSIDIRITEHELMET = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.HELMET), () -> ModItems.OBSIDIRITEHELMET);
    public static final RegistryObject<Item> OBSIDIRITECHEST = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.CHESTPLATE), () -> ModItems.OBSIDIRITECHEST);
    public static final RegistryObject<Item> OBSIDIRITELEGS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.LEGGINGS), () -> ModItems.OBSIDIRITELEGS);
    public static final RegistryObject<Item> OBSIDIRITEBOOTS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.OBSIDIRITE, ModNames.Tools.BOOTS), () -> ModItems.OBSIDIRITEBOOTS);
    //netherite boosted
    public static final RegistryObject<Item> UNOBTIANNETHERITESWORD = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.SWORD), () -> ModItems.UNOBTIANNETHERITESWORD);
    public static final RegistryObject<Item> UNOBTIANNETHERITEPICKAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.PICKAXE), () -> ModItems.UNOBTIANNETHERITEPICKAXE);
    public static final RegistryObject<Item> UNOBTIANNETHERITEAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.AXE), () -> ModItems.UNOBTIANNETHERITEAXE);
    public static final RegistryObject<Item> UNOBTIANNETHERITESPADE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.SHOVEL), () -> ModItems.UNOBTIANNETHERITESPADE);
    public static final RegistryObject<Item> UNOBTIANNETHERITEHOE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.HOE), () -> ModItems.UNOBTIANNETHERITEHOE);
    public static final RegistryObject<Item> UNOBTIANNETHERITEHELMET = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.HELMET), () -> ModItems.UNOBTIANNETHERITEHELMET);
    public static final RegistryObject<Item> UNOBTIANNETHERITECHEST = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.CHESTPLATE), () -> ModItems.UNOBTIANNETHERITECHEST);
    public static final RegistryObject<Item> UNOBTIANNETHERITELEGS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.LEGGINGS), () -> ModItems.UNOBTIANNETHERITELEGS);
    public static final RegistryObject<Item> UNOBTIANNETHERITEBOOTS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANNETHERITE, ModNames.Tools.BOOTS), () -> ModItems.UNOBTIANNETHERITEBOOTS);
    //obsidirite boosted
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITESWORD = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.SWORD), () -> ModItems.UNOBTIANOBSIDIRITESWORD);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEPICKAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.PICKAXE), () -> ModItems.UNOBTIANOBSIDIRITEPICKAXE);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEAXE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.AXE), () -> ModItems.UNOBTIANOBSIDIRITEAXE);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITESPADE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.SHOVEL), () -> ModItems.UNOBTIANOBSIDIRITESPADE);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEHOE = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.HOE), () -> ModItems.UNOBTIANOBSIDIRITEHOE);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEHELMET = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.HELMET), () -> ModItems.UNOBTIANOBSIDIRITEHELMET);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITECHEST = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.CHESTPLATE), () -> ModItems.UNOBTIANOBSIDIRITECHEST);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITELEGS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.LEGGINGS), () -> ModItems.UNOBTIANOBSIDIRITELEGS);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEBOOTS = ITEMS.register(ModNames.Tools.getToolNames(ModNames.Tools.UNOBTIANOBSIDIRITE, ModNames.Tools.BOOTS), () -> ModItems.UNOBTIANOBSIDIRITEBOOTS);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void registerModItems(){
        SimpelAddModForge.LOGGER.info("Registering Mod Items for " + SimpelAddModForge.MODID);
    }
}