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
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public enum CompatWatercraftMaterial implements ModWatercraftMaterial
{
    //softwoods, makes canoes
    BIRCH(true, () -> Blocks.BIRCH_PLANKS, () -> Blocks.STRIPPED_BIRCH_LOG),
    JUNGLE(true, () -> Blocks.JUNGLE_PLANKS, () -> Blocks.STRIPPED_JUNGLE_LOG),
    SPRUCE(true, () -> Blocks.SPRUCE_PLANKS, () -> Blocks.STRIPPED_SPRUCE_LOG),

    ACACIA(false, () -> Blocks.ACACIA_PLANKS, () -> Blocks.STRIPPED_ACACIA_LOG),
    CHERRY(false, () -> Blocks.CHERRY_PLANKS, () -> Blocks.STRIPPED_CHERRY_LOG),
    DARK_OAK(false, () -> Blocks.DARK_OAK_PLANKS, () -> Blocks.STRIPPED_DARK_OAK_LOG),
    MANGROVE(false, () -> Blocks.MANGROVE_PLANKS, () -> Blocks.STRIPPED_MANGROVE_LOG),
    OAK(false, () -> Blocks.OAK_PLANKS, () -> Blocks.STRIPPED_OAK_LOG),

    CRIMSON(false, () -> Blocks.CRIMSON_PLANKS, () -> Blocks.STRIPPED_CRIMSON_STEM),
    WARPED(false, () -> Blocks.WARPED_PLANKS, () -> Blocks.STRIPPED_WARPED_STEM),
    ;

    public final boolean isSoftwood;
    private final Supplier<Block> planks;
    private final Supplier<Block> strippedLog;

    final String woodNamespace = "minecraft";


    CompatWatercraftMaterial(boolean isSoftwood, Supplier<Block> planks, Supplier<Block> strippedLog) {
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
        if(this.equals(CRIMSON) || this.equals(WARPED)){
            return ResourceLocation.fromNamespaceAndPath(woodNamespace, "block/stripped_" + this.getSerializedName() + "_stem");
        } else {
            return ResourceLocation.fromNamespaceAndPath(woodNamespace, "block/stripped_" + this.getSerializedName() + "_log");
        }
    }

    @Override
    public ResourceLocation getStrippedLogTopTexture()
    {
        if(this.equals(CRIMSON) || this.equals(WARPED)){
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
