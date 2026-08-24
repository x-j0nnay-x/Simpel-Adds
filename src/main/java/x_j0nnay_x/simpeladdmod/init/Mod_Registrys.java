package x_j0nnay_x.simpeladdmod.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import x_j0nnay_x.simpeladdmod.block.*;
import x_j0nnay_x.simpeladdmod.block.entitys.*;
import x_j0nnay_x.simpeladdmod.items.*;
import x_j0nnay_x.simpeladdmod.menu.*;
import x_j0nnay_x.simpeladdmod.menu.Harvester_Menu;
import x_j0nnay_x.simpeladdmod.menu.Tool_Repair_Menu;
import x_j0nnay_x.simpeladdmod.recipe.*;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Mod_Registrys {


public class Mod_Block_Entity{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Mod_Constants.MOD_ID);

    public static final Supplier<BlockEntityType<Grinder_Block_Entity>> GRINDER_BLOCK_ENTITY = BLOCK_ENTITIES.register(Mod_Constants.getBlockNameForEntity(Mod_Constants.Blocks.GRINDER), () -> new BlockEntityType<>(Grinder_Block_Entity::new, Mod_Blocks.GRINDER_BLOCK.get()));
    public static final Supplier<BlockEntityType<Grinder_Netherite_Block_Entity>> GRINDER_NETHERITE_BLOCK_ENTITY = BLOCK_ENTITIES.register(Mod_Constants.getBlockNameForEntity(Mod_Constants.Blocks.GRINDER_NETHERITE), () -> new BlockEntityType<>(Grinder_Netherite_Block_Entity::new, Mod_Blocks.GRINDER_NETHERITE_BLOCK.get()));
    public static final Supplier<BlockEntityType<Netherite_Crafter_Block_Entity>> NETHERITE_CRAFTER_BLOCK_ENTITY = BLOCK_ENTITIES.register(Mod_Constants.getBlockNameForEntity(Mod_Constants.Blocks.NETHERITE_CRAFTER), () -> new BlockEntityType<>(Netherite_Crafter_Block_Entity::new, Mod_Blocks.NETHERITE_CRAFTER_BLOCK.get()));
    public static final Supplier<BlockEntityType<Crop_Growth_Block_Entity>> CROP_GROWTH_BLOCK_ENTITY = BLOCK_ENTITIES.register(Mod_Constants.getBlockNameForEntity(Mod_Constants.Blocks.CROP_GROWTH), () -> new BlockEntityType<>(Crop_Growth_Block_Entity::new, Mod_Blocks.CROP_GROWTH_BLOCK.get()));
    public static final Supplier<BlockEntityType<Harvester_Block_Entity>> HARVESTER_BLOCK_ENTITY = BLOCK_ENTITIES.register(Mod_Constants.getBlockNameForEntity(Mod_Constants.Blocks.HARVESTER), () -> new BlockEntityType<>(Harvester_Block_Entity::new, Mod_Blocks.HARVESTER_BLOCK.get()));
    public static final Supplier<BlockEntityType<Tick_Accelerator_Block_Entity>> TICK_ACCELERATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register(Mod_Constants.getBlockNameForEntity(Mod_Constants.Blocks.TICK_ACCELERATOR), () -> new BlockEntityType<>(Tick_Accelerator_Block_Entity::new, Mod_Blocks.TICK_ACCELERATOR_BLOCK.get()));
    public static final Supplier<BlockEntityType<Tool_Repair_Block_Entity>> TOOL_REPAIR_BLOCK_ENTTY = BLOCK_ENTITIES.register(Mod_Constants.getBlockNameForEntity(Mod_Constants.Blocks.TOOL_REPAIR), () -> new BlockEntityType<>(Tool_Repair_Block_Entity::new, Mod_Blocks.TOOL_REPAIR_BLOCK.get()));
    public static final Supplier<BlockEntityType<Simpel_Spawner_Block_Entity>> SIMPEL_SPAWNER_BLOCK_ENTTY = BLOCK_ENTITIES.register(Mod_Constants.getBlockNameForEntity(Mod_Constants.Blocks.SIMPEL_SPAWNER), () -> new BlockEntityType<>(Simpel_Spawner_Block_Entity::new, Mod_Blocks.SIMPEL_SPAWNER.get()));

}

public class Mod_Blocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Mod_Constants.MOD_ID);
    //deepslate ores
    public static final DeferredBlock<Block> DEEPSLATE_DEBRI_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.DEBRI, Mod_Constants.OreStoneType.DEEP), p -> new DropExperienceBlock(UniformInt.of(2, 6), p.strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()), Mod_Config.DEEPSLATE_DEBRI.getAsBoolean());
    //nether ores
    public static final DeferredBlock<Block> NETHERRACK_COAL_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.COAL, Mod_Constants.OreStoneType.NETHER), p -> new DropExperienceBlock(UniformInt.of(3, 7), p.strength(4.8F, 3.0F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()), Mod_Config.NEHTER_DEBRI.getAsBoolean());
    public static final DeferredBlock<Block> NETHERRACK_COPPER_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.COPPER, Mod_Constants.OreStoneType.NETHER), p -> new DropExperienceBlock(UniformInt.of(3, 7) , p.strength(4.8F, 3.0F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()), Mod_Config.NETHER_COPPER.getAsBoolean());
    public static final DeferredBlock<Block> NETHERRACK_DEBRI_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.DEBRI, Mod_Constants.OreStoneType.NETHER), p -> new DropExperienceBlock(UniformInt.of(3, 7), p.strength(4.8F, 3.0F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()), Mod_Config.NEHTER_DEBRI.getAsBoolean());
    public static final DeferredBlock<Block> NETHERRACK_DIAMOND_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.DIAMOND, Mod_Constants.OreStoneType.NETHER), p -> new DropExperienceBlock(UniformInt.of(3, 7), p.strength(4.8F, 3.0F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()), Mod_Config.NETHER_DIAMOND.getAsBoolean());
    public static final DeferredBlock<Block> NETHERRACK_EMERALD_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.EMERALD, Mod_Constants.OreStoneType.NETHER), p -> new DropExperienceBlock(UniformInt.of(3, 7), p.strength(4.8F, 3.0F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()), Mod_Config.NETHER_EMERALD.getAsBoolean());
    public static final DeferredBlock<Block> NETHERRACK_GOLD_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.GOLD, Mod_Constants.OreStoneType.NETHER), p -> new DropExperienceBlock(UniformInt.of(3, 7), p.strength(4.8F, 3.0F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()), Mod_Config.NETHER_GOLD.getAsBoolean());
    public static final DeferredBlock<Block> NETHERRACK_IRON_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.IRON, Mod_Constants.OreStoneType.NETHER), p -> new DropExperienceBlock(UniformInt.of(3, 7), p.strength(4.8F, 3.0F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()), Mod_Config.NETHER_IRON.getAsBoolean());
    public static final DeferredBlock<Block> NETHERRACK_LAPIS_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.LAPIS, Mod_Constants.OreStoneType.NETHER), p -> new DropExperienceBlock(UniformInt.of(3, 7), p.strength(4.8F, 3.0F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()), Mod_Config.NETHER_LAPIS.getAsBoolean());
    public static final DeferredBlock<Block> NETHERRACK_REDSTONE_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.REDSTONE, Mod_Constants.OreStoneType.NETHER), p -> new DropExperienceBlock(UniformInt.of(3, 7), p.strength(4.8F, 3.0F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()), Mod_Config.NETHER_REDSTONE.getAsBoolean());
    //end ores
    public static final DeferredBlock<Block> END_COAL_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.COAL, Mod_Constants.OreStoneType.END), p -> new DropExperienceBlock(UniformInt.of(4, 8), p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()), Mod_Config.END_COAL.getAsBoolean());
    public static final DeferredBlock<Block> END_COPPER_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.COPPER, Mod_Constants.OreStoneType.END), p -> new DropExperienceBlock(UniformInt.of(4, 8), p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()), Mod_Config.END_COPPER.getAsBoolean());
    public static final DeferredBlock<Block> END_DEBRI_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.DEBRI, Mod_Constants.OreStoneType.END), p -> new DropExperienceBlock(UniformInt.of(4, 8), p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()), Mod_Config.END_DEBRI.getAsBoolean());
    public static final DeferredBlock<Block> END_DIAMOND_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.DIAMOND, Mod_Constants.OreStoneType.END), p -> new DropExperienceBlock(UniformInt.of(4, 8), p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()), Mod_Config.END_DIAMOND.getAsBoolean());
    public static final DeferredBlock<Block> END_EMERALD_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.EMERALD, Mod_Constants.OreStoneType.END), p -> new DropExperienceBlock(UniformInt.of(4, 8), p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()), Mod_Config.END_EMERALD.getAsBoolean());
    public static final DeferredBlock<Block> END_GOLD_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.GOLD, Mod_Constants.OreStoneType.END), p -> new DropExperienceBlock(UniformInt.of(4, 8), p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()), Mod_Config.END_GOLD.getAsBoolean());
    public static final DeferredBlock<Block> END_IRON_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.IRON, Mod_Constants.OreStoneType.END), p -> new DropExperienceBlock(UniformInt.of(4, 8), p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()), Mod_Config.END_IRON.getAsBoolean());
    public static final DeferredBlock<Block> END_LAPIS_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.LAPIS, Mod_Constants.OreStoneType.END), p -> new DropExperienceBlock(UniformInt.of(4, 8), p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()), Mod_Config.END_LAPIS.getAsBoolean());
    public static final DeferredBlock<Block> END_REDSTONE_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.REDSTONE, Mod_Constants.OreStoneType.END), p -> new DropExperienceBlock(UniformInt.of(4, 8), p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()), Mod_Config.END_REDSTONE.getAsBoolean());
    public static final DeferredBlock<Block> UNOBTANIUM_ORE = bRegister(Mod_Constants.getBlockNameForOre(Mod_Constants.Types.UNOBTANIUM, Mod_Constants.OreStoneType.END), p -> new Block(p.strength(5.2F, 3.0F).requiresCorrectToolForDrops()));
    //random blocks
    public static final DeferredBlock<Block> SIMPEL_FARM_LAND = bRegister(Mod_Constants.Blocks.SIMPEL_FARM_LAND, Simpel_Farm_Land::new);
    public static final DeferredBlock<Block> CHUNK_TOURCH = bRegister(Mod_Constants.Blocks.FUEL_CHUNKS, Fuel_Chunks::new);
    //storage ore blocks
    public static final DeferredBlock<Block> RAW_DEBRI_SHARD_BLOCK = bRegister(Mod_Constants.getRawOreBlockName(Mod_Constants.Types.DEBRI), p -> new Block(p.strength(5.0F, 6.0F).sound(SoundType.IRON)), shouldRegisterRawShardBlock(Mod_Config.DEEPSLATE_DEBRI.getAsBoolean(), Mod_Config.NEHTER_DEBRI.getAsBoolean(), Mod_Config.END_DEBRI.getAsBoolean()));
    public static final DeferredBlock<Block> RAW_DIAMOND_SHARD_BLOCK = bRegister(Mod_Constants.getRawOreBlockName(Mod_Constants.Types.DIAMOND), p -> new Block(p.strength(5.0F, 6.0F).sound(SoundType.IRON)), shouldRegisterRawShardBlock(Mod_Config.NETHER_DIAMOND.getAsBoolean(), Mod_Config.END_DIAMOND.getAsBoolean()));
    public static final DeferredBlock<Block> RAW_EMERALD_SHARD_BLOCK = bRegister(Mod_Constants.getRawOreBlockName(Mod_Constants.Types.EMERALD), p -> new Block(p.strength(5.0F, 6.0F).sound(SoundType.IRON)), shouldRegisterRawShardBlock(Mod_Config.NETHER_EMERALD.getAsBoolean(), Mod_Config.END_EMERALD.getAsBoolean()));
    //machine blocks
    public static final DeferredBlock<Block> GRINDER_BLOCK = bRegister(Mod_Constants.Blocks.GRINDER, p -> new Grinder_Block(p.strength(2.0f, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> GRINDER_NETHERITE_BLOCK = bRegister(Mod_Constants.Blocks.GRINDER_NETHERITE, p -> new Grinder_Netherite_Block(p.strength(2.0f, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> NETHERITE_CRAFTER_BLOCK = bRegister(Mod_Constants.Blocks.NETHERITE_CRAFTER, p -> new Netherite_Crafter_Block(p.strength(2.0f, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> CROP_GROWTH_BLOCK = bRegister(Mod_Constants.Blocks.CROP_GROWTH, p -> new Crop_Growth_Block(p.strength(2.0f, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> HARVESTER_BLOCK = bRegister(Mod_Constants.Blocks.HARVESTER, p -> new Harvester_Block(p.strength(2.0f, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TICK_ACCELERATOR_BLOCK = bRegister(Mod_Constants.Blocks.TICK_ACCELERATOR, p -> new Tick_Accelerator_Block(p.strength(2.0f, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TOOL_REPAIR_BLOCK = bRegister(Mod_Constants.Blocks.TOOL_REPAIR, p -> new Tool_Repair_Block(p.strength(2.0f, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SIMPEL_SPAWNER = bRegister(Mod_Constants.Blocks.SIMPEL_SPAWNER, p -> new Simpel_Spwaner_Block(p.strength(2.0f, 6.0F).sound(SoundType.METAL)));


    private static <T extends Block> DeferredBlock<T> bRegister(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory) {
        return registerBlockWithItem(name, () -> blockFactory.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Mod_Constants.id(name)))));
    }

    private static <T extends Block> DeferredBlock<T> bRegister(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory, boolean should_register) {
        if (should_register) {
            return registerBlockWithItem(name, () -> blockFactory.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Mod_Constants.id(name)))));
        }
        return null;
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Supplier<? extends T> blockSupplier) {
        DeferredBlock<T> registeredBlock = BLOCKS.register(name, blockSupplier);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    private static <T extends Block> DeferredItem<BlockItem> registerBlockItem(String name, DeferredBlock<T> block) {
        return Mod_Items.ITEMS.registerItem(name,
                properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    private static boolean shouldRegisterRawShardBlock(boolean first, boolean second) {
        if (first || second) {
            return true;
        }
        return false;
    }

    private static boolean shouldRegisterRawShardBlock(boolean first, boolean second, boolean third) {
        if (first || second || third) {
            return true;
        }
        return false;
    }
}
public class Mod_Items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Mod_Constants.MOD_ID);
    //dusts
    public static final DeferredItem<Item> COPPER_DUST = iRegister(Mod_Constants.getDustName(Mod_Constants.Types.COPPER));
    public static final DeferredItem<Item> DIAMOND_SHARD_DUST = iRegister(Mod_Constants.getDustName(Mod_Constants.Types.DIAMOND_SHARD));
    public static final DeferredItem<Item> EMERALD_SHARD_DUST = iRegister(Mod_Constants.getDustName(Mod_Constants.Types.EMERALD_SHARD));
    public static final DeferredItem<Item> GOLD_DUST = iRegister(Mod_Constants.getDustName(Mod_Constants.Types.GOLD));
    public static final DeferredItem<Item> IRON_DUST = iRegister(Mod_Constants.getDustName(Mod_Constants.Types.IRON));
    public static final DeferredItem<Item> NETHERITE_DUST = iRegister(Mod_Constants.getDustName(Mod_Constants.Types.NEHTERITE));
    public static final DeferredItem<Item> NEHTERITE_SHARD_DUST = iRegister(Mod_Constants.getDustName(Mod_Constants.Types.NEHTERITE_SHARD));
    public static final DeferredItem<Item> OBSIDIAN_DUST = iRegister(Mod_Constants.getDustName(Mod_Constants.Types.OBSIDIAN));
    public static final DeferredItem<Item> UNOBTIANIUM_DUST = iRegister(Mod_Constants.getDustName(Mod_Constants.Types.UNOBTANIUM));
    //materials
    public static final DeferredItem<Item> DIAMOND_SHARD = iRegister(Mod_Constants.Types.DIAMOND_SHARD);
    public static final DeferredItem<Item> DIAMOND_SHARD_RAW = iRegister(Mod_Constants.getRawName(Mod_Constants.Types.DIAMOND_SHARD));
    public static final DeferredItem<Item> EMERALD_SHARD = iRegister(Mod_Constants.Types.EMERALD_SHARD);
    public static final DeferredItem<Item> EMERALD_SHARD_RAW = iRegister(Mod_Constants.getRawName(Mod_Constants.Types.EMERALD_SHARD));
    public static final DeferredItem<Item> NEHTERITE_SHARD = iRegister(Mod_Constants.Types.NEHTERITE_SHARD);
    public static final DeferredItem<Item> NEHTERITE_SHARD_RAW = iRegister(Mod_Constants.getRawName(Mod_Constants.Types.NEHTERITE_SHARD));
    public static final DeferredItem<Item> OBSIDIAN_INGOT = iRegister(Mod_Constants.Items.OBSIDIAN_INGOT);
    public static final DeferredItem<Item> UNOBTIANIUMSCRAP = iRegister(Mod_Constants.Items.UNOBTIANIUM_SCRAP);
    //grind head
    public static final DeferredItem<Item> GRINDER_HEAD = iRegister(Mod_Constants.Items.GRINDER_HEAD, p -> new Grinder_Head_Item(p, Mod_Config.FLINT_GRINDER_USES.getAsInt()));
    public static final DeferredItem<Item> GRINDER_HEAD_NETHERITE = iRegister(Mod_Constants.Items.GRINDER_HEAD_NETHERITE, p -> new Grinder_Head_Item(p, Mod_Config.NETHERITE_GRINDER_USES.getAsInt()));
    public static final DeferredItem<Item> GRINDER_HEAD_UNOBTIANIUM = iRegister(Mod_Constants.Items.GRINDER_HEAD_UNOBTIANIUM, p -> new Grinder_Head_Item(p, Mod_Config.UNOBTIANIUM_GRINDER_USES.getAsInt()));
    //random
    public static final DeferredItem<Item> FLESH = iRegister(Mod_Constants.Items.FLESH, p -> new Item(p.food(Mod_Foods.FLESH)));
    public static final DeferredItem<Item> GROW_STAFF = iRegister(Mod_Constants.Items.GROW_STAFF, Grow_Staff_Item::new);
    public static final DeferredItem<Item> SIMPEL_ELITRA_HALF = iRegister(Mod_Constants.Items.SIMPEL_ELITRA_HALF);
    public static final DeferredItem<Item> SIMPEL_ELITRA_PART1 = iRegister(Mod_Constants.Items.SIMPEL_ELITRA_PART1);
    public static final DeferredItem<Item> SIMPEL_ELITRA_PART2 = iRegister(Mod_Constants.Items.SIMPEL_ELITRA_PART2);
    public static final DeferredItem<Item> WOOD_FIBER = iRegister(Mod_Constants.Items.WOOD_FIBER);
    public static final DeferredItem<Item> TRAVEL_CRYSTAL = iRegister(Mod_Constants.Items.TRAVEL_CRYSTAL, Travel_Cristal::new);
    public static final DeferredItem<Item> REPAIR_GEM = iRegister(Mod_Constants.Items.REPAIR_GEM, p -> new Repair_Gem(p.durability(Mod_Config.REPAIR_GEM_USES.getAsInt())));
    public static final DeferredItem<Item> FLIGHT_GEM = iRegister(Mod_Constants.Items.FLIGHT_GEM, p -> new Flight_Gem(p.durability(Mod_Config.FLIGHT_GEM_USES.getAsInt()).stacksTo(1)));
    public static final DeferredItem<Item> FIRE_GEM = iRegister(Mod_Constants.Items.FIRE_GEM, p -> new Fire_Gem(p.durability(Mod_Config.FIRE_GEM_USES.getAsInt()).stacksTo(1)));
    public static final DeferredItem<Item> TOTEM_GEM = iRegister(Mod_Constants.Items.TOTEM_GEM, p -> new Totem_Gem(p.durability(Mod_Config.TOTEM_GEM_USES.getAsInt()).stacksTo(1)));
    public static final DeferredItem<Item> FEEDING_GEM = iRegister(Mod_Constants.Items.FEEDING_GEM, p -> new Feeding_Gem(p.durability(Mod_Config.FEEDING_GEM_USES.getAsInt()).stacksTo(1)));
    public static final DeferredItem<Item> HOME_WAND = iRegister(Mod_Constants.Items.HOME_WAND, p -> new Home_Wand(p.durability(Mod_Config.HOME_WAND_USES.getAsInt())));
    public static final DeferredItem<Item> XP_CRYSTAL = iRegister(Mod_Constants.Items.XP_CRYSTAL,  XP_Crystal::new);
    //upgrades
    public static final DeferredItem<Item> SPEED_UPGRADE_1 = iRegister(Mod_Constants.Items.SPEED_UPGRADE_1, p -> new Item(p.stacksTo(1)));
    public static final DeferredItem<Item> SPEED_UPGRADE_2 = iRegister(Mod_Constants.Items.SPEED_UPGRADE_2, p -> new Item(p.stacksTo(1)));
    public static final DeferredItem<Item> SPEED_UPGRADE_3 = iRegister(Mod_Constants.Items.SPEED_UPGRADE_3, p -> new Item(p.stacksTo(1)));
    public static final DeferredItem<Item> SPEED_UPGRADE_4 = iRegister(Mod_Constants.Items.SPEED_UPGRADE_4, p -> new Item(p.stacksTo(1)));
    public static final DeferredItem<Item> BOOST_UPGRADE = iRegister(Mod_Constants.Items.BOOST_UPGRADE, p -> new Item(p.stacksTo(3)));;
    public static final DeferredItem<Item> REMOTE_UPGRADE = iRegister(Mod_Constants.Items.REMOTE_UPGRADE, Remote_Upgrade::new);
    public static final DeferredItem<Item> SIMPEL_SPAWNER_EGG = iRegister(Mod_Constants.Items.SIMPEL_SPAWNER_EGG, Simpel_Spawner_Egg::new);
    //sandwiches
    public static final DeferredItem<Item> SANDWICH_BEEF = iRegister(Mod_Constants.Items.SANDWICH_BEEF, p -> new Item(p.food(Mod_Foods.SANDWICH_BEEF)));
    public static final DeferredItem<Item> SANDWICH_MUT = iRegister(Mod_Constants.Items.SANDWICH_MUT, p -> new Item(p.food(Mod_Foods.SANDWICH_MUT)));
    public static final DeferredItem<Item> SANDWICH_PORK = iRegister(Mod_Constants.Items.SANDWICH_PORK, p -> new Item(p.food(Mod_Foods.SANDWICH_PORK)));
    public static final DeferredItem<Item> SANDWICH_CHECKIN = iRegister(Mod_Constants.Items.SANDWICH_CHICKEN, p -> new Item(p.food(Mod_Foods.SANDWICH_CHICKEN)));
    public static final DeferredItem<Item> SANDWICH_VEG = iRegister(Mod_Constants.Items.SANDWICH_VEG, p -> new Item(p.food(Mod_Foods.SANDWICH_VEG)));
    public static final DeferredItem<Item> SANDWICH_MEAT_LOVE = iRegister(Mod_Constants.Items.SANDWICH_MEAT_LOVE, p -> new Item(p.food(Mod_Foods.SANDWICH_MEAT_LOVE)));
    public static final DeferredItem<Item> SANDWICH_MEAT_LOVE_VEG = iRegister(Mod_Constants.Items.SANDWICH_MEAT_LOVE_VEG, p -> new Item(p.food(Mod_Foods.SANDWICH_MEAT_LOVE_VEG)));
    //Templates
    public static final DeferredItem<Item> BLANK_UPGRADE = iRegister(Mod_Constants.Items.BLANK_UPGRADE);
    public static final DeferredItem<Item> BLANK_UPGRADE_RAW = iRegister(Mod_Constants.Items.BLANK_UPGRADE_RAW);
    public static final DeferredItem<Item> OBSIDIAN_UPGRADE_SMITHING = iRegister(Mod_Constants.Items.OBSIDIAN_UPGRADE_SMITHING);
    public static final DeferredItem<Item> UNOBTANIUM_UPGRADE_SMITHING = iRegister(Mod_Constants.Items.UNOBTANIUM_UPGRADE_SMITHING);
    //obsidian tear
    public static final DeferredItem<Item> OBSIDIAN_HELMET = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.HELMET), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.OBSIDIAN, ArmorType.HELMET).fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_CHESTPLATE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.CHESTPLATE), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.OBSIDIAN, ArmorType.CHESTPLATE).fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_LEGGINGS = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.LEGGINGS), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.OBSIDIAN, ArmorType.LEGGINGS).fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_BOOTS = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.BOOTS), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.OBSIDIAN, ArmorType.BOOTS).fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_SWORD = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.SWORD), p ->   new Item(p.sword(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIAN.getMaterial(), 3.0f, -2.4f).fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_SHOVEL = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.SHOVEL), p ->   new ShovelItem(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIAN.getMaterial(), 1.5f, -3.0f, p.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_PICKAXE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.PICKAXE), p -> new Item(p.pickaxe(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIAN.getMaterial(), 1.0f, -2.8f).fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_AXE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.AXE), p ->   new AxeItem(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIAN.getMaterial(), 5.0f, -3.0f, p.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_HOE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.HOE), p ->   new HoeItem(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIAN.getMaterial(), 0.0f, -3.0f, p.fireResistant()));;
    public static final DeferredItem<Item> OBSIDIAN_SPEAR = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIAN, Mod_Constants.Tools.SPEAR), p -> new Item(p.spear(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIAN.getMaterial(),1.07F, 1.079F, 0.52F, 3.1F, 9.8F, 6.4F, 5.1F, 10.0F, 4.6F)));
    //obsidirite tear
    public static final DeferredItem<Item> OBSIDIRITE_HELMET = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.HELMET), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.OBSIDIRITE, ArmorType.HELMET).fireResistant()));
    public static final DeferredItem<Item> OBSIDIRITE_CHEST = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.CHESTPLATE), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.OBSIDIRITE, ArmorType.CHESTPLATE).fireResistant()));
    public static final DeferredItem<Item> OBSIDIRITE_LEGGINGS = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.LEGGINGS), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.OBSIDIRITE, ArmorType.LEGGINGS).fireResistant()));
    public static final DeferredItem<Item> OBSIDIRITE_BOOTS = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.BOOTS), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.OBSIDIRITE, ArmorType.BOOTS).fireResistant()));
    public static final DeferredItem<Item> OBSIDIRITE_SWORD = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.SWORD), p ->   new Item(p.sword(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIRITE.getMaterial(), 3.0f, -2.4f).fireResistant()));
    public static final DeferredItem<Item> OBSIDIRITE_SHOVEL = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.SHOVEL), p ->   new ShovelItem(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIRITE.getMaterial(), 1.5f, -3.0f, p.fireResistant()));
    public static final DeferredItem<Item> OBSIDIRITE_PICKAXE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.PICKAXE), p -> new Item(p.pickaxe(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIRITE.getMaterial(), 1.0f, -2.8f).fireResistant()));
    public static final DeferredItem<Item> OBSIDIRITE_AXE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.AXE), p ->   new AxeItem(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIRITE.getMaterial(), 5.0f, -3.0f, p.fireResistant()));
    public static final DeferredItem<Item> OBSIDIRITE_HOE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.HOE), p ->   new HoeItem(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIRITE.getMaterial(), 0.0f, -3.0f, p.fireResistant()));;
    public static final DeferredItem<Item> OBSIDIRITE_SPEAR = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.OBSIDIRITE, Mod_Constants.Tools.SPEAR), p -> new Item(p.spear(Mod_Armors_Tools.Mod_Tool_Material.OBSIDIRITE.getMaterial(),1.25F, 1.24F, 0.3F, 2.2F, 8.5F, 5.2F, 5.1F, 8.56F, 4.6F)));
    //obsidirite boosted
    public static final DeferredItem<Item> UNOBTIAN_OBSIDIRITE_HELMET = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.HELMET), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.UNOBTIAN_OBSIDIRITE, ArmorType.HELMET).fireResistant()));
    public static final DeferredItem<Item> UNOBTIAN_OBSIDIRITE_CHESTPLATE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.CHESTPLATE), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.UNOBTIAN_OBSIDIRITE, ArmorType.CHESTPLATE).fireResistant()));
    public static final DeferredItem<Item> UNOBTIAN_OBSIDIRITE_LEGGINGS = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.LEGGINGS), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.UNOBTIAN_OBSIDIRITE, ArmorType.LEGGINGS).fireResistant()));
    public static final DeferredItem<Item> UNOBTIAN_OBSIDIRITE_BOOTS = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.BOOTS), p ->   new Item(p.humanoidArmor(Mod_Armors_Tools.Mod_Armor_Material.UNOBTIAN_OBSIDIRITE, ArmorType.BOOTS).fireResistant()));
    public static final DeferredItem<Item> UNOBTIAN_OBSIDIRITE_SWORD = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.SWORD), p ->   new Item(p.sword(Mod_Armors_Tools.Mod_Tool_Material.UNOBTIAN_OBSIDIRITE.getMaterial(), 3.0f, -2.4f).fireResistant()));
    public static final DeferredItem<Item> UNOBTIANOBSIDIRITE_SHOVEL = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.SHOVEL), p ->   new ShovelItem(Mod_Armors_Tools.Mod_Tool_Material.UNOBTIAN_OBSIDIRITE.getMaterial(), 1.5f, -3.0f, p.fireResistant()));
    public static final DeferredItem<Item> UNOBTIAN_OBSIDIRITE_PICKAXE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.PICKAXE), p-> new Item(p.pickaxe(Mod_Armors_Tools.Mod_Tool_Material.UNOBTIAN_OBSIDIRITE.getMaterial(), 1.0f, -2.8f).fireResistant()));
    public static final DeferredItem<Item> UNOBTIAN_OBSIDIRITE_AXE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.AXE), p ->   new AxeItem(Mod_Armors_Tools.Mod_Tool_Material.UNOBTIAN_OBSIDIRITE.getMaterial(), 5.0f, -3.0f, p.fireResistant()));
    public static final DeferredItem<Item> UNOBTIAN_OBSIDIRITE_HOE = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.HOE), p ->   new HoeItem(Mod_Armors_Tools.Mod_Tool_Material.UNOBTIAN_OBSIDIRITE.getMaterial(), 0.0f, -3.0f, p.fireResistant()));;
    public static final DeferredItem<Item> UNOBTIAN_OBSIDIRITE_SPEAR = iRegister(Mod_Constants.getToolNames(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, Mod_Constants.Tools.SPEAR), p -> new Item(p.spear(Mod_Armors_Tools.Mod_Tool_Material.UNOBTIAN_OBSIDIRITE.getMaterial(),1.3F, 1.3F, 0.2F, 1.8F, 7.5F, 4.8F, 5.1F, 8.2F, 4.6F)));

    private static DeferredItem<Item> iRegister(String name) {
        return Mod_Items.ITEMS.registerSimpleItem(name, p ->
                new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Mod_Constants.id(name))));
    }

    private static DeferredItem<Item> iRegister(String name, Function<Item.Properties, ? extends Item> itemFactory) {
        return Mod_Items.ITEMS.registerItem(name, p -> itemFactory.apply(
                new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Mod_Constants.id(name)))));
    }
}

