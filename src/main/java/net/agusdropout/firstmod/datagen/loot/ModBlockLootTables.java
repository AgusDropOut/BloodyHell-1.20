package net.agusdropout.firstmod.datagen.loot;

import net.agusdropout.firstmod.block.ModBlocks;
import net.agusdropout.firstmod.block.custom.EyeballCropBlock;
import net.agusdropout.firstmod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.soul_BLOCK.get());
        this.dropSelf(ModBlocks.Jumpy_Block.get());
        this.dropSelf(ModBlocks.soul_Lamp.get());
        this.dropSelf(ModBlocks.BLOOD_PLANKS.get());
        this.dropSelf(ModBlocks.BLOOD_WORKBENCH.get());
        this.dropSelf(ModBlocks.BLOOD_SAPLING.get());
        this.dropSelf(ModBlocks.STRIPPED_BLOOD_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_BLOOD_LOG.get());
        this.dropSelf(ModBlocks.BLOOD_WOOD.get());
        this.dropSelf(ModBlocks.BLOOD_LOG.get());
        this.dropSelf(ModBlocks.BLOOD_SMALL_ROCKS.get());
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






        this.add(ModBlocks.soul_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.soul_ORE.get(), ModItems.RAW_soul.get()));
        this.add(ModBlocks.DEEPSLATE_soul_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_soul_ORE.get(), ModItems.RAW_soul.get()));




        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.Eyeball_crop.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(EyeballCropBlock.AGE, 6))
                .or(LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(ModBlocks.Eyeball_crop.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(EyeballCropBlock.AGE, 6)));


        this.add(ModBlocks.Eyeball_crop.get(), createCropDrops(ModBlocks.Eyeball_crop.get(), ModItems.Eyeball.get(),
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
}
