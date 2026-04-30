package com.bumppo109.firmaciv_firmacompat.datagen.recipe_builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.FalseCondition;
import org.jetbrains.annotations.Nullable;

public final class DisabledRecipe implements FinishedRecipe
{
    ResourceLocation recipeID;
    public DisabledRecipe(ResourceLocation id)
    {
        recipeID = id;
    }

    @Override
    public JsonObject serializeRecipe() {
        JsonObject jsonobject = new JsonObject();
        this.serializeRecipeData(jsonobject);
        return jsonobject;
    }

    @Override
    public void serializeRecipeData(JsonObject pJson) {
        JsonArray conditionsArray = new JsonArray();
        conditionsArray.add(CraftingHelper.serialize(FalseCondition.INSTANCE));

        pJson.add("conditions", conditionsArray);
    }

    @Override
    public ResourceLocation getId() {
        return recipeID;
    }

    @Override
    public RecipeSerializer<?> getType() {
        return null;
    }

    @Override
    public @Nullable JsonObject serializeAdvancement() {
        return null;
    }

    @Override
    public @Nullable ResourceLocation getAdvancementId() {
        return null;
    }
}
