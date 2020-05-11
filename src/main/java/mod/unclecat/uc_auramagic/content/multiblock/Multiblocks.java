package mod.unclecat.uc_auramagic.content.multiblock;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.multiblock.creators.instrument_work.InstrumentWorkMultiblockCreationTrigger;
import mod.unclecat.uc_auramagic.content.multiblock.creators.instrument_work.LoomCreator;

public class Multiblocks
{
	protected static Multimap<Class<? extends IMultiblockCreationTrigger>, IMultiblockCreator> creators = ArrayListMultimap.create();
	protected static boolean isAvaiable = true;
	
	
	public static void put(Class<? extends IMultiblockCreationTrigger> trigger, IMultiblockCreator value) // TODO: Make it return the value parameter if it was successfully put
	{
		if (isAvaiable)
		{
			float putPriority = value.getPriority();
			float previousPriority = 0.0f;
			List<IMultiblockCreator> list = (List<IMultiblockCreator>) creators.get(trigger);
			
			for (int i = 0; i < list.size(); i++)
			{
				float currentPriority = list.get(i).getPriority();
				
				if (previousPriority <= putPriority && putPriority <= currentPriority)
				{
					list.add(i, value);
					creators.replaceValues(trigger, list);
					break;
				}
				
				previousPriority = currentPriority;
			}
			
			if (list.size() == 0)
			{
				creators.put(trigger, value);
			}
			
		}
		else
		{			
			Auramagic.LOG.error("Attempted to put multiblock creator {} in an unavaiable multiblock creators registry. The creator will not be put in the registry.", value.getClass().getSimpleName());
		}
	}
	
	public static Collection<IMultiblockCreator> get(Class<? extends IMultiblockCreationTrigger> key)
	{
		return creators.get(key);
	}
	
	protected static boolean isAvaiable()
	{
		return isAvaiable;
	}
	
	public static <T extends IMultiblockCreationTrigger> T triggerCreation(T triggerBody)
	{
		for (IMultiblockCreator i : creators.get(triggerBody.getClass()))
		{
			if (i.matches(triggerBody))
			{
				triggerBody.setNoMatches(false);
				i.create(triggerBody);
				break;
			}
		}
		
		return triggerBody;
	}
}
