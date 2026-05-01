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

    CRIMSON(true),
    WARPED(true),
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
        return this.strippedLog().asItem();
    }

    @Override
    public Block getPlanks()
    {
        return planks();        // keep your internal method or inline it
    }

    @Override
    public Block getStrippedLogBlock()
    {
        return strippedLog();
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
    final String woodNamespace = "firma_compat";

    public Block planks() {
        return switch (this) {  // 'this' is the current enum instance
            case ACACIA   -> Blocks.ACACIA_PLANKS;
            case BIRCH    -> Blocks.BIRCH_PLANKS;
            case CHERRY   -> Blocks.CHERRY_PLANKS;
            case DARK_OAK -> Blocks.DARK_OAK_PLANKS;
            case JUNGLE   -> Blocks.JUNGLE_PLANKS;
            case MANGROVE -> Blocks.MANGROVE_PLANKS;
            case OAK      -> Blocks.OAK_PLANKS;
            case SPRUCE   -> Blocks.SPRUCE_PLANKS;
            case CRIMSON  -> Blocks.CRIMSON_PLANKS;
            case WARPED   -> Blocks.WARPED_PLANKS;
            // No default needed — enum switch is exhaustive
        };
    }

    public Block strippedLog() {
        return switch (this) {
            case ACACIA   -> Blocks.STRIPPED_ACACIA_LOG;
            case BIRCH    -> Blocks.STRIPPED_BIRCH_LOG;
            case CHERRY   -> Blocks.STRIPPED_CHERRY_LOG;
            case DARK_OAK -> Blocks.STRIPPED_DARK_OAK_LOG;
            case JUNGLE   -> Blocks.STRIPPED_JUNGLE_LOG;
            case MANGROVE -> Blocks.STRIPPED_MANGROVE_LOG;
            case OAK      -> Blocks.STRIPPED_OAK_LOG;
            case SPRUCE   -> Blocks.STRIPPED_SPRUCE_LOG;
            case CRIMSON  -> Blocks.STRIPPED_CRIMSON_STEM;   // Note: stems for fungi
            case WARPED   -> Blocks.STRIPPED_WARPED_STEM;
            // No default needed
        };
    }

    public Block stair() {
        return switch (this) {  // 'this' is the current enum instance
            case ACACIA   -> Blocks.ACACIA_STAIRS;
            case BIRCH    -> Blocks.BIRCH_STAIRS;
            case CHERRY   -> Blocks.CHERRY_STAIRS;
            case DARK_OAK -> Blocks.DARK_OAK_STAIRS;
            case JUNGLE   -> Blocks.JUNGLE_STAIRS;
            case MANGROVE -> Blocks.MANGROVE_STAIRS;
            case OAK      -> Blocks.OAK_STAIRS;
            case SPRUCE   -> Blocks.SPRUCE_STAIRS;
            case CRIMSON  -> Blocks.CRIMSON_STAIRS;
            case WARPED   -> Blocks.WARPED_STAIRS;
            // No default needed — enum switch is exhaustive
        };
    }

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

    public ResourceLocation logSideTexture() {
        return switch (this) {  // 'this' is the current enum instance
            case ACACIA   -> new ResourceLocation("minecraft", "block/acacia_log");
            case BIRCH    -> new ResourceLocation("minecraft", "block/birch_log");
            case CHERRY   -> new ResourceLocation("minecraft", "block/cherry_log");
            case DARK_OAK -> new ResourceLocation("minecraft", "block/dark_oak_log");
            case JUNGLE   -> new ResourceLocation("minecraft", "block/jungle_log");
            case MANGROVE -> new ResourceLocation("minecraft", "block/mangrove_log");
            case OAK      -> new ResourceLocation("minecraft", "block/oak_log");
            case SPRUCE   -> new ResourceLocation("minecraft", "block/spruce_log");
            case CRIMSON  -> new ResourceLocation("minecraft", "block/crimson_stem");
            case WARPED   -> new ResourceLocation("minecraft", "block/warped_stem");
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
