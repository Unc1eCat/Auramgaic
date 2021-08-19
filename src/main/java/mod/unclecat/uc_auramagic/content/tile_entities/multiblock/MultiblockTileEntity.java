package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class MultiblockTileEntity extends ModTileEntity {
    public MultiblockTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void remove() {
        onDestroyed();
        super.remove();
    }

    public abstract MasterTileEntity getMaster();

    public boolean isInTheSameMultiblock(MultiblockTileEntity te) {
        return te.getMaster() == getMaster();
    }

    public abstract void onDestroyed();

    public abstract void onNeighbourChanged(BlockPos fromPos);
}
