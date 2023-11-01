package net.x_j0nnay_x.simpeladdmod.recipe;

import com.google.common.collect.Maps;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;

import java.util.HashMap;
import java.util.Map;


public class GrinderRecipeHardcoded  {
    public static Map<Item, Item> GetOutput(){
        Map<Item, Item> outPut = Maps.newLinkedHashMap();
        outPut.put(Items.RAW_COPPER, ModItems.COPPERDUST.get());
        outPut.put(Items.RAW_IRON, ModItems.IRONDUST.get());
        outPut.put(Items.RAW_GOLD, ModItems.GOLDDUST.get());
        outPut.put(Items.COPPER_ORE, ModItems.COPPERDUST.get());
        outPut.put(Items.IRON_ORE, ModItems.IRONDUST.get());
        outPut.put(Items.GOLD_ORE, ModItems.GOLDDUST.get());
        outPut.put(Items.DEEPSLATE_COPPER_ORE, ModItems.COPPERDUST.get());
        outPut.put(Items.DEEPSLATE_IRON_ORE, ModItems.IRONDUST.get());
        outPut.put(Items.DEEPSLATE_GOLD_ORE, ModItems.GOLDDUST.get());
        outPut.put(Items.ANCIENT_DEBRIS, ModItems.NETHERITEDUST.get());
        outPut.put(Items.OBSIDIAN, ModItems.OBSIDAINDUST.get());
        outPut.put(Items.BLAZE_ROD, Items.BLAZE_POWDER);

        outPut.put(Items.ACACIA_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.BIRCH_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.CHERRY_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.OAK_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.JUNGLE_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.MANGROVE_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.DARK_OAK_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.SPRUCE_LOG, ModItems.WOODFIBER.get());

        outPut.put(Items.STRIPPED_ACACIA_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.STRIPPED_BIRCH_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.STRIPPED_CHERRY_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.STRIPPED_OAK_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.STRIPPED_JUNGLE_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.STRIPPED_MANGROVE_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.STRIPPED_DARK_OAK_LOG, ModItems.WOODFIBER.get());
        outPut.put(Items.STRIPPED_SPRUCE_LOG, ModItems.WOODFIBER.get());


        return outPut;
    }

    public static Map<Item, Integer> GetInputCount() {
        Map<Item, Integer> inputCount = Maps.newLinkedHashMap();
        inputCount.put(Items.RAW_COPPER, 2);
        inputCount.put(Items.COPPER_ORE, 3);
        inputCount.put(Items.DEEPSLATE_COPPER_ORE, 3);
        inputCount.put(Items.RAW_IRON, 2);
        inputCount.put(Items.IRON_ORE, 3);
        inputCount.put(Items.DEEPSLATE_IRON_ORE, 3);
        inputCount.put(Items.RAW_GOLD, 2);
        inputCount.put(Items.GOLD_ORE, 3);
        inputCount.put(Items.DEEPSLATE_GOLD_ORE, 3);
        inputCount.put(Items.ANCIENT_DEBRIS, 2);
        inputCount.put(Items.OBSIDIAN, 2);
        inputCount.put(Items.BLAZE_ROD, 3);

        inputCount.put(Items.ACACIA_LOG, 8);
        inputCount.put(Items.BIRCH_LOG, 8);
        inputCount.put(Items.CHERRY_LOG, 8);
        inputCount.put(Items.OAK_LOG, 8);
        inputCount.put(Items.JUNGLE_LOG, 8);
        inputCount.put(Items.MANGROVE_LOG, 8);
        inputCount.put(Items.DARK_OAK_LOG, 8);
        inputCount.put(Items.SPRUCE_LOG, 8);

        inputCount.put(Items.STRIPPED_ACACIA_LOG, 8);
        inputCount.put(Items.STRIPPED_BIRCH_LOG, 8);
        inputCount.put(Items.STRIPPED_CHERRY_LOG, 8);
        inputCount.put(Items.STRIPPED_OAK_LOG, 8);
        inputCount.put(Items.STRIPPED_JUNGLE_LOG, 8);
        inputCount.put(Items.STRIPPED_MANGROVE_LOG, 8);
        inputCount.put(Items.STRIPPED_DARK_OAK_LOG, 8);
        inputCount.put(Items.STRIPPED_SPRUCE_LOG, 8);



        return inputCount;
    }
   public void GetRecipe(){

   }

}
