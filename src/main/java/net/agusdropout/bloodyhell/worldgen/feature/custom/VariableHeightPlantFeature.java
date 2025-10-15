package net.agusdropout.bloodyhell.worldgen.feature.custom;

import com.mojang.serialization.Codec;
import net.agusdropout.bloodyhell.block.base.TallPlantBlock;
import net.agusdropout.bloodyhell.block.base.TallPlantPart;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class VariableHeightPlantFeature  extends Feature<NoneFeatureConfiguration> {



        private final TallPlantBlock plantBlock;

        public VariableHeightPlantFeature(Codec<NoneFeatureConfiguration> codec, TallPlantBlock plantBlock) {
            super(codec);
            this.plantBlock = plantBlock;
        }

        @Override
        public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
            WorldGenLevel level = context.level();
            BlockPos pos = context.origin();
            RandomSource random = context.random();


            int height = 2 + random.nextInt(6);


            for (int i = 0; i < height; i++) {
                BlockPos current = pos.above(i);
                TallPlantPart part;

                if (i == 0) {
                    part = TallPlantPart.BOTTOM;
                } else if (i == height - 1) {
                    part = TallPlantPart.TOP;
                } else {
                    part = TallPlantPart.MIDDLE;
                }

                BlockState stateToPlace = plantBlock.defaultBlockState().setValue(TallPlantBlock.PART, part);

                if (!stateToPlace.canSurvive(level, current)) {
                    return false;
                }

                level.setBlock(current, stateToPlace, 2);
            }

            return true;
        }


}
