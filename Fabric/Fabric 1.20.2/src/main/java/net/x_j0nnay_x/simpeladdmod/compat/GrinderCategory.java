package net.x_j0nnay_x.simpeladdmod.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.x_j0nnay_x.simpeladdmod.Simpeladd;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;

import java.util.LinkedList;
import java.util.List;
public class GrinderCategory implements DisplayCategory<BasicDisplay>{

    public static final Identifier TEXTURE =
            new Identifier(Simpeladd.MOD_ID, "textures/screens/grinder_gui.png");
    public static final CategoryIdentifier<GrinderDisplay> GRINDER =
            CategoryIdentifier.of(Simpeladd.MOD_ID, "grinder");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return GRINDER;
    }

    @Override
    public Text getTitle() {
        return Text.literal("Grinder");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.GRINDER_BLOCK.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 34, startPoint.y + 44))
                .entries(display.getInputEntries().get(0)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 124, startPoint.y + 44))
                .markOutput().entries(display.getOutputEntries().get(0)));


        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}