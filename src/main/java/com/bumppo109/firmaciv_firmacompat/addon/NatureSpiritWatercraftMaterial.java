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

public enum NatureSpiritWatercraftMaterial implements ModWatercraftMaterial {

    FIR(true),
    CYPRESS(true),
    COCONUT(true),
    CEDAR(true),
    SUGI(true),
    JOSHUA(true),
    LARCH(true),

    MAHOGANY(false),
    SAXAUL(false),
    REDWOOD(false),
    ASPEN(false),
    WISTERIA(false),
    WILLOW(false),
    PALO_VERDE(false),
    OLIVE(false),
    GHAF(false),
    MAPLE(false)
    ;

    public final boolean isSoftwood;

    NatureSpiritWatercraftMaterial(boolean isSoftwood) { this.isSoftwood = isSoftwood; }

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
    final String woodNamespace = "natures_spirit";

    public Block planks() {
        Block block = ForgeRegistries.BLOCKS.getValue(
                new ResourceLocation(woodNamespace, this.getSerializedName() + "_planks")
        );

        return block != null ? block : Blocks.AIR;
    }

    public Block strippedLog() {
        Block block;

        block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(woodNamespace, "stripped_" + this.getSerializedName() + "_log"));

        return block != null ? block : Blocks.AIR;
    }
}
