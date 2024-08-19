package net.agusdropout.firstmod.networking.packet;

import net.agusdropout.firstmod.fluid.ModFluids;
import net.agusdropout.firstmod.networking.ModMessages;
import net.agusdropout.firstmod.thirst.PlayerThirstProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DrinkWaterC2SPacket {
    public static final String MESSAGE_DRINK_WATER = "message.firstmod.drink_water";
    public static final String MESSAGE_NO_WATER = "message.firstmod.no_water";

    public DrinkWaterC2SPacket(){

    }
    public DrinkWaterC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf Buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
            if(hasBloodAroundThem(player,level, 2)) {
                player.sendSystemMessage(Component.translatable(MESSAGE_DRINK_WATER).withStyle(ChatFormatting.DARK_AQUA));

                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                        0.5F, level.random.nextFloat() * 0.1F + 0.9F);
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    System.out.println("Capability is present!");
                    thirst.addThirst(1);
                    player.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst()).withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                });

            } else {
                player.sendSystemMessage(Component.translatable(MESSAGE_NO_WATER).withStyle(ChatFormatting.DARK_RED));
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {

                    player.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst()).withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                });
            }


        });
        return true;
    }

    private boolean hasBloodAroundThem(ServerPlayer player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size)).anyMatch(estado -> {
            // Obtén el fluido del estado del bloque
            FluidState estadoDelFluido = estado.getFluidState();
            Fluid fluido = estadoDelFluido.getType();

            // Compara el tipo de fluido con tu ModFluids.FLOWING_SOAP_WATER
            return fluido == ModFluids.SOURCE_BLOOD.get();
        });
    }
}
