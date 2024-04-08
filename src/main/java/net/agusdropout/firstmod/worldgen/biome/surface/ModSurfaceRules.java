package net.agusdropout.firstmod.worldgen.biome.surface;


import net.agusdropout.firstmod.block.ModBlocks;
import net.agusdropout.firstmod.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;


public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource BLOOD_DIRT_BLOCK = makeStateRule(ModBlocks.BLOOD_DIRT_BLOCK.get());
    private static final SurfaceRules.RuleSource BLOOD_GRASS_BLOCK = makeStateRule(ModBlocks.BLOOD_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource SOUL_BLOCK = makeStateRule(ModBlocks.soul_BLOCK.get());





    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource aLittleBelowWaterLevel = SurfaceRules.abovePreliminarySurface();

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, BLOOD_GRASS_BLOCK), BLOOD_DIRT_BLOCK);
        SurfaceRules.RuleSource belowGrassSurface = SurfaceRules.ifTrue(aLittleBelowWaterLevel, BLOOD_DIRT_BLOCK);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BLOOD_BIOME),



                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(aLittleBelowWaterLevel, grassSurface)))
                        //SurfaceRules.ifTrue(aLittleBelowWaterLevel, belowGrassSurface))
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }



}