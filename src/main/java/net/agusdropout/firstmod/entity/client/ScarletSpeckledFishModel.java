package net.agusdropout.firstmod.entity.client;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.entity.custom.ScarletSpeckledFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ScarletSpeckledFishModel extends GeoModel<ScarletSpeckledFishEntity> {
    @Override
    public ResourceLocation getModelResource(ScarletSpeckledFishEntity object) {
        return new ResourceLocation(FirstMod.MODID, "geo/scarletspeckledfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ScarletSpeckledFishEntity object) {
        return new ResourceLocation(FirstMod.MODID, "textures/entity/scarletspeckledfish_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ScarletSpeckledFishEntity animatable) {
        return new ResourceLocation(FirstMod.MODID, "animations/scarletspeckledfish.animation.json");
    }


}
