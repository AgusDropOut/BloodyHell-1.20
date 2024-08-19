package net.agusdropout.firstmod.entity.client;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.entity.custom.BloodPigEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BloodPigModel extends GeoModel<BloodPigEntity> {
    @Override
    public ResourceLocation getModelResource(BloodPigEntity object) {
        return new ResourceLocation(FirstMod.MODID, "geo/bloodpig.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodPigEntity object) {
        return new ResourceLocation(FirstMod.MODID, "textures/entity/bloodpig_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodPigEntity animatable) {
        return new ResourceLocation(FirstMod.MODID, "animations/bloodpig.animation.json");
    }

    @Override
    public void setCustomAnimations(BloodPigEntity animatable, long instanceId, AnimationState<BloodPigEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(-entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }
    }
}

