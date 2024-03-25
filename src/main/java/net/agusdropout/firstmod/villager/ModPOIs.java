package net.agusdropout.firstmod.villager;

import com.google.common.collect.ImmutableSet;
import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.block.ModBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



    public class ModPOIs {

        public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, FirstMod.MODID);

        public static final RegistryObject<PoiType> BLOOD_PORTAL = POI.register("blood_portal_key", () -> new PoiType(ImmutableSet.copyOf(ModBlocks.BLOOD_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));

        public static void register(IEventBus event){
            POI.register(event);
        }

    }