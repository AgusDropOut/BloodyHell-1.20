package net.agusdropout.bloodyhell.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.agusdropout.bloodyhell.entity.custom.BloodSlashEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class BloodSlashRenderer extends GeoEntityRenderer<BloodSlashEntity> {
    public BloodSlashRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BloodSlashEntityModel());
        this.shadowRadius = 0.8f;

    }

    @Override
    public ResourceLocation getTextureLocation(BloodSlashEntity instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blood_slash_entity.png");
    }

    @Override
    public RenderType getRenderType(BloodSlashEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(BloodSlashEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
