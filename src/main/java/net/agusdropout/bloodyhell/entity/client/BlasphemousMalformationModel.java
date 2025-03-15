package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BlasphemousMalformationEntity;
import net.agusdropout.bloodyhell.entity.custom.OffspringOfTheUnknownEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BlasphemousMalformationModel extends GeoModel<BlasphemousMalformationEntity> {
    @Override
    public ResourceLocation getModelResource(BlasphemousMalformationEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blasphemous_malformation.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlasphemousMalformationEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blasphemous_malformation.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlasphemousMalformationEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/blasphemous_malformation.animation.json");
    }

@Override
public void setCustomAnimations(BlasphemousMalformationEntity animatable, long instanceId, AnimationState<BlasphemousMalformationEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");
        CoreGeoBone body = getAnimationProcessor().getBone("offpring of the unknown");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(-entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }

}
}
