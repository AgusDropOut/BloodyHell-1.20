package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.custom.OmenGazerEntity;
import net.agusdropout.bloodyhell.entity.projectile.SmallCrimsonDagger;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class OmenGazerSuicideGoal extends Goal {
    private final OmenGazerEntity omenGazer;
    private static final double SUICIDE_SPEED = 1.5; // Aumenta la velocidad
    private static final double EXPLOSION_RADIUS = 2.5; // Distancia para explotar

    public OmenGazerSuicideGoal(OmenGazerEntity omenGazer) {
        this.omenGazer = omenGazer;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.omenGazer.getTarget();
        return target != null && this.omenGazer.isAboutToExplode();
    }

    @Override
    public void tick() {
        LivingEntity target = this.omenGazer.getTarget();
        if (target != null) {
            // Moverse rápidamente hacia el objetivo
            this.omenGazer.getNavigation().moveTo(target, SUICIDE_SPEED);
            this.omenGazer.getLookControl().setLookAt(target, 30.0F, 30.0F);
            // Verificar si está lo suficientemente cerca para explotar
            if (this.omenGazer.distanceTo(target) < EXPLOSION_RADIUS) {
                explode();
            }
        }
    }

    private void explode() {
        if (!this.omenGazer.level().isClientSide) {
            this.omenGazer.level().explode(
                    this.omenGazer, // Entidad que explota
                    this.omenGazer.getX(),
                    this.omenGazer.getY(),
                    this.omenGazer.getZ(),
                    4.0F, // Fuerza de la explosión
                    false, // No destruye bloques
                    Level.ExplosionInteraction.MOB
            );
            this.omenGazer.discard(); // Eliminar la entidad tras explotar
        }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void stop() {
        this.omenGazer.setAboutToExplode(false);
    }
}