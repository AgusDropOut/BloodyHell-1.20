package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation CRYSTAL_PILLAR = new ModelLayerLocation(BloodyHell.prefix("crystal_pillar"), "main");
    public static final ModelLayerLocation VESPER = new ModelLayerLocation(
            new ResourceLocation(BloodyHell.MODID, "vesper"), "main");
}
