package net.agusdropout.bloodyhell.event;

//import net.agusdropout.bloodyhell.client.ThirstHudOverlay;

import com.mojang.blaze3d.shaders.FogShape;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.client.CrimsonVeilHudOverlay;
import net.agusdropout.bloodyhell.client.VisceralEffectHudOverlay;
import net.agusdropout.bloodyhell.entity.client.CrystalPillarModel;
import net.agusdropout.bloodyhell.entity.client.ModModelLayers;
import net.agusdropout.bloodyhell.entity.client.UnknownEyeEntityModel;
import net.agusdropout.bloodyhell.entity.client.VesperModel;
import net.agusdropout.bloodyhell.entity.effects.EntityCameraShake;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.agusdropout.bloodyhell.particle.custom.*;
import net.agusdropout.bloodyhell.util.ClientTickHandler;
import net.agusdropout.bloodyhell.worldgen.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.BeaconBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.plaf.DimensionUIResource;

public class ClientEvents  {
    @Mod.EventBusSubscriber(modid = BloodyHell.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @Mod.EventBusSubscriber(modid = BloodyHell.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientModBusEvents {
            @SubscribeEvent
            public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
                event.registerSpriteSet(ModParticles.BLOOD_PARTICLES.get(), BloodParticles.Provider::new);
                event.registerSpriteSet(ModParticles.LIGHT_PARTICLES.get(), LightParticle.Provider::new);
                event.registerSpriteSet(ModParticles.DIRTY_BLOOD_FLOWER_PARTICLE.get(), DirtyBloodFlowerParticle.Provider::new);
                event.registerSpriteSet(ModParticles.IMPACT_PARTICLE.get(), ImpactParticle.Provider::new);
                event.registerSpriteSet(ModParticles.SLASH_PARTICLE.get(), SlashParticle.Provider::new);
                event.registerSpriteSet(ModParticles.VICERAL_PARTICLE.get(), ViceralParticle.Provider::new);
            }


            @SubscribeEvent
            public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {

                event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "mana_hud", CrimsonVeilHudOverlay.OVERLAY);
                event.registerBelow(VanillaGuiOverlay.CROSSHAIR.id(), "visceral_overlay", VisceralEffectHudOverlay.OVERLAY);
            }


            @SubscribeEvent
            public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
                event.registerLayerDefinition(ModModelLayers.CRYSTAL_PILLAR, CrystalPillarModel::createBodyLayer);
                event.registerLayerDefinition(ModModelLayers.VESPER, VesperModel::createBodyLayer);

            }


        }
        @SubscribeEvent
        public static void clientTickEvent(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.START) {
                ClientTickHandler.ticksInGame++;
            }

        }

    }
}