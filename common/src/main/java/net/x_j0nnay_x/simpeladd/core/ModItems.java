package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.x_j0nnay_x.simpeladd.item.*;
import net.x_j0nnay_x.simpeladd.item.util.ModArmmorTier;
import net.x_j0nnay_x.simpeladd.item.util.ModFoods;
import net.x_j0nnay_x.simpeladd.item.util.ModToolTiers;

public class ModItems {
//wing items
    public static final Item SIMPEL_ELITRA_HALF = new SimpelItem(new Item.Properties());
    public static final Item SIMPEL_ELITRA_PART1 = new SimpelItem(new Item.Properties());
    public static final Item SIMPEL_ELITRA_PART2 = new SimpelItem(new Item.Properties());
//grind head
    public static final Item GRINDERHEAD = new GrinderHeadItem(256);
    public static final Item GRINDERHEADNEHTERITE = new GrinderHeadItem(676);
    public static final Item GRINDERHEADUNOBTIANIUM = new GrinderHeadItem(1596);
//random
    public static final Item WOODFIBER  = new SimpelItem(new Item.Properties());
    public static final Item FLESH  = new SimpelItem(new Item.Properties().food(ModFoods.FLESH));
    public static final Item NEHTERITE_SHARD  = new SimpelItem(new Item.Properties().fireResistant());
    public static final Item NEHTERITE_SHARD_RAW = new SimpelItem(new Item.Properties().fireResistant());
    public static final Item OBSIDAININGOT  = new SimpelItem(new Item.Properties().fireResistant());
    public static final Item UNOBTIANIUMSCRAP = new SimpelItem(new Item.Properties().fireResistant());
    public static final Item REPAIRTOOL = new SimpelRepairTool(1000);
    public static final Item FIREPROOFTOOL = new SimpelFireProofTool(600);
    public static final Item FEEDINGTOOL = new SimpelFeedingTool(450);
    public static final Item GROWSTAFF = new GrowStaff(380);
    public static final Item FULECHUNKS = new FuelChunks(new Item.Properties());
//upgrades
    public static final Item SPEEDUPGRADE_1  = new SimpelItem(new Item.Properties());
    public static final Item SPEEDUPGRADE_2  = new SimpelItem(new Item.Properties());
    public static final Item SPEEDUPGRADE_3  = new SimpelItem(new Item.Properties());
    public static final Item SPEEDUPGRADE_4  = new SimpelItem(new Item.Properties());
    public static final Item BOOSTUPGRADE  = new SimpelItem(new Item.Properties());
    public static final Item XPBOOSTUPGRADE = new SimpelItem(new Item.Properties());
//sandwiches
    public static final Item SANDWICH_BEEF = new SimpelItem(new Item.Properties().food(ModFoods.SANDWISH_BEEF));
    public static final Item SANDWICH_MUT = new SimpelItem(new Item.Properties().food(ModFoods.SANDWISH_MUT));
    public static final Item SANDWICH_PORK = new SimpelItem(new Item.Properties().food(ModFoods.SANDWISH_PORK));
    public static final Item SANDWICH_CHECKIN = new SimpelItem(new Item.Properties().food(ModFoods.SANDWISH_CHECKIN));
    public static final Item SANDWICH_VEG = new SimpelItem(new Item.Properties().food(ModFoods.SANDWISH_VEG));
    public static final Item SANDWICH_MEET_LOVE = new SimpelItem(new Item.Properties().food(ModFoods.SANDWICH_MEET_LOVE));
    public static final Item SANDWICH_MEET_LOVE_VEG = new SimpelItem(new Item.Properties().food(ModFoods.SANDWICH_MEET_LOVE_VEG));
//templates
    public static final Item BLANKUPGRADE = new SimpelItem(new Item.Properties());
    public static final Item BLANKUPGRADE_RAW = new SimpelItem(new Item.Properties());
    public static final Item OBSIDIANUPGRADE_SMITHING = new SimpelItem(new Item.Properties());
    public static final Item UNOBTANIUMUPGRADE_SMITHING = new SimpelItem(new Item.Properties());
//dusts
    public static final Item COPPERDUST = new SimpelItem(new Item.Properties());
    public static final Item IRONDUST = new SimpelItem(new Item.Properties());
    public static final Item GOLDDUST = new SimpelItem(new Item.Properties());
    public static final Item NETHERITEDUST = new SimpelItem(new Item.Properties());
    public static final Item NEHTERITE_SHARD_DUST = new SimpelItem(new Item.Properties());
    public static final Item OBSIDAINDUST  = new SimpelItem(new Item.Properties());
    public static final Item UNOBTIANIUMDUST = new SimpelItem(new Item.Properties());
//obsidian tear
    public static final Item OBSIDIANSWORD = new SimpelSwordItem(ModToolTiers.OBSIDIANTIER, 3, -2.4f);
    public static final Item OBSIDIANPICKAXE = new SimpelPickaxeItem(ModToolTiers.OBSIDIANTIER, 1, -2.8f);
    public static final Item OBSIDAINAXE = new SimpelAxeItem(ModToolTiers.OBSIDIANTIER, 5, 3.0f);
    public static final Item OBSIDIANSPADE = new SimpelShovelItem(ModToolTiers.OBSIDIANTIER, 1.5f, -3.0f);
    public static final Item OBSIDIANHOE = new SimpelHoeItem(ModToolTiers.OBSIDIANTIER, 0, -3.0f);
    public static final Item OBSIDIANHELMET = new SimpelArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.HELMET);
    public static final Item OBSIDIANCHEST = new SimpelArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.CHESTPLATE);
    public static final Item OBSIDIANLEGS = new SimpelArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.LEGGINGS);
    public static final Item OBSIDIANBOOTS = new SimpelArmorItem(ModArmmorTier.OBSIDIANA, ArmorItem.Type.BOOTS);
