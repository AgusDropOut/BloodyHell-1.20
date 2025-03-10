package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.OmenGazerEntity;
import net.agusdropout.bloodyhell.entity.custom.VeinraverEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class VeinRaverEntityModel extends GeoModel<VeinraverEntity> {
    @Override
    public ResourceLocation getModelResource(VeinraverEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/veinraver_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VeinraverEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/veinraver_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VeinraverEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/veinraver_entity.animation.json");
    }

@Override
public void setCustomAnimations(VeinraverEntity animatable, long instanceId, AnimationState<VeinraverEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }
}
}