public class Mod_Menu {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Mod_Constants.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<Grinder_Menu>> GRINDER_MENU = registerMenuType(Mod_Constants.getMenuName(Mod_Constants.Blocks.GRINDER), Grinder_Menu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<Grinder_Netherite_Menu>> GRINDER_NETHERITE_MENU = registerMenuType(Mod_Constants.getMenuName(Mod_Constants.Blocks.GRINDER_NETHERITE), Grinder_Netherite_Menu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<Netherite_Crafter_Menu>> NETHERITE_CRAFTER_MENU = registerMenuType(Mod_Constants.getMenuName(Mod_Constants.Blocks.NETHERITE_CRAFTER), Netherite_Crafter_Menu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<Crop_Growth_Menu>> CROP_GROWTH_MENU = registerMenuType(Mod_Constants.getMenuName(Mod_Constants.Blocks.CROP_GROWTH), Crop_Growth_Menu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<Harvester_Menu>> HARVESTER_MENU = registerMenuType(Mod_Constants.getMenuName(Mod_Constants.Blocks.HARVESTER), Harvester_Menu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<Tick_Accelerator_Menu>> TICK_ACCELERATOR = registerMenuType(Mod_Constants.getMenuName(Mod_Constants.Blocks.TICK_ACCELERATOR), Tick_Accelerator_Menu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<Tool_Repair_Menu>> TOOL_REPAIR = registerMenuType(Mod_Constants.getMenuName(Mod_Constants.Blocks.TOOL_REPAIR), Tool_Repair_Menu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<Simpel_Spawner_Menu>> SIMPEL_SPAWNER = registerMenuType(Mod_Constants.getMenuName(Mod_Constants.Blocks.SIMPEL_SPAWNER), Simpel_Spawner_Menu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }
}

public class Mod_Recipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Mod_Constants.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, Mod_Constants.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<Blank_Upgrade_Recipe>> BLANK_UPGRADE_SMITHING_SERIALIZER =
            SERIALIZERS.register(Mod_Constants.Recipe.BLANK_UPGRADE_SMITHING, () -> new RecipeSerializer<>(Blank_Upgrade_Recipe.CODEC, Blank_Upgrade_Recipe.STREAM_CODEC));
    public static final DeferredHolder<RecipeType<?>, RecipeType<Blank_Upgrade_Recipe>> BLANK_UPGRADE_SMITHING_TYPES =
            RECIPE_TYPES.register(Mod_Constants.Recipe.BLANK_UPGRADE_SMITHING, () -> new RecipeType<Blank_Upgrade_Recipe>() {
                @Override
                public String toString() {
                    return Mod_Constants.Recipe.BLANK_UPGRADE_SMITHING;
                }
            });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<Manual_Grind>> MANUAL_GRIND_SERIALIZER =
            SERIALIZERS.register(Mod_Constants.Recipe.MANUALGRIND, () -> new RecipeSerializer<>(Manual_Grind.CODEC, Manual_Grind.STREAM_CODEC));
    public static final DeferredHolder<RecipeType<?>, RecipeType<Manual_Grind>> MANUAL_GRIND_TYPES =
            RECIPE_TYPES.register(Mod_Constants.Recipe.MANUALGRIND, () -> new RecipeType<Manual_Grind>() {
                @Override
                public String toString() {
                    return Mod_Constants.Recipe.MANUALGRIND;
                }
            });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<Grinder_Recipe>> GRINDER_RECIPE_SERIALIZER =
            SERIALIZERS.register(Mod_Constants.Recipe.GRINDER, () -> new RecipeSerializer<>(Grinder_Recipe.CODEC, Grinder_Recipe.STREAM_CODEC));
    public static final DeferredHolder<RecipeType<?>, RecipeType<Grinder_Recipe>> GRINDER_RECIPE_TYPES =
            RECIPE_TYPES.register(Mod_Constants.Recipe.GRINDER, () -> new RecipeType<Grinder_Recipe>() {
                @Override
                public String toString() {
                    return Mod_Constants.Recipe.GRINDER;
                }
            });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<Crop_Growth_Recipe>> CROP_GROWTH_RECIPE =
            SERIALIZERS.register(Mod_Constants.Recipe.CropGrowth, () -> new RecipeSerializer<>(Crop_Growth_Recipe.CODEC, Crop_Growth_Recipe.STREAM_CODEC));
    public static final DeferredHolder<RecipeType<?>, RecipeType<Crop_Growth_Recipe>> CROP_GROWTH_RECIPE_TYPES =
            RECIPE_TYPES.register(Mod_Constants.Recipe.CropGrowth, () -> new RecipeType<Crop_Growth_Recipe>() {
                @Override
                public String toString() {
                    return Mod_Constants.Recipe.CropGrowth;
                }
            });
}
public class Mod_Creative_Tab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Mod_Constants.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SIMPEL_TAB = CREATIVE_MODE_TABS.register(Mod_Constants.CREATIVETAB, () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.simpeladdmod.simpel_tab"))
            .icon(() -> new ItemStack(Mod_Items.GRINDER_HEAD.get()))
            .displayItems((parameters, output) -> {
                Mod_Items.ITEMS.getEntries().forEach(e -> {
                    Item item = e.get();
                    output.accept(item);
                });

            }).build());
}
//data componats
public class Mod_Data_Componet{
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Mod_Constants.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = register("coordinates", builder -> builder.persistent(BlockPos.CODEC).networkSynchronized(BlockPos.STREAM_CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ResourceKey<Level>>> DIMENSION = register("dimension", builder -> builder.persistent(Level.RESOURCE_KEY_CODEC).networkSynchronized(ResourceKey.streamCodec(Registries.DIMENSION)));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUID>> USER_UUID = register("ueser_uuid", builder -> builder.persistent(UUIDUtil.CODEC).networkSynchronized(UUIDUtil.STREAM_CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> USER_NAME = register("ueser_name", builder -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> XP_PERCENT = register("xp_precent", builder -> builder.persistent(ExtraCodecs.POSITIVE_FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> XP_LEVELS = register("xp_levels", builder -> builder.persistent(ExtraCodecs.POSITIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> HOME_WAND_COOLDOWN = register("home_wand_cooldown", builder -> builder.persistent(ExtraCodecs.POSITIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> SIMPEL_SPAWNER_ENTITY = register("simpel_spawner_entity", builder -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> SIMPEL_SPAWNER_FALLBACK_ENTITY = register("simpel_spawner_fallback_entity", builder -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }
}

    public static void register(IEventBus eventBus) {
        Mod_Block_Entity.BLOCK_ENTITIES.register(eventBus);
        Mod_Blocks.BLOCKS.register(eventBus);
        Mod_Items.ITEMS.register(eventBus);
        Mod_Menu.MENUS.register(eventBus);
        Mod_Recipes.SERIALIZERS.register(eventBus);
        Mod_Recipes.RECIPE_TYPES.register(eventBus);
        Mod_Creative_Tab.CREATIVE_MODE_TABS.register(eventBus);
        Mod_Data_Componet.DATA_COMPONENT_TYPES.register(eventBus);
    }

}
