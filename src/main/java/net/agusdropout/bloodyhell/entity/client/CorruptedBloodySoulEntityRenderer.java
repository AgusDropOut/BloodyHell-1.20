package net.agusdropout.bloodyhell.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodySoulEntity;
import net.agusdropout.bloodyhell.entity.custom.CorruptedBloodySoulEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class CorruptedBloodySoulEntityRenderer extends GeoEntityRenderer<CorruptedBloodySoulEntity> {
    public CorruptedBloodySoulEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CorruptedBloodySoulEntityModel());
        this.shadowRadius = 0.0f;
        addRenderLayer(new AutoGlowingGeoLayer<>(this));

    }

    @Override
    public ResourceLocation getTextureLocation(CorruptedBloodySoulEntity instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/corrupted_bloody_soul_entity.png");
    }

    @Override
    public RenderType getRenderType(CorruptedBloodySoulEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(CorruptedBloodySoulEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
