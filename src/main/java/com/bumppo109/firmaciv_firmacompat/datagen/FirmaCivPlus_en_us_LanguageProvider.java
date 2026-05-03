package com.bumppo109.firmaciv_firmacompat.datagen;

import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivBlocks;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivEntities;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FirmaCivPlus_en_us_LanguageProvider extends LanguageProvider
{
    public FirmaCivPlus_en_us_LanguageProvider(PackOutput output) {
        super(output, FirmaCivFirmaCompat.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlockTranslations();
        addEntityTranslations();
    }

    private void addBlockTranslations()
    {
        add("block_type.firmaciv_firmacompat.roofing", "%s Roofing");

        CompatFirmaCivBlocks.getWoodRoofings().forEach((material, registryObject) ->
        {
            addBlock(registryObject, langify(material.getSerializedName()) + " Roofing");
        });

        CompatFirmaCivBlocks.getCanoeComponentBlocks().forEach(((watercraftMaterial, canoeComponentBlockRegistryObject) ->
        {
            addBlock(canoeComponentBlockRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Canoe Component");
        }));

        CompatFirmaCivBlocks.getWoodenBoatFrameFlatBlocks().forEach((watercraftMaterial, firmacivFlatWoodenBoatFrameBlockRegistryObject) ->
        {
            addBlock(firmacivFlatWoodenBoatFrameBlockRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Flat Shipwright's Scaffolding");
        });

        CompatFirmaCivBlocks.getWoodenBoatFrameAngledBlocks().forEach((watercraftMaterial, firmacivAngledWoodenBoatFrameBlockRegistryObject) ->
        {
            addBlock(firmacivAngledWoodenBoatFrameBlockRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Sloped Shipwright's Scaffolding");
        });
    }

    private void addEntityTranslations()
    {
        CompatFirmaCivEntities.getCanoes().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            addEntityType(entityTypeRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Dugout Canoe");
        });

        CompatFirmaCivEntities.getSloops().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            addEntityType(entityTypeRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Sloop");
        });

        CompatFirmaCivEntities.getSloopsUnderConstruction().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            addEntityType(entityTypeRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Sloop");
        });

        CompatFirmaCivEntities.getRowboats().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            addEntityType(entityTypeRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Rowboat");
        });
    }

    public String langify(final String serializedName) {
        return Arrays.stream(serializedName.split("_"))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .collect(Collectors.joining(" "));
    }
}
