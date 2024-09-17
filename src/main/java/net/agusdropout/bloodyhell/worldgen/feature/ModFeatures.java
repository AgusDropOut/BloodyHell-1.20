package net.agusdropout.bloodyhell.worldgen.feature;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.worldgen.feature.custom.BloodScrapperPlantFeature;
import net.agusdropout.bloodyhell.worldgen.feature.custom.DroopvineFeature;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, BloodyHell.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> DROOPVINE = FEATURES.register("droopvine", () ->
            new DroopvineFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> BLOOD_SCRAPPER_PLANT = FEATURES.register("blood_scrapper_plant", () ->
            new BloodScrapperPlantFeature(ProbabilityFeatureConfiguration.CODEC));


    public static void register(IEventBus eventBus){
        FEATURES.register(eventBus);
    }
}