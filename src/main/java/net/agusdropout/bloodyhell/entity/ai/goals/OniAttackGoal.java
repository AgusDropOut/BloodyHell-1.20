package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.agusdropout.bloodyhell.entity.custom.OniEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class OniAttackGoal extends MeleeAttackGoal {
    private OniEntity oni;
    public OniAttackGoal(OniEntity oni, double p_25553_, boolean p_25554_) {
        super(oni, p_25553_, p_25554_);
        this.oni = oni;
    }


    protected void checkAndPerformAttack(LivingEntity p_25557_, double p_25558_) {
        double d0 = this.getAttackReachSqr(p_25557_);
        if (p_25558_ <= d0 && super.getTicksUntilNextAttack() <= 0) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            List<LivingEntity> list = oni.level().getEntitiesOfClass(LivingEntity.class, oni.getBoundingBox().inflate(6));
            for (LivingEntity enemy : list) {
                if(enemy instanceof Player) {


                    Vec3 selfPos = oni.position().add(0, 1.6f, 0);
                    Vec3 enemyPos = enemy.getEyePosition().subtract(selfPos);
                    Vec3 normalizedDirection = enemyPos.normalize();

                    double knockbackX = 0.5 * (1 - enemy.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    double knockbackY = 2.5 * (1 - enemy.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));

                    double distanceFromEnemy = oni.distanceTo(enemy);
                    boolean canDamage = true;
                    if (distanceFromEnemy > 3 && !enemy.onGround()) canDamage = false;

                    if (canDamage) {
                        oni.doHurtTarget(enemy);
                        oni.heal(10.0F);
                        enemy.push(normalizedDirection.x() * knockbackY, normalizedDirection.y() * knockbackX, normalizedDirection.z() * knockbackY);
                    }
                }
            }
        }
    }
}
