package net.agusdropout.firstmod.worldgen.structure;

import net.agusdropout.firstmod.FirstMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ModStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, FirstMod.MODID);
    public static final ResourceKey<Structure> CATACOMBS = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(FirstMod.MODID, "portal"));



    public static void register(IEventBus eventBus){
        STRUCTURES.register(eventBus);
    }
}
