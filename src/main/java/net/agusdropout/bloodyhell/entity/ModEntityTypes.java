package net.agusdropout.bloodyhell.entity;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BloodyHell.MODID);

    public static final RegistryObject<EntityType<BloodThirstyBeastEntity>> BLOODTHIRSTYBEAST = ENTITY_TYPES.register("bloodthirstybeast",
            () -> EntityType.Builder.of(BloodThirstyBeastEntity::new, MobCategory.MONSTER).sized(1.5f,1.5f).build(new ResourceLocation(BloodyHell.MODID,
                    "bloodthirstybeast").toString()));
    public static final RegistryObject<EntityType<BloodSeekerEntity>> BLOOD_SEEKER = ENTITY_TYPES.register("bloodseeker",
            () -> EntityType.Builder.of(BloodSeekerEntity::new, MobCategory.CREATURE).sized(1f,1f).build(new ResourceLocation(BloodyHell.MODID,
                    "bloodseeker").toString()));
    public static final RegistryObject<EntityType<BloodySoulEntity>> BLOODY_SOUL_ENTITY = ENTITY_TYPES.register("bloody_soul_entity",
            () -> EntityType.Builder.of(BloodySoulEntity::new, MobCategory.CREATURE).sized(1f,1.5f).build(new ResourceLocation(BloodyHell.MODID,
                    "bloody_soul_entity").toString()));
    public static final RegistryObject<EntityType<CorruptedBloodySoulEntity>> CORRUPTED_BLOODY_SOUL_ENTITY = ENTITY_TYPES.register("corrupted_bloody_soul_entity",
            () -> EntityType.Builder.of(CorruptedBloodySoulEntity::new, MobCategory.CREATURE).sized(1f,1.5f).build(new ResourceLocation(BloodyHell.MODID,
                    "corrupted_bloody_soul_entity").toString()));
    public static final RegistryObject<EntityType<CrimsonRavenEntity>> CRIMSON_RAVEN = ENTITY_TYPES.register("crimsonraven",
            () -> EntityType.Builder.of(CrimsonRavenEntity::new,MobCategory.CREATURE).sized(1f,1f).build(new ResourceLocation(BloodyHell.MODID,
                    "crimsonraven").toString()));
    public static final RegistryObject<EntityType<EyeshellSnailEntity>> EYESHELL_SNAIL = ENTITY_TYPES.register("eyeshellsnail",
            () -> EntityType.Builder.of(EyeshellSnailEntity::new,MobCategory.CREATURE).sized(1f,1f).build(new ResourceLocation(BloodyHell.MODID,
                    "eyeshellsnail").toString()));
    public static final RegistryObject<EntityType<ScarletSpeckledFishEntity>> SCARLETSPECKLED_FISH = ENTITY_TYPES.register("scarletspeckledfish",
            () -> EntityType.Builder.of(ScarletSpeckledFishEntity::new,MobCategory.AMBIENT).sized(0.2f,0.2f).build(new ResourceLocation(BloodyHell.MODID,
                    "scarletspeckledfish").toString()));
    public static final RegistryObject<EntityType<BloodPigEntity>> BLOODPIG = ENTITY_TYPES.register("bloodpig",
            () -> EntityType.Builder.of(BloodPigEntity::new,MobCategory.AMBIENT).sized(0.9f,0.9f).build(new ResourceLocation(BloodyHell.MODID,
                    "bloodpig").toString()));
    public static final RegistryObject<EntityType<OniEntity>> ONI = ENTITY_TYPES.register("oni",
            () -> EntityType.Builder.of(OniEntity::new,MobCategory.MONSTER).sized(1.5f,1.5f).build(new ResourceLocation(BloodyHell.MODID,
                    "oni").toString()));
    public static final RegistryObject<EntityType<BloodArrowEntity>> BLOOD_ARROW = ENTITY_TYPES.register("blood_arrow_entity",
            () -> EntityType.Builder.<BloodArrowEntity>of(BloodArrowEntity::new,MobCategory.AMBIENT).sized(1.2f,1.2f).build(new ResourceLocation(BloodyHell.MODID,
                    "blood_arrow_entity").toString()));
    public static final RegistryObject<EntityType<CrystalPillar>> CRYSTAL_PILLAR = ENTITY_TYPES.register("crystal_pillar",
            () -> EntityType.Builder.<CrystalPillar>of(CrystalPillar::new,MobCategory.AMBIENT).sized(1.2f,1.2f).build(new ResourceLocation(BloodyHell.MODID,
                    "crystal_pillar").toString()));
    public static final RegistryObject<EntityType<UnknownEyeEntity>> UNKNOWN_EYE_ENTITY = ENTITY_TYPES.register("unknown_eye_entity",
            () -> EntityType.Builder.<UnknownEyeEntity>of(UnknownEyeEntity::new,MobCategory.AMBIENT).sized(1.2f,1.2f).build(new ResourceLocation(BloodyHell.MODID,
                    "unknown_eye_entity").toString()));
    public static final RegistryObject<EntityType<UnknownEntityArms>> UNKNOWN_ENTITY_ARMS = ENTITY_TYPES.register("unknown_entity_arms",
            () -> EntityType.Builder.<UnknownEntityArms>of(UnknownEntityArms::new,MobCategory.AMBIENT).sized(1.2f,1.2f).build(new ResourceLocation(BloodyHell.MODID,
                    "unknown_entity_arms").toString()));
    public static final RegistryObject<EntityType<SanguineSacrificeEntity>> SANGUINE_SACRIFICE_ENTITY = ENTITY_TYPES.register("sanguine_sacrifice_entity",
            () -> EntityType.Builder.<SanguineSacrificeEntity>of(SanguineSacrificeEntity::new,MobCategory.AMBIENT).sized(1.2f,1.2f).build(new ResourceLocation(BloodyHell.MODID,
                    "sanguine_sacrifice_entity").toString()));

    public static final RegistryObject<EntityType<VesperEntity>> VESPER = ENTITY_TYPES.register("vesper",
            () -> EntityType.Builder.<VesperEntity>of(VesperEntity::new,MobCategory.MONSTER).sized(1.2f,1.2f).build(new ResourceLocation(BloodyHell.MODID,
                    "vesper").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
