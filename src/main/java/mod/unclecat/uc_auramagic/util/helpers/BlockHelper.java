package mod.unclecat.uc_auramagic.util.helpers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import net.minecraft.state.IProperty;

public class BlockHelper
{
	public static List<IProperty<?>> getPropertiesFromBlock(ModBlock block)
	{
 		Field[] fields = block.getClass().getDeclaredFields();
		List<IProperty<?>> ret = new ArrayList<IProperty<?>>();
		
		for (Field i : fields)
		{
			try
			{
				if (/*i.get(this).getClass().getTypeName() == "IProperty"*/ i.get(block) instanceof IProperty<?>) // TODO: Frick generic types. Why didn't they make superinterface for IProperty
				{
					ret.add((IProperty<?>) i.get(block));
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				Auramagic.LOG.error("Could not get a property from block \"" + block.getRegistryName() + "\".");
			}
		}
		
		if (block.externalProperties != null) ret.addAll(block.externalProperties);
		
		return ret;
	}
}
