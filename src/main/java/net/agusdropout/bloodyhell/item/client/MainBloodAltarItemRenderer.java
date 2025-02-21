package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.item.custom.BloodAltarItem;
import net.agusdropout.bloodyhell.item.custom.MainBloodAltarItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MainBloodAltarItemRenderer extends GeoItemRenderer<MainBloodAltarItem> {
    public MainBloodAltarItemRenderer() {
        super(new MainBloodAltarItemModel());
    }

}
