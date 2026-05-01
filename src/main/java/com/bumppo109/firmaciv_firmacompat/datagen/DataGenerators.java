package com.bumppo109.firmaciv_firmacompat.datagen;

import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = FirmaCivFirmaCompat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent evt) {
        DataGenerator generator = evt.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = evt.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = evt.getLookupProvider();

        if (evt.includeClient()) {
            generator.addProvider(true, new FirmaCivPlusBlockStateProvider(output, helper));
            generator.addProvider(true, new FirmaCivPlus_en_us_LanguageProvider(output));
        }

        if (evt.includeServer()) {
            var blockTags = generator.addProvider(true,
                    new FirmaCivPlusBlockTagGenerator(output, lookup, helper));

            generator.addProvider(true,
                    new FirmaCivPlusItemTagGenerator(output, lookup, blockTags.contentsGetter(), helper));

            generator.addProvider(true,
                    new FirmaCivPlusEntityTypeTagsGenerator(output, lookup, helper));

            generator.addProvider(true,
                    FirmaCivPlusLootTableProvider.create(output));

            generator.addProvider(true,
                    new FirmaCivPlusRecipeProvider(output));
        }
    }
}