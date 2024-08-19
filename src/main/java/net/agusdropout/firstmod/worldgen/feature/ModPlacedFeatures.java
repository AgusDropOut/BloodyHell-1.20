package net.agusdropout.firstmod.worldgen.feature;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {


    public static final ResourceKey<PlacedFeature> SOUL_PLACED_KEY = createKey("soul_placed");

    public static final ResourceKey<PlacedFeature> BLOOD_TREE_PLACED_KEY = createKey("blood_tree_placed");
    public static final ResourceKey<PlacedFeature> GIANT_BLOOD_TREE_PLACED_KEY = createKey("giant_blood_tree_placed");
    public static final ResourceKey<PlacedFeature> SOUL_TREE_PLACED_KEY = createKey("soul_tree_placed");
    public static final ResourceKey<PlacedFeature> SMALL_BLOOD_TREE_PLACED_KEY = createKey("small_blood_tree_placed");

    public static final ResourceKey<PlacedFeature> BLOOD_FLOWER_PLACED_KEY = createKey("blood_flower_placed");
    public static final ResourceKey<PlacedFeature> LIGHT_MUSHROOM_PLACED_KEY = createKey("light_mushroom_placed");
    public static final ResourceKey<PlacedFeature> BLOOD_GRASS_PLACED_KEY = createKey("blood_grass_placed");
    public static final ResourceKey<PlacedFeature> BLOOD_BUSH_PLACED_KEY = createKey("blood_bush_placed");
    public static final ResourceKey<PlacedFeature> BLOOD_SMALL_ROCKS_PLACED_KEY = createKey("blood_small_rocks_placed");
    public static final ResourceKey<PlacedFeature> BLOOD_PETALS_PLACED_KEY = createKey("blood_petals_placed");
    public static final ResourceKey<PlacedFeature> BLEEDING_BLOCK_PLACED_KEY = createKey("bleeding_block_placed");
    public static final ResourceKey<PlacedFeature> BLOOD_LIQUID_PLACED_KEY = createKey("blood_liquid_placed");
    public static final ResourceKey<PlacedFeature> BLOOD_LILY_BLOCK_PLACED_KEY = createKey("blood_lily_block_placed");



    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


        register(context, SOUL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SOUL_ORE_KEY),
                commonOrePlacement(12, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, BLEEDING_BLOCK_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLEEDING_BLOCK_KEY),
                commonOrePlacement(24, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(80))));
        register(context, BLOOD_LIQUID_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_LIQUID_KEY),
                commonOrePlacement(24, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(80))));
        register(context,BLOOD_TREE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.1f,2), ModBlocks.BLOOD_SAPLING.get()));
        register(context,GIANT_BLOOD_TREE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.GIANT_BLOOD_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.1f,2), ModBlocks.GIANT_BLOOD_SAPLING.get()));
        register(context,SMALL_BLOOD_TREE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.SMALL_BLOOD_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.1f,2), ModBlocks.SMALL_BLOOD_SAPLING.get()));
        register(context,SOUL_TREE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.SOUL_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1,0.1f,1), ModBlocks.SOUL_SAPLING.get()));
        register(context, BLOOD_SMALL_ROCKS_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_SMALL_ROCKS_KEY),patch(50));
        register(context, BLOOD_FLOWER_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_FLOWER_KEY),patch(64));
        register(context, LIGHT_MUSHROOM_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.LIGHT_MUSHROOM_KEY),patch(64));
        register(context, BLOOD_GRASS_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_GRASS_KEY),patch(100));
        register(context, BLOOD_BUSH_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_BUSH_KEY),patch(64));
        register(context, BLOOD_PETALS_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_PETALS_KEY),patch(70));
        register(context, BLOOD_LILY_BLOCK_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_LILY_KEY),patch(70));

    }

    private static List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(FirstMod.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
