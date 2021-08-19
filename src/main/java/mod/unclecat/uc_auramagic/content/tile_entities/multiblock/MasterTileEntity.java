package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;


import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class MasterTileEntity extends MultiblockTileEntity {
    public MasterTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public MasterTileEntity getMaster() {
        return this;
    }

    public abstract void onPartDestroyed(MultiblockTileEntity part);

    public abstract void onPartsNeighbourChanged(MultiblockTileEntity part, BlockPos fromPos);

    @Override
    public void onDestroyed() {
        onPartDestroyed(this);
    }

    @Override
    public void onNeighbourChanged(BlockPos fromPos) {
        onPartsNeighbourChanged(this, fromPos);
    }

    @SuppressWarnings("unchecked")
    public <T extends MasterLocatingSlaveTileEntity> List<T> getSlaves() {
        List<T> list = new ArrayList<T>();

        forEachSlave((t) -> {
            list.add((T) t);
        });

        return list;
    }

    public abstract void forEachSlave(Consumer<SlaveTileEntity> consumer);
}
