package net.agusdropout.bloodyhell.datagen;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class ModRecipes extends ModRecipesProvider {

    public ModRecipes(PackOutput output) {
        super(output);
    }

    @SuppressWarnings("removal")
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        makePlanks(ModBlocks.BLOOD_PLANKS, ModTags.Items.BLOOD_LOGS).save(consumer);


        makeBricks(ModBlocks.POLISHED_BLOODY_STONE_BLOCK, ModBlocks.BLOODY_STONE_BLOCK).save(consumer);
        makeBricks(ModBlocks.BLOODY_STONE_BRICKS, ModBlocks.POLISHED_BLOODY_STONE_BLOCK).save(consumer);
        makeBricks(ModBlocks.BLOODY_STONE_TILES_BLOCK, ModBlocks.BLOODY_STONE_BRICKS).save(consumer);

        //Bloody stone block
        makeStairs(ModBlocks.BLOODY_STONE_STAIRS, ModBlocks.BLOODY_STONE_BLOCK).save(consumer);
        makeSlab(ModBlocks.BLOODY_STONE_SLAB, ModBlocks.BLOODY_STONE_BLOCK).save(consumer);
        makeWall(ModBlocks.BLOODY_STONE_WALL, ModBlocks.BLOODY_STONE_BLOCK).save(consumer);
        makeFence(ModBlocks.BLOODY_STONE_FENCE, ModBlocks.BLOODY_STONE_BLOCK).save(consumer);
        makeFenceGate(ModBlocks.BLOODY_STONE_FENCE_GATE, ModBlocks.BLOODY_STONE_BLOCK).save(consumer);

        //Polished bloody stone block
        makeStairs(ModBlocks.POLISHED_BLOODY_STONE_STAIRS, ModBlocks.POLISHED_BLOODY_STONE_BLOCK).save(consumer);
        makeSlab(ModBlocks.POLISHED_BLOODY_STONE_SLAB, ModBlocks.POLISHED_BLOODY_STONE_BLOCK).save(consumer);
        makeWall(ModBlocks.POLISHED_BLOODY_STONE_WALL, ModBlocks.POLISHED_BLOODY_STONE_BLOCK).save(consumer);
        makeFence(ModBlocks.POLISHED_BLOODY_STONE_FENCE, ModBlocks.POLISHED_BLOODY_STONE_BLOCK).save(consumer);
        makeFenceGate(ModBlocks.POLISHED_BLOODY_STONE_FENCE_GATE, ModBlocks.POLISHED_BLOODY_STONE_BLOCK).save(consumer);

        //Bloody stone tiles block
        makeStairs(ModBlocks.BLOODY_STONE_TILES_STAIRS, ModBlocks.BLOODY_STONE_TILES_BLOCK).save(consumer);
        makeSlab(ModBlocks.BLOODY_STONE_TILES_SLAB, ModBlocks.BLOODY_STONE_TILES_BLOCK).save(consumer);
        makeWall(ModBlocks.BLOODY_STONE_TILES_WALL, ModBlocks.BLOODY_STONE_TILES_BLOCK).save(consumer);
        makeFence(ModBlocks.BLOODY_STONE_TILES_FENCE, ModBlocks.BLOODY_STONE_TILES_BLOCK).save(consumer);
        makeFenceGate(ModBlocks.BLOODY_STONE_FENCE_TILES_GATE, ModBlocks.BLOODY_STONE_TILES_BLOCK).save(consumer);

        //Bloody stone bricks block
        makeStairs(ModBlocks.BLOODY_STONE_BRICKS_STAIRS, ModBlocks.BLOODY_STONE_BRICKS).save(consumer);
        makeSlab(ModBlocks.BLOODY_STONE_BRICKS_SLAB, ModBlocks.BLOODY_STONE_BRICKS).save(consumer);
        makeWall(ModBlocks.BLOODY_STONE_BRICKS_WALL, ModBlocks.BLOODY_STONE_BRICKS).save(consumer);
        makeFence(ModBlocks.BLOODY_STONE_BRICKS_FENCE, ModBlocks.BLOODY_STONE_BRICKS).save(consumer);
        makeFenceGate(ModBlocks.BLOODY_STONE_FENCE_BRICKS_GATE, ModBlocks.BLOODY_STONE_BRICKS).save(consumer);

        //Blood Planks
        makeStairs(ModBlocks.BLOOD_PLANKS_STAIRS, ModBlocks.BLOOD_PLANKS).save(consumer);
        makeSlab(ModBlocks.BLOOD_PLANKS_SLAB, ModBlocks.BLOOD_PLANKS).save(consumer);
        makeFence(ModBlocks.BLOOD_PLANKS_FENCE, ModBlocks.BLOOD_PLANKS).save(consumer);
        makeFenceGate(ModBlocks.BLOOD_PLANKS_FENCE_GATE, ModBlocks.BLOOD_PLANKS).save(consumer);

        //Mineral blocks
        makeIngotToBlock(ModBlocks.SANGUINITE_BLOCK, ModItems.SANGUINITE).save(consumer);
        makeIngotToBlock(ModBlocks.RHNULL_BLOCK, ModItems.RHNULL).save(consumer);

        //------------------------------Tools crafting--------------------------------//
            //Sanguinite tools
        makeSword(ModItems.SANGUINITE_SWORD, ModItems.SANGUINITE).save(consumer);
        makePickaxe(ModItems.SANGUINITE_PICKAXE, ModItems.SANGUINITE).save(consumer);
        makeShovel(ModItems.SANGUINITE_SHOVEL, ModItems.SANGUINITE).save(consumer);
        makeAxe(ModItems.SANGUINITE_AXE, ModItems.SANGUINITE).save(consumer);
        makeHoe(ModItems.SANGUINITE_HOE, ModItems.SANGUINITE).save(consumer);

            //Rhnull tools
        makeSword(ModItems.RHNULL_SWORD, ModItems.RHNULL).save(consumer);
        makePickaxe(ModItems.RHNULL_PICKAXE, ModItems.RHNULL).save(consumer);
        makeShovel(ModItems.RHNULL_SHOVEL, ModItems.RHNULL).save(consumer);
        makeAxe(ModItems.RHNULL_AXE, ModItems.RHNULL).save(consumer);
        makeHoe(ModItems.RHNULL_HOE, ModItems.RHNULL).save(consumer);

        //------------------------------Armor crafting--------------------------------//
            //Sanguinite armor
        makeHelmet(ModItems.BLOOD_HELMET, ModItems.SANGUINITE).save(consumer);
        makeChestplate(ModItems.BLOOD_CHESTPLATE, ModItems.SANGUINITE).save(consumer);
        makeLeggings(ModItems.BLOOD_LEGGINGS, ModItems.SANGUINITE).save(consumer);
        makeBoots(ModItems.BLOOD_BOOTS, ModItems.SANGUINITE).save(consumer);

        //---------------------------------Misc Items crafting--------------------------------//
            //Bloody soul dust
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLOODY_SOUL_DUST.get(), 1)
                .pattern("GP")
                .pattern("PG")
                .define('G', ModItems.VEINREAVER_HORN.get())
                .define('P', ModItems.SANGUINITE.get())
                .unlockedBy("has_sanguinite", has(ModItems.SANGUINITE.get()))
                .unlockedBy("has_veinreaver_horn", has(ModItems.VEINREAVER_HORN.get()))
                .save(consumer, name("bloody_soul_dust_from_veinreaver_horn"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLOODY_SOUL_DUST.get(), 1)
                .pattern("GP")
                .pattern("PG")
                .define('G', ModItems.AUREAL_REVENANT_DAGGER.get())
                .define('P', ModItems.SANGUINITE.get())
                .unlockedBy("has_sanguinite", has(ModItems.SANGUINITE.get()))
                .unlockedBy("has_aureal_revenant_dagger", has(ModItems.AUREAL_REVENANT_DAGGER.get()))
                .save(consumer, name("bloody_soul_dust_from_aureal_revenant_dagger"));

        //Chalice of the dammed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHALICE_OF_THE_DAMMED.get(), 1)
                .pattern("GRG")
                .pattern("DGD")
                .pattern("GGG")
                .define('G', Items.GOLD_INGOT)
                .define('D', Items.DIAMOND)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(consumer, name("chalice_of_the_dammed"));
        //Crimson idol coin
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CRIMSON_IDOL_COIN.get(), 1)
                .pattern("HSH")
                .pattern("SGS")
                .pattern("HSH")
                .define('G', ModItems.SANGUINITE.get())
                .define('H', ModItems.VEINREAVER_HORN.get())
                .define('S', ModItems.AUREAL_REVENANT_DAGGER.get())
                .unlockedBy("has_sanguinite", has(ModItems.SANGUINITE.get()))
                .unlockedBy("has_veinreaver_horn", has(ModItems.VEINREAVER_HORN.get()))
                .unlockedBy("has_aureal_revenant_dagger", has(ModItems.AUREAL_REVENANT_DAGGER.get()))
                .save(consumer, name("crimson_idol_coin"));
        //---------------------------------Glowing blocks crafting--------------------------------//
            //Glowing crystal glass block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOWING_CRYSTAL_GLASS_BLOCK.get(), 8)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("GGG")
                .define('C', ModBlocks.GLOWING_CRYSTAL.get().asItem())
                .define('G', Items.GLASS)
                .unlockedBy("has_glowing_crystal", has(ModBlocks.GLOWING_CRYSTAL.get()))
                .unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, name("glowing_crystal_glass_block"));
            //Blood glow stone block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOOD_GLOW_STONE.get(), 8)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("GGG")
                .define('C', ModItems.BLOODY_SOUL_DUST.get())
                .define('G', Items.GLOWSTONE)
                .unlockedBy("has_bloody_soul_dust", has(ModItems.BLOODY_SOUL_DUST.get()))
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(consumer, name("blood_glow_stone_block"));
            //Blood glowing chains
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BLOOD_GLOWING_CHAINS_BLOCK.get(), 8)
                .pattern("G")
                .pattern("C")
                .pattern("G")
                .define('C', ModItems.BLOODY_SOUL_DUST.get())
                .define('G', ModBlocks.GLOWING_CRYSTAL.get())
                .unlockedBy("has_bloody_soul_dust", has(ModItems.BLOODY_SOUL_DUST.get()))
                .unlockedBy("has_glowing_crystal", has(ModBlocks.GLOWING_CRYSTAL.get()))
                .save(consumer, name("blood_glowing_chains"));
            //Glowing crystal lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.GLOWING_CRYSTAL_LANTERN.get(), 1)
                .pattern("PPP")
                .pattern("PGP")
                .pattern("PPP")
                .define('G', ModBlocks.GLOWING_CRYSTAL.get())
                .define('P', ModItems.SANGUINITE_NUGGET.get())
                .unlockedBy("has_glowing_crystal", has(ModBlocks.GLOWING_CRYSTAL.get()))
                .unlockedBy("has_sanguinite_nugget", has(ModItems.SANGUINITE_NUGGET.get()))
                .save(consumer, name("glowing_crystal_lantern"));

        //Sanguine Crucible
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANGUINE_CRUCIBLE.get(), 1)
                .pattern("GCG")
                .pattern("GSG")
                .pattern("GGG")
                .define('G', ModItems.SANGUINITE.get())
                .define('S', Items.GOLD_INGOT)
                .define('C', ModItems.SANGUINE_CRUCIBLE_CORE.get())
                .unlockedBy("has_sanguinite", has(ModItems.SANGUINITE.get()))
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .unlockedBy("has_sanguine_crucible_core", has(ModItems.SANGUINE_CRUCIBLE_CORE.get()))
                .save(consumer, name("sanguine_crucible"));

        //-------------------------------Block to ingot----------------------------------//
        makeBlockToIngot(ModItems.SANGUINITE,ModBlocks.SANGUINITE_BLOCK).save(consumer);
        makeBlockToIngot(ModItems.RHNULL,ModBlocks.RHNULL_BLOCK).save(consumer);

        //---------------------------------Smelting & Blasting--------------------------//
        //Sanguinite
        smeltingRecipe(ModItems.SANGUINITE.get(), ModItems.RAW_SANGUINITE.get(), 1F).save(consumer, name("smelt_raw_sanguinite"));
        blastingRecipe(ModItems.SANGUINITE.get(), ModItems.RAW_SANGUINITE.get(), 1F).save(consumer, name("blast_raw_sanguinite"));

        //---------------------------------Stonecutting----------------------------------//
            //Bloody stone
        bloodyStonecutting(ModBlocks.BLOODY_STONE_STAIRS.get(),2).save(consumer, name("bloody_stone_stairs_stonecutting"));
        bloodyStonecutting(ModBlocks.BLOODY_STONE_SLAB.get(),2).save(consumer, name("bloody_stone_slab_stonecutting"));
        bloodyStonecutting(ModBlocks.BLOODY_STONE_WALL.get(),1).save(consumer, name("bloody_stone_wall_stonecutting"));
            //Polished bloody stone
        polishedBloodyStoneStonecutting(ModBlocks.POLISHED_BLOODY_STONE_STAIRS.get(),2).save(consumer, name("polished_bloody_stone_stairs_stonecutting"));
        polishedBloodyStoneStonecutting(ModBlocks.POLISHED_BLOODY_STONE_SLAB.get(),2).save(consumer, name("polished_bloody_stone_slab_stonecutting"));
        polishedBloodyStoneStonecutting(ModBlocks.POLISHED_BLOODY_STONE_WALL.get(),1).save(consumer, name("polished_bloody_stone_wall_stonecutting"));
            //Bloody stone tiles
        bloodyStoneTilesStonecutting(ModBlocks.BLOODY_STONE_TILES_STAIRS.get(),2).save(consumer, name("bloody_stone_tiles_stairs_stonecutting"));
        bloodyStoneTilesStonecutting(ModBlocks.BLOODY_STONE_TILES_SLAB.get(),2).save(consumer, name("bloody_stone_tiles_slab_stonecutting"));
        bloodyStoneTilesStonecutting(ModBlocks.BLOODY_STONE_TILES_WALL.get(),1).save(consumer, name("bloody_stone_tiles_wall_stonecutting"));
            //Bloody stone bricks
        bloodyStoneBricksStonecutting(ModBlocks.BLOODY_STONE_BRICKS_STAIRS.get(),2).save(consumer, name("bloody_stone_bricks_stairs_stonecutting"));
        bloodyStoneBricksStonecutting(ModBlocks.BLOODY_STONE_BRICKS_SLAB.get(),2).save(consumer, name("bloody_stone_bricks_slab_stonecutting"));
        bloodyStoneBricksStonecutting(ModBlocks.BLOODY_STONE_BRICKS_WALL.get(),1).save(consumer, name("bloody_stone_bricks_wall_stonecutting"));



    }

    private ResourceLocation name(String name) {
        return new ResourceLocation(BloodyHell.MODID, name);
    }
}