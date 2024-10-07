package net.agusdropout.bloodyhell.entity.custom;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import net.agusdropout.bloodyhell.block.entity.BloodWorkbenchBlockEntity;
import net.agusdropout.bloodyhell.item.ModItems;
import net.agusdropout.bloodyhell.screen.BloodWorkBenchMenu;
import net.agusdropout.bloodyhell.screen.VesperMenu;
import net.agusdropout.bloodyhell.worldgen.dimension.ModDimensions;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.OptionalInt;
import java.util.UUID;

public class VesperEntity extends Mob implements MenuProvider {

    public VesperEntity(EntityType<? extends Mob> p_35267_, Level p_35268_) {
        super(p_35267_, p_35268_);

    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.ATTACK_SPEED, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 7.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.23).build();
    }


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, this);
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new VesperMenu(i, inventory,new ItemStackHandler(3), this);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Vesper");
    }


}



