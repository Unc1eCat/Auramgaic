package mod.unclecat.uc_auramagic.content.multiblock;

import java.util.Collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import mod.unclecat.uc_auramagic.Auramagic;

public class Multiblocks
{
	protected static Multimap<Class<? extends IMultiblockCreationTrigger>, IMultiblockCreator> creators = ArrayListMultimap.create();
	protected static boolean isBaked = false;
	
	
	public static void put(Class<? extends IMultiblockCreationTrigger> trigger, IMultiblockCreator value)
	{
		if (isBaked)
		{
			Auramagic.LOG.error("Attempted to put multiblock creator {} in a baked multiblock creators registry. The creator will not be put in the registry.", value.getClass().getSimpleName());
		}
		else
		{			
			creators.put(trigger, value);
		}
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
	
	
	public static void bake()
	{
		//TODO: Sort "creators" multimap values by multiblock structure size
	}
}
