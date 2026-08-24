package x_j0nnay_x.simpeladdmod.init;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Mod_Config {
    //item use config
    public static ModConfigSpec.IntValue FLINT_GRINDER_USES;
    public static ModConfigSpec.IntValue NETHERITE_GRINDER_USES;
    public static ModConfigSpec.IntValue UNOBTIANIUM_GRINDER_USES;
    public static ModConfigSpec.IntValue GROW_STAFF_USES;
    public static ModConfigSpec.IntValue HOME_WAND_USES;
    public static ModConfigSpec.IntValue REPAIR_GEM_USES;
    public static ModConfigSpec.IntValue FLIGHT_GEM_USES;
    public static ModConfigSpec.IntValue FIRE_GEM_USES;
    public static ModConfigSpec.IntValue TOTEM_GEM_USES;
    public static ModConfigSpec.IntValue FEEDING_GEM_USES;
    //ore toggle config
    public static ModConfigSpec.BooleanValue NETHER_ORE_ENABLE;
    public static ModConfigSpec.BooleanValue END_ORE_ENABLE;
    public static ModConfigSpec.BooleanValue NETHER_COAL;
    public static ModConfigSpec.BooleanValue END_COAL;
    public static ModConfigSpec.BooleanValue NETHER_COPPER;
    public static ModConfigSpec.BooleanValue END_COPPER;
    public static ModConfigSpec.BooleanValue NETHER_IRON;
    public static ModConfigSpec.BooleanValue END_IRON;
    public static ModConfigSpec.BooleanValue NETHER_GOLD;
    public static ModConfigSpec.BooleanValue END_GOLD;
    public static ModConfigSpec.BooleanValue NETHER_DIAMOND;
    public static ModConfigSpec.BooleanValue END_DIAMOND;
    public static ModConfigSpec.BooleanValue NETHER_EMERALD;
    public static ModConfigSpec.BooleanValue END_EMERALD;
    public static ModConfigSpec.BooleanValue NETHER_LAPIS;
    public static ModConfigSpec.BooleanValue END_LAPIS;
    public static ModConfigSpec.BooleanValue NETHER_REDSTONE;
    public static ModConfigSpec.BooleanValue END_REDSTONE;
    public static ModConfigSpec.BooleanValue DEEPSLATE_DEBRI;
    public static ModConfigSpec.BooleanValue NEHTER_DEBRI;
    public static ModConfigSpec.BooleanValue END_DEBRI;
    //block config
    public static ModConfigSpec.BooleanValue GRINDER_BRAKES_GRINDER_ITEM;
    public static ModConfigSpec.BooleanValue GRINDER_BRAKES_MANUAL_GRIND;
    public static ModConfigSpec.BooleanValue GROW_STAFF_BRAKES_HARVESTER;
    public static ModConfigSpec.BooleanValue GROW_STAFF_BRAKES;
    public static ModConfigSpec.BooleanValue HOME_WAND_BRAKES;
    public static ModConfigSpec.BooleanValue REPAIR_GEM_BRAKES;
    public static ModConfigSpec.BooleanValue FLIGHT_GEM_BRAKES;
    public static ModConfigSpec.BooleanValue FIRE_GEM_BRAKES;
    public static ModConfigSpec.BooleanValue TOTEM_GEM_BRAKES;
    public static ModConfigSpec.BooleanValue FEEDING_GEM_BRAKES;

    public static ModConfigSpec SERVER_CONFIG;

    public static void register(ModContainer container) {
        registerConfigs(container);
    }
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();



    private static void registerConfigs(ModContainer container) {
        generalConfig();
        oreConfig();
        brakeConfigs();
        SERVER_CONFIG = BUILDER.build();
        container.registerConfig(ModConfig.Type.STARTUP, BUILDER.build());
    }


    public static void generalConfig() {
        BUILDER.comment("here is the item use config").push("item_uses");
        FLINT_GRINDER_USES = BUILDER
                .comment("the number of uses the flint grinder has")
                .defineInRange("flint_grinder_uses", 256, 1, Integer.MAX_VALUE);
        NETHERITE_GRINDER_USES = BUILDER
                .comment("the number of uses the netherite grinder has")
                .defineInRange("netherite_grinder_uses", 676, 1, Integer.MAX_VALUE);
        UNOBTIANIUM_GRINDER_USES = BUILDER
                .comment("the number of uses the unobtianium grinder has")
                .defineInRange("unobtianium_grinder_uses", 1596, 1, Integer.MAX_VALUE);
        GROW_STAFF_USES = BUILDER
                .comment("the number of uses the Grow Staff has")
                .defineInRange("grow_staff_uses", 380, 1, Integer.MAX_VALUE);
        HOME_WAND_USES = BUILDER
                .comment("the number of uses the Home Wand has")
                .defineInRange("home_wand_uses", 256, 1, Integer.MAX_VALUE);
        REPAIR_GEM_USES = BUILDER
                .comment("the number of uses the Repair Gem has")
                .defineInRange("repair_gem_uses", 1000, 1, Integer.MAX_VALUE);
        FLIGHT_GEM_USES = BUILDER
                .comment("the number of uses the Flight Gem has")
                .defineInRange("flight_gem_uses", 800, 1, Integer.MAX_VALUE);
        FIRE_GEM_USES = BUILDER
                .comment("the number of uses the Fire Gem has")
                .defineInRange("fire_gem_uses", 1200, 1, Integer.MAX_VALUE);
        TOTEM_GEM_USES = BUILDER
                .comment("the number of uses the Totem Gem has")
                .defineInRange("totem_gem_uses", 4, 1, Integer.MAX_VALUE);
        FEEDING_GEM_USES = BUILDER
                .comment("the number of uses the Feeding Gem has")
                .defineInRange("feeding_gem_uses", 800, 1, Integer.MAX_VALUE);
        BUILDER.pop();

    }

    public static void oreConfig() {
        BUILDER.comment("here is the Ore Toggle Config").push("ore_toggle");
        BUILDER.comment("This is for turning off all ore Register added at once");
        NETHER_ORE_ENABLE = BUILDER
                .comment("Enable or disable the Register of ores in the nether all")
                .define("golobal_nether_spawn", true);
        END_ORE_ENABLE = BUILDER
                .comment("Enable or disable the Register of ores in the end all")
                .define("golobal_end_spawn", true);
        BUILDER.comment("This is for turning off ores Register one by one");
        NETHER_COAL = BUILDER
                .comment("enable or disable the Register of coal ore in the nether")
                .define("nether_coal_spawn", true);
        END_COAL    = BUILDER
                .comment("enable or disable the Register of coal ore in the end")
                .define("end_coal_spawn", true);
        NETHER_COPPER = BUILDER
                .comment("enable or disable the Register of copper ore in the nether")
                .define("nether_copper_spawn", true);
        END_COPPER    = BUILDER
                .comment("enable or disable the Register of copper ore in the end")
                .define("end_copper_spawn", true);
        NETHER_IRON = BUILDER
                .comment("enable or disable the Register of iron ore in the nether")
                .define("nether_iron_spawn", true);
        END_IRON    = BUILDER
                .comment("enable or disable the Register of iron ore in the end")
                .define("end_iron_spawn", true);
        NETHER_GOLD = BUILDER
                .comment("enable or disable the Register of gold ore in the nether")
                .define("nether_gold_spawn", true);
        END_GOLD    = BUILDER
                .comment("enable or disable the Register of gold ore in the end")
                .define("end_gold_spawn", true);
        NETHER_DIAMOND = BUILDER
                .comment("enable or disable the Register of diamond ore in the nether")
                .define("nether_diamond_spawn", true);
        END_DIAMOND    = BUILDER
                .comment("enable or disable the Register of diamond ore in the end")
                .define("end_diamond_spawn", true);
        NETHER_EMERALD = BUILDER
                .comment("enable or disable the Register of emerald ore in the nether")
                .define("nether_emerald_spawn", true);
        END_EMERALD    = BUILDER
                .comment("enable or disable the Register of emerald ore in the end")
                .define("end_emerald_spawn", true);
        NETHER_LAPIS = BUILDER
                .comment("enable or disable the Register of lapis ore in the nether")
                .define("nether_lapis_spawn", true);
        END_LAPIS    = BUILDER
                .comment("enable or disable the Register of lapis ore in the end")
                .define("end_lapis_spawn", true);
        NETHER_REDSTONE = BUILDER
                .comment("enable or disable the Register of redstone ore in the nether")
                .define("nether_redstone_spawn", true);
        END_REDSTONE    = BUILDER
                .comment("enable or disable the Register of redstone ore in the end")
                .define("end_redstone_spawn", true);
        DEEPSLATE_DEBRI = BUILDER
                .comment("enable or disable the Register of debri ore in deepslate")
                .define("deepslate_debri_spawn", true);
        NEHTER_DEBRI = BUILDER
                .comment("enable or disable the Register of debri ore in the nether")
                .define("nether_debri_spawn", true);
        END_DEBRI    = BUILDER
                .comment("enable or disable the Register of debri ore in the end")
                .define("end_debri_spawn", true);
        BUILDER.pop();

    }

    public static void brakeConfigs() {
        BUILDER.comment("here is the item braking config. some items will not brake when damage hits zero. this is done as the item is expansive or its to stop crafting the time.").push("block_config");
        GRINDER_BRAKES_GRINDER_ITEM = BUILDER
                .comment("enable or disable the grinder head breaking inside the grinder blocks")
                .define("grinder_breaks_grinder_item", false);
        GRINDER_BRAKES_MANUAL_GRIND = BUILDER
                .comment("enable or disable the grinder head breaking during the manual grinder")
                .define("grinder_breaks_manual_grinder_item", false);
        GROW_STAFF_BRAKES_HARVESTER = BUILDER
                .comment("enable or disable the grow staff breaking during the harvester")
                .define("grow_staff_breaks_harvester", false);
        GROW_STAFF_BRAKES = BUILDER
                .comment("enable or disable the grow staff breaking during the use of the grow staff")
                .define("grow_staff_breaks", false);
        HOME_WAND_BRAKES = BUILDER
                .comment("enable or disable the home wand breaking during the use of the home wand")
                .define("home_wand_breaks", false);
        REPAIR_GEM_BRAKES = BUILDER
                .comment("enable or disable the repair gem breaking during the use of the repair gem")
                .define("repair_gem_breaks", false);
        FLIGHT_GEM_BRAKES = BUILDER
                .comment("enable or disable the flight gem breaking during the use of the flight gem")
                .define("flight_gem_breaks", false);
        FIRE_GEM_BRAKES = BUILDER
                .comment("enable or disable the fire gem breaking during the use of the fire gem")
                .define("fire_gem_breaks", false);
        TOTEM_GEM_BRAKES = BUILDER
                .comment("enable or disable the totem gem breaking during the use of the totem gem")
                .define("totem_gem_breaks", false);
        FEEDING_GEM_BRAKES = BUILDER
                .comment("enable or disable the feeding gem breaking during the use of the feeding gem")
                .define("feeding_gem_breaks", false);
        BUILDER.pop();
    }

}
