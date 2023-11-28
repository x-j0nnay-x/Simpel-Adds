package net.x_j0nnay_x.simpeladdmod.until;

import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
       
        public static final TagKey<Block> OBSIDIAN = tag("obsidain_tool_level");

        public static final TagKey<Block> OBSIDIANRITE = tag("obsidainrite_tool_level");



        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Simpeladd.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> CANGRIND = tag("grindable");
        public static final TagKey<Item> CHILLING = tag("chilling");
        public static final TagKey<Item> UPGRADES = tag("upgrades");
        public static final TagKey<Item> RAW_GOLD_DROPPER = tag("raw_gold_droper");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Simpeladd.MOD_ID, name));
        }
    }
}

