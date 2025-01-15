package net.helgard.mccourse.datagen;

import net.helgard.mccourse.MCCourseMod;
import net.helgard.mccourse.block.ModBlocks;
import net.helgard.mccourse.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
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
        ShapedRecipeBuilder
                .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ALEXANDRITE.get())
                .unlockedBy("has_alexandrite", has(ModItems.ALEXANDRITE.get()))
                .save(recipeOutput);
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 9)
                .requires(ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_alexandrite_block", has(ModBlocks.ALEXANDRITE_BLOCK.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_ALEXANDRITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.RAW_ALEXANDRITE.get())
                .unlockedBy("has_raw_alexandrite", has(ModItems.RAW_ALEXANDRITE.get()))
                .save(recipeOutput);
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE.get(), 9)
                .requires(ModBlocks.RAW_ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_raw_alexandrite_block", has(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()))
                .save(recipeOutput);

        oreSmelting(
                recipeOutput,
                ALEXANDRITE_SMELTABLES,
                RecipeCategory.MISC,
                ModItems.ALEXANDRITE.get(),
                0.25f,
                200,
                "alexandrite"
        );

        oreBlasting(
                recipeOutput,
                ALEXANDRITE_SMELTABLES,
                RecipeCategory.MISC,
                ModItems.ALEXANDRITE.get(),
                0.25f,
                100,
                "alexandrite"
        );
    }

    protected static void oreSmelting(
            @NotNull RecipeOutput recipeOutput,
            List<ItemLike> ingredients,
            @NotNull RecipeCategory recipeCategory,
            @NotNull ItemLike result,
            float experience,
            int cookingTime,
            @NotNull String group
    ) {
        oreCooking(
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

    protected static void oreBlasting(
            @NotNull RecipeOutput recipeOutput,
            List<ItemLike> ingredients,
            @NotNull RecipeCategory recipeCategory,
            @NotNull ItemLike result,
            float experience,
            int cookingTime,
            @NotNull String group
    ) {
        oreCooking(
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

    private static <T extends AbstractCookingRecipe> void oreCooking(
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
