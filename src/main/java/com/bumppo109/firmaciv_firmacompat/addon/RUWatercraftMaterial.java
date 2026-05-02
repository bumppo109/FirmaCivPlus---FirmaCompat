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

public enum RUWatercraftMaterial implements ModWatercraftMaterial {
    CYPRESS(true),
    LARCH(true),
    PINE(true),
    SOCOTRA(true),
    JOSHUA(true),

    REDWOOD(false),
    BAOBAB(false),
    BLACKWOOD(false),
    EUCALYPTUS(false),
    KAPOK(false),
    MAGNOLIA(false),
    MAPLE(false),
    MAUVE(false),
    PALM(false),
    WILLOW(false),
    BRIMWOOD(false),
    COBALT(false),

    GREEN_BIOSHROOM(true),
    BLUE_BIOSHROOM(true),
    PINK_BIOSHROOM(true),
    YELLOW_BIOSHROOM(true)
    ;

    public final boolean isSoftwood;

    RUWatercraftMaterial(boolean isSoftwood) { this.isSoftwood = isSoftwood; }

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
        return this.strippedLog().asItem();
    }

    @Override
    public Block getPlanks()
    {
        return this.planks();
    }

    @Override
    public Block getStrippedLogBlock()
    {
        return this.strippedLog();
    }

    @Override
    public ResourceLocation getPlanksTexture()
    {
        ResourceLocation resLoc = ForgeRegistries.BLOCKS.getKey(planks());
        assert resLoc != null;
        return new ResourceLocation(resLoc.getNamespace(), "block/" + resLoc.getPath());
    }

    @Override
    public ResourceLocation getStrippedLogTexture()
    {
        ResourceLocation resLoc = ForgeRegistries.BLOCKS.getKey(strippedLog());
        assert resLoc != null;
        return new ResourceLocation(resLoc.getNamespace(), "block/" + resLoc.getPath());
    }

    @Override
    public ResourceLocation getStrippedLogTopTexture()
    {
        ResourceLocation resLoc = ForgeRegistries.BLOCKS.getKey(strippedLog());
        assert resLoc != null;
        return new ResourceLocation(resLoc.getNamespace(), "block/" + resLoc.getPath() + "_top");
    }

    @Override
    public boolean withstandsLava()
    {
        return false;
    }

    @Override
    public BlockState getDeckBlock()
    {
        return this.planks().defaultBlockState();
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

    // ============= Add References
    final String woodNamespace = "regions_unexplored";

    public Block planks() {
        Block block = ForgeRegistries.BLOCKS.getValue(
                new ResourceLocation(woodNamespace, this.getSerializedName() + "_planks")
        );

        return block != null ? block : Blocks.AIR;
    }

    /*
    public Block strippedLog() {
        return switch (this) {  // 'this' is the current enum instance
            case CYPRESS   -> RuBlocks.STRIPPED_CYPRESS_LOG.get();
            case LARCH-> RuBlocks.STRIPPED_LARCH_LOG.get();
            case PINE-> RuBlocks.STRIPPED_PINE_LOG.get();
            case SOCOTRA-> RuBlocks.STRIPPED_SOCOTRA_LOG.get();
            case JOSHUA-> RuBlocks.STRIPPED_JOSHUA_LOG.get();

            case REDWOOD-> RuBlocks.STRIPPED_REDWOOD_LOG.get();
            case BAOBAB-> RuBlocks.STRIPPED_BAOBAB_LOG.get();
            case BLACKWOOD-> RuBlocks.STRIPPED_BLACKWOOD_LOG.get();
            case EUCALYPTUS-> RuBlocks.STRIPPED_EUCALYPTUS_LOG.get();
            case KAPOK-> RuBlocks.STRIPPED_KAPOK_LOG.get();
            case MAGNOLIA-> RuBlocks.STRIPPED_MAGNOLIA_LOG.get();
            case MAPLE-> RuBlocks.STRIPPED_MAPLE_LOG.get();
            case MAUVE-> RuBlocks.STRIPPED_MAUVE_LOG.get();
            case PALM-> RuBlocks.STRIPPED_PALM_LOG.get();
            case WILLOW-> RuBlocks.STRIPPED_WILLOW_LOG.get();
            case BRIMWOOD-> RuBlocks.STRIPPED_BRIMWOOD_LOG.get();
            case COBALT-> RuBlocks.STRIPPED_COBALT_LOG.get();

            case GREEN_BIOSHROOM -> RuBlocks.STRIPPED_GREEN_BIOSHROOM_HYPHAE.get();
            case BLUE_BIOSHROOM -> RuBlocks.STRIPPED_BLUE_BIOSHROOM_HYPHAE.get();
            case PINK_BIOSHROOM -> RuBlocks.STRIPPED_PINK_BIOSHROOM_HYPHAE.get();
            case YELLOW_BIOSHROOM -> RuBlocks.STRIPPED_YELLOW_BIOSHROOM_HYPHAE.get();
        };
    }

     */
    public Block strippedLog() {
        Block block;
        if(this.equals(BLUE_BIOSHROOM) || this.equals(YELLOW_BIOSHROOM) || this.equals(GREEN_BIOSHROOM) || this.equals(PINK_BIOSHROOM)){
            block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(woodNamespace, "stripped_" + this.getSerializedName() + "_hyphae"));
        } else {
            block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(woodNamespace, "stripped_" + this.getSerializedName() + "_log"));
        }
        return block != null ? block : Blocks.AIR;
    }
}
