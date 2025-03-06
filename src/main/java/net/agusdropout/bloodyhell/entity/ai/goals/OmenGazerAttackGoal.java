package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.custom.OmenGazerEntity;
import net.agusdropout.bloodyhell.sound.ModSounds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class OmenGazerAttackGoal extends Goal {
    private final OmenGazerEntity omenGazer;
    private Path path;
    private static final double SPEED = 2.5; // Ajusta la velocidad de la embestida
    private int chargeTime; // Tiempo de embestida en ticks

    public OmenGazerAttackGoal(OmenGazerEntity omenGazer) {
        this.omenGazer = omenGazer;
    }


    @Override
    public void start() {

        LivingEntity target = this.omenGazer.getTarget();
        if (target != null) {
            this.omenGazer.getLookControl().setLookAt(target, 30.0F, 30.0F);
            omenGazer.getNavigation().stop();
            omenGazer.setAttackCooldown(100);
            omenGazer.setCharging(true);
            omenGazer.swing(InteractionHand.MAIN_HAND);
            // Dirección normalizada hacia el objetivo
            Vec3 direction = new Vec3(
                    target.getX() - omenGazer.getX(),
                    target.getY() - omenGazer.getY(),
                    target.getZ() - omenGazer.getZ()
            ).normalize().scale(SPEED);

            // Aplicar el impulso de la embestida
            this.omenGazer.setDeltaMovement(direction);

            // Definir la duración de la embestida (por ejemplo, 20 ticks = 1 segundo)
            this.chargeTime = 40;

        }
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.omenGazer.getTarget();

        if (target != null) {
            double distance = this.omenGazer.distanceTo(target);
            return distance <= 6.0 && !omenGazer.isThrowing() && omenGazer.getAttackCooldown() == 0
                    && !omenGazer.isAboutToExplode(); // Solo embiste si el objetivo está a 10 bloques o menos
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        // La embestida continúa hasta que el tiempo se acabe
        return chargeTime > 0;
    }

    @Override
    public void stop() {
        this.omenGazer.setCharging(false);
    }
    public boolean requiresUpdateEveryTick() {
        return true;
    }
    @Override
    public void tick() {
        if (chargeTime > 0) {
            chargeTime--;
            if (omenGazer.getTarget() != null) {
                omenGazer.getNavigation().stop();
                //this.path = this.omenGazer.getNavigation().createPath(omenGazer.getTarget(), 0);
                //this.omenGazer.getNavigation().moveTo(this.path, 1.5);
            }
            // Obtener todas las entidades dentro de la bounding box
            AABB hitbox = this.omenGazer.getBoundingBox().inflate(1.5);
            List<Entity> entitiesHit = this.omenGazer.level().getEntities(
                    this.omenGazer, hitbox, entity -> entity instanceof LivingEntity && entity != omenGazer
            );

            // Aplicar daño a cada entidad golpeada
            for (Entity entity : entitiesHit) {
                entity.hurt(this.omenGazer.damageSources().mobAttack(omenGazer), (float) 5);
            }
        } else {
            stop();
        }
    }
}
