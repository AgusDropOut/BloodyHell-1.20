package net.agusdropout.firstmod.villager;
//
//import com.google.common.collect.ImmutableSet;
//import net.agusdropout.firstmod.FirstMod;
//import net.agusdropout.firstmod.block.ModBlocks;
//import net.minecraft.sounds.SoundEvent;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.world.entity.ai.village.poi.PoiType;
//import net.minecraft.world.entity.npc.VillagerProfession;
//import net.minecraft.world.level.block.Block;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.lang.reflect.InvocationTargetException;
//
//public class ModVillagers {
//    public static final DeferredRegister<PoiType> POI_TYPES =
//            DeferredRegister.create(ForgeRegistries.POI_TYPES, FirstMod.MODID);
//    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
//            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, FirstMod.MODID);
//    public static void register(IEventBus eventBus){
//        POI_TYPES.register(eventBus);
//        VILLAGER_PROFESSIONS.register(eventBus);
//    }
//    public static final RegistryObject<PoiType> JUMPY_BLOCK_POI = POI_TYPES.register("jumpy_block_poi",() -> new PoiType(ImmutableSet.copyOf(ModBlocks.Jumpy_Block.get().getStateDefinition().getPossibleStates()),
//            1,1));
//    public static final RegistryObject<VillagerProfession> JUMP_MASTER = VILLAGER_PROFESSIONS.register("jump_master", ()-> new VillagerProfession("jump_master", x -> x.get() == JUMPY_BLOCK_POI.get(),x -> x.get() == JUMPY_BLOCK_POI.get(),ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));
//    public static void registerPOIs() {
//        try{
//            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, JUMPY_BLOCK_POI.get());
//        } catch (InvocationTargetException | IllegalAccessException exception) {
//          exception.printStackTrace();
//        }
//    }
//
//}
