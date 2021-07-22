package mod.unclecat.uc_auramagic.content.multiblock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import mod.unclecat.uc_auramagic.Auramagic;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Multiblocks
{
	protected static List<IMultiblockCreator> creators = new ArrayList();
	static boolean isAvailable = true;
	
	
	public static void add(IMultiblockCreator value)
	{
		if (isAvailable)
		{
			creators.add(value);
		}
		else
		{			
			Auramagic.LOG.error("Attempted to put multiblock creator {} in an unavailable multiblock creators registry. The creator will not be put in the registry.", value.getClass().getSimpleName());
		}
	}
	
	protected static boolean isAvailable()
	{
		return isAvailable;
	}
	
	public static <T extends IMultiblockCreationTrigger> T triggerCreation(T creationTrigger)
	{
		for (IMultiblockCreator i : creators)
		{
			if (i.handleCreationTrigger(creationTrigger))
			{
				break;
			}
		}
		
		return creationTrigger;
	}
}
