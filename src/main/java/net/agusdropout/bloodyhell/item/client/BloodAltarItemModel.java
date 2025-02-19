package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.item.custom.BloodAltarItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodAltarItemModel extends GeoModel<BloodAltarItem> {
    @Override
    public ResourceLocation getModelResource(BloodAltarItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blood_altar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodAltarItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "textures/block/blood_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodAltarItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/blood_altar.animation.json");
    }
}
