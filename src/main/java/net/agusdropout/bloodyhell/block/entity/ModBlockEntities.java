package net.agusdropout.bloodyhell.block.entity;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BloodyHell.MODID);

    public static final RegistryObject<BlockEntityType<BloodWorkbenchBlockEntity>> BLOOD_WORKBENCH =
            BLOCK_ENTITIES.register("blood_workbench", () ->
                    BlockEntityType.Builder.of(BloodWorkbenchBlockEntity::new,
                            ModBlocks.BLOOD_WORKBENCH.get()).build(null));
    public static final RegistryObject<BlockEntityType<BHChestBlockEntity>> BH_CHEST = BLOCK_ENTITIES.register("bh_chest", () ->
            BlockEntityType.Builder.of(BHChestBlockEntity::new,
                    ModBlocks.BLOOD_WOOD_CHEST.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}