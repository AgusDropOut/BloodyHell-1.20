package net.agusdropout.bloodyhell.item.potions;

import net.agusdropout.bloodyhell.datagen.ModTags;
import net.agusdropout.bloodyhell.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class BloodFlaskItem extends Item {
    public BloodFlaskItem(Properties p_42979_) {
        super(p_42979_);
    }

   // public InteractionResultHolder<ItemStack> use(Level level, Player context, InteractionHand interactionHand) {
//
//
   //         ItemStack itemStack = context.getItemInHand(interactionHand);
   //         BlockHitResult hitResult = getPlayerPOVHitResult(level, context, ClipContext.Fluid.SOURCE_ONLY);
   //         if (hitResult.getType() == HitResult.Type.MISS) {
   //             return InteractionResultHolder.pass(itemStack);
   //         } else {
   //             if (hitResult.getType() == HitResult.Type.BLOCK) {
   //                 context.getMainHandItem().shrink(1);
   //                 BlockPos $$8 = hitResult.getBlockPos();
   //                 if (!level.mayInteract(context, $$8)) {
   //                     return InteractionResultHolder.pass(itemStack);
   //                 }
//
   //                 if (level.getFluidState($$8).is(FluidTags.WATER)) {
   //                     level.playSound(context, context.getX(), context.getY(), context.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
   //                     level.gameEvent(context, GameEvent.FLUID_PICKUP, $$8);
   //                     return InteractionResultHolder.sidedSuccess(turnBottleIntoItem(itemStack, context,new ItemStack (ModItems.Eight_ball.get())), level.isClientSide());
   //                 }
   //             }
//
   //             return InteractionResultHolder.pass(itemStack);
   //         }
   //
   // }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        InteractionHand interactionHand = context.getHand();
        Player player = context.getPlayer();
        ItemStack itemStack = player.getItemInHand(interactionHand);
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else {
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos $$8 = hitResult.getBlockPos();
                if (!level.mayInteract(player, $$8)) {
                    return InteractionResult.PASS;
                }

                if (level.getFluidState($$8).is(ModTags.Fluids.BLOODY_LIQUID)) {
                    level.playSound(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                    level.gameEvent(player, GameEvent.FLUID_PICKUP, $$8);
                    player.setItemInHand(interactionHand, new ItemStack(ModItems.FILLED_BLOOD_FLASK.get()));
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            }

            return InteractionResult.PASS;
        }
    }

    protected ItemStack turnBottleIntoItem(ItemStack p_40652_, Player p_40653_, ItemStack p_40654_) {
        p_40653_.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(p_40652_, p_40653_, p_40654_);
    }



}
