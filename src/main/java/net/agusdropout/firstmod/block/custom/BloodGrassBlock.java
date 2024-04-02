package net.agusdropout.firstmod.block.custom;

import net.agusdropout.firstmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BloodGrassBlock extends SpreadingBloodSoilBlock implements BonemealableBlock {
    public BloodGrassBlock(Properties properties) {
        super(properties);
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();

        label48:
        for (int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for (int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!level.getBlockState(blockpos1.below()).is(this) || level.getBlockState(blockpos1).isCollisionShapeFullBlock(level, blockpos1)) {
                    continue label48;
                }
            }
            System.out.println("exitoo!!!");
            BlockState blockstate2 = level.getBlockState(blockpos1);
            if (blockstate2.is(deepturfOrShimmerweed(random).getBlock()) && random.nextInt(10) == 0) {
                ((BonemealableBlock) deepturfOrShimmerweed(random).getBlock()).performBonemeal(level, random, blockpos1, blockstate2);
            }

            if (blockstate2.isAir()) {
                if (deepturfOrShimmerweed(random).canSurvive(level, blockpos1)) {
                    level.setBlock(blockpos1, deepturfOrShimmerweed(random), 3);
                }
            }
        }
    }

    private static BlockState deepturfOrShimmerweed(RandomSource random) {
        if (random.nextInt(10) == 0) {
            return ModBlocks.BLOOD_FLOWER.get().defaultBlockState();
        } else return ModBlocks.BLOOD_GRASS.get().defaultBlockState();
    }
}
