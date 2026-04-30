package com.bumppo109.firmaciv_firmacompat;

import com.alekiponi.firmaciv.common.entity.vehicle.CanoeEntity;
import com.alekiponi.firmaciv.common.entity.vehicle.FirmacivRowboatEntity;
import com.alekiponi.firmaciv.common.entity.vehicle.FirmacivSloopEntity;
import com.alekiponi.firmaciv.common.entity.vehicle.FirmacivSloopUnderConstructionEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;

public class CompatFirmaCivEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FirmaCivFirmaCompat.MOD_ID);

    private static final Map<ModWatercraftMaterial, RegistryObject<EntityType<CanoeEntity>>> _CANOES = new HashMap<>();
    public static Map<ModWatercraftMaterial, RegistryObject<EntityType<CanoeEntity>>> getCanoes()
    {
        return Collections.unmodifiableMap(_CANOES);
    }

    private static final Map<ModWatercraftMaterial, RegistryObject<EntityType<FirmacivRowboatEntity>>> _ROWBOATS = new HashMap<>();
    public static Map<ModWatercraftMaterial, RegistryObject<EntityType<FirmacivRowboatEntity>>> getRowboats()
    {
        return Collections.unmodifiableMap(_ROWBOATS);
    }

    private static final Map<ModWatercraftMaterial, RegistryObject<EntityType<FirmacivSloopUnderConstructionEntity>>> _SLOOPS_UNDER_CONSTRUCTION = new HashMap<>();
    public static Map<ModWatercraftMaterial, RegistryObject<EntityType<FirmacivSloopUnderConstructionEntity>>> getSloopsUnderConstruction()
    {
        return Collections.unmodifiableMap(_SLOOPS_UNDER_CONSTRUCTION);
    }

    private static final Map<ModWatercraftMaterial, RegistryObject<EntityType<FirmacivSloopEntity>>> _SLOOPS = new HashMap<>();
    public static Map<ModWatercraftMaterial, RegistryObject<EntityType<FirmacivSloopEntity>>> getSloops()
    {
        return Collections.unmodifiableMap(_SLOOPS);
    }

    static void init(IEventBus eventBus)
    {
        for(var woodEntry : ModWatercraftMaterial._ALL_WATERCRAFT_MATERIALS)
        {
            if(woodEntry.isSoftwood())
            {
                putCanoeEntity(woodEntry);
            }
            else
            {
                putRowboatEntity(woodEntry);
                putSloopEntity(woodEntry);
                putSloopUnderConstructionEntity(woodEntry);
            }
        }

        if(!ENTITY_TYPES.getEntries().isEmpty())
            ENTITY_TYPES.register(eventBus);
    }

    private static void putCanoeEntity(ModWatercraftMaterial compatWatercraftMaterial)
    {
        String name = "dugout_canoe/" + compatWatercraftMaterial.getSerializedName();
        var builder = EntityType.Builder.of((EntityType<CanoeEntity> entityType, Level level) ->
        {
            return new CanoeEntity(entityType, level, compatWatercraftMaterial);
        }, MobCategory.MISC)
                .sized(1.125f, 0.625f);

        var entry = registerEntity(name, builder);
        _CANOES.put(compatWatercraftMaterial, entry);
    }

    private static void putRowboatEntity(ModWatercraftMaterial compatWatercraftMaterial)
    {
        String name = "rowboat/" + compatWatercraftMaterial.getSerializedName();
        var builder = EntityType.Builder.of((EntityType<FirmacivRowboatEntity> entityType, Level level) ->
        {
            return new FirmacivRowboatEntity(entityType, level, compatWatercraftMaterial);
        }, MobCategory.MISC).sized(1.875f, 0.625f);

        var entry = registerEntity(name, builder);
        _ROWBOATS.put(compatWatercraftMaterial, entry);
    }

    private static void putSloopUnderConstructionEntity(ModWatercraftMaterial compatWatercraftMaterial)
    {
        String name = "sloop_construction/" + compatWatercraftMaterial.getSerializedName();
        var builder = EntityType.Builder.of((EntityType<FirmacivSloopUnderConstructionEntity> entityType, Level level) ->
        {
            return new FirmacivSloopUnderConstructionEntity(entityType, level, compatWatercraftMaterial);
        }, MobCategory.MISC).sized(4f, 0.75f)
                .setTrackingRange(20)
                .fireImmune()
                .noSummon();

        var entry = registerEntity(name, builder);
        _SLOOPS_UNDER_CONSTRUCTION.put(compatWatercraftMaterial, entry);
    }

    private static void putSloopEntity(ModWatercraftMaterial compatWatercraftMaterial)
    {
        String name = "sloop/" + compatWatercraftMaterial.getSerializedName();
        var builder = EntityType.Builder.of((EntityType<FirmacivSloopEntity> entityType, Level level) ->
        {
            return new FirmacivSloopEntity(entityType, level, compatWatercraftMaterial);
        }, MobCategory.MISC).sized(3f, 0.75f)
                .setTrackingRange(20)
                .fireImmune();

        var entry = registerEntity(name, builder);
        _SLOOPS.put(compatWatercraftMaterial, entry);
    }

    private static <E extends Entity> RegistryObject<EntityType<E>> registerEntity(final String name,
                                                                            final EntityType.Builder<E> builder)
    {
        final String id = name.toLowerCase(Locale.ROOT);
        return ENTITY_TYPES.register(id, () ->
        {
            return builder.build(FirmaCivFirmaCompat.MOD_ID + ":" + id);
        });
    }
}
