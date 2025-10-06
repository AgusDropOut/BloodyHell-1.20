package net.agusdropout.bloodyhell.entity.EntityBaseTypes;

import java.awt.*;

public interface BloodyHellBoss {
    float getHealth();
    float getMaxHealth();
    boolean isDeadOrDying();
    String getBossName();
}
