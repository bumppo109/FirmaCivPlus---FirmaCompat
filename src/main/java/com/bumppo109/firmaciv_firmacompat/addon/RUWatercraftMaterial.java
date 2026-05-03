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

public enum RUWatercraftMaterial implements ModWatercraftMaterial
{
    //softwoods, makes canoes
    CYPRESS(true, RuBlocks.CYPRESS_PLANKS.get(), RuBlocks.STRIPPED_CYPRESS_LOG.get()),
    LARCH(true, RuBlocks.LARCH_PLANKS.get(), RuBlocks.STRIPPED_LARCH_LOG.get()),
    PINE(true, RuBlocks.PINE_PLANKS.get(), RuBlocks.STRIPPED_PINE_LOG.get()),
    SOCOTRA(true, RuBlocks.SOCOTRA_PLANKS.get(), RuBlocks.STRIPPED_SOCOTRA_LOG.get()),
    JOSHUA(true, RuBlocks.JOSHUA_PLANKS.get(), RuBlocks.STRIPPED_JOSHUA_LOG.get()),

    REDWOOD(false, RuBlocks.REDWOOD_PLANKS.get(), RuBlocks.STRIPPED_REDWOOD_LOG.get()),
    BAOBAB(false, RuBlocks.BAOBAB_PLANKS.get(), RuBlocks.STRIPPED_BAOBAB_LOG.get()),
    BLACKWOOD(false, RuBlocks.BLACKWOOD_PLANKS.get(), RuBlocks.STRIPPED_BLACKWOOD_LOG.get()),
    EUCALYPTUS(false, RuBlocks.EUCALYPTUS_PLANKS.get(), RuBlocks.STRIPPED_EUCALYPTUS_LOG.get()),
    KAPOK(false, RuBlocks.KAPOK_PLANKS.get(), RuBlocks.STRIPPED_KAPOK_LOG.get()),
    MAGNOLIA(false, RuBlocks.MAGNOLIA_PLANKS.get(), RuBlocks.STRIPPED_MAGNOLIA_LOG.get()),
    MAPLE(false, RuBlocks.MAPLE_PLANKS.get(), RuBlocks.STRIPPED_MAPLE_LOG.get()),
    MAUVE(false, RuBlocks.MAUVE_PLANKS.get(), RuBlocks.STRIPPED_MAUVE_LOG.get()),
    PALM(false, RuBlocks.PALM_PLANKS.get(), RuBlocks.STRIPPED_PALM_LOG.get()),
    WILLOW(false, RuBlocks.WILLOW_PLANKS.get(), RuBlocks.STRIPPED_WILLOW_LOG.get()),
    BRIMWOOD(false, RuBlocks.BRIMWOOD_PLANKS.get(), RuBlocks.STRIPPED_BRIMWOOD_LOG.get()),
    COBALT(false, RuBlocks.COBALT_PLANKS.get(), RuBlocks.STRIPPED_COBALT_LOG.get()),

    GREEN_BIOSHROOM(false, RuBlocks.GREEN_BIOSHROOM_PLANKS.get(), RuBlocks.STRIPPED_GREEN_BIOSHROOM_STEM.get()),
    BLUE_BIOSHROOM(false, RuBlocks.BLUE_BIOSHROOM_PLANKS.get(), RuBlocks.STRIPPED_BLUE_BIOSHROOM_STEM.get()),
    YELLOW_BIOSHROOM(false, RuBlocks.YELLOW_BIOSHROOM_PLANKS.get(), RuBlocks.STRIPPED_YELLOW_BIOSHROOM_STEM.get()),
    PINK_BIOSHROOM(false, RuBlocks.PINK_BIOSHROOM_PLANKS.get(), RuBlocks.STRIPPED_PINK_BIOSHROOM_STEM.get())
    ;

    public final boolean isSoftwood;
    private final Block planks;
    private final Block strippedLog;

    final String woodNamespace = "regions_unexplored";


    RUWatercraftMaterial(boolean isSoftwood, Block planks, Block strippedLog) {
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
                new ResourceLocation("firma_compat", woodName + "_lumber"),
                new ResourceLocation("firma_compat", "firma_compat/" + woodNamespace + "/" + woodName + "_lumber")
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
        return strippedLog.asItem();
    }

    @Override
    public Block getPlanks()
    {
        return planks;
    }

    @Override
    public Block getStrippedLogBlock()
    {
        return strippedLog;
    }

    @Override
    public ResourceLocation getPlanksTexture()
    {
        return new ResourceLocation(woodNamespace, "block/" + this.getSerializedName() + "_planks");
    }

    @Override
    public ResourceLocation getStrippedLogTexture()
    {
        if(this.equals(BLUE_BIOSHROOM) || this.equals(YELLOW_BIOSHROOM) || this.equals(PINK_BIOSHROOM) || this.equals(GREEN_BIOSHROOM)){
            return new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_stem");
        } else {
            return new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_log");
        }
    }

    @Override
    public ResourceLocation getStrippedLogTopTexture()
    {
        if(this.equals(BLUE_BIOSHROOM) || this.equals(YELLOW_BIOSHROOM) || this.equals(PINK_BIOSHROOM) || this.equals(GREEN_BIOSHROOM)){
            return new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_stem_top");
        } else {
            return new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_log_top");
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
        return planks.defaultBlockState();
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
