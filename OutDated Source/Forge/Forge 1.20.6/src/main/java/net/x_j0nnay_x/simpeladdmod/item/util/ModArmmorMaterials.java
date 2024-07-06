package net.x_j0nnay_x.simpeladdmod.item.util;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmmorMaterials extends ArmorMaterials {
    public static final Holder<ArmorMaterial> OBSIDIANA;
    public static final Holder<ArmorMaterial> OBSIDIRITEA;
    public static final Holder<ArmorMaterial> UNOBTAINNETHERITE;
    public static final Holder<ArmorMaterial> UNOBTIANOBSIDIRITEA;
    public ModArmmorMaterials() {
    }

    public static Holder<ArmorMaterial> bootstrap(Registry<ArmorMaterial> p_332591_) {
        return OBSIDIANA;
    }

    private static Holder<ArmorMaterial> register(String p_334359_, EnumMap<ArmorItem.Type, Integer> p_329993_, int p_332696_, Holder<SoundEvent> p_333975_, float p_329381_, float p_334853_, Supplier<Ingredient> p_333678_) {
        List<ArmorMaterial.Layer> $$7 = List.of(new ArmorMaterial.Layer(new ResourceLocation(p_334359_)));
        return register(p_334359_, p_329993_, p_332696_, p_333975_, p_329381_, p_334853_, p_333678_, $$7);
    }

    private static Holder<ArmorMaterial> register(String p_332406_, EnumMap<ArmorItem.Type, Integer> p_331524_, int p_331490_, Holder<SoundEvent> p_331648_, float p_327988_, float p_328616_, Supplier<Ingredient> p_334412_, List<ArmorMaterial.Layer> p_330855_) {
        EnumMap<ArmorItem.Type, Integer> $$8 = new EnumMap(ArmorItem.Type.class);
        ArmorItem.Type[] var9 = ArmorItem.Type.values();
        int var10 = var9.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            ArmorItem.Type $$9 = var9[var11];
            $$8.put($$9, (Integer)p_331524_.get($$9));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, new ResourceLocation(p_332406_), new ArmorMaterial($$8, p_331490_, p_331648_, p_334412_, p_330855_, p_327988_, p_328616_));
    }

    static {
        OBSIDIANA = register("obsidian", (EnumMap) Util.make(new EnumMap(ArmorItem.Type.class), (p_327101_) -> {
            p_327101_.put(ArmorItem.Type.BOOTS, 3);
            p_327101_.put(ArmorItem.Type.LEGGINGS, 8);
            p_327101_.put(ArmorItem.Type.CHESTPLATE, 6);
            p_327101_.put(ArmorItem.Type.HELMET, 3);
            p_327101_.put(ArmorItem.Type.BODY, 3);
        }), 24, SoundEvents.ARMOR_EQUIP_LEATHER, 2.4F, 0.07F, () -> {
            return Ingredient.of(new ItemLike[]{ModItems.OBSIDAININGOT.get()});
        }, List.of(new ArmorMaterial.Layer(new ResourceLocation(Simpeladd.MOD_ID + ":" + "obsidian"), "", true), new ArmorMaterial.Layer(new ResourceLocation(Simpeladd.MOD_ID + ":" + "obsidian"), "_overlay", false)));
        OBSIDIRITEA = register("obsidirite", (EnumMap) Util.make(new EnumMap(ArmorItem.Type.class), (p_327101_) -> {
            p_327101_.put(ArmorItem.Type.BOOTS, 3);
            p_327101_.put(ArmorItem.Type.LEGGINGS, 8);
            p_327101_.put(ArmorItem.Type.CHESTPLATE, 6);
            p_327101_.put(ArmorItem.Type.HELMET, 3);
            p_327101_.put(ArmorItem.Type.BODY, 3);
        }), 24, SoundEvents.ARMOR_EQUIP_LEATHER, 2.4F, 0.07F, () -> {
            return Ingredient.of(new ItemLike[]{ModItems.OBSIDAININGOT.get()});
        }, List.of(new ArmorMaterial.Layer(new ResourceLocation(Simpeladd.MOD_ID + ":" + "obsidirite"), "", true), new ArmorMaterial.Layer(new ResourceLocation(Simpeladd.MOD_ID + ":" + "obsidian"), "_overlay", false)));
        UNOBTAINNETHERITE = register("iron", (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (p_327096_) -> {
            p_327096_.put(ArmorItem.Type.BOOTS, 2);
            p_327096_.put(ArmorItem.Type.LEGGINGS, 5);
            p_327096_.put(ArmorItem.Type.CHESTPLATE, 6);
            p_327096_.put(ArmorItem.Type.HELMET, 2);
            p_327096_.put(ArmorItem.Type.BODY, 5);
        }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
            return Ingredient.of(new ItemLike[]{Items.IRON_INGOT});
        });
        UNOBTIANOBSIDIRITEA = register("diamond", (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (p_327102_) -> {
            p_327102_.put(ArmorItem.Type.BOOTS, 3);
            p_327102_.put(ArmorItem.Type.LEGGINGS, 6);
            p_327102_.put(ArmorItem.Type.CHESTPLATE, 8);
            p_327102_.put(ArmorItem.Type.HELMET, 3);
            p_327102_.put(ArmorItem.Type.BODY, 11);
        }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
            return Ingredient.of(new ItemLike[]{Items.DIAMOND});
        });
    }

}