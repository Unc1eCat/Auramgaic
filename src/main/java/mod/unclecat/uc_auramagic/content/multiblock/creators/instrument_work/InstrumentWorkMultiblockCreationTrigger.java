package mod.unclecat.uc_auramagic.content.multiblock.creators.instrument_work;

import java.util.ArrayList;
import java.util.List;

import mod.unclecat.uc_auramagic.content.block.content.UnderInstrumentsConstructionBlock;
import mod.unclecat.uc_auramagic.content.item.content.InstrumentItem;
import mod.unclecat.uc_auramagic.content.multiblock.IMultiblockCreationTrigger;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;


/*
 * The idea is that you build some structure and click it with certain instrument-items in row. If something 
 * goes wrong for too long time the block that is being clicked with instruments breaks without drop of itself
 */
public class InstrumentWorkMultiblockCreationTrigger implements IMultiblockCreationTrigger
{
	public BlockPos clickedPos;
	public PlayerEntity player;
	public List<InstrumentItem> clickedInstrumentsSquence = new ArrayList<InstrumentItem>();
	
	// Returned
	public boolean wrong = true;
	public UnderInstrumentsConstructionBlock turnBlockIntoBlock;
	
	
	@Override
	public void setNoMatches(boolean val)
	{
		wrong = val;
	}
	
	
	public boolean matchesClickedSequenceByItem(InstrumentItem... requiredSequence)
	{
		if (clickedInstrumentsSquence.size() > requiredSequence.length) return false;
		
		for (int i = 0; i < clickedInstrumentsSquence.size(); i++)
		{
			if (clickedInstrumentsSquence.get(i).getItem() != requiredSequence[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean equalsCllickedSequenceByItem(InstrumentItem... requiredSequence)
	{
		if (requiredSequence.length != clickedInstrumentsSquence.size()) return false;
		
		for (int i = 0; i < requiredSequence.length; i++)
		{
			if (requiredSequence[i] != clickedInstrumentsSquence.get(i))
			{
				return false;
			}
		}
		
		return true;
	}
}

