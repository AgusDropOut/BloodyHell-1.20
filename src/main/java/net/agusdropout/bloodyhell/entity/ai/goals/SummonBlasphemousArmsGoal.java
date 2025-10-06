package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.BlasphemousArmEntity;
import net.agusdropout.bloodyhell.entity.custom.SelioraEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SummonBlasphemousArmsGoal extends Goal {
    private SelioraEntity entity;
    private int armsNumber;
    private int animationTicks;
    private int maxAnimationTicks;

    public SummonBlasphemousArmsGoal(SelioraEntity entity, int armsNumber) {
        this.entity = entity;
        this.armsNumber = armsNumber;
        this.maxAnimationTicks = entity.getRiseTicksDuration();
        this.animationTicks = entity.getRiseTicksDuration();
    }

    @Override
    public boolean canUse() {
        return entity.canUseSummonBlasphemousArmsGoal();
    }

    @Override
    public void start() {
        entity.setRiseActive(true);
        animationTicks = entity.getRiseTicksDuration();
    }

    @Override
    public void tick() {
        if(animationTicks == maxAnimationTicks) {
            LivingEntity target = entity.getTarget();
            if (target != null && target.isAlive()) {
                Level level = entity.level();
                for (int i = 0; i < armsNumber; i++) {
                    BlasphemousArmEntity arm = new BlasphemousArmEntity(ModEntityTypes.BLASPHEMOUS_ARM_ENTITY.get(), level, entity);
                    int xOffset = (int) (Math.random() * 10 - 5);
                    int zOffset = (int) (Math.random() * 10 - 5);
                    arm.setPos(target.getX() + xOffset, target.getY(), target.getZ() + zOffset);
                    level.addFreshEntity(arm);
                }
            }
            animationTicks--;
        }else if (animationTicks > 0) {
            LivingEntity target = entity.getTarget();
            if (target != null && target.isAlive()) {
                entity.lookAt(EntityAnchorArgument.Anchor.EYES, target.position());
            }
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
        entity.setRiseActive(false);
        entity.setRiseCooldown(entity.getRiseMaxCooldown());
    }
}
