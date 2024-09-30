package net.agusdropout.bloodyhell.block.custom;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.OniEntity;
import net.agusdropout.bloodyhell.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class OniStatueBlock extends Block {
    public OniStatueBlock(Properties properties) {
        super(properties);
    }
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1, 1, true);
        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND && player.getMainHandItem().is(ModItems.DIRTY_BLOOD_FLOWER.get())){
            OniEntity oni = new OniEntity(ModEntityTypes.ONI.get(), level);
            level.addFreshEntity(oni);
            oni.moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            player.getMainHandItem().shrink(1);
            level.destroyBlock(blockPos, false);

        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }



}
