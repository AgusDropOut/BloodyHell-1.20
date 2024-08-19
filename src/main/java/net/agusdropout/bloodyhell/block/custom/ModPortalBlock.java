package net.agusdropout.bloodyhell.block.custom;

import net.agusdropout.bloodyhell.particle.ModParticles;
import net.agusdropout.bloodyhell.worldgen.dimension.ModDimensions;
import net.agusdropout.bloodyhell.worldgen.portal.ModTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModPortalBlock extends Block {
    public ModPortalBlock() {
        super(Properties.of()
                .pushReaction(PushReaction.BLOCK)
                .strength(-1F)
                .noCollission()
                .lightLevel((state) -> 10)
                .noLootTable()
        );
        registerDefaultState(stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.canChangeDimensions()) {
            handleBloodPortal(pPlayer);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }

    private void handleBloodPortal(Entity entity) {
        MinecraftServer server = entity.level().getServer();
        ResourceKey<Level> destination = entity.level().dimension() == ModDimensions.SOUL_LEVEL_KEY ? Level.OVERWORLD : ModDimensions.SOUL_LEVEL_KEY;
        if (server != null) {
            ServerLevel destinationLevel = server.getLevel(destination);
            if (destinationLevel != null && server.isNetherEnabled() && !entity.isPassenger()) {
                entity.level().getProfiler().push("blood_portal");
                entity.setPortalCooldown();
                entity.changeDimension(destinationLevel, new ModTeleporter(destinationLevel));
                entity.level().getProfiler().pop();
            }
        }
    }
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                if (!entity.level().isClientSide() && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }

                Level entityWorld = entity.level();
                if(entityWorld != null) {
                    MinecraftServer minecraftserver = entityWorld.getServer();
                    ResourceKey<Level> destination = entity.level().dimension() == ModDimensions.SOUL_LEVEL_KEY
                            ? Level.OVERWORLD : ModDimensions.SOUL_LEVEL_KEY;
                    if(minecraftserver != null) {
                        ServerLevel destinationWorld = minecraftserver.getLevel(destination);
                        if(destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
                            entity.level().getProfiler().push("blood_portal");
                            entity.setPortalCooldown();
                            entity.changeDimension(destinationWorld, new ModTeleporter(destinationWorld));
                            entity.level().getProfiler().pop();
                        }
                    }
                }
            }
        }
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(100) == 0) {
            level.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.ALLAY_AMBIENT_WITH_ITEM, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double x = (double) pos.getX() + random.nextDouble();
            double y = (double) pos.getY() + random.nextDouble();
            double z = (double) pos.getZ() + random.nextDouble();
            double xSpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
            double ySpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
            double zSpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
            int j = random.nextInt(2) * 2 - 1;
            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                x = (double) pos.getX() + 0.5D + 0.25D * (double) j;
                xSpeed = random.nextFloat() * 2.0F * (float) j;
            } else {
                z = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
                zSpeed = random.nextFloat() * 2.0F * (float) j;
            }
            level.addParticle(ModParticles.BLOOD_PARTICLES.get(), x, y, z, xSpeed, ySpeed, zSpeed);

        }

    }
    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(AXIS)) {
                    case Z:
                        return state.setValue(AXIS, Direction.Axis.X);
                    case X:
                        return state.setValue(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

}
