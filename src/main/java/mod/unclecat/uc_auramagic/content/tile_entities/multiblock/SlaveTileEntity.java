package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class SlaveTileEntity extends MultiblockTileEntity {
    public SlaveTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void onDestroyed() {
        getMaster().onPartDestroyed(this);
    }

    @Override
    public void onNeighbourChanged(BlockPos fromPos) {
        getMaster().onPartsNeighbourChanged(this, fromPos);
    }
}
