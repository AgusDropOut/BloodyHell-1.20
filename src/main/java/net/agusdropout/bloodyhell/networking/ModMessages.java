package net.agusdropout.bloodyhell.networking;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.networking.packet.CrimsonVeilC2SPacket;
import net.agusdropout.bloodyhell.networking.packet.CrimsonVeilDataSyncS2CPacket;
import net.agusdropout.bloodyhell.networking.packet.EnergySyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    public static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(BloodyHell.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;



        net.messageBuilder(CrimsonVeilC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CrimsonVeilC2SPacket::new)
                .encoder(CrimsonVeilC2SPacket::toBytes)
                .consumerMainThread(CrimsonVeilC2SPacket::handle)
                .add();
        net.messageBuilder(CrimsonVeilDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CrimsonVeilDataSyncS2CPacket::new)
                .encoder(CrimsonVeilDataSyncS2CPacket::toBytes)
                .consumerMainThread(CrimsonVeilDataSyncS2CPacket::handle)
                .add();
        net.messageBuilder(EnergySyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergySyncS2CPacket::new)
                .encoder(EnergySyncS2CPacket::toBytes)
                .consumerMainThread(EnergySyncS2CPacket::handle)
                .add();
    }
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
    public static <MSG> void sendToClients (MSG message){
        INSTANCE.send(PacketDistributor.ALL.noArg(),message);
    }
}
