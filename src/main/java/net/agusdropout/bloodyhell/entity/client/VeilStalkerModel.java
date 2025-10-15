package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.HornedWormEntity;
import net.agusdropout.bloodyhell.entity.custom.VeilStalkerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class VeilStalkerModel extends GeoModel<VeilStalkerEntity> {
    @Override
    public ResourceLocation getModelResource(VeilStalkerEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/veil_stalker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VeilStalkerEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/veil_stalker_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VeilStalkerEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/veil_stalker.animation.json");
    }

    @Override
    public void setCustomAnimations(VeilStalkerEntity animatable, long instanceId, AnimationState<VeilStalkerEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");


        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(-entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
