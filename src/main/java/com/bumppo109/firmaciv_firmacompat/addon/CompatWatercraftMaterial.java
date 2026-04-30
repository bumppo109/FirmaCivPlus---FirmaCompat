package com.bumppo109.firmaciv_firmacompat.addon;

import com.alekiponi.alekiships.common.entity.vehicle.AbstractVehicle;
import com.alekiponi.firmaciv.common.entity.vehicle.CanoeEntity;
import com.bumppo109.firma_compat.block.CompatWood;
import com.bumppo109.firma_compat.block.ModRegistryWood;
import com.bumppo109.firma_compat.item.ModItems;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivEntities;
import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public enum CompatWatercraftMaterial implements ModWatercraftMaterial
{
    //softwoods, makes canoes
    ACACIA(CompatWood.ACACIA, true),
    BIRCH(CompatWood.BIRCH, false)
    ;

    public final boolean isSoftwood;
    public final CompatWood wood;

    CompatWatercraftMaterial(CompatWood wood, boolean isSoftwood) { this.wood = wood; this.isSoftwood = isSoftwood; }
    @Override
    public Item getRailing()
    {
        return ModItems.LUMBER.get(wood).get();
    }

    @Override
    public Item getStrippedLog()
    {
        return wood.strippedLog().asItem();
    }

    @Override
    public boolean withstandsLava()
    {
        return false;
    }

    @Override
    public BlockState getDeckBlock()
    {
        return wood.planks().defaultBlockState();
    }

    @Override
    public CompatWood getWoodType(){
        return wood;
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
        return wood.getSerializedName();
    }

    @Override
    public ModRegistryWood getWood()
    {
        return wood;
    }

    @Override
    public boolean isSoftwood()
    {
        return isSoftwood;
    }
}
