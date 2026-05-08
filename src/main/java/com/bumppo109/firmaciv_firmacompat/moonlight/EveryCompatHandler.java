package com.bumppo109.firmaciv_firmacompat.moonlight;

import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.minecraftforge.fml.ModList;

public class EveryCompatHandler {

    private EveryCompatHandler() {} // no instances

    public static void registerModules() {
        if(ModList.get().isLoaded("everycomp")){
            if(ModList.get().isLoaded("alekiroofs")) {
                WoodGoodModule woodModule = new WoodGoodModule();
                EveryCompatAPI.registerModule(woodModule);
            }
        }
    }
}

