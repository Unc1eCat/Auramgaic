package mod.unclecat.uc_auramagic.content;

import mod.unclecat.uc_auramagic.Auramagic;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupAurmagic extends ItemGroup
{
	public static ItemGroupAurmagic INSTANCE = new ItemGroupAurmagic();
	ItemStack icon = new ItemStack(Content.)
	
	

	public ItemGroupAurmagic()
	{
		super(Auramagic.MODID + "aurmagic");
	}

	@Override
	public ItemStack createIcon()
	{
		return icon;
	}

}
