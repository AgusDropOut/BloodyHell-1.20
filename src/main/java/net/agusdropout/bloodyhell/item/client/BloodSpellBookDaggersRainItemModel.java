package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.item.custom.BloodSpellBookBloodNovaItem;
import net.agusdropout.bloodyhell.item.custom.BloodSpellBookDaggersRainItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodSpellBookDaggersRainItemModel extends GeoModel<BloodSpellBookDaggersRainItem> {
    @Override
    public ResourceLocation getModelResource(BloodSpellBookDaggersRainItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "geo/blood_spell_book_daggersrain.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BloodSpellBookDaggersRainItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "textures/item/blood_spell_book_daggersrain.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BloodSpellBookDaggersRainItem animatable) {
        return new ResourceLocation(BloodyHell.MODID, "animations/blood_spell_book_daggersrain.animation.json");
    }
}
