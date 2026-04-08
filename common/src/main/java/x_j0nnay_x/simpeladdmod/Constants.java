package x_j0nnay_x.simpeladdmod;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    public static final String CREATIVETAB = "simpel_tab";
    public static final String MOD_ID = "simpeladdmod";
    public static final String MOD_NAME = "SimpelAdd";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static Identifier id(String name){
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }

    public static class OreStoneType {
        public static final String NETHER = "_netherrack";
        public static final String END = "_endstone";
        public static final String DEEP = "_deepslate";
    }

    public static class Types{
        public static final String DEBRI = "debri";
        public static final String UNOBTANIUM = "unobtanium";
        public static final String REDSTONE = "redstone";
        public static final String DIAMOND = "diamond";
        public static final String EMERALD = "emerald";
        public static final String LAPIS = "lapis";
        public static final String COAL = "coal";
        public static final String IRON = "iron";
        public static final String GOLD = "gold";
        public static final String COPPER = "copper";
        public static final String NEHTERITE = "netherite";
        public static final String DIAMOND_SHARD = "diamond_shard";
        public static final String EMERALD_SHARD = "emerald_shard";
        public static final String NEHTERITE_SHARD = "netherite_shard";
        public static final String OBSIDIAN = "obsidian";
        public static final String OBSIDIRITE = "obsidirite";
        public static final String UNOBTIAN_NETHERITE = "unobtain_netherite";
        public static final String UNOBTIAN_OBSIDIRITE = "unobtain_obsidirite";
    }

    public static class Tools{
        public static final String SWORD = "sword";
        public static final String PICKAXE = "pickaxe";
        public static final String AXE = "axe";
        public static final String SHOVEL = "spade";
        public static final String HOE = "hoe";
        public static final String HELMET = "helmet";
        public static final String CHESTPLATE = "chest";
        public static final String LEGGINGS = "legs";
        public static final String BOOTS = "boots";
    }

    public static class Blocks {
        public static final String BLOCK_FACTORY = "block_factory";
        public static final String CHILLER = "chiller";
        public static final String GRINDER = "grinder";
        public static final String GRINDER_UP = "grinder_up";
        public static final String GRIND_FACTORY = "grind_factory";
        public static final String NETHERITE_CRAFTER = "netherite_crafter";
        public static final String UPGRADED_FURNACE = "upgraded_furnace";
        public static final String SIMPEL_FARM_LAND = "simpel_farmland";
        public static final String FUEL_CHUNKS = "fuel_chunks";
        public static final String TICK_ACCELERATOR = "tick_accelerator";
        public static final String TOOL_REPAIR = "tool_repair";
        public static final String HARVESTER = "harvester";
        public static final String CROP_GROWTH = "crop_growth";
        public static final String SIMPEL_SPAWNER = "simpel_spawner";
    }

    public static class Items {
        //wings
        public static final String SIMPEL_ELITRA_HALF = "simpel_elitra_half";
        public static final String SIMPEL_ELITRA_PART1 = "simpel_elitra_part1";
        public static final String SIMPEL_ELITRA_PART2 = "simpel_elitra_part2";
        //grind head
        public static final String GRINDER_HEAD = "grinder_head";
        public static final String GRINDER_HEAD_NETHERITE = "grinder_head_netherite";
        public static final String GRINDER_HEAD_UNOBTIANIUM = "grinder_head_unobtanium";
        public static final String GRINDER_HEAD_BROKEN = "grinder_head_broken";
        //random
        public static final String WOOD_FIBER = "wood_fiber";
        public static final String FLESH = "flesh";
        //materials
        public static final String OBSIDAIN_INGOT = "obsidian_ingot";
        public static final String UNOBTIANIUM_SCRAP = "unobtanium_scrap";
        //other
        public static final String GROW_STAFF = "grow_staff";
        public static final String XP_CRYSTAL = "xp_crystal";
        //upgrades
        public static final String SPEED_UPGRADE_1 = "speed_upgrade_1";
        public static final String SPEED_UPGRADE_2 = "speed_upgrade_2";
        public static final String SPEED_UPGRADE_3 = "speed_upgrade_3";
        public static final String SPEED_UPGRADE_4 = "speed_upgrade_4";
        public static final String BOOST_UPGRADE = "boost_upgrade";
        public static final String XP_BOOST_UPGRADE = "xp_boost_upgrade";
        public static final String REMOTE_UPGRADE = "remote_upgrade";
        //sandwiches
        public static final String SANDWICH_BEEF = "sandwich_beef";
        public static final String SANDWICH_MUT = "sandwich_mut";
        public static final String SANDWICH_PORK = "sandwich_pork";
        public static final String SANDWICH_CHICKEN = "sandwich_chicken";
        public static final String SANDWICH_VEG = "sandwich_veg";
        public static final String SANDWICH_MEET_LOVE = "sandwich_meet_love";
        public static final String SANDWICH_MEET_LOVE_VEG = "sandwich_meet_love_veg";
        //Templates
        public static final String BLANK_UPGRADE = "blank_upgrade";
        public static final String BLANK_UPGRADE_RAW = "blank_upgrade_raw";
        public static final String OBSIDIAN_UPGRADE_SMITHING = "obsidian_upgrade_smithing";
        public static final String UNOBTANI_UMUPGRADE_SMITHING = "unobtanium_upgrade_smithing";
    }

    public static String getMenuName(String blockName){return blockName + "_menu";}
    public static String getDustName(String materialType){ return materialType + "_dust";}
    public static String getRawName(String type){ return type + "_raw";}
    public static String getToolNames(String tier, String tool) {
        return tier + "_" + tool;
    }
    public static String getRawOreBlockName(String oreType){ return "raw_" + oreType + "_shard_block";}
    public static String getBlockNameForEntity(String blockName) {
        return blockName + "_block";
    }
    public static String getBlockNameForOre(String type, String stoneType) {return "ore_" + type + stoneType;}

    public static class Recipe{
        public static final String GRINDER = "grinder";
        public static final String MANUALGRIND = "manual_grind";
        public static final String SimpelCraftRepair = "simpel_crafting_repair";
        public static final String CropGrowth = "crop_growth";
        public static final String BlankUpgradeSmithing = "blank_upgrade_recipe";
    }

    public static class DataComponentTypes {
        public static final String HOMEWAND_COMPNENTS = "homewand_data";
        public static final String XP_CRYSTAL_LEVEL = "xp_crystal_level";
        public static final String XP_CRYSTAL_PROGRESS = "xp_crystal_progress";
    }
}