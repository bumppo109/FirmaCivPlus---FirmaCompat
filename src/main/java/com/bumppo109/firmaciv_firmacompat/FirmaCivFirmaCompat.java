package com.bumppo109.firmaciv_firmacompat;

import com.bumppo109.firmaciv_firmacompat.addon.CompatWatercraftMaterial;
import com.bumppo109.firmaciv_firmacompat.addon.RUWatercraftMaterial;
import com.bumppo109.firmaciv_firmacompat.moonlight.CompatDynamicResourcesHandler;
import com.bumppo109.firmaciv_firmacompat.moonlight.WoodGoodModule;
import com.mojang.logging.LogUtils;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(FirmaCivFirmaCompat.MOD_ID)
public class FirmaCivFirmaCompat {

    public static final String MOD_ID = "firmaciv_firmacompat";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FirmaCivFirmaCompat() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModWatercraftMaterial.addMaterials(CompatWatercraftMaterial.values());
        ModWatercraftMaterial.addMaterials(RUWatercraftMaterial.values());

        CompatFirmaCivBlocks.init(bus);
        CompatFirmaCivItems.init(bus);
        CompatFirmaCivBlockEntities.init(bus);
        CompatFirmaCivEntities.init(bus);
        ModItems.ITEMS.register(bus);

        if(ModList.get().isLoaded("everycompat")){
            WoodGoodModule woodModule = new WoodGoodModule();
            EveryCompatAPI.registerModule(woodModule);
        }

        if (FMLEnvironment.dist == Dist.CLIENT) {
            CompatDynamicResourcesHandler.INSTANCE.register();

            CompatFirmaCivClientEvents.init(bus);
            CompatFirmaCivRenderEventHandler.init(bus);
        }

        bus.addListener(CommonSetupHandler::onCommonSetup);
    }
}