package net.x_j0nnay_x.simpeladd.item;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.x_j0nnay_x.simpeladd.core.ModBlocks;
import net.x_j0nnay_x.simpeladd.core.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrinderHeadItem_Broken extends Item{

    private static final Map<Integer, Item> VersonMap =
            Map.of(
                    0, ModItems.GRINDERHEAD,
                    1, ModItems.GRINDERHEADNEHTERITE,
                    2, ModItems.GRINDERHEADUNOBTIANIUM
            );


    public GrinderHeadItem_Broken() {
        super(new Properties()
                .stacksTo(1)
                );
    }


    public static void setVersion(int version, ItemStack itemStack) {
       itemStack.set(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag(){{
            putInt("GrinderVersion", version);
       }}));
    }

    public static ItemStack getNewDefaultInstance(int version) {
        ItemStack grinder = ModItems.GRINDERHEAD_BROKEN.getDefaultInstance();
        setVersion(version, grinder);
        return grinder;
    }

    public static ItemStack healItem(int healAmount, ItemStack itemStack) {
      ItemStack grinder = VersonMap.get(itemStack.get(DataComponents.CUSTOM_DATA).copyTag().getInt("GrinderVersion")).getDefaultInstance();
      grinder.setDamageValue(grinder.getMaxDamage() - healAmount);
     return grinder;
    }



    @Override
    public boolean isValidRepairItem(ItemStack $$0, ItemStack $$1) {
        return $$1.is(Items.FLINT);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(stack.get(DataComponents.CUSTOM_DATA) != null) {
            int GrinderVersion = stack.get(DataComponents.CUSTOM_DATA).copyTag().getInt("GrinderVersion");
            if (GrinderVersion == 0) {
                tooltipComponents.add(Component.translatable("item.simpeladdmod.grinderhead_broken.info.default"));
            }
            if (GrinderVersion == 1) {
                tooltipComponents.add(Component.translatable("item.simpeladdmod.grinderhead_broken.info.netherite"));
            }
            if (GrinderVersion == 2) {
                tooltipComponents.add(Component.translatable("item.simpeladdmod.grinderhead_broken.info.unobtanium"));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}