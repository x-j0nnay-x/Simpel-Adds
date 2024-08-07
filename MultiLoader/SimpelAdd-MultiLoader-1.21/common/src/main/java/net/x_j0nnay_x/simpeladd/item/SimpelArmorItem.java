package net.x_j0nnay_x.simpeladd.item;


import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.x_j0nnay_x.simpeladd.item.util.ModArmmorTier;
import org.jetbrains.annotations.ApiStatus;

public class SimpelArmorItem extends ArmorItem {
    public SimpelArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    public SimpelArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type armorType)
    {
        this(material, armorType, new Properties().durability(armorType.getDurability(ModArmmorTier.getArmorDurabilityMultiplier(material))).fireResistant());
    }
}
