package net.agusdropout.bloodyhell.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.OffspringOfTheUnknownEntity;
import net.agusdropout.bloodyhell.entity.custom.SelioraEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class SelioraRenderer extends GeoEntityRenderer<SelioraEntity> {
    public SelioraRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SelioraModel());
        this.shadowRadius = 0.8f;
        addRenderLayer(new AutoGlowingGeoLayer<>(this));

    }

    @Override
    public ResourceLocation getTextureLocation(SelioraEntity instance) {
        if(!instance.isSecondPhase()) {
            return new ResourceLocation(BloodyHell.MODID, "textures/entity/seliora_texture.png");
        } else {
            return new ResourceLocation(BloodyHell.MODID, "textures/entity/seliora_second_phase_texture.png");
        }
    }

    @Override
    public RenderType getRenderType(SelioraEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(SelioraEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
