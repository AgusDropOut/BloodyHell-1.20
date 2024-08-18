package net.agusdropout.bloodyhell.worldgen.biome;


import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(BloodyHell.MODID, "overworld"), 5));
    }
}
