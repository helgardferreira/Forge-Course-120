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
