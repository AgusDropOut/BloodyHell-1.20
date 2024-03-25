package net.agusdropout.firstmod;

import com.mojang.logging.LogUtils;
import net.agusdropout.firstmod.block.ModBlocks;
import net.agusdropout.firstmod.block.entity.ModBlockEntities;
import net.agusdropout.firstmod.capability.IBloodPortal;
import net.agusdropout.firstmod.effect.ModEffects;
import net.agusdropout.firstmod.entity.ModEntityTypes;
import net.agusdropout.firstmod.entity.client.BloodSeekerRenderer;
import net.agusdropout.firstmod.entity.client.BloodThirstyBeastRenderer;
import net.agusdropout.firstmod.entity.client.CrimsonRavenRenderer;
import net.agusdropout.firstmod.entity.client.EyeshellSnailRenderer;
import net.agusdropout.firstmod.fluid.ModFluidTypes;
import net.agusdropout.firstmod.fluid.ModFluids;
import net.agusdropout.firstmod.item.ModCreativeModeTab;
import net.agusdropout.firstmod.item.ModItems;
import net.agusdropout.firstmod.networking.ModMessages;
import net.agusdropout.firstmod.painting.ModPaintings;
import net.agusdropout.firstmod.particle.ModParticles;
import net.agusdropout.firstmod.recipe.ModRecipes;
import net.agusdropout.firstmod.registry.BloodCapabilities;
import net.agusdropout.firstmod.screen.BloodWorkBenchScreen;
import net.agusdropout.firstmod.screen.ModMenuTypes;
import net.agusdropout.firstmod.villager.ModPOIs;
import net.agusdropout.firstmod.worldgen.biome.ModTerrablender;
import net.agusdropout.firstmod.worldgen.structure.ModStructures;
import net.agusdropout.firstmod.worldgen.tree.ModTreeDecoratorTypes;
import net.agusdropout.firstmod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FirstMod.MODID)
public class FirstMod
{
    public static final String MODID = "firstmod";

    private static final Logger LOGGER = LogUtils.getLogger();


    public FirstMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.addListener(this::portalTick);
        ModCreativeModeTab.register(modEventBus);
        ModPOIs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        //ModVillagers.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModParticles.register(modEventBus);

        ModTerrablender.registerBiomes();


        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModTrunkPlacerTypes.register(modEventBus);
        ModEffects.register(modEventBus);
        ModTreeDecoratorTypes.register(modEventBus);
        ModStructures.register(modEventBus);





        GeckoLib.initialize();








    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.BLOOD_SEEKER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.BLOODTHIRSTYBEAST.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CRIMSON_RAVEN.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,
                    Animal::checkMobSpawnRules);
            SpawnPlacements.register(ModEntityTypes.EYESHELL_SNAIL.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,
                    Animal::checkMobSpawnRules);

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.BLOOD_FLOWER.getId(),ModBlocks.POTTED_BLOOD_FLOWER);
            ModMessages.register();
           //ModVillagers.registerPOIs();



            //SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, ModSurfaceRules.makeRules());
        });




    }
    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTab() == ModCreativeModeTab.FIRST_TAB.get()){
            event.accept(ModItems.soul);
            event.accept(ModItems.RAW_soul);
            event.accept(ModItems.BLOOD_BUCKET);
            event.accept(ModItems.BLOODTHIRSTYBEAST_SPAWN_EGG);
            event.accept(ModItems.BLOOD_SEEKER_SPAWN_EGG);
            event.accept(ModItems.BLOODY_SOUL_DUST);
            event.accept(ModItems.MATERIALIZED_SOUL);
            event.accept(ModItems.BLOOD_PICKAXE);
            event.accept(ModItems.BLOOD_SWORD);
            event.accept(ModItems.BLOOD_SCYTHE);
            event.accept(ModItems.Eight_ball);
            event.accept(ModItems.Eyeball);
            event.accept(ModItems.Eyeball_seed);
            event.accept(ModItems.SOUL_PICKAXE);
            event.accept(ModItems.SOUL_SWORD);
            event.accept(ModItems.CRIMSON_RAVEN_SPAWN_EGG);
            event.accept(ModItems.EYESHELLSNAIL_SPAWN_EGG);




            event.accept(ModBlocks.BLOOD_WORKBENCH);
            event.accept(ModBlocks.Jumpy_Block);
            event.accept(ModBlocks.soul_BLOCK);
            event.accept(ModBlocks.DEEPSLATE_soul_ORE);
            event.accept(ModBlocks.soul_Lamp);
            event.accept(ModBlocks.soul_ORE);
            event.accept(ModBlocks.BLOOD_SAPLING);
            event.accept(ModBlocks.SOUL_SAPLING);
            event.accept(ModBlocks.BLOOD_SMALL_ROCKS);
            event.accept(ModBlocks.BLEEDING_BLOCK);



            event.accept(ModBlocks.BLOOD_LOG);
            event.accept(ModBlocks.STRIPPED_BLOOD_LOG);
            event.accept(ModBlocks.SOUL_LOG);
            event.accept(ModBlocks.STRIPPED_SOUL_LOG);



            event.accept(ModBlocks.BLOOD_PLANKS);
            event.accept(ModBlocks.BLOOD_LEAVES);
            event.accept(ModBlocks.SOUL_PLANKS);
            event.accept(ModBlocks.SOUL_LEAVES);
            event.accept(ModBlocks.HANGING_SOUL_TREE_LEAVES);

            event.accept(ModBlocks.BLOOD_FLOWER);
            event.accept(ModBlocks.BLOOD_BUSH);
            event.accept(ModBlocks.BLOOD_PETALS);


            event.accept(ModBlocks.EYEBALLSHELL_SNAIL_GOO_BLOCK);
            event.accept(ModBlocks.EYEBALLSHELL_SNAIL_GOO);




        }

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {


        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), RenderType.translucent());  //RENDERIZADO DE LOS LIQUIDOS PARA QUE SEAN TRANSPARNETES
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(), RenderType.translucent());
            MenuScreens.register(ModMenuTypes.BLOOD_WORKBENCH_MENU.get(), BloodWorkBenchScreen::new);
            EntityRenderers.register(ModEntityTypes.BLOOD_SEEKER.get(), BloodSeekerRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOODTHIRSTYBEAST.get(), BloodThirstyBeastRenderer::new);
            EntityRenderers.register(ModEntityTypes.CRIMSON_RAVEN.get(), CrimsonRavenRenderer::new);
            EntityRenderers.register(ModEntityTypes.EYESHELL_SNAIL.get(), EyeshellSnailRenderer::new);

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLOOD_BUSH.get(), RenderType::canConsolidateConsecutiveGeometry);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLOOD_PETALS.get(), RenderType.translucent());


        }
    }
    public void portalTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            player.getCapability(BloodCapabilities.BLOOD_PORTAL_CAPABILITY).ifPresent(IBloodPortal::handleBloodPortal);
        }
    }
}
