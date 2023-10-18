package net.x_j0nnay_x.simpeladdmod.until;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.simpeladdmod;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, simpeladdmod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SIMPEL_TAB = CREATIVE_MODE_TABS.register("simpel_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GRINDERHEAD.get()))
                    .title(Component.translatable("creativetab.simpel_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        //items
                        pOutput.accept(ModItems.GRINDERHEAD.get());
                        pOutput.accept(ModItems.GOLDSTICK.get());
                        pOutput.accept(ModItems.WOODFIBER.get());
                        pOutput.accept(ModItems.FLESH.get());

                        pOutput.accept(ModItems.SANDWICH_MUT.get());
                        pOutput.accept(ModItems.SANDWICH_BEEF.get());
                        pOutput.accept(ModItems.SANDWICH_PORK.get());
                        pOutput.accept(ModItems.SANDWICH_CHECKIN.get());
                        pOutput.accept(ModItems.SANDWICH_VEG.get());
                        pOutput.accept(ModItems.SANDWICH_MEET_LOVE.get());
                        pOutput.accept(ModItems.SANDWICH_MEET_LOVE_VEG.get());

                        pOutput.accept(ModItems.BLANKUPGRADE.get());
                        pOutput.accept(ModItems.BLANKUPGRADE_RAW.get());
                        pOutput.accept(ModItems.OBSIDIANUPGRADE_SMITHING.get());

                        pOutput.accept(ModItems.GOLDDUST.get());
                        pOutput.accept(ModItems.IRONDUST.get());
                        pOutput.accept(ModItems.COPPERDUST.get());
                        pOutput.accept(ModItems.NETHERITEDUST.get());
                        pOutput.accept(ModItems.NEHTERITE_SHARD_DUST.get());

                        pOutput.accept(ModItems.SIMPEL_ELITRA_HALF.get());
                        pOutput.accept(ModItems.SIMPEL_ELITRA_PART1.get());
                        pOutput.accept(ModItems.SIMPEL_ELITRA_PART2.get());

                        pOutput.accept(ModItems.OBSIDAININGOT.get());
                        pOutput.accept(ModItems.OBSIDAINDUST.get());

                        pOutput.accept(ModItems.NEHTERITE_SHARD.get());
                        pOutput.accept(ModItems.NEHTERITE_SHARD_RAW.get());

                        pOutput.accept(ModItems.OBSIDAINAXE.get());
                        pOutput.accept(ModItems.OBSIDIANHOE.get());
                        pOutput.accept(ModItems.OBSIDIANSWORD.get());
                        pOutput.accept(ModItems.OBSIDIANPICKAXE.get());
                        pOutput.accept(ModItems.OBSIDIANSPADE.get());

                        pOutput.accept(ModItems.OBSIDIANHELMET.get());
                        pOutput.accept(ModItems.OBSIDIANCHEST.get());
                        pOutput.accept(ModItems.OBSIDIANLEGS.get());
                        pOutput.accept(ModItems.OBSIDIANBOOTS.get());


                        pOutput.accept(ModItems.OBSIDIRITEAXE.get());
                        pOutput.accept(ModItems.OBSIDIRITEHOE.get());
                        pOutput.accept(ModItems.OBSIDIRITESWORD.get());
                        pOutput.accept(ModItems.OBSIDIRITEPICKAXE.get());
                        pOutput.accept(ModItems.OBSIDIRITESPADE.get());

                        pOutput.accept(ModItems.OBSIDIRITEHELMET.get());
                        pOutput.accept(ModItems.OBSIDIRITECHEST.get());
                        pOutput.accept(ModItems.OBSIDIRITELEGS.get());
                        pOutput.accept(ModItems.OBSIDIRITEBOOTS.get());

                        //block
                        pOutput.accept(ModBlocks.DEEPSLATE_DEBRI_ORE.get());
                        pOutput.accept(ModBlocks.GRINDER_BLOCK.get());
                        pOutput.accept(ModBlocks.BLOCK_FACTORY_BLOCK.get());
                        pOutput.accept(ModBlocks.CHILLER_BLOCK.get());




                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
