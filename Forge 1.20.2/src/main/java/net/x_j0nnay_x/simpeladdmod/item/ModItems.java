package net.x_j0nnay_x.simpeladdmod.item;

import net.minecraft.world.item.*;
import net.x_j0nnay_x.simpeladdmod.item.custom.grinderHead;

import net.x_j0nnay_x.simpeladdmod.item.util.*;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, simpeladdmod.MOD_ID);

    public static final RegistryObject<Item> SIMPEL_ELITRA_HALF = ITEMS.register("simpel_elitra_half", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SIMPEL_ELITRA_PART1 = ITEMS.register("simpel_elitra_part1", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SIMPEL_ELITRA_PART2 = ITEMS.register("simpel_elitra_part2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRINDERHEAD = ITEMS.register("grinderhead", () -> new grinderHead(256));
      //want it to be like a bucket but return damage

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

    public static final RegistryObject<Item> OBSIDIANSWORD = ITEMS.register("obsidiansword", () -> new SwordItem(ModToolTiers.OBSIDIANT, 4, 3, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIANPICKAXE = ITEMS.register("obsidianpickaxe", () -> new PickaxeItem(ModToolTiers.OBSIDIANT, 2, 2, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDAINAXE = ITEMS.register("obsidianaxe", () -> new AxeItem(ModToolTiers.OBSIDIANT, 6, 3, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIANSPADE = ITEMS.register("obsidianspade", () -> new ShovelItem(ModToolTiers.OBSIDIANT, 3, 3, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIANHOE = ITEMS.register("obsidianhoe", () -> new HoeItem(ModToolTiers.OBSIDIANT, -2, 0, new Item.Properties()));

    public static final RegistryObject<Item> OBSIDIANHELMET = ITEMS.register("obsidianhelmit", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIANCHEST = ITEMS.register("obsidianchest", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIANLEGS = ITEMS.register("obsidianlegs", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIANBOOTS = ITEMS.register("obsidianboots", () -> new ArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.BOOTS, new Item.Properties()));


//obsidirite tear
    public static final RegistryObject<Item> OBSIDIRITESWORD = ITEMS.register("obsidiritesword", () -> new SwordItem(ModToolTiers.OBSIDIRITET, 5, 1, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIRITEPICKAXE = ITEMS.register("obsidiritepickaxe", () -> new PickaxeItem(ModToolTiers.OBSIDIRITET, 2, 2, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIRITEAXE = ITEMS.register("obsidiriteaxe", () -> new AxeItem(ModToolTiers.OBSIDIRITET, 6, 3, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIRITESPADE = ITEMS.register("obsidiritespade", () -> new ShovelItem(ModToolTiers.OBSIDIRITET, 3, 3, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIRITEHOE = ITEMS.register("obsidiritehoe", () -> new HoeItem(ModToolTiers.OBSIDIRITET, -3, 0, new Item.Properties()));

    public static final RegistryObject<Item> OBSIDIRITEHELMET = ITEMS.register("obsidiritehelmit", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIRITECHEST = ITEMS.register("obsidiritechest", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIRITELEGS = ITEMS.register("obsidiritelegs", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIRITEBOOTS = ITEMS.register("obsidiriteboots", () -> new ArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.BOOTS, new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}