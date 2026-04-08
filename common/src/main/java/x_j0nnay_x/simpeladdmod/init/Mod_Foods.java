package x_j0nnay_x.simpeladdmod.init;

import net.minecraft.world.food.FoodProperties;

public class Mod_Foods {

    public static final FoodProperties FLESH = new FoodProperties.Builder().nutrition(4)
            .saturationModifier(0.3f).build();

    public static final FoodProperties SANDWICH_PORK = new FoodProperties.Builder().nutrition(13)
            .saturationModifier(1.8f).alwaysEdible().build();
    public static final FoodProperties SANDWICH_BEEF = new FoodProperties.Builder().nutrition(13)
            .saturationModifier(1.8f).alwaysEdible().build();
    public static final FoodProperties SANDWICH_MUT = new FoodProperties.Builder().nutrition(11)
            .saturationModifier(1.9f).alwaysEdible().build();
    public static final FoodProperties SANDWICH_CHICKEN = new FoodProperties.Builder().nutrition(11)
            .saturationModifier(1.9f).alwaysEdible().build();
    public static final FoodProperties SANDWICH_MEET_LOVE = new FoodProperties.Builder().nutrition(27)
            .saturationModifier(3.6f).alwaysEdible().build();
    public static final FoodProperties SANDWICH_MEET_LOVE_VEG = new FoodProperties.Builder().nutrition(30)
            .saturationModifier(4.2f).alwaysEdible().build();
    public static final FoodProperties SANDWICH_VEG = new FoodProperties.Builder().nutrition(8)
            .saturationModifier(0.9f).alwaysEdible().build();



    //beef 8/0.8
    //chicken 6/0.6
    //pork 8/0.8
    //mutton 6/0.8
    //veg 5/0.6
    //bread 5/0.6
}