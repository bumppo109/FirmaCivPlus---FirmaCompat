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
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

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
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(getPlanksTexture())).asItem();
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
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(getPlanksTexture())).defaultBlockState();
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

    public ResourceLocation planksTexture() {
        return switch (this) {
            case CYPRESS -> new ResourceLocation(woodNamespace, "cypress_planks");
            case LARCH -> new ResourceLocation(woodNamespace, "larch_planks");
            case PINE -> new ResourceLocation(woodNamespace, "pine_planks");
            case REDWOOD -> new ResourceLocation(woodNamespace, "redwood_planks");
            case SOCOTRA -> new ResourceLocation(woodNamespace, "socotra_planks");
            case JOSHUA -> new ResourceLocation(woodNamespace, "joshua_planks");
            case BAOBAB -> new ResourceLocation(woodNamespace, "baobab_planks");
            case MAGNOLIA -> new ResourceLocation(woodNamespace, "magnolia_planks");
            case BLACKWOOD -> new ResourceLocation(woodNamespace, "blackwood_planks");
            case EUCALYPTUS -> new ResourceLocation(woodNamespace, "eucalyptus_planks");
            case KAPOK -> new ResourceLocation(woodNamespace, "kapok_planks");
            case MAPLE -> new ResourceLocation(woodNamespace, "maple_planks");
            case MAUVE -> new ResourceLocation(woodNamespace, "mauve_planks");
            case PALM -> new ResourceLocation(woodNamespace, "palm_planks");
            case WILLOW -> new ResourceLocation(woodNamespace, "willow_planks");
            case BLUE_BIOSHROOM -> new ResourceLocation(woodNamespace, "blue_bioshroom_planks");
            case YELLOW_BIOSHROOM -> new ResourceLocation(woodNamespace, "yellow_bioshroom_planks");
            case GREEN_BIOSHROOM -> new ResourceLocation(woodNamespace, "green_bioshroom_planks");
            case PINK_BIOSHROOM -> new ResourceLocation(woodNamespace, "pink_bioshroom_planks");
            case COBALT -> new ResourceLocation(woodNamespace, "cobalt_planks");
            case BRIMWOOD -> new ResourceLocation(woodNamespace, "brimwood_planks");
        };
    }

    public ResourceLocation strippedLogTexture() {
        return switch (this) {
            case CYPRESS -> new ResourceLocation(woodNamespace, "stripped_cypress_log");
            case LARCH -> new ResourceLocation(woodNamespace, "stripped_larch_log");
            case PINE -> new ResourceLocation(woodNamespace, "stripped_pine_log");
            case REDWOOD -> new ResourceLocation(woodNamespace, "stripped_redwood_log");
            case SOCOTRA -> new ResourceLocation(woodNamespace, "stripped_socotra_log");
            case JOSHUA -> new ResourceLocation(woodNamespace, "stripped_joshua_log");
            case BAOBAB -> new ResourceLocation(woodNamespace, "stripped_baobab_log");
            case MAGNOLIA -> new ResourceLocation(woodNamespace, "stripped_magnolia_log");
            case BLACKWOOD -> new ResourceLocation(woodNamespace, "stripped_blackwood_log");
            case EUCALYPTUS -> new ResourceLocation(woodNamespace, "stripped_eucalyptus_log");
            case KAPOK -> new ResourceLocation(woodNamespace, "stripped_kapok_log");
            case MAPLE -> new ResourceLocation(woodNamespace, "stripped_maple_log");
            case MAUVE -> new ResourceLocation(woodNamespace, "stripped_mauve_log");
            case PALM -> new ResourceLocation(woodNamespace, "stripped_palm_log");
            case WILLOW -> new ResourceLocation(woodNamespace, "stripped_willow_log");
            case BLUE_BIOSHROOM -> new ResourceLocation(woodNamespace, "stripped_blue_bioshroom_hyphae");
            case YELLOW_BIOSHROOM -> new ResourceLocation(woodNamespace, "stripped_yellow_bioshroom_hyphae");
            case GREEN_BIOSHROOM -> new ResourceLocation(woodNamespace, "stripped_green_bioshroom_hyphae");
            case PINK_BIOSHROOM -> new ResourceLocation(woodNamespace, "stripped_pink_bioshroom_hyphae");
            case COBALT -> new ResourceLocation(woodNamespace, "stripped_cobalt_log");
            case BRIMWOOD -> new ResourceLocation(woodNamespace, "stripped_brimwood_log");
        };
    }

    public ResourceLocation strippedLogTopTexture() {
        return switch (this) {
            case CYPRESS -> new ResourceLocation(woodNamespace, "stripped_cypress_log_top");
            case LARCH -> new ResourceLocation(woodNamespace, "stripped_larch_log_top");
            case PINE -> new ResourceLocation(woodNamespace, "stripped_pine_log_top");
            case REDWOOD -> new ResourceLocation(woodNamespace, "stripped_redwood_log_top");
            case SOCOTRA -> new ResourceLocation(woodNamespace, "stripped_socotra_log_top");
            case JOSHUA -> new ResourceLocation(woodNamespace, "stripped_joshua_log_top");
            case BAOBAB -> new ResourceLocation(woodNamespace, "stripped_baobab_log_top");
            case MAGNOLIA -> new ResourceLocation(woodNamespace, "stripped_magnolia_log_top");
            case BLACKWOOD -> new ResourceLocation(woodNamespace, "stripped_blackwood_log_top");
            case EUCALYPTUS -> new ResourceLocation(woodNamespace, "stripped_eucalyptus_log_top");
            case KAPOK -> new ResourceLocation(woodNamespace, "stripped_kapok_log_top");
            case MAPLE -> new ResourceLocation(woodNamespace, "stripped_maple_log_top");
            case MAUVE -> new ResourceLocation(woodNamespace, "stripped_mauve_log_top");
            case PALM -> new ResourceLocation(woodNamespace, "stripped_palm_log_top");
            case WILLOW -> new ResourceLocation(woodNamespace, "stripped_willow_log_top");
            case BLUE_BIOSHROOM -> new ResourceLocation(woodNamespace, "stripped_blue_bioshroom_hyphae_top");
            case YELLOW_BIOSHROOM -> new ResourceLocation(woodNamespace, "stripped_yellow_bioshroom_hyphae_top");
            case GREEN_BIOSHROOM -> new ResourceLocation(woodNamespace, "stripped_green_bioshroom_hyphae_top");
            case PINK_BIOSHROOM -> new ResourceLocation(woodNamespace, "stripped_pink_bioshroom_hyphae_top");
            case COBALT -> new ResourceLocation(woodNamespace, "stripped_cobalt_log_top");
            case BRIMWOOD -> new ResourceLocation(woodNamespace, "stripped_brimwood_log_top");
        };
    }
}
