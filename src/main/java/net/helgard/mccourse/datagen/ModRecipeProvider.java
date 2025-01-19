package net.helgard.mccourse.datagen;

import net.helgard.mccourse.MCCourseMod;
import net.helgard.mccourse.block.ModBlocks;
import net.helgard.mccourse.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ALEXANDRITE_SMELTABLES = List.of(
            ModBlocks.ALEXANDRITE_ORE.get(),
            ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
            ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
            ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
            ModItems.RAW_ALEXANDRITE.get()
    );

    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }


    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        customNineBlockStorageRecipes(
                recipeOutput,
                RecipeCategory.MISC,
                ModItems.ALEXANDRITE.get(),
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.ALEXANDRITE_BLOCK.get()
        );
        customNineBlockStorageRecipes(
                recipeOutput,
                RecipeCategory.MISC,
                ModItems.RAW_ALEXANDRITE.get(),
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.RAW_ALEXANDRITE_BLOCK.get()
        );

        customOreSmelting(
                recipeOutput,
                ALEXANDRITE_SMELTABLES,
                RecipeCategory.MISC,
                ModItems.ALEXANDRITE.get(),
                0.25f,
                200,
                "alexandrite"
        );
        customOreBlasting(
                recipeOutput,
                ALEXANDRITE_SMELTABLES,
                RecipeCategory.MISC,
                ModItems.ALEXANDRITE.get(),
                0.25f,
                100,
                "alexandrite"
        );

        customSlab(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.ALEXANDRITE_SLAB.get(),
                ModItems.ALEXANDRITE.get()
        );

        customStairs(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.ALEXANDRITE_STAIRS.get(),
                ModItems.ALEXANDRITE.get()
        );

        customButton(
                recipeOutput,
                RecipeCategory.REDSTONE,
                ModBlocks.ALEXANDRITE_BUTTON.get(),
                ModItems.ALEXANDRITE.get()
        );

        customPressurePlate(
                recipeOutput,
                RecipeCategory.REDSTONE,
                ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(),
                ModItems.ALEXANDRITE.get()
        );

        customFence(
                recipeOutput,
                RecipeCategory.DECORATIONS,
                ModBlocks.ALEXANDRITE_FENCE.get(),
                ModItems.ALEXANDRITE.get()
        );

        customFenceGate(
                recipeOutput,
                RecipeCategory.REDSTONE,
                ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
                ModItems.ALEXANDRITE.get()
        );

        customWall(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.ALEXANDRITE_WALL.get(),
                ModBlocks.ALEXANDRITE_BLOCK.get()
        );

        customDoor(
                recipeOutput,
                RecipeCategory.REDSTONE,
                ModBlocks.ALEXANDRITE_DOOR.get(),
                ModItems.ALEXANDRITE.get()
        );

        customTrapdoor(
                recipeOutput,
                RecipeCategory.REDSTONE,
                ModBlocks.ALEXANDRITE_TRAPDOOR.get(),
                ModItems.ALEXANDRITE.get()
        );
    }

    private static void customTrapdoor(
            RecipeOutput recipeOutput,
            RecipeCategory recipeCategory,
            ItemLike trapdoor,
            ItemLike material
    ) {
        ShapedRecipeBuilder
                .shaped(recipeCategory, trapdoor, 2)
                .define('#', material)
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private static void customDoor(
            RecipeOutput recipeOutput,
            RecipeCategory recipeCategory,
            ItemLike door,
            ItemLike material
    ) {
        ShapedRecipeBuilder
                .shaped(recipeCategory, door, 3)
                .define('#', material)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }


    private static void customWall(
            RecipeOutput recipeOutput,
            RecipeCategory recipeCategory,
            ItemLike wall,
            ItemLike material
    ) {
        ShapedRecipeBuilder
                .shaped(recipeCategory, wall, 6)
                .define('#', material)
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput)
        ;
    }

    private static void customFenceGate(
            RecipeOutput recipeOutput,
            RecipeCategory recipeCategory,
            ItemLike fenceGate,
            ItemLike material
    ) {
        ShapedRecipeBuilder
                .shaped(recipeCategory, fenceGate)
                .define('#', Items.STICK)
                .define('W', material)
                .pattern("#W#")
                .pattern("#W#")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private static void customFence(
            RecipeOutput recipeOutput,
            RecipeCategory recipeCategory,
            ItemLike fence,
            ItemLike material
    ) {
        ShapedRecipeBuilder
                .shaped(recipeCategory, fence, 3)
                .define('W', material)
                .define('#', Items.STICK)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private static void customPressurePlate(
            RecipeOutput recipeOutput,
            RecipeCategory recipeCategory,
            ItemLike pressurePlate,
            ItemLike material
    ) {
        ShapedRecipeBuilder
                .shaped(recipeCategory, pressurePlate)
                .define('#', material)
                .pattern("##")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private static void customButton(
            RecipeOutput recipeOutput,
            RecipeCategory recipeCategory,
            ItemLike button,
            ItemLike material
    ) {
        ShapelessRecipeBuilder
                .shapeless(recipeCategory, button)
                .requires(material)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private static void customStairs(
            RecipeOutput recipeOutput,
            RecipeCategory recipeCategory,
            ItemLike stairs,
            ItemLike material
    ) {
        ShapedRecipeBuilder
                .shaped(recipeCategory, stairs, 4)
                .define('#', material)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private static void customSlab(
            RecipeOutput recipeOutput,
            RecipeCategory recipeCategory,
            ItemLike slab,
            ItemLike material
    ) {
        ShapedRecipeBuilder
                .shaped(recipeCategory, slab, 6)
                .define('#', material)
                .pattern("###")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private static void customNineBlockStorageRecipes(
            RecipeOutput recipeOutput,
            RecipeCategory unpackedCategory,
            ItemLike unpacked,
            RecipeCategory packedCategory,
            ItemLike packed
    ) {
        ShapedRecipeBuilder
                .shaped(packedCategory, packed)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', unpacked)
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(recipeOutput);
        ShapelessRecipeBuilder
                .shapeless(unpackedCategory, unpacked, 9)
                .requires(packed)
                .unlockedBy(getHasName(packed), has(packed))
                .save(recipeOutput);
    }

    private static void customOreSmelting(
            @NotNull RecipeOutput recipeOutput,
            List<ItemLike> ingredients,
            @NotNull RecipeCategory recipeCategory,
            @NotNull ItemLike result,
            float experience,
            int cookingTime,
            @NotNull String group
    ) {
        customOreCooking(
                recipeOutput,
                RecipeSerializer.SMELTING_RECIPE,
                SmeltingRecipe::new,
                ingredients,
                recipeCategory,
                result,
                experience,
                cookingTime,
                group,
                "_from_smelting"
        );
    }

    private static void customOreBlasting(
            @NotNull RecipeOutput recipeOutput,
            List<ItemLike> ingredients,
            @NotNull RecipeCategory recipeCategory,
            @NotNull ItemLike result,
            float experience,
            int cookingTime,
            @NotNull String group
    ) {
        customOreCooking(
                recipeOutput,
                RecipeSerializer.BLASTING_RECIPE,
                BlastingRecipe::new,
                ingredients,
                recipeCategory,
                result,
                experience,
                cookingTime,
                group,
                "_from_blasting");
    }

    private static <T extends AbstractCookingRecipe> void customOreCooking(
            RecipeOutput recipeOutput,
            RecipeSerializer<T> recipeSerializer,
            AbstractCookingRecipe.Factory<T> recipeFactory,
            List<ItemLike> ingredients,
            RecipeCategory recipeCategory,
            ItemLike result,
            float experience,
            int cookingTime,
            String group,
            String suffix
    ) {
        for (ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder
                    .generic(
                            Ingredient.of(itemlike),
                            recipeCategory,
                            result,
                            experience,
                            cookingTime,
                            recipeSerializer,
                            recipeFactory
                    )
                    .group(group)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(
                            recipeOutput,
                            MCCourseMod.MOD_ID + ":" + getItemName(result) + suffix + "_" + getItemName(itemlike)
                    );
        }
    }
}
