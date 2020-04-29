package mod.unclecat.uc_auramagic.util.multiblock;

import net.minecraft.util.math.BlockPos;

public class ConstantSizeAreaMatcher // TODO: Complete
{
	protected BlockPos currentPosNorth;
	protected BlockPos currentPosSouth;
	protected BlockPos currentPosWest;
	protected BlockPos currentPosEast;
	
	
	public ConstantSizeAreaMatcher(BlockPos begin, BlockPos end) 
	{
		currentPosNorth = begin;
		currentPosSouth = begin;
		currentPosWest = begin;
		currentPosEast = begin;
	}
	
	
	public ConstantSizeAreaMatcher up()
	{
		currentPosNorth.add(0, 1, 0);
		
		return this;
	}
	
	public ConstantSizeAreaMatcher row()
	{
		
		
		return this;
	}
}
