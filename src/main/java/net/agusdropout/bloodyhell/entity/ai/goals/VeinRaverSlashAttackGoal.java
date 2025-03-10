package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.BloodSlashEntity;
import net.agusdropout.bloodyhell.entity.custom.VeinraverEntity;
import net.agusdropout.bloodyhell.entity.effects.EntityFallingBlock;
import net.agusdropout.bloodyhell.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class VeinRaverSlashAttackGoal extends Goal {

    private final VeinraverEntity veinraverEntity;

    private int goalTime;
    private BlockPos targetPos;

    public VeinRaverSlashAttackGoal(VeinraverEntity veinraverEntity) {
        this.veinraverEntity = veinraverEntity;
    }


    @Override
    public void start() {
        veinraverEntity.setSlashAttack(true);
        veinraverEntity.getNavigation().stop();
        this.goalTime = 50;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.veinraverEntity.getTarget();
        if (target != null) {
            double distance = this.veinraverEntity.distanceTo(target);
            return distance > 4.0 && distance < 9 && !veinraverEntity.isThrowing() && !veinraverEntity.isSlashAttack() && !veinraverEntity.isSlamAttack() && veinraverEntity.getSlashAttackCooldown() == 0;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return goalTime > 0
                && veinraverEntity.getTarget() != null;
    }

    @Override
    public void stop() {
        super.stop();
        this.veinraverEntity.setSlashAttack(false);
        this.veinraverEntity.setSlashAttackCooldown(120);
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }
    @Override
    public void tick() {
        goalTime--;
        if (goalTime > 0) {
            if (veinraverEntity.getTarget() != null) {
                veinraverEntity.getNavigation().stop();
            }
            if(goalTime == 5){
                performSlashAttack();
            }

        } else {
            stop();
        }
    }
    public void performSlashAttack() {
        if(veinraverEntity.getTarget() != null) {
            this.targetPos = veinraverEntity.getTarget().blockPosition().below();
        }
        veinraverEntity.lookAt(veinraverEntity.getTarget(), 30.0F, 30.0F);
        this.veinraverEntity.level().playSound(null, this.veinraverEntity.blockPosition(),
                ModSounds.VEINRAVER_SLASH.get(), SoundSource.HOSTILE, 1.0F, 1.0F);

        if (targetPos == null) return;


        Vec3 startPos = this.veinraverEntity.position().add(0, 1.5, 0);
        Vec3 targetVec = Vec3.atCenterOf(targetPos);
        Vec3 direction = targetVec.subtract(startPos).normalize();


        float yaw = (float) (Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90);
        float pitch = (float) Math.toDegrees(-Math.asin(direction.y));


        BloodSlashEntity bloodSlash = new BloodSlashEntity(
                this.veinraverEntity.level(),
                startPos.x, startPos.y, startPos.z,
                6.0f,
                this.veinraverEntity,
                yaw, pitch,
                (float) direction.x, (float) direction.y, (float) direction.z, true
        );

        this.veinraverEntity.level().addFreshEntity(bloodSlash);
    }
}
