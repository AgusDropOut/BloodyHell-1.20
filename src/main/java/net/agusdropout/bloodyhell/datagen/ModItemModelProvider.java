package net.agusdropout.bloodyhell.datagen;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BloodyHell.MODID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        //Sanguinite Items
        toolItem(ModItems.SANGUINITE_SWORD);
        toolItem(ModItems.SANGUINITE_PICKAXE);
        toolItem(ModItems.SANGUINITE_AXE);
        toolItem(ModItems.SANGUINITE_SHOVEL);
        toolItem(ModItems.SANGUINITE_HOE);
        normalItem(ModItems.BLOOD_HELMET);
        normalItem(ModItems.BLOOD_CHESTPLATE);
        normalItem(ModItems.BLOOD_LEGGINGS);
        normalItem(ModItems.BLOOD_BOOTS);
        normalItem(ModItems.SANGUINITE);
        normalItem(ModItems.SANGUINITE_NUGGET);
        normalItem(ModItems.RAW_SANGUINITE);

        //Rhnull Items
        toolItem(ModItems.RHNULL_SWORD);
        toolItem(ModItems.RHNULL_PICKAXE);
        toolItem(ModItems.RHNULL_AXE);
        toolItem(ModItems.RHNULL_SHOVEL);
        toolItem(ModItems.RHNULL_HOE);
        normalItem(ModItems.RHNULL);
        normalItem(ModItems.RHNULL_NUGGET);

        //Mobs Drops
        normalItem(ModItems.AUREAL_REVENANT_DAGGER);
        normalItem(ModItems.VEINREAVER_HORN);
        normalItem(ModItems.CRIMSON_SHELL);
        normalItem(ModItems.SCARLET_FEATHER);

        //Food Items
        normalItem(ModItems.GOREHOG_RAW_STEAK);
        normalItem(ModItems.SCARLET_RAW_CHICKEN);
        normalItem(ModItems.GLOW_FRUIT);
        normalItem(ModItems.GLOW_MUSHROOM);

        //Misc Items
        normalItem(ModItems.CHALICE_OF_THE_DAMMED);
        normalItem(ModItems.CRIMSON_IDOL_COIN);
        normalItem(ModItems.SANGUINE_CRUCIBLE_CORE);




        evenSimplerBlockItem(ModBlocks.SANGUINE_CRUCIBLE);
        evenSimplerBlockItem(ModBlocks.RHNULL_BLOCK);
        simpleBlockItemBlockTexture(ModBlocks.BLOOD_FLOWER);
        simpleBlockItemBlockTexture(ModBlocks.BLOOD_GRASS);



        evenSimplerBlockItem(ModBlocks.GLOWING_CRYSTAL_GLASS_BLOCK);
        evenSimplerBlockItem(ModBlocks.BLOOD_GLOWING_CHAINS_BLOCK);

        normalItem(ModItems.BLOOD_LILY);


        //Bloody stone
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_BLOCK);
        fenceItem(ModBlocks.BLOODY_STONE_FENCE, ModBlocks.BLOODY_STONE_BLOCK);
        wallItem(ModBlocks.BLOODY_STONE_WALL, ModBlocks.BLOODY_STONE_BLOCK);
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_STAIRS);
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_SLAB);
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_FENCE_GATE);

        //Bloody stone tiles
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_TILES_BLOCK);
        fenceItem(ModBlocks.BLOODY_STONE_TILES_FENCE, ModBlocks.BLOODY_STONE_TILES_BLOCK);
        wallItem(ModBlocks.BLOODY_STONE_TILES_WALL, ModBlocks.BLOODY_STONE_TILES_BLOCK);
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_TILES_STAIRS);
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_TILES_SLAB);
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_FENCE_TILES_GATE);

        //Polished bloody stone
        evenSimplerBlockItem(ModBlocks.POLISHED_BLOODY_STONE_BLOCK);
        fenceItem(ModBlocks.POLISHED_BLOODY_STONE_FENCE, ModBlocks.POLISHED_BLOODY_STONE_BLOCK);
        wallItem(ModBlocks.POLISHED_BLOODY_STONE_WALL, ModBlocks.POLISHED_BLOODY_STONE_BLOCK);
        evenSimplerBlockItem(ModBlocks.POLISHED_BLOODY_STONE_STAIRS);
        evenSimplerBlockItem(ModBlocks.POLISHED_BLOODY_STONE_SLAB);
        evenSimplerBlockItem(ModBlocks.POLISHED_BLOODY_STONE_FENCE_GATE);

        //Bloody Stone Bricks
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_BRICKS);
        fenceItem(ModBlocks.BLOODY_STONE_BRICKS_FENCE, ModBlocks.BLOODY_STONE_BRICKS);
        wallItem(ModBlocks.BLOODY_STONE_BRICKS_WALL, ModBlocks.BLOODY_STONE_BRICKS);
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_BRICKS_STAIRS);
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_BRICKS_SLAB);
        evenSimplerBlockItem(ModBlocks.BLOODY_STONE_FENCE_BRICKS_GATE);

        //Blood Planks
        fenceItem(ModBlocks.BLOOD_PLANKS_FENCE, ModBlocks.BLOOD_PLANKS);
        evenSimplerBlockItem(ModBlocks.BLOOD_PLANKS_STAIRS);
        evenSimplerBlockItem(ModBlocks.BLOOD_PLANKS_SLAB);
        evenSimplerBlockItem(ModBlocks.BLOOD_PLANKS_FENCE_GATE);


        normalItem(ModItems.DIRTY_BLOOD_FLOWER);
        normalItem(ModItems.BLOOD_ARROW);

        simpleBlockItemBlockTexture(ModBlocks.HANGING_BLOOD_TREE_LEAVES);
        simpleBlockItemBlockTexture(ModBlocks.GLOWING_CRYSTAL);
        simpleBlockItemBlockTexture(ModBlocks.DROOPVINE);
        simpleBlockItemBlockTexture(ModBlocks.DROOPVINE_PLANT);


    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(BloodyHell.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(BloodyHell.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(BloodyHell.MODID,"block/" + item.getId().getPath()));
    }
    private String blockName(Supplier<? extends Block> block) {
        return BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
    }

    private ResourceLocation texture(String name) {
        return modLoc("block/" + name);
    }

    public void itemFence(Supplier<? extends Block> block, String name) {
        withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
                .texture("texture", ("block/" + name));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block) {
        return block(block, blockName(block));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), modLoc("block/" + name));
    }

    public void blockFlat(Supplier<? extends Block> block) {
        withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + blockName(block)));
    }

    public void blockFlatWithBlockTexture(Supplier<? extends Block> block, String name) {
        withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + name))
                .renderType("translucent");
    }

    public void blockFlatWithItemTexture(Supplier<? extends Block> block, String name) {
        withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    public void normalItem(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath()));
    }

    public void normalItemSpecifiedTexture(Supplier<? extends Item> item, String name) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    public void torchItem(Supplier<? extends Block> item) {
        withExistingParent(BuiltInRegistries.BLOCK.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(item.get()).getPath()));
    }

    public void toolItem(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath()));
    }

    public void rodItem(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/handheld_rod"))
                .texture("layer0", modLoc("item/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath()));
    }

    public void egg(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/template_spawn_egg"));
    }

    public void sign(Supplier<? extends SignBlock> sign) {
        withExistingParent(blockName(sign), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + blockName(sign)));
    }

    public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        return wallInventory(BuiltInRegistries.BLOCK.getKey(wall.get()).getPath(), texture(blockName(fullBlock)));
    }

    public ItemModelBuilder button(Supplier<? extends ButtonBlock> button, Supplier<? extends Block> fullBlock) {
        return buttonInventory(BuiltInRegistries.BLOCK.getKey(button.get()).getPath(), texture(blockName(fullBlock)));
    }

    public void trapdoor(Supplier<? extends TrapDoorBlock> trapdoor) {
        withExistingParent(BuiltInRegistries.BLOCK.getKey(trapdoor.get()).getPath(), new ResourceLocation(BloodyHell.MODID, "block/" + blockName(trapdoor) + "_bottom"));
    }
    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(BloodyHell.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(BloodyHell.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }


}
