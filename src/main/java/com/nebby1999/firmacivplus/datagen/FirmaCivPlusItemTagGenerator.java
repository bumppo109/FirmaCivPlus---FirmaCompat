package com.nebby1999.firmacivplus.datagen;

import com.alekiponi.alekiships.util.AlekiShipsTags;
import com.alekiponi.firmaciv.util.FirmacivTags;
import com.nebby1999.firmacivplus.FirmaCivPlus;
import com.nebby1999.firmacivplus.FirmaCivPlusBlocks;
import com.nebby1999.firmacivplus.WatercraftMaterial;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FirmaCivPlusItemTagGenerator extends ItemTagsProvider
{
    public FirmaCivPlusItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(pOutput, pLookupProvider, pBlockTags, FirmaCivPlus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        FirmaCivPlusBlocks.getWoodenBoatFrameFlatBlocks().forEach((watercraftMaterial, firmacivFlatWoodenBoatFrameBlockRegistryObject) ->
        {
            tag(FirmacivTags.Items.HARD_WOOD)
                    .addOptional(getResourceLocation(watercraftMaterial.getDeckItem()));
        });

        WatercraftMaterial._ALL_WATERCRAFT_MATERIALS.forEach(watercraftMaterial ->
        {
            tag(AlekiShipsTags.Items.CRAFTING_TABLES)
                    .addOptional(getResourceLocation(watercraftMaterial.getWood().getBlock(Wood.BlockType.WORKBENCH).get().asItem()));
            tag(FirmacivTags.Items.CHESTS)
                    .addOptional(getResourceLocation(watercraftMaterial.getWood().getBlock(Wood.BlockType.CHEST).get().asItem()));
        });
    }

    private ResourceLocation getResourceLocation(Item item)
    {
        return ForgeRegistries.ITEMS.getKey(item);
    }
}
