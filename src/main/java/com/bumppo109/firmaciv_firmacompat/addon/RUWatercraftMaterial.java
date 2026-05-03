package com.bumppo109.firmaciv_firmacompat.addon;

import com.alekiponi.alekiships.common.entity.vehicle.AbstractVehicle;
import com.alekiponi.firmaciv.common.entity.vehicle.CanoeEntity;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivEntities;
import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.regions_unexplored.block.RuBlocks;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public enum RUWatercraftMaterial implements ModWatercraftMaterial
{
    //softwoods, makes canoes
    CYPRESS(true, RuBlocks.CYPRESS_PLANKS, RuBlocks.STRIPPED_CYPRESS_LOG),
    LARCH(true, RuBlocks.LARCH_PLANKS, RuBlocks.STRIPPED_LARCH_LOG),
    PINE(true, RuBlocks.PINE_PLANKS, RuBlocks.STRIPPED_PINE_LOG),
    SOCOTRA(true, RuBlocks.SOCOTRA_PLANKS, RuBlocks.STRIPPED_SOCOTRA_LOG),
    JOSHUA(true, RuBlocks.JOSHUA_PLANKS, RuBlocks.STRIPPED_JOSHUA_LOG),

    REDWOOD(false, RuBlocks.REDWOOD_PLANKS, RuBlocks.STRIPPED_REDWOOD_LOG),
    BAOBAB(false, RuBlocks.BAOBAB_PLANKS, RuBlocks.STRIPPED_BAOBAB_LOG),
    BLACKWOOD(false, RuBlocks.BLACKWOOD_PLANKS, RuBlocks.STRIPPED_BLACKWOOD_LOG),
    EUCALYPTUS(false, RuBlocks.EUCALYPTUS_PLANKS, RuBlocks.STRIPPED_EUCALYPTUS_LOG),
    KAPOK(false, RuBlocks.KAPOK_PLANKS, RuBlocks.STRIPPED_KAPOK_LOG),
    MAGNOLIA(false, RuBlocks.MAGNOLIA_PLANKS, RuBlocks.STRIPPED_MAGNOLIA_LOG),
    MAPLE(false, RuBlocks.MAPLE_PLANKS, RuBlocks.STRIPPED_MAPLE_LOG),
    MAUVE(false, RuBlocks.MAUVE_PLANKS, RuBlocks.STRIPPED_MAUVE_LOG),
    PALM(false, RuBlocks.PALM_PLANKS, RuBlocks.STRIPPED_PALM_LOG),
    WILLOW(false, RuBlocks.WILLOW_PLANKS, RuBlocks.STRIPPED_WILLOW_LOG),
    BRIMWOOD(false, RuBlocks.BRIMWOOD_PLANKS, RuBlocks.STRIPPED_BRIMWOOD_LOG),
    COBALT(false, RuBlocks.COBALT_PLANKS, RuBlocks.STRIPPED_COBALT_LOG),

    GREEN_BIOSHROOM(false, RuBlocks.GREEN_BIOSHROOM_PLANKS, RuBlocks.STRIPPED_GREEN_BIOSHROOM_STEM),
    BLUE_BIOSHROOM(false, RuBlocks.BLUE_BIOSHROOM_PLANKS, RuBlocks.STRIPPED_BLUE_BIOSHROOM_STEM),
    YELLOW_BIOSHROOM(false, RuBlocks.YELLOW_BIOSHROOM_PLANKS, RuBlocks.STRIPPED_YELLOW_BIOSHROOM_STEM),
    PINK_BIOSHROOM(false, RuBlocks.PINK_BIOSHROOM_PLANKS, RuBlocks.STRIPPED_PINK_BIOSHROOM_STEM)
    ;

    public final boolean isSoftwood;
    private final Supplier<Block> planks;
    private final Supplier<Block> strippedLog;

    final String woodNamespace = "regions_unexplored";


    RUWatercraftMaterial(boolean isSoftwood, Supplier<Block> planks, Supplier<Block> strippedLog) {
        this.isSoftwood = isSoftwood;
        this.planks = planks;
        this.strippedLog = strippedLog;
    }

    private Item railingItem = null;

    @Override
    public Item getRailing()
    {
        if (railingItem != null) return railingItem;

        String woodName = this.getSerializedName();

        // Common locations for lumber in TFC ecosystem
        ResourceLocation[] possibleLocations = {
                ResourceLocation.fromNamespaceAndPath("firma_compat", woodName + "_lumber"),
                ResourceLocation.fromNamespaceAndPath("firma_compat", "firma_compat/" + woodNamespace + "/" + woodName + "_lumber")
        };

        for (ResourceLocation loc : possibleLocations)
        {
            Item item = ForgeRegistries.ITEMS.getValue(loc);
            if (item != null && item != Items.AIR)
            {
                railingItem = item;
                return item;
            }
        }

        // Final fallback
        railingItem = Items.AIR;
        return Items.AIR;
    }

    @Override
    public String getNamespace()
    {
        return woodNamespace;
    }

    @Override
    public Item getStrippedLog()
    {
        return strippedLog.get().asItem();
    }

    @Override
    public Block getPlanks()
    {
        return planks.get();
    }

    @Override
    public Block getStrippedLogBlock()
    {
        return strippedLog.get();
    }

    @Override
    public ResourceLocation getPlanksTexture()
    {
        return ResourceLocation.fromNamespaceAndPath(woodNamespace, "block/" + this.getSerializedName() + "_planks");
    }

    @Override
    public ResourceLocation getStrippedLogTexture()
    {
        if(this.equals(BLUE_BIOSHROOM) || this.equals(YELLOW_BIOSHROOM) || this.equals(PINK_BIOSHROOM) || this.equals(GREEN_BIOSHROOM)){
            return ResourceLocation.fromNamespaceAndPath(woodNamespace, "block/stripped_" + this.getSerializedName() + "_stem");
        } else {
            return ResourceLocation.fromNamespaceAndPath(woodNamespace, "block/stripped_" + this.getSerializedName() + "_log");
        }
    }

    @Override
    public ResourceLocation getStrippedLogTopTexture()
    {
        if(this.equals(BLUE_BIOSHROOM) || this.equals(YELLOW_BIOSHROOM) || this.equals(PINK_BIOSHROOM) || this.equals(GREEN_BIOSHROOM)){
            return ResourceLocation.fromNamespaceAndPath(woodNamespace, "block/stripped_" + this.getSerializedName() + "_stem_top");
        } else {
            return ResourceLocation.fromNamespaceAndPath(woodNamespace, "block/stripped_" + this.getSerializedName() + "_log_top");
        }
    }

    @Override
    public boolean withstandsLava()
    {
        return false;
    }

    @Override
    public BlockState getDeckBlock()
    {
        return planks.get().defaultBlockState();
    }

    @Override
    public Optional<EntityType<? extends AbstractVehicle>> getEntityType(BoatType boatType)
    {
        return switch (boatType) {
            case ROWBOAT -> Optional.of(CompatFirmaCivEntities.getRowboats().get(this).get());
            case SLOOP -> Optional.of(CompatFirmaCivEntities.getSloops().get(this).get());
            case CONSTRUCTION_SLOOP -> Optional.of(CompatFirmaCivEntities.getSloopsUnderConstruction().get(this).get());
        };
    }

    @Override
    public Optional<EntityType<? extends CanoeEntity>> getCanoeType()
    {
        return Optional.of(CompatFirmaCivEntities.getCanoes().get(this).get());
    }

    @Override
    public String getSerializedName()
    {
        return this.name().toLowerCase(Locale.ROOT);
    }

    @Override
    public boolean isSoftwood()
    {
        return isSoftwood;
    }
}
