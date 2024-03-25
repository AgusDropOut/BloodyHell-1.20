package net.agusdropout.firstmod.block.custom;

import net.agusdropout.firstmod.effect.ModEffects;
import net.agusdropout.firstmod.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BleedingBlock extends Block {


    public BleedingBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState state, Entity entity) {
        if(entity instanceof LivingEntity LivingEntity){
            LivingEntity.addEffect(new MobEffectInstance(ModEffects.BLEEDING.get(), 200));
        }
        

        super.stepOn(level, blockPos, state, entity);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {


        for (int i = 0; i < 4; ++i) {
            double x = (double) pos.getX() + random.nextDouble();
            double y = (double) pos.getY() + random.nextDouble();
            double z = (double) pos.getZ() + random.nextDouble();
            double xSpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
            double ySpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
            double zSpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
            int j = random.nextInt(2) * 2 - 1;
            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                x = (double) pos.getX() + 0.5D + 0.25D * (double) j;
                xSpeed = random.nextFloat() * 2.0F * (float) j;
            } else {
                z = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
                zSpeed = random.nextFloat() * 1.0F * (float) j;
            }

            level.addParticle(ModParticles.BLOOD_PARTICLES.get(), x, y, z, xSpeed, ySpeed, zSpeed);
        }



        super.animateTick(state, level, pos, random);
    }
}
