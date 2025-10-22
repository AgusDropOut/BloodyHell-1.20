package net.agusdropout.bloodyhell.particle;

import com.mojang.serialization.Codec;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.particle.ParticleOptions.SimpleBlockParticleOptions;
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
    public static final RegistryObject<SimpleParticleType> BLASPHEMOUS_MAGIC_RING =
            PARTICLE_TYPES.register("blasphemous_magic_ring", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MAGIC_LINE_PARTICLE =
            PARTICLE_TYPES.register("magic_particle_line", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MAGIC_SIMPLE_LINE_PARTICLE =
            PARTICLE_TYPES.register("magic_simple_particle_line", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLASPHEMOUS_BIOME_PARTICLE =
            PARTICLE_TYPES.register("blasphemous_biome_particle", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> SLASH_PARTICLE =
            PARTICLE_TYPES.register("slash_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> VICERAL_PARTICLE =
            PARTICLE_TYPES.register("viceral_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CYLINDER_PARTICLE =
            PARTICLE_TYPES.register("cylinder_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> STAR_EXPLOSION_PARTICLE =
            PARTICLE_TYPES.register("star_explosion_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> MAGIC_WAVE_PARTICLE =
            PARTICLE_TYPES.register("magic_wave_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> CYCLOPS_HALO_PARTICLE =
            PARTICLE_TYPES.register("cyclops_halo_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> EYE_PARTICLE =
            PARTICLE_TYPES.register("eye_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleBlockParticleOptions>> SIMPLE_BLOCK_PARTICLE =
            PARTICLE_TYPES.register("simple_block_particle", () ->
                    new ParticleType<SimpleBlockParticleOptions>(false, SimpleBlockParticleOptions.DESERIALIZER) {
                        @Override
                        public Codec<SimpleBlockParticleOptions> codec() {
                            return SimpleBlockParticleOptions.CODEC;
                        }
                    });

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }


}
