package com.bumppo109.firmaciv_firmacompat;

import com.alekiponi.firmaciv.common.blockentity.CanoeComponentBlockEntity;
import com.alekiponi.firmaciv.common.blockentity.FirmacivBlockEntities;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.mojang.text2speech.Narrator.LOGGER;

public class CompatFirmaCivBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FirmaCivFirmaCompat.MOD_ID);

    private static final RegistryObject<BlockEntityType<CanoeComponentBlockEntity>> _CANOE_COMPONENT_BLOCK_ENTITY = register("canoe_component_block_entity", CanoeComponentBlockEntity::new, CompatFirmaCivBlocks.getCanoeComponentBlocks().values().stream());

    static void registerCanoesToFirmaCivRegistry()
    {
        LOGGER.info("FirmaCiv FirmaCompat registered canoes");
        var registry = FirmacivBlockEntities.CANOE_COMPONENT_BLOCK_ENTITY;
        var blockEntityType = registry.get();
        var hashSet = new HashSet<Block>();
        hashSet.addAll(_CANOE_COMPONENT_BLOCK_ENTITY.get().validBlocks);
        hashSet.addAll(blockEntityType.validBlocks);
        blockEntityType.validBlocks = hashSet;
    }

    static void init(IEventBus bus)
    {
        LOGGER.info("FirmaCiv FirmaCompat adding Block Entities");
        BLOCK_ENTITIES.register(bus);
    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Stream<? extends Supplier<? extends Block>> blocks)
    {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, blocks);
    }
}
