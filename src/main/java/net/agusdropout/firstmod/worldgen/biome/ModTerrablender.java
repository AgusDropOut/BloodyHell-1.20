package net.agusdropout.firstmod.worldgen.biome;

import net.agusdropout.firstmod.FirstMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(FirstMod.MODID, "overworld"), 5));
    }
}
