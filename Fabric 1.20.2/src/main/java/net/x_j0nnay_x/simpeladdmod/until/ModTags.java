package net.x_j0nnay_x.simpeladdmod.until;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> OBSIDIANTOOL = tag("needs_obsidiantool");
          private static TagKey<Block> tag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Simpeladd.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> CANGRIND = tag("grindable");
        public static final TagKey<Item> CHILLING = tag("chilling");
        public static final TagKey<Item> UPGRADES = tag("upgrades");
        public static final TagKey<Item> RAW_GOLD_DROPPER = tag("raw_gold_droper");
        private static TagKey<Item> tag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Simpeladd.MOD_ID, name));
        }
    }
}

