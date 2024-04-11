package net.agusdropout.firstmod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import net.agusdropout.firstmod.block.ModBlocks;
import net.agusdropout.firstmod.block.base.BaseWallPlantBlock;
import net.agusdropout.firstmod.worldgen.tree.ModTreeDecoratorTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class BloodTreeTrunkDecorator extends TreeDecorator {
    public static final BloodTreeTrunkDecorator INSTANCE = new BloodTreeTrunkDecorator();
    public static final Codec<BloodTreeTrunkDecorator> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.BLOOD_TREE_TRUNK_DECORATOR.get();
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource random = context.random();

        context.logs().forEach((pos) -> {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos newPos = pos.offset(direction.getStepX(), 0, direction.getStepZ());
                if (random.nextInt(75) == 0) {
                    if (context.isAir(newPos)) {
                        placeGronglet(context, newPos, direction);
                    }
                }
            }
        });
    }

    private void placeGronglet(TreeDecorator.Context context, BlockPos pos, Direction direction) {
        context.setBlock(pos, ModBlocks.BLOOD_WALL_MUSHROOM_BLOCK.get().defaultBlockState().setValue(BaseWallPlantBlock.FACING, direction));
    }
}
