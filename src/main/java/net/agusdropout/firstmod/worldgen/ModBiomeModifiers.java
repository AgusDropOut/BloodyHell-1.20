package net.agusdropout.firstmod.worldgen;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.worldgen.feature.ModPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_SOUL_ORE = registerKey("add_soul_ore");

    public static final ResourceKey<BiomeModifier> ADD_BLOOD_TREE = registerKey("add_blood_tree");
    public static final ResourceKey<BiomeModifier> ADD_BLOOD_FLOWER = registerKey("add_blood_flower");



    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);






    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(FirstMod.MODID, name));
    }
}
