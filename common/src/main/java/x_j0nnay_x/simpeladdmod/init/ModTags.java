package x_j0nnay_x.simpeladdmod.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import x_j0nnay_x.simpeladdmod.Constants;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> PLANTABLECROPS = tag("plantablecrops");
        public static final TagKey<Block> TRAVILGEM_BAN = tag("travailgem_ban_blocks");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, Constants.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GRINDERS = tag("grinders");
        public static final TagKey<Item> CHILLING = tag("chilling");
        public static final TagKey<Item> UPGRADES = tag("upgrades");
        public static final TagKey<Item> MANUALREPAIR = tag("manualrepair");
        public static final TagKey<Item> NOTREPAIRABLE = tag("notrepairable");


        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM,Constants.id(name));
        }
    }

    public static class Entyties {
        public static final TagKey<EntityType<?>> TRAVILGEM_BAN = tag("travailgem_ban_entitys");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, Constants.id(name));
        }
    }
}

