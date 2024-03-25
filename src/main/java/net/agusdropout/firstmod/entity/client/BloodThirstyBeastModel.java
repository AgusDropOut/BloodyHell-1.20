package net.agusdropout.firstmod.entity.client;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.entity.custom.BloodThirstyBeastEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodThirstyBeastModel extends GeoModel<BloodThirstyBeastEntity> {
    @Override
    public ResourceLocation getModelResource(BloodThirstyBeastEntity object) {
        return new ResourceLocation(FirstMod.MODID, "geo/bloodthirstybeast.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodThirstyBeastEntity object) {
        return new ResourceLocation(FirstMod.MODID, "textures/entity/bloodthirstybeast_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodThirstyBeastEntity animatable) {
        return new ResourceLocation(FirstMod.MODID, "animations/bloodthirstybeast.animation.json");
    }

    }

