package mod.unclecat.uc_auramagic.content.item.content;

import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.ColorHandler.IDynamicColor;
import mod.unclecat.uc_auramagic.content.experience_gem.EnumExperienceColor;
import mod.unclecat.uc_auramagic.content.item.ModItem;

public class ItemExperienceShard extends ModItem implements IDynamicColor
{
	public EnumExperienceColor kind;
	
	
	public ItemExperienceShard(EnumExperienceColor kind)
	{
		super(kind.appendToName("experience_shard"), ItemGroupAurmagic.INSTANCE, new Properties());
		
		this.kind = kind;
	}
}
