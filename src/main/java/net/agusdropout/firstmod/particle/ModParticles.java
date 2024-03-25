package net.agusdropout.firstmod.particle;

import net.agusdropout.firstmod.FirstMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, FirstMod.MODID);

    public static final RegistryObject<SimpleParticleType> BLOOD_PARTICLES =
            PARTICLE_TYPES.register("blood_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> LIGHT_PARTICLES =
            PARTICLE_TYPES.register("light_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }


}
