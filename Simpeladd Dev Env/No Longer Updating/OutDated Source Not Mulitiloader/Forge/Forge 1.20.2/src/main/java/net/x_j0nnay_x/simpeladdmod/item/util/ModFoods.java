package net.x_j0nnay_x.simpeladdmod.item.util;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties SANDWISH_PORK = new FoodProperties.Builder().nutrition(13)
            .saturationMod(18.8f).alwaysEat().meat().build();
    public static final FoodProperties SANDWISH_BEEF = new FoodProperties.Builder().nutrition(13)
            .saturationMod(18.8f).alwaysEat().meat().build();
    public static final FoodProperties SANDWISH_MUT = new FoodProperties.Builder().nutrition(11)
            .saturationMod(16.9f).alwaysEat().meat().build();
    public static final FoodProperties SANDWISH_CHECKIN = new FoodProperties.Builder().nutrition(11)
            .saturationMod(14.9f).alwaysEat().meat().build();

    public static final FoodProperties SANDWICH_MEET_LOVE = new FoodProperties.Builder().nutrition(27)
            .saturationMod(31.6f).alwaysEat().meat().build();
    public static final FoodProperties SANDWICH_MEET_LOVE_VEG = new FoodProperties.Builder().nutrition(30)
            .saturationMod(32.2f).alwaysEat().meat().build();
    public static final FoodProperties SANDWISH_VEG = new FoodProperties.Builder().nutrition(8)
            .saturationMod(8.2f).alwaysEat().meat().build();

}