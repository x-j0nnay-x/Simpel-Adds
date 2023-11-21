package net.x_j0nnay_x.simpeladdmod.until;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;


public class ModCreativeTab {

    public static final ItemGroup SIMPEL_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Simpeladd.MOD_ID, "simpel_tab"),
            FabricItemGroup.builder().displayName(Text.translatable("creativetab.simpel_tab"))
                    .icon(() -> new ItemStack(ModItems.GRINDERHEAD)).entries((displayContext, entries) -> {

                        //items
                        entries.add(ModItems.GRINDERHEAD);
                        entries.add(ModItems.GOLDSTICK);
                        entries.add(ModItems.WOODFIBER);
                        entries.add(ModItems.FLESH);

                        entries.add(ModItems.SANDWICH_MUT);
                        entries.add(ModItems.SANDWICH_BEEF);
                        entries.add(ModItems.SANDWICH_PORK);
                        entries.add(ModItems.SANDWICH_CHECKIN);
                        entries.add(ModItems.SANDWICH_VEG);
                        entries.add(ModItems.SANDWICH_MEET_LOVE);
                        entries.add(ModItems.SANDWICH_MEET_LOVE_VEG);

                        entries.add(ModItems.BLANKUPGRADE);
                        entries.add(ModItems.BLANKUPGRADE_RAW);
                        entries.add(ModItems.OBSIDIANUPGRADE_SMITHING);
                        entries.add(ModItems.UNOBTANIUMUPGRADE_SMITHING);

                        entries.add(ModItems.GOLDDUST);
                        entries.add(ModItems.IRONDUST);
                        entries.add(ModItems.COPPERDUST);
                        entries.add(ModItems.NETHERITEDUST);
                        entries.add(ModItems.NEHTERITE_SHARD_DUST);
                        entries.add(ModItems.UNOBTIANIUMDUST);

                        entries.add(ModItems.SIMPEL_ELITRA_HALF);
                        entries.add(ModItems.SIMPEL_ELITRA_PART1);
                        entries.add(ModItems.SIMPEL_ELITRA_PART2);

                        entries.add(ModItems.OBSIDAININGOT);
                        entries.add(ModItems.OBSIDAINDUST);

                        entries.add(ModItems.NEHTERITE_SHARD);
                        entries.add(ModItems.NEHTERITE_SHARD_RAW);

                        entries.add(ModItems.UNOBTIANIUMSCRAP);

                        entries.add(ModItems.OBSIDAINAXE);
                        entries.add(ModItems.OBSIDIANHOE);
                        entries.add(ModItems.OBSIDIANSWORD);
                        entries.add(ModItems.OBSIDIANPICKAXE);
                        entries.add(ModItems.OBSIDIANSPADE);

                        entries.add(ModItems.OBSIDIANHELMET);
                        entries.add(ModItems.OBSIDIANCHEST);
                        entries.add(ModItems.OBSIDIANLEGS);
                        entries.add(ModItems.OBSIDIANBOOTS);


                        entries.add(ModItems.OBSIDIRITEAXE);
                        entries.add(ModItems.OBSIDIRITEHOE);
                        entries.add(ModItems.OBSIDIRITESWORD);
                        entries.add(ModItems.OBSIDIRITEPICKAXE);
                        entries.add(ModItems.OBSIDIRITESPADE);

                        entries.add(ModItems.OBSIDIRITEHELMET);
                        entries.add(ModItems.OBSIDIRITECHEST);
                        entries.add(ModItems.OBSIDIRITELEGS);
                        entries.add(ModItems.OBSIDIRITEBOOTS);

                        entries.add(ModItems.UNOBTIANNETHERITEAXE);
                        entries.add(ModItems.UNOBTIANNETHERITEHOE);
                        entries.add(ModItems.UNOBTIANNETHERITESWORD);
                        entries.add(ModItems.UNOBTIANNETHERITEPICKAXE);
                        entries.add(ModItems.UNOBTIANNETHERITESPADE);
                        entries.add(ModItems.UNOBTIANNETHERITEHELMET);
                        entries.add(ModItems.UNOBTIANNETHERITECHEST);
                        entries.add(ModItems.UNOBTIANNETHERITELEGS);
                        entries.add(ModItems.UNOBTIANNETHERITEBOOTS);


                        entries.add(ModItems.UNOBTIANOBSIDIRITEAXE);
                        entries.add(ModItems.UNOBTIANOBSIDIRITEHOE);
                        entries.add(ModItems.UNOBTIANOBSIDIRITESWORD);
                        entries.add(ModItems.UNOBTIANOBSIDIRITEPICKAXE);
                        entries.add(ModItems.UNOBTIANOBSIDIRITESPADE);
                        entries.add(ModItems.UNOBTIANOBSIDIRITEHELMET);
                        entries.add(ModItems.UNOBTIANOBSIDIRITECHEST);
                        entries.add(ModItems.UNOBTIANOBSIDIRITELEGS);
                        entries.add(ModItems.UNOBTIANOBSIDIRITEBOOTS);

                        //block
                        entries.add(ModBlocks.DEEPSLATE_DEBRI_ORE);
                        entries.add(ModBlocks.GRINDER_BLOCK);
                        entries.add(ModBlocks.BLOCK_FACTORY);
                        entries.add(ModBlocks.CHILLER);
                        entries.add(ModBlocks.NETHERITE_CRAFTER);
                        entries.add(ModBlocks.STONE_SHIFTER);
                        entries.add(ModBlocks.UNOBTANIUM_ORE);

                    }).build());


    public static void registerCreativeTab(){
        Simpeladd.LOGGER.info("Registering Creative Tab For " + Simpeladd.MOD_ID);
    }
}
