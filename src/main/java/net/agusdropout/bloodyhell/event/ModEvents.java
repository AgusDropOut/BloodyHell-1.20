package net.agusdropout.bloodyhell.event;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.effect.ModEffects;
import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.*;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.agusdropout.bloodyhell.particle.custom.BloodParticles;
import net.agusdropout.bloodyhell.particle.custom.DirtyBloodFlowerParticle;
import net.agusdropout.bloodyhell.particle.custom.ImpactParticle;
import net.agusdropout.bloodyhell.particle.custom.LightParticle;
import net.agusdropout.bloodyhell.worldgen.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
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
            }


            @SubscribeEvent
            public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {


            }



            @SubscribeEvent
            public static void EntityAttributeEvent(EntityAttributeCreationEvent event) {
                event.put(BLOODTHIRSTYBEAST.get(), BloodThirstyBeastEntity.setAttributes());
                event.put(BLOOD_SEEKER.get(), BloodSeekerEntity.setAttributes());
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
            public static void PlayerTickEvent(TickEvent.PlayerTickEvent event){
                   // if(!event.player.level().isClientSide) {
                   //     if (event.player.level().dimension().equals(ModDimensions.SOUL_LEVEL_KEY)) {
                   //         if (!event.player.hasEffect(ModEffects.BLOOD_LUST.get())) {
                   //             if (!event.player.hasEffect(ModEffects.ILLUMINATED.get())) {
                   //                 MobEffectInstance bloodLust = new MobEffectInstance(ModEffects.BLOOD_LUST.get(), 100, 1);
                   //                 event.player.addEffect(bloodLust);
                   //             }
                   //         }
                   //     }
                   // }




            }


        }
}



  //  @SubscribeEvent
  //  public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
  //      if(event.side == LogicalSide.SERVER) {
  //          event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
  //              if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
  //                  thirst.subThirst(1);
  //                  ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), ((ServerPlayer) event.player));
  //              }
  //          });
  //      }
  //  }

