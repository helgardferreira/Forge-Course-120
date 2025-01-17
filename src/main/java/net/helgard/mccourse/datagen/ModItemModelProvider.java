package net.helgard.mccourse.datagen;

import net.helgard.mccourse.MCCourseMod;
import net.helgard.mccourse.block.ModBlocks;
import net.helgard.mccourse.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MCCourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(ModBlocks.ALEXANDRITE_DOOR);
        simpleItem(ModItems.ALEXANDRITE);
        simpleItem(ModItems.KOHLRABI);
        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.PEAT_BRICK);
        simpleItem(ModItems.RAW_ALEXANDRITE);

        handheldItem(ModItems.ALEXANDRITE_AXE);
        handheldItem(ModItems.ALEXANDRITE_HOE);
        handheldItem(ModItems.ALEXANDRITE_PAXEL);
        handheldItem(ModItems.ALEXANDRITE_PICKAXE);
        handheldItem(ModItems.ALEXANDRITE_SHOVEL);
        handheldItem(ModItems.ALEXANDRITE_SWORD);

        buttonItem(ModBlocks.ALEXANDRITE_BUTTON, ModBlocks.ALEXANDRITE_BLOCK);

        fenceItem(ModBlocks.ALEXANDRITE_FENCE, ModBlocks.ALEXANDRITE_BLOCK);

        wallItem(ModBlocks.ALEXANDRITE_WALL, ModBlocks.ALEXANDRITE_BLOCK);
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(
                ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                new ResourceLocation("block/fence_inventory")
        ).texture(
                "texture",
                new ResourceLocation(
                        MCCourseMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()
                )
        );
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(
                ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                new ResourceLocation("block/wall_inventory")
        ).texture(
                "wall",
                new ResourceLocation(
                        MCCourseMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()
                )
        );
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(
                ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                new ResourceLocation("block/button_inventory")
        ).texture(
                "texture",
                new ResourceLocation(
                        MCCourseMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()
                )
        );
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(
                item.getId().getPath(),
                new ResourceLocation("item/handheld")
        ).texture(
                "layer0",
                new ResourceLocation(
                        MCCourseMod.MOD_ID,
                        "item/" + item.getId().getPath()
                )
        );
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(
                item.getId().getPath(),
                new ResourceLocation("item/generated")
        ).texture(
                "layer0",
                new ResourceLocation(
                        MCCourseMod.MOD_ID,
                        "item/" + item.getId().getPath()
                )
        );
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(
                item.getId().getPath(),
                new ResourceLocation("item/generated")
        ).texture(
                "layer0",
                new ResourceLocation(
                        MCCourseMod.MOD_ID,
                        "item/" + item.getId().getPath()
                )
        );
    }
}
