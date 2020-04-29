package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class SlaveStoringMasterTileEntity extends MasterTileEntity implements ISlaveProvidingMasterTileEntity
{
	public static TileEntityType<SlaveStoringMasterTileEntity> TYPE;
	
	List<BlockPos> slaveSpotsPosition; // Stores position of blocks from each of separated by air groups of slaves
	
	
	public SlaveStoringMasterTileEntity()
	{
		super(TYPE);
	}
	
	public SlaveStoringMasterTileEntity(TileEntityType<?> type)
	{
		super(type);
	}
	

	@Override
	public <T extends SlaveTileEntity> void foreachSlave(Consumer<T> consumer)
	{
		
	}
	
	
	public static <T extends SlaveTileEntity> void foreachSlaveInSpot(BlockPos spotPos, BlockPos masterPos, Consumer<T> consumer)
	{
		// TODO: Complete
	}
}
