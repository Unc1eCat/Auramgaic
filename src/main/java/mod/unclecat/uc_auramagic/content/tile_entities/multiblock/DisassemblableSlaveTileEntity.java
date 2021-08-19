package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class DisassemblableSlaveTileEntity extends MasterLocatingSlaveTileEntity implements IDisassemblablePart {
    public static TileEntityType<DisassemblableSlaveTileEntity> TYPE;

    protected boolean isAssembled = true;

    public DisassemblableSlaveTileEntity(BlockPos masterPos) {
        super(TYPE, masterPos);
    }

    public DisassemblableSlaveTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public DisassemblableSlaveTileEntity(TileEntityType<?> type, BlockPos masterPos) {
        super(type, masterPos);
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

    public void disassemble() {
        isAssembled = false;
        onDissasemble();
    }

    public void onDissasemble() {

    }

    @Override
    public boolean isAssembled() {
        return isAssembled;
    }
}
