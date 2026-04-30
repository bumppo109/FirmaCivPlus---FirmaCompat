package com.bumppo109.firmaciv_firmacompat.datagen;

import com.alekiponi.alekiships.util.AlekiShipsTags;
import com.alekiponi.firmaciv.util.FirmacivTags;
import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivBlocks;
import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FirmaCivPlusItemTagGenerator extends ItemTagsProvider
{
    public FirmaCivPlusItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(pOutput, pLookupProvider, pBlockTags, FirmaCivFirmaCompat.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        CompatFirmaCivBlocks.getWoodenBoatFrameFlatBlocks().forEach((watercraftMaterial, firmacivFlatWoodenBoatFrameBlockRegistryObject) ->
        {
            tag(FirmacivTags.Items.HARD_WOOD)
                    .addOptional(getResourceLocation(watercraftMaterial.getDeckItem()));
        });

        ModWatercraftMaterial._ALL_WATERCRAFT_MATERIALS.forEach(watercraftMaterial ->
        {
            tag(AlekiShipsTags.Items.CRAFTING_TABLES)
                    .addOptional(getResourceLocation(Blocks.CRAFTING_TABLE.asItem()));
            tag(FirmacivTags.Items.CHESTS)
                    .addOptional(getResourceLocation(Blocks.CHEST.asItem()));
        });
    }

    private ResourceLocation getResourceLocation(Item item)
    {
        return ForgeRegistries.ITEMS.getKey(item);
    }
}
