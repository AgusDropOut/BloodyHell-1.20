package net.agusdropout.firstmod.block.base;


import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.EnumMap;

public abstract class BaseWallPlantBlock extends HorizontalDirectionalBlock  {
    private static final EnumMap<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, box(1, 1, 8, 15, 15, 16),
            Direction.SOUTH, box(1, 1, 0, 15, 15, 8),
            Direction.WEST, box(8, 1, 1, 16, 15, 15),
            Direction.EAST, box(0, 1, 1, 8, 15, 15)
    ));
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected BaseWallPlantBlock(Properties settings) {
        super(settings);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ePos) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockPos = pos.relative(direction.getOpposite());
        BlockState blockState = level.getBlockState(blockPos);
        return isSupport(level, blockPos, blockState, direction);
    }

    public boolean isSupport(LevelReader world, BlockPos pos, BlockState blockState, Direction direction) {
        return blockState.isSolid() && blockState.isFaceSturdy(world, pos, direction);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {

        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Direction facing,
            BlockState neighborState,
            LevelAccessor world,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        if (!canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return state;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
    private static BlockState mirrorHorizontal(BlockState state, Mirror mirror, Property<Direction> facing) {
        return state.rotate(mirror.getRotation(state.getValue(facing)));
    }


}
