package net.agusdropout.firstmod.datagen;

import net.agusdropout.firstmod.FirstMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags {
    public static final TagKey<Biome> HAS_GIANT_BUDDHA = TagKey.create(Registries.BIOME, new ResourceLocation(FirstMod.MODID, "has_structure/giant_buddha"));

}
