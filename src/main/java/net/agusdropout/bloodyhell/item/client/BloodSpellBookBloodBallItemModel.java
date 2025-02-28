package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.item.custom.BloodSpellBookBloodBallItem;
import net.agusdropout.bloodyhell.item.custom.BloodSpellBookScratchItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodSpellBookBloodBallItemModel extends GeoModel<BloodSpellBookBloodBallItem> {
    @Override
    public ResourceLocation getModelResource(BloodSpellBookBloodBallItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blood_spell_book_bloodball.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodSpellBookBloodBallItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "textures/item/blood_spell_book_bloodball.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodSpellBookBloodBallItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/blood_spell_book_bloodball.animation.json");
    }
}
