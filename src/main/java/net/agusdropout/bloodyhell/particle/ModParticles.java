package net.agusdropout.bloodyhell.particle;

import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BloodyHell.MODID);

    public static final RegistryObject<SimpleParticleType> BLOOD_PARTICLES =
            PARTICLE_TYPES.register("blood_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> LIGHT_PARTICLES =
            PARTICLE_TYPES.register("light_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DIRTY_BLOOD_FLOWER_PARTICLE =
            PARTICLE_TYPES.register("dirty_blood_flower_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> IMPACT_PARTICLE =
            PARTICLE_TYPES.register("impact_particle", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }


}
