package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.agusdropout.bloodyhell.entity.custom.OffspringOfTheUnknownEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OffspringOfTheUnknownModel extends GeoModel<OffspringOfTheUnknownEntity> {
    @Override
    public ResourceLocation getModelResource(OffspringOfTheUnknownEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/offspring_of_the_unknown.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OffspringOfTheUnknownEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/offspring_of_the_unknown.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OffspringOfTheUnknownEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/offspring_of_the_unknown.animation.json");
    }

@Override
public void setCustomAnimations(OffspringOfTheUnknownEntity animatable, long instanceId, AnimationState<OffspringOfTheUnknownEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");
        CoreGeoBone body = getAnimationProcessor().getBone("offpring of the unknown");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(-entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }

}
}
