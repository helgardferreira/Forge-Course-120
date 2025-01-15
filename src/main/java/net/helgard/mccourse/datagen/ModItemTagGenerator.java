package net.helgard.mccourse.datagen;

import net.helgard.mccourse.MCCourseMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(
            PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> holderLookupProvider,
            CompletableFuture<TagLookup<Block>> tagLookupProvider,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(packOutput, holderLookupProvider, tagLookupProvider, MCCourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider lookupProvider) {
        // Add tags here...
        // tag(ModTags.)
    }

    @Override
    public @NotNull String getName() {
        return "Item Tags";
    }
}
