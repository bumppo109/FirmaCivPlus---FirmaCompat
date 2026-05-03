package com.bumppo109.firmaciv_firmacompat.datagen;

import com.alekiponi.alekiships.util.AlekiShipsTags;
import com.alekiponi.firmaciv.util.FirmacivTags;
import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivBlocks;
import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class FirmaCivPlusBlockTagGenerator extends BlockTagsProvider
{
    public FirmaCivPlusBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, FirmaCivFirmaCompat.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        CompatFirmaCivBlocks.getCanoeComponentBlocks().forEach((watercraftMaterial, canoeComponentBlockRegistryObject) ->
        {
            tag(FirmacivTags.Blocks.CANOE_COMPONENT_BLOCKS)
                    .addOptional(getResourceLocation(canoeComponentBlockRegistryObject.get()));

            tag(FirmacivTags.Blocks.CAN_MAKE_CANOE)
                    .addOptional(getResourceLocation(watercraftMaterial.getStrippedLogBlock()));
            tag(FirmacivTags.Blocks.CAN_MAKE_CANOE_UNRESTRICTED)
                    .addOptional(getResourceLocation(watercraftMaterial.getStrippedLogBlock()));
        });

        CompatFirmaCivBlocks.getWoodenBoatFrameFlatBlocks().forEach((watercraftMaterial, firmacivFlatWoodenBoatFrameBlockRegistryObject) ->
        {
            tag(AlekiShipsTags.Blocks.WOODEN_WATERCRAFT_FRAMES)
                    .addOptional(getResourceLocation(firmacivFlatWoodenBoatFrameBlockRegistryObject.get()));
        });

        CompatFirmaCivBlocks.getWoodenBoatFrameAngledBlocks().forEach((watercraftMaterial, firmacivAngledWoodenBoatFrameBlockRegistryObject) ->
        {
            tag(AlekiShipsTags.Blocks.WOODEN_WATERCRAFT_FRAMES)
                    .addOptional(getResourceLocation(firmacivAngledWoodenBoatFrameBlockRegistryObject.get()));
        });

        CompatFirmaCivBlocks.getWoodRoofings().forEach((registryWood, squaredAngleBlockRegistryObject) ->
        {
            tag(TagKey.create(Registries.BLOCK, new ResourceLocation("alekiroofs", "roofing")))
                    .addOptional(getResourceLocation(squaredAngleBlockRegistryObject.get()));
            tag(BlockTags.MINEABLE_WITH_AXE)
                    .addOptional(getResourceLocation(squaredAngleBlockRegistryObject.get()));
        });
    }

    private ResourceLocation getResourceLocation(Block block)
    {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    /*
    public static Block getStrippedLogBlock(ModWatercraftMaterial material) {
        return ForgeRegistries.BLOCKS.getValue(material.getStrippedLogTexture());
    }

     */
}
