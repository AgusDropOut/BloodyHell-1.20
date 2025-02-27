package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.item.custom.BloodSpellBookScratchItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodSpellBookScratchItemModel extends GeoModel<BloodSpellBookScratchItem> {
    @Override
    public ResourceLocation getModelResource(BloodSpellBookScratchItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blood_spell_book_scratch.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodSpellBookScratchItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "textures/item/blood_spell_book_scratch.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodSpellBookScratchItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/blood_spell_book_scratch.animation.json");
    }
}
