package net.agusdropout.bloodyhell.datagen.loot;

import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.block.custom.Droopvine;
import net.agusdropout.bloodyhell.block.custom.EyeballCropBlock;
import net.agusdropout.bloodyhell.datagen.ModBlockLootTableProvider;
import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.minecraft.world.item.Items.SHEARS;

public class ModLootTables extends LootTableProvider {
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    public ModLootTables(PackOutput output) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(Entities::new, LootContextParamSets.ENTITY)));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }

    public static class Blocks extends ModBlockLootTableProvider {

        @Override
        protected void generate() {
            this.dropSelf(ModBlocks.SANGUINITE_BLOCK.get());
            this.dropSelf(ModBlocks.RHNULL_BLOCK);
            this.dropSelf(ModBlocks.Jumpy_Block.get());
            this.dropSelf(ModBlocks.SOUL_LAMP.get());
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



            this.dropSelf(ModBlocks.POLISHED_BLOODY_STONE_BLOCK.get());
            this.dropSelf(ModBlocks.POLISHED_BLOODY_STONE_SLAB.get());
            this.dropSelf(ModBlocks.POLISHED_BLOODY_STONE_FENCE_GATE.get());
            this.dropSelf(ModBlocks.POLISHED_BLOODY_STONE_FENCE.get());
            this.dropSelf(ModBlocks.POLISHED_BLOODY_STONE_WALL.get());
            this.dropSelf(ModBlocks.POLISHED_BLOODY_STONE_STAIRS.get());

            this.dropSelf(ModBlocks.BLOODY_STONE_TILES_BLOCK.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_TILES_SLAB.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_FENCE_TILES_GATE.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_TILES_FENCE.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_TILES_WALL.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_TILES_STAIRS.get());




            this.dropSelf(ModBlocks.BLOODY_STONE_SLAB.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_FENCE_GATE.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_FENCE.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_WALL.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_STAIRS.get());

            this.dropSelf(ModBlocks.BLOODY_STONE_BRICKS.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_BRICKS_SLAB.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_FENCE_BRICKS_GATE.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_BRICKS_FENCE.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_BRICKS_WALL.get());
            this.dropSelf(ModBlocks.BLOODY_STONE_BRICKS_STAIRS.get());

            this.dropSelf(ModBlocks.BLOOD_PLANKS.get());
            this.dropSelf(ModBlocks.BLOOD_PLANKS_STAIRS.get());
            this.dropSelf(ModBlocks.BLOOD_PLANKS_SLAB.get());
            this.dropSelf(ModBlocks.BLOOD_PLANKS_FENCE.get());
            this.dropSelf(ModBlocks.BLOOD_PLANKS_FENCE_GATE.get());


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
            this.dropOther(ModBlocks.DROOPVINE.get(),ModItems.GLOW_FRUIT.get());
            this.dropOther(ModBlocks.DROOPVINE_PLANT.get(),ModItems.GLOW_FRUIT.get());

            ore(ModBlocks.SANGUINITE_ORE, ModItems.RAW_SANGUINITE);





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

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    private static LootTable.Builder droopvine(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.GLOW_FRUIT.get())).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(Droopvine.GLOWY, true))));
    }
    public static class Entities extends EntityLootSubProvider {

        public Entities() {
            super(FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        public void generate() {
            this.add(ModEntityTypes.BLOOD_SEEKER.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.VEINREAVER_HORN.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            )
                    )
            );
            this.add(ModEntityTypes.BLOODTHIRSTYBEAST.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.AUREAL_REVENANT_DAGGER.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            )
                    )
            );
            this.add(ModEntityTypes.BLOODPIG.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.GOREHOG_RAW_STEAK.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            )
                    )
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Items.LEATHER)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            )
                    )
            );
            this.add(ModEntityTypes.CRIMSON_RAVEN.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.SCARLET_RAW_CHICKEN.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            )
                            .add(LootItem.lootTableItem(ModItems.SCARLET_FEATHER.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            )
                    )
            );
            this.add(ModEntityTypes.EYESHELL_SNAIL.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.CRIMSON_SHELL.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            )
                    )
            );
            this.add(ModEntityTypes.SCARLETSPECKLED_FISH.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.CRIMSON_SHELL.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            )
                    )
            );
            this.add(ModEntityTypes.ONI.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.RHNULL.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            )
                    )
            );
            this.add(ModEntityTypes.BLOOD_ARROW.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                    )
            );
            this.add(ModEntityTypes.CRYSTAL_PILLAR.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                    )
            );
            this.add(ModEntityTypes.VESPER.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                    )
            );


        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return ModEntityTypes.ENTITY_TYPES.getEntries().stream().map(Supplier::get);
        }
    }

}
