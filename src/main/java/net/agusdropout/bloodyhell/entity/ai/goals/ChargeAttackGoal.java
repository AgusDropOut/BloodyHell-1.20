package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.SelioraEntity;
import net.agusdropout.bloodyhell.entity.effects.EntityFallingBlock;
import net.agusdropout.bloodyhell.sound.ModSounds;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ChargeAttackGoal extends Goal {
    private SelioraEntity entity;
    private int chargeTicks;
    private boolean hasCharged = false;
    private int minimunChargeTicks = 60;

    public ChargeAttackGoal(SelioraEntity entity) {
        this.entity = entity;
        this.chargeTicks = entity.getChargeAttackChargeTicks();
    }

    @Override
    public boolean canUse() {
        return entity.canUseChargeAttack();
    }

    @Override
    public void start() {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                //entity.setStarFallActive(true);
                //entity.level().playSound(null, this.entity.blockPosition(), ModSounds.SELIORA_THROW_SOUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                entity.setChargeAttackActive(true);
            }
    }

    @Override
    public void tick() {
        if( chargeTicks > 0) {
            entity.setDeltaMovement(Vec3.ZERO);
            LivingEntity target = entity.getTarget();
            if (target != null && target.isAlive()) {
                entity.lookAt(EntityAnchorArgument.Anchor.EYES, target.position());
            }
            chargeTicks--;
        } else if (chargeTicks == 0 && !hasCharged) {
            LivingEntity target = entity.getTarget();
            if (target != null) {
                Vec3 targetDirection = target.position().subtract(entity.position()).normalize();
                entity.setDeltaMovement(targetDirection.scale(4));
                entity.level().playSound(null, entity.getOnPos(), ModSounds.SELIORA_CHARGE_ATTACK_SOUND.get(),
                        SoundSource.HOSTILE, 1.5F, 0.9F + entity.level().random.nextFloat() * 0.2F);
            }
            hasCharged = true;
        } else if (this.minimunChargeTicks > 0) {
                doAttackDamage();
                minimunChargeTicks--;
        } else {
            stop();
        }
    }

    @Override
    public void stop() {
        entity.setChargeAttackActive(false);
        hasCharged = false;
        minimunChargeTicks = 20;
        chargeTicks = entity.getChargeAttackChargeTicks();
        entity.setChargeAttackCooldown(entity.getChargeAttackMaxCooldown());
    }

    public void doAttackDamage() {
        Level level = entity.level();
        if (level.isClientSide) return;

        double radius = 2; // radio de efecto
        double damage = 20;

        BlockPos centerPos = entity.blockPosition();

        // --- 1. Daño a enemigos cercanos y knockback ---
        for (LivingEntity e : level.getEntitiesOfClass(LivingEntity.class,
                entity.getBoundingBox().inflate(radius),
                e -> e != entity && e.isAlive())) {
            e.hurt(entity.damageSources().mobAttack(entity), (float) damage);

            // Empuja un poco al target en dirección de la carga
            Vec3 pushDir = entity.position().subtract(e.position()).normalize();
            e.push(pushDir.x * 0.5, 0.3, pushDir.z * 0.5);
        }

        // --- 2. Partículas de bloque “volando” ---
        if (level instanceof ServerLevel server) {
            BlockState belowBlock = level.getBlockState(centerPos.below());
            server.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, belowBlock),
                    entity.getX(), entity.getY() + 0.1, entity.getZ(),
                    15, 0.5, 0.2, 0.5, 0.1);

            // Partículas tipo barrido de ataque
            server.sendParticles(ParticleTypes.SWEEP_ATTACK,
                    entity.getX(), entity.getY() + 1.0, entity.getZ(),
                    10, 0.5, 0.1, 0.5, 0.0);

            // Partículas brillantes de energía
            server.sendParticles(ParticleTypes.END_ROD,
                    entity.getX(), entity.getY() + 1.0, entity.getZ(),
                    20, 0.5, 0.2, 0.5, 0.05);
        }

        // --- 3. Efecto de bloques falling block radial (opcional) ---
        spawnRadialFallingBlocks(level, centerPos);

        // --- 4. Sonido de impacto ---
       // level.playSound(null, centerPos,
       //         ModSounds.SELIORA_CHARGE_HIT.get(), SoundSource.HOSTILE,
       //         1.2F, 0.9F + level.random.nextFloat() * 0.2F);
    }

    // Método auxiliar para spawn radial de bloques tipo FallingBlock
    private void spawnRadialFallingBlocks(Level level, BlockPos impactPos) {
        Random random = new Random();
        int maxRadius = 2;

        for (int r = 0; r <= maxRadius; r++) {
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    if (Math.abs(dx) != r && Math.abs(dz) != r) continue;

                    BlockPos pos = impactPos.offset(dx, 0, dz);
                    BlockState blockState = level.getBlockState(pos);

                    if (blockState.isAir()) continue;

                    float baseVelocity = 0.1f + 0.1f * r;
                    float velocity = baseVelocity + (random.nextFloat() * 0.1f - 0.05f);

                    EntityFallingBlock fb = new EntityFallingBlock(ModEntityTypes.ENTITY_FALLING_BLOCK.get(),
                            level, blockState, velocity);
                    fb.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
                    fb.setDuration(30 + random.nextInt(10));

                    level.addFreshEntity(fb);
                }
            }
        }
    }


    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }



}
