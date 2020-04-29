package mod.unclecat.uc_auramagic.content.item;

import javax.annotation.Nullable;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ModItem extends Item
{
	public ModItem(String name, ItemGroup group, @Nullable Properties properties)
	{
		super(properties == null ? new Item.Properties().group(group) : properties.group(group));
		setRegistryName(Auramagic.prefix(name));
	}
	
	public ModItem(String name, Properties properties)
	{
		this(name, ItemGroupAurmagic.INSTANCE, properties);
	}
}
