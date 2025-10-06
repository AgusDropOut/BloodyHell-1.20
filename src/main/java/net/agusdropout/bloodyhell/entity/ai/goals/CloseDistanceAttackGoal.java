package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.custom.BlasphemousArmEntity;
import net.agusdropout.bloodyhell.entity.custom.OniEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CloseDistanceAttackGoal extends MeleeAttackGoal {
    private final BlasphemousArmEntity entity;
    private int delayTime;
    private final int maxDelayTime = 20;
    private boolean isChargingAttack = false;

    public CloseDistanceAttackGoal(BlasphemousArmEntity entity, double speed, boolean follow) {
        super(entity, speed, follow);
        this.entity = entity;
    }

    @Override
    public void start() {
        this.mob.setAggressive(true);
        this.delayTime = maxDelayTime;
        this.isChargingAttack = false;
    }

    @Override
    public void stop() {
        this.isChargingAttack = false;
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (entity.isValidTarget()) {
            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);


            if (!isChargingAttack && entity.isInAttackRange(target) && entity.getAttackCooldown() <= 0) {
                startAttack();
            }


            if (isChargingAttack) {
                if (delayTime > 0) {
                    delayTime--;
                } else {
                    applyDamage();
                    finishAttack();
                }
            }
        }
    }

    private void startAttack() {
        this.delayTime = maxDelayTime;
        this.isChargingAttack = true;

        entity.swing(InteractionHand.MAIN_HAND);
        entity.setIsAttacking(true);
    }

    private void applyDamage() {
                LivingEntity enemy = entity.getTarget();
                if (enemy == null) return;

                Vec3 selfPos = entity.position().add(0, 1.6f, 0);
                Vec3 enemyPos = enemy.getEyePosition().subtract(selfPos);
                Vec3 normalized = enemyPos.normalize();

                double knockbackX = 0.5 * (1 - enemy.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                double knockbackY = 2.5 * (1 - enemy.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));

                if (entity.distanceTo(enemy) <= 3 || enemy.onGround()) {
                    entity.doHurtTarget(enemy);
                    enemy.hurt(entity.damageSources().mobAttack(entity), 10.0F);
                    entity.heal(10.0F);
                    enemy.push(normalized.x * knockbackY,
                            normalized.y * knockbackX,
                            normalized.z * knockbackY);
                }
    }

    private void finishAttack() {
        this.isChargingAttack = false;
        entity.setAttackCooldown(40);
        entity.setIsAttacking(false);
    }
}
