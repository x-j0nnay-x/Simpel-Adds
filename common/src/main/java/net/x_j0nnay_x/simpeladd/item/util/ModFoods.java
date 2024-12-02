package net.x_j0nnay_x.simpeladd.item.util;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties FLESH = new FoodProperties.Builder().nutrition(4)
            .saturationMod(0.3f).meat().build();

    public static final FoodProperties SANDWISH_PORK = new FoodProperties.Builder().nutrition(13)
            .saturationMod(1.8f).alwaysEat().meat().build();
    public static final FoodProperties SANDWISH_BEEF = new FoodProperties.Builder().nutrition(13)
            .saturationMod(1.8f).alwaysEat().meat().build();
    public static final FoodProperties SANDWISH_MUT = new FoodProperties.Builder().nutrition(11)
            .saturationMod(1.9f).alwaysEat().meat().build();
    public static final FoodProperties SANDWISH_CHECKIN = new FoodProperties.Builder().nutrition(11)
            .saturationMod(1.9f).alwaysEat().meat().build();

    public static final FoodProperties SANDWICH_MEET_LOVE = new FoodProperties.Builder().nutrition(27)
            .saturationMod(3.6f).alwaysEat().meat().build();
    public static final FoodProperties SANDWICH_MEET_LOVE_VEG = new FoodProperties.Builder().nutrition(30)
            .saturationMod(4.2f).alwaysEat().meat().build();
    public static final FoodProperties SANDWISH_VEG = new FoodProperties.Builder().nutrition(8)
            .saturationMod(0.9f).alwaysEat().meat().build();
    //beef 8/0.8
    //chicken 6/0.6
    //pork 8/0.8
    //mutton 6/0.8
    //veg 5/0.6
    //bread 5/0.6
}