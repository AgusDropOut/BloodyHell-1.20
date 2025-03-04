package net.agusdropout.bloodyhell.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodPortalEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class BloodPortalEntityRenderer extends GeoEntityRenderer<BloodPortalEntity> {
    public BloodPortalEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BloodPortalEntityModel());
        this.shadowRadius = 0.8f;
    }

    @Override
    public ResourceLocation getTextureLocation(BloodPortalEntity instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blood_portal_entity.png");
    }

    @Override
    public RenderType getRenderType(BloodPortalEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(BloodPortalEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
