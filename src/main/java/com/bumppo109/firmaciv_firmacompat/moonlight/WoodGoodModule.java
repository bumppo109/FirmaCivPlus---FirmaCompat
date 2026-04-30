package com.bumppo109.firmaciv_firmacompat.moonlight;

import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;

import java.util.function.Consumer;

public final class WoodGoodModule extends EveryCompatModule {
    public final ItemOnlyEntrySet<WoodType, Item> LUMBER;

    public WoodGoodModule() {
        super(FirmaCivFirmaCompat.MOD_ID, FirmaCivFirmaCompat.MOD_ID, FirmaCivFirmaCompat.MOD_ID);

        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        LUMBER = ItemOnlyEntrySet.builder(WoodType.class, "lumber",
                        getModItem("lumber"), () -> VanillaWoodTypes.OAK,
                        w -> new Item(new Item.Properties())
                )
                .addTexture(modRes("entity/template/watercraft/dugout_canoe"), PaletteStrategies.MAIN_CHILD)
                .addTexture(modRes("entity/template/watercraft/rowboat"), PaletteStrategies.MAIN_CHILD)
                .addTexture(modRes("entity/template/watercraft/sloop"), PaletteStrategies.MAIN_CHILD)
                .addTexture(modRes("entity/template/watercraft/sloop_construction"), PaletteStrategies.MAIN_CHILD)
                .setTabKey(tab)
                .excludeBlockTypes("tfc:.*")
                .excludeBlockTypes("afc:.*")
                .build();
        this.addEntry(LUMBER);
    }

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
        });
    }
}

