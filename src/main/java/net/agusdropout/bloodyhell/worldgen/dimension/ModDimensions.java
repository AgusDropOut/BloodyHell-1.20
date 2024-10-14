package net.agusdropout.bloodyhell.worldgen.dimension;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.OptionalLong;


public class ModDimensions {
    public static final ResourceKey<LevelStem> SOUL_DIMENSION_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(BloodyHell.MODID, "soul_dimension"));
    public static final ResourceKey<Level> SOUL_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(BloodyHell.MODID, "soul_dimension"));
    public static final ResourceKey<DimensionType> SOUL_DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(BloodyHell.MODID, "soul_dimension"));
    public static final ResourceKey<NoiseGeneratorSettings> SOUL_DIMENSION_NOISE_GEN = ResourceKey.create(Registries.NOISE_SETTINGS,
            new ResourceLocation(BloodyHell.MODID, "soul_dimension"));


    private static ResourceLocation name(String name) {
        return new ResourceLocation(BloodyHell.MODID, name);
    }


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(SOUL_DIMENSION_TYPE, new DimensionType(
                OptionalLong.of(20000), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                4.0D, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                128, // height
                128, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                0.05f, // ambientLight
                new DimensionType.MonsterSettings(true, false, UniformInt.of(0, 7), 7)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
        context.register(SOUL_DIMENSION_KEY, new LevelStem(dimTypes.getOrThrow(SOUL_DIMENSION_TYPE),
                new NoiseBasedChunkGenerator(ModBiomes.buildBiomeSource(biomeRegistry), noiseGenSettings.getOrThrow(SOUL_DIMENSION_NOISE_GEN))));
        //NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
        //        new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.BLOOD_BIOME)),
        //        noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));
    }
    public static void bootstrapNoise(BootstapContext<NoiseGeneratorSettings> context) {

        HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noises = context.lookup(Registries.NOISE);
        DensityFunction densityfunction = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_X);
        DensityFunction densityfunction1 = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_Z);

        context.register(SOUL_DIMENSION_NOISE_GEN, new NoiseGeneratorSettings(
                NoiseSettings.create(0, 128, 2, 2),
                ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                new NoiseRouter(
                        DensityFunctions.zero(), //barrier
                        DensityFunctions.zero(), //fluid level floodedness
                        DensityFunctions.zero(), //fluid level spread
                        DensityFunctions.zero(), //lava
                        DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, noises.getOrThrow(Noises.TEMPERATURE)), //temperature
                        DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, noises.getOrThrow(Noises.VEGETATION)), //vegetation
                        NoiseRouterData.getFunction(functions, NoiseRouterData.CONTINENTS), //continents
                        NoiseRouterData.getFunction(functions, NoiseRouterData.EROSION), //erosion
                        DensityFunctions.rangeChoice(
                                NoiseRouterData.getFunction(functions, NoiseRouterData.Y),
                                0.0D,
                                32.0D,
                                DensityFunctions.constant(2.0D),
                                DensityFunctions.constant(-2.0D)), //depth
                        NoiseRouterData.getFunction(functions, NoiseRouterData.RIDGES), //ridges
                        DensityFunctions.zero(), //initial density
                        DensityFunctions.mul(
                                DensityFunctions.constant(0.64D),
                                DensityFunctions.interpolated(
                                        DensityFunctions.blendDensity(
                                                DensityFunctions.add(
                                                        DensityFunctions.constant(2.5D),
                                                        DensityFunctions.mul(
                                                                DensityFunctions.yClampedGradient(-8, 24, 0.0D, 1.0D),
                                                                DensityFunctions.add(
                                                                        DensityFunctions.constant(-2.5D),
                                                                        DensityFunctions.add(
                                                                                DensityFunctions.constant(0.5D),
                                                                                DensityFunctions.mul(
                                                                                        DensityFunctions.yClampedGradient(110, 128, 1.0D, 0.0D),
                                                                                        DensityFunctions.add(
                                                                                                DensityFunctions.constant(-0.5F),
                                                                                                BlendedNoise.createUnseeded(0.1D, 0.3D, 80.0D, 60.0D, 1.0D))
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        ).squeeze(), //final density
                        DensityFunctions.zero(), //vein toggle
                        DensityFunctions.zero(), //vein ridged
                        DensityFunctions.zero() //vein gap
                ),
                SurfaceRules.sequence(

                        //bedrock floor
                        SurfaceRules.ifTrue(SurfaceRules.verticalGradient("minecraft:bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
                        //filler depthrock
                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(110),1),SurfaceRules.state(Blocks.AIR.defaultBlockState())),
                        //SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0), SurfaceRules.state(ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState())),
                        //sediment
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR), SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(33), 0)), SurfaceRules.state(ModBlocks.BLOOD_DIRT_BLOCK.get().defaultBlockState()))),
                        ////mix coarse deepsoil into blood bog
                        //SurfaceRules.ifTrue(
                        //        SurfaceRules.isBiome(ModBiomes.BLOOD_BIOME),
                        //        SurfaceRules.ifTrue(
                        //                SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.CEILING),
                        //                SurfaceRules.sequence(
                        //                        SurfaceRules.ifTrue(
                        //                                SurfaceRules.noiseCondition(noises.getOrThrow(Noises.NETHER_STATE_SELECTOR).key(), 0.0D, 1.8D),
                        //                                SurfaceRules.state(ModBlocks.BLOOD_GRASS_BLOCK.get().defaultBlockState())
                        //                        ),
                        //                        SurfaceRules.ifTrue(
                        //                                SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                        //                                SurfaceRules.state(ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState())
                        //                        )
                        //                )
                        //        )
                        //),
                        //mix coarse deepsoil into smog spires
                       // SurfaceRules.ifTrue(
                       //         SurfaceRules.isBiome(UGBiomes.SMOG_SPIRES),
                       //         SurfaceRules.ifTrue(
                       //                 SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
                       //                 SurfaceRules.sequence(
                       //                         SurfaceRules.ifTrue(
                       //                                 SurfaceRules.noiseCondition(noises.getOrThrow(Noises.NETHER_STATE_SELECTOR).key(), 0.0D, 1.8D),
                       //                                 SurfaceRules.state(ModBlocks.BLOOD_GRASS_BLOCK.get().defaultBlockState())
                       //                         ),
                       //                         SurfaceRules.ifTrue(
                       //                                 SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                       //                                 SurfaceRules.state(ModBlocks.BLOOD_DIRT_BLOCK.get().defaultBlockState())
                       //                         )
                       //                 )
                       //         )
                       // ),
                        //mix coarse deepsoil into barren biomes
                        //SurfaceRules.ifTrue(
                        //        SurfaceRules.isBiome(UGBiomes.BARREN_ABYSS, UGBiomes.DEAD_SEA),
                        //        SurfaceRules.ifTrue(
                        //                SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
                        //                SurfaceRules.sequence(
                        //                        SurfaceRules.ifTrue(
                        //                                SurfaceRules.noiseCondition(noises.getOrThrow(Noises.NETHER_STATE_SELECTOR).key(), 0.0D, 1.8D),
                        //                                SurfaceRules.state(UGBlocks.COARSE_DEEPSOIL.get().defaultBlockState())
                        //                        ),
                        //                        SurfaceRules.ifTrue(
                        //                                SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                        //                                SurfaceRules.state(UGBlocks.DEPTHROCK.get().defaultBlockState())
                        //                        ),
                        //                        SurfaceRules.state(UGBlocks.DEPTHROCK.get().defaultBlockState())
                        //                )
                        //        )
                        //),
                        //mix powder snow into icy biomes
                        //SurfaceRules.ifTrue(
                        //        SurfaceRules.isBiome(UGBiomes.FROSTFIELDS, UGBiomes.ICY_SEA, UGBiomes.FROSTY_SMOGSTEM_FOREST),
                        //        SurfaceRules.ifTrue(
                        //                SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
                        //                SurfaceRules.sequence(
                        //                        SurfaceRules.ifTrue(
                        //                                SurfaceRules.noiseCondition(noises.getOrThrow(Noises.POWDER_SNOW).key(), 0.45D, 0.58D),
                        //                                SurfaceRules.state(Blocks.POWDER_SNOW.defaultBlockState())
                        //                        ),
                        //                        SurfaceRules.ifTrue(
                        //                                SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                        //                                SurfaceRules.state(UGBlocks.FROZEN_DEEPTURF_BLOCK.get().defaultBlockState())
                        //                        )
                        //                )
                        //        )
                        //),
                        //cover the ground in deepturf
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR), SurfaceRules.state(ModBlocks.BLOOD_GRASS_BLOCK.get().defaultBlockState())),
                        //add deepsoil underneath
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(ModBlocks.BLOOD_GRASS_BLOCK.get().defaultBlockState()))
                        //add shiverstone to icy biomes
                        //SurfaceRules.ifTrue(SurfaceRules.isBiome(UGBiomes.FROSTFIELDS, UGBiomes.ICY_SEA, UGBiomes.FROSTY_SMOGSTEM_FOREST), SurfaceRules.state(UGBlocks.SHIVERSTONE.get().defaultBlockState()))
                ),
                List.of(), //spawn targets
                32,
                false,
                false,
                false,
                false
        ));
    }


}



