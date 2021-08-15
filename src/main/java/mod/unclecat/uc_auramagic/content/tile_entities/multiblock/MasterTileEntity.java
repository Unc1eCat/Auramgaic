package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;


public abstract class MasterTileEntity extends ModTileEntity implements IMultiblockTileEntity
{
	public MasterTileEntity(TileEntityType<?> type)
	{
		super(type);
	}
	
	public abstract void onSlaveDestroyed(SlaveTileEntity slave);
	
	public abstract void onSlavesNeighbourChanged(SlaveTileEntity slave);
	
	public <T extends SlaveTileEntity> boolean isItsSlave(T slave)
	{
		return slave.getMaster() == this;
	}

}
