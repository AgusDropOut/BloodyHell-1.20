package net.agusdropout.firstmod.worldgen.feature;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.block.ModBlocks;
import net.agusdropout.firstmod.worldgen.tree.custom.BloodTrunkPlacer;
import net.agusdropout.firstmod.worldgen.tree.custom.SoulTreeLeafDecorator;
import net.agusdropout.firstmod.worldgen.tree.custom.SoulTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {


    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SOUL_ORE_KEY = registerKey("overworld_soul_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> BLOOD_TREE_KEY = registerKey("blood_tree");
    public static final ResourceKey<ConfiguredFeature<?,?>> SOUL_TREE_KEY = registerKey("soul_tree");
    public static final ResourceKey<ConfiguredFeature<?,?>> BLOOD_FLOWER_KEY = registerKey("blood_flower");
    public static final ResourceKey<ConfiguredFeature<?,?>> BLOOD_BUSH_KEY = registerKey("blood_bush_flower");
    public static final ResourceKey<ConfiguredFeature<?,?>> BLOOD_SMALL_ROCKS_KEY = registerKey("blood_small_rocks");
    public static final ResourceKey<ConfiguredFeature<?,?>> BLOOD_PETALS_KEY = registerKey("blood_petals");
    public static final ResourceKey<ConfiguredFeature<?,?>> BLEEDING_BLOCK_KEY = registerKey("bleeding_block");



    public static final Supplier<List<OreConfiguration.TargetBlockState>> SOUL_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.soul_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_soul_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> BLEEDING_BLOCK = Suppliers.memoize(() -> List.of(
           OreConfiguration.target(new TagMatchTest(BlockTags.DIRT), ModBlocks.BLEEDING_BLOCK.get().defaultBlockState())));



    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);



        register(context, OVERWORLD_SOUL_ORE_KEY, Feature.ORE, new OreConfiguration(SOUL_ORES.get(),12));
        register(context, BLEEDING_BLOCK_KEY, Feature.ORE, new OreConfiguration(BLEEDING_BLOCK.get(),33));


        register(context,BLOOD_TREE_KEY,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BLOOD_LOG.get()),
                new BloodTrunkPlacer(1, 2, 2),
                BlockStateProvider.simple(ModBlocks.BLOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 2),
                new TwoLayersFeatureSize(1,0,2)).build()
        );
        register(context,SOUL_TREE_KEY,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SOUL_LOG.get()),
                new SoulTrunkPlacer(1, 2, 2),
                BlockStateProvider.simple(ModBlocks.SOUL_LEAVES.get()),
                new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1)),
                new TwoLayersFeatureSize(1,1,3)).decorators(ImmutableList.of(SoulTreeLeafDecorator.INSTANCE)).build()
        );
        context.register(BLOOD_FLOWER_KEY, new ConfiguredFeature<>(Feature.FLOWER, patch(ModBlocks.BLOOD_FLOWER.get(), 64)));
        context.register(BLOOD_BUSH_KEY, new ConfiguredFeature<>(Feature.FLOWER, patch(ModBlocks.BLOOD_BUSH.get(), 32)));
        context.register(BLOOD_SMALL_ROCKS_KEY, new ConfiguredFeature<>(Feature.FLOWER, patch(ModBlocks.BLOOD_SMALL_ROCKS.get(), 50)));
        context.register(BLOOD_PETALS_KEY, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(ModBlocks.BLOOD_PETALS.get(), 70)));



}

    private static RandomPatchConfiguration patch(Block block, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block))));
    }

    private static RandomPatchConfiguration patch(Block block, int tries, List<Block> whitelist) {
        return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), whitelist, tries);
    }



    private static RandomPatchConfiguration patch(BlockState block, int tries, List<Block> whitelist) {
        return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), whitelist, tries);
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(FirstMod.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
