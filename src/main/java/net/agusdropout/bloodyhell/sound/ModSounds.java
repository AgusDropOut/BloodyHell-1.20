package net.agusdropout.bloodyhell.sound;

import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BloodyHell.MODID);

    public static final RegistryObject<SoundEvent> GRAWL_DROWN = registerSoundEvents("grawl_drown");
    public static final RegistryObject<SoundEvent> GRAWL_HURT = registerSoundEvents("grawl_hurt");
    public static final RegistryObject<SoundEvent> GRAWL_ATTACK = registerSoundEvents("grawl_attack");
    public static final RegistryObject<SoundEvent> GRAWL_DEATH = registerSoundEvents("grawl_death");





    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BloodyHell.MODID, name)));
    }


    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
