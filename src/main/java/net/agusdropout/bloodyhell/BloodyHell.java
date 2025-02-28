package net.agusdropout.bloodyhell;

import com.mojang.logging.LogUtils;

import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.block.entity.ModBlockEntities;
import net.agusdropout.bloodyhell.capability.IBloodPortal;
import net.agusdropout.bloodyhell.client.render.BloodDimensionRenderInfo;
import net.agusdropout.bloodyhell.datagen.ModEntityTagGenerator;
import net.agusdropout.bloodyhell.datagen.ModTags;
import net.agusdropout.bloodyhell.effect.ModEffects;
import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.client.*;

import net.agusdropout.bloodyhell.entity.projectile.BloodProjectileEntity;
import net.agusdropout.bloodyhell.fluid.ModFluidTypes;
import net.agusdropout.bloodyhell.fluid.ModFluids;
import net.agusdropout.bloodyhell.item.ModCreativeModeTab;
import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.item.client.BloodAltarItemRenderer;
import net.agusdropout.bloodyhell.item.custom.ModArmorItem;
import net.agusdropout.bloodyhell.networking.ModMessages;
import net.agusdropout.bloodyhell.painting.ModPaintings;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.agusdropout.bloodyhell.potion.ModPotions;
import net.agusdropout.bloodyhell.recipe.ModRecipes;
import net.agusdropout.bloodyhell.registry.BloodCapabilities;
import net.agusdropout.bloodyhell.screen.BloodWorkBenchScreen;
import net.agusdropout.bloodyhell.screen.ModMenuTypes;
import net.agusdropout.bloodyhell.screen.VesperScreen;
import net.agusdropout.bloodyhell.util.ModItemProperties;
import net.agusdropout.bloodyhell.villager.ModPOIs;
import net.agusdropout.bloodyhell.worldgen.biome.surface.ModSurfaceRules;
import net.agusdropout.bloodyhell.worldgen.dimension.ModDimensions;
import net.agusdropout.bloodyhell.worldgen.feature.ModFeatures;
import net.agusdropout.bloodyhell.worldgen.structure.ModStructures;

import net.agusdropout.bloodyhell.worldgen.tree.ModTreeDecoratorTypes;
import net.agusdropout.bloodyhell.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
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

