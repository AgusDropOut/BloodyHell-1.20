package net.agusdropout.bloodyhell.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.projectile.BlasphemousSmallWhirlwindEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

@OnlyIn(Dist.CLIENT)
public class BlasphemousSmallWhirlwindEntityRenderer extends GeoEntityRenderer<BlasphemousSmallWhirlwindEntity> {
    public BlasphemousSmallWhirlwindEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BlasphemousSmallWhirlwindEntityModel());
        this.shadowRadius = 0.8f;
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(BlasphemousSmallWhirlwindEntity instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blasphemous_small_whirlwind_entity.png");
    }

    @Override
    public RenderType getRenderType(BlasphemousSmallWhirlwindEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(BlasphemousSmallWhirlwindEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
