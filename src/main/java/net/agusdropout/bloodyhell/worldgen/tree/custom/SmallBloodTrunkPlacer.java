package net.agusdropout.bloodyhell.worldgen.tree.custom;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.agusdropout.bloodyhell.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class SmallBloodTrunkPlacer extends TrunkPlacer {
    public static final Codec<SmallBloodTrunkPlacer> CODEC = RecordCodecBuilder.create(SmallBloodTrunkPlacerInstance ->
            trunkPlacerParts(SmallBloodTrunkPlacerInstance).apply(SmallBloodTrunkPlacerInstance, SmallBloodTrunkPlacer::new));

    public SmallBloodTrunkPlacer(int p_70268_, int p_70269_, int p_70270_) {
        super(p_70268_, p_70269_, p_70270_);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.SMALL_BLOOD_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos,
            BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        OptionalInt optionalInt = OptionalInt.empty();





            placeLog(pLevel, pBlockSetter, pRandom, pPos, pConfig);

            list.add(new FoliagePlacer.FoliageAttachment(pPos, 1, false));
            return list;


    }
}
