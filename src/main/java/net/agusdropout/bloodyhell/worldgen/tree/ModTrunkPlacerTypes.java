package net.agusdropout.bloodyhell.worldgen.tree;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.worldgen.tree.custom.BloodTrunkPlacer;
import net.agusdropout.bloodyhell.worldgen.tree.custom.GiantBloodTrunkPlacer;
import net.agusdropout.bloodyhell.worldgen.tree.custom.SmallBloodTrunkPlacer;
import net.agusdropout.bloodyhell.worldgen.tree.custom.SoulTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, BloodyHell.MODID);

    public static final RegistryObject<TrunkPlacerType<BloodTrunkPlacer>> BLOOD_TRUNK_PLACER =
            TRUNK_PLACER.register("blood_trunk_placer", ()-> new TrunkPlacerType<>(BloodTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<GiantBloodTrunkPlacer>> GIANT_BLOOD_TRUNK_PLACER =
            TRUNK_PLACER.register("giant_blood_trunk_placer", ()-> new TrunkPlacerType<>(GiantBloodTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<SoulTrunkPlacer>> SOUL_TRUNK_PLACER =
            TRUNK_PLACER.register("soul_trunk_placer", ()-> new TrunkPlacerType<>(SoulTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<SmallBloodTrunkPlacer>> SMALL_BLOOD_TRUNK_PLACER =
            TRUNK_PLACER.register("small_blood_trunk_placer", ()-> new TrunkPlacerType<>(SmallBloodTrunkPlacer.CODEC));


    public static void register(IEventBus eventbus){
        TRUNK_PLACER.register(eventbus);
    }
}
