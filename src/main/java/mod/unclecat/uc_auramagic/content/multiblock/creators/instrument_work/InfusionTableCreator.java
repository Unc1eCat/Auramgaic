package mod.unclecat.uc_auramagic.content.multiblock.creators.instrument_work;


import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.multiblock.IMultiblockCreationTrigger;
import mod.unclecat.uc_auramagic.content.multiblock.IMultiblockCreator;

public class InfusionTableCreator implements IMultiblockCreator
{	
	@Override
	public boolean matches(IMultiblockCreationTrigger trigger)
	{
		InstrumentWorkMultiblockCreationTrigger t = ((InstrumentWorkMultiblockCreationTrigger)trigger);
		
		if (t.clickedState.getBlock() != Content.WOODEN_TABLE) return false;
		return t.matchesClickedSequenceByItem(null); // TODO: Create instruments
	}

	@Override
	public void create(IMultiblockCreationTrigger trigger)
	{
		// TODO Create infusion table
	}
}
