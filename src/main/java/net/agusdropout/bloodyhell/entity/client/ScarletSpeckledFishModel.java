package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.ScarletSpeckledFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ScarletSpeckledFishModel extends GeoModel<ScarletSpeckledFishEntity> {
    @Override
    public ResourceLocation getModelResource(ScarletSpeckledFishEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/scarletspeckledfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ScarletSpeckledFishEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/scarletspeckledfish_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ScarletSpeckledFishEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/scarletspeckledfish.animation.json");
    }


}
