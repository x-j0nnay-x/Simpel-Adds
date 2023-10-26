package net.x_j0nnay_x.simpeladdmod.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;

import java.util.List;
import java.util.Map;


public class GrinderListRecipe  {
    public static Map<Item, Integer> getIput() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        add(map, Items.RAW_COPPER, 2);
        add(map, Items.RAW_GOLD, 2);
        add(map, Items.RAW_IRON, 2);
        add(map, Items.ANCIENT_DEBRIS, 2);
        add(map, Items.OBSIDIAN, 2);
        add(map, ModItems.NEHTERITE_SHARD_RAW.get(), 2);

        add(map, Items.BLAZE_ROD, 3);
        add(map, ItemTags.COPPER_ORES, 3);
        add(map, ModTags.Items.RAW_GOLD_DROPPING, 3);
        add(map, ItemTags.IRON_ORES, 3);
        add(map, ModBlocks.DEEPSLATE_DEBRI_ORE.get(), 3);

        add(map, ItemTags.LOGS, 8);
        add(map, Items.BONE, 5);
        add(map, ItemTags.WOOL, 5);

        return map;
    }
    private static void add(Map<Item, Integer> pMap, TagKey<Item> pItemTag, int count) {
        for(Holder<Item> holder : BuiltInRegistries.ITEM.getTagOrEmpty(pItemTag)) {
            if (!isGrandable(holder.value())) {
                pMap.put(holder.value(), count);
            }
        }

    }
    private static void add(Map<Item, Integer> pMap, ItemLike pItem, int count) {
        Item item = pItem.asItem();
        if (isGrandable(item)) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack)null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            pMap.put(item, count);
        }
    }
    private static boolean isGrandable(Item pItem) {
        return pItem.builtInRegistryHolder().is(ModTags.Items.CANGRIND);
    }

}
