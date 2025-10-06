package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.custom.SelioraEntity;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class TeleportFarGoal extends Goal {
    private final SelioraEntity entity;
    private final Random random = new Random();

    public TeleportFarGoal(SelioraEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return entity.canTeleportFar(); // define este método en tu entidad igual que canTeleportNear()
    }

    @Override
    public void start() {
        LivingEntity target = entity.getTarget();
        if (target == null) return;

        Vec3 targetPos = target.position();
        Vec3 entityPos = entity.position();

        // Vector desde el target hacia la entidad (para alejarse en esa dirección)
        Vec3 awayDir = entityPos.subtract(targetPos).normalize();

        // Distancia base a la que se aleja (puedes ajustar)
        double distance = 10 + random.nextInt(5); // entre 15 y 25 bloques

        // Nueva posición tentativa lejos del target
        Vec3 farPos = entityPos.add(awayDir.scale(distance));

        // Agregamos algo de aleatoriedad para que no sea tan lineal
        farPos = farPos.add(
                (random.nextDouble() - 0.5) * 6.0, // ±3 bloques de desviación en X
                0,
                (random.nextDouble() - 0.5) * 6.0  // ±3 bloques en Z
        );

        BlockPos teleportPos = new BlockPos((int)farPos.x,(int) entity.getY(),(int) farPos.z);

        // Aseguramos que esté en el nivel del suelo
        teleportPos = findSafeTeleportPos(teleportPos);

        if (teleportPos == null) {
            return;
        }

        if (entity.level() instanceof ServerLevel serverLevel) {
            // Partículas de destino
            serverLevel.sendParticles(ModParticles.BLASPHEMOUS_MAGIC_RING.get(),
                    teleportPos.getX(), teleportPos.getY() + 0.1, teleportPos.getZ(),
                    1, 0.0, 0.0, 0.0, 0.0);

            // Teletransporte real
            entity.teleportTo(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());

            // Efecto visual post-teleport
            spawnBlasphemousParticles();

            // Sonido de teletransporte
            entity.level().playSound(null, entity.blockPosition(),
                    SoundEvents.ENDERMAN_TELEPORT, SoundSource.HOSTILE, 1.0F, 1.0F);
        }
        stop();
    }

    private BlockPos findSafeTeleportPos(BlockPos pos) {
        ServerLevel level = (ServerLevel) entity.level();

        for (int attempt = 0; attempt < 10; attempt++) {
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(
                    pos.getX() + level.random.nextIntBetweenInclusive(-3, 3),
                    pos.getY() + level.random.nextIntBetweenInclusive(-2, 2),
                    pos.getZ() + level.random.nextIntBetweenInclusive(-3, 3)
            );

            // Bajar hasta encontrar un bloque sólido
            while (mutable.getY() > level.getMinBuildHeight() && !level.getBlockState(mutable).blocksMotion()) {
                mutable.move(Direction.DOWN);
            }

            BlockState ground = level.getBlockState(mutable);
            if (!ground.blocksMotion() || ground.getFluidState().is(FluidTags.WATER)) continue;

            boolean safe = true;

            // 🔸 Chequear que el bloque base + 4 arriba estén libres
            for (int i = 1; i <= 4 && safe; i++) {
                if (!level.isEmptyBlock(mutable.above(i))) safe = false;
            }

            // 🔸 Chequear alrededores (9 bloques incluyendo diagonales)
            for (int dx = -1; dx <= 1 && safe; dx++) {
                for (int dz = -1; dz <= 1 && safe; dz++) {
                    BlockPos baseAround = mutable.offset(dx, 0, dz);
                    // Verificar 4 bloques hacia arriba también
                    for (int i = 1; i <= 4 && safe; i++) {
                        if (!level.isEmptyBlock(baseAround.above(i))) safe = false;
                    }
                }
            }

            if (safe) {
                // Devuelve la posición donde puede pararse (1 arriba del suelo)
                return mutable.above();
            }
        }

        // Si no encontró posición segura
        return null;
    }

    public void spawnBlasphemousParticles() {
        if (entity.level() instanceof ServerLevel serverLevel) {
            double radiusXZ = 2.0;
            double height = 1.5;
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


