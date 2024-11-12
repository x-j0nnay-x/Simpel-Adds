package net.x_j0nnay_x.simpeladd.core;

public class ModNames {
    public static class Blocks {
        public static final String BLOCKFACTORY = "blockfactory";
        public static final String CHILLER = "chiller";
        public static final String GRINDER = "grinder";
        public static final String GRINDER_UP = "grinder_up";
        public static final String GRINDFACTORY = "grind_factory";
        public static final String NETHERITE_CRAFTER = "netherite_crafter";
        public static final String UPGRADED_FURNACE = "upgraded_furnace";
        public static final String DEEPSLATE_DEBRI_ORE = "deepslate_debri_ore";
        public static final String UNOBTANIUM_ORE = "unobtanium_ore";
        public static final String SIMPEL_FARM_LAND = "simpel_farmland";

        public static String getBlockNameForEntity(String name) {
            switch (name) {
                case BLOCKFACTORY:
                    return BLOCKFACTORY + "_block";
                case CHILLER:
                    return CHILLER + "_block";
                case GRINDER:
                    return GRINDER + "_block";
                case GRINDER_UP:
                    return GRINDER_UP + "_block";
                case GRINDFACTORY:
                    return GRINDFACTORY + "_block";
                case NETHERITE_CRAFTER:
                    return NETHERITE_CRAFTER + "_block";
                case UPGRADED_FURNACE:
                    return UPGRADED_FURNACE + "_block";
            }
            return name;
        }
    }
    public static class Items {
        //wings
        public static final String SIMPEL_ELITRA_HALF = "simpel_elitra_half";
        public static final String SIMPEL_ELITRA_PART1 = "simpel_elitra_part1";
        public static final String SIMPEL_ELITRA_PART2 = "simpel_elitra_part2";
        //grind head
        public static final String GRINDERHEAD = "grinderhead";
        public static final String GRINDERHEADNETHERITE = "grinderhead_netherite";
        public static final String GRINDERHEADUNOBTIANIUM = "grinderhead_unobtanium";
        //random
        public static final String WOODFIBER = "woodfiber";
        public static final String FLESH = "flesh";
        public static final String OBSIDAININGOT = "obsidianingot";
        public static final String NEHTERITE_SHARD = "netherite_shard";
        public static final String NEHTERITE_SHARD_RAW = "netherite_shard_raw";
        public static final String UNOBTIANIUMSCRAP = "unobtanium_scrap";
        public static final String REPAIRTOOL = "repairtool";
        public static final String FIREPROOFTOOL = "fireprooftool";
        public static final String FEEDINGTOOL = "feedingtool";
        public static final String GROWSTAFF = "growstaff";
        //upgrades
        public static final String SPEEDUPGRADE_1 = "speedupgrade_1";
        public static final String SPEEDUPGRADE_2 = "speedupgrade_2";
        public static final String SPEEDUPGRADE_3 = "speedupgrade_3";
        public static final String SPEEDUPGRADE_4 = "speedupgrade_4";
        public static final String BOOSTUPGRADE = "boostupgrade";
        public static final String XPBOOSTUPGRADE = "xpboostupgrade";
        //sandwiches
        public static final String SANDWICH_BEEF = "sandwich_beef";
        public static final String SANDWICH_MUT = "sandwich_mut";
        public static final String SANDWICH_PORK = "sandwich_pork";
        public static final String SANDWICH_CHECKIN = "sandwich_checkin";
        public static final String SANDWICH_VEG = "sandwich_veg";
        public static final String SANDWICH_MEET_LOVE = "sandwich_meet_love";
        public static final String SANDWICH_MEET_LOVE_VEG = "sandwich_meet_love_veg";
        //Templates
        public static final String BLANKUPGRADE = "blankupgrade";
        public static final String BLANKUPGRADE_RAW = "blankupgrade_raw";
        public static final String OBSIDIANUPGRADE_SMITHING = "obsidian_upgrade_smithing";
        public static final String UNOBTANIUMUPGRADE_SMITHING = "unobtanium_upgrade_smithing";
        //dusts
        public static final String COPPERDUST = "copperdust";
        public static final String IRONDUST = "irondust";
        public static final String GOLDDUST = "golddust";
        public static final String NETHERITEDUST = "netheritedust";
        public static final String NEHTERITE_SHARD_DUST = "netherite_shard_dust";
        public static final String OBSIDAINDUST = "obsidiandust";
        public static final String UNOBTIANIUMDUST = "unobtaniumdust";
    }

    public static class Tools{
        //tiers
        public static final String OBSIDIAN = "obsidian";
        public static final String OBSIDIRITE = "obsidirite";
        public static final String UNOBTIANNETHERITE = "unobtainnetherite";
        public static final String UNOBTIANOBSIDIRITE = "unobtainobsidirite";
        //tools
        public static final String SWORD = "sword";
        public static final String PICKAXE = "pickaxe";
        public static final String AXE = "axe";
        public static final String SHOVEL = "spade";
        public static final String HOE = "hoe";
        public static final String HELMET = "helmit";
        public static final String CHESTPLATE = "chest";
        public static final String LEGGINGS = "legs";
        public static final String BOOTS = "boots";


        public static String getToolNames(String name, String type) {
            switch (name) {
                case OBSIDIAN:
                    switch (type){
                        case SWORD:
                            return OBSIDIAN + SWORD;
                        case PICKAXE:
                            return OBSIDIAN + PICKAXE;
                        case AXE:
                            return OBSIDIAN + AXE;
                        case SHOVEL:
                            return OBSIDIAN + SHOVEL;
                        case HOE:
                            return OBSIDIAN + HOE;
                        case HELMET:
                            return OBSIDIAN + HELMET;
                        case CHESTPLATE:
                            return OBSIDIAN + CHESTPLATE;
                        case LEGGINGS:
                            return OBSIDIAN + LEGGINGS;
                        case BOOTS:
                            return OBSIDIAN + BOOTS;
                    }
                case OBSIDIRITE:
                    switch (type){
                        case SWORD:
                            return OBSIDIRITE + SWORD;
                        case PICKAXE:
                            return OBSIDIRITE + PICKAXE;
                        case AXE:
                            return OBSIDIRITE + AXE;
                        case SHOVEL:
                            return OBSIDIRITE + SHOVEL;
                        case HOE:
                            return OBSIDIRITE + HOE;
                        case HELMET:
                            return OBSIDIRITE + HELMET;
                        case CHESTPLATE:
                            return OBSIDIRITE + CHESTPLATE;
                        case LEGGINGS:
                            return OBSIDIRITE + LEGGINGS;
                        case BOOTS:
                            return OBSIDIRITE + BOOTS;
                    }
                case UNOBTIANNETHERITE:
                    switch (type){
                        case SWORD:
                            return UNOBTIANNETHERITE + SWORD;
                        case PICKAXE:
                            return UNOBTIANNETHERITE + PICKAXE;
                        case AXE:
                            return UNOBTIANNETHERITE + AXE;
                        case SHOVEL:
                            return UNOBTIANNETHERITE + SHOVEL;
                        case HOE:
                            return UNOBTIANNETHERITE + HOE;
                        case HELMET:
                            return UNOBTIANNETHERITE + HELMET;
                        case CHESTPLATE:
                            return UNOBTIANNETHERITE + CHESTPLATE;
                        case LEGGINGS:
                            return UNOBTIANNETHERITE + LEGGINGS;
                        case BOOTS:
                            return UNOBTIANNETHERITE + BOOTS;
                    }
                case UNOBTIANOBSIDIRITE:
                    switch (type){
                        case SWORD:
                            return UNOBTIANOBSIDIRITE + SWORD;
                        case PICKAXE:
                            return UNOBTIANOBSIDIRITE + PICKAXE;
                        case AXE:
                            return UNOBTIANOBSIDIRITE + AXE;
                        case SHOVEL:
                            return UNOBTIANOBSIDIRITE + SHOVEL;
                        case HOE:
                            return UNOBTIANOBSIDIRITE + HOE;
                        case HELMET:
                            return UNOBTIANOBSIDIRITE + HELMET;
                        case CHESTPLATE:
                            return UNOBTIANOBSIDIRITE + CHESTPLATE;
                        case LEGGINGS:
                            return UNOBTIANOBSIDIRITE + LEGGINGS;
                        case BOOTS:
                            return UNOBTIANOBSIDIRITE + BOOTS;
                    }

            }
            return name;
        }
    }

    public static class Menu {
        public static final String BLOCKFACTORYMENU = "blockfactorymenu";
        public static final String GRINDERMENU = "grindermenu";
        public static final String GRINDERMENU_UP = "grindermenu_up";
        public static final String GRIND_FACTORY_MENU = "grind_factory_menu";
        public static final String UPGRADED_FURNACE_MENU = "furnacemenu_up";
        public static final String CHILLER_MENU = "chillermenu";
        public static final String NETHERITE_MENU = "netheritemenu";
    }

    public static class Recipe{
        public static final String GRINDER = "grinder";
        public static final String MANUALGRIND = "manualgrind";
        public static final String SimpelCraftRepair = "simpel_crafting_repair";

    }


}
