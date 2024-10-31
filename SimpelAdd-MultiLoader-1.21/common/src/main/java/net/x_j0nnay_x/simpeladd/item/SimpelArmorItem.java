package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.item.util.ModArmmorTier;

public class SimpelArmorItem extends ArmorItem {

    public SimpelArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    public SimpelArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type armorType)
    {
        this(material, armorType, new Properties().durability(armorType.getDurability(ModArmmorTier.getArmorDurabilityMultiplier(material))).fireResistant());
    }

}
