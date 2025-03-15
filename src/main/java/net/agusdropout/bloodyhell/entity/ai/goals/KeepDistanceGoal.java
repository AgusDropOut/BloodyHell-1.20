package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.custom.BlasphemousMalformationEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class KeepDistanceGoal extends Goal {
    private final BlasphemousMalformationEntity entity;
    private final double moveSpeed;
    private final double minDistance; // Distancia mínima antes de retroceder
    private final double maxDistance; // Distancia máxima antes de avanzar

    public KeepDistanceGoal(BlasphemousMalformationEntity entity, double moveSpeed, double minDistance, double maxDistance) {
        this.entity = entity;
        this.moveSpeed = moveSpeed;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = entity.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public void tick() {
        LivingEntity target = entity.getTarget();
        if (target == null) return;

        double distance = entity.distanceTo(target);

        if (distance < minDistance) {
            // Demasiado cerca, retroceder
            Vec3 direction = entity.position().subtract(target.position()).normalize().scale(1.5);
            Vec3 newPos = entity.position().add(direction);
            entity.getNavigation().moveTo(newPos.x, newPos.y, newPos.z, moveSpeed);
        } else if (distance > maxDistance) {
            // Demasiado lejos, avanzar
            entity.getNavigation().moveTo(target, moveSpeed);
        } else {
            // Mantenerse en posición
            entity.getNavigation().stop();
        }
    }
}