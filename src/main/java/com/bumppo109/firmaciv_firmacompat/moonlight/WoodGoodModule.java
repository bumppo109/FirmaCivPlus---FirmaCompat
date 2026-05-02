package com.bumppo109.firmaciv_firmacompat.moonlight;

import com.alekiponi.alekiroofs.SquaredAngleBlock;
import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.wood.TFCFenceBlock;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;

import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public final class WoodGoodModule extends EveryCompatModule {
    public final SimpleEntrySet<WoodType, Block> ROOFING;

    public WoodGoodModule() {
        super(FirmaCivFirmaCompat.MOD_ID, FirmaCivFirmaCompat.MOD_ID, FirmaCivFirmaCompat.MOD_ID);

        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        ROOFING = SimpleEntrySet.builder(WoodType.class, "roofing",
                        getModBlock("oak_roofing"), () -> VanillaWoodTypes.OAK,
                        w -> new
                                SquaredAngleBlock(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("planks")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .dropSelf()
                .setTabKey(tab)
                .excludeBlockTypes("tfc:.*").excludeBlockTypes("afc:.*").excludeBlockTypes("domum_ornamentum:.*")
                .build();
        this.addEntry(ROOFING);

    }

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
        });
    }
}

