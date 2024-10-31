package net.x_j0nnay_x.simpeladd.item.util;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public final class ModArmmorTier {

    public static final Holder<ArmorMaterial> OBSIDIANA;
    public static final Holder<ArmorMaterial> OBSIDIRITEA;
    public static final Holder<ArmorMaterial> UNOBTAINNETHERITE;
    public static final Holder<ArmorMaterial> UNOBTIANOBSIDIRITEA;

    private ModArmmorTier() {
    }

    static {
        OBSIDIANA = register("obsidian", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
            attribute.put(ArmorItem.Type.BOOTS, 3);
            attribute.put(ArmorItem.Type.LEGGINGS, 6);
            attribute.put(ArmorItem.Type.CHESTPLATE, 8);
            attribute.put(ArmorItem.Type.HELMET, 3);
            attribute.put(ArmorItem.Type.BODY, 35);
        }), 24, 2.7F, 0.07F, ModItems.OBSIDAININGOT);
        OBSIDIRITEA = register("obsidirite", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
            attribute.put(ArmorItem.Type.BOOTS, 4);
            attribute.put(ArmorItem.Type.LEGGINGS, 7);
            attribute.put(ArmorItem.Type.CHESTPLATE, 9);
            attribute.put(ArmorItem.Type.HELMET, 4);
            attribute.put(ArmorItem.Type.BODY, 40);
        }), 29, 3.2F, 0.12F, Items.NETHERITE_INGOT);
        UNOBTAINNETHERITE = register("unobtainnetherite", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
            attribute.put(ArmorItem.Type.BOOTS, 5);
            attribute.put(ArmorItem.Type.LEGGINGS, 8);
            attribute.put(ArmorItem.Type.CHESTPLATE, 10);
            attribute.put(ArmorItem.Type.HELMET, 5);
            attribute.put(ArmorItem.Type.BODY, 45);
        }), 34, 3.7F, 0.17F, ModItems.UNOBTIANIUMSCRAP);
        UNOBTIANOBSIDIRITEA = register("unobtainobsidirite", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
            attribute.put(ArmorItem.Type.BOOTS, 6);
            attribute.put(ArmorItem.Type.LEGGINGS, 9);
            attribute.put(ArmorItem.Type.CHESTPLATE, 11);
            attribute.put(ArmorItem.Type.HELMET, 6);
            attribute.put(ArmorItem.Type.BODY, 55);
        }), 44, 4.2F, 0.22F, ModItems.UNOBTIANIUMSCRAP);
    }

    public static int getArmorDurabilityMultiplier(Holder<ArmorMaterial> material)
    {
        if (material == OBSIDIANA)
            return 39;
        if (material == OBSIDIRITEA)
            return 41;
        if (material == UNOBTAINNETHERITE)
            return 43;
        if (material == UNOBTIANOBSIDIRITEA)
            return 47;
        return 0;
    }

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtections, int enchantability, float toughness, float knockbackResistance, Item ingredientItem) {
        ResourceLocation loc = ResourceLocation.fromNamespaceAndPath(SimpelAddMod.MOD_ID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(loc));
        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtections.get(type));
        }
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, loc, new ArmorMaterial(typeProtections, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}