package net.agusdropout.bloodyhell.datagen.loot;

import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.block.custom.EyeballCropBlock;
import net.agusdropout.bloodyhell.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.function.Supplier;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SANGUINITE_BLOCK.get());
        this.dropSelf(ModBlocks.Jumpy_Block.get());
        this.dropSelf(ModBlocks.SOUL_LAMP.get());
        this.dropSelf(ModBlocks.BLOOD_PLANKS.get());
        this.dropSelf(ModBlocks.SANGUINE_CRUCIBLE.get());
        this.dropSelf(ModBlocks.BLOOD_SAPLING.get());
        this.dropSelf(ModBlocks.STRIPPED_BLOOD_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_BLOOD_LOG.get());
        this.dropSelf(ModBlocks.BLOOD_WOOD.get());
        this.dropSelf(ModBlocks.BLOOD_LOG.get());
        this.dropSelf(ModBlocks.SMALL_ROCKS.get());
        this.dropSelf(ModBlocks.BLOOD_PETALS.get());
        this.dropSelf(ModBlocks.BLEEDING_BLOCK.get());
        this.dropSelf(ModBlocks.SOUL_PLANKS.get());
        this.dropSelf(ModBlocks.SOUL_SAPLING.get());
        this.dropSelf(ModBlocks.STRIPPED_SOUL_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_SOUL_LOG.get());
        this.dropSelf(ModBlocks.SOUL_WOOD.get());
        this.dropSelf(ModBlocks.SOUL_LOG.get());
        this.dropSelf(ModBlocks.HANGING_SOUL_TREE_LEAVES.get());
        this.dropSelf(ModBlocks.EYEBALLSHELL_SNAIL_GOO.get());
        this.dropSelf(ModBlocks.EYEBALLSHELL_SNAIL_GOO_BLOCK.get());
        this.dropSelf(ModBlocks.BLOOD_GRASS_BLOCK.get());
        this.dropSelf(ModBlocks.BLOOD_DIRT_BLOCK.get());
        this.dropSelf(ModBlocks.BLOODDIRT_FARMLAND.get());
        this.dropSelf(ModBlocks.BLOODY_STONE_BLOCK.get());
        this.dropSelf(ModBlocks.BLOODY_STONE_TILES_BLOCK.get());
        this.dropSelf(ModBlocks.POLISHED_BLOODY_STONE_BLOCK.get());
        this.dropSelf(ModBlocks.BLOODY_STONE_SLAB.get());
        this.dropSelf(ModBlocks.BLOODY_STONE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.BLOODY_STONE_FENCE.get());
        this.dropSelf(ModBlocks.BLOODY_STONE_WALL.get());
        this.dropSelf(ModBlocks.BLOODY_STONE_STAIRS.get());
        this.dropSelf(ModBlocks.BLOOD_LILY_BLOCK.get());
        this.dropSelf(ModBlocks.SMALL_BLOOD_SAPLING.get());
        this.dropSelf(ModBlocks.BLOOD_WALL_MUSHROOM_BLOCK.get());
        this.dropSelf(ModBlocks.GIANT_BLOOD_SAPLING.get());
        this.dropSelf(ModBlocks.HANGING_BLOOD_TREE_LEAVES.get());
        this.dropSelf(ModBlocks.BLOOD_GLOW_STONE.get());
        this.dropSelf(ModBlocks.DROOPVINE.get());
        this.dropSelf(ModBlocks.DROOPVINE_PLANT.get());

        this.dropSelf(ModBlocks.BLOOD_SCRAPPER_PLANT.get());
        this.dropSelf(ModBlocks.BLOOD_SCRAPPER_PLANT_SAPLING.get());
        this.dropSelf(ModBlocks.GLOWING_CRYSTAL.get());
        this.dropSelf(ModBlocks.GLOWING_CRYSTAL_GLASS_BLOCK.get());
        this.dropSelf(ModBlocks.GLOWING_CRYSTAL_LANTERN.get());
        this.dropSelf(ModBlocks.BLOOD_GLOWING_CHAINS_BLOCK.get());
        this.dropSelf(ModBlocks.BLOOD_WOOD_CHEST.get());

        this.dropOther(ModBlocks.LIGHT_MUSHROOM_BLOCK.get(),ModItems.GLOW_MUSHROOM.get());







        this.add(ModBlocks.SANGUINITE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.SANGUINITE_ORE.get(), ModItems.RAW_SANGUINITE.get()));





        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.EYEBALL_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(EyeballCropBlock.AGE, 6))
                .or(LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(ModBlocks.EYEBALL_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(EyeballCropBlock.AGE, 6)));


        this.add(ModBlocks.EYEBALL_CROP.get(), createCropDrops(ModBlocks.EYEBALL_CROP.get(), ModItems.Eyeball.get(),
                ModItems.Eyeball_seed.get(), lootitemcondition$builder2));

        this.add(ModBlocks.BLOOD_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BLOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.SOUL_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BLOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        this.dropSelf(ModBlocks.BLOOD_BUSH.get());
        this.dropSelf(ModBlocks.BLOOD_FLOWER.get());
        this.dropSelf(ModBlocks.BLOOD_GRASS.get());
        this.add(ModBlocks.POTTED_BLOOD_FLOWER.get(), createPotFlowerItemTable(ModBlocks.BLOOD_FLOWER.get()));
    }
    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }



    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    public void dropOther(Supplier<? extends Block> brokenBlock, ItemLike droppedBlock) {
        this.dropOther(brokenBlock.get(), droppedBlock);
    }
}
