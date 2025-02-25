package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.UnknownEntityArms;
import net.agusdropout.bloodyhell.entity.custom.UnknownEyeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class UnknownEntityArmsModel extends GeoModel<UnknownEntityArms> {
    @Override
    public ResourceLocation getModelResource(UnknownEntityArms object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/unknown_entity_arms.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(UnknownEntityArms object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/unknown_entity_arms.png");
    }

    @Override
    public ResourceLocation getAnimationResource(UnknownEntityArms animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/unknown_entity_arms.animation.json");
    }
    @Override
    public void setCustomAnimations(UnknownEntityArms animatable, long instanceId, AnimationState<UnknownEntityArms> animationState) {

    }
}
