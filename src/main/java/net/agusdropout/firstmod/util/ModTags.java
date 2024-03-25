package net.agusdropout.firstmod.util;

import net.agusdropout.firstmod.FirstMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(FirstMod.MODID, name));
        }
        public static final TagKey<Block> PORTAL_FRAME_BLOCKS
                = tag("portal_frame_blocks");

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
  //  public static class Entities {
//
  //      public static final TagKey<EntityType<?>> IMMUNE_TO_EYEBALL_SNAIL_GOO = tag("immune_to_eyeball_snail_goo");
//
//
  //      private static TagKey<EntityType<?>> tag(String name) {
  //          return EntityTypeTags.create(new ResourceLocation(Undergarden.MODID, name).toString());
  //      }
  //  }



}
