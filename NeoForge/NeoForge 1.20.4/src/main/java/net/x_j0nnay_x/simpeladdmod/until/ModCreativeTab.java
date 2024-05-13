package net.x_j0nnay_x.simpeladdmod.until;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

public class ModCreativeTab {

    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Simpeladd.MOD_ID);

    // tab title
    public static String SIMPELADD_MOD_TAB_TITLE = "creativetab.simpel_tab";

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TUTORIAL_MOD_TAB = CREATIVE_MODE_TABS.register("tutorial_mod_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplayParameters, output) -> {
            ModItems.ITEMS.getEntries()
                    .stream()
                    .map(DeferredHolder::get)
                    .forEach(output::accept);

            ModBlocks.BLOCKS.getEntries()
                    .stream()
                    .map(DeferredHolder::get)
                    .forEach(output::accept);
        });

        builder.icon(() -> new ItemStack(ModItems.GRINDERHEAD.get()));
        builder.title(Component.translatable(SIMPELADD_MOD_TAB_TITLE));

        return builder.build();
    });


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static void registerCreativeTab(){
        Simpeladd.LOGGER.info("Registering Creative Tab For " + Simpeladd.MOD_ID);
    }
}
