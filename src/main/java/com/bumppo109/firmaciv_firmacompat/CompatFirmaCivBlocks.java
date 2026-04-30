package com.bumppo109.firmaciv_firmacompat;

import com.alekiponi.alekiroofs.SquaredAngleBlock;
import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivAngledWoodenBoatFrameBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.common.block.FirmacivFlatWoodenBoatFrameBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Supplier;

import static com.mojang.text2speech.Narrator.LOGGER;

public class CompatFirmaCivBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirmaCivFirmaCompat.MOD_ID);

    private static final Map<ModWatercraftMaterial, RegistryObject<CanoeComponentBlock>> _CANOE_COMPONENT_BLOCKS = new HashMap<>();
    public static Map<ModWatercraftMaterial, RegistryObject<CanoeComponentBlock>> getCanoeComponentBlocks()
    {
        return Collections.unmodifiableMap(_CANOE_COMPONENT_BLOCKS);
    }

    private static final Map<ModWatercraftMaterial, RegistryObject<FirmacivAngledWoodenBoatFrameBlock>> _WOODEN_BOAT_FRAME_ANGLED = new HashMap<>();
    public static Map<ModWatercraftMaterial, RegistryObject<FirmacivAngledWoodenBoatFrameBlock>> getWoodenBoatFrameAngledBlocks()
    {
        return Collections.unmodifiableMap(_WOODEN_BOAT_FRAME_ANGLED);
    }

    private static final Map<ModWatercraftMaterial, RegistryObject<FirmacivFlatWoodenBoatFrameBlock>> _WOODEN_BOAT_FRAME_FLAT = new HashMap<>();
    public static Map<ModWatercraftMaterial, RegistryObject<FirmacivFlatWoodenBoatFrameBlock>> getWoodenBoatFrameFlatBlocks()
    {
        return Collections.unmodifiableMap(_WOODEN_BOAT_FRAME_FLAT);
    }

    private static final Map<ModWatercraftMaterial, RegistryObject<SquaredAngleBlock>> _WOOD_ROOFING = new HashMap<>();
    public static Map<ModWatercraftMaterial, RegistryObject<SquaredAngleBlock>> getWoodRoofings()
    {
        return Collections.unmodifiableMap(_WOOD_ROOFING);
    }


    static void init(IEventBus eventBus)
    {
        LOGGER.info("FirmaCiv FirmaCompat adding Blocks");
        for(var woodEntry : ModWatercraftMaterial._ALL_WATERCRAFT_MATERIALS)
        {
            //putWoodRoofing(woodEntry);
            if(woodEntry.isSoftwood())
            {
                putCanoeComponentBlock(woodEntry);
            }
            else
            {
                putAngledBoatFrameBlock(woodEntry);
                putFlatBoatFrameBlock(woodEntry);
            }
        }

        if(!BLOCKS.getEntries().isEmpty())
            BLOCKS.register(eventBus);
    }

    /*
    private static void putWoodRoofing(ModWatercraftMaterial compatWatercraftMaterial)
    {
        String name = "wood/" + compatWatercraftMaterial.getSerializedName() + "_roofing";
        Supplier<SquaredAngleBlock> supplier = () ->
        {
            var stairs = compatWatercraftMaterial.stair();
            var blockState = stairs.defaultBlockState();
            var blockProperties = BlockBehaviour.Properties.copy(stairs)
                    .mapColor(compatWatercraftMaterial.getWood().woodColor()).noOcclusion();
            return new SquaredAngleBlock(blockState, blockProperties);
        };

        var entry = BLOCKS.register(name, supplier);
        _WOOD_ROOFING.put(compatWatercraftMaterial, entry);
        CompatFirmaCivItems.ITEMS.register(name, () -> new BlockItem(entry.get(), new Item.Properties()));
    }

     */

    private static void putCanoeComponentBlock(ModWatercraftMaterial compatWatercraftMaterial)
    {
        String name = "wood/canoe_component_block/" + compatWatercraftMaterial.getSerializedName();
        Supplier<CanoeComponentBlock> supplier = () ->
        {
            var wood = compatWatercraftMaterial.getStrippedLogBlock();
            var strippedLog = wood;

            BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(strippedLog).noOcclusion();
            return new CanoeComponentBlock(properties, compatWatercraftMaterial);
        };

        var entry = BLOCKS.register(name, supplier);
        _CANOE_COMPONENT_BLOCKS.put(compatWatercraftMaterial, entry);
    }

    private static void putAngledBoatFrameBlock(ModWatercraftMaterial compatWatercraftMaterial)
    {
        String name = "wood/watercraft_frame/angled/" + compatWatercraftMaterial.getSerializedName();
        Supplier<FirmacivAngledWoodenBoatFrameBlock> supplier = () ->
                new FirmacivAngledWoodenBoatFrameBlock(compatWatercraftMaterial, BlockBehaviour.Properties.copy(FirmacivBlocks.BOAT_FRAME_ANGLED.get()));
        var entry = BLOCKS.register(name, supplier);
        _WOODEN_BOAT_FRAME_ANGLED.put(compatWatercraftMaterial, entry);
    }

    private static void putFlatBoatFrameBlock(ModWatercraftMaterial modWatercraftMaterial)
    {
        String name = "wood/watercraft_frame/flat/" + modWatercraftMaterial.getSerializedName();
        Supplier<FirmacivFlatWoodenBoatFrameBlock> supplier = () ->
                new FirmacivFlatWoodenBoatFrameBlock(modWatercraftMaterial, BlockBehaviour.Properties.copy(FirmacivBlocks.BOAT_FRAME_FLAT.get()));
        var entry = BLOCKS.register(name, supplier);
        _WOODEN_BOAT_FRAME_FLAT.put(modWatercraftMaterial, entry);
    }
}
