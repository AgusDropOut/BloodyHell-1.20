package net.agusdropout.firstmod.entity.client;


import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.entity.custom.BloodThirstyBeastEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BloodThirstyBeastRenderer extends GeoEntityRenderer<BloodThirstyBeastEntity> {
    public BloodThirstyBeastRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BloodThirstyBeastModel());
        this.shadowRadius = 0.1f;
    }

    @Override
    public ResourceLocation getTextureLocation(BloodThirstyBeastEntity instance) {
        return new ResourceLocation(FirstMod.MODID, "textures/entity/bloodthirstybeast_texture.png");
    }

    @Override
    public RenderType getRenderType(BloodThirstyBeastEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
