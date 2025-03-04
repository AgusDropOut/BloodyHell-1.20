package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.agusdropout.bloodyhell.entity.custom.BloodSlashEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.Optional;

public class BloodSlashEntityModel extends GeoModel<BloodSlashEntity> {
    @Override
    public ResourceLocation getModelResource(BloodSlashEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blood_slash_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodSlashEntity object) {
        return new ResourceLocation(BloodyHell.MODID, "textures/entity/blood_slash_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodSlashEntity animatable) {
        return null;
    }

    @Override
    public void setCustomAnimations(BloodSlashEntity animatable, long instanceId, AnimationState<BloodSlashEntity> animationState) {
        CoreGeoBone bone = getAnimationProcessor().getBone("blood_slash_entity");

        if (bone != null ) {
            // Verificamos si la instancia actual corresponde a la entidad correcta
            if (instanceId == animatable.getId()) {
                float yRot = animatable.getYRot();
                float xRot = animatable.getXRot();
                bone.setRotX(xRot * Mth.DEG_TO_RAD);
                bone.setRotY(yRot * Mth.DEG_TO_RAD);
            }
        }
    }
}
