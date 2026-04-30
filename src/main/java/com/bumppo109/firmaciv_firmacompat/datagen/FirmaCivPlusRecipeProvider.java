package com.bumppo109.firmaciv_firmacompat.datagen;

import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivBlocks;
import com.bumppo109.firmaciv_firmacompat.datagen.recipe_builders.AdvancedCraftingRecipeBuilder;
import com.bumppo109.firmaciv_firmacompat.datagen.recipe_builders.DisabledRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class FirmaCivPlusRecipeProvider extends RecipeProvider
{
    public FirmaCivPlusRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        var sawTag = TagKey.create(Registries.ITEM, new ResourceLocation("tfc", "saws"));
        CompatFirmaCivBlocks.getWoodRoofings().forEach((watercraftMaterial, squaredAngleBlockRegistryObject) ->
        {
            var planks = watercraftMaterial.getWoodType().planks();

            //crafting roofing
            AdvancedCraftingRecipeBuilder.shaped("crafting", squaredAngleBlockRegistryObject.get(), 6, CraftingBookCategory.BUILDING)
                    .pattern("  a")
                    .pattern(" a ")
                    .pattern("a  ")
                    .define('a', planks)
                    .unlockedBy("has_plank", has(planks))
                    .showNotification(true)
                    .save(consumer);

            var lumber = watercraftMaterial.getRailing();
            var lumberRegistry = ForgeRegistries.ITEMS.getKey(lumber);
            //uncrafting roofing
            AdvancedCraftingRecipeBuilder.shapeless("crafting", watercraftMaterial.getRailing(), 2)
                    .requires(squaredAngleBlockRegistryObject.get())
                    .requires(sawTag)
                    .primaryIngredient(Ingredient.of(sawTag))
                    .damageInputs()
                    .unlockedBy("has_saw", has(sawTag))
                    .unlockedBy("has_roofing", has(squaredAngleBlockRegistryObject.get()))
                    .save(consumer, new ResourceLocation(FirmaCivFirmaCompat.MOD_ID, "crafting/" + lumberRegistry.getPath()));

            //kill off the boat
            var dummy = new DisabledRecipe(new ResourceLocation(lumberRegistry.getNamespace(), "crafting/wood/" + watercraftMaterial.getSerializedName() + "_boat"));
            consumer.accept(dummy);
        });
    }
}
