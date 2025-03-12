package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.custom.OffspringOfTheUnknownEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class FleeFromTargetGoal extends Goal {
    private final OffspringOfTheUnknownEntity entity;
    private static final double FLEE_SPEED = 1.2; // Velocidad al huir
    private static final int FLEE_TIME = 40; // Duración en ticks
    private int fleeTime;

    public FleeFromTargetGoal(OffspringOfTheUnknownEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return entity.getTarget() != null && entity.getHealth() < entity.getMaxHealth() * 0.3 && !entity.getIsAttacking(); // Huye si la vida es menor al 30%
    }

    @Override
    public void start() {
        fleeTime = FLEE_TIME;
    }

    @Override
    public void tick() {
        LivingEntity target = entity.getTarget();
        if (target == null) return;

        // Calcula la dirección contraria al objetivo
        double dx = entity.getX() - target.getX();
        double dz = entity.getZ() - target.getZ();
        Vec3 fleeDirection = new Vec3(dx, 0, dz).normalize().scale(FLEE_SPEED);

        // Aplica el movimiento en la dirección contraria
        entity.setDeltaMovement(fleeDirection.add(0, 0.1, 0)); // Un poco de altura para evitar trabarse

        // Rotar la entidad en la dirección de huida
        float yaw = (float) (Mth.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F;
        entity.setYRot(yaw);
        entity.yBodyRot = yaw;
        entity.yHeadRot = yaw;

        fleeTime--;
    }

    @Override
    public boolean canContinueToUse() {
        return fleeTime > 0 && entity.getTarget() != null;
    }

    @Override
    public void stop() {
        fleeTime = 0;
    }
}