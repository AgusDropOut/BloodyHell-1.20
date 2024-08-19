package net.agusdropout.bloodyhell.block.custom;

import net.agusdropout.bloodyhell.block.ModBlocks;
import net.agusdropout.bloodyhell.capability.IBloodPortal;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.agusdropout.bloodyhell.registry.BloodCapabilities;
import net.agusdropout.bloodyhell.util.ModTags;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.level.BlockEvent;

import javax.annotation.Nullable;

public class ModBloodPortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public ModBloodPortalBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL)
                .noCollission()
                .strength(-1F)
                .lightLevel((state) -> 10)
                .noLootTable());
        registerDefaultState(stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch (state.getValue(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }

    public boolean trySpawnPortal(LevelAccessor level, BlockPos pos) {
       ModBloodPortalBlock.BloodPortalShape size = this.isPortal(level, pos);
        if (size != null && !onTrySpawnPortal(level, pos, size)) {
            size.createPortalBlocks();
            return true;
        } else {
            return false;
        }
    }

    public static boolean onTrySpawnPortal(LevelAccessor world, BlockPos pos, ModBloodPortalBlock.BloodPortalShape size) {
        return MinecraftForge.EVENT_BUS.post(new BlockEvent.PortalSpawnEvent(world, pos, world.getBlockState(pos), size));
    }



    @Nullable
    public ModBloodPortalBlock.BloodPortalShape isPortal(LevelAccessor level, BlockPos pos) {
        ModBloodPortalBlock.BloodPortalShape bloodPortalShapeX = new BloodPortalShape(level, pos, Direction.Axis.X);
        if (bloodPortalShapeX.isValid() && bloodPortalShapeX.numPortalBlocks == 0) {
            return bloodPortalShapeX;
        } else {
            ModBloodPortalBlock.BloodPortalShape bloodPortalShapeZ = new BloodPortalShape(level, pos, Direction.Axis.Z);
            return bloodPortalShapeZ.isValid() && bloodPortalShapeZ.numPortalBlocks == 0 ? bloodPortalShapeZ : null;
        }
    }


    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis direction$axis = facing.getAxis();
        Direction.Axis direction$axis1 = stateIn.getValue(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && facingState.getBlock() != this && !(new BloodPortalShape(level, currentPos, direction$axis1)).isComplete() ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
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

                LazyOptional<IBloodPortal> portalCapability = entity.getCapability(BloodCapabilities.BLOOD_PORTAL_CAPABILITY);

                if (portalCapability.isPresent()) {
                    portalCapability.ifPresent(consumer -> {
                        consumer.setInPortal(true);
                        int waitTime = consumer.getPortalTimer();
                        if (waitTime >= entity.getPortalWaitTime()) {
                            this.handleBloodPortal(entity);
                            consumer.setPortalTimer(0);
                        }
                    });
                } else this.handleBloodPortal(entity);
            }
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
    public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
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
    public static class BloodPortalShape extends PortalShape {
        private static final int MIN_WIDTH = 1;
        public static final int MAX_WIDTH = 21;
        private static final int MIN_HEIGHT = 2;
        public static final int MAX_HEIGHT = 21;
        private static final BlockBehaviour.StatePredicate FRAME = (state, getter, pos) -> state.is(ModTags.Blocks.PORTAL_FRAME_BLOCKS);
        private final LevelAccessor level;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private int numPortalBlocks;
        private BlockPos bottomLeft;
        private int height;
        private final int width;

        public BloodPortalShape(LevelAccessor level, BlockPos bottomLeftPos, Direction.Axis axis) {
            super(level, bottomLeftPos, axis);
            this.level = level;
            this.axis = axis;
            this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
            this.bottomLeft = this.calculateBottomLeft(bottomLeftPos);
            if (this.bottomLeft == null) {
                this.bottomLeft = bottomLeftPos;
                this.width = 1;
                this.height = 1;
            } else {
                this.width = this.calculateWidth();
                if (this.width > 0) {
                    this.height = this.calculateHeight();
                }
            }

        }

        @Nullable
        private BlockPos calculateBottomLeft(BlockPos pos) {
            for (int i = Math.max(this.level.getMinBuildHeight(), pos.getY() - MAX_HEIGHT); pos.getY() > i && isEmpty(this.level.getBlockState(pos.below())); pos = pos.below()) {
            }

            Direction direction = this.rightDir.getOpposite();
            int j = this.getDistanceUntilEdgeAboveFrame(pos, direction) - 1;
            return j < 0 ? null : pos.relative(direction, j);
        }

        private int calculateWidth() {
            int i = this.getDistanceUntilEdgeAboveFrame(this.bottomLeft, this.rightDir);
            return i >= MIN_WIDTH && i <= MAX_WIDTH ? i : 0;
        }

        private int getDistanceUntilEdgeAboveFrame(BlockPos pos, Direction direction) {
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (int i = 0; i <= MAX_WIDTH; ++i) {
                mutablePos.set(pos).move(direction, i);
                BlockState blockstate = this.level.getBlockState(mutablePos);
                if (!isEmpty(blockstate)) {
                    if (FRAME.test(blockstate, this.level, mutablePos)) {
                        return i;
                    }
                    break;
                }

                BlockState blockstate1 = this.level.getBlockState(mutablePos.move(Direction.DOWN));
                if (!FRAME.test(blockstate1, this.level, mutablePos)) {
                    break;
                }
            }

            return 0;
        }

        private int calculateHeight() {
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
            int i = this.getDistanceUntilTop(mutablePos);
            return i >= MIN_HEIGHT && i <= MAX_HEIGHT && this.hasTopFrame(mutablePos, i) ? i : 0;
        }

        private boolean hasTopFrame(BlockPos.MutableBlockPos pos, int height) {
            for (int i = 0; i < this.width; ++i) {
                BlockPos.MutableBlockPos mutablePos = pos.set(this.bottomLeft).move(Direction.UP, height).move(this.rightDir, i);
                if (!FRAME.test(this.level.getBlockState(mutablePos), this.level, mutablePos)) {
                    return false;
                }
            }

            return true;
        }

        private int getDistanceUntilTop(BlockPos.MutableBlockPos pos) {
            for (int i = 0; i < MAX_HEIGHT; ++i) {
                pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, -1);
                if (!FRAME.test(this.level.getBlockState(pos), this.level, pos)) {
                    return i;
                }

                pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, this.width);
                if (!FRAME.test(this.level.getBlockState(pos), this.level, pos)) {
                    return i;
                }

                for (int j = 0; j < this.width; ++j) {
                    pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, j);
                    BlockState blockstate = this.level.getBlockState(pos);
                    if (!isEmpty(blockstate)) {
                        return i;
                    }

                    if (blockstate.is(ModBlocks.BLOOD_PORTAL.get())) {
                        ++this.numPortalBlocks;
                    }
                }
            }

            return MAX_HEIGHT;
        }

        private static boolean isEmpty(BlockState state) {
            return state.isAir() || state.is(ModBlocks.BLOOD_PORTAL.get());
        }

        public boolean isValid() {
            return this.bottomLeft != null && this.width >= MIN_WIDTH && this.width <= MAX_WIDTH && this.height >= MIN_HEIGHT && this.height <= MAX_HEIGHT;
        }

        public void createPortalBlocks() {
            BlockState blockstate = ModBlocks.BLOOD_PORTAL.get().defaultBlockState().setValue(NetherPortalBlock.AXIS, this.axis);
            BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1)).forEach(pos -> this.level.setBlock(pos, blockstate, 18));
        }

        public boolean isComplete() {
            return this.isValid() && this.numPortalBlocks == this.width * this.height;
        }
    }
}



