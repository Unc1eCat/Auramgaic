package mod.unclecat.uc_auramagic.content.multiblock.creators.instrument_work;

import java.util.List;

import mod.unclecat.uc_auramagic.content.multiblock.IMultiblockCreationTrigger;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;


/*
 * The idea is that you build some structure and click it with certain instrument-items in row. If something 
 * goes wrong for too long time the block that is being clicked with instruments breaks without drop of itself
 */
public class InstrumentWorkMultiblockCreationTrigger implements IMultiblockCreationTrigger
{
	public BlockPos clickedPos;
	public BlockState clickedState;
	public PlayerEntity player;
	public List<ItemStack> clickedInstrumentsSquence;
	
	
	public boolean matchesClickedSequenceByItem(Item... requiredSequence)
	{
		if (clickedInstrumentsSquence.size() > requiredSequence.length) return false; // This can't happen but
		
		for (int i = 0; i < clickedInstrumentsSquence.size(); i++)
		{
			if (clickedInstrumentsSquence.get(i).getItem() != requiredSequence[i].getItem())
			{
				return false;
			}
		}
		
		return true;
	}
}

