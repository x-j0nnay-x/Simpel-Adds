package net.x_j0nnay_x.simpeladd.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.x_j0nnay_x.simpeladd.SimpelAddMod;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import net.x_j0nnay_x.simpeladd.core.ModTags;
import java.util.ArrayList;
import java.util.List;

public class SimpelCraftingRepair extends RepairItemRecipe {
    public static final String IDNAME = "simpel_crafting_repair";
    public static final ResourceLocation ID = new ResourceLocation(SimpelAddMod.MOD_ID,  IDNAME);


    // variables for added readability
    Item flintItem = Items.FLINT;
    Item scrapItem = ModItems.UNOBTIANIUMSCRAP;

    public SimpelCraftingRepair(ResourceLocation $$0, CraftingBookCategory $$1) {
        super($$0, $$1);
    }


    @Override
    public boolean matches(CraftingContainer input, Level level) {
        ItemStack toolRepairing = null;
        List<ItemStack> repairItem = new ArrayList<>();
        for (int i = 0; i < input.getContainerSize(); ++i) {
            ItemStack stackInQuestion = input.getItem(i);
            if (!stackInQuestion.isEmpty()) {
                if (stackInQuestion.is(ModTags.Items.MANUALREPAIR) && stackInQuestion.isDamaged()) {
                    toolRepairing = stackInQuestion;
                }
                if (stackInQuestion.is(flintItem)) {
                    repairItem.add(stackInQuestion);
                }
                if (stackInQuestion.is(scrapItem)) {
                    repairItem.add(stackInQuestion);
                }
            }
        }
        return toolRepairing != null && !repairItem.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingContainer input, RegistryAccess access) {
        List<Item> flintList = new ArrayList<>();
        List<Item> scrapList = new ArrayList<>();
        ItemStack toolItems = null;
        for (int i = 0; i < input.getContainerSize(); ++i) {
            ItemStack itemstack = input.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.is(ModTags.Items.MANUALREPAIR)) {
                    if (toolItems == null) {
                        toolItems = itemstack;
                    } else {
                        return ItemStack.EMPTY;
                    }
                }
                if (itemstack.is(flintItem)) {
                    flintList.add(itemstack.getItem());
                }
                if (itemstack.is(scrapItem)) {
                    scrapList.add(itemstack.getItem());
                }
            }
        }
        if (toolItems != null && toolItems.isDamaged()) {
            ItemStack newGrinder = ModItems.GRINDERHEAD.getDefaultInstance();
            ItemStack newGrinderN = ModItems.GRINDERHEADNEHTERITE.getDefaultInstance();
            ItemStack newGrinderU = ModItems.GRINDERHEADUNOBTIANIUM.getDefaultInstance();
            ItemStack newRepairTool = ModItems.REPAIRTOOL.getDefaultInstance();
            ItemStack newFireProofTool = ModItems.FIREPROOFTOOL.getDefaultInstance();
            ItemStack newFeedingTool = ModItems.FEEDINGTOOL.getDefaultInstance();
            if(!flintList.isEmpty() && toolItems.is(ModItems.GRINDERHEAD)) {
                newGrinder.setDamageValue(toolItems.getDamageValue() - (flintList.size() * 68));
                return newGrinder;
            }
            if(!flintList.isEmpty() && toolItems.is(ModItems.GRINDERHEADNEHTERITE)) {
                newGrinderN.setDamageValue(toolItems.getDamageValue() - (flintList.size() * 68));
                return newGrinderN;
            }
            if(!flintList.isEmpty() && toolItems.is(ModItems.GRINDERHEADUNOBTIANIUM)) {
                newGrinderU.setDamageValue(toolItems.getDamageValue() - (flintList.size() * 68));
                return newGrinderU;
            }
            if(!scrapList.isEmpty() && toolItems.is(ModItems.REPAIRTOOL)) {
                newRepairTool.setDamageValue(toolItems.getDamageValue() - (scrapList.size() * 18));
                return newRepairTool;
            }
            if(!scrapList.isEmpty() && toolItems.is(ModItems.FIREPROOFTOOL)) {
                newFireProofTool.setDamageValue(toolItems.getDamageValue() - (scrapList.size() * 18));
                return newFireProofTool;
            }
            if(!scrapList.isEmpty() && toolItems.is(ModItems.FEEDINGTOOL)) {
                newFeedingTool.setDamageValue(toolItems.getDamageValue() - (scrapList.size() * 18));
                return newFeedingTool;
            }
            }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }
}