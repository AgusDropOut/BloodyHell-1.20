package net.agusdropout.bloodyhell.registry;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.capability.BloodPortalCapability;
import net.agusdropout.bloodyhell.capability.IBloodPortal;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = BloodyHell.MODID)
public class BloodCapabilities {
    public static final Capability<IBloodPortal> BLOOD_PORTAL_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            BloodPortalCapability portal = new BloodPortalCapability(player);
            LazyOptional<IBloodPortal> capability = LazyOptional.of(() -> portal);
            ICapabilityProvider provider = new ICapabilityProvider() {
                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    return BLOOD_PORTAL_CAPABILITY.orEmpty(cap, capability);
                }
            };
            event.addCapability(new ResourceLocation(BloodyHell.MODID, "blood_portal"), provider);
        }
    }

}
