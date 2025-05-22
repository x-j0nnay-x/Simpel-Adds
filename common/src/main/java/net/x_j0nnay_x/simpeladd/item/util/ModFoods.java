package net.x_j0nnay_x.simpeladd.item.util;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties FLESH = new FoodProperties.Builder().nutrition(8)
            .saturationModifier(4.9f).build();
    public static final FoodProperties SANDWISH_PORK = new FoodProperties.Builder().nutrition(13)
            .saturationModifier(18.8f).alwaysEdible().build();

    public static final FoodProperties SANDWISH_BEEF = new FoodProperties.Builder().nutrition(13)
            .saturationModifier(18.8f).alwaysEdible().build();

    public static final FoodProperties SANDWISH_MUT = new FoodProperties.Builder().nutrition(11)
            .saturationModifier(16.9f).alwaysEdible().build();

    public static final FoodProperties SANDWISH_CHECKIN = new FoodProperties.Builder().nutrition(11)
            .saturationModifier(14.9f).alwaysEdible().build();

    public static final FoodProperties SANDWICH_MEET_LOVE = new FoodProperties.Builder().nutrition(27)
            .saturationModifier(31.6f).alwaysEdible().build();

    public static final FoodProperties SANDWICH_MEET_LOVE_VEG = new FoodProperties.Builder().nutrition(30)
            .saturationModifier(32.2f).alwaysEdible().build();

    public static final FoodProperties SANDWISH_VEG = new FoodProperties.Builder().nutrition(8)
            .saturationModifier(8.2f).alwaysEdible().build();
}