package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import net.minecraft.util.math.BlockPos;

public interface IMultiblockTileEntity
{
	public void onDestroyed();
	
	public void onNeighbourChanged(BlockPos fromPos);
}
