package net.agusdropout.bloodyhell.block.custom;

import net.agusdropout.bloodyhell.block.entity.BloodAltarBlockEntity;
import net.agusdropout.bloodyhell.block.entity.BloodWorkbenchBlockEntity;
import net.agusdropout.bloodyhell.block.entity.MainBloodAltarBlockEntity;
import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.util.VanillaPacketDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainBloodAltarBlock extends BaseEntityBlock {
    private MainBloodAltarBlockEntity mainBloodAltarEntity;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public MainBloodAltarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
        super.createBlockStateDefinition(builder);
    }

    private static final VoxelShape SHAPE =
            Block.box(0, 0, 0, 16, 35, 16);

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        mainBloodAltarEntity = new MainBloodAltarBlockEntity(pos, state);
        return mainBloodAltarEntity;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof MainBloodAltarBlockEntity) {
                ((MainBloodAltarBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!(level.getBlockEntity(blockPos) instanceof MainBloodAltarBlockEntity altar)) {
            return InteractionResult.PASS;
        }
        System.out.println(isAltarSetupReady(level,blockPos));

        if (interactionHand == InteractionHand.MAIN_HAND) {
            ItemStack heldItem = player.getMainHandItem();
            if (heldItem.is(ModItems.FILLED_BLOOD_FLASK.get())){
                altar.setActive(true);
                player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.BLOOD_FLASK.get()));
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(altar);
                level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, 1.0F, false);
                level.setBlock(blockPos,blockState.setValue(ACTIVE,true), 3);
                return InteractionResult.sidedSuccess(level.isClientSide());
            } else if (altar.isActive() && isAltarSetupReady(level,blockPos)){
               if(rainRitual(blockState,level,blockPos,player,interactionHand,blockHitResult)){
                   return InteractionResult.sidedSuccess(level.isClientSide());
               }
            }


        }

        return InteractionResult.PASS;
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {


        if (random.nextInt(100) == 0 && state.getValue(ACTIVE)) {
            level.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.WARDEN_AMBIENT, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }



        super.animateTick(state, level, pos, random);
    }

    //ritual function

    public boolean isAltarSetupReady(Level level, BlockPos pos){

            BlockPos smallAltarPos = pos.north(4);
            if(!(level.getBlockState(smallAltarPos).getBlock() instanceof BloodAltarBlock)){
                return false;
            }
            smallAltarPos = pos.east(4);
            if(!(level.getBlockState(smallAltarPos).getBlock() instanceof BloodAltarBlock)){
                return false;
            }
            smallAltarPos = pos.south(4);
            if(!(level.getBlockState(smallAltarPos).getBlock() instanceof BloodAltarBlock)){
                return false;
            }
            smallAltarPos = pos.west(4);
            if(!(level.getBlockState(smallAltarPos).getBlock() instanceof BloodAltarBlock)){
                return false;
            }
        return true;
    }

    public List<List<ItemStack>> getItemsFromAltars(Level level, BlockPos pos){
        List<List<ItemStack>> items = new ArrayList<>();
        BlockPos[] altarPositions = {
                pos.north(4),
                pos.east(4),
                pos.south(4),
                pos.west(4)
        };

        for (BlockPos altarPos : altarPositions) {
            if (level.getBlockState(altarPos).getBlock() instanceof BloodAltarBlock) {
                BloodAltarBlockEntity bloodAltarBlockEntity =
                        (BloodAltarBlockEntity) level.getBlockEntity(altarPos);
                if (bloodAltarBlockEntity != null) { // Verifica si la entidad del bloque no es nula
                    List<ItemStack> itemStacks = bloodAltarBlockEntity.getItemsInside();
                    if (!itemStacks.isEmpty()) { // Asegúrate de que no esté vacío antes de agregarlo
                        items.add(itemStacks);
                    }
                }
            }
        }
        return items;
    }

    public void consumeItemsFromAltars(Level level, BlockPos pos){
        BlockPos smallAltarPos = pos.north(4);
        if(level.getBlockState(smallAltarPos).getBlock() instanceof BloodAltarBlock){
            BloodAltarBlockEntity bloodAltarBlockEntity = (BloodAltarBlockEntity) level.getBlockEntity(smallAltarPos);
            assert bloodAltarBlockEntity != null;
            bloodAltarBlockEntity.clearItemsInside();
        }
        smallAltarPos = pos.east(4);
        if(level.getBlockState(smallAltarPos).getBlock() instanceof BloodAltarBlock){
            BloodAltarBlockEntity bloodAltarBlockEntity = (BloodAltarBlockEntity) level.getBlockEntity(smallAltarPos);
            assert bloodAltarBlockEntity != null;
            bloodAltarBlockEntity.clearItemsInside();
        }
        smallAltarPos = pos.south(4);
        if(level.getBlockState(smallAltarPos).getBlock() instanceof BloodAltarBlock){
            BloodAltarBlockEntity bloodAltarBlockEntity = (BloodAltarBlockEntity) level.getBlockEntity(smallAltarPos);
            assert bloodAltarBlockEntity != null;
            bloodAltarBlockEntity.clearItemsInside();
        }
        smallAltarPos = pos.west(4);
        if(level.getBlockState(smallAltarPos).getBlock() instanceof BloodAltarBlock){
            BloodAltarBlockEntity bloodAltarBlockEntity = (BloodAltarBlockEntity) level.getBlockEntity(smallAltarPos);
            assert bloodAltarBlockEntity != null;
            bloodAltarBlockEntity.clearItemsInside();
        }
        level.destroyBlock(pos.east(),false);
    }
    public boolean rainRitual(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult){
        if (!(level.getBlockEntity(blockPos) instanceof MainBloodAltarBlockEntity altar)) {
            return false;
        }
            List<List<ItemStack>> items = getItemsFromAltars(level,blockPos);
            if(items.size() == 4){
                for (List<ItemStack> altarContainer : items) {
                    for (ItemStack stack : altarContainer) {
                        if(stack.is(ModItems.BLOOD_FLASK.get())){
                            consumeItemsFromAltars(level,blockPos);
                            altar.setActive(false);
                            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(altar);
                            level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 1.0F, 1.0F, false);
                            level.setBlock(blockPos,blockState.setValue(ACTIVE,false), 3);
                            return true;
                        }
                    }
                }
            }

        return false;
    }

}


