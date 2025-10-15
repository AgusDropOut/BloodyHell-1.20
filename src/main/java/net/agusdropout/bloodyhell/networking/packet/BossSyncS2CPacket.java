package net.agusdropout.bloodyhell.networking.packet;

import net.agusdropout.bloodyhell.client.ClientBossBarData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BossSyncS2CPacket {
    private final int health;
    private final int maxHealth;
    private final boolean dead;
    private final boolean isNear;

    public BossSyncS2CPacket(int health, int maxHealth, boolean dead, boolean isNear) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.dead = dead;
        this.isNear = isNear;
    }

    public BossSyncS2CPacket(FriendlyByteBuf buf) {
        this.health = buf.readInt();
        this.maxHealth = buf.readInt();
        this.dead = buf.readBoolean();
        this.isNear = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(health);
        buf.writeInt(maxHealth);
        buf.writeBoolean(dead);
        buf.writeBoolean(isNear);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientBossBarData.setCurrentBoss(health, maxHealth, dead,isNear);
        });
        return true;
    }
}