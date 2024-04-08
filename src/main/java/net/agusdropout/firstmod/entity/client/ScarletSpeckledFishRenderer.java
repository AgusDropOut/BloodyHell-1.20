package net.agusdropout.firstmod.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.entity.custom.ScarletSpeckledFishEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ScarletSpeckledFishRenderer extends GeoEntityRenderer<ScarletSpeckledFishEntity> {
    public ScarletSpeckledFishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ScarletSpeckledFishModel());
        this.shadowRadius = 0.2f;
    }

    @Override
    public ResourceLocation getTextureLocation(ScarletSpeckledFishEntity instance) {
        return new ResourceLocation(FirstMod.MODID, "textures/entity/scarletspeckledfish_texture.png");
    }

    @Override
    public RenderType getRenderType(ScarletSpeckledFishEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(ScarletSpeckledFishEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
        poseStack.scale(0.15f, 0.15f, 0.15f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
