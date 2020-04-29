package mod.unclecat.uc_auramagic.content;

import mod.unclecat.uc_auramagic.Auramagic;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupAurmagic extends ItemGroup
{
	public static ItemGroupAurmagic INSTANCE = new ItemGroupAurmagic();
	public static int activeIcon = 0;
	public static int iconSwitchTimer = 0;
	public static ItemStack[] icons = 
			{
					ItemStack.EMPTY,
					ItemStack.EMPTY,
					ItemStack.EMPTY,
					ItemStack.EMPTY
			};
	

	public ItemGroupAurmagic()
	{
		super(Auramagic.MODID + "aurmagic");
	}

	@Override
	public ItemStack createIcon()
	{
		return null;
	}
	
	@Override
	public ItemStack getIcon()
	{
		iconSwitchTimer++;
		if (icons[0].isEmpty()) 
		{
			icons[0] = new ItemStack(Content.EXPERIENCE_GEM_AIR);
			icons[1] = new ItemStack(Content.EXPERIENCE_GEM_FIRE);
			icons[2] = new ItemStack(Content.EXPERIENCE_GEM_EARTH);
			icons[3] = new ItemStack(Content.EXPERIENCE_GEM_AQUA);
		}
		if (iconSwitchTimer >= 100)
		{
			iconSwitchTimer = 0;
			activeIcon++;
			if (activeIcon >= icons.length) activeIcon = 0;
		}
		return icons[activeIcon];
	}
	
	@Override
	public String getTranslationKey()
	{
		return "itemGroupAuramagic.name";
	}
	
	// TODO: Add image at background and other beauty
}
