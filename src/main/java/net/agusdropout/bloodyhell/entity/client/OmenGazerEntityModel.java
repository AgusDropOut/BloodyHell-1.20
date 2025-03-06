package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.agusdropout.bloodyhell.entity.custom.OmenGazerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OmenGazerEntityModel extends GeoModel<OmenGazerEntity> {
    @Override
    public ResourceLocation getModelResource(OmenGazerEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/omen_gazer_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OmenGazerEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/omen_gazer_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OmenGazerEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/omen_gazer_entity.animation.json");
    }

@Override
public void setCustomAnimations(OmenGazerEntity animatable, long instanceId, AnimationState<OmenGazerEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(-entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }
}
}
