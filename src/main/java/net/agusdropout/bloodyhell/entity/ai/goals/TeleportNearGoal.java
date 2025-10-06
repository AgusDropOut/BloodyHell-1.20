package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.SelioraEntity;
import net.agusdropout.bloodyhell.entity.effects.EntityFallingBlock;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.agusdropout.bloodyhell.sound.ModSounds;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

public class TeleportNearGoal extends Goal {
    private SelioraEntity entity;

    public TeleportNearGoal(SelioraEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return entity.canTeleportNear();
    }

    @Override
    public void start() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                Vec3 targetPos = target.position();
                int xOffset = (new Random().nextInt(7) - 3); // Random offset between -3 and 3
                int zOffset = (new Random().nextInt(7) - 3); // Random offset between -3 and 3
                BlockPos teleportPos = new BlockPos((int)targetPos.x + xOffset, (int) targetPos.y, (int) targetPos.z + zOffset);
                if (entity.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                            ModParticles.BLASPHEMOUS_MAGIC_RING.get(),
                            teleportPos.getX() ,
                            teleportPos.getY() + 0.1,
                            teleportPos.getZ() ,
                            1,   // cantidad
                            0.0, 0.0, 0.0,
                            0.0   // velocidad
                    );
                    serverLevel.sendParticles(
                            ModParticles.CYLINDER_PARTICLE.get(),
                            teleportPos.getX() ,
                            teleportPos.getY() + 0.1,
                            teleportPos.getZ() ,
                            1,   // cantidad
                            0.0, 0.0, 0.0,
                            0.0   // velocidad
                    );
                }
                entity.teleportTo(teleportPos.getX() , teleportPos.getY(), teleportPos.getZ() );
                spawnBlasphemousParticles();
                entity.level().playSound(null, this.entity.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.HOSTILE, 1.0F, 1.0F);

            }
            stop();

    }

    public void spawnBlasphemousParticles() {
        if (entity.level() instanceof ServerLevel serverLevel) {
            double radiusXZ = 2.0;
            double height = 1.5;
            Random random = new Random();
            for (int i = 0; i < 20; i++) {

                double offsetX = (random.nextDouble() - 0.5) * radiusXZ;
                double offsetY = random.nextDouble() * height;
                double offsetZ = (random.nextDouble() - 0.5) * radiusXZ;

               serverLevel.sendParticles(ModParticles.MAGIC_LINE_PARTICLE.get(),
                        entity.getX() + offsetX,
                        entity.getY() + offsetY,
                        entity.getZ() + offsetZ,
                        1,
                        0.0, 0.05 + random.nextDouble() * 0.05, 0.0,
                        0.0
                );
            }
        }
    }




    @Override
    public void stop() {
        entity.setTeleportCooldown(entity.getTeleportMaxCooldown());
    }


}
