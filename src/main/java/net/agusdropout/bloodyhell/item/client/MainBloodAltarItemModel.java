package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.item.custom.BloodAltarItem;
import net.agusdropout.bloodyhell.item.custom.MainBloodAltarItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MainBloodAltarItemModel extends GeoModel<MainBloodAltarItem> {
    @Override
    public ResourceLocation getModelResource(MainBloodAltarItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "geo/main_blood_altar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MainBloodAltarItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "textures/block/main_blood_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MainBloodAltarItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/main_blood_altar.animation.json");
    }
}
