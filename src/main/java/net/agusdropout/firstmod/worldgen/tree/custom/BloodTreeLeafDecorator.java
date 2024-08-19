package net.agusdropout.firstmod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import net.agusdropout.firstmod.block.ModBlocks;
import net.agusdropout.firstmod.worldgen.tree.ModTreeDecoratorTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class BloodTreeLeafDecorator extends TreeDecorator {
    public static final BloodTreeLeafDecorator INSTANCE = new BloodTreeLeafDecorator();
    public static final Codec<BloodTreeLeafDecorator> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.BLOOD_TREE_LEAF_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();
        int Length = 6 /*2 + random.nextInt(1,7)*/;

        context.leaves().forEach((pos -> {
            if (random.nextInt(3) == 0) {
                int vineLength = 2 + random.nextInt(6); // Esto generará una longitud aleatoria entre 2 y 7

                for(int i = 0; i < vineLength; i++) {
                    BlockPos currentPos = pos.below(i+1);
                    if (context.isAir(currentPos)) {
                        context.setBlock(currentPos, ModBlocks.HANGING_BLOOD_TREE_LEAVES.get().defaultBlockState());
                    } else {
                        break;
                    }
                }

                BlockPos upperPos = pos.below(vineLength);
                if (context.isAir(upperPos)) {
                    context.setBlock(upperPos, ModBlocks.HANGING_BLOOD_TREE_LEAVES.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
                }
            }
        }));
    }

}
