package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodSlashEntity;
import net.agusdropout.bloodyhell.entity.projectile.BloodProjectileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class BloodProjectileEntityModel extends GeoModel<BloodProjectileEntity> {
    @Override
    public ResourceLocation getModelResource(BloodProjectileEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blood_projectile.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodProjectileEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blood_projectile.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodProjectileEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/blood_projectile.animation.json") ;
    }

    @Override
    public void setCustomAnimations(BloodProjectileEntity animatable, long instanceId, AnimationState<BloodProjectileEntity> animationState) {

    }
}
