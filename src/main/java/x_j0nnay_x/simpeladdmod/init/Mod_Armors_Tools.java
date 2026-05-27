package x_j0nnay_x.simpeladdmod.init;

import com.google.common.collect.Maps;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public final class Mod_Armors_Tools {

    public interface Mod_Armor_Material {

        ArmorMaterial OBSIDIAN = registerArmorMaterial(Mod_Constants.Types.OBSIDIAN, 30, createDefenseMap(3, 6, 8, 3, 35), 24, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.7F, 0.07F, Mod_Tags.Items.REPAIRS_OBSIDIAN);
        ArmorMaterial OBSIDIRITE = registerArmorMaterial(Mod_Constants.Types.OBSIDIRITE, 40, createDefenseMap(4, 7, 9, 4, 40), 29, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.2F, 0.12F, Mod_Tags.Items.REPAIRS_OBSIDIRITE);
        ArmorMaterial UNOBTIAN_OBSIDIRITE = registerArmorMaterial(Mod_Constants.Types.UNOBTIAN_OBSIDIRITE, 47, createDefenseMap(6, 9, 11, 6, 55), 44, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.2F, 0.22F, Mod_Tags.Items.REPAIRS_UNOBTIANOBSIDIRITE);

        static Map<ArmorType, Integer> makeDefense(final int boots, final int legs, final int chest, final int helm, final int body) {
            return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
        }


        static EnumMap<ArmorType, Integer> createDefenseMap(int boots, int leggings, int chestplate, int helmet, int body) {
            EnumMap<ArmorType, Integer> defenseMap = new EnumMap<>(ArmorType.class);
            defenseMap.put(ArmorType.BOOTS, boots);
            defenseMap.put(ArmorType.LEGGINGS, leggings);
            defenseMap.put(ArmorType.CHESTPLATE, chestplate);
            defenseMap.put(ArmorType.HELMET, helmet);
            defenseMap.put(ArmorType.BODY, body);
            return defenseMap;
        }


        static ResourceKey<EquipmentAsset> createId(String name) {
            return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Mod_Constants.MOD_ID, name));
        }

        private static ArmorMaterial registerArmorMaterial(String name, int durability, EnumMap<ArmorType, Integer> defenseMap, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient) {
            return new ArmorMaterial(durability, defenseMap, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, createId(name));
        }

    }

    public enum Mod_Tool_Material {

        OBSIDIAN(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1900, 8.9f, 3.5f, 14, Mod_Tags.Items.REPAIRS_OBSIDIAN),
        OBSIDIRITE(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2900, 11.1f, 4.5f, 22, Mod_Tags.Items.REPAIRS_OBSIDIRITE),
        UNOBTIAN_OBSIDIRITE(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3900, 12.3f, 5.5f, 22, Mod_Tags.Items.REPAIRS_UNOBTIANOBSIDIRITE);

        private final ToolMaterial material;

        Mod_Tool_Material(TagKey<Block> incorrectBlockTag, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairTagName) {
            this.material = new ToolMaterial(incorrectBlockTag, durability, speed, attackDamageBonus, enchantmentValue, repairTagName);
        }

        public ToolMaterial getMaterial() {
            return this.material;
        }

        public int durability() { return material.durability(); }
        public float speed() { return material.speed(); }
        public float attackDamageBonus() { return material.attackDamageBonus(); }
        public int enchantmentValue() { return material.enchantmentValue(); }
        public TagKey<Item> repairItems() { return material.repairItems(); }
    }

}