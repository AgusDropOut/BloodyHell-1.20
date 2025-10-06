package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BlasphemousArmEntity;
import net.agusdropout.bloodyhell.entity.custom.UnknownEntityArms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class BlasphemousArmEntityModel extends GeoModel<BlasphemousArmEntity> {
    @Override
    public ResourceLocation getModelResource(BlasphemousArmEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blasphemous_arm_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlasphemousArmEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blasphemous_arm_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlasphemousArmEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/blasphemous_arm_entity.animation.json");
    }
}
