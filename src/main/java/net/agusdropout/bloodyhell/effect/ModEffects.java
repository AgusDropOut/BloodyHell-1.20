package net.agusdropout.bloodyhell.effect;

import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BloodyHell.MODID);

    public static final RegistryObject<MobEffect> BLEEDING = MOB_EFFECTS.register("bleeding",
            () -> new BleedingEffect(MobEffectCategory.HARMFUL, 0xAA0000));
    public static final RegistryObject<MobEffect> ILLUMINATED = MOB_EFFECTS.register("illuminated",
            () -> new IlluminatedEffect(MobEffectCategory.BENEFICIAL, 0xFFE800));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
