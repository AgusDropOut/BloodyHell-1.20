package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.custom.OmenGazerEntity;
import net.agusdropout.bloodyhell.entity.projectile.SmallCrimsonDagger;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.minecraft.server.level.ServerLevel;

public class OmenGazerThrowGoal extends Goal {
    private final OmenGazerEntity omenGazer;
    private static final double THROW_SPEED = 1.2;
    private int throwCooldown = 20;


    public OmenGazerThrowGoal(OmenGazerEntity omenGazer) {
        this.omenGazer = omenGazer;
    }


    @Override
    public void start() {
        LivingEntity target = this.omenGazer.getTarget();
        if (target != null && !this.omenGazer.level().isClientSide) {
            omenGazer.setThrowingCooldown(120);
            omenGazer.setThrowing(true);
            omenGazer.swing(InteractionHand.MAIN_HAND);
            // Calcular la dirección hacia el objetivo
            Vec3 direction = new Vec3(
                    target.getX() - omenGazer.getX(),
                    target.getY() - omenGazer.getY(), // Apuntar al centro del objetivo
                    target.getZ() - omenGazer.getZ()
            ).normalize().scale(THROW_SPEED);

            // Crear la daga con su posición y velocidad
            SmallCrimsonDagger dagger = new SmallCrimsonDagger(
                    this.omenGazer.level(),
                    omenGazer.getX(), omenGazer.getY() + 1.5, omenGazer.getZ(), // Posición de la daga
                    direction.x, direction.y, direction.z // Velocidad (dirección escalada)
                    ,5F, omenGazer // Daño y propietario
            );

            // Agregar la daga al mundo
            ((ServerLevel) this.omenGazer.level()).addFreshEntity(dagger);


        }
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.omenGazer.getTarget();

        if (target != null) {
            return !omenGazer.isCharging() && omenGazer.getThrowingCooldown() == 0 && omenGazer.distanceTo(target) > 6 && omenGazer.distanceTo(target) < 12
                    && !omenGazer.isAboutToExplode();
        }
        return false;
    }

    @Override
    public void tick() {
        this.throwCooldown--;
        if(this.omenGazer.getTarget() != null ) {
            this.omenGazer.getLookControl().setLookAt(omenGazer.getTarget(), 30.0F, 30.0F);
        }
        if (this.throwCooldown <= 0) {
            stop();
        }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void stop() {
        this.omenGazer.setThrowing(false);
    }

}
