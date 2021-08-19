package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.NBTHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

/// Saves master's position
public class MasterLocatingSlaveTileEntity extends SlaveTileEntity {
    public static TileEntityType<MasterLocatingSlaveTileEntity> TYPE;

    protected BlockPos masterPos;

    public MasterLocatingSlaveTileEntity() {
        super(TYPE);
    }

    public MasterLocatingSlaveTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public MasterLocatingSlaveTileEntity(BlockPos masterPos) {
        super(TYPE);
        this.masterPos = masterPos;
        markDirty();
    }

    public MasterLocatingSlaveTileEntity(TileEntityType<?> type, BlockPos masterPos) {
        super(type);
        this.masterPos = masterPos;
        markDirty();
    }


    @Override
    public CompoundNBT writeData(CompoundNBT nbt) {
        nbt.put("masterPos", NBTHelper.coordinatesToNBT(masterPos));

        return nbt;
    }

    @Override
    public void readData(CompoundNBT nbt) {
        setMasterPos(new BlockPos(NBTHelper.nbtToIntCoordinates((ListNBT) nbt.get("masterPos"))));
    }

    @Override
    public MasterTileEntity getMaster() {
        return (MasterTileEntity) world.getTileEntity(masterPos);
    }

    public void setMasterPos(BlockPos masterPos) {
        this.masterPos = masterPos;
        markDirty();
    }

    public BlockPos getMasterPos() {
        return masterPos;
    }
}
