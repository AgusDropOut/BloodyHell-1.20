package net.agusdropout.bloodyhell.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodPigEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BloodPigRenderer extends GeoEntityRenderer<BloodPigEntity> {
    public BloodPigRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BloodPigModel());
        this.shadowRadius = 0.8f;


    }

    @Override
    public ResourceLocation getTextureLocation(BloodPigEntity instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/bloodpig_texture.png");
    }

    @Override
    public RenderType getRenderType(BloodPigEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(BloodPigEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.5f, 1.5f, 1.5f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
