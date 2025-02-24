package net.agusdropout.bloodyhell.util.rituals;

import net.agusdropout.bloodyhell.datagen.ModTags;
import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.util.Ritual;
import net.agusdropout.bloodyhell.worldgen.structure.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class FindMausoleumRitual extends Ritual {
    public FindMausoleumRitual(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, List<List<Item>> itemsInput) {
        super(blockState, level, blockPos, player, interactionHand, blockHitResult, itemsInput);
        super.itemsNeeded = List.of(List.of(ModItems.VEINREAVER_HORN.get(), ModItems.AUREAL_REVENANT_DAGGER.get(),ModItems.GLOW_MUSHROOM.get()), List.of(ModItems.VEINREAVER_HORN.get(), ModItems.AUREAL_REVENANT_DAGGER.get(),ModItems.GLOW_MUSHROOM.get()), List.of(ModItems.VEINREAVER_HORN.get(), ModItems.AUREAL_REVENANT_DAGGER.get(),ModItems.GLOW_MUSHROOM.get()), List.of(ModItems.VEINREAVER_HORN.get(), ModItems.AUREAL_REVENANT_DAGGER.get(),ModItems.GLOW_MUSHROOM.get()));
    }

    @Override
    public void applyResults() {
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel) this.level;
            BlockPos b = serverLevel.findNearestMapStructure(ModTags.Structures.MAUSOLEUM, blockPos, 100, false);

            if (b != null) {
                BlockPos safePos = serverLevel.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, b);


                // Teletransportar al jugador
                player.teleportTo((double) safePos.getX(), (double) safePos.getY(), (double) safePos.getZ());

                // Generar partículas en círculo expandiéndose debajo del jugador
                spawnTeleportParticles(level, player.blockPosition());
                level.playLocalSound(safePos.getX(), safePos.getY(), safePos.getZ(), SoundEvents.ENDERMAN_TELEPORT, player.getSoundSource(), 1.0F, 1.0F, false);

                // Mensaje al jugador
                player.sendSystemMessage(Component.literal("§cYou've have been teleported to the nearest Mausoleum!, good luck"));
            }
        }
    }

    // Método para generar partículas en círculo expandiéndose
    private void spawnTeleportParticles(Level level, BlockPos center) {
        int radius = 1; // Radio inicial
        int maxRadius = 5; // Radio máximo de expansión
        int steps = 8; // Cantidad de partículas por círculo
        double height = 0.1; // Altura de las partículas

        for (int r = radius; r <= maxRadius; r++) {
            for (int i = 0; i < steps; i++) {
                double angle = (2 * Math.PI / steps) * i;
                double xOffset = Math.cos(angle) * r;
                double zOffset = Math.sin(angle) * r;
                BlockPos particlePos = center.offset((int) xOffset, (int) height, (int) zOffset);

                level.addParticle(ParticleTypes.PORTAL,
                        particlePos.getX() + 0.5, particlePos.getY(), particlePos.getZ() + 0.5,
                        1, 0, 0);
            }
        }
    }
}
