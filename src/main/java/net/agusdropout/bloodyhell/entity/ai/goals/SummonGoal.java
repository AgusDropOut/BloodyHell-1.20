package net.agusdropout.bloodyhell.entity.ai.goals;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.agusdropout.bloodyhell.entity.custom.OniEntity;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class SummonGoal extends Goal {
    private OniEntity oni;
    public SummonGoal(OniEntity oni) {
        this.oni = oni;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
    }

    public boolean canContinueToUse() {
        return this.canUse();
    }

    @Override
    public void start() {
        this.oni.swing(InteractionHand.MAIN_HAND);
        oni.setCanSummonAttack(false);
        List<Player> targets =  oni.level().getEntitiesOfClass(Player.class,this.oni.getBoundingBox().inflate(32.0D, 16.0D, 32.0D));
        for (Player target : targets){
            BloodSeekerEntity bloodSeekerEntity = new BloodSeekerEntity(ModEntityTypes.BLOOD_SEEKER.get(),oni.level());
            oni.level().addFreshEntity(bloodSeekerEntity);
            bloodSeekerEntity.setPos(target.getX(),target.getY(),target.getZ());


        }
        super.start();

    }


    @Override
    public void stop() {
        oni.setCanSummonAttack(false);
        super.stop();

    }

    @Override
    public boolean canUse() {
        return oni.getPhase() == OniEntity.Phase.SUMMONING_ATTACK && oni.isCanSummonAttack();
    }



}
