package net.agusdropout.bloodyhell.worldgen.tree;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.worldgen.tree.custom.BloodTreeTrunkDecorator;
import net.agusdropout.bloodyhell.worldgen.tree.custom.SoulTreeLeafDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, BloodyHell.MODID);
    public static final RegistryObject<TreeDecoratorType<SoulTreeLeafDecorator>> SOUL_TREE_LEAF_DECORATOR =
            TREE_DECORATORS.register("soul_tree_leaf_decorator", () -> new TreeDecoratorType<>(SoulTreeLeafDecorator.CODEC));
    public static final RegistryObject<TreeDecoratorType<BloodTreeTrunkDecorator>> BLOOD_TREE_TRUNK_DECORATOR =
            TREE_DECORATORS.register("blood_tree_trunk_decorator", () -> new TreeDecoratorType<>(BloodTreeTrunkDecorator.CODEC));


    public static void register(IEventBus eventbus){
        TREE_DECORATORS.register(eventbus);
    }
}
