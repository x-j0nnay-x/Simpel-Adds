package net.x_j0nnay_x.simpeladdmod.item;



import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.item.custom.grinderHead;
import net.x_j0nnay_x.simpeladdmod.item.util.*;




public class ModItems {


    public static final Item SIMPEL_ELITRA_HALF = registerItem("simpel_elitra_half", new Item(new Item.Settings()));
    public static final Item SIMPEL_ELITRA_PART1 = registerItem("simpel_elitra_part1", new Item(new Item.Settings()));
    public static final Item SIMPEL_ELITRA_PART2 = registerItem("simpel_elitra_part2", new Item(new Item.Settings()));

    public static final Item GRINDERHEAD = registerItem("grinderhead", new grinderHead(256));
    public static final Item GOLDSTICK  = registerItem("goldstick", new Item(new Item.Settings()));
    public static final Item WOODFIBER  = registerItem("woodfiber", new Item(new Item.Settings()));
    public static final Item FLESH  = registerItem("flesh", new Item(new Item.Settings()));

    public static final Item SPEEDUPGRADE_1  = registerItem("speedupgrade_1", new Item(new Item.Settings()));
    public static final Item SPEEDUPGRADE_2  = registerItem("speedupgrade_2",  new Item(new Item.Settings()));
    public static final Item SPEEDUPGRADE_3  = registerItem("speedupgrade_3",  new Item(new Item.Settings()));
    public static final Item BOOSTUPGRADE  = registerItem("boostupgrade",  new Item(new Item.Settings()));

    //ancient shards stuff
    public static final Item NEHTERITE_SHARD  = registerItem("netherite_shard", new Item(new Item.Settings()));
    public static final Item NEHTERITE_SHARD_DUST  = registerItem("netherite_shard_dust", new Item(new Item.Settings()));
    public static final Item NEHTERITE_SHARD_RAW     = registerItem("netherite_shard_raw", new Item(new Item.Settings()));

    public static final Item SANDWICH_BEEF = registerItem("sandwich_beef", new Item(new Item.Settings().food(ModFoods.SANDWISH_BEEF)));
    public static final Item SANDWICH_MUT = registerItem("sandwich_mut", new Item(new Item.Settings().food(ModFoods.SANDWISH_MUT)));
    public static final Item SANDWICH_PORK = registerItem("sandwich_pork", new Item(new Item.Settings().food(ModFoods.SANDWISH_PORK)));
    public static final Item SANDWICH_CHECKIN = registerItem("sandwich_checkin", new Item(new Item.Settings().food(ModFoods.SANDWISH_CHECKIN)));
    public static final Item SANDWICH_VEG = registerItem("sandwich_veg", new Item(new Item.Settings().food(ModFoods.SANDWISH_VEG)));
    public static final Item SANDWICH_MEET_LOVE = registerItem("sandwich_meet_love", new Item(new Item.Settings().food(ModFoods.SANDWICH_MEET_LOVE)));
    public static final Item SANDWICH_MEET_LOVE_VEG = registerItem("sandwich_meet_love_veg", new Item(new Item.Settings().food(ModFoods.SANDWICH_MEET_LOVE_VEG   )));

//upgrades
    public static final Item BLANKUPGRADE  = registerItem("blankupgrade", new Item(new Item.Settings()));
    public static final Item BLANKUPGRADE_RAW   = registerItem("blankupgrade_raw", new Item(new Item.Settings()));
    public static final Item OBSIDIANUPGRADE_SMITHING   = registerItem("obsidian_upgrade_smithing", new Item(new Item.Settings()));
    public static final Item UNOBTANIUMUPGRADE_SMITHING   = registerItem("unobtanium_upgrade_smithing",  new Item(new Item.Settings()));


    //dusts
    public static final Item COPPERDUST = registerItem("copperdust", new Item(new Item.Settings()));
    public static final Item IRONDUST = registerItem("irondust", new Item(new Item.Settings()));
    public static final Item GOLDDUST = registerItem("golddust", new Item(new Item.Settings()));
    public static final Item NETHERITEDUST = registerItem("netheritedust", new Item(new Item.Settings()));

//obsidian tear
    public static final Item OBSIDAININGOT  = registerItem("obsidianingot", new Item(new Item.Settings()));
    public static final Item OBSIDAINDUST  = registerItem("obsidiandust", new Item(new Item.Settings()));

   public static final Item OBSIDIANSWORD = registerItem("obsidiansword", new SwordItem(ModToolTiers.OBSIDIANTIER,  new Item.Settings().fireproof()));
   public static final Item OBSIDIANPICKAXE = registerItem("obsidianpickaxe", new PickaxeItem(ModToolTiers.OBSIDIANTIER,  new Item.Settings().fireproof()));
   public static final Item OBSIDAINAXE = registerItem("obsidianaxe", new AxeItem(ModToolTiers.OBSIDIANTIER,  new Item.Settings().fireproof()));
   public static final Item OBSIDIANSPADE = registerItem("obsidianspade", new ShovelItem(ModToolTiers.OBSIDIANTIER,  new Item.Settings().fireproof()));
   public static final Item OBSIDIANHOE = registerItem("obsidianhoe", new HoeItem(ModToolTiers.OBSIDIANTIER,  new Item.Settings().fireproof()));

   public static final Item OBSIDIANHELMET = registerItem("obsidianhelmit", new ArmorItem(ModArmorMaterials.OBSIDIANA, ArmorItem.Type.HELMET, new Item.Settings().fireproof()));
   public static final Item OBSIDIANCHEST = registerItem("obsidianchest", new ArmorItem(ModArmorMaterials.OBSIDIANA, ArmorItem.Type.CHESTPLATE, new Item.Settings().fireproof()));
    public static final Item OBSIDIANLEGS = registerItem("obsidianlegs", new ArmorItem(ModArmorMaterials.OBSIDIANA, ArmorItem.Type.LEGGINGS, new Item.Settings().fireproof()));
    public static final Item OBSIDIANBOOTS = registerItem("obsidianboots", new ArmorItem(ModArmorMaterials.OBSIDIANA, ArmorItem.Type.BOOTS, new Item.Settings().fireproof()));


//obsidirite tear
    public static final Item OBSIDIRITESWORD = registerItem("obsidiritesword", new SwordItem(ModToolTiers.OBSIDIRITETTIER,  new Item.Settings().fireproof()));
    public static final Item OBSIDIRITEPICKAXE = registerItem("obsidiritepickaxe", new PickaxeItem(ModToolTiers.OBSIDIRITETTIER,  new Item.Settings().fireproof()));
    public static final Item OBSIDIRITEAXE = registerItem("obsidiriteaxe", new AxeItem(ModToolTiers.OBSIDIRITETTIER,  new Item.Settings().fireproof()));
    public static final Item OBSIDIRITESPADE = registerItem("obsidiritespade", new ShovelItem(ModToolTiers.OBSIDIRITETTIER,  new Item.Settings().fireproof()));
    public static final Item OBSIDIRITEHOE = registerItem("obsidiritehoe", new HoeItem(ModToolTiers.OBSIDIRITETTIER,  new Item.Settings().fireproof()));

    public static final Item OBSIDIRITEHELMET = registerItem("obsidiritehelmit", new ArmorItem(ModArmorMaterials.OBSIDIRITEA, ArmorItem.Type.HELMET, new Item.Settings().fireproof()));
    public static final Item OBSIDIRITECHEST = registerItem("obsidiritechest", new ArmorItem(ModArmorMaterials.OBSIDIRITEA, ArmorItem.Type.CHESTPLATE, new Item.Settings().fireproof()));
    public static final Item OBSIDIRITELEGS = registerItem("obsidiritelegs", new ArmorItem(ModArmorMaterials.OBSIDIRITEA, ArmorItem.Type.LEGGINGS, new Item.Settings().fireproof()));
    public static final Item OBSIDIRITEBOOTS = registerItem("obsidiriteboots", new ArmorItem(ModArmorMaterials.OBSIDIRITEA, ArmorItem.Type.BOOTS, new Item.Settings().fireproof()));
    //unobtain stuffs
    public static final Item UNOBTIANIUMDUST = registerItem("unobtaniumdust",  new Item(new Item.Settings()));
    public static final Item UNOBTIANIUMSCRAP = registerItem("unobtanium_scrap",  new Item(new Item.Settings()));

