package net.agusdropout.firstmod.recipe;


import net.agusdropout.firstmod.FirstMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FirstMod.MODID);

    public static final RegistryObject<RecipeSerializer<BloodWorkBenchRecipe>> GEM_INFUSING_SERIALIZER =
            SERIALIZERS.register("blood_infusing", () -> BloodWorkBenchRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}