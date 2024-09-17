package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public class SimpelArmorItem extends ArmorItem {
    public SimpelArmorItem(ArmorMaterial material, Type type) {
        super(material, type, new Item.Properties().fireResistant());
    }
}
