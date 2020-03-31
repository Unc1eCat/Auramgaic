package mod.unclecat.uc_auramagic.content;

import mod.unclecat.uc_auramagic.Auramagic;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupAurmagic extends ItemGroup
{
	public static ItemGroupAurmagic INSTANCE = new ItemGroupAurmagic();
	public static ItemStack icon[] = 
			{
					new ItemStack(Content.EXPERIENCE_GEM_FIRE),
					new ItemStack(Content.EXPERIENCE_GEM_AIR),
					new ItemStack(Content.EXPERIENCE_GEM_EARTH),
					new ItemStack(Content.EXPERIENCE_GEM_AQUA),
			};
	public static int activeIcon = 0;
	

	public ItemGroupAurmagic()
	{
		super(Auramagic.MODID + "aurmagic");
	}

	@Override
	public ItemStack createIcon()
	{
		return icon[activeIcon];
	}
	
	@Override
	public ItemStack getIcon()
	{
		return icon[activeIcon];
	}
	
	@Override
	public String getTranslationKey()
	{
		return "itemGroupAuramagic.name";
	}
	
	@Override
	public boolean drawInForegroundOfTab()
	{
		boolean ret = super.drawInForegroundOfTab();
		
		if (Minecraft.getInstance().getRenderPartialTicks() % 300 == 0)
		{
			activeIcon++;
		}
		
		return ret;
	}
	
	// TODO: Add image at background and other beauty
}
