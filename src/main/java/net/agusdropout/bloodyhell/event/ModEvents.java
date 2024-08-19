package net.agusdropout.bloodyhell.event;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.*;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.agusdropout.bloodyhell.particle.custom.BloodParticles;
import net.agusdropout.bloodyhell.particle.custom.DirtyBloodFlowerParticle;
import net.agusdropout.bloodyhell.particle.custom.LightParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
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
            }

            @SubscribeEvent
            public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {


            }
           //@SubscribeEvent
           //public static void onLivingHurt(LivingHurtEvent event){
           //    if(event.getEntity() instanceof Player){
           //
           //    }
           //}

            @SubscribeEvent
            public static void EntityAttributeEvent(EntityAttributeCreationEvent event) {
                event.put(BLOODTHIRSTYBEAST.get(), BloodThirstyBeastEntity.setAttributes());
                event.put(BLOOD_SEEKER.get(), BloodSeekerEntity.setAttributes());
                event.put(CRIMSON_RAVEN.get(), CrimsonRavenEntity.setAttributes());
                event.put(EYESHELL_SNAIL.get(), EyeshellSnailEntity.setAttributes());
                event.put(SCARLETSPECKLED_FISH.get(), ScarletSpeckledFishEntity.setAttributes());
                event.put(BLOODPIG.get(), BloodPigEntity.setAttributes());


            }

            @SubscribeEvent
            public static void commonSetup(FMLCommonSetupEvent event) {
                event.enqueueWork(() -> {


                });

            }
        }}


       // @SubscribeEvent
       // public static void addCustomTrades(VillagerTradesEvent event) {
       //     if (event.getType() == VillagerProfession.TOOLSMITH) {
       //         Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
       //         ItemStack stack = new ItemStack(ModItems.Eight_ball.get(), 1);
       //         int villagerLevel = 1;
//
       //         trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
       //                 new ItemStack(Items.EMERALD, 2),
       //                 stack, 10, 8, 0.02F));
       //     }
       //     if (event.getType() == ModVillagers.JUMP_MASTER.get()) {
       //         Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
       //         ItemStack stack = new ItemStack(ModItems.Eyeball.get(), 1);
       //         int villagerLevel = 1;
//
       //         trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
       //                 new ItemStack(Items.EMERALD, 2),
       //                 stack, 10, 8, 0.02F));
       //     }
       // }
  //      @SubscribeEvent
  //      public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
  //          if (event.getObject() instanceof Player) {
  //              if (!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
  //                  event.addCapability(new ResourceLocation(BloodyHell.MODID, "properties"), new PlayerThirstProvider());
  //              }
  //          }
  //      }
//
//
//
  //      @SubscribeEvent
  //      public static void onPlayerCloned(PlayerEvent.Clone event) {
  //          if(event.isWasDeath()) {
  //              event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
  //                  event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
  //                      newStore.copyFrom(oldStore);
  //                  });
  //              });
  //          }
  //      }
//
  //      @SubscribeEvent
  //      public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
  //          event.register(PlayerThirst.class);
  //      }
//
  //      @SubscribeEvent
  //      public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
  //          if(event.side == LogicalSide.SERVER) {
  //              event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
  //                  if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
  //                      thirst.subThirst(1);
  //                      ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), ((ServerPlayer) event.player));
  //                  }
  //              });
  //          }
  //      }
  //      @SubscribeEvent
  //      public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
  //          if(!event.getLevel().isClientSide()) {
  //              if(event.getEntity() instanceof ServerPlayer player) {
  //                  player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
  //                      ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
  //                  });
  //              }
  //          }
  //      }
//
  //  }

  //  @SubscribeEvent
  //  public static void addCustomTrades(VillagerTradesEvent event) {
       // if (event.getType() == VillagerProfession.TOOLSMITH) {
       //     Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
       //     ItemStack stack = new ItemStack(ModItems.Eight_ball.get(), 1);
       //     int villagerLevel = 1;
//
       //     trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
       //             new ItemStack(Items.EMERALD, 2),
       //             stack, 10, 8, 0.02F));
       // }
       // if (event.getType() == ModVillagers.JUMP_MASTER.get()) {
       //     Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
       //     ItemStack stack = new ItemStack(ModItems.Eyeball.get(), 1);
       //     int villagerLevel = 1;
//
       //     trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
       //             new ItemStack(Items.EMERALD, 2),
       //             stack, 10, 8, 0.02F));
       // }

  //  @SubscribeEvent
  //  public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
  //      if (event.getObject() instanceof Player) {
  //          if (!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
  //              event.addCapability(new ResourceLocation(BloodyHell.MODID, "properties"), new PlayerThirstProvider());
  //          }
  //      }
  //  }
//
//
//
  //  @SubscribeEvent
  //  public static void onPlayerCloned(PlayerEvent.Clone event) {
  //      if(event.isWasDeath()) {
  //          event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
  //              event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
  //                  newStore.copyFrom(oldStore);
  //              });
  //          });
  //      }
  //  }
//
  //  @SubscribeEvent
  //  public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
  //      event.register(PlayerThirst.class);
  //  }
//
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
  //  @SubscribeEvent
  //  public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
  //      if(!event.getLevel().isClientSide()) {
  //          if(event.getEntity() instanceof ServerPlayer player) {
  //              player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
  //                  ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
  //              });
  //          }
  //      }
  //  }

