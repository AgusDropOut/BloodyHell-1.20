package net.agusdropout.bloodyhell;

import com.mojang.logging.LogUtils;
import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.block.entity.ModBlockEntities;
import net.agusdropout.bloodyhell.capability.IBloodPortal;
import net.agusdropout.bloodyhell.effect.ModEffects;
import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.client.*;
import net.agusdropout.bloodyhell.fluid.ModFluidTypes;
import net.agusdropout.bloodyhell.fluid.ModFluids;
import net.agusdropout.bloodyhell.item.ModCreativeModeTab;
import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.networking.ModMessages;
import net.agusdropout.bloodyhell.painting.ModPaintings;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.agusdropout.bloodyhell.recipe.ModRecipes;
import net.agusdropout.bloodyhell.registry.BloodCapabilities;
import net.agusdropout.bloodyhell.screen.BloodWorkBenchScreen;
import net.agusdropout.bloodyhell.screen.ModMenuTypes;
import net.agusdropout.bloodyhell.villager.ModPOIs;
import net.agusdropout.bloodyhell.worldgen.biome.ModTerrablender;
import net.agusdropout.bloodyhell.worldgen.biome.surface.ModSurfaceRules;
import net.agusdropout.bloodyhell.worldgen.structure.ModStructures;
import net.agusdropout.bloodyhell.worldgen.tree.ModTreeDecoratorTypes;
import net.agusdropout.bloodyhell.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;
import terrablender.api.SurfaceRuleManager;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(BloodyHell.MODID)
public class BloodyHell
{
    public static final String MODID = "bloodyhell";

    private static final Logger LOGGER = LogUtils.getLogger();


    public BloodyHell()
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
            SpawnPlacements.register(ModEntityTypes.SCARLETSPECKLED_FISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR,
                    AbstractFish::checkMobSpawnRules);


            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.BLOOD_FLOWER.getId(),ModBlocks.POTTED_BLOOD_FLOWER);
            ModMessages.register();
           //ModVillagers.registerPOIs();
            MinecraftForge.EVENT_BUS.addListener(this::onPlayerInteract);



            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, ModSurfaceRules.makeRules());
        });


    }
    private void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        Player player = event.getEntity();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        ItemStack heldItem = player.getMainHandItem();
        if (heldItem.getItem() instanceof HoeItem && (block == ModBlocks.BLOOD_GRASS_BLOCK.get() || block == ModBlocks.BLOOD_DIRT_BLOCK.get())) {
            player.swing(InteractionHand.MAIN_HAND);
            // Transformar el bloque en BLOODDIRT_FARMLAND
            level.setBlockAndUpdate(pos, ModBlocks.BLOODDIRT_FARMLAND.get().defaultBlockState());

            // Dañar la azada
            heldItem.hurtAndBreak(1, player, (playerEntity) -> playerEntity.broadcastBreakEvent(playerEntity.getUsedItemHand()));

            // Detener la propagación del evento para evitar que se realice la acción predeterminada de la azada
            event.setCanceled(true);
        }

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
            event.accept(ModItems.BLOOD_PIG_SPAWN_EGG);
            event.accept(ModItems.SCARLETSPECKLED_FISH_SPAWN_EGG);
            event.accept(ModItems.DIRTY_BLOOD_FLOWER);
            event.accept(ModItems.BLOOD_LILY);
            event.accept(ModItems.BLOOD_PIG_SPAWN_EGG);




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
            event.accept(ModBlocks.BLOOD_GRASS_BLOCK);
            event.accept(ModBlocks.BLOOD_DIRT_BLOCK);
            event.accept(ModBlocks.BLOODY_STONE_BLOCK);
            event.accept(ModBlocks.POLISHED_BLOODY_STONE_BLOCK);
            event.accept(ModBlocks.BLOODY_STONE_TILES_BLOCK);
            event.accept(ModBlocks.BLOODY_STONE_STAIRS);
            event.accept(ModBlocks.BLOODY_STONE_WALL);
            event.accept(ModBlocks.BLOODY_STONE_FENCE);
            event.accept(ModBlocks.BLOODY_STONE_FENCE_GATE);
            event.accept(ModBlocks.BLOODY_STONE_SLAB);
            event.accept(ModBlocks.HANGING_BLOOD_TREE_LEAVES);







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
            event.accept(ModBlocks.BLOOD_GRASS);
            event.accept(ModBlocks.BLOOD_BUSH);
            event.accept(ModBlocks.BLOOD_PETALS);
            event.accept(ModBlocks.BLOOD_WALL_MUSHROOM_BLOCK);
            event.accept(ModBlocks.LIGHT_MUSHROOM_BLOCK);


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

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_BLOOD.get(), RenderType.translucent());  //RENDERIZADO DE LOS LIQUIDOS PARA QUE SEAN TRANSPARNETES
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_BLOOD.get(), RenderType.translucent());
            MenuScreens.register(ModMenuTypes.BLOOD_WORKBENCH_MENU.get(), BloodWorkBenchScreen::new);
            EntityRenderers.register(ModEntityTypes.BLOOD_SEEKER.get(), BloodSeekerRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOODTHIRSTYBEAST.get(), BloodThirstyBeastRenderer::new);
            EntityRenderers.register(ModEntityTypes.SCARLETSPECKLED_FISH.get(), ScarletSpeckledFishRenderer::new);
            EntityRenderers.register(ModEntityTypes.CRIMSON_RAVEN.get(), CrimsonRavenRenderer::new);
            EntityRenderers.register(ModEntityTypes.EYESHELL_SNAIL.get(), EyeshellSnailRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOODPIG.get(), BloodPigRenderer::new);

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
