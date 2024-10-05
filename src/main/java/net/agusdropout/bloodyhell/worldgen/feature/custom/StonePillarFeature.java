package net.agusdropout.bloodyhell.worldgen.feature.custom;

import com.mojang.serialization.Codec;
import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.block.custom.Droopvine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class StonePillarFeature extends Feature<NoneFeatureConfiguration>  {

    public StonePillarFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        LevelAccessor level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if (!level.isEmptyBlock(pos) || !level.getBlockState(pos.below()).is(ModBlocks.BLOOD_GRASS_BLOCK.get().defaultBlockState().getBlock())){
            return false;
        } else {
            level.setBlock(pos, ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
            level.setBlock(pos.above(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
            level.setBlock(pos.above().above(), ModBlocks.BLOODY_STONE_TILES_BLOCK.get().defaultBlockState(), 2);
            if(random.nextFloat() < 0.25){
                level.setBlock(pos.north(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos.west(), ModBlocks.BLOODY_STONE_SLAB.get().defaultBlockState(), 2);
                level.setBlock(pos.below().north(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos.below().south(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
            } else if(random.nextFloat() < 0.50){
                level.setBlock(pos.south(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos.east(), ModBlocks.BLOODY_STONE_SLAB.get().defaultBlockState(), 2);
                level.setBlock(pos.below().south(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos.below().north(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
            } else if (random.nextFloat() < 0.75){
                level.setBlock(pos.east(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos.south(), ModBlocks.BLOODY_STONE_SLAB.get().defaultBlockState(), 2);
                level.setBlock(pos.below().east(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos.below().west(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
            } else if (random.nextFloat() < 1.00){
                level.setBlock(pos.west(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos.north(), ModBlocks.BLOODY_STONE_SLAB.get().defaultBlockState(), 2);
                level.setBlock(pos.below().west(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos.below().east(), ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(), 2);
            }

            return true;
        }
    }


}
