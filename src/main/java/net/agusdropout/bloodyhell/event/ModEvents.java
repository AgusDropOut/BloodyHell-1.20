package net.agusdropout.bloodyhell.event;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.CrimsonveilPower.PlayerCrimsonVeil;
import net.agusdropout.bloodyhell.CrimsonveilPower.PlayerCrimsonveilProvider;
import net.agusdropout.bloodyhell.client.render.BloodDimensionRenderInfo;
import net.agusdropout.bloodyhell.entity.custom.*;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.agusdropout.bloodyhell.particle.custom.*;
import net.agusdropout.bloodyhell.worldgen.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static net.agusdropout.bloodyhell.entity.ModEntityTypes.*;


public class ModEvents {

        @Mod.EventBusSubscriber(modid = BloodyHell.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ModEventBusEvents {
            @SubscribeEvent
            public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
                Minecraft.getInstance().particleEngine.register(ModParticles.BLOOD_PARTICLES.get(), BloodParticles.Provider::new);
                event.registerSpriteSet(ModParticles.BLOOD_PARTICLES.get(), BloodParticles.Provider::new);
                Minecraft.getInstance().particleEngine.register(ModParticles.LIGHT_PARTICLES.get(), LightParticle.Provider::new);
                event.registerSpriteSet(ModParticles.LIGHT_PARTICLES.get(), LightParticle.Provider::new);
                Minecraft.getInstance().particleEngine.register(ModParticles.DIRTY_BLOOD_FLOWER_PARTICLE.get(), DirtyBloodFlowerParticle.Provider::new);
                event.registerSpriteSet(ModParticles.DIRTY_BLOOD_FLOWER_PARTICLE.get(), DirtyBloodFlowerParticle.Provider::new);
                Minecraft.getInstance().particleEngine.register(ModParticles.IMPACT_PARTICLE.get(), ImpactParticle.Provider::new);
                event.registerSpriteSet(ModParticles.IMPACT_PARTICLE.get(), ImpactParticle.Provider::new);
                Minecraft.getInstance().particleEngine.register(ModParticles.SLASH_PARTICLE.get(), SlashParticle.Provider::new);
                event.registerSpriteSet(ModParticles.SLASH_PARTICLE.get(), SlashParticle.Provider::new);
            }


            @SubscribeEvent
            public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {


            }
            private void RegisterDimensionSpecialEffectsEvent(RegisterDimensionSpecialEffectsEvent event) {
                event.register(ModDimensions.DIMENSION_RENDERER, new BloodDimensionRenderInfo(128.0F, false, DimensionSpecialEffects.SkyType.NONE, false, false));
            }




            @SubscribeEvent
            public static void EntityAttributeEvent(EntityAttributeCreationEvent event) {
                event.put(BLOODTHIRSTYBEAST.get(), BloodThirstyBeastEntity.setAttributes());
                event.put(BLOOD_SEEKER.get(), BloodSeekerEntity.setAttributes());
                event.put(BLOODY_SOUL_ENTITY.get(), BloodySoulEntity.setAttributes());
                event.put(CORRUPTED_BLOODY_SOUL_ENTITY.get(), CorruptedBloodySoulEntity.setAttributes());
                event.put(CRIMSON_RAVEN.get(), CrimsonRavenEntity.setAttributes());
                event.put(EYESHELL_SNAIL.get(), EyeshellSnailEntity.setAttributes());
                event.put(SCARLETSPECKLED_FISH.get(), ScarletSpeckledFishEntity.setAttributes());
                event.put(BLOODPIG.get(), BloodPigEntity.setAttributes());
                event.put(ONI.get(), OniEntity.setAttributes());
                event.put(VESPER.get(), VesperEntity.setAttributes());


            }

            @SubscribeEvent
            public static void commonSetup(FMLCommonSetupEvent event) {
                event.enqueueWork(() -> {



                });


            }
        }
        @Mod.EventBusSubscriber(modid = BloodyHell.MODID)
        public static class ForgeEvents{
            @SubscribeEvent
            public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
                if(event.getObject() instanceof Player) {
                    if(!event.getObject().getCapability(PlayerCrimsonveilProvider.PLAYER_CRIMSONVEIL).isPresent()) {
                        event.addCapability(new ResourceLocation(BloodyHell.MODID, "properties"), new PlayerCrimsonveilProvider());
                    }
                }
            }

            @SubscribeEvent
            public static void onPlayerCloned(PlayerEvent.Clone event) {
                if(event.isWasDeath()) {
                    event.getOriginal().reviveCaps();
                    System.out.println("a");
                    event.getOriginal().getCapability(PlayerCrimsonveilProvider.PLAYER_CRIMSONVEIL).ifPresent(oldStore -> {
                        event.getEntity().getCapability(PlayerCrimsonveilProvider.PLAYER_CRIMSONVEIL).ifPresent(newStore -> {
                            System.out.println("k");
                            newStore.copyFrom(oldStore);
                        });
                    });
                    event.getOriginal().invalidateCaps();
                }
            }

            @SubscribeEvent
            public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
                event.register(PlayerCrimsonVeil.class);
            }
            private void RegisterDimensionSpecialEffectsEvent(RegisterDimensionSpecialEffectsEvent event) {
                event.register(ModDimensions.DIMENSION_RENDERER, new BloodDimensionRenderInfo(128.0F, false, DimensionSpecialEffects.SkyType.NONE, false, false));
            }






        }
}





