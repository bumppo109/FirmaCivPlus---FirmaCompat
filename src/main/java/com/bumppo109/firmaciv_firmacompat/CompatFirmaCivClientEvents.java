package com.bumppo109.firmaciv_firmacompat;

import com.alekiponi.alekiships.AlekiShips;
import com.alekiponi.alekiships.client.resources.PaintedTextureGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;
import java.util.List;

public class CompatFirmaCivClientEvents
{
    static void init(IEventBus evtBus)
    {
        evtBus.addListener(CompatFirmaCivClientEvents::onRegisterReloadListeners);
    }

    private static void onRegisterReloadListeners(final RegisterClientReloadListenersEvent evt)
    {
        final ResourceLocation rowboatPaint = ResourceLocation.fromNamespaceAndPath(AlekiShips.MOD_ID, "entity/watercraft/rowboat/paint");
        final ResourceLocation sloopPaint = ResourceLocation.fromNamespaceAndPath(AlekiShips.MOD_ID, "entity/watercraft/sloop/paint");

        for(final var woodEntry : ModWatercraftMaterial._ALL_WATERCRAFT_MATERIALS)
        {
            if(woodEntry.isSoftwood())
                continue;

            evt.registerReloadListener(new PaintedTextureGenerator(
                    ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID, "entity/watercraft/rowboat/" + woodEntry.getNamespace() + "/" + woodEntry.getSerializedName()), rowboatPaint
            ));

            evt.registerReloadListener(new PaintedTextureGenerator(
                    ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID, "entity/watercraft/sloop/" + woodEntry.getNamespace() + "/" + woodEntry.getSerializedName()), sloopPaint
            ));
        }
    }
}