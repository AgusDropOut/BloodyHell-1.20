package net.agusdropout.bloodyhell.worldgen.feature.custom;

import com.mojang.serialization.Codec;
import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.block.custom.Droopvine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DroopvineFeature extends Feature<NoneFeatureConfiguration>  {

    public DroopvineFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        LevelAccessor level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        if (!level.isEmptyBlock(pos)) {
            return false;
        } else {
            BlockState blockstate = level.getBlockState(pos.above());
            if (!blockstate.is(ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState().getBlock())) {
                return false;
            } else {
                this.placeRoofDroopvine(level, random, pos);
                return true;
            }
        }
    }

    private void placeRoofDroopvine(LevelAccessor level, RandomSource random, BlockPos pos) {
        BlockPos.MutableBlockPos posMutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i < 100; ++i) {
            posMutable.setWithOffset(pos, random.nextInt(8) - random.nextInt(8), random.nextInt(2) - random.nextInt(7), random.nextInt(8) - random.nextInt(8));
            if (level.isEmptyBlock(posMutable)) {
                BlockState blockstate = level.getBlockState(posMutable.above());
                if (blockstate.is(ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState().getBlock())) {
                    int length = Mth.nextInt(random, 1, 8);
                    if (random.nextInt(6) == 0) {
                        length *= 2;
                    }

                    if (random.nextInt(5) == 0) {
                        length = 1;
                    }

                    placeDroopvineColumn(level, random, posMutable, length, 17, 25);
                }
            }
        }
    }

    private static void placeDroopvineColumn(LevelAccessor level, RandomSource random, BlockPos.MutableBlockPos posMutable, int length, int min, int max) {
        for (int i = 0; i <= length; ++i) {
            if (level.isEmptyBlock(posMutable)) {
                if (i == length || !level.isEmptyBlock(posMutable.below())) {
                    level.setBlock(posMutable, ModBlocks.DROOPVINE.get().defaultBlockState().setValue(Droopvine.GLOWY, level.getRandom().nextBoolean()).setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, min, max)), 2);
                    break;
                }

                level.setBlock(posMutable, ModBlocks.DROOPVINE_PLANT.get().defaultBlockState().setValue(Droopvine.GLOWY, level.getRandom().nextBoolean()), 2);
            }

            posMutable.move(Direction.DOWN);
        }
    }
}
