package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.agusdropout.bloodyhell.entity.custom.BloodSlashEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BloodSlashEntityModel extends GeoModel<BloodSlashEntity> {
    @Override
    public ResourceLocation getModelResource(BloodSlashEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blood_slash_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodSlashEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blood_slash_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodSlashEntity animatable) {
        return null;
    }

@Override
public void setCustomAnimations(BloodSlashEntity animatable, long instanceId, AnimationState<BloodSlashEntity> animationState) {

}
}
