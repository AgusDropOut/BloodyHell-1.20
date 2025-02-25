package net.agusdropout.bloodyhell.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.UnknownEntityArms;
import net.agusdropout.bloodyhell.entity.custom.UnknownEyeEntity;
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
public class UnknownEntityArmsRenderer extends GeoEntityRenderer<UnknownEntityArms> {
    public UnknownEntityArmsRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new UnknownEntityArmsModel());
        this.shadowRadius = 0.8f;
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(UnknownEntityArms instance) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/unknown_entity_arms.png");
    }

    @Override
    public RenderType getRenderType(UnknownEntityArms animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(UnknownEntityArms entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {



        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
