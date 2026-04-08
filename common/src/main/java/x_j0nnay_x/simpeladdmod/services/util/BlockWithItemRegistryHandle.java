package x_j0nnay_x.simpeladdmod.services.util;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public record BlockWithItemRegistryHandle<T extends Block>(
        RegistryHandle<T> block,
        RegistryHandle<? extends BlockItem> item
) {
}