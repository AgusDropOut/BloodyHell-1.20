package net.agusdropout.bloodyhell.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.EyeshellSnailEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class EyeshellSnailRenderer extends GeoEntityRenderer<EyeshellSnailEntity> {
    public EyeshellSnailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EyeshellSnailModel<EyeshellSnailEntity>());
        this.shadowRadius = 0.8f;
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EyeshellSnailEntity instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/eyeshellsnail_texture.png");
    }

    @Override
    public RenderType getRenderType(EyeshellSnailEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(EyeshellSnailEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
        poseStack.scale(0.4f, 0.4f, 0.4f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
