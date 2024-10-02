package net.agusdropout.bloodyhell.item;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.datagen.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier BLOOD = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25,
                    ModTags.Blocks.NEEDS_BLOOD_TOOL, () -> Ingredient.of(ModItems.SANGUINITE.get())),
            new ResourceLocation(BloodyHell.MODID, "blood"), List.of(Tiers.DIAMOND), List.of());

}