import java.util.Locale;


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
        MinecraftForge.EVENT_BUS.addListener(this::registerDimEffects);

        MinecraftForge.EVENT_BUS.addListener(this::portalTick);
        ModCreativeModeTab.register(modEventBus);
        GeckoLib.initialize();
        ModPOIs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        //ModVillagers.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModParticles.register(modEventBus);
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
        ModFeatures.register(modEventBus);
        ModPotions.register(modEventBus);















    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

        event.enqueueWork(() -> {
            SpawnPlacements.register(net.agusdropout.bloodyhell.entity.ModEntityTypes.BLOOD_SEEKER.get(),
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
            SpawnPlacements.register(ModEntityTypes.BLOODPIG.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,
                    Animal::checkMobSpawnRules);


            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.BLOOD_FLOWER.getId(),ModBlocks.POTTED_BLOOD_FLOWER);
            ModMessages.register();
            ModItemProperties.addCustomItemProperties();
           //ModVillagers.registerPOIs();
            MinecraftForge.EVENT_BUS.addListener(this::onPlayerInteract);






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
    private void registerDimEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(ModDimensions.DIMENSION_RENDERER, new BloodDimensionRenderInfo(128.0F, false, DimensionSpecialEffects.SkyType.NONE, false, false));
    }
    private void addCreative(BuildCreativeModeTabContentsEvent event){

        if(event.getTab() == ModCreativeModeTab.FIRST_TAB.get()){
            //Sanguinite Items
            event.accept(ModItems.RAW_SANGUINITE);
            event.accept(ModItems.SANGUINITE);
            event.accept(ModItems.SANGUINITE_NUGGET);
            event.accept(ModItems.SANGUINITE_PICKAXE);
            event.accept(ModItems.SANGUINITE_SWORD);
            event.accept(ModItems.SANGUINITE_AXE);
            event.accept(ModItems.SANGUINITE_SHOVEL);
            event.accept(ModItems.SANGUINITE_HOE);
            event.accept(ModItems.BLOOD_BOW);
            event.accept(ModItems.BLOOD_ARROW);
            event.accept(ModItems.BLOOD_HELMET);
            event.accept(ModItems.BLOOD_CHESTPLATE);
            event.accept(ModItems.BLOOD_LEGGINGS);
            event.accept(ModItems.BLOOD_BOOTS);
            event.accept(ModItems.BLOOD_BUCKET);
            event.accept(ModItems.RHNULL_BLOOD_BUCKET);
            event.accept(ModItems.BLOOD_SCYTHE);


            //Rhnull Items
            event.accept(ModItems.RHNULL);
            event.accept(ModItems.RHNULL_NUGGET);
            event.accept(ModBlocks.RHNULL_BLOCK);
            event.accept(ModItems.RHNULL_PICKAXE);
            event.accept(ModItems.RHNULL_SWORD);
            event.accept(ModItems.RHNULL_AXE);
            event.accept(ModItems.RHNULL_SHOVEL);
            event.accept(ModItems.RHNULL_HOE);


            //Spawn Eggs
            event.accept(ModItems.BLOODTHIRSTYBEAST_SPAWN_EGG);
            event.accept(ModItems.BLOOD_SEEKER_SPAWN_EGG);
            event.accept(ModItems.CRIMSON_RAVEN_SPAWN_EGG);
            event.accept(ModItems.EYESHELLSNAIL_SPAWN_EGG);
            event.accept(ModItems.BLOOD_PIG_SPAWN_EGG);
            event.accept(ModItems.SCARLETSPECKLED_FISH_SPAWN_EGG);

            //Food Items
            event.accept(ModItems.Eyeball);
            event.accept(ModItems.Eyeball_seed);
            event.accept(ModItems.GLOW_FRUIT);
            event.accept(ModItems.GLOW_MUSHROOM);
            event.accept(ModItems.SCARLET_RAW_CHICKEN);
            event.accept(ModItems.GOREHOG_RAW_STEAK);

            //Mob Drops
            event.accept(ModItems.AUREAL_REVENANT_DAGGER);
            event.accept(ModItems.VEINREAVER_HORN);
            event.accept(ModItems.CRIMSON_SHELL);
            event.accept(ModItems.SCARLET_FEATHER);

            //Misc Items
            event.accept(ModItems.BLOODY_SOUL_DUST);
            event.accept(ModItems.MATERIALIZED_SOUL);
            event.accept(ModItems.CRIMSON_IDOL_COIN);
            event.accept(ModItems.CHALICE_OF_THE_DAMMED);
            event.accept(ModItems.Eight_ball);
            event.accept(ModItems.DIRTY_BLOOD_FLOWER);
            event.accept(ModItems.BLOOD_LILY);
            event.accept(ModItems.SANGUINE_CRUCIBLE_CORE);

            //Sanguinite
            event.accept(ModBlocks.SANGUINE_CRUCIBLE);
            event.accept(ModBlocks.SANGUINITE_BLOCK);

            //Ores
            event.accept(ModBlocks.SANGUINITE_ORE);
            event.accept(ModBlocks.Jumpy_Block);

            //Vegetation
            event.accept(ModBlocks.BLOOD_SAPLING);
            event.accept(ModBlocks.SOUL_SAPLING);
            event.accept(ModBlocks.HANGING_BLOOD_TREE_LEAVES);
            event.accept(ModBlocks.HANGING_SOUL_TREE_LEAVES);
            event.accept(ModBlocks.BLOOD_FLOWER);
            event.accept(ModBlocks.BLOOD_GRASS);
            event.accept(ModBlocks.BLOOD_BUSH);
            event.accept(ModBlocks.BLOOD_PETALS);
            event.accept(ModBlocks.BLOOD_WALL_MUSHROOM_BLOCK);
            event.accept(ModBlocks.LIGHT_MUSHROOM_BLOCK);

            //Dirt
            event.accept(ModBlocks.BLOOD_GRASS_BLOCK);
            event.accept(ModBlocks.BLOOD_DIRT_BLOCK);
            event.accept(ModBlocks.BLOOD_SCRAPPER_PLANT);


            //Bloody Stone
            event.accept(ModBlocks.BLOODY_STONE_BLOCK);
            event.accept(ModBlocks.BLOODY_STONE_STAIRS);
            event.accept(ModBlocks.BLOODY_STONE_WALL);
            event.accept(ModBlocks.BLOODY_STONE_FENCE);
            event.accept(ModBlocks.BLOODY_STONE_FENCE_GATE);
            event.accept(ModBlocks.BLOODY_STONE_SLAB);

            //Polished Bloody Stone
            event.accept(ModBlocks.POLISHED_BLOODY_STONE_BLOCK);
            event.accept(ModBlocks.POLISHED_BLOODY_STONE_STAIRS);
            event.accept(ModBlocks.POLISHED_BLOODY_STONE_WALL);
            event.accept(ModBlocks.POLISHED_BLOODY_STONE_FENCE);
            event.accept(ModBlocks.POLISHED_BLOODY_STONE_FENCE_GATE);
            event.accept(ModBlocks.POLISHED_BLOODY_STONE_SLAB);

            //Bloody Stone Tiles
            event.accept(ModBlocks.BLOODY_STONE_TILES_BLOCK);
            event.accept(ModBlocks.BLOODY_STONE_TILES_STAIRS);
            event.accept(ModBlocks.BLOODY_STONE_TILES_WALL);
            event.accept(ModBlocks.BLOODY_STONE_TILES_FENCE);
            event.accept(ModBlocks.BLOODY_STONE_FENCE_TILES_GATE);
            event.accept(ModBlocks.BLOODY_STONE_TILES_SLAB);

            //Bloody Stone Bricks
            event.accept(ModBlocks.BLOODY_STONE_BRICKS);
            event.accept(ModBlocks.BLOODY_STONE_BRICKS_STAIRS);
            event.accept(ModBlocks.BLOODY_STONE_BRICKS_WALL);
            event.accept(ModBlocks.BLOODY_STONE_BRICKS_FENCE);
            event.accept(ModBlocks.BLOODY_STONE_FENCE_BRICKS_GATE);
            event.accept(ModBlocks.BLOODY_STONE_BRICKS_SLAB);

            //Wood
            event.accept(ModBlocks.BLOOD_LOG);
            event.accept(ModBlocks.STRIPPED_BLOOD_LOG);
            event.accept(ModBlocks.BLOOD_PLANKS);
            event.accept(ModBlocks.BLOOD_PLANKS_STAIRS);
            event.accept(ModBlocks.BLOOD_PLANKS_SLAB);
            event.accept(ModBlocks.BLOOD_PLANKS_FENCE);
            event.accept(ModBlocks.BLOOD_PLANKS_FENCE_GATE);
            event.accept(ModBlocks.BLOOD_LEAVES);
            event.accept(ModBlocks.SOUL_LOG);
            event.accept(ModBlocks.STRIPPED_SOUL_LOG);
            event.accept(ModBlocks.SOUL_PLANKS);
            event.accept(ModBlocks.SOUL_LEAVES);

            //Glowing
            event.accept(ModBlocks.GLOWING_CRYSTAL);
            event.accept(ModBlocks.GLOWING_CRYSTAL_GLASS_BLOCK);
            event.accept(ModBlocks.GLOWING_CRYSTAL_LANTERN);
            event.accept(ModBlocks.BLOOD_GLOWING_CHAINS_BLOCK);
            event.accept(ModBlocks.SOUL_LAMP);
            event.accept(ModBlocks.BLOOD_GLOW_STONE);

            //Mob Generated
            event.accept(ModBlocks.EYEBALLSHELL_SNAIL_GOO_BLOCK);
            event.accept(ModBlocks.EYEBALLSHELL_SNAIL_GOO);

            //Misc
            event.accept(ModBlocks.SMALL_ROCKS);
            event.accept(ModBlocks.BLEEDING_BLOCK);
            event.accept(ModBlocks.ONI_STATUE);

            //Animated Block Items
            event.accept(ModItems.BLOOD_ALTAR);
            event.accept(ModBlocks.BLOOD_ALTAR);
            event.accept(ModItems.MAIN_BLOOD_ALTAR);
            event.accept(ModBlocks.MAIN_BLOOD_ALTAR);

            //Potions
            event.accept(ModItems.BLOOD_FLASK);
            event.accept(ModItems.CORRUPTED_BLOOD_FLASK);
            event.accept(ModItems.FILLED_BLOOD_FLASK);
            event.accept(ModItems.FILLED_RHNULL_BLOOD_FLASK);

            //Dagger
            event.accept(ModItems.SACRIFICIAL_DAGGER);
            event.accept(ModItems.HERETIC_SACRIFICIAL_DAGGER);

            //Unknown Entity
            event.accept(ModItems.UNKNOWN_ENTITY_FINGER);

            //Spell Book
            event.accept(ModItems.BLOOD_SPELL_BOOK_SCRATCH);
            event.accept(ModItems.BLOOD_SPELL_BOOK_BLOODBALL);
            event.accept(ModItems.BLOOD_SPELL_BOOK_BLOODNOVA);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SANGUINITE);
            event.accept(ModItems.RAW_SANGUINITE);
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
            MenuScreens.register(ModMenuTypes.VESPER_MENU.get(), VesperScreen::new);
            EntityRenderers.register(ModEntityTypes.BLOOD_SEEKER.get(), BloodSeekerRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOODY_SOUL_ENTITY.get(), BloodySoulEntityRenderer::new);
            EntityRenderers.register(ModEntityTypes.CORRUPTED_BLOODY_SOUL_ENTITY.get(), CorruptedBloodySoulEntityRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOODTHIRSTYBEAST.get(), BloodThirstyBeastRenderer::new);
            EntityRenderers.register(ModEntityTypes.ONI.get(), OniRenderer::new);
            EntityRenderers.register(ModEntityTypes.SCARLETSPECKLED_FISH.get(), ScarletSpeckledFishRenderer::new);
            EntityRenderers.register(ModEntityTypes.CRIMSON_RAVEN.get(), CrimsonRavenRenderer::new);
            EntityRenderers.register(ModEntityTypes.EYESHELL_SNAIL.get(), EyeshellSnailRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOODPIG.get(), BloodPigRenderer::new);
            EntityRenderers.register(ModEntityTypes.CRYSTAL_PILLAR.get(), CrystalPillarRenderer::new);
            EntityRenderers.register(ModEntityTypes.UNKNOWN_EYE_ENTITY.get(), UnknownEyeEntityRenderer::new);
            EntityRenderers.register(ModEntityTypes.UNKNOWN_ENTITY_ARMS.get(), UnknownEntityArmsRenderer::new);
            EntityRenderers.register(ModEntityTypes.SANGUINE_SACRIFICE_ENTITY.get(), SanguineSacrificeEntityRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOOD_SLASH_ENTITY.get(), BloodSlashRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOOD_ARROW.get(), BloodArrowRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.BH_CHEST.get(),BHChestRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOOD_ARROW.get(), BloodArrowRenderer::new);
            EntityRenderers.register(ModEntityTypes.VESPER.get(), VesperRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOOD_PROJECTILE.get(), BloodProjectileEntityRenderer::new);
            EntityRenderers.register(ModEntityTypes.BLOOD_NOVA_ENTITY.get(), BloodNovaEntityRenderer::new);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLOOD_BUSH.get(), RenderType::canConsolidateConsecutiveGeometry);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLOOD_PETALS.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SANGUINE_CRUCIBLE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_MUSHROOM_BLOCK.get(), RenderType.translucent());
            BlockEntityRenderers.register(ModBlockEntities.BLOOD_ALTAR.get(), BloodAltarRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.MAIN_BLOOD_ALTAR.get(), MainBloodAltarRenderer::new);



        }



    }
    public void portalTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            player.getCapability(BloodCapabilities.BLOOD_PORTAL_CAPABILITY).ifPresent(IBloodPortal::handleBloodPortal);
        }
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
    }
}
