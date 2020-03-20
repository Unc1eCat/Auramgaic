package mod.unclecat.uc_auramagic.util.helpers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.Content;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegistryHelper
{
	@SuppressWarnings("unchecked") // I believe in Forge's reliability
	public static <T extends IForgeRegistryEntry<T>> void registerAllViaEvent(RegistryEvent.Register<T> event)
	{
		Field[] fields = Content.class.getDeclaredFields();
		
		for (Field i : fields)
		{
			try
			{
				if (((Class<T>)event.getGenericType()).isAssignableFrom(i.get(null).getClass()))
				{
					event.getRegistry().register((T) i.get(null));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Auramagic.LOG.error("Could not register object \"" + i.getName() + "\"\n" + "Skipping the object, it will not appear in the game.");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IForgeRegistryEntry<T>> void registerAllFromAdditionalsGameObjects(RegistryEvent.Register<T> event)
	{
		for (Object i : Content.additionalGameObjects)
		{
			try
			{
				if (((Class<T>)event.getGenericType()).isAssignableFrom(i.getClass()))
				{
					event.getRegistry().register((T) i);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Auramagic.LOG.error("Could not register object of type \"" + i.getClass().getName() + "\"\n" + "Skipping the object, it will not appear in the game.");
			}
		}	
	}
	
	public static List<IItemProvider> getDynamicColorItems()
	{
		List<IItemProvider> list = new ArrayList<IItemProvider>();
		Field
		
		for ()
	}
}
 