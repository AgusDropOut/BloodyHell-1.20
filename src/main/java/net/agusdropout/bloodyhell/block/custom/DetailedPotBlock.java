package net.agusdropout.bloodyhell.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class DetailedPotBlock extends Block {

    public static final IntegerProperty SIZE = IntegerProperty.create("size",0,2);

    public DetailedPotBlock(Properties properties) {
        super(properties);
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SIZE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public void setPlacedBy(Level p_49847_, BlockPos p_49848_, BlockState p_49849_, @Nullable LivingEntity p_49850_, ItemStack p_49851_) {
        super.setPlacedBy(p_49847_, p_49848_, p_49849_, p_49850_, p_49851_);
        Random random = new Random();
        if(random.nextFloat() < 0.33f) {
            p_49847_.setBlock(p_49848_, p_49849_.setValue(SIZE, 0), 3);
        } else if(random.nextFloat() < 0.66f) {
            p_49847_.setBlock(p_49848_, p_49849_.setValue(SIZE, 1), 3);
        } else  {
            p_49847_.setBlock(p_49848_, p_49849_.setValue(SIZE, 2), 3);
        }
    }
}
