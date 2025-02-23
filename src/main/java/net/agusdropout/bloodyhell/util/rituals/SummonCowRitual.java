package net.agusdropout.bloodyhell.util.rituals;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.BloodSeekerEntity;
import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.util.Ritual;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class SummonCowRitual extends Ritual {

    public SummonCowRitual(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, List<List<Item>> itemsInput) {
        super(blockState, level, blockPos, player, interactionHand, blockHitResult, itemsInput);
        super.itemsNeeded = List.of(List.of(Items.WHEAT,Items.MILK_BUCKET,Items.WHEAT), List.of(Items.WHEAT,Items.MILK_BUCKET,Items.WHEAT), List.of(Items.WHEAT,Items.MILK_BUCKET,Items.WHEAT), List.of(Items.WHEAT,Items.MILK_BUCKET,Items.WHEAT));
    }

    @Override
    public void applyResults() {
        Cow cow = new Cow(EntityType.COW, level);
        level.addFreshEntity(cow);
        cow.setPos(blockPos.getX()+1, blockPos.getY(), blockPos.getZ());
    }

}
