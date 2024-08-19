package net.agusdropout.bloodyhell.datagen;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, BloodyHell.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLOOD_LOG.get().asItem())
                .add(ModBlocks.BLOOD_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_BLOOD_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_BLOOD_WOOD.get().asItem())
                .add(ModBlocks.SOUL_LOG.get().asItem())
                .add(ModBlocks.SOUL_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_SOUL_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_SOUL_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.SOUL_PLANKS.get().asItem());
    }
}
