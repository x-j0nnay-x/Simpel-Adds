package net.x_j0nnay_x.simpeladd.core;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> OBSIDIANTOOL = tag("needs_obsidiantool");
        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(SimpelAddMod.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> CANGRIND = tag("grindable");
        public static final TagKey<Item> CHILLING = tag("chilling");
        public static final TagKey<Item> UPGRADES = tag("upgrades");
        public static final TagKey<Item> RAW_GOLD_DROPPER = tag("raw_gold_droper");
        public static final TagKey<Item> VEGGIE = tag("veggie_list");
        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(SimpelAddMod.MOD_ID, name));
        }
    }
}

