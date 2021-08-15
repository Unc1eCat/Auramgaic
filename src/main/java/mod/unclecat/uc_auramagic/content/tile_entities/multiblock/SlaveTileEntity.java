package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.NBTHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

/// Slaves are not supposed to store any state or handle any game logic. They are just used to refer to the master and mark boundaries of a construction.
/// Though in some cases you might want to subclass this to introduce some logic
public class SlaveTileEntity extends ModTileEntity implements IMultiblockTileEntity
{
	public static TileEntityType<SlaveTileEntity> TYPE;
	
	private BlockPos masterPos;
	
	
	public SlaveTileEntity()
	{
		super(TYPE);
	}
	
	public SlaveTileEntity(TileEntityType<?> type)
	{
		super(type);
	}
	

	@Override
	public CompoundNBT writeData(CompoundNBT nbt)
	{		
		nbt.put("masterPos", NBTHelper.coordinatesToNBT(getMasterPos()));
		
		return nbt;
	}

	@Override
	public void readData(CompoundNBT nbt)
	{
		masterPos = new BlockPos(NBTHelper.nbtToIntCoordinates((ListNBT) nbt.get("masterPos")));
	}
		
	
	@Override
	public void onDestroyed()
	{
		getMaster().onSlaveDestroyed(this);
	}
	
	@Override
	public void onNeighbourChanged(BlockPos fromPos)
	{
		getMaster().onSlavesNeighbourChanged(this);
	}
	
	@Override
	public void onRemoval()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public MasterTileEntity getMaster()
	{
		return (MasterTileEntity) world.getTileEntity(getMasterPos());
	}

	public BlockPos getMasterPos()
	{
		return masterPos;
	}
	
	public <T extends MasterTileEntity> boolean isItsMaster(T master)
	{
		return master == getMaster();
	}

}
