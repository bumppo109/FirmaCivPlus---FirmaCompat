package com.bumppo109.firmaciv_firmacompat;

import com.alekiponi.alekiships.util.BoatMaterial;
import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.util.CanoeMaterial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.HashSet;
import java.util.List;

import static com.mojang.text2speech.Narrator.LOGGER;

public interface ModWatercraftMaterial extends BoatMaterial, CanoeMaterial
{
    //ModRegistryWood getWood();

    boolean isSoftwood();
    String getNamespace();

    Block getPlanks();
    Block getStrippedLogBlock();
    Block getStairs();

    ResourceLocation getPlanksTexture();
    ResourceLocation getStrippedLogTexture();
    ResourceLocation getStrippedLogTopTexture();

    //CompatWood getWoodType();

    HashSet<ModWatercraftMaterial> _ALL_WATERCRAFT_MATERIALS = new HashSet<>();

    static void addMaterials(ModWatercraftMaterial[] materials)
    {
        LOGGER.info("FirmaCiv FirmaCompat adding materials");
        _ALL_WATERCRAFT_MATERIALS.addAll(List.of(materials));
    }

    static void registerFrames()
    {
        LOGGER.info("FirmaCiv FirmaCompat registered frames");
        for(var material : _ALL_WATERCRAFT_MATERIALS)
        {
            if(material.isSoftwood())
            {
                CanoeComponentBlock.registerCanoeComponent(
                        (RotatedPillarBlock) material.getStrippedLogBlock(),
                                CompatFirmaCivBlocks.getCanoeComponentBlocks().get(material).get());
            }
            else
            {
                FirmacivBlocks.BOAT_FRAME_ANGLED.get()
                        .registerFrame(material.getPlanks().asItem(),
                                CompatFirmaCivBlocks.getWoodenBoatFrameAngledBlocks().get(material).get());
                FirmacivBlocks.BOAT_FRAME_FLAT.get()
                        .registerFrame(material.getPlanks().asItem(),
                                CompatFirmaCivBlocks.getWoodenBoatFrameFlatBlocks().get(material).get());
            }
        }
    }
}
