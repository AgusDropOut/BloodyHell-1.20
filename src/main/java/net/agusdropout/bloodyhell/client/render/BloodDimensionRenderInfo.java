package net.agusdropout.bloodyhell.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;


import java.util.Optional;

public class BloodDimensionRenderInfo extends DimensionSpecialEffects {
    public BloodDimensionRenderInfo(float cloudHeight, boolean placebo, SkyType fogType, boolean brightenLightMap, boolean entityLightingBottomsLit) {
        super(cloudHeight, placebo, fogType, brightenLightMap, entityLightingBottomsLit);
    }

    @Nullable
    @Override
    public float[] getSunriseColor(float daycycle, float partialTicks) { // Fog color
        return null;
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight) { // For modifying biome fog color with daycycle
        return biomeFogColor.multiply(0,0 , 0);
    }

    @Override
    public boolean isFoggyAt(int i, int i1) {
        return false;
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
        return false;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        return BloodDimensionSkyRenderer.renderSky(level, partialTick, poseStack, camera, projectionMatrix, setupFog);
    }





}
