package net.agusdropout.bloodyhell.effect;

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
        RandomSource random = entity.getRandom();

        // 💨 Movimiento errático
        if (random.nextFloat() < 0.5) {
            double dx = (random.nextDouble() - 0.5) * 0.5 * (amplifier + 1);
            double dz = (random.nextDouble() - 0.5) * 0.5 * (amplifier + 1);
            entity.setDeltaMovement(entity.getDeltaMovement().add(dx, 0, dz));
        }

        // 🤢 Vomito en la dirección en la que mira la entidad
        Vec3 lookVec = entity.getLookAngle(); // Obtiene la dirección en la que mira
        double speed = 0.2 + 0.1 * amplifier; // Velocidad de las partículas
        for (int i = 0; i < 5; i++) { // Varias partículas con leve dispersión
            entity.level().addParticle(
                    ModParticles.VICERAL_PARTICLE.get(),
                    entity.getX(), entity.getY() + 1.0, entity.getZ(), // Posición de la partícula
                    lookVec.x * speed + (random.nextDouble() - 0.5) * 0.1, // Dirección con dispersión
                    lookVec.y * speed + (random.nextDouble() - 0.2) * 0.1,
                    lookVec.z * speed + (random.nextDouble() - 0.5) * 0.1
            );
        }
        // 💥 Daño por segundo (DPS)
        if (entity.getHealth() > 1F && tick % 4 == 0){
            float damage = 0.1F + amplifier * 0.5F; // Ajusta el daño según el amplificador
            entity.hurt(entity.damageSources().magic(), damage);
        }

        // ⛔ Aplicar náusea solo una vez al inicio del efecto
        if (entity.getEffect(this) != null && entity.getEffect(this).getDuration() > 100 - amplifier * 20) {
            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100 + amplifier * 40, 0));
        }

        // 🎭 Animación de vómito en jugadores
        if (entity instanceof Player player) {
            player.swing(InteractionHand.MAIN_HAND);
            player.playSound(SoundEvents.BOAT_PADDLE_WATER, 1.0F, 1.0F);
        }
        tick++;
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Hacer que el efecto ocurra en cada tick
        return true;
    }
}
