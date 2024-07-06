package net.x_j0nnay_x.simpeladdmod.item.util;


import net.minecraft.component.type.FoodComponent;


public class ModFoods {
    public static final FoodComponent SANDWISH_PORK = new FoodComponent.Builder().nutrition(13)
            .saturationModifier(18.8f).alwaysEdible().build();
    public static final FoodComponent SANDWISH_BEEF = new FoodComponent.Builder().nutrition(13)
            .saturationModifier(18.8f).alwaysEdible().build();
    public static final FoodComponent SANDWISH_MUT = new FoodComponent.Builder().nutrition(11)
            .saturationModifier(16.9f).alwaysEdible().build();
    public static final FoodComponent SANDWISH_CHECKIN = new FoodComponent.Builder().nutrition(11)
            .saturationModifier(14.9f).alwaysEdible().build();

    public static final FoodComponent SANDWICH_MEET_LOVE = new FoodComponent.Builder() .nutrition(27)
            .saturationModifier(31.6f).alwaysEdible().build();
    public static final FoodComponent SANDWICH_MEET_LOVE_VEG = new FoodComponent.Builder().nutrition(30)
            .saturationModifier(32.2f).alwaysEdible().build();
    public static final FoodComponent SANDWISH_VEG = new FoodComponent.Builder().nutrition(8)
            .saturationModifier(8.2f).alwaysEdible().build();

}