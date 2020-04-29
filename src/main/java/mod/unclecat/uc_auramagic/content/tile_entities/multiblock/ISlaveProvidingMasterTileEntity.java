package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public interface ISlaveProvidingMasterTileEntity
{
	@SuppressWarnings("unchecked")
	default public <T extends SlaveTileEntity> List<T> getSlaves()
	{
		List<T> list = new ArrayList<T>();
		
		foreachSlave((t) -> { list.add((T) t); });
		
		return list;
	}
	
	public <T extends SlaveTileEntity> void foreachSlave(Consumer<T> consumer);
}
