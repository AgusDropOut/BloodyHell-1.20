package net.agusdropout.bloodyhell.entity.custom;

public interface BloodyHellBoss {
    float getHealth();
    float getMaxHealth();
    boolean isDeadOrDying();
    String getBossName();
}
