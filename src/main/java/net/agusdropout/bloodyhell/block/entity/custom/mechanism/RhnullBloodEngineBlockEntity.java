package net.agusdropout.bloodyhell.block.entity.custom.mechanism;

import net.agusdropout.bloodyhell.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RhnullBloodEngineBlockEntity extends BlockEntity {

    private UUID ownerUUID;
    public static final Set<BlockPos> ACTIVE_BLOBS = new HashSet<>();

    public RhnullBloodEngineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RHNULL_BLOOD_ENGINE.get(), pos, state);
    }

    public void setOwner(LivingEntity owner) {
        if (owner != null) {
            this.ownerUUID = owner.getUUID();
            this.setChanged();
        }
    }

    public boolean isSafe(LivingEntity entity) {
        if (this.ownerUUID == null || entity == null) {
            return false;
        }
        return this.ownerUUID.equals(entity.getUUID());
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (this.ownerUUID != null) {
            tag.putUUID("OwnerUUID", this.ownerUUID);
        }
    }



    @Override
    public void onLoad() {
        super.onLoad();
        if (this.level != null && this.level.isClientSide) {
            ACTIVE_BLOBS.add(this.worldPosition);
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        if (this.level != null && this.level.isClientSide) {
            ACTIVE_BLOBS.remove(this.worldPosition);
        }
    }
}