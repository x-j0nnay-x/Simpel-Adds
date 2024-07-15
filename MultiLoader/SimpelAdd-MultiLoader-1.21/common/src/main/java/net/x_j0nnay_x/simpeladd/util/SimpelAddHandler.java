package net.x_j0nnay_x.simpeladd.util;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface  SimpelAddHandler {
    int $getSlotCount();
    ItemStack $getStack(int slot);
    void $setStack(int slot, ItemStack stack);
    CompoundTag $serialize(HolderLookup.Provider levelRegistry);
    void $deserialize(CompoundTag invTag, HolderLookup.Provider levelRegistry);
}
