package net.agusdropout.bloodyhell.block.custom;

import net.agusdropout.bloodyhell.block.base.BaseWallPlantBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;

import java.util.Comparator;

public class CinderBloomCactusFlower extends BaseWallPlantBlock {
    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    public CinderBloomCactusFlower(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPEN);
        super.createBlockStateDefinition(builder);
    }



    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);




        if (random.nextFloat() > 0.5f) return;

        double range = 7.0D;
        AABB area = new AABB(pos).inflate(range);

        boolean playerNearby = !level.getEntitiesOfClass(Player.class, area).isEmpty();
        boolean open = state.getValue(OPEN);

        if (playerNearby && open) {
            level.setBlock(pos, state.setValue(OPEN, false), 2);
            level.playLocalSound(pos, SoundEvents.CAVE_VINES_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f, false);
        } else if (!playerNearby && !open) {
            level.setBlock(pos, state.setValue(OPEN, true), 2);
        }
    }
}
