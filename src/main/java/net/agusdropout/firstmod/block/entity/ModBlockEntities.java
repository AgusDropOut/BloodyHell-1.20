package net.agusdropout.firstmod.block.entity;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FirstMod.MODID);

    public static final RegistryObject<BlockEntityType<BloodWorkbenchBlockEntity>> BLOOD_WORKBENCH =
            BLOCK_ENTITIES.register("blood_workbench", () ->
                    BlockEntityType.Builder.of(BloodWorkbenchBlockEntity::new,
                            ModBlocks.BLOOD_WORKBENCH.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}