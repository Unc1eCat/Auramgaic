package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/// Represents a tile entity that can give its slaves
public interface ISlaveProvidingTileEntity
{
	@SuppressWarnings("unchecked")
	default public <T extends SlaveTileEntity> List<T> getSlaves()
	{
		List<T> list = new ArrayList<T>();
		
		forEachSlave((t) -> { list.add((T) t); });
		
		return list;
	}
	
	public <T extends SlaveTileEntity> void forEachSlave(Consumer<T> consumer);
}
