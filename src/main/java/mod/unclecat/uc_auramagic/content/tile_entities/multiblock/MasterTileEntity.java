package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;


public class MasterTileEntity extends ModTileEntity implements IMultiblockTileEntity
{
	public static TileEntityType<MasterTileEntity> TYPE;
	
	
	public MasterTileEntity()
	{
		super(TYPE);
	}

	public MasterTileEntity(TileEntityType<?> type)
	{
		super(type);
	}

	
	@Override
	public CompoundNBT writeData(CompoundNBT nbt)
	{
		return nbt;		
		
	}

	@Override
	public void readData(CompoundNBT nbt)
	{
		
	}
		
	
	@Override
	public void onDestroyed()
	{
		
	}
	
	@Override
	public void onNeighbourChanged(BlockPos fromPos)
	{
		
	}
	
	
	public void onSlaveDestroyed(SlaveTileEntity slave)
	{
		
	}
	
	public void onSlavesNeighbourChanged(SlaveTileEntity slave)
	{
		
	}
	
	@Override
	public void onRemoval()
	{
		
	}
	
	
	public <T extends SlaveTileEntity> boolean isItsSlave(T slave)
	{
		return slave.getMaster() == this;
	}

}
