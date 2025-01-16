package net.helgard.mccourse.datagen.loot;

import net.helgard.mccourse.block.ModBlocks;
import net.helgard.mccourse.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
        this.dropSelf(ModBlocks.ALEXANDRITE_STAIRS.get());
        this.dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());

        this.add(
                ModBlocks.ALEXANDRITE_ORE.get(),
                block -> createOreDropWithCount(
                        ModBlocks.ALEXANDRITE_ORE.get(),
                        ModItems.RAW_ALEXANDRITE.get(),
                        1.0f,
                        1.0f
                )
        );
        this.add(
                ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
                block -> createOreDropWithCount(
                        ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
                        ModItems.RAW_ALEXANDRITE.get(),
                        1.0f,
                        2.0f
                )
        );
        this.add(
                ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
                block -> createOreDropWithCount(
                        ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
                        ModItems.RAW_ALEXANDRITE.get(),
                        2.0f,
                        4.0f
                )
        );
        this.add(
                ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
                block -> createOreDropWithCount(
                        ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
                        ModItems.RAW_ALEXANDRITE.get(),
                        2.0f,
                        4.0f
                )
        );

        this.add(
                ModBlocks.ALEXANDRITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.ALEXANDRITE_SLAB.get())
        );
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS
                .getEntries()
                .stream()
                .map(RegistryObject::get)::iterator;
    }

    private LootTable.Builder createOreDropWithCount(Block block, Item item, float min, float max) {
        return createSilkTouchDispatchTable(
                block,
                this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.FORTUNE))
                )
        );
    }
}
