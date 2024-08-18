package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BloodSeekerModel extends GeoModel<BloodSeekerEntity> {
    @Override
    public ResourceLocation getModelResource(BloodSeekerEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/bloodseeker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodSeekerEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/bloodseeker_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodSeekerEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/bloodseeker.animation.json");
    }

@Override
public void setCustomAnimations(BloodSeekerEntity animatable, long instanceId, AnimationState<BloodSeekerEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(-entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }
}
}
