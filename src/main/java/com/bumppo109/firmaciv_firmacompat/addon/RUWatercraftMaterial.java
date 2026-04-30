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

import java.util.Locale;
import java.util.Optional;

public enum RUWatercraftMaterial implements ModWatercraftMaterial {
    CYPRESS(true),
    LARCH(true),
    PINE(true),
    REDWOOD(true),
    SOCOTRA(true),
    JOSHUA(true),

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

    GREEN_BIOSHROOM(false),
    BLUE_BIOSHROOM(false),
    PINK_BIOSHROOM(false),
    YELLOW_BIOSHROOM(false)
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
        return planks();
    }

    @Override
    public Block getStrippedLogBlock()
    {
        return strippedLog();
    }

    @Override
    public Block getStairs()
    {
        return stair();
    }

    @Override
    public ResourceLocation getPlanksTexture()
    {
        return planksTexture();
    }

    @Override
    public ResourceLocation getStrippedLogTexture()
    {
        return strippedLogTexture();
    }

    @Override
    public ResourceLocation getStrippedLogTopTexture()
    {
        return strippedLogTopTexture();
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

    public Block strippedLog() {
        Block block;
        if(this.equals(BLUE_BIOSHROOM) || this.equals(YELLOW_BIOSHROOM) || this.equals(GREEN_BIOSHROOM) || this.equals(PINK_BIOSHROOM)){
            block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(woodNamespace, "stripped_" + this.getSerializedName() + "_hyphae"));
        } else {
            block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(woodNamespace, "stripped_" + this.getSerializedName() + "_log"));
        }
        return block != null ? block : Blocks.AIR;
    }

    public Block stair() {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(woodNamespace, this.getSerializedName() + "_stairs"));

        return block != null ? block : Blocks.AIR;
    }

    public ResourceLocation planksTexture() {
        ResourceLocation block = ForgeRegistries.BLOCKS.getKey(this.getPlanks());

        assert block != null;
        return new ResourceLocation(woodNamespace, "block/" + block.getPath());
    }

    public ResourceLocation strippedLogTexture() {
        ResourceLocation block;

        if(this.equals(BLUE_BIOSHROOM) || this.equals(YELLOW_BIOSHROOM) || this.equals(GREEN_BIOSHROOM) || this.equals(PINK_BIOSHROOM)){
            block = new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_hyphae");
        } else {
            block = new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_log");
        }

        return block;
    }

    public ResourceLocation strippedLogTopTexture() {
        ResourceLocation block;

        if(this.equals(BLUE_BIOSHROOM) || this.equals(YELLOW_BIOSHROOM) || this.equals(GREEN_BIOSHROOM) || this.equals(PINK_BIOSHROOM)){
            block = new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_hyphae_top");
        } else {
            block = new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_log_top");
        }

        return block;
    }
}
