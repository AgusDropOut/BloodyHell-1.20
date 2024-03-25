package net.agusdropout.firstmod.entity.client;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.entity.custom.EyeshellSnailEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class EyeshellSnailModel extends GeoModel<EyeshellSnailEntity> {
    @Override
    public ResourceLocation getModelResource(EyeshellSnailEntity object) {
        return new ResourceLocation(FirstMod.MODID, "geo/eyeshellsnail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EyeshellSnailEntity object) {
        return new ResourceLocation(FirstMod.MODID, "textures/entity/eyeshellsnail_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EyeshellSnailEntity animatable) {
        return new ResourceLocation(FirstMod.MODID, "animations/eyeshellsnail.animation.json");
    }

@Override
public void setCustomAnimations(EyeshellSnailEntity animatable, long instanceId, AnimationState<EyeshellSnailEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(-entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }
}
}
