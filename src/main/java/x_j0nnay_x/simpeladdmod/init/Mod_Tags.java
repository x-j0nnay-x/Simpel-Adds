package x_j0nnay_x.simpeladdmod.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Mod_Tags {

    public static class Blocks {
        public static final TagKey<Block> PLANTABLE_CROPS = tag("plantable_crops");
        public static final TagKey<Block> TRAVIL_GEM_BAN = tag("travail_gem_ban_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(Mod_Constants.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GRINDERS = tag("grinder_list");
        public static final TagKey<Item> UPGRADES = tag("upgrades");
        public static final TagKey<Item> REPAIRS_OBSIDIAN = tag("repairs_obsidian");
        public static final TagKey<Item> REPAIRS_OBSIDIRITE = tag("repairs_obsidirite");
        public static final TagKey<Item> REPAIRS_UNOBTIANOBSIDIRITE = tag("repairs_unobtain_obsidirite");
        public static final TagKey<Item> REPAIR_NOT_ALLOWED = tag("repair_not_allowed");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(Mod_Constants.id(name));
        }
    }

}
