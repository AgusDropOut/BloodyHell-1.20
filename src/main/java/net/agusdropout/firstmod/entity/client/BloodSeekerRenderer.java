package net.agusdropout.firstmod.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.entity.custom.BloodSeekerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BloodSeekerRenderer extends GeoEntityRenderer<BloodSeekerEntity> {
    public BloodSeekerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BloodSeekerModel());
        this.shadowRadius = 0.8f;
    }

    @Override
    public ResourceLocation getTextureLocation(BloodSeekerEntity instance) {
        return new ResourceLocation(FirstMod.MODID, "textures/entity/bloodseeker_texture.png");
    }

    @Override
    public RenderType getRenderType(BloodSeekerEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(BloodSeekerEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(2, 2, 2);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
