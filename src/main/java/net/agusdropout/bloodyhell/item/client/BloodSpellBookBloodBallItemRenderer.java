package net.agusdropout.bloodyhell.item.client;

import net.agusdropout.bloodyhell.item.custom.BloodSpellBookBloodBallItem;
import net.agusdropout.bloodyhell.item.custom.BloodSpellBookScratchItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BloodSpellBookBloodBallItemRenderer extends GeoItemRenderer<BloodSpellBookBloodBallItem> {
    public BloodSpellBookBloodBallItemRenderer() {
        super(new BloodSpellBookBloodBallItemModel());
    }

}
