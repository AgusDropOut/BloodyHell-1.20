package net.agusdropout.bloodyhell.worldgen.structure;


import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, BloodyHell.MODID);
    public static final ResourceKey<Structure> CATACOMBS = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(BloodyHell.MODID, "portal"));
    //public static final ResourceKey<Structure> BLOOD_LIGHTHOUSE = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(BloodyHell.MODID, "blood_lighthouse"));
    public static final ResourceKey<Structure> MAUSOLEUM = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(BloodyHell.MODID, "mausoleum"));
    public static final ResourceKey<Structure> VESPERS_HUT = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(BloodyHell.MODID, "vespers_hut"));
    public static final RegistryObject<StructureType<BiggerJigsawStructure>> BIGGER_JIGSAW = STRUCTURES.register("bigger_jigsaw", () -> () -> BiggerJigsawStructure.CODEC);
    public static void register(IEventBus eventBus){
        STRUCTURES.register(eventBus);
    }
}
