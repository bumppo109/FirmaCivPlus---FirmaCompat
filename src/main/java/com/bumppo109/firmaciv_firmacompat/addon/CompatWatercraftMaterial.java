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
    ACACIA(true),
    BIRCH(true),
    JUNGLE(true),
    SPRUCE(true),

    CHERRY(false),
    DARK_OAK(false),
    MANGROVE(false),
    OAK(false),

    CRIMSON(false),
    WARPED(false),
    ;

    public final boolean isSoftwood;

    CompatWatercraftMaterial(boolean isSoftwood) { this.isSoftwood = isSoftwood; }

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
    final String woodNamespace = "firma_compat";

    public ResourceLocation planksTexture() {
        return switch (this) {  // 'this' is the current enum instance
            case ACACIA   -> new ResourceLocation("minecraft", "block/acacia_planks");
            case BIRCH    -> new ResourceLocation("minecraft", "block/birch_planks");
            case CHERRY   -> new ResourceLocation("minecraft", "block/cherry_planks");
            case DARK_OAK -> new ResourceLocation("minecraft", "block/dark_oak_planks");
            case JUNGLE   -> new ResourceLocation("minecraft", "block/jungle_planks");
            case MANGROVE -> new ResourceLocation("minecraft", "block/mangrove_planks");
            case OAK      -> new ResourceLocation("minecraft", "block/oak_planks");
            case SPRUCE   -> new ResourceLocation("minecraft", "block/spruce_planks");
            case CRIMSON  -> new ResourceLocation("minecraft", "block/crimson_planks");
            case WARPED   -> new ResourceLocation("minecraft", "block/warped_planks");
            // No default needed — enum switch is exhaustive
        };
    }

    public ResourceLocation strippedLogTexture() {
        return switch (this) {  // 'this' is the current enum instance
            case ACACIA   -> new ResourceLocation("minecraft", "block/stripped_acacia_log");
            case BIRCH    -> new ResourceLocation("minecraft", "block/stripped_birch_log");
            case CHERRY   -> new ResourceLocation("minecraft", "block/stripped_cherry_log");
            case DARK_OAK -> new ResourceLocation("minecraft", "block/stripped_dark_oak_log");
            case JUNGLE   -> new ResourceLocation("minecraft", "block/stripped_jungle_log");
            case MANGROVE -> new ResourceLocation("minecraft", "block/stripped_mangrove_log");
            case OAK      -> new ResourceLocation("minecraft", "block/stripped_oak_log");
            case SPRUCE   -> new ResourceLocation("minecraft", "block/stripped_spruce_log");
            case CRIMSON  -> new ResourceLocation("minecraft", "block/stripped_crimson_stem");
            case WARPED   -> new ResourceLocation("minecraft", "block/stripped_warped_stem");
            // No default needed — enum switch is exhaustive
        };
    }

    public ResourceLocation strippedLogTopTexture() {
        return switch (this) {  // 'this' is the current enum instance
            case ACACIA   -> new ResourceLocation("minecraft", "block/stripped_acacia_log_top");
            case BIRCH    -> new ResourceLocation("minecraft", "block/stripped_birch_log_top");
            case CHERRY   -> new ResourceLocation("minecraft", "block/stripped_cherry_log_top");
            case DARK_OAK -> new ResourceLocation("minecraft", "block/stripped_dark_oak_log_top");
            case JUNGLE   -> new ResourceLocation("minecraft", "block/stripped_jungle_log_top");
            case MANGROVE -> new ResourceLocation("minecraft", "block/stripped_mangrove_log_top");
            case OAK      -> new ResourceLocation("minecraft", "block/stripped_oak_log_top");
            case SPRUCE   -> new ResourceLocation("minecraft", "block/stripped_spruce_log_top");
            case CRIMSON  -> new ResourceLocation("minecraft", "block/stripped_crimson_stem_top");
            case WARPED   -> new ResourceLocation("minecraft", "block/stripped_warped_stem_top");
            // No default needed — enum switch is exhaustive
        };
    }

}
