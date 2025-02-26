package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.agusdropout.bloodyhell.entity.custom.BloodySoulEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BloodySoulEntityModel extends GeoModel<BloodySoulEntity> {
    @Override
    public ResourceLocation getModelResource(BloodySoulEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/bloody_soul_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodySoulEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/bloody_soul_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodySoulEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/bloody_soul_entity.animation.json");
    }

@Override
public void setCustomAnimations(BloodySoulEntity animatable, long instanceId, AnimationState<BloodySoulEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(-entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }
}
}
