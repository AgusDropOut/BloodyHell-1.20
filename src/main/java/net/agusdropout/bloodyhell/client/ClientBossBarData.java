package net.agusdropout.bloodyhell.client;

import net.agusdropout.bloodyhell.entity.custom.BloodyHellBoss;

public class ClientBossBarData {
    private static int health;
    private static int maxHealth;
    private static boolean dead;
    private static boolean isNear;

    public static void setCurrentBoss(int hp, int maxHp, boolean isDead, boolean near) {
        health = hp;
        maxHealth = maxHp;
        dead = isDead;
        isNear = near;

    }

    public static int getHealth() { return health; }
    public static int getMaxHealth() { return maxHealth; }
    public static boolean isDead() { return dead; }
    public static boolean isNear() { return isNear; }
    public static void clear() { dead = true; }
}