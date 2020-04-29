package mod.unclecat.uc_auramagic.content.block;

import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.IMultiblockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MultiblockBlock<T extends IMultiblockTileEntity> extends ModBlock
{
	public MultiblockBlock(String name, Properties properties, ItemGroup group,
			net.minecraft.item.Item.Properties itemProperties)
	{
		super(name, properties, group, itemProperties);
	}
	public MultiblockBlock(String name, Properties properties)
	{
		super(name, properties);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
	{
		super.onBlockHarvested(worldIn, pos, state, player);
		
		T te = (T) worldIn.getTileEntity(pos);
		
		te.onDestroyed();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving)
	{
		T te = (T) worldIn.getTileEntity(pos);
		
		te.onNeighbourChanged(fromPos);
	}
	
	@Override
	public Class<? extends ModTileEntity> getTileEntityClass()
	{
		return super.getTileEntityClass();
	}
}
