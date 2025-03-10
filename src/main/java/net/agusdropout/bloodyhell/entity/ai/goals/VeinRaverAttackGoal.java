package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.custom.VeinraverEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class VeinRaverAttackGoal extends MeleeAttackGoal {
    VeinraverEntity veinraverEntity;
    public VeinRaverAttackGoal(PathfinderMob mob, double p_25553_, boolean p_25554_) {
        super(mob, p_25553_, p_25554_);
        this.veinraverEntity = (VeinraverEntity) mob;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && !veinraverEntity.isSlamAttack() && !veinraverEntity.isSlashAttack() && !veinraverEntity.isThrowing();
    }
}
