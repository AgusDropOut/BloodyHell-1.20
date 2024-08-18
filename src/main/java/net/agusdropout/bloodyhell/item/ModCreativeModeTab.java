package net.agusdropout.bloodyhell.item;

import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, BloodyHell.MODID);
    public static RegistryObject<CreativeModeTab> FIRST_TAB = CREATIVE_MODE_TABS.register("first_tab", () -> CreativeModeTab.builder().icon(() ->
            new ItemStack(ModItems.Eyeball.get())).title(Component.literal("Blood Tab")).build());


    public static void register(IEventBus eventbus){
        CREATIVE_MODE_TABS.register(eventbus);
    }

}
