package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.CrimsonRavenEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CrimsonRavenModel extends GeoModel<CrimsonRavenEntity> {
    @Override
    public ResourceLocation getModelResource(CrimsonRavenEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/crimsonraven.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CrimsonRavenEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/crimsonraven_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CrimsonRavenEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/crimsonraven.animation.json");
    }
    @Override
    public void setCustomAnimations(CrimsonRavenEntity animatable, long instanceId, AnimationState<CrimsonRavenEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }
    }


}
