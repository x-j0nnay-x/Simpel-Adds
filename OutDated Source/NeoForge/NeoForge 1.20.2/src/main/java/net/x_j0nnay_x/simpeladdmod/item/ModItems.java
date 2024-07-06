package net.x_j0nnay_x.simpeladdmod.item;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.item.custom.grinderHead;
import net.x_j0nnay_x.simpeladdmod.item.util.*;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Simpeladd.MOD_ID);

    public static final DeferredItem<Item> SIMPEL_ELITRA_HALF = ITEMS.register("simpel_elitra_half", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SIMPEL_ELITRA_PART1 = ITEMS.register("simpel_elitra_part1", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SIMPEL_ELITRA_PART2 = ITEMS.register("simpel_elitra_part2", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GRINDERHEAD = ITEMS.register("grinderhead", () -> new grinderHead(256));
      //want it to be like a bucket but return damage

    public static final DeferredItem<Item> GOLDSTICK  = ITEMS.register("goldstick", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WOODFIBER  = ITEMS.register("woodfiber", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLESH  = ITEMS.register("flesh", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SPEEDUPGRADE_1  = ITEMS.register("speedupgrade_1", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPEEDUPGRADE_2  = ITEMS.register("speedupgrade_2", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPEEDUPGRADE_3  = ITEMS.register("speedupgrade_3", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BOOSTUPGRADE  = ITEMS.register("boostupgrade", () -> new Item(new Item.Properties()));

    //ancient shards stuff
    public static final DeferredItem<Item> NEHTERITE_SHARD  = ITEMS.register("netherite_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NEHTERITE_SHARD_DUST  = ITEMS.register("netherite_shard_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NEHTERITE_SHARD_RAW     = ITEMS.register("netherite_shard_raw", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SANDWICH_BEEF = ITEMS.register("sandwich_beef", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_BEEF)));
    public static final DeferredItem<Item> SANDWICH_MUT = ITEMS.register("sandwich_mut", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_MUT)));
    public static final DeferredItem<Item> SANDWICH_PORK = ITEMS.register("sandwich_pork", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_PORK)));
    public static final DeferredItem<Item> SANDWICH_CHECKIN = ITEMS.register("sandwich_checkin", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_CHECKIN)));
    public static final DeferredItem<Item> SANDWICH_VEG = ITEMS.register("sandwich_veg", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_VEG)));
    public static final DeferredItem<Item> SANDWICH_MEET_LOVE = ITEMS.register("sandwich_meet_love", () -> new Item(new Item.Properties().food(ModFoods.SANDWICH_MEET_LOVE)));
    public static final DeferredItem<Item> SANDWICH_MEET_LOVE_VEG = ITEMS.register("sandwich_meet_love_veg", () -> new Item(new Item.Properties().food(ModFoods.SANDWICH_MEET_LOVE_VEG   )));

//upgrades
    public static final DeferredItem<Item> BLANKUPGRADE  = ITEMS.register("blankupgrade", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLANKUPGRADE_RAW   = ITEMS.register("blankupgrade_raw", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OBSIDIANUPGRADE_SMITHING   = ITEMS.register("obsidian_upgrade_smithing", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> UNOBTANIUMUPGRADE_SMITHING   = ITEMS.register("unobtanium_upgrade_smithing", () -> new Item(new Item.Properties()));


    //dusts
    public static final DeferredItem<Item> COPPERDUST = ITEMS.register("copperdust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRONDUST = ITEMS.register("irondust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLDDUST = ITEMS.register("golddust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHERITEDUST = ITEMS.register("netheritedust", () -> new Item(new Item.Properties()));

//obsidian tear
    public static final DeferredItem<Item> OBSIDAININGOT  = ITEMS.register("obsidianingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OBSIDAINDUST  = ITEMS.register("obsidiandust", () -> new Item(new Item.Properties()));

    public static final DeferredItem<SwordItem> OBSIDIANSWORD = ITEMS.register("obsidiansword", () -> new SwordItem(ModToolTiers.OBSIDIANTIER, 3, -2.4f, new Item.Properties().fireResistant()));
    public static final DeferredItem<PickaxeItem> OBSIDIANPICKAXE = ITEMS.register("obsidianpickaxe", () -> new PickaxeItem(ModToolTiers.OBSIDIANTIER, 1, -2.8f, new Item.Properties().fireResistant()));
    public static final DeferredItem<AxeItem> OBSIDAINAXE = ITEMS.register("obsidianaxe", () -> new AxeItem(ModToolTiers.OBSIDIANTIER, 5, 3.0f, new Item.Properties().fireResistant()));
    public static final DeferredItem<ShovelItem> OBSIDIANSPADE = ITEMS.register("obsidianspade", () -> new ShovelItem(ModToolTiers.OBSIDIANTIER, 1.5f, -3.0f, new Item.Properties().fireResistant()));
    public static final DeferredItem<HoeItem> OBSIDIANHOE = ITEMS.register("obsidianhoe", () -> new HoeItem(ModToolTiers.OBSIDIANTIER, 0, -3.0f, new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> OBSIDIANHELMET = ITEMS.register("obsidianhelmit", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> OBSIDIANCHEST = ITEMS.register("obsidianchest", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> OBSIDIANLEGS = ITEMS.register("obsidianlegs", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> OBSIDIANBOOTS = ITEMS.register("obsidianboots", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));


    //obsidirite tear
    public static final DeferredItem<SwordItem> OBSIDIRITESWORD = ITEMS.register("obsidiritesword", () -> new SwordItem(ModToolTiers.OBSIDIRITETTIER, 3, -2.4f, new Item.Properties().fireResistant()));
    public static final DeferredItem<PickaxeItem> OBSIDIRITEPICKAXE = ITEMS.register("obsidiritepickaxe", () -> new PickaxeItem(ModToolTiers.OBSIDIRITETTIER, 1, -2.8f, new Item.Properties().fireResistant()));
    public static final DeferredItem<AxeItem> OBSIDIRITEAXE = ITEMS.register("obsidiriteaxe", () -> new AxeItem(ModToolTiers.OBSIDIRITETTIER, 5, 3.0f, new Item.Properties().fireResistant()));
    public static final DeferredItem<ShovelItem> OBSIDIRITESPADE = ITEMS.register("obsidiritespade", () -> new ShovelItem(ModToolTiers.OBSIDIRITETTIER, 1.5f, -3.0f, new Item.Properties().fireResistant()));
    public static final DeferredItem<HoeItem> OBSIDIRITEHOE = ITEMS.register("obsidiritehoe", () -> new HoeItem(ModToolTiers.OBSIDIRITETTIER, 0, -3.0f, new Item.Properties().fireResistant()));


    public static final DeferredItem<Item> OBSIDIRITEHELMET = ITEMS.register("obsidiritehelmit", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> OBSIDIRITECHEST = ITEMS.register("obsidiritechest", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> OBSIDIRITELEGS = ITEMS.register("obsidiritelegs", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> OBSIDIRITEBOOTS = ITEMS.register("obsidiriteboots", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.BOOTS, new Item.Properties()));

    //unobtain stuffs
    public static final DeferredItem<Item> UNOBTIANIUMDUST = ITEMS.register("unobtaniumdust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> UNOBTIANIUMSCRAP = ITEMS.register("unobtanium_scrap", () -> new Item(new Item.Properties()));

    //netherite boosted
    public static final DeferredItem<SwordItem> UNOBTIANNETHERITESWORD = ITEMS.register("unobtainnetheritesword", () -> new SwordItem(ModToolTiers.UNOBTAINNETHERITETIER, 3, -2.4f, new Item.Properties().fireResistant()));
    public static final DeferredItem<PickaxeItem> UNOBTIANNETHERITEPICKAXE = ITEMS.register("unobtainnetheritepickaxe", () -> new PickaxeItem(ModToolTiers.UNOBTAINNETHERITETIER, 1, -2.8f, new Item.Properties().fireResistant()));
    public static final DeferredItem<AxeItem> UNOBTIANNETHERITEAXE = ITEMS.register("unobtainnetheriteaxe", () -> new AxeItem(ModToolTiers.UNOBTAINNETHERITETIER, 5, 3.0f, new Item.Properties().fireResistant()));
    public static final DeferredItem<ShovelItem> UNOBTIANNETHERITESPADE = ITEMS.register("unobtainnetheritespade", () -> new ShovelItem(ModToolTiers.UNOBTAINNETHERITETIER, 1.5f, -3.0f, new Item.Properties().fireResistant()));
    public static final DeferredItem<HoeItem> UNOBTIANNETHERITEHOE = ITEMS.register("unobtainnetheritehoe", () -> new HoeItem(ModToolTiers.UNOBTAINNETHERITETIER, 0, -3.0f, new Item.Properties().fireResistant()));


    public static final DeferredItem<Item> UNOBTIANNETHERITEHELMET = ITEMS.register("unobtainnetheritehelmit", () -> new ArmorItem(ModArmmorTier.UNOBTAINNETHERITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> UNOBTIANNETHERITECHEST = ITEMS.register("unobtainnetheritechest", () -> new ArmorItem(ModArmmorTier.UNOBTAINNETHERITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> UNOBTIANNETHERITELEGS = ITEMS.register("unobtainnetheritelegs", () -> new ArmorItem(ModArmmorTier.UNOBTAINNETHERITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> UNOBTIANNETHERITEBOOTS = ITEMS.register("unobtainnetheriteboots", () -> new ArmorItem(ModArmmorTier.UNOBTAINNETHERITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
    //obsidirite boosted
    public static final DeferredItem<SwordItem> UNOBTIANOBSIDIRITESWORD = ITEMS.register("unobtainobsidiritesword", () -> new SwordItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 3, -2.4f, new Item.Properties().fireResistant()));
    public static final DeferredItem<PickaxeItem> UNOBTIANOBSIDIRITEPICKAXE = ITEMS.register("unobtainobsidiritepickaxe", () -> new PickaxeItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 1, -2.8f, new Item.Properties().fireResistant()));
    public static final DeferredItem<AxeItem> UNOBTIANOBSIDIRITEAXE = ITEMS.register("unobtainobsidiriteaxe", () -> new AxeItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 5, 3.0f, new Item.Properties().fireResistant()));
    public static final DeferredItem<ShovelItem> UNOBTIANOBSIDIRITESPADE = ITEMS.register("unobtainobsidiritespade", () -> new ShovelItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 1.5f, -3.0f, new Item.Properties().fireResistant()));
    public static final DeferredItem<HoeItem> UNOBTIANOBSIDIRITEHOE = ITEMS.register("unobtainobsidiritehoe", () -> new HoeItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 0, -3.0f, new Item.Properties().fireResistant()));


    public static final DeferredItem<Item> UNOBTIANOBSIDIRITEHELMET = ITEMS.register("unobtainobsidiritehelmit", () -> new ArmorItem(ModArmmorTier.UNOBTIANOBSIDIRITEA, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> UNOBTIANOBSIDIRITECHEST = ITEMS.register("unobtainobsidiritechest", () -> new ArmorItem(ModArmmorTier.UNOBTIANOBSIDIRITEA, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> UNOBTIANOBSIDIRITELEGS = ITEMS.register("unobtainobsidiritelegs", () -> new ArmorItem(ModArmmorTier.UNOBTIANOBSIDIRITEA, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> UNOBTIANOBSIDIRITEBOOTS = ITEMS.register("unobtainobsidiriteboots", () -> new ArmorItem(ModArmmorTier.UNOBTIANOBSIDIRITEA, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));


    public static void registerModItems(){
        Simpeladd.LOGGER.info("Registering Mod Items for " + Simpeladd.MOD_ID);
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}