package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.item.custom.BloodSpellBookBloodNovaItem;
import net.agusdropout.bloodyhell.item.custom.BloodSpellBookScratchItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodSpellBookBloodNovaItemModel extends GeoModel<BloodSpellBookBloodNovaItem> {
    @Override
    public ResourceLocation getModelResource(BloodSpellBookBloodNovaItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blood_spell_book_bloodnova.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodSpellBookBloodNovaItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "textures/item/blood_spell_book_bloodnova.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodSpellBookBloodNovaItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/blood_spell_book_bloodnova.animation.json");
    }
}
