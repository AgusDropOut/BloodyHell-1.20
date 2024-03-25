package net.agusdropout.firstmod.item;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.block.ModBlocks;
import net.agusdropout.firstmod.entity.ModEntityTypes;
import net.agusdropout.firstmod.fluid.ModFluids;
import net.agusdropout.firstmod.item.custom.BloodScytheItem;
import net.agusdropout.firstmod.item.custom.CatalystItem;
import net.agusdropout.firstmod.item.custom.EightBallItem;
import net.agusdropout.firstmod.item.custom.SoulItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MODID);
    public static final RegistryObject<Item> soul = ITEMS.register("soul", () -> new Item( new Item.Properties()));
    public static final RegistryObject<Item> RAW_soul = ITEMS.register("raw_soul", () -> new Item( new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_SOUL_DUST = ITEMS.register("bloody_soul_dust", () -> new Item( new Item.Properties()));
    public static final RegistryObject<Item> Eight_ball = ITEMS.register("eight_ball", () -> new EightBallItem( new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> Eyeball_seed = ITEMS.register("eyeball_seed", () -> new ItemNameBlockItem(ModBlocks.Eyeball_crop.get(), new Item.Properties()));
    public static final RegistryObject<Item> Eyeball = ITEMS.register("eyeball", () ->
            new Item(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(3).saturationMod(2f)
                            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 6000, 0), 1.0f)
                            .build())
            )
    );
    public static final RegistryObject<Item> BLOOD_BUCKET = ITEMS.register("blood_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> BLOOD_SCYTHE = ITEMS.register("blood_scythe",
            () -> new BloodScytheItem(Tiers.NETHERITE, 10, 5f,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BLOOD_SWORD = ITEMS.register("blood_sword",() -> new SwordItem(Tiers.DIAMOND,8,5,
            new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SOUL_SWORD = ITEMS.register("soul_sword",() -> new SoulItem(Tiers.NETHERITE,11,6,
            new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BLOOD_PICKAXE = ITEMS.register("blood_pickaxe",() -> new PickaxeItem(Tiers.DIAMOND,5,5,
            new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SOUL_PICKAXE = ITEMS.register("soul_pickaxe",() -> new PickaxeItem(Tiers.NETHERITE,6,6,
            new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BLOODTHIRSTYBEAST_SPAWN_EGG = ITEMS.register("bloodthirstybeast_spawn_egg",() -> new ForgeSpawnEggItem(ModEntityTypes.BLOODTHIRSTYBEAST,0x400303,0x00FFA0,
            new Item.Properties()));
    public static final RegistryObject<Item> BLOOD_SEEKER_SPAWN_EGG = ITEMS.register("bloodseeker_spawn_egg",() -> new ForgeSpawnEggItem(ModEntityTypes.BLOOD_SEEKER,0x660000 ,0xFFDD00,
            new Item.Properties()));
    public static final RegistryObject<Item> EYESHELLSNAIL_SPAWN_EGG = ITEMS.register("eyeshellsnail_spawn_egg",() -> new ForgeSpawnEggItem(ModEntityTypes.EYESHELL_SNAIL,0xFF0000  ,0xFFB400 ,
            new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_RAVEN_SPAWN_EGG = ITEMS.register("crimsonraven_spawn_egg",() -> new ForgeSpawnEggItem(ModEntityTypes.CRIMSON_RAVEN,0xFF3434 ,0xFCFF00,
            new Item.Properties()));
    public static final RegistryObject<Item> MATERIALIZED_SOUL = ITEMS.register("materialized_soul", () -> new CatalystItem());

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
