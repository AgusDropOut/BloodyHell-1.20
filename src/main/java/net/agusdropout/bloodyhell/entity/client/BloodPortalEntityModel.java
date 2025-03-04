package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodPortalEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class BloodPortalEntityModel extends GeoModel<BloodPortalEntity> {
    @Override
    public ResourceLocation getModelResource(BloodPortalEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blood_portal_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodPortalEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blood_portal_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodPortalEntity animatable) {
        return null;
    }
    @Override
    public void setCustomAnimations(BloodPortalEntity animatable, long instanceId, AnimationState<BloodPortalEntity> animationState) {

    }
}
