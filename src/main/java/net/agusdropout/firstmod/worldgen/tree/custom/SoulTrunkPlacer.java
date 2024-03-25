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

public class SoulTrunkPlacer extends TrunkPlacer {
    public static final Codec<SoulTrunkPlacer> CODEC = RecordCodecBuilder.create(soulTrunkPlacerInstance ->
            trunkPlacerParts(soulTrunkPlacerInstance).apply(soulTrunkPlacerInstance, SoulTrunkPlacer::new));

    public SoulTrunkPlacer(int p_70268_, int p_70269_, int p_70270_) {
        super(p_70268_, p_70269_, p_70270_);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.SOUL_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos,
            BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        OptionalInt optionalInt = OptionalInt.empty();

        int height = 5 + pRandom.nextInt(heightRandA, heightRandA + 3);
        int heighteast = pRandom.nextInt(heightRandA, heightRandA + 1);
        int heightwest = pRandom.nextInt(heightRandA, heightRandA + 1);
        int heightsouth = pRandom.nextInt(heightRandA, heightRandA + 1);
        int heightnorth = pRandom.nextInt(heightRandA, heightRandA + 1);


        for (int i = 0; i < height; i++) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
        }
        if (pRandom.nextFloat() > 0.25f) {
            for (int i = 0; i < heighteast; i++) {
                placeLog(pLevel, pBlockSetter, pRandom, pPos.east(2).above(i), pConfig);
                if (i == heighteast - 1) {
                    pBlockSetter.accept(pPos.above(heighteast - 1).relative(Direction.EAST, 1),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                }
            }
        }
        if (pRandom.nextFloat() > 0.25f) {
            for (int i = 0; i < heightwest; i++) {
                placeLog(pLevel, pBlockSetter, pRandom, pPos.west(2).above(i), pConfig);
                if (i == heightwest - 1) {
                    pBlockSetter.accept(pPos.above(heightwest - 1).relative(Direction.WEST, 1),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                }
            }
        }
        if (pRandom.nextFloat() > 0.25f) {
            for (int i = 0; i < heightnorth; i++) {
                placeLog(pLevel, pBlockSetter, pRandom, pPos.north(2).above(i), pConfig);
                if (i == heightnorth - 1) {
                    pBlockSetter.accept(pPos.above(heightnorth - 1).relative(Direction.NORTH, 1),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                }
            }
        }
        if (pRandom.nextFloat() > 0.25f) {
            for (int i = 0; i < heightsouth; i++) {
                placeLog(pLevel, pBlockSetter, pRandom, pPos.south(2).above(i), pConfig);
                if (i == heightsouth - 1) {
                    pBlockSetter.accept(pPos.above(heightsouth - 1).relative(Direction.SOUTH, 1),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                }
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(pPos.above(height-1), 2, false));

        return list;
    }
    }

