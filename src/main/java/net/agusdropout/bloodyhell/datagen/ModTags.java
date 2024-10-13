package net.agusdropout.bloodyhell.datagen;

import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags {

    public static class Items {
    }

    public static class Blocks {


        public static final TagKey<Block> BLOOD_SCRAPPER_PLANT_PLACEABLE_ON = tag("blood_scrapper_plant_placeable_on");
        public static final TagKey<Block> NEEDS_BLOOD_TOOL = tag("needs_blood_tool");
        public static final TagKey<Block> BLOOD_ORE_REPLACEABLES = tag("blood_ore_replaceables");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(BloodyHell.MODID, name));
        }

    }

    public static class Entities {
        public static final TagKey<EntityType<?>> INMUNE_TO_BLEEDING_BLOCK = tag("inmune_to_bleeding_block");
        private static TagKey<EntityType<?>> tag(String name) {
            return EntityTypeTags.create(new ResourceLocation(BloodyHell.MODID, name).toString());
        }
    }

    public static class Fluids {



        private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(new ResourceLocation(BloodyHell.MODID, name));
        }
    }

    public static class Biomes {



        private static TagKey<Biome> tag(String name) {
            return BiomeTags.create(new ResourceLocation(BloodyHell.MODID, name).toString());
        }
    }

}