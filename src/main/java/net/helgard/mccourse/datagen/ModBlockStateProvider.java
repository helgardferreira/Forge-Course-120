package net.helgard.mccourse.datagen;

import net.helgard.mccourse.MCCourseMod;
import net.helgard.mccourse.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MCCourseMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.END_STONE_ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.NETHER_ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.RAW_ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.SOUND_BLOCK);

        buttonBlock(
                (ButtonBlock) ModBlocks.ALEXANDRITE_BUTTON.get(),
                blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get())
        );

        pressurePlateBlock(
                (PressurePlateBlock) ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(),
                blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get())
        );
        blockItem(ModBlocks.ALEXANDRITE_PRESSURE_PLATE);

        slabBlock(
                (SlabBlock) ModBlocks.ALEXANDRITE_SLAB.get(),
                blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()),
                blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get())
        );
        blockItem(ModBlocks.ALEXANDRITE_SLAB);

        stairsBlock(
                (StairBlock) ModBlocks.ALEXANDRITE_STAIRS.get(),
                blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get())
        );
        blockItem(ModBlocks.ALEXANDRITE_STAIRS);

        fenceBlock(
                (FenceBlock) ModBlocks.ALEXANDRITE_FENCE.get(),
                blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get())
        );

        fenceGateBlock(
                (FenceGateBlock) ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
                blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get())
        );
        blockItem(ModBlocks.ALEXANDRITE_FENCE_GATE);

        wallBlock(
                (WallBlock) ModBlocks.ALEXANDRITE_WALL.get(),
                blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get())
        );

        doorBlockWithRenderType(
                (DoorBlock) ModBlocks.ALEXANDRITE_DOOR.get(),
                new ResourceLocation(MCCourseMod.MOD_ID, "block/alexandrite_door_bottom"),
                new ResourceLocation(MCCourseMod.MOD_ID, "block/alexandrite_door_top"),
                "cutout"
        );

        trapdoorBlockWithRenderType(
                (TrapDoorBlock) ModBlocks.ALEXANDRITE_TRAPDOOR.get(),
                new ResourceLocation(MCCourseMod.MOD_ID, "block/alexandrite_trapdoor"),
                true,
                "cutout"
        );
        blockItem(ModBlocks.ALEXANDRITE_TRAPDOOR, "_bottom");
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject, String appendix) {
        simpleBlockItem(
                blockRegistryObject.get(),
                new ModelFile.UncheckedModelFile(
                        "mccourse:block/" + ForgeRegistries.BLOCKS
                                .getKey(blockRegistryObject.get())
                                .getPath() +
                                appendix
                )
        );
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(
                blockRegistryObject.get(),
                new ModelFile.UncheckedModelFile(
                        "mccourse:block/" + ForgeRegistries.BLOCKS
                                .getKey(blockRegistryObject.get())
                                .getPath()
                )
        );
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
