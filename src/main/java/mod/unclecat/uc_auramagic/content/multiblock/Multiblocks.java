package mod.unclecat.uc_auramagic.content.multiblock;

import java.util.Collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Multiblocks
{
	protected static Multimap<Class<? extends IMultiblockCreationTrigger>, IMultiblockCreator> creators = ArrayListMultimap.create();

	
	public static void put(Class<? extends IMultiblockCreationTrigger> trigger, IMultiblockCreator value)
	{
		creators.put(trigger, value);
	}
	
	public static Collection<IMultiblockCreator> get(Class<? extends IMultiblockCreationTrigger> key)
	{
		return creators.get(key);
	}
	
	public static void triggerCreation(IMultiblockCreationTrigger triggerBody)
	{
		for (IMultiblockCreator i : creators.get(triggerBody.getClass()))
		{
			if (i.matches(triggerBody))
			{
				i.create(triggerBody);
				break;
			}
		}
	}
}
