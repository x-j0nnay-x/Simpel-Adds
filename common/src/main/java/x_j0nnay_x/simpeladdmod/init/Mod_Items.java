package x_j0nnay_x.simpeladdmod.init;

import net.minecraft.world.item.Item;
import x_j0nnay_x.simpeladdmod.Constants;
import x_j0nnay_x.simpeladdmod.items.Grinder_Head_Item;
import x_j0nnay_x.simpeladdmod.items.Grinder_Head_Item_Broken;
import x_j0nnay_x.simpeladdmod.services.Services;
import x_j0nnay_x.simpeladdmod.services.util.RegistryHandle;

public final class Mod_Items {
    public static void load(){}
    //Ints
    public static final int FLINT_GRINDER_USES = 256;
    public static final int NETHERITE_GRINDER_USES = 676;
    public static final int UNOBTIANIUM_GRINDER_USES = 1596;
    public static final int GROWSTAFF_USES = 380;
//items that have no custom info
    //dusts
    public static final RegistryHandle<Item> COPPER_DUST = Services.REGISTRY.registerItem(Constants.getDustName(Constants.Types.COPPER), Item::new);
    public static final RegistryHandle<Item> DIAMOND_SHARD_DUST = Services.REGISTRY.registerItem(Constants.getDustName(Constants.Types.DIAMOND_SHARD), Item::new);
    public static final RegistryHandle<Item> EMERALD_SHARD_DUST = Services.REGISTRY.registerItem(Constants.getDustName(Constants.Types.EMERALD_SHARD), Item::new);
    public static final RegistryHandle<Item> GOLD_DUST = Services.REGISTRY.registerItem(Constants.getDustName(Constants.Types.GOLD), Item::new);
    public static final RegistryHandle<Item> IRON_DUST = Services.REGISTRY.registerItem(Constants.getDustName(Constants.Types.IRON), Item::new);
    public static final RegistryHandle<Item> NETHERITE_DUST = Services.REGISTRY.registerItem(Constants.getDustName(Constants.Types.NEHTERITE), Item::new);
    public static final RegistryHandle<Item> NEHTERITE_SHARD_DUST = Services.REGISTRY.registerItem(Constants.getDustName(Constants.Types.NEHTERITE_SHARD), Item::new);
    public static final RegistryHandle<Item> OBSIDAIN_DUST  = Services.REGISTRY.registerItem(Constants.getDustName(Constants.Types.OBSIDIAN), Item::new);
    public static final RegistryHandle<Item> UNOBTIANIUM_DUST = Services.REGISTRY.registerItem(Constants.getDustName(Constants.Types.UNOBTANIUM), Item::new);
    //materials
    public static final RegistryHandle<Item> OBSIDAIN_INGOT = Services.REGISTRY.registerItem(Constants.Items.OBSIDAIN_INGOT, Item::new);
    public static final RegistryHandle<Item> NEHTERITE_SHARD = Services.REGISTRY.registerItem(Constants.Types.NEHTERITE_SHARD, Item::new);
    public static final RegistryHandle<Item> DIAMOND_SHARD = Services.REGISTRY.registerItem(Constants.Types.DIAMOND_SHARD, Item::new);
    public static final RegistryHandle<Item> EMERALD_SHARD = Services.REGISTRY.registerItem(Constants.Types.EMERALD_SHARD, Item::new);
    public static final RegistryHandle<Item> NEHTERITE_SHARD_RAW = Services.REGISTRY.registerItem(Constants.getRawName(Constants.Types.NEHTERITE_SHARD), Item::new);
    public static final RegistryHandle<Item> DIAMOND_SHARD_RAW = Services.REGISTRY.registerItem(Constants.getRawName(Constants.Types.DIAMOND_SHARD), Item::new);
    public static final RegistryHandle<Item> EMERALD_SHARD_RAW = Services.REGISTRY.registerItem(Constants.getRawName(Constants.Types.EMERALD_SHARD), Item::new);
    public static final RegistryHandle<Item> UNOBTIANIUM_SCRAP = Services.REGISTRY.registerItem(Constants.Items.UNOBTIANIUM_SCRAP, Item::new);
    //random
    public static final RegistryHandle<Item> WOOD_FIBER = Services.REGISTRY.registerItem(Constants.Items.WOOD_FIBER, Item::new);
    public static final RegistryHandle<Item> FLESH = Services.REGISTRY.registerItem(Constants.Items.FLESH, Item::new);
    public static final RegistryHandle<Item> SIMPEL_ELITRA_HALF = Services.REGISTRY.registerItem(Constants.Items.SIMPEL_ELITRA_HALF, Item::new);
    public static final RegistryHandle<Item> SIMPEL_ELITRA_PART1 = Services.REGISTRY.registerItem(Constants.Items.SIMPEL_ELITRA_PART1, Item::new);
    public static final RegistryHandle<Item> SIMPEL_ELITRA_PART2 = Services.REGISTRY.registerItem(Constants.Items.SIMPEL_ELITRA_PART2, Item::new);
    //sandwiches
    public static final RegistryHandle<Item> SANDWICH_BEEF = Services.REGISTRY.registerItem(Constants.Items.SANDWICH_BEEF, properties -> new Item(properties.food(Mod_Foods.SANDWICH_BEEF)));
    public static final RegistryHandle<Item> SANDWICH_MUT = Services.REGISTRY.registerItem(Constants.Items.SANDWICH_MUT, properties -> new Item(properties.food(Mod_Foods.SANDWICH_MUT)));
    public static final RegistryHandle<Item> SANDWICH_PORK = Services.REGISTRY.registerItem(Constants.Items.SANDWICH_PORK, properties -> new Item(properties.food(Mod_Foods.SANDWICH_PORK)));
    public static final RegistryHandle<Item> SANDWICH_CHECKIN = Services.REGISTRY.registerItem(Constants.Items.SANDWICH_CHICKEN, properties -> new Item(properties.food(Mod_Foods.SANDWICH_CHICKEN)));
    public static final RegistryHandle<Item> SANDWICH_VEG = Services.REGISTRY.registerItem(Constants.Items.SANDWICH_VEG, properties -> new Item(properties.food(Mod_Foods.SANDWICH_VEG)));
    public static final RegistryHandle<Item> SANDWICH_MEET_LOVE = Services.REGISTRY.registerItem(Constants.Items.SANDWICH_MEET_LOVE, properties -> new Item(properties.food(Mod_Foods.SANDWICH_MEET_LOVE)));
    public static final RegistryHandle<Item> SANDWICH_MEET_LOVE_VEG = Services.REGISTRY.registerItem(Constants.Items.SANDWICH_MEET_LOVE_VEG, properties -> new Item(properties.food(Mod_Foods.SANDWICH_MEET_LOVE_VEG)));
    //grind head
    public static final RegistryHandle<Item> GRINDER_HEAD = Services.REGISTRY.registerItem(Constants.Items.GRINDER_HEAD, properties -> new Grinder_Head_Item(properties.stacksTo(1).durability(FLINT_GRINDER_USES)));
    public static final RegistryHandle<Item> GRINDER_HEAD_NEHTERITE =  Services.REGISTRY.registerItem(Constants.Items.GRINDER_HEAD_NETHERITE, properties -> new Grinder_Head_Item(properties.stacksTo(1).durability(NETHERITE_GRINDER_USES)));
    public static final RegistryHandle<Item> GRINDER_HEAD_UNOBTIANIUM =  Services.REGISTRY.registerItem(Constants.Items.GRINDER_HEAD_UNOBTIANIUM, properties -> new Grinder_Head_Item(properties.stacksTo(1).durability(UNOBTIANIUM_GRINDER_USES)));
    public static final RegistryHandle<Item> GRINDER_HEAD_BROKEN =  Services.REGISTRY.registerItem(Constants.Items.GRINDER_HEAD_BROKEN, properties -> new Grinder_Head_Item_Broken(properties.stacksTo(1)));

}
