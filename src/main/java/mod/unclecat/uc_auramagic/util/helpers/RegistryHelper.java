package mod.unclecat.uc_auramagic.util.helpers;

import java.lang.reflect.Field;

import mod.unclecat.uc_auramagic.content.Content;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegistryHelper
{
	public static <T extends IForgeRegistryEntry<T>> void registerViaEvent(Class<T> clas, RegistryEvent.Register<T> event)
	{
		Field[] fields = Content.class.getDeclaredFields();
		
		for (Field i : fields)
		{
			i.getDeclaringClass();
		}
	}
}
