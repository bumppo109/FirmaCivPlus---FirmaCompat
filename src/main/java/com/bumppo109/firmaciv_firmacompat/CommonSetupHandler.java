package com.bumppo109.firmaciv_firmacompat;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.mojang.text2speech.Narrator.LOGGER;

public final class CommonSetupHandler
{
    static void onCommonSetup(FMLCommonSetupEvent evt)
    {
        LOGGER.info("FirmaCiv FirmaCompat running common setup");
        evt.enqueueWork(() ->
        {
           CompatFirmaCivBlockEntities.registerCanoesToFirmaCivRegistry();
            ModWatercraftMaterial.registerFrames();
        });
    }
}
