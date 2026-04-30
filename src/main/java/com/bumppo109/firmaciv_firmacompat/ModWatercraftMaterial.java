package com.bumppo109.firmaciv_firmacompat;

import com.alekiponi.alekiships.util.BoatMaterial;
import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.util.CanoeMaterial;
import com.bumppo109.firma_compat.block.CompatWood;
import com.bumppo109.firma_compat.block.ModRegistryWood;
import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.HashSet;
import java.util.List;

public interface ModWatercraftMaterial extends BoatMaterial, CanoeMaterial
{
    ModRegistryWood getWood();

    boolean isSoftwood();

    CompatWood getWoodType();

    HashSet<ModWatercraftMaterial> _ALL_WATERCRAFT_MATERIALS = new HashSet<>();

    static void addMaterials(ModWatercraftMaterial[] materials)
    {
        _ALL_WATERCRAFT_MATERIALS.addAll(List.of(materials));
    }

    static void registerFrames()
    {
        for(var material : _ALL_WATERCRAFT_MATERIALS)
        {
            if(material.isSoftwood())
            {
                CanoeComponentBlock.registerCanoeComponent(
                        (RotatedPillarBlock) material.getWoodType().strippedLog(),
                                CompatFirmaCivBlocks.getCanoeComponentBlocks().get(material).get());
            }
            else
            {
                FirmacivBlocks.BOAT_FRAME_ANGLED.get()
                        .registerFrame(material.getWoodType().planks().asItem(),
                                CompatFirmaCivBlocks.getWoodenBoatFrameAngledBlocks().get(material).get());
                FirmacivBlocks.BOAT_FRAME_FLAT.get()
                        .registerFrame(material.getWoodType().planks().asItem(),
                                CompatFirmaCivBlocks.getWoodenBoatFrameFlatBlocks().get(material).get());
            }
        }
    }
}
