package net.agusdropout.bloodyhell.event;

//import net.agusdropout.bloodyhell.client.ThirstHudOverlay;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.client.BossBarHudOverlay;
import net.agusdropout.bloodyhell.client.ClientBossBarData;
import net.agusdropout.bloodyhell.client.CrimsonVeilHudOverlay;
import net.agusdropout.bloodyhell.client.VisceralEffectHudOverlay;
import net.agusdropout.bloodyhell.client.render.BloodDimensionRenderInfo;
import net.agusdropout.bloodyhell.entity.EntityBaseTypes.BloodyHellBoss;
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
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.BeaconBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;

import javax.swing.plaf.DimensionUIResource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientEvents {
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
                event.registerSpriteSet(ModParticles.BLASPHEMOUS_MAGIC_RING.get(), BlasphemousMagicCircleParticle.Provider::new);
                event.registerSpriteSet(ModParticles.SLASH_PARTICLE.get(), SlashParticle.Provider::new);
                event.registerSpriteSet(ModParticles.VICERAL_PARTICLE.get(), ViceralParticle.Provider::new);
                event.registerSpriteSet(ModParticles.MAGIC_LINE_PARTICLE.get(), MagicLineParticle.Provider::new);
                event.registerSpriteSet(ModParticles.MAGIC_SIMPLE_LINE_PARTICLE.get(), MagicSimpleLineParticle.Provider::new);
                event.registerSpecial(ModParticles.CYLINDER_PARTICLE.get(), new CylinderParticle.Provider());
                event.registerSpecial(ModParticles.STAR_EXPLOSION_PARTICLE.get(), new StarExplosionParticle.Provider());
                event.registerSpecial(ModParticles.MAGIC_WAVE_PARTICLE.get(), new MagicWaveParticle.Provider());
                event.registerSpecial(ModParticles.SIMPLE_BLOCK_PARTICLE.get(), new SimpleBlockParticle.Provider());
            }

            @SubscribeEvent
            public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
                event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "mana_hud", CrimsonVeilHudOverlay.OVERLAY);
                event.registerBelow(VanillaGuiOverlay.CROSSHAIR.id(), "visceral_overlay", VisceralEffectHudOverlay.OVERLAY);
                event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "boss_bar", BossBarHudOverlay.OVERLAY);
            }

            @SubscribeEvent
            public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
                event.registerLayerDefinition(ModModelLayers.CRYSTAL_PILLAR, CrystalPillarModel::createBodyLayer);
                event.registerLayerDefinition(ModModelLayers.VESPER, VesperModel::createBodyLayer);
            }


            @SubscribeEvent
            public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
                event.register(ModDimensions.DIMENSION_RENDERER,
                        new BloodDimensionRenderInfo(-189.0F, false, DimensionSpecialEffects.SkyType.NONE, false, false));
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