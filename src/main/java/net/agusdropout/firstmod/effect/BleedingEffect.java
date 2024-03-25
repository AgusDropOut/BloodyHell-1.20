package net.agusdropout.firstmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class BleedingEffect extends MobEffect {
    public BleedingEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    public static final UUID MOVEMENT_SPEED_MODIFIER_UUID = UUID.fromString("EC6F9365-42AA-4D06-AB22-36D799177F32");
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {

        if (!entity.level().isClientSide()) {
            // Aplicar daño al jugador
            float damageAmount = 0.4f;
            entity.hurt(entity.damageSources().magic(),damageAmount);
            float speedModifier = 2;

            this.addAttributeModifier(Attributes.MOVEMENT_SPEED,MOVEMENT_SPEED_MODIFIER_UUID.toString() , -0.15D, AttributeModifier.Operation.MULTIPLY_TOTAL);



        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Devuelve true para que el efecto se aplique en cada tick
        return true;
    }
}
