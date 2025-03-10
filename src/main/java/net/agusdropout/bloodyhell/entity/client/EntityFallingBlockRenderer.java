package net.agusdropout.bloodyhell.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.agusdropout.bloodyhell.entity.effects.EntityFallingBlock;
import net.agusdropout.bloodyhell.util.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/*Code borrowed from Mowzie's Mobs*/

public class EntityFallingBlockRenderer extends EntityRenderer<EntityFallingBlock> {
    public EntityFallingBlockRenderer(EntityRendererProvider.Context mgr) {
        super(mgr);
    }

    @Override
    public void render(EntityFallingBlock entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();
        matrixStackIn.pushPose();
        matrixStackIn.translate(0, 0.5f, 0);
        if (entityIn.getMode() == EntityFallingBlock.EnumFallingBlockMode.MOBILE) {
            matrixStackIn.mulPose(MathUtils.quatFromRotationXYZ(0, Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()), 0, true));
            matrixStackIn.mulPose(MathUtils.quatFromRotationXYZ(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot()), 0, 0, true));
        }
        else {
            matrixStackIn.translate(0, Mth.lerp(partialTicks, entityIn.prevAnimY, entityIn.animY), 0);
            matrixStackIn.translate(0, -1, 0);
        }
        matrixStackIn.translate(-0.5f, -0.5f, -0.5f);
        dispatcher.renderSingleBlock(entityIn.getBlock(), matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFallingBlock entity) {
        return null;
    }
}
