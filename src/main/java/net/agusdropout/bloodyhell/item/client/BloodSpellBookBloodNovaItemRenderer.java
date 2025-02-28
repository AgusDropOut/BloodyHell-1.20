package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.item.custom.BloodSpellBookBloodBallItem;
import net.agusdropout.bloodyhell.item.custom.BloodSpellBookBloodNovaItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BloodSpellBookBloodNovaItemRenderer extends GeoItemRenderer<BloodSpellBookBloodNovaItem> {
    public BloodSpellBookBloodNovaItemRenderer() {
        super(new BloodSpellBookBloodNovaItemModel());
    }

}
