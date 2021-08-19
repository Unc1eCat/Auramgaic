package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.List;

public class DisassemblableMasterTileEntity extends SlaveLocatingMasterTileEntity implements IDisassemblablePart {
    protected boolean isAssembled = true;

    public DisassemblableMasterTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public DisassemblableMasterTileEntity(TileEntityType<?> type, List<AxisAlignedBB> slaveSpotsPosition) {
        super(type, slaveSpotsPosition);
    }

    @Override
    public void onPartDestroyed(MultiblockTileEntity part) {

    }

    @Override
    public void onPartsNeighbourChanged(MultiblockTileEntity part, BlockPos fromPos) {

    }

    @Override
    public void onDestroyed() {
        if (isAssembled) {
            getMaster().onPartDestroyed(this);
        }
    }

    @Override
    public void onNeighbourChanged(BlockPos fromPos) {
        if (isAssembled) {
            getMaster().onPartsNeighbourChanged(this, fromPos);
        }
    }

    @OverridingMethodsMustInvokeSuper
    public void disassemble() {
        forEachSlave(i -> {
            try {
                ((DisassemblableSlaveTileEntity) i).disassemble();
            } catch (Exception e) {
                throw new IllegalStateException("Disassemblable master can have only disassemblable slaves");
            }
        });
        isAssembled = false;
        onDisassembled();
    }

    public void onDisassembled() {

    }

    @Override
    public boolean isAssembled() {
        return isAssembled;
    }
}
