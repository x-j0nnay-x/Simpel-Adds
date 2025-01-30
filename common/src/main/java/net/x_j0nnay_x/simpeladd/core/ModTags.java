package net.x_j0nnay_x.simpeladd.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> PLANTABLECROPS = tag("plantablecrops");
        public static final TagKey<Block> TRAVILGEM_BAN = tag("travailgem_ban");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GRINDERS = tag("grinders");
        public static final TagKey<Item> CHILLING = tag("chilling");
        public static final TagKey<Item> UPGRADES = tag("upgrades");
        public static final TagKey<Item> MANUALREPAIR = tag("manualrepair");
        public static final TagKey<Item> NOTREPAIRABLE = tag("notrepairable");


        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, name));
        }
    }
}

