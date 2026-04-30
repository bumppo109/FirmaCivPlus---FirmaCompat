package com.bumppo109.firmaciv_firmacompat;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public final class CommonSetupHandler
{
    static void onCommonSetup(FMLCommonSetupEvent evt)
    {
        evt.enqueueWork(() ->
        {
           CompatFirmaCivBlockEntities.registerCanoesToFirmaCivRegistry();
            ModWatercraftMaterial.registerFrames();
        });
    }
}
