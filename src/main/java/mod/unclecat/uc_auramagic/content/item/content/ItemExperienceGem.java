package mod.unclecat.uc_auramagic.content.item.content;

import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.ColorHandler.IDynamicColor;
import mod.unclecat.uc_auramagic.content.experience_gem.EnumExperienceColor;
import mod.unclecat.uc_auramagic.content.item.ModItem;

public class ItemExperienceGem extends ModItem implements IDynamicColor
{
	public EnumExperienceColor kind;
	
	
	public ItemExperienceGem(EnumExperienceColor kind)
	{
		super(kind.appendToName("experience_gem"), ItemGroupAurmagic.INSTANCE, new Properties().maxDamage(EnumExperienceColor.values().length));
		
		this.kind = kind;
	}
}

