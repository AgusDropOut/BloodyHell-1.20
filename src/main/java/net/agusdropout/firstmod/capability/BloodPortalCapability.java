package net.agusdropout.firstmod.capability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Player;

public class BloodPortalCapability implements IBloodPortal{
    private final Player player;

    public boolean isInsidePortal = false;
    public int portalTimer = 0;
    public float portalAnimTime = 0;
    public float prevPortalAnimTime = 0;

    public BloodPortalCapability(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void setInPortal(boolean inPortal) {
        this.isInsidePortal = inPortal;
    }

    @Override
    public boolean isInsidePortal() {
        return this.isInsidePortal;
    }

    @Override
    public void setPortalTimer(int timer) {
        this.portalTimer = timer;
    }

    @Override
    public int getPortalTimer() {
        return this.portalTimer;
    }

    @Override
    public float getPortalAnimTime() {
        return this.portalAnimTime;
    }

    @Override
    public float getPrevPortalAnimTime() {
        return this.prevPortalAnimTime;
    }

    @Override
    public void handleBloodPortal() {
        if (this.getPlayer().level().isClientSide()) {
            this.prevPortalAnimTime = this.portalAnimTime;
            Minecraft minecraft = Minecraft.getInstance();
            if (this.isInsidePortal) {
                if (minecraft.screen != null && !minecraft.screen.isPauseScreen()) {
                    if (minecraft.screen instanceof AbstractContainerScreen) {
                        this.getPlayer().closeContainer();
                    }
                    minecraft.setScreen(null);
                }

                if (this.getPortalAnimTime() == 0.0F) {

                }
            }
        }

        if (this.isInsidePortal()) {
            ++this.portalTimer;
            if (this.getPlayer().level().isClientSide()) {
                this.portalAnimTime += 0.0125F;
                if (this.getPortalAnimTime() > 1.0F) {
                    this.portalAnimTime = 1.0F;
                }
            }
            this.isInsidePortal = false;
        } else {
            if (this.getPlayer().level().isClientSide()) {
                if (this.getPortalAnimTime() > 0.0F) {
                    this.portalAnimTime -= 0.05F;
                }

                if (this.getPortalAnimTime() < 0.0F) {
                    this.portalAnimTime = 0.0F;
                }
            }
            if (this.getPortalTimer() > 0) {
                this.portalTimer -= 4;
            }
        }
    }
}
