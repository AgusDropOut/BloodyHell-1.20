package net.agusdropout.bloodyhell.block.base;

import net.minecraft.util.StringRepresentable;

public enum TallPlantPart implements StringRepresentable {
    BOTTOM("root"),
    MIDDLE("stem"),
    TOP("top");

    private final String name;

    TallPlantPart(String name) { this.name = name; }

    @Override
    public String getSerializedName() {
        return name;
    }
}