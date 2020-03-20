package mod.unclecat.uc_auramagic.content.item.content;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.experience_gem.ExperienceGems;
import mod.unclecat.uc_auramagic.content.item.ModItem;

public class ItemExperienceGem extends ModItem
{
	public ItemExperienceGem(ExperienceGems kind)
	{
		super(kind.appendToName(null, Auramagic.MODID + "experience_gem"), ItemGroupAurmagic.INSTANCE, new Properties().maxDamage(ExperienceGems.values().length));	
	}
}
