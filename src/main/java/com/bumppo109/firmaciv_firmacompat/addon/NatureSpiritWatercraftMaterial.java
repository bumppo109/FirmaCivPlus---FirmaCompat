package com.bumppo109.firmaciv_firmacompat.addon;

import com.alekiponi.alekiships.common.entity.vehicle.AbstractVehicle;
import com.alekiponi.firmaciv.common.entity.vehicle.CanoeEntity;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivEntities;
import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.regions_unexplored.block.RuBlocks;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public enum NatureSpiritWatercraftMaterial implements ModWatercraftMaterial
{
    //softwoods, makes canoes
    FIR(true, () -> getBlock("fir_planks"), () -> getBlock("stripped_fir_log")),
    CYPRESS(true, () -> getBlock("cypress_planks"), () -> getBlock("stripped_cypress_log")),
    COCONUT(true, () -> getBlock("coconut_planks"), () -> getBlock("stripped_coconut_log")),
    CEDAR(true, () -> getBlock("cedar_planks"), () -> getBlock("stripped_cedar_log")),
    SUGI(true, () -> getBlock("sugi_planks"), () -> getBlock("stripped_sugi_log")),
    JOSHUA(true, () -> getBlock("joshua_planks"), () -> getBlock("stripped_joshua_log")),
    LARCH(true, () -> getBlock("larch_planks"), () -> getBlock("stripped_larch_log")),

    MAHOGANY(false, () -> getBlock("mahogany_planks"), () -> getBlock("stripped_mahogany_log")),
    SAXAUL(false, () -> getBlock("saxaul_planks"), () -> getBlock("stripped_saxaul_log")),
    REDWOOD(false, () -> getBlock("redwood_planks"), () -> getBlock("stripped_redwood_log")),
    ASPEN(false, () -> getBlock("aspen_planks"), () -> getBlock("stripped_aspen_log")),
    WISTERIA(false, () -> getBlock("wisteria_planks"), () -> getBlock("stripped_wisteria_log")),
    WILLOW(false, () -> getBlock("willow_planks"), () -> getBlock("stripped_willow_log")),
    PALO_VERDE(false, () -> getBlock("palo_verde_planks"), () -> getBlock("stripped_palo_verde_log")),
    OLIVE(false, () -> getBlock("olive_planks"), () -> getBlock("stripped_olive_log")),
    GHAF(false, () -> getBlock("ghaf_planks"), () -> getBlock("stripped_ghaf_log")),
    MAPLE(false, () -> getBlock("maple_planks"), () -> getBlock("stripped_maple_log")),
    ;

    public final boolean isSoftwood;
    private final Supplier<Block> planks;
    private final Supplier<Block> strippedLog;

    final String woodNamespace = "natures_spirit";

    private static Block getBlock(String name) {
        return ForgeRegistries.BLOCKS.getValue(
                new ResourceLocation("natures_spirit", name)
        );
    }


    NatureSpiritWatercraftMaterial(boolean isSoftwood, Supplier<Block> planks, Supplier<Block> strippedLog) {
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
        return new ResourceLocation(woodNamespace, "block/" + this.getSerializedName() + "_planks");
    }

    @Override
    public ResourceLocation getStrippedLogTexture()
    {
        return new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_log");
    }

    @Override
    public ResourceLocation getStrippedLogTopTexture()
    {
        return new ResourceLocation(woodNamespace, "block/stripped_" + this.getSerializedName() + "_log_top");
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
