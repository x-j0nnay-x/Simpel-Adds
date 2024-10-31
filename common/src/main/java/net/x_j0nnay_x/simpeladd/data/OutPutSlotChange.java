package net.x_j0nnay_x.simpeladd.data;

import net.minecraft.util.StringRepresentable;

public enum  OutPutSlotChange implements StringRepresentable {

    COBBLE("cobble"),
    GRAVLE("gravle"),
    SAND("sand"),
    OBSIDIAN("obsidian"),
    DISABLE("disable");

    private final String mode;

    private OutPutSlotChange(String mode) {
        this.mode = mode;
    }

    public static OutPutSlotChange valueOf(int mode) {
        switch (mode) {
            case 0:
                return COBBLE;
            case 1:
                return GRAVLE;
            case 2:
                return SAND;
            case 3:
                return OBSIDIAN;
            case 4:
                return DISABLE;
            default:
                return DISABLE;
        }
    }

    public String getSerializedName() {
        return this.mode;
    }
}
