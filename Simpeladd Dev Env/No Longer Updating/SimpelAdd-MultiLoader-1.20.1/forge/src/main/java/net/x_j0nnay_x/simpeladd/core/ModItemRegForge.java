package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladd.SimpelAddModForge;
import net.x_j0nnay_x.simpeladd.item.GrinderHeadItem;

public class ModItemRegForge {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SimpelAddModForge.MODID);
//other items
    public static final RegistryObject<Item> SIMPEL_ELITRA_HALF = ITEMS.register("simpel_elitra_half", () -> ModItems.SIMPEL_ELITRA_HALF);
    public static final RegistryObject<Item> SIMPEL_ELITRA_PART1 = ITEMS.register("simpel_elitra_part1", () -> ModItems.SIMPEL_ELITRA_PART1);
    public static final RegistryObject<Item> SIMPEL_ELITRA_PART2 = ITEMS.register("simpel_elitra_part2", () -> ModItems.SIMPEL_ELITRA_PART2);

    public static final RegistryObject<Item> GRINDERHEAD = ITEMS.register("grinderhead", () -> ModItems.GRINDERHEAD);
    public static final RegistryObject<Item> GOLDSTICK = ITEMS.register("goldstick", () -> ModItems.GOLDSTICK);
    public static final RegistryObject<Item> WOODFIBER = ITEMS.register("woodfiber", () -> ModItems.WOODFIBER);
    public static final RegistryObject<Item> FLESH = ITEMS.register("flesh", () -> ModItems.FLESH);

    public static final RegistryObject<Item> SPEEDUPGRADE_1 = ITEMS.register("speedupgrade_1", () -> ModItems.SPEEDUPGRADE_1);
    public static final RegistryObject<Item> SPEEDUPGRADE_2 = ITEMS.register("speedupgrade_2", () -> ModItems.SPEEDUPGRADE_2);
    public static final RegistryObject<Item> SPEEDUPGRADE_3 = ITEMS.register("speedupgrade_3", () -> ModItems.SPEEDUPGRADE_3);
    public static final RegistryObject<Item> BOOSTUPGRADE = ITEMS.register("boostupgrade", () -> ModItems.BOOSTUPGRADE);
//ancient shards stuff
    public static final RegistryObject<Item> NEHTERITE_SHARD = ITEMS.register("netherite_shard", () -> ModItems.NEHTERITE_SHARD);
    public static final RegistryObject<Item> NEHTERITE_SHARD_DUST = ITEMS.register("netherite_shard_dust", () -> ModItems.NEHTERITE_SHARD_DUST);
    public static final RegistryObject<Item> NEHTERITE_SHARD_RAW = ITEMS.register("netherite_shard_raw", () -> ModItems.NEHTERITE_SHARD_RAW);
//sandwiches
    public static final RegistryObject<Item> SANDWICH_BEEF = ITEMS.register("sandwich_beef", () -> ModItems.SANDWICH_BEEF);
    public static final RegistryObject<Item> SANDWICH_MUT = ITEMS.register("sandwich_mut", () -> ModItems.SANDWICH_MUT);
    public static final RegistryObject<Item> SANDWICH_PORK = ITEMS.register("sandwich_pork", () -> ModItems.SANDWICH_PORK);
    public static final RegistryObject<Item> SANDWICH_CHECKIN = ITEMS.register("sandwich_checkin", () -> ModItems.SANDWICH_CHECKIN);
    public static final RegistryObject<Item> SANDWICH_VEG = ITEMS.register("sandwich_veg", () -> ModItems.SANDWICH_VEG);
    public static final RegistryObject<Item> SANDWICH_MEET_LOVE = ITEMS.register("sandwich_meet_love", () -> ModItems.SANDWICH_MEET_LOVE);
    public static final RegistryObject<Item> SANDWICH_MEET_LOVE_VEG = ITEMS.register("sandwich_meet_love_veg", () -> ModItems.SANDWICH_MEET_LOVE_VEG);

//upgrades
    public static final RegistryObject<Item> BLANKUPGRADE = ITEMS.register("blankupgrade", () -> ModItems.BLANKUPGRADE);
    public static final RegistryObject<Item> BLANKUPGRADE_RAW = ITEMS.register("blankupgrade_raw", () -> ModItems.BLANKUPGRADE_RAW);
    public static final RegistryObject<Item> OBSIDIANUPGRADE_SMITHING = ITEMS.register("obsidian_upgrade_smithing", () -> ModItems.OBSIDIANUPGRADE_SMITHING);
    public static final RegistryObject<Item> UNOBTANIUMUPGRADE_SMITHING = ITEMS.register("unobtanium_upgrade_smithing", () -> ModItems.UNOBTANIUMUPGRADE_SMITHING);

//dusts
    public static final RegistryObject<Item> COPPERDUST = ITEMS.register("copperdust", () -> ModItems.COPPERDUST);
    public static final RegistryObject<Item> IRONDUST = ITEMS.register("irondust", () -> ModItems.IRONDUST);
    public static final RegistryObject<Item> GOLDDUST = ITEMS.register("golddust", () -> ModItems.GOLDDUST);
    public static final RegistryObject<Item> NETHERITEDUST = ITEMS.register("netheritedust", () -> ModItems.NETHERITEDUST);

//obsidian tear
    public static final RegistryObject<Item> OBSIDAININGOT = ITEMS.register("obsidianingot", () -> ModItems.OBSIDAININGOT);
    public static final RegistryObject<Item> OBSIDAINDUST = ITEMS.register("obsidiandust", () -> ModItems.OBSIDAINDUST);

    public static final RegistryObject<Item> OBSIDIANSWORD = ITEMS.register("obsidiansword", () -> ModItems.OBSIDIANSWORD);
    public static final RegistryObject<Item> OBSIDIANPICKAXE = ITEMS.register("obsidianpickaxe", () -> ModItems.OBSIDIANPICKAXE);
    public static final RegistryObject<Item> OBSIDAINAXE = ITEMS.register("obsidianaxe", () -> ModItems.OBSIDAINAXE);
    public static final RegistryObject<Item> OBSIDIANSPADE = ITEMS.register("obsidianspade", () -> ModItems.OBSIDIANSPADE);
    public static final RegistryObject<Item> OBSIDIANHOE = ITEMS.register("obsidianhoe", () -> ModItems.OBSIDIANHOE);

    public static final RegistryObject<Item> OBSIDIANHELMET = ITEMS.register("obsidianhelmit", () -> ModItems.OBSIDIANHELMET);
    public static final RegistryObject<Item> OBSIDIANCHEST = ITEMS.register("obsidianchest", () -> ModItems.OBSIDIANCHEST);
    public static final RegistryObject<Item> OBSIDIANLEGS = ITEMS.register("obsidianlegs", () -> ModItems.OBSIDIANLEGS);
    public static final RegistryObject<Item> OBSIDIANBOOTS = ITEMS.register("obsidianboots", () -> ModItems.OBSIDIANBOOTS);

//obsidirite tear
    public static final RegistryObject<Item> OBSIDIRITESWORD = ITEMS.register("obsidiritesword", () -> ModItems.OBSIDIRITESWORD);
    public static final RegistryObject<Item> OBSIDIRITEPICKAXE = ITEMS.register("obsidiritepickaxe", () -> ModItems.OBSIDIRITEPICKAXE);
    public static final RegistryObject<Item> OBSIDIRITEAXE = ITEMS.register("obsidiriteaxe", () -> ModItems.OBSIDIRITEAXE);
    public static final RegistryObject<Item> OBSIDIRITESPADE = ITEMS.register("obsidiritespade", () -> ModItems.OBSIDIRITESPADE);
    public static final RegistryObject<Item> OBSIDIRITEHOE = ITEMS.register("obsidiritehoe", () -> ModItems.OBSIDIRITEHOE);

    public static final RegistryObject<Item> OBSIDIRITEHELMET = ITEMS.register("obsidiritehelmit", () -> ModItems.OBSIDIRITEHELMET);
    public static final RegistryObject<Item> OBSIDIRITECHEST = ITEMS.register("obsidiritechest", () -> ModItems.OBSIDIRITECHEST);
    public static final RegistryObject<Item> OBSIDIRITELEGS = ITEMS.register("obsidiritelegs", () -> ModItems.OBSIDIRITELEGS);
    public static final RegistryObject<Item> OBSIDIRITEBOOTS = ITEMS.register("obsidiriteboots", () -> ModItems.OBSIDIRITEBOOTS);

