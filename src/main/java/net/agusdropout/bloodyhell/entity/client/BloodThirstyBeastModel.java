package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodThirstyBeastEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodThirstyBeastModel extends GeoModel<BloodThirstyBeastEntity> {
    @Override
    public ResourceLocation getModelResource(BloodThirstyBeastEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/bloodthirstybeast.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodThirstyBeastEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/bloodthirstybeast_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodThirstyBeastEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/bloodthirstybeast.animation.json");
    }

    }

