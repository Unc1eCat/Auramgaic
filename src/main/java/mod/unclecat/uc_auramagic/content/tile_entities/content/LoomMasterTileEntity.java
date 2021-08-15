package mod.unclecat.uc_auramagic.content.tile_entities.content;

import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.SlaveStoringMasterTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.SlaveTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class LoomMasterTileEntity extends SlaveStoringMasterTileEntity {
    public static TileEntityType<LoomMasterTileEntity> TYPE;

    public LoomMasterTileEntity()
    {
        super(TYPE);
    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public void onNeighbourChanged(BlockPos fromPos) {

    }

    @Override
    public void onRemoval() {

    }

    @Override
    public void onSlaveDestroyed(SlaveTileEntity slave) {

    }

    @Override
    public void onSlavesNeighbourChanged(SlaveTileEntity slave) {

    }
}
