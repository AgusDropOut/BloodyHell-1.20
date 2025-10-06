package net.agusdropout.bloodyhell.client;

import net.agusdropout.bloodyhell.entity.EntityBaseTypes.BloodyHellBoss;

public class ClientBossBarData {
    private static BloodyHellBoss currentBoss;

    public static void setCurrentBoss(BloodyHellBoss boss) {
        currentBoss = boss;
    }

    public static BloodyHellBoss getCurrentBoss() {
        return currentBoss;
    }
}