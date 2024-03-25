package net.agusdropout.firstmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SoulItem extends SwordItem{
    private static final int BLEED_DURATION = 20 * 10; // Duración del efecto de sangrado en ticks (10 segundos)

    public SoulItem(Tier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Realiza la curación del jugador aquí
        if (attacker instanceof Player) {
            Player player = (Player) attacker;
            player.heal(2); // Ajusta la cantidad de curación según sea necesario
        }

        // Aplica el efecto de sangrado al enemigo
        applyBleedingEffect(target);

        // Llama al método en la superclase para gestionar el daño
        return super.hurtEnemy(stack, target, attacker);
    }

    private void applyBleedingEffect(LivingEntity target) {
        // Aplica el efecto de sangrado al enemigo
        MobEffectInstance bleedingEffect = new MobEffectInstance(MobEffects.WITHER, BLEED_DURATION, 1);
        MobEffectInstance slowingEffect = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, BLEED_DURATION, 1);
        target.addEffect(bleedingEffect);
        target.addEffect(slowingEffect);
    }
}
