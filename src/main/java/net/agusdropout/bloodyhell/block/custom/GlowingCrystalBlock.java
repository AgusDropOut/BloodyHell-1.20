package net.agusdropout.bloodyhell.block.custom;

import net.agusdropout.bloodyhell.block.base.BaseFacingAnyDirectionBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GlowingCrystalBlock extends BaseFacingAnyDirectionBlock {

    public GlowingCrystalBlock(Properties properties) {
        super(properties);
    }
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {

        if(randomSource.nextFloat() < 0.15f) {
            level.playLocalSound(blockPos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 0.1f, 0.1f, true);
        }


        super.animateTick(blockState, level, blockPos, randomSource);
    }
}
