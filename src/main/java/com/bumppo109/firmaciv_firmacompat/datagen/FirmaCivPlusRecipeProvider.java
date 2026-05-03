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

        var sawTag = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("tfc", "saws"));
        CompatFirmaCivBlocks.getWoodRoofings().forEach((watercraftMaterial, squaredAngleBlockRegistryObject) ->
        {
            //kill off the boat
            var dummy = new DisabledRecipe(ResourceLocation.fromNamespaceAndPath(watercraftMaterial.getNamespace(), watercraftMaterial.getSerializedName() + "_boat"));
            consumer.accept(dummy);
        });
    }
}
