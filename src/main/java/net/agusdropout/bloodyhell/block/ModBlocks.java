package net.agusdropout.bloodyhell.block;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.block.base.BHChestBlock;
import net.agusdropout.bloodyhell.block.base.BaseWallPlantBlock;
import net.agusdropout.bloodyhell.block.base.TallGrowingPlant;
import net.agusdropout.bloodyhell.block.custom.*;
import net.agusdropout.bloodyhell.fluid.ModFluids;
import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.worldgen.tree.BloodTreeGrower;
import net.agusdropout.bloodyhell.worldgen.tree.GiantBloodTreeGrower;
import net.agusdropout.bloodyhell.worldgen.tree.SmallBloodTreeGrower;
import net.agusdropout.bloodyhell.worldgen.tree.SoulTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BloodyHell.MODID);
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    //Sanguinite
    public static final RegistryObject<Block> SANGUINITE_BLOCK = registerBlock("sanguinite_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f).requiresCorrectToolForDrops()));

    //Rhnull
    public static final RegistryObject<Block> RHNULL_BLOCK = registerBlock("rhnull_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f).requiresCorrectToolForDrops()));


    //Bloody Stone
    public static final RegistryObject<Block> BLOODY_STONE_BLOCK = registerBlock("bloody_stone_block", ()-> new Block(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOODY_STONE_STAIRS = registerBlock("bloody_stone_stairs", () -> new StairBlock(() -> ModBlocks.BLOODY_STONE_BLOCK.get().defaultBlockState(),
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static final RegistryObject<Block> BLOODY_STONE_SLAB = registerBlock("bloody_stone_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_FENCE)));
    public static final RegistryObject<Block> BLOODY_STONE_FENCE = registerBlock("bloody_stone_fence", () -> new FenceBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static final RegistryObject<Block> BLOODY_STONE_FENCE_GATE = registerBlock("bloody_stone_fence_gate", () -> new FenceGateBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS), SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> BLOODY_STONE_WALL = registerBlock("bloody_stone_wall", () -> new WallBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));


    //Polished Bloody Stone
    public static final RegistryObject<Block> POLISHED_BLOODY_STONE_BLOCK = registerBlock("polished_bloody_stone_block", ()-> new Block(
            BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> POLISHED_BLOODY_STONE_STAIRS = registerBlock("polished_bloody_stone_stairs", () -> new StairBlock(() -> ModBlocks.POLISHED_BLOODY_STONE_BLOCK.get().defaultBlockState(),
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> POLISHED_BLOODY_STONE_SLAB = registerBlock("polished_bloody_stone_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_FENCE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> POLISHED_BLOODY_STONE_FENCE = registerBlock("polished_bloody_stone_fence", () -> new FenceBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> POLISHED_BLOODY_STONE_FENCE_GATE = registerBlock("polished_bloody_stone_fence_gate", () -> new FenceGateBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops(), SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> POLISHED_BLOODY_STONE_WALL = registerBlock("polished_bloody_stone_wall", () -> new WallBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops()));

    //Bloody Stone Tiles
    public static final RegistryObject<Block> BLOODY_STONE_TILES_BLOCK = registerBlock("bloody_stone_tiles_block", ()-> new Block(
            BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOODY_STONE_TILES_STAIRS = registerBlock("bloody_stone_tiles_stairs", () -> new StairBlock(() -> ModBlocks.BLOODY_STONE_TILES_BLOCK.get().defaultBlockState(),
            BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_STAIRS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOODY_STONE_TILES_SLAB = registerBlock("bloody_stone_tiles_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOODY_STONE_TILES_FENCE = registerBlock("bloody_stone_tiles_fence", () -> new FenceBlock(
            BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOODY_STONE_FENCE_TILES_GATE = registerBlock("bloody_stone_fence_tiles_gate", () -> new FenceGateBlock(
            BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops(), SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> BLOODY_STONE_TILES_WALL = registerBlock("bloody_stone_tiles_wall", () -> new WallBlock(
            BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()));

    //Bloody Stone Bricks
    public static final RegistryObject<Block> BLOODY_STONE_BRICKS = registerBlock("bloody_stone_bricks", ()-> new Block(
            BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_TILES).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOODY_STONE_BRICKS_STAIRS = registerBlock("bloody_stone_bricks_stairs", () -> new StairBlock(() -> ModBlocks.BLOODY_STONE_BRICKS.get().defaultBlockState(),
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOODY_STONE_BRICKS_SLAB = registerBlock("bloody_stone_bricks_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_FENCE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOODY_STONE_BRICKS_FENCE = registerBlock("bloody_stone_bricks_fence", () -> new FenceBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOODY_STONE_FENCE_BRICKS_GATE = registerBlock("bloody_stone_fence_bricks_gate", () -> new FenceGateBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops(), SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> BLOODY_STONE_BRICKS_WALL = registerBlock("bloody_stone_bricks_wall", () -> new WallBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops()));


    //Blood Grass
    public static final RegistryObject<Block> BLOOD_GRASS_BLOCK = registerBlock("blood_grass_block", ()-> new BloodGrassBlock(
            BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).strength(2f).randomTicks().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> BLOODDIRT_FARMLAND = registerBlock("blooddirt_farmland", () -> new BloodDirtFarmlandBlock(
            BlockBehaviour.Properties.copy(Blocks.FARMLAND)));
    public static final RegistryObject<Block> BLOOD_DIRT_BLOCK = registerBlock("blood_dirt_block", ()-> new Block(
            BlockBehaviour.Properties.copy(Blocks.DIRT).strength(2f)));



    //Ores
    public static final RegistryObject<Block> SANGUINITE_ORE = registerBlock("sanguinite_ore", ()-> new DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)));




    //Mob generated
    public static final RegistryObject<Block> EYEBALLSHELL_SNAIL_GOO = registerBlock("eyeballshell_snail_goo", () -> new GooLayerBlock(
            BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).strength(6f).randomTicks().replaceable().noCollission().noOcclusion()));
    public static final RegistryObject<Block> EYEBALLSHELL_SNAIL_GOO_BLOCK = registerBlock("eyeballshell_snail_goo_block", () -> new GooBlock(
            BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).strength(6f) ));



    //Vegetation
    public static final RegistryObject<Block> EYEBALL_CROP = BLOCKS.register("eyeball_crop", ()-> new EyeballCropBlock(
            BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> BLOOD_SAPLING = registerBlock("blood_sapling", ()-> new SaplingBlock(new BloodTreeGrower(),
            BlockBehaviour.Properties.copy(Blocks.CHERRY_SAPLING)));
    public static final RegistryObject<Block> SMALL_BLOOD_SAPLING = registerBlock("small_blood_sapling", ()-> new SaplingBlock(new SmallBloodTreeGrower(),
            BlockBehaviour.Properties.copy(Blocks.CHERRY_SAPLING)));
    public static final RegistryObject<Block> HANGING_SOUL_TREE_LEAVES = registerBlock("hanging_soultree_leaves", ()-> new HangingSoulTreeLeavesBlock(
            BlockBehaviour.Properties.copy(Blocks.VINE)));
    public static final RegistryObject<Block> HANGING_BLOOD_TREE_LEAVES = registerBlock("hanging_bloodtree_leaves", ()-> new HangingSoulTreeLeavesBlock(
            BlockBehaviour.Properties.copy(Blocks.VINE).lightLevel((state)->15)) );
    public static final RegistryObject<Block> SOUL_SAPLING = registerBlock("soul_sapling", ()-> new SaplingBlock(new SoulTreeGrower(),
            BlockBehaviour.Properties.copy(Blocks.CHERRY_SAPLING)));
    public static final RegistryObject<Block> BLOOD_FLOWER = registerBlock("blood_flower", ()-> new FlowerBlock(()-> MobEffects.POISON,5,
            BlockBehaviour.Properties.copy(Blocks.ALLIUM).noCollission().noOcclusion().lightLevel((state)->6)));
    public static final RegistryObject<Block> BLOOD_GRASS = registerBlock("blood_grass", ()-> new FlowerBlock(()-> MobEffects.POISON,5,
            BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().noOcclusion()));
    public static final RegistryObject<Block> POTTED_BLOOD_FLOWER = BLOCKS.register("potted_blood_flower", ()-> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT) ,ModBlocks.BLOOD_FLOWER,
            BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final RegistryObject<Block> BLOOD_BUSH = registerBlock("blood_bush", ()-> new DoublePlantBlock(
            BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH)));
    public static final RegistryObject<Block> BLOOD_LILY_BLOCK = registerBlock("blood_lily_block", ()-> new BloodLilyBlock(
            BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_PINK).noOcclusion().noCollission().lightLevel((state)->20).instabreak().sound(SoundType.LILY_PAD).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Block> BLOOD_PETALS = registerBlock("blood_petals", ()-> new FlowerBlock(()-> MobEffects.HARM,5,
            BlockBehaviour.Properties.copy(Blocks.ALLIUM).noCollission().noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIGHT_MUSHROOM_BLOCK = registerBlock("light_mushroom_block", ()-> new FlowerBlock(()-> MobEffects.POISON,5,
            BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK).noCollission().noOcclusion().lightLevel((state)->15)));
    public static final RegistryObject<Block> BLOOD_WALL_MUSHROOM_BLOCK = registerBlock("blood_wall_mushroom_block", ()-> new BaseWallPlantBlock(
            BlockBehaviour.Properties.copy(Blocks.COCOA).noOcclusion().dynamicShape().lightLevel( (state)->15 )) {
                @Override
                protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
                    super.createBlockStateDefinition(stateManager);
                }
            });
    public static final RegistryObject<Block> DROOPVINE = registerBlock("droopvine", () -> new DroopvineBlock(
            BlockBehaviour.Properties.copy(Blocks.CAVE_VINES).strength(0.1F).lightLevel(Droopvine.light())));
    public static final RegistryObject<Block> DROOPVINE_PLANT = registerBlock("droopvine_plant", () -> new DroopvinePlantBlock(
            BlockBehaviour.Properties.copy(Blocks.CAVE_VINES_PLANT).strength(0.1F).lightLevel(Droopvine.light())));
    public static final RegistryObject<Block> BLOOD_SCRAPPER_PLANT = registerBlock("blood_scrapper_plant", () -> new TallGrowingPlant(
            BlockBehaviour.Properties.copy(Blocks.BAMBOO).strength(0.1F).dynamicShape().noOcclusion().noCollission().randomTicks().forceSolidOn().lightLevel((state)->20)));
    public static final RegistryObject<Block> BLOOD_SCRAPPER_PLANT_SAPLING = registerBlock("blood_scrapper_plant_sapling", () -> new BloodScrapperPlantSapling(BlockBehaviour.Properties.copy(Blocks.BAMBOO).strength(0.1F)));
    public static final RegistryObject<Block> GIANT_BLOOD_SAPLING = registerBlock("giant_blood_sapling", ()-> new SaplingBlock(new GiantBloodTreeGrower(),
            BlockBehaviour.Properties.copy(Blocks.CHERRY_SAPLING)));

    //Wood
    public static final RegistryObject<Block> BLOOD_LOG = registerBlock("blood_log", ()-> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> BLOOD_WOOD = registerBlock("blood_wood", ()-> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_BLOOD_LOG = registerBlock("stripped_blood_log", ()-> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_BLOOD_WOOD = registerBlock("stripped_blood_wood", ()-> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));

    public static final RegistryObject<Block> BLOOD_PLANKS = registerBlock("blood_planks", ()-> new Block(
            BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> BLOOD_PLANKS_STAIRS = registerBlock("blood_planks_stairs", () -> new StairBlock(() -> ModBlocks.BLOOD_PLANKS.get().defaultBlockState(),
            BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> BLOOD_PLANKS_SLAB = registerBlock("blood_planks_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> BLOOD_PLANKS_FENCE = registerBlock("blood_planks_fence", () -> new FenceBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> BLOOD_PLANKS_FENCE_GATE = registerBlock("blood_planks_fence_gate", () -> new FenceGateBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), SoundEvents.WOOD_PLACE, SoundEvents.WOOD_BREAK));


    public static final RegistryObject<Block> BLOOD_WOOD_CHEST = registerBlock("blood_wood_chest", () -> new BHChestBlock(
            BlockBehaviour.Properties.copy(Blocks.CHEST).strength(1F).lightLevel((state)-> 7).noOcclusion()));
    public static final RegistryObject<Block> BLOOD_LEAVES = registerBlock("blood_leaves", ()-> new LeavesBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).lightLevel((state)->15)));
    public static final RegistryObject<Block> SOUL_LOG = registerBlock("soul_log", ()-> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> SOUL_WOOD = registerBlock("soul_wood", ()-> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_SOUL_LOG = registerBlock("stripped_soul_log", ()-> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_SOUL_WOOD = registerBlock("stripped_soul_wood", ()-> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> SOUL_PLANKS = registerBlock("soul_planks", ()-> new Block(
            BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> SOUL_LEAVES = registerBlock("soul_leaves", ()-> new LeavesBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));


    //Fluid
    public static final RegistryObject<LiquidBlock> BLOOD_FLUID_BLOCK = BLOCKS.register("blood_fluid_block",
            () -> new LiquidBlock(ModFluids.SOURCE_BLOOD, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable().lightLevel((state)->50).liquid()));

    //Glowing
    public static final RegistryObject<Block> SOUL_LAMP = registerBlock("soul_lamp", ()-> new Soullampblock(
            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f).lightLevel(state -> state.getValue(Soullampblock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOOD_GLOW_STONE = registerBlock("blood_glow_stone",
            ()-> new EffectBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).lightLevel((state)->15)));
    public static final RegistryObject<Block> GLOWING_CRYSTAL = registerBlock("glowing_crystal", () -> new GlowingCrystalBlock(
            BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).strength(1F).lightLevel((state)-> 20).explosionResistance(20f).noOcclusion()));
    public static final RegistryObject<Block> GLOWING_CRYSTAL_GLASS_BLOCK = registerBlock("glowing_crystal_glass_block", () -> new GlassBlock(
            BlockBehaviour.Properties.copy(Blocks.GLASS).strength(1F).lightLevel((state)-> 20).noOcclusion()));
    public static final RegistryObject<Block> GLOWING_CRYSTAL_LANTERN = registerBlock("glowing_crystal_lantern", () -> new LanternBlock(
            BlockBehaviour.Properties.copy(Blocks.GLASS).strength(1F).lightLevel((state)-> 14).noOcclusion()));
    public static final RegistryObject<Block> BLOOD_GLOWING_CHAINS_BLOCK = registerBlock("blood_glowing_chains_block", () -> new ChainBlock(
            BlockBehaviour.Properties.copy(Blocks.CHAIN).strength(1F).lightLevel((state)-> 7).noOcclusion()));

    //Tranfusion Power Generating Mushrooms
    public static final RegistryObject<Block> BLOOD_ALTAR = BLOCKS.register("blood_altar", ()-> new BloodAltarBlock(
            BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_TILES).noOcclusion().lightLevel((state)->15)));
    public static final RegistryObject<Block> MAIN_BLOOD_ALTAR = BLOCKS.register("main_blood_altar", ()-> new MainBloodAltarBlock(
            BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_TILES).noOcclusion().lightLevel((state)->15)));






    //Misc
    public static final RegistryObject<Block> BLEEDING_BLOCK = registerBlock("bleeding_block", () -> new BleedingBlock(
            BlockBehaviour.Properties.copy(Blocks.STONE).strength(6f).requiresCorrectToolForDrops() ));
    public static final RegistryObject<Block> SANGUINE_CRUCIBLE = registerBlock("sanguine_crucible", ()-> new BloodWorkbenchBlock(
            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noCollission()));
    public static final RegistryObject<Block> BLOOD_PORTAL = registerBlockWithoutBlockItem("blood_portal", ModBloodPortalBlock::new);
    public static final RegistryObject<Block> SMALL_ROCKS = registerBlock("small_rocks", ()-> new SmallRocks(
                    BlockBehaviour.Properties.copy(Blocks.STONE).noCollission().noOcclusion().strength(1)));
    public static final RegistryObject<Block> ONI_STATUE = registerBlock("oni_statue", () -> new OniStatueBlock(
            BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).strength(1F).noOcclusion().randomTicks().noLootTable()));
    public static final RegistryObject<Block> Jumpy_Block = registerBlock("jumpy_block", ()-> new JumpyBlock(
            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f).requiresCorrectToolForDrops()));



    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }



}
