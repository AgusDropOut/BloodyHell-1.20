package net.agusdropout.bloodyhell.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.HornedWormEntity;
import net.agusdropout.bloodyhell.entity.custom.VeilStalkerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class VeilStalkerRenderer extends GeoEntityRenderer<VeilStalkerEntity> {
    public VeilStalkerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VeilStalkerModel());
        this.shadowRadius = 0.001f;
        addRenderLayer(new AutoGlowingGeoLayer<>(this));

    }

    @Override
    public ResourceLocation getTextureLocation(VeilStalkerEntity instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/veil_stalker_texture.png");
    }

    @Override
    public RenderType getRenderType(VeilStalkerEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(VeilStalkerEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
