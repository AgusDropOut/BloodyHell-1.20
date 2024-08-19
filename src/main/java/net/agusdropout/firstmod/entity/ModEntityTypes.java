package net.agusdropout.firstmod.entity;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FirstMod.MODID);

    public static final RegistryObject<EntityType<BloodThirstyBeastEntity>> BLOODTHIRSTYBEAST = ENTITY_TYPES.register("bloodthirstybeast",
            () -> EntityType.Builder.of(BloodThirstyBeastEntity::new, MobCategory.MONSTER).sized(1.5f,1.5f).build(new ResourceLocation(FirstMod.MODID,
                    "bloodthirstybeast").toString()));
    public static final RegistryObject<EntityType<BloodSeekerEntity>> BLOOD_SEEKER = ENTITY_TYPES.register("bloodseeker",
            () -> EntityType.Builder.of(BloodSeekerEntity::new, MobCategory.MONSTER).sized(1f,1f).build(new ResourceLocation(FirstMod.MODID,
                    "bloodseeker").toString()));
    public static final RegistryObject<EntityType<CrimsonRavenEntity>> CRIMSON_RAVEN = ENTITY_TYPES.register("crimsonraven",
            () -> EntityType.Builder.of(CrimsonRavenEntity::new,MobCategory.CREATURE).sized(1f,1f).build(new ResourceLocation(FirstMod.MODID,
                    "crimsonraven").toString()));
    public static final RegistryObject<EntityType<EyeshellSnailEntity>> EYESHELL_SNAIL = ENTITY_TYPES.register("eyeshellsnail",
            () -> EntityType.Builder.of(EyeshellSnailEntity::new,MobCategory.CREATURE).sized(1f,1f).build(new ResourceLocation(FirstMod.MODID,
                    "eyeshellsnail").toString()));
    public static final RegistryObject<EntityType<ScarletSpeckledFishEntity>> SCARLETSPECKLED_FISH = ENTITY_TYPES.register("scarletspeckledfish",
            () -> EntityType.Builder.of(ScarletSpeckledFishEntity::new,MobCategory.AMBIENT).sized(0.2f,0.2f).build(new ResourceLocation(FirstMod.MODID,
                    "scarletspeckledfish").toString()));
    public static final RegistryObject<EntityType<BloodPigEntity>> BLOODPIG = ENTITY_TYPES.register("bloodpig",
            () -> EntityType.Builder.of(BloodPigEntity::new,MobCategory.AMBIENT).sized(1.2f,1.2f).build(new ResourceLocation(FirstMod.MODID,
                    "bloodpig").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
