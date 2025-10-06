package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.custom.SelioraEntity;
import net.agusdropout.bloodyhell.entity.projectile.BlasphemousWhirlwindEntity;
import net.agusdropout.bloodyhell.entity.projectile.StarfallProjectile;
import net.agusdropout.bloodyhell.sound.ModSounds;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WhirlwindGoal extends Goal {
        private SelioraEntity entity;
        private int animationTicks;

        public WhirlwindGoal(SelioraEntity entity) {
            this.entity = entity;
            this.animationTicks = entity.getWhirlWindTicksDuration();
        }

        @Override
        public boolean canUse() {
            return entity.canUseWhirlwind();
        }

        @Override
        public void start() {
            entity.setWhirlWindActive(true);
            animationTicks = entity.getWhirlWindTicksDuration();
            Level level = entity.level();
            LivingEntity target = entity.getTarget();
            if (target != null) {
                entity.level().playSound(null, this.entity.blockPosition(), ModSounds.SELIORA_LULLABY_SOUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                BlasphemousWhirlwindEntity whirlwindEntity = new BlasphemousWhirlwindEntity(level,
                        entity.getX(), entity.getY() + 1, entity.getZ(), entity);
                level.addFreshEntity(whirlwindEntity);
            }
        }

        @Override
        public void tick() {

            if (animationTicks > 0) {
                entity.setDeltaMovement(Vec3.ZERO);
                animationTicks--;
            } else {
                stop();
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void stop() {
            this.animationTicks = entity.getWhirlWindTicksDuration();
            entity.setWhirlWindActive(false);
            entity.setWhirlWindCooldown(entity.getWhirlWindMaxCooldown());
        }
}
