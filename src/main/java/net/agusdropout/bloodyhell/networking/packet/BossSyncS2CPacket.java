package net.agusdropout.bloodyhell.networking.packet;

import net.agusdropout.bloodyhell.client.ClientBossBarData;
import net.agusdropout.bloodyhell.entity.EntityBaseTypes.BloodyHellBoss;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BossSyncS2CPacket {
    private final int bossId; // ID de entidad o -1 para limpiar

    public BossSyncS2CPacket(int bossId) {
        this.bossId = bossId;
    }

    public BossSyncS2CPacket(FriendlyByteBuf buf) {
        this.bossId = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(bossId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level == null) return;

            if (bossId == -1) {
                ClientBossBarData.setCurrentBoss(null);
                return;
            }

            Entity e = Minecraft.getInstance().level.getEntity(bossId);
            if (e instanceof BloodyHellBoss boss) {
                ClientBossBarData.setCurrentBoss(boss);
            } else {
                ClientBossBarData.setCurrentBoss(null);
            }
        });
        return true;
    }
}
