package net.x_j0nnay_x.simpeladd.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.core.ModTags;

import java.util.ArrayList;
import java.util.List;

public class GrinderRepair extends RepairItemRecipe {
    public static final String ID = "grindrepair";

    public GrinderRepair(CraftingBookCategory category){
        super(category);
    }
    // variables for added readability
    Item flintItem = Items.FLINT;


    @Override
    public boolean matches(CraftingInput input, Level level) {
        ItemStack grinder = null;
        List<ItemStack> flint = new ArrayList<>();

        for (int i = 0; i < input.size(); ++i) {
            ItemStack stackInQuestion = input.getItem(i);
            if (!stackInQuestion.isEmpty()) {
                if (stackInQuestion.is(ModTags.Items.GRINDERS) && stackInQuestion.isDamaged()) {
                    grinder = stackInQuestion;
                }
                if (stackInQuestion.is(flintItem)) {
                    flint.add(stackInQuestion);
                }
            }
        }
        return grinder != null && !flint.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider access) {
        List<Item> flintList = new ArrayList<>();
        ItemStack grinder = null;
        for (int i = 0; i < input.size(); ++i) {
            ItemStack itemstack = input.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.is(ModTags.Items.GRINDERS)) {
                    if (grinder == null) {
                        grinder = itemstack;
                    } else {
                        return ItemStack.EMPTY;
                    }
                }

                if (itemstack.is(flintItem)) {
                    flintList.add(itemstack.getItem());
                }
            }
        }

        if ((!flintList.isEmpty()) && grinder != null && grinder.isDamaged()) {
            ItemStack newGrinder = ModItems.GRINDERHEAD.getDefaultInstance();
            ItemStack newGrinderN = ModItems.GRINDERHEADNEHTERITE.getDefaultInstance();
            ItemStack newGrinderU = ModItems.GRINDERHEADUNOBTIANIUM.getDefaultInstance();
            if(!flintList.isEmpty() && grinder.is(ModItems.GRINDERHEAD)) {
                newGrinder.setDamageValue(grinder.getDamageValue() - (flintList.size() * 5));
                return newGrinder;
            }
            if(!flintList.isEmpty() && grinder.is(ModItems.GRINDERHEADNEHTERITE)) {
                newGrinderN.setDamageValue(grinder.getDamageValue() - (flintList.size() * 5));
                return newGrinderN;
            }
            if(!flintList.isEmpty() && grinder.is(ModItems.GRINDERHEADUNOBTIANIUM)) {
                newGrinderU.setDamageValue(grinder.getDamageValue() - (flintList.size() * 5));
                return newGrinderU;
            }


            }



        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }




}