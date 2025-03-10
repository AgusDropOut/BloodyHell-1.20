package net.agusdropout.bloodyhell.effect;

import net.agusdropout.bloodyhell.datagen.ModTags;
import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class VisceralEffect extends MobEffect {
    private int tick = 0;
    public VisceralEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {

        if(entity.getType().is(ModTags.Entities.INMUNE_TO_VISCERAL_EFFECT)){
            return;
        }
        if(entity instanceof Player player){
            if(player.getInventory().contains(ModItems.BLASPHEMOUS_RING.get().getDefaultInstance())){
                return;
            }
        }


        RandomSource random = entity.getRandom();


        if (random.nextFloat() < 0.20) {
            double dx = (random.nextDouble() - 0.5) * 0.5 * (amplifier + 1);
            double dz = (random.nextDouble() - 0.5) * 0.5 * (amplifier + 1);
            entity.setDeltaMovement(entity.getDeltaMovement().add(dx, 0, dz));
        }


        Vec3 lookVec = entity.getLookAngle();
        double speed = 0.2 + 0.1 * amplifier;
        for (int i = 0; i < 5; i++) {
            entity.level().addParticle(
                    ModParticles.VICERAL_PARTICLE.get(),
                    entity.getX(), entity.getY() + 1.0, entity.getZ(),
                    lookVec.x * speed + (random.nextDouble() - 0.5) * 0.1,
                    lookVec.y * speed + (random.nextDouble() - 0.2) * 0.1,
                    lookVec.z * speed + (random.nextDouble() - 0.5) * 0.1
            );
        }

        if (entity.getHealth() > 1F && tick % 4 == 0){
            float damage = 0.1F + amplifier * 0.5F;
            entity.hurt(entity.damageSources().magic(), damage);
        }


        if (entity.getEffect(this) != null && tick == 1) {
            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100 + amplifier * 40, 0));
        }



        if (entity instanceof Player player) {
            player.swing(InteractionHand.MAIN_HAND);
            player.playSound(SoundEvents.BOAT_PADDLE_WATER, 1.0F, 1.0F);
        }
        tick++;
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
