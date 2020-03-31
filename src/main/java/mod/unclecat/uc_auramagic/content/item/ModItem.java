package mod.unclecat.uc_auramagic.content.item;

import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ModItem extends Item
{
	public ModItem(String name, ItemGroup group, Properties properties)
	{
		super(properties.group(group));
		setRegistryName(name);
	}
	
	public ModItem(String name, Properties properties)
	{
		super(properties.group(ItemGroupAurmagic.INSTANCE));
		setRegistryName(name);
	}
}
