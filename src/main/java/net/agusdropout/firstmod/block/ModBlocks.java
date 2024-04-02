package net.agusdropout.firstmod.block;

import net.agusdropout.firstmod.FirstMod;
import net.agusdropout.firstmod.block.custom.*;
import net.agusdropout.firstmod.fluid.ModFluids;
import net.agusdropout.firstmod.item.ModItems;
import net.agusdropout.firstmod.worldgen.tree.BloodTreeGrower;
import net.agusdropout.firstmod.worldgen.tree.SoulTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirstMod.MODID);
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static final RegistryObject<Block> soul_BLOCK = registerBlock("soul_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOOD_GRASS_BLOCK = registerBlock("blood_grass_block", ()-> new BloodGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).strength(2f).randomTicks().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> BLOODDIRT_FARMLAND = registerBlock("blooddirt_farmland", () -> new BloodDirtFarmlandBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND)));
    public static final RegistryObject<Block> BLOOD_DIRT_BLOCK = registerBlock("blood_dirt_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(2f)));
    public static final RegistryObject<Block> soul_ORE = registerBlock("soul_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> DEEPSLATE_soul_ORE = registerBlock("deepslate_soul_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).strength(7f).requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> Jumpy_Block = registerBlock("jumpy_block", ()-> new JumpyBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLEEDING_BLOCK = registerBlock("bleeding_block", () -> new BleedingBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(6f).requiresCorrectToolForDrops() ));
    public static final RegistryObject<Block> EYEBALLSHELL_SNAIL_GOO = registerBlock("eyeballshell_snail_goo", () -> new GooLayerBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).strength(6f).randomTicks().replaceable().noCollission().noOcclusion()));
    public static final RegistryObject<Block> EYEBALLSHELL_SNAIL_GOO_BLOCK = registerBlock("eyeballshell_snail_goo_block", () -> new GooBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).strength(6f) ));
    public static final RegistryObject<Block> soul_Lamp = registerBlock("soul_lamp", ()-> new Soullampblock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f).lightLevel(state -> state.getValue(Soullampblock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> Eyeball_crop = BLOCKS.register("eyeball_crop", ()-> new EyeballCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<LiquidBlock> SOAP_WATER_BLOCK = BLOCKS.register("soap_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<Block> BLOOD_WORKBENCH = registerBlock("blood_workbench",
            ()-> new BloodWorkbenchBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> BLOOD_LOG = registerBlock("blood_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> BLOOD_WOOD = registerBlock("blood_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_BLOOD_LOG = registerBlock("stripped_blood_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_BLOOD_WOOD = registerBlock("stripped_blood_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> BLOOD_PLANKS = registerBlock("blood_planks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
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
    public static final RegistryObject<Block> BLOOD_LEAVES = registerBlock("blood_leaves",
            ()-> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> BLOOD_SAPLING = registerBlock("blood_sapling",
            ()-> new SaplingBlock(new BloodTreeGrower(),BlockBehaviour.Properties.copy(Blocks.CHERRY_SAPLING)));
    public static final RegistryObject<Block> SOUL_LOG = registerBlock("soul_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> SOUL_WOOD = registerBlock("soul_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_SOUL_LOG = registerBlock("stripped_soul_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_SOUL_WOOD = registerBlock("stripped_soul_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> SOUL_PLANKS = registerBlock("soul_planks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
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
    public static final RegistryObject<Block> SOUL_LEAVES = registerBlock("soul_leaves",
            ()-> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> HANGING_SOUL_TREE_LEAVES = registerBlock("hanging_soultree_leaves",
            ()-> new HangingSoulTreeLeavesBlock(BlockBehaviour.Properties.copy(Blocks.VINE)));
    public static final RegistryObject<Block> SOUL_SAPLING = registerBlock("soul_sapling",
            ()-> new SaplingBlock(new SoulTreeGrower(),BlockBehaviour.Properties.copy(Blocks.CHERRY_SAPLING)));

    public static final RegistryObject<Block> BLOOD_PORTAL = registerBlockWithoutBlockItem("blood_portal",
            ModBloodPortalBlock::new);
    public static final RegistryObject<Block> BLOOD_FLOWER = registerBlock("blood_flower",
            ()-> new FlowerBlock(()-> MobEffects.POISON,5,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noCollission().noOcclusion().lightLevel((state)->6)));
    public static final RegistryObject<Block> BLOOD_GRASS = registerBlock("blood_grass",
            ()-> new FlowerBlock(()-> MobEffects.POISON,5,
                    BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().noOcclusion()));
    public static final RegistryObject<Block> POTTED_BLOOD_FLOWER = BLOCKS.register("potted_blood_flower",
            ()-> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT) ,ModBlocks.BLOOD_FLOWER,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final RegistryObject<Block> BLOOD_SMALL_ROCKS = registerBlock("blood_small_rocks",
            ()-> new FlowerBlock(()-> MobEffects.POISON,5,
                    BlockBehaviour.Properties.copy(Blocks.PINK_PETALS).noCollission().noOcclusion()));
    public static final RegistryObject<Block> BLOOD_BUSH = registerBlock("blood_bush",
            ()-> new DoublePlantBlock(
                    BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH)));
    public static final RegistryObject<Block> BLOOD_PETALS = registerBlock("blood_petals",
            ()-> new FlowerBlock(()-> MobEffects.HARM,5,BlockBehaviour.Properties.copy(Blocks.ALLIUM).noCollission().noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }



}
