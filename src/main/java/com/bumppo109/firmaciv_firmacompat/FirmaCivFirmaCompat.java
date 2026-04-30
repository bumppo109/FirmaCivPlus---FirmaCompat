package com.bumppo109.firmaciv_firmacompat;

import com.bumppo109.firmaciv_firmacompat.addon.CompatWatercraftMaterial;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FirmaCivFirmaCompat.MOD_ID)
public class FirmaCivFirmaCompat
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "firmaciv_firmacompat";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public FirmaCivFirmaCompat()
    {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModWatercraftMaterial.addMaterials(CompatWatercraftMaterial.values());

        CompatFirmaCivBlocks.init(bus);
        CompatFirmaCivItems.init(bus);
        CompatFirmaCivBlockEntities.init(bus);
        CompatFirmaCivEntities.init(bus);

        if(FMLEnvironment.dist == Dist.CLIENT)
        {
            CompatFirmaCivClientEvents.init(bus);
            CompatFirmaCivRenderEventHandler.init(bus);
        }

        bus.addListener(CommonSetupHandler::onCommonSetup);
    }
}
