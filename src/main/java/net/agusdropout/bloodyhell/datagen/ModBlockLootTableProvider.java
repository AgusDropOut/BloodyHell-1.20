package net.agusdropout.bloodyhell.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;
import java.util.function.Supplier;

public abstract class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    public void dropSelf(Supplier<? extends Block> block) {
        super.dropSelf(block.get());
    }

    public void slab(Supplier<? extends SlabBlock> slab) {
        this.add(slab.get(), this::createSlabItemTable);
    }

    public void dropOther(Supplier<? extends Block> brokenBlock, ItemLike droppedBlock) {
        super.dropOther(brokenBlock.get(), droppedBlock);
    }

    public void dropAsSilk(Supplier<? extends Block> block) {
        super.dropWhenSilkTouch(block.get());
    }

    public void dropWithSilk(Supplier<? extends Block> block, Supplier<? extends ItemLike> drop) {
        add(block.get(), (result) -> createSingleItemTableWithSilkTouch(result, drop.get()));
    }

    public void ore(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
        super.add(block.get(), (result) -> createOreDrop(result, drop.get()));
    }

    public void ore(Supplier<? extends Block> block, Item drop) {
        super.add(block.get(), (result) -> createOreDrop(result, drop));
    }

    public void nuggetOre(Supplier<? extends Block> block, Item drop) {
        this.add(block.get(), (ore) -> createSilkTouchDispatchTable(ore, applyExplosionDecay(ore, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
    }


    public void createGrassDrops(Supplier<? extends Block> grass, Item drop) {
        this.add(grass.get(), (block) -> createShearsDispatchTable(block, applyExplosionDecay(block, (LootItem.lootTableItem(drop).when(LootItemRandomChanceCondition.randomChance(0.125F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)))));
    }

    public void createPlantsDrops(Supplier<? extends Block> grass, Item drop) {
        this.add(grass.get(), (block) -> createShearsDispatchTable(block, applyExplosionDecay(block, (LootItem.lootTableItem(drop).when(LootItemRandomChanceCondition.randomChance(0.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)))));
    }


    public void  createSilkTouchOrShearsDispatchTable(Block blockDrop, Item drop) {
        this.add(blockDrop, (block) -> createSilkTouchOrShearsDispatchTable(block, (LootItem.lootTableItem(drop))));
    }


}
