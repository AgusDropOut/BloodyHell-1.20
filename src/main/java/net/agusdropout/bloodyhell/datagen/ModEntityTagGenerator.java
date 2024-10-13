package net.agusdropout.bloodyhell.datagen;

import net.agusdropout.bloodyhell.BloodyHell;
import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagGenerator extends EntityTypeTagsProvider {

   public ModEntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper existingFileHelper) {
       super(output, future, BloodyHell.MODID, existingFileHelper);
   }

   @Override
   public String getName() {
       return "bloodyhell Entity Type Tags";
   }

   @Override
   protected void addTags(HolderLookup.Provider provider) {

       tag(ModTags.Entities.INMUNE_TO_BLEEDING_BLOCK)
               .add(ModEntityTypes.EYESHELL_SNAIL.get())
               .add(ModEntityTypes.BLOOD_SEEKER.get())
               .add(ModEntityTypes.BLOODTHIRSTYBEAST.get())
               .add(ModEntityTypes.ONI.get())
               .add(ModEntityTypes.BLOODPIG.get());

    }
}
