package net.agusdropout.bloodyhell.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodSlashEntity;
import net.agusdropout.bloodyhell.entity.projectile.BloodProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BloodProjectileEntityRenderer extends GeoEntityRenderer<BloodProjectileEntity> {
    public BloodProjectileEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BloodProjectileEntityModel());
        this.shadowRadius = 0.01f;

    }

    @Override
    public ResourceLocation getTextureLocation(BloodProjectileEntity instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blood_projectile.png");
    }

    @Override
    public RenderType getRenderType(BloodProjectileEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(BloodProjectileEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
