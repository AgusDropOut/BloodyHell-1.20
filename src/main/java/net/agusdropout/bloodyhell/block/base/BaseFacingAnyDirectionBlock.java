package net.agusdropout.bloodyhell.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;


public class BaseFacingAnyDirectionBlock extends Block  implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED;
    public static final DirectionProperty FACING;


    

    public BaseFacingAnyDirectionBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState) this.defaultBlockState().setValue(WATERLOGGED, false)).setValue(FACING, Direction.UP));
    }

    public boolean canSurvive(BlockState p_152026_, LevelReader p_152027_, BlockPos p_152028_) {
        Direction $$3 = (Direction)p_152026_.getValue(FACING);
        BlockPos $$4 = p_152028_.relative($$3.getOpposite());
        return p_152027_.getBlockState($$4).isFaceSturdy(p_152027_, $$4, $$3);
    }

    public BlockState updateShape(BlockState p_152036_, Direction p_152037_, BlockState p_152038_, LevelAccessor p_152039_, BlockPos p_152040_, BlockPos p_152041_) {
        if ((Boolean)p_152036_.getValue(WATERLOGGED)) {
            p_152039_.scheduleTick(p_152040_, Fluids.WATER, Fluids.WATER.getTickDelay(p_152039_));
        }

        return p_152037_ == ((Direction)p_152036_.getValue(FACING)).getOpposite() && !p_152036_.canSurvive(p_152039_, p_152040_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_152036_, p_152037_, p_152038_, p_152039_, p_152040_, p_152041_);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_152019_) {
        LevelAccessor $$1 = p_152019_.getLevel();
        BlockPos $$2 = p_152019_.getClickedPos();
        return (BlockState)((BlockState)this.defaultBlockState().setValue(WATERLOGGED, $$1.getFluidState($$2).getType() == Fluids.WATER)).setValue(FACING, p_152019_.getClickedFace());
    }

    public BlockState rotate(BlockState p_152033_, Rotation p_152034_) {
        return (BlockState)p_152033_.setValue(FACING, p_152034_.rotate((Direction)p_152033_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_152030_, Mirror p_152031_) {
        return p_152030_.rotate(p_152031_.getRotation((Direction)p_152030_.getValue(FACING)));
    }

    public FluidState getFluidState(BlockState p_152045_) {
        return (Boolean)p_152045_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_152045_);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152043_) {
        p_152043_.add(new Property[]{WATERLOGGED, FACING});
    }

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        FACING = BlockStateProperties.FACING;
    }
}