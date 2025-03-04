package net.agusdropout.bloodyhell.util.rituals;

import net.agusdropout.bloodyhell.datagen.ModTags;
import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.util.ItemResultRitual;
import net.agusdropout.bloodyhell.util.Ritual;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class BloodAncientGemRitual extends ItemResultRitual {
    public BloodAncientGemRitual(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, List<List<Item>> itemsInput) {
        super(blockState, level, blockPos, player, interactionHand, blockHitResult, itemsInput);
        super.itemsNeeded = List.of(List.of(ModItems.VEINREAVER_HORN.get(), ModItems.FILLED_BLOOD_FLASK.get(),ModItems.SANGUINITE.get()), List.of(ModItems.VEINREAVER_HORN.get(), ModItems.FILLED_BLOOD_FLASK.get(),ModItems.SANGUINITE.get()), List.of(ModItems.VEINREAVER_HORN.get(), ModItems.FILLED_BLOOD_FLASK.get(),ModItems.SANGUINITE.get()), List.of(ModItems.VEINREAVER_HORN.get(), ModItems.FILLED_BLOOD_FLASK.get(),ModItems.SANGUINITE.get()));
        super.result = new ItemStack(ModItems.ANCIENT_GEM.get());
    }


}
