package com.bumppo109.firmaciv_firmacompat;

import com.alekiponi.alekiships.AlekiShips;
import com.alekiponi.alekiships.client.render.entity.vehicle.RowboatRenderer;
import com.alekiponi.alekiships.client.render.entity.vehicle.SloopConstructionRenderer;
import com.alekiponi.alekiships.client.render.entity.vehicle.SloopRenderer;
import com.alekiponi.alekiships.util.CommonHelper;
import com.alekiponi.firmaciv.client.render.entity.vehicle.CanoeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import static com.mojang.text2speech.Narrator.LOGGER;

public final class CompatFirmaCivRenderEventHandler
{
    static void init(IEventBus evtBus)
    {
        LOGGER.info("FirmaCiv FirmaCompat adding adding renderers");
        evtBus.addListener(CompatFirmaCivRenderEventHandler::registerRenderers);
    }

    private static void registerRenderers(final EntityRenderersEvent.RegisterRenderers evt)
    {
        for(final ModWatercraftMaterial woodEntry : ModWatercraftMaterial._ALL_WATERCRAFT_MATERIALS)
        {
            if(woodEntry.isSoftwood())
            {
                // Canoes
                evt.registerEntityRenderer(CompatFirmaCivEntities.getCanoes().get(woodEntry).get(),
                        context -> new CanoeRenderer(context, ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID,
                                "textures/entity/watercraft/dugout_canoe/" + woodEntry.getNamespace() + "/" + woodEntry.getSerializedName() + "/" + woodEntry.getSerializedName() + ".png")));
            }
            else
            {
                // Rowboat
                evt.registerEntityRenderer(CompatFirmaCivEntities.getRowboats().get(woodEntry).get(),
                        context -> new RowboatRenderer(context, ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID,
                                "textures/entity/watercraft/rowboat/" + woodEntry.getNamespace() + "/" + woodEntry.getSerializedName() + "/" + woodEntry.getSerializedName() + ".png"),
                                CommonHelper.mapOfKeys(DyeColor.class, dyeColor -> ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID,
                                        "textures/entity/watercraft/rowboat/" + woodEntry.getNamespace() + "/" + woodEntry.getSerializedName() + "/" + dyeColor.getSerializedName() + ".png"))));
                // Sloops
                evt.registerEntityRenderer(CompatFirmaCivEntities.getSloops().get(woodEntry).get(),
                        context -> new SloopRenderer(context, ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID,
                                "textures/entity/watercraft/sloop/" + woodEntry.getNamespace() + "/" + woodEntry.getSerializedName() + "/" + woodEntry.getSerializedName() + ".png"),
                                CommonHelper.mapOfKeys(DyeColor.class, dyeColor -> ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID,
                                        "textures/entity/watercraft/sloop/" + woodEntry.getNamespace() + "/" + woodEntry.getSerializedName() + "/" + dyeColor.getSerializedName() + ".png"))));
                // Construction sloops
                evt.registerEntityRenderer(CompatFirmaCivEntities.getSloopsUnderConstruction().get(woodEntry).get(),
                        context -> new SloopConstructionRenderer(context, ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID,
                                "textures/entity/watercraft/sloop_construction/" + woodEntry.getNamespace() + "/" + woodEntry.getSerializedName() + "/" + woodEntry.getSerializedName() + ".png")));
            }
        }
    }
}