//unobtain stuffs
    public static final RegistryObject<Item> UNOBTIANIUMDUST = ITEMS.register("unobtaniumdust", () -> ModItems.UNOBTIANIUMDUST);
    public static final RegistryObject<Item> UNOBTIANIUMSCRAP = ITEMS.register("unobtanium_scrap", () -> ModItems.UNOBTIANIUMSCRAP);

//netherite boosted
    public static final RegistryObject<Item> UNOBTIANNETHERITESWORD = ITEMS.register("unobtainnetheritesword", () -> ModItems.UNOBTIANNETHERITESWORD);
    public static final RegistryObject<Item> UNOBTIANNETHERITEPICKAXE = ITEMS.register("unobtainnetheritepickaxe", () -> ModItems.UNOBTIANNETHERITEPICKAXE);
    public static final RegistryObject<Item> UNOBTIANNETHERITEAXE = ITEMS.register("unobtainnetheriteaxe", () -> ModItems.UNOBTIANNETHERITEAXE);
    public static final RegistryObject<Item> UNOBTIANNETHERITESPADE = ITEMS.register("unobtainnetheritespade", () -> ModItems.UNOBTIANNETHERITESPADE);
    public static final RegistryObject<Item> UNOBTIANNETHERITEHOE = ITEMS.register("unobtainnetheritehoe", () -> ModItems.UNOBTIANNETHERITEHOE);

    public static final RegistryObject<Item> UNOBTIANNETHERITEHELMET = ITEMS.register("unobtainnetheritehelmit", () -> ModItems.UNOBTIANNETHERITEHELMET);
    public static final RegistryObject<Item> UNOBTIANNETHERITECHEST = ITEMS.register("unobtainnetheritechest", () -> ModItems.UNOBTIANNETHERITECHEST);
    public static final RegistryObject<Item> UNOBTIANNETHERITELEGS = ITEMS.register("unobtainnetheritelegs", () -> ModItems.UNOBTIANNETHERITELEGS);
    public static final RegistryObject<Item> UNOBTIANNETHERITEBOOTS = ITEMS.register("unobtainnetheriteboots", () -> ModItems.UNOBTIANNETHERITEBOOTS);
//obsidirite boosted
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITESWORD = ITEMS.register("unobtainobsidiritesword", () -> ModItems.UNOBTIANOBSIDIRITESWORD);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEPICKAXE = ITEMS.register("unobtainobsidiritepickaxe", () -> ModItems.UNOBTIANOBSIDIRITEPICKAXE);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEAXE = ITEMS.register("unobtainobsidiriteaxe", () -> ModItems.UNOBTIANOBSIDIRITEAXE);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITESPADE = ITEMS.register("unobtainobsidiritespade", () -> ModItems.UNOBTIANOBSIDIRITESPADE);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEHOE = ITEMS.register("unobtainobsidiritehoe", () -> ModItems.UNOBTIANOBSIDIRITEHOE);

    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEHELMET = ITEMS.register("unobtainobsidiritehelmit", () -> ModItems.UNOBTIANOBSIDIRITEHELMET);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITECHEST = ITEMS.register("unobtainobsidiritechest", () -> ModItems.UNOBTIANOBSIDIRITECHEST);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITELEGS = ITEMS.register("unobtainobsidiritelegs", () -> ModItems.UNOBTIANOBSIDIRITELEGS);
    public static final RegistryObject<Item> UNOBTIANOBSIDIRITEBOOTS = ITEMS.register("unobtainobsidiriteboots", () -> ModItems.UNOBTIANOBSIDIRITEBOOTS);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static void registerModItems(){
        SimpelAddModForge.LOGGER.info("Registering Mod Items for " + SimpelAddModForge.MODID);
    }
}