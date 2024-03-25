package net.agusdropout.firstmod.worldgen.tree.custom;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.agusdropout.firstmod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BloodTrunkPlacer extends TrunkPlacer {
    public static final Codec<BloodTrunkPlacer> CODEC = RecordCodecBuilder.create(bloodTrunkPlacerInstance ->
            trunkPlacerParts(bloodTrunkPlacerInstance).apply(bloodTrunkPlacerInstance, BloodTrunkPlacer::new));

    public BloodTrunkPlacer(int p_70268_, int p_70269_, int p_70270_) {
        super(p_70268_, p_70269_, p_70270_);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.BLOOD_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos,
            BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        OptionalInt optionalInt = OptionalInt.empty();

        int height = 8 + pRandom.nextInt(heightRandA, heightRandA + 3);


        for (int i = 0; i < height; i++) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
            if (pRandom.nextBoolean() && i == 5) {
                if (pRandom.nextFloat() > 0.25f) {
                    for (int j = 0; j < 4; j++) {
                        pBlockSetter.accept(pPos.above(i).relative(Direction.NORTH, j),
                                ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                    }
                    // Coloca un tronco adicional en la parte superior de la rama
                    pBlockSetter.accept(pPos.above(6).relative(Direction.NORTH, 3),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));
                    list.add(new FoliagePlacer.FoliageAttachment(pPos.above(6).relative(Direction.NORTH,3), 0, false));

                } else if (pRandom.nextFloat() > 0.25f) {
                    for (int j = 0; j < 4; j++) {
                        pBlockSetter.accept(pPos.above(i).relative(Direction.SOUTH, j),
                                ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                    }
                    // Coloca un tronco adicional en la parte superior de la rama
                    pBlockSetter.accept(pPos.above(6).relative(Direction.SOUTH, 3),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));
                    list.add(new FoliagePlacer.FoliageAttachment(pPos.above(6).relative(Direction.SOUTH,3), 0, false));
                } else if (pRandom.nextFloat() > 0.25f) {
                    for (int j = 0; j < 4; j++) {
                        pBlockSetter.accept(pPos.above(i).relative(Direction.WEST, j),
                                ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                    }
                    // Coloca un tronco adicional en la parte superior de la rama
                    pBlockSetter.accept(pPos.above(6).relative(Direction.WEST, 3),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));
                    list.add(new FoliagePlacer.FoliageAttachment(pPos.above(6).relative(Direction.WEST,3), 0, false));
                } else {
                    for (int j = 0; j < 4; j++) {
                        pBlockSetter.accept(pPos.above(i).relative(Direction.EAST, j),
                                ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                    }
                    // Coloca un tronco adicional en la parte superior de la rama
                    pBlockSetter.accept(pPos.above(6).relative(Direction.EAST, 3),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));
                    list.add(new FoliagePlacer.FoliageAttachment(pPos.above(6).relative(Direction.EAST,3), 0, false));
                }
            }


        }
        list.add(new FoliagePlacer.FoliageAttachment(pPos.above(height-1), 2, false));
        return list;
    }
    }

