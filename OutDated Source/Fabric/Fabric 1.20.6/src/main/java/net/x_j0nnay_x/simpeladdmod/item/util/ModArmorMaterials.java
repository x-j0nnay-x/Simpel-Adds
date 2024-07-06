package net.x_j0nnay_x.simpeladdmod.item.util;




import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.event.listener.GameEventListener;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import org.apache.commons.compress.archivers.Lister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Autovw
 */
public final class ModArmorMaterials
{
    public static final RegistryEntry<ArmorMaterial> OBSIDIANA;
    public static final RegistryEntry<ArmorMaterial> OBSIDIRITEA;
    public static final RegistryEntry<ArmorMaterial> UNOBTAINNETHERITE;
    public static final RegistryEntry<ArmorMaterial> UNOBTIANOBSIDIRITEA;

    private ModArmorMaterials()
    {
    }

    static
    {
        OBSIDIANA = register("obsidian", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
            attribute.put(ArmorItem.Type.BOOTS, 3);
            attribute.put(ArmorItem.Type.LEGGINGS, 6);
            attribute.put(ArmorItem.Type.CHESTPLATE, 8);
            attribute.put(ArmorItem.Type.HELMET, 3);
            attribute.put(ArmorItem.Type.BODY, 30);
        }), 24, 2.7F, 0.07F, ModItems.OBSIDAININGOT);
        OBSIDIRITEA = register("obsidirite", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
            attribute.put(ArmorItem.Type.BOOTS, 4);
            attribute.put(ArmorItem.Type.LEGGINGS, 7);
            attribute.put(ArmorItem.Type.CHESTPLATE, 9);
            attribute.put(ArmorItem.Type.HELMET, 4);
            attribute.put(ArmorItem.Type.BODY, 11);
        }), 24, 3.1F, 0.07F, Items.NETHERITE_SCRAP);
        UNOBTAINNETHERITE = register("unobtainnetherite", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
            attribute.put(ArmorItem.Type.BOOTS, 4);
            attribute.put(ArmorItem.Type.LEGGINGS, 7);
            attribute.put(ArmorItem.Type.CHESTPLATE, 9);
            attribute.put(ArmorItem.Type.HELMET, 4);
            attribute.put(ArmorItem.Type.BODY, 11);
        }), 24, 4.2F, 0.23F, ModItems.UNOBTIANIUMSCRAP);
        UNOBTIANOBSIDIRITEA = register("unobtainobsidirite", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
            attribute.put(ArmorItem.Type.BOOTS, 5);
            attribute.put(ArmorItem.Type.LEGGINGS, 7);
            attribute.put(ArmorItem.Type.CHESTPLATE, 9);
            attribute.put(ArmorItem.Type.HELMET, 5);
            attribute.put(ArmorItem.Type.BODY, 11);
        }), 24, 4.9F, 0.33F, ModItems.UNOBTIANIUMSCRAP);
    }


    private static RegistryEntry<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtections, int enchantability, float toughness, float knockbackResistance, Item ingredientItem)
    {
        Identifier loc = new Identifier(Simpeladd.MOD_ID, name);
        RegistryEntry<SoundEvent> equipSound = SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.ofItems(ingredientItem);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(loc));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values())
        {
            typeMap.put(type, typeProtections.get(type));
        }

        return Registry.registerReference(Registries.ARMOR_MATERIAL, loc, new ArmorMaterial(typeProtections, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}