package net.agusdropout.bloodyhell.datagen;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.worldgen.ModBiomeModifiers;
import net.agusdropout.bloodyhell.worldgen.biome.ModBiomes;
import net.agusdropout.bloodyhell.worldgen.dimension.ModDimensions;
import net.agusdropout.bloodyhell.worldgen.feature.ModConfiguredFeatures;
import net.agusdropout.bloodyhell.worldgen.feature.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.BIOME, ModBiomes::boostrap)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(BloodyHell.MODID));
    }
}
