package x_j0nnay_x.simpeladdmod.items;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.NotNull;
import x_j0nnay_x.simpeladdmod.init.Mod_Items;
import java.util.Map;
import java.util.function.Consumer;

public class Grinder_Head_Item_Broken extends Item{

    private static final Map<Integer, Item> VersonMap =
            Map.of(
                    0, Mod_Items.GRINDER_HEAD.get(),
                    1, Mod_Items.GRINDER_HEAD_NEHTERITE.get(),
                    2, Mod_Items.GRINDER_HEAD_UNOBTIANIUM.get()
            );


    public Grinder_Head_Item_Broken(Item.Properties properties) {
        super(properties);

    }


    @Override
    public @NotNull DataComponentMap components() {

        return super.components();
    }


    public static void setVersion(int version, ItemStack itemStack) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("GrinderVersion", version);

    }


    public static ItemStack getNewDefaultInstance(int version) {
        ItemStack grinder = Mod_Items.GRINDER_HEAD_BROKEN.get().getDefaultInstance();
        setVersion(version, grinder);
        return grinder;
    }



    public static ItemStack healItem(int healAmount, ItemStack itemStack) {
        ItemStack grinder = itemStack.copy();
        grinder.setDamageValue(grinder.getMaxDamage() - healAmount);
        return grinder;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(itemStack.get(DataComponents.CUSTOM_DATA) != null) {
            int GrinderVersion = itemStack.get(DataComponents.CUSTOM_DATA).copyTag().getInt("GrinderVersion").get();
            if (GrinderVersion == 0) {
                builder.accept(Component.translatable("item.simpeladdmod.grinderhead_broken.info.default"));
            }
            if (GrinderVersion == 1) {
                builder.accept(Component.translatable("item.simpeladdmod.grinderhead_broken.info.netherite"));
            }
            if (GrinderVersion == 2) {
                builder.accept(Component.translatable("item.simpeladdmod.grinderhead_broken.info.unobtanium"));
            }
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }


}