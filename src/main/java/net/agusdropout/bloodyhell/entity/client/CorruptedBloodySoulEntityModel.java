package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodySoulEntity;
import net.agusdropout.bloodyhell.entity.custom.CorruptedBloodySoulEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CorruptedBloodySoulEntityModel extends GeoModel<CorruptedBloodySoulEntity> {
    @Override
    public ResourceLocation getModelResource(CorruptedBloodySoulEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/corrupted_bloody_soul_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CorruptedBloodySoulEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/corrupted_bloody_soul_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CorruptedBloodySoulEntity animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/corrupted_bloody_soul_entity.animation.json");
    }

@Override
public void setCustomAnimations(CorruptedBloodySoulEntity animatable, long instanceId, AnimationState<CorruptedBloodySoulEntity> animationState) {

        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(-entityData.netHeadYaw() * Mth.DEG_TO_RAD);

        }
}
}
