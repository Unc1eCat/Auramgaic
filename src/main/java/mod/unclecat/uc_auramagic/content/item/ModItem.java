package mod.unclecat.uc_auramagic.content.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ModItem extends Item
{
	public ModItem(String name, ItemGroup group, Properties properties)
	{
		super(properties.group(group));
		setRegistryName(name);
	}
}
