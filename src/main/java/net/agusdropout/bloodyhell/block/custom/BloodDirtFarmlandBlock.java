package net.agusdropout.bloodyhell.block.custom;

import net.agusdropout.bloodyhell.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;


import javax.annotation.Nullable;

public class BloodDirtFarmlandBlock extends FarmBlock {
    public BloodDirtFarmlandBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 0));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? ModBlocks.BLOOD_DIRT_BLOCK.get().defaultBlockState() : super.getStateForPlacement(context);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter getter, BlockPos pos, Direction direction, IPlantable plantable) {
        PlantType type = plantable.getPlantType(getter, pos.relative(direction));
        return type == PlantType.CROP || type == PlantType.PLAINS;
    }

    public static void turnToDeepsoil(@Nullable net.minecraft.world.entity.Entity entity, BlockState state, Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, pushEntitiesUp(state, ModBlocks.BLOOD_DIRT_BLOCK.get().defaultBlockState(), level, pos));
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, state));
    }

    private static boolean isNearWater(LevelReader level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (state.canBeHydrated(level, pos, level.getFluidState(blockpos), blockpos)) {
                return true;
            }
        }
        return FarmlandWaterManager.hasBlockWaterTicket(level, pos);
    }

    private static boolean isUnderCrops(BlockGetter level, BlockPos pos) {
        BlockState plant = level.getBlockState(pos.above());
        BlockState state = level.getBlockState(pos);
        return plant.getBlock() instanceof IPlantable && state.canSustainPlant(level, pos, Direction.UP, (IPlantable) plant.getBlock());
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            turnToDeepsoil(null, state, level, pos);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int moistness = state.getValue(MOISTURE);
        if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
            if (moistness > 0) {
                level.setBlock(pos, state.setValue(MOISTURE, moistness - 1), 2);
            } else if (!isUnderCrops(level, pos)) {
                turnToDeepsoil(null, state, level, pos);
            }
        } else if (moistness < 7) {
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        }

    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float v) {

            if (!level.isClientSide() && blockState.getBlock() instanceof FarmBlock) {
                // Acciones específicas para el bloque de tierra de cultivo (FarmlandBlock)
                BlockState newState = ModBlocks.BLOOD_DIRT_BLOCK.get().defaultBlockState(); // Nuevo estado de bloque
                level.setBlockAndUpdate(blockPos, newState); // Cambiar el bloque a "blood dirt block"

                // Realizar otras acciones necesarias, como causar daño a la entidad
                entity.causeFallDamage(v, 1.0F, level.damageSources().fall());
            } else {
                // Si no es tierra de cultivo, simplemente causar daño por la caída normalmente
                entity.causeFallDamage(v, 1.0F, level.damageSources().fall());
            }
        }
    }


