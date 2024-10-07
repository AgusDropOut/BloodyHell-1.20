package net.agusdropout.bloodyhell.screen;
import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, BloodyHell.MODID);

    public static final RegistryObject<MenuType<BloodWorkBenchMenu>> BLOOD_WORKBENCH_MENU =
            registerMenuType(BloodWorkBenchMenu::new, "blood_workbench_menu");
    public static final RegistryObject<MenuType<VesperMenu>> VESPER_MENU = ModMenuTypes.MENUS.register("vesper_menu", () -> new MenuType(VesperMenu::new, FeatureFlags.DEFAULT_FLAGS));

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