//obsidirite tear
    public static final Item OBSIDIRITESWORD = new SimpelSwordItem(ModToolTiers.OBSIDIRITETTIER, 3, -2.4f);
    public static final Item OBSIDIRITEPICKAXE = new SimpelPickaxeItem(ModToolTiers.OBSIDIRITETTIER, 1, -2.8f);
    public static final Item OBSIDIRITEAXE = new SimpelAxeItem(ModToolTiers.OBSIDIRITETTIER, 5, 3.0f);
    public static final Item OBSIDIRITESPADE = new SimpelShovelItem(ModToolTiers.OBSIDIRITETTIER, 1.5f, -3.0f);
    public static final Item OBSIDIRITEHOE = new SimpelHoeItem(ModToolTiers.OBSIDIRITETTIER, 0, -3.0f);
    public static final Item OBSIDIRITEHELMET = new SimpelArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.HELMET);
    public static final Item OBSIDIRITECHEST = new SimpelArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.CHESTPLATE);
    public static final Item OBSIDIRITELEGS = new SimpelArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.LEGGINGS);
    public static final Item OBSIDIRITEBOOTS = new SimpelArmorItem(ModArmmorTier.OBSIDIRITEA, ArmorItem.Type.BOOTS);
//netherite boosted
    public static final Item UNOBTIANNETHERITESWORD = new SimpelSwordItem(ModToolTiers.UNOBTAINNETHERITETIER, 3, -2.4f);
    public static final Item UNOBTIANNETHERITEPICKAXE = new SimpelPickaxeItem(ModToolTiers.UNOBTAINNETHERITETIER, 1, -2.8f);
    public static final Item UNOBTIANNETHERITEAXE = new SimpelAxeItem(ModToolTiers.UNOBTAINNETHERITETIER, 5, 3.0f);
    public static final Item UNOBTIANNETHERITESPADE = new SimpelShovelItem(ModToolTiers.UNOBTAINNETHERITETIER, 1.5f, -3.0f);
    public static final Item UNOBTIANNETHERITEHOE = new SimpelHoeItem(ModToolTiers.UNOBTAINNETHERITETIER, 0, -3.0f);
    public static final Item UNOBTIANNETHERITEHELMET = new SimpelArmorItem(ModArmmorTier.UNOBTAINNETHERITE, ArmorItem.Type.HELMET);
    public static final Item UNOBTIANNETHERITECHEST = new SimpelArmorItem(ModArmmorTier.UNOBTAINNETHERITE, ArmorItem.Type.CHESTPLATE);
    public static final Item UNOBTIANNETHERITELEGS = new SimpelArmorItem(ModArmmorTier.UNOBTAINNETHERITE, ArmorItem.Type.LEGGINGS);
    public static final Item UNOBTIANNETHERITEBOOTS = new SimpelArmorItem(ModArmmorTier.UNOBTAINNETHERITE, ArmorItem.Type.BOOTS);
//obsidirite boosted
    public static final Item UNOBTIANOBSIDIRITESWORD = new SimpelSwordItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 3, -2.4f);
    public static final Item UNOBTIANOBSIDIRITEPICKAXE = new SimpelPickaxeItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 1, -2.8f);
    public static final Item UNOBTIANOBSIDIRITEAXE = new SimpelAxeItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 5, 3.0f);
    public static final Item UNOBTIANOBSIDIRITESPADE = new SimpelShovelItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 1.5f, -3.0f);
    public static final Item UNOBTIANOBSIDIRITEHOE = new SimpelHoeItem(ModToolTiers.UNOBTIANOBSIDIRITEATIER, 0, -3.0f);
    public static final Item UNOBTIANOBSIDIRITEHELMET = new SimpelArmorItem(ModArmmorTier.UNOBTIANOBSIDIRITEA, ArmorItem.Type.HELMET);
    public static final Item UNOBTIANOBSIDIRITECHEST = new SimpelArmorItem(ModArmmorTier.UNOBTIANOBSIDIRITEA, ArmorItem.Type.CHESTPLATE);
    public static final Item UNOBTIANOBSIDIRITELEGS = new SimpelArmorItem(ModArmmorTier.UNOBTIANOBSIDIRITEA, ArmorItem.Type.LEGGINGS);
    public static final Item UNOBTIANOBSIDIRITEBOOTS = new SimpelArmorItem(ModArmmorTier.UNOBTIANOBSIDIRITEA, ArmorItem.Type.BOOTS);
}
