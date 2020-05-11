package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import java.util.List;
import java.util.function.Consumer;

import mod.unclecat.uc_auramagic.util.helpers.NBTHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class SlaveStoringMasterTileEntity extends MasterTileEntity implements ISlaveProvidingMasterTileEntity
{
	public static TileEntityType<SlaveStoringMasterTileEntity> TYPE;
	
	protected List<AxisAlignedBB> slaveSpotsPosition;
	
	
	public SlaveStoringMasterTileEntity()
	{
		super(TYPE);
	}
	
	public SlaveStoringMasterTileEntity(TileEntityType<?> type)
	{
		super(type);
	}
	
	
	@Override
	public CompoundNBT writeData(CompoundNBT nbt)
	{
		for (AxisAlignedBB i : slaveSpotsPosition)
		{
			nbt.put("slaves", NBTHelper.coordinatesToNBT(new Vec3i(i.minX, i.minY, i.minZ)));
			nbt.put("slaves", NBTHelper.coordinatesToNBT(new Vec3i(i.minX, i.minY, i.minZ)));
		}
		return nbt;
	}
		
	@Override
	public void readData(CompoundNBT nbt)
	{
		ListNBT list = (ListNBT) nbt.get("slaves");
		
		for (int i = 0; i < list.size(); i += 2)
		{
			slaveSpotsPosition.add(new AxisAlignedBB(new BlockPos(NBTHelper.nbtToIntCoordinates(list.getList(i))), new BlockPos(NBTHelper.nbtToIntCoordinates(list.getList(i + 1)))));
		}
	}
	

	@Override
	public <T extends SlaveTileEntity> void foreachSlave(Consumer<T> consumer)
	{
		for (AxisAlignedBB i : slaveSpotsPosition) foreachSlaveInSpot(i, consumer);
	}
	
	
	 // TODO!!!!!!: Make it find all slaves in a spot only by one random pos from spot
	@SuppressWarnings("unchecked")
	public <T extends SlaveTileEntity> void foreachSlaveInSpot(AxisAlignedBB spot, Consumer<T> consumer)
	{
		BlockPos pos = new BlockPos(spot.minX, spot.minY, spot.minZ);
		BlockPos endPos = new BlockPos(spot.maxX, spot.maxY, spot.maxZ);

		
		for (; pos.getX() <= endPos.getX(); pos.add(1, 0, 0))
		{
			for (; pos.getY() <= endPos.getY(); pos.add(0, 1, 0))
			{
				for (; pos.getZ() <= endPos.getZ(); pos.add(0, 0, 1))
				{
					TileEntity te = world.getTileEntity(pos);
					
					if (te != null && te instanceof SlaveTileEntity && this.isItsSlave((SlaveTileEntity) te))
					{
						consumer.accept((T) te);
					}
				}	
				pos = new BlockPos(pos.getX(), pos.getY(), spot.minZ);
			}
			pos = new BlockPos(pos.getX(), spot.minX, spot.minZ);
		}
	}
}
