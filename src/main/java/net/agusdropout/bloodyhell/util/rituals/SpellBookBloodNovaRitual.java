package net.agusdropout.bloodyhell.util.rituals;

import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.util.ItemResultRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class SpellBookBloodNovaRitual extends ItemResultRitual {
    public SpellBookBloodNovaRitual(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, List<List<Item>> itemsInput) {
        super(blockState, level, blockPos, player, interactionHand, blockHitResult, itemsInput);
        super.itemsNeeded = List.of(List.of(Items.BOOK, ModItems.FILLED_RHNULL_BLOOD_FLASK.get(),ModItems.ANCIENT_GEM.get()), List.of(ModItems.AUREAL_REVENANT_DAGGER.get(), ModItems.FILLED_RHNULL_BLOOD_FLASK.get(),ModItems.ANCIENT_GEM.get()), List.of(ModItems.AUREAL_REVENANT_DAGGER.get(), ModItems.FILLED_RHNULL_BLOOD_FLASK.get(),ModItems.ANCIENT_GEM.get()), List.of(ModItems.AUREAL_REVENANT_DAGGER.get(), ModItems.FILLED_RHNULL_BLOOD_FLASK.get(),ModItems.ANCIENT_GEM.get()));
        super.result = new ItemStack(ModItems.BLOOD_SPELL_BOOK_BLOODNOVA.get());
    }


}
