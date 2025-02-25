package net.agusdropout.bloodyhell.item.custom;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.SanguineSacrificeEntity;
import net.agusdropout.bloodyhell.entity.custom.UnknownEntityArms;
import net.agusdropout.bloodyhell.entity.custom.UnknownEyeEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SacrificialDagger extends SwordItem {
    public SacrificialDagger(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity dealer) {
        if(!((Player)dealer).getCooldowns().isOnCooldown(this)){

            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 250, false, false));
            target.hasImpulse = false;
            if(dealer instanceof Player){
                ((Player) dealer).getCooldowns().addCooldown(this, 200);
            }


            // 2. Spawnea la entidad "Ojo Desconocido" encima del mob congelado
            SanguineSacrificeEntity unknownEye = new SanguineSacrificeEntity(target.level(),target.getX(), target.getY(), target.getZ(), target.getYRot(),0,1,dealer,target);
            unknownEye.moveTo(target.getX(), target.getY(), target.getZ(), target.getYRot(), target.getXRot());
            target.setDeltaMovement(0, 0, 0);
            target.level().addFreshEntity(unknownEye);

            // 3. Opcional: Efecto de partículas oscuras para darle más impacto
            ((ServerLevel) target.level()).sendParticles(ParticleTypes.SMOKE,
                    target.getX(), target.getY() + 0.5, target.getZ(),
                    20, 0.3, 0.3, 0.3, 0.01);

            // 4. Opcional: Sonido de absorción
            target.level().playSound(null, target.blockPosition(), SoundEvents.ENDERMAN_TELEPORT,
                    SoundSource.PLAYERS, 1.0F, 0.5F);

        }
        return super.hurtEnemy(itemStack, dealer, target);
    }

}

