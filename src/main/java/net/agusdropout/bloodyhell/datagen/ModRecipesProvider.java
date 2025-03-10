package net.agusdropout.bloodyhell.datagen;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ModRecipesProvider extends RecipeProvider {
    public ModRecipesProvider(PackOutput output) {
        super(output);
    }


    public ShapelessRecipeBuilder makePlanks(Supplier<? extends Block> plankOut, TagKey<Item> logIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, plankOut.get(), 4)
                .requires(logIn)
                .group("planks")
                .unlockedBy("has_log", has(logIn));
    }

    public ShapedRecipeBuilder makeDoor(Supplier<? extends Block> doorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, doorOut.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .pattern("PP")
                .define('P', plankIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(plankIn.get()).getPath(), has(plankIn.get()));
    }

    public ShapedRecipeBuilder makeTrapdoor(Supplier<? extends Block> trapdoorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, trapdoorOut.get(), 2)
                .pattern("PPP")
                .pattern("PPP")
                .define('P', plankIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(plankIn.get()).getPath(), has(plankIn.get()));
    }

    public ShapelessRecipeBuilder makeButton(Supplier<? extends Block> buttonOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, buttonOut.get())
                .requires(blockIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makePressurePlate(Supplier<? extends Block> pressurePlateOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, pressurePlateOut.get())
                .pattern("BB")
                .define('B', blockIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeStairs(Supplier<? extends Block> stairsOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairsOut.get(), 4)
                .pattern("M  ")
                .pattern("MM ")
                .pattern("MMM")
                .define('M', blockIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeSlab(Supplier<? extends Block> slabOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slabOut.get(), 6)
                .pattern("MMM")
                .define('M', blockIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeWall(Supplier<? extends Block> wallOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wallOut.get(), 6)
                .pattern("MMM")
                .pattern("MMM")
                .define('M', blockIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeFence(Supplier<? extends Block> fenceOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fenceOut.get(), 6)
                .pattern("M/M")
                .pattern("M/M")
                .define('M', blockIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeFenceGate(Supplier<? extends Block> fenceGateOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, fenceGateOut.get())
                .pattern("/M/")
                .pattern("/M/")
                .define('M', blockIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeBricks(Supplier<? extends Block> bricksOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bricksOut.get(), 4)
                .pattern("MM")
                .pattern("MM")
                .define('M', blockIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeChiseledBricks(Supplier<? extends Block> bricksOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bricksOut.get())
                .pattern("M")
                .pattern("M")
                .define('M', blockIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeWood(Supplier<? extends Block> woodOut, Supplier<? extends Block> logIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, woodOut.get(), 3)
                .pattern("MM")
                .pattern("MM")
                .define('M', logIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(logIn.get()).getPath(), has(logIn.get()));
    }

    public ShapedRecipeBuilder makeIngotToBlock(Supplier<? extends Block> blockOut, Supplier<? extends Item> ingotIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ingotIn.get())
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingotIn.get()).getPath(), has(ingotIn.get()));
    }

    public ShapelessRecipeBuilder makeBlockToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingotOut.get(), 9)
                .requires(blockIn.get())
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeNuggetToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Item> nuggetIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingotOut.get(), 1)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .define('N', nuggetIn.get())
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(nuggetIn.get()).getPath(), has(nuggetIn.get()));
    }

    public ShapelessRecipeBuilder makeIngotToNugget(Supplier<? extends Item> nuggetOut, Supplier<? extends Item> ingotIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nuggetOut.get(), 9)
                .requires(ingotIn.get())
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingotIn.get()).getPath(), has(ingotIn.get()));
    }

    public ShapedRecipeBuilder makeSword(Supplier<? extends Item> swordOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, swordOut.get())
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get()).getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makePickaxe(Supplier<? extends Item> pickaxeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxeOut.get())
                .pattern("###")
                .pattern(" / ")
                .pattern(" / ")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get()).getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeAxe(Supplier<? extends Item> axeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axeOut.get())
                .pattern("##")
                .pattern("#/")
                .pattern(" /")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get()).getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeShovel(Supplier<? extends Item> shovelOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovelOut.get())
                .pattern("#")
                .pattern("/")
                .pattern("/")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get()).getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeHoe(Supplier<? extends Item> hoeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoeOut.get())
                .pattern("##")
                .pattern(" /")
                .pattern(" /")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get()).getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeHelmet(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetOut.get())
                .pattern("MMM")
                .pattern("M M")
                .define('M', materialIn.get())
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get()).getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeChestplate(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetOut.get())
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', materialIn.get())
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get()).getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeLeggings(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetOut.get())
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .define('M', materialIn.get())
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get()).getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeBoots(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetOut.get())
                .pattern("M M")
                .pattern("M M")
                .define('M', materialIn.get())
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get()).getPath(), has(materialIn.get()));
    }

    public ShapelessRecipeBuilder makeStew(Supplier<? extends Item> stewOut, Supplier<? extends Block> mushroomIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, stewOut.get())
                .requires(Items.BOWL)
                .requires(mushroomIn.get(), 3)
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(mushroomIn.get()).getPath(), has(mushroomIn.get()));
    }

    public ShapedRecipeBuilder makeBoat(Supplier<? extends Item> boatOut, Supplier<? extends Block> planksIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boatOut.get())
                .pattern("P P")
                .pattern("PPP")
                .define('P', planksIn.get())
                .group("boat")
                .unlockedBy("in_water", insideOf(Blocks.WATER));
    }

    public ShapelessRecipeBuilder makeChestBoat(Supplier<? extends Item> chestBoatOut, Supplier<? extends Block> boatIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chestBoatOut.get())
                .requires(boatIn.get())
                .requires(Tags.Items.CHESTS_WOODEN)
                .group("chest_boat")
                .unlockedBy("has_boat", has(ItemTags.BOATS));
    }

    public ShapedRecipeBuilder makeSign(Supplier<? extends SignBlock> signOut, Supplier<? extends Block> planksIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, signOut.get(), 3)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .define('P', planksIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(planksIn.get()).getPath(), has(planksIn.get()));
    }

    protected ShapedRecipeBuilder makeHangingSign(Supplier<? extends CeilingHangingSignBlock> result, Supplier<? extends Block> log) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
                .pattern("| |")
                .pattern("###")
                .pattern("###")
                .define('#', log.get())
                .define('|', Items.CHAIN)
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(log.get()).getPath(), has(log.get()));
    }

    public void ore(ItemLike result, List<ItemLike> ingredients, float xp, String group, Consumer<FinishedRecipe> consumer) {
        oreSmeltingRecipe(result, ingredients, xp, group, consumer);
        oreBlastingRecipe(result, ingredients, xp, group, consumer);
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    private void oreSmeltingRecipe(ItemLike result, List<ItemLike> ingredients, float xp, String group, Consumer<FinishedRecipe> consumer) {
        for (ItemLike ingredient : ingredients) {
            smeltingRecipe(result, ingredient, xp, 1).group(group).save(consumer, new ResourceLocation(BloodyHell.MODID, "smelt_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()).getPath()));
        }
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()), has(ingredient));
    }

    public SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp) {
        return smeltingRecipeTag(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_" + ingredient, has(ingredient));
    }

    public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return blastingRecipe(result, ingredient, exp, 1);
    }

    private void oreBlastingRecipe(ItemLike result, List<ItemLike> ingredients, float xp, String group, Consumer<FinishedRecipe> consumer) {
        for (ItemLike ingredient : ingredients) {
            blastingRecipe(result, ingredient, xp, 1).group(group).save(consumer, new ResourceLocation(BloodyHell.MODID, "blast_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()).getPath()));
        }
    }

    public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 100)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()), has(ingredient));
    }

    public SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp) {
        return blastingRecipeTag(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 100)
                .unlockedBy("has_" + ingredient, has(ingredient));
    }
    public SimpleCookingRecipeBuilder campfireRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return campfireRecipe(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder campfireRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.FOOD, result, exp, 600)
                .unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(ingredient.asItem()), has(ingredient));

    }

    public SimpleCookingRecipeBuilder smokingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return smokingRecipe(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smokingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smoking(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 100)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()), has(ingredient));

    }

    public SmithingTransformRecipeBuilder smithingRecipe(Supplier<Item> input, Supplier<Item> upgradeItem, Supplier<Item> templateItem, Supplier<Item> result) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem.get()), RecipeCategory.MISC, result.get())
                .unlocks("has_" + ForgeRegistries.ITEMS.getKey(upgradeItem.get()), has(upgradeItem.get()));
    }

    public SingleItemRecipeBuilder stonecutting(Supplier<Block> input, ItemLike result) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result)
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(input.get()), has(input.get()));
    }

    public SingleItemRecipeBuilder stonecutting(Supplier<Block> input, ItemLike result, int resultAmount) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result, resultAmount)
                .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(input.get()), has(input.get()));
    }

    public SingleItemRecipeBuilder bloodyStonecutting(ItemLike result) {
        return stonecutting(ModBlocks.BLOODY_STONE_BLOCK, result);
    }

    public SingleItemRecipeBuilder bloodyStonecutting(ItemLike result, int resultAmount) {
        return stonecutting(ModBlocks.BLOODY_STONE_BLOCK, result, resultAmount);
    }

    public SingleItemRecipeBuilder bloodyStoneBricksStonecutting(ItemLike result) {
        return stonecutting(ModBlocks.BLOODY_STONE_BRICKS, result);
    }

    public SingleItemRecipeBuilder bloodyStoneBricksStonecutting(ItemLike result, int resultAmount) {
        return stonecutting(ModBlocks.BLOODY_STONE_BRICKS, result, resultAmount);
    }

    public SingleItemRecipeBuilder bloodyStoneTilesStonecutting(ItemLike result) {
        return stonecutting(ModBlocks.BLOODY_STONE_TILES_BLOCK, result);
    }

    public SingleItemRecipeBuilder bloodyStoneTilesStonecutting(ItemLike result, int resultAmount) {
        return stonecutting(ModBlocks.BLOODY_STONE_TILES_BLOCK, result, resultAmount);
    }

    public SingleItemRecipeBuilder polishedBloodyStoneStonecutting(ItemLike result) {
        return stonecutting(ModBlocks.POLISHED_BLOODY_STONE_BLOCK, result);
    }

    public SingleItemRecipeBuilder polishedBloodyStoneStonecutting(ItemLike result, int resultAmount) {
        return stonecutting(ModBlocks.POLISHED_BLOODY_STONE_BLOCK, result, resultAmount);
    }

}
