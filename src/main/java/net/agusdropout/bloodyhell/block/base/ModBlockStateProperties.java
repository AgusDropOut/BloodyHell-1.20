package net.agusdropout.bloodyhell.block.base;

import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModBlockStateProperties {
    public static final EnumProperty<BloodScrapperPlantLeaves> BLOOD_LEAVES;
    public ModBlockStateProperties() {
    }

    static {
        BLOOD_LEAVES = EnumProperty.create("leaves", BloodScrapperPlantLeaves.class);
    }

}
