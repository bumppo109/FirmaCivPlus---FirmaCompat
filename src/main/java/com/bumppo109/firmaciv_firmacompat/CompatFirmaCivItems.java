package com.bumppo109.firmaciv_firmacompat;

import com.alekiponi.firmaciv.common.item.FirmacivTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.mojang.text2speech.Narrator.LOGGER;

public final class CompatFirmaCivItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirmaCivFirmaCompat.MOD_ID);

    public static void init(IEventBus bus) {
        LOGGER.info("FirmaCiv FirmaCompat adding Items");
        ITEMS.register(bus);

        bus.addListener(CompatFirmaCivItems::BuildCreativeModeTabContentsEvent);
    }

    private static void BuildCreativeModeTabContentsEvent(BuildCreativeModeTabContentsEvent evt)
    {
        if(evt.getTabKey() == FirmacivTabs.FIRMACIV_TAB.getKey())
        {
            for(var item : ITEMS.getEntries())
            {
                evt.accept(item);
            }
        }
    }
}
