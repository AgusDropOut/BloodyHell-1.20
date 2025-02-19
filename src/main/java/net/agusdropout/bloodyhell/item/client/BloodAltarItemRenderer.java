package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.item.custom.BloodAltarItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BloodAltarItemRenderer extends GeoItemRenderer<BloodAltarItem> {
    public BloodAltarItemRenderer() {
        super(new BloodAltarItemModel());
    }

}
