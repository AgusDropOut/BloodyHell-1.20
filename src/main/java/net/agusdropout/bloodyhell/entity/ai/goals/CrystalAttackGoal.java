package net.agusdropout.bloodyhell.entity.ai.goals;

import com.google.common.collect.ImmutableMap;
import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.CrystalPillar;
import net.agusdropout.bloodyhell.entity.custom.OniEntity;
import net.agusdropout.bloodyhell.entity.custom.UnknownEyeEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;


import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrystalAttackGoal extends Goal {
    private static final int DURATION = Mth.ceil(22.4F);
    private static final int MAX_DURATION = 50;

    private void createPillar(OniEntity mob, int index, float damage, float damageLowerRate, double d, double e, double f, double g, float h) {
        BlockPos blockPos = BlockPos.containing(d, g, e);
        boolean bl = false;
        double j = 0.0;
        do {
            VoxelShape voxelShape;
            BlockPos blockPos2 = blockPos.below();
            BlockState blockState = mob.level().getBlockState(blockPos2);
            if (!blockState.isFaceSturdy(mob.level(), blockPos2, Direction.UP)) continue;
            if (!mob.level().isEmptyBlock(blockPos) && !(voxelShape = mob.level().getBlockState(blockPos).getCollisionShape(mob.level(), blockPos)).isEmpty()) {
                j = voxelShape.max(Direction.Axis.Y);
            }
            bl = true;
            break;
        } while ((blockPos = blockPos.below()).getY() >= Mth.floor(f) - 1);
        if (bl) {
            if (damageLowerRate > 0) damage -= index * damageLowerRate;
            mob.level().addFreshEntity(new UnknownEyeEntity(mob.level(), d, (double)blockPos.getY() + j, e, h, index, damage, mob));
        }
    }

    private final OniEntity oni;

    @SuppressWarnings("this-escape")
    public CrystalAttackGoal(OniEntity oni) {
        this.oni = oni;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
    }

    public boolean canContinueToUse() {
        return this.canUse();
    }

    @Override
    public void start() {
        this.oni.swing(InteractionHand.MAIN_HAND);
        oni.setCanCrystalAttack(false);
        super.start();
    }


    @Override
    public void stop() {
        oni.setCanCrystalAttack(false);
        super.stop();

    }

    @Override
    public boolean canUse() {
        return oni.getPhase() == OniEntity.Phase.CRYSTAL_ATTACK && oni.isCanCrystalAttack();
    }

    @Override
    public void tick() {
        List<Player> targets =  oni.level().getEntitiesOfClass(Player.class,this.oni.getBoundingBox().inflate(32.0D, 16.0D, 32.0D));
        for(Player target : targets){
            if (this.oni.getSensing().hasLineOfSight(target) && !target.isCreative()) {
                double d = Math.min(target.getY(), oni.getY());
                double e = Math.max(target.getY(), oni.getY()) + 1.0;
                float f = (float)Mth.atan2(target.getZ() - oni.getZ(), target.getX() - oni.getX());
                if (oni.distanceTo(target) < 4) {
                    oni.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(-1, -1, -1));
                    float g;
                    int index;
                    for (index = 0; index < 5; ++index) {
                        g = f + (float)index * (float)Math.PI * 0.4f;
                        this.createPillar(oni,0, 4, 0, oni.getX() + (double)Mth.cos(g) * 1.5, oni.getZ() + (double)Mth.sin(g) * 1.5, d, e, g);
                    }
                    for (index = 0; index < 8; ++index) {
                        g = f + (float)index * (float)Math.PI * 2.0f / 8.0f + 1.2566371f;
                        this.createPillar(oni,3, 4, 0, oni.getX() + (double)Mth.cos(g) * 2.5, oni.getZ() + (double)Mth.sin(g) * 2.5, d, e, g);
                    }
                } else {
                    oni.lookAt(EntityAnchorArgument.Anchor.EYES, target.position());
                    for (int index = 0; index < 16; ++index) {
                        double h = 1.25 * (double)(index + 1);
                        this.createPillar(oni, index, 6, 0.2F,oni.getX() + (double)Mth.cos(f) * h + ((oni.getRandom().nextFloat() - 0.5F) * 0.4F), oni.getZ() + (double)Mth.sin(f) * h + ((oni.getRandom().nextFloat() - 0.5F) * 0.4F), d, e, f);
                    }
                }
            }

        }


    }


}
