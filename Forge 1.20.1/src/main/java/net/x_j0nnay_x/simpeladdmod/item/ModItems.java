package net.x_j0nnay_x.simpeladdmod.item;

import net.minecraft.world.item.*;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.item.custom.grinderHead;
import net.x_j0nnay_x.simpeladdmod.item.util.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Simpeladd.MOD_ID);

    public static final RegistryObject<Item> SIMPEL_ELITRA_HALF = ITEMS.register("simpel_elitra_half", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SIMPEL_ELITRA_PART1 = ITEMS.register("simpel_elitra_part1", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SIMPEL_ELITRA_PART2 = ITEMS.register("simpel_elitra_part2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRINDERHEAD = ITEMS.register("grinderhead", () -> new grinderHead(256));
    public static final RegistryObject<Item> GOLDSTICK  = ITEMS.register("goldstick", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WOODFIBER  = ITEMS.register("woodfiber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLESH  = ITEMS.register("flesh", () -> new Item(new Item.Properties()));

    //ancient shards stuff
    public static final RegistryObject<Item> NEHTERITE_SHARD  = ITEMS.register("netherite_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NEHTERITE_SHARD_DUST  = ITEMS.register("netherite_shard_dust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NEHTERITE_SHARD_RAW     = ITEMS.register("netherite_shard_raw", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SANDWICH_BEEF = ITEMS.register("sandwich_beef", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_BEEF)));
    public static final RegistryObject<Item> SANDWICH_MUT = ITEMS.register("sandwich_mut", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_MUT)));
    public static final RegistryObject<Item> SANDWICH_PORK = ITEMS.register("sandwich_pork", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_PORK)));
    public static final RegistryObject<Item> SANDWICH_CHECKIN = ITEMS.register("sandwich_checkin", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_CHECKIN)));
    public static final RegistryObject<Item> SANDWICH_VEG = ITEMS.register("sandwich_veg", () -> new Item(new Item.Properties().food(ModFoods.SANDWISH_VEG)));
    public static final RegistryObject<Item> SANDWICH_MEET_LOVE = ITEMS.register("sandwich_meet_love", () -> new Item(new Item.Properties().food(ModFoods.SANDWICH_MEET_LOVE)));
    public static final RegistryObject<Item> SANDWICH_MEET_LOVE_VEG = ITEMS.register("sandwich_meet_love_veg", () -> new Item(new Item.Properties().food(ModFoods.SANDWICH_MEET_LOVE_VEG   )));

//upgrades
    public static final RegistryObject<Item> BLANKUPGRADE  = ITEMS.register("blankupgrade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLANKUPGRADE_RAW   = ITEMS.register("blankupgrade_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIANUPGRADE_SMITHING   = ITEMS.register("obsidian_upgrade_smithing", () -> new Item(new Item.Properties()));

//dusts
    public static final RegistryObject<Item> COPPERDUST = ITEMS.register("copperdust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRONDUST = ITEMS.register("irondust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLDDUST = ITEMS.register("golddust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITEDUST = ITEMS.register("netheritedust", () -> new Item(new Item.Properties()));

//obsidian tear
    public static final RegistryObject<Item> OBSIDAININGOT  = ITEMS.register("obsidianingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OBSIDAINDUST  = ITEMS.register("obsidiandust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<SwordItem> OBSIDIANSWORD = ITEMS.register("obsidiansword", () -> new SwordItem(ModToolTiers.OBSIDIANTIER, 3, -2.4f, new Item.Properties().fireResistant()));
    public static final RegistryObject<PickaxeItem> OBSIDIANPICKAXE = ITEMS.register("obsidianpickaxe", () -> new PickaxeItem(ModToolTiers.OBSIDIANTIER, 1, -2.8f, new Item.Properties().fireResistant()));
    public static final RegistryObject<AxeItem> OBSIDAINAXE = ITEMS.register("obsidianaxe", () -> new AxeItem(ModToolTiers.OBSIDIANTIER, 5, 3.0f, new Item.Properties().fireResistant()));
    public static final RegistryObject<ShovelItem> OBSIDIANSPADE = ITEMS.register("obsidianspade", () -> new ShovelItem(ModToolTiers.OBSIDIANTIER, 1.5f, -3.0f, new Item.Properties().fireResistant()));
    public static final RegistryObject<HoeItem> OBSIDIANHOE = ITEMS.register("obsidianhoe", () -> new HoeItem(ModToolTiers.OBSIDIANTIER, 0, -3.0f, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> OBSIDIANHELMET = ITEMS.register("obsidianhelmit", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> OBSIDIANCHEST = ITEMS.register("obsidianchest", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> OBSIDIANLEGS = ITEMS.register("obsidianlegs", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> OBSIDIANBOOTS = ITEMS.register("obsidianboots", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));


//obsidirite tear
    public static final RegistryObject<SwordItem> OBSIDIRITESWORD = ITEMS.register("obsidiritesword", () -> new SwordItem(ModToolTiers.OBSIDIRITETTIER, 3, -2.4f, new Item.Properties().fireResistant()));
    public static final RegistryObject<PickaxeItem> OBSIDIRITEPICKAXE = ITEMS.register("obsidiritepickaxe", () -> new PickaxeItem(ModToolTiers.OBSIDIRITETTIER, 1, -2.8f, new Item.Properties().fireResistant()));
    public static final RegistryObject<AxeItem> OBSIDIRITEAXE = ITEMS.register("obsidiriteaxe", () -> new AxeItem(ModToolTiers.OBSIDIRITETTIER, 5, 3.0f, new Item.Properties().fireResistant()));
    public static final RegistryObject<ShovelItem> OBSIDIRITESPADE = ITEMS.register("obsidiritespade", () -> new ShovelItem(ModToolTiers.OBSIDIRITETTIER, 1.5f, -3.0f, new Item.Properties().fireResistant()));
    public static final RegistryObject<HoeItem> OBSIDIRITEHOE = ITEMS.register("obsidiritehoe", () -> new HoeItem(ModToolTiers.OBSIDIRITETTIER, 0, -3.0f, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> OBSIDIRITEHELMET = ITEMS.register("obsidiritehelmit", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> OBSIDIRITECHEST = ITEMS.register("obsidiritechest", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> OBSIDIRITELEGS = ITEMS.register("obsidiritelegs", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> OBSIDIRITEBOOTS = ITEMS.register("obsidiriteboots", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static void registerModItems(){
        Simpeladd.LOGGER.info("Registering Mod Items for " + Simpeladd.MOD_ID);
    }
}