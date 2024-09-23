package net.agusdropout.bloodyhell.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodPigEntity;
import net.agusdropout.bloodyhell.entity.custom.OniEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class OniRenderer extends GeoEntityRenderer<OniEntity> {
    public OniRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OniModel());
        this.shadowRadius = 0.8f;
        addRenderLayer(new AutoGlowingGeoLayer<>(this));


    }

    @Override
    public ResourceLocation getTextureLocation(OniEntity instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/oni_texture.png");
    }

    @Override
    public RenderType getRenderType(OniEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(OniEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(2f, 2f, 2f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