    //netherite boosted
    public static final Item UNOBTIANNETHERITESWORD = registerItem("unobtainnetheritesword",  new SwordItem(ModToolTiers.UNOBTAINNETHERITETIER,  new Item.Settings().fireproof()));
    public static final Item UNOBTIANNETHERITEPICKAXE = registerItem("unobtainnetheritepickaxe",  new PickaxeItem(ModToolTiers.UNOBTAINNETHERITETIER,  new Item.Settings().fireproof()));
    public static final Item UNOBTIANNETHERITEAXE = registerItem("unobtainnetheriteaxe",  new AxeItem(ModToolTiers.UNOBTAINNETHERITETIER,  new Item.Settings().fireproof()));
    public static final Item UNOBTIANNETHERITESPADE = registerItem("unobtainnetheritespade",  new ShovelItem(ModToolTiers.UNOBTAINNETHERITETIER,  new Item.Settings().fireproof()));
    public static final Item UNOBTIANNETHERITEHOE = registerItem("unobtainnetheritehoe",  new HoeItem(ModToolTiers.UNOBTAINNETHERITETIER,  new Item.Settings().fireproof()));


    public static final Item UNOBTIANNETHERITEHELMET = registerItem("unobtainnetheritehelmit",  new ArmorItem(ModArmorMaterials.UNOBTAINNETHERITE, ArmorItem.Type.HELMET, new Item.Settings().fireproof()));
    public static final Item UNOBTIANNETHERITECHEST = registerItem("unobtainnetheritechest",  new ArmorItem(ModArmorMaterials.UNOBTAINNETHERITE, ArmorItem.Type.CHESTPLATE, new Item.Settings().fireproof()));
    public static final Item UNOBTIANNETHERITELEGS = registerItem("unobtainnetheritelegs",  new ArmorItem(ModArmorMaterials.UNOBTAINNETHERITE, ArmorItem.Type.LEGGINGS, new Item.Settings().fireproof()));
    public static final Item UNOBTIANNETHERITEBOOTS = registerItem("unobtainnetheriteboots",  new ArmorItem(ModArmorMaterials.UNOBTAINNETHERITE, ArmorItem.Type.BOOTS, new Item.Settings().fireproof()));
    //obsidirite boosted
    public static final Item UNOBTIANOBSIDIRITESWORD = registerItem("unobtainobsidiritesword",  new SwordItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, new Item.Settings().fireproof()));
    public static final Item UNOBTIANOBSIDIRITEPICKAXE = registerItem("unobtainobsidiritepickaxe",  new PickaxeItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER,  new Item.Settings().fireproof()));
    public static final Item UNOBTIANOBSIDIRITEAXE = registerItem("unobtainobsidiriteaxe",  new AxeItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER,  new Item.Settings().fireproof()));
    public static final Item UNOBTIANOBSIDIRITESPADE = registerItem("unobtainobsidiritespade",  new ShovelItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER,  new Item.Settings().fireproof()));
    public static final Item UNOBTIANOBSIDIRITEHOE = registerItem("unobtainobsidiritehoe",  new HoeItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER,  new Item.Settings().fireproof()));


    public static final Item UNOBTIANOBSIDIRITEHELMET = registerItem("unobtainobsidiritehelmit",  new ArmorItem(ModArmorMaterials.UNOBTIANOBSIDIRITEA, ArmorItem.Type.HELMET, new Item.Settings().fireproof()));
    public static final Item UNOBTIANOBSIDIRITECHEST = registerItem("unobtainobsidiritechest",  new ArmorItem(ModArmorMaterials.UNOBTIANOBSIDIRITEA, ArmorItem.Type.CHESTPLATE, new Item.Settings().fireproof()));
    public static final Item UNOBTIANOBSIDIRITELEGS = registerItem("unobtainobsidiritelegs",  new ArmorItem(ModArmorMaterials.UNOBTIANOBSIDIRITEA, ArmorItem.Type.LEGGINGS, new Item.Settings().fireproof()));
    public static final Item UNOBTIANOBSIDIRITEBOOTS = registerItem("unobtainobsidiriteboots",  new ArmorItem(ModArmorMaterials.UNOBTIANOBSIDIRITEA, ArmorItem.Type.BOOTS, new Item.Settings().fireproof()));



    public static void registerModItems(){
        Simpeladd.LOGGER.info("Registrering Mod Items for " + Simpeladd.MOD_ID);
    }
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Simpeladd.MOD_ID, name), item);
    }

}