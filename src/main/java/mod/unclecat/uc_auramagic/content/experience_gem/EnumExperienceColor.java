package mod.unclecat.uc_auramagic.content.experience_gem;

import mod.unclecat.uc_auramagic.content.block.content.BlockExperienceBlock;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceGem;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceShard;
import mod.unclecat.uc_auramagic.util.game_objects.IKind;

public enum EnumExperienceColor implements IKind
{
	FIRE("fire", 0xFF1A00),
	AIR("air", 0xFCFD98),
	EARTH("earth", 0x3BDD22),
	AQUA("aqua", 0x15BDEE);
	// TODO: Complete
	

	
	public final String name;
	public final int color;
	public ItemExperienceGem gem;
	public ItemExperienceShard shard;
	public BlockExperienceBlock block;
		

	
	private EnumExperienceColor(String name, int color)
	{
		this.name = name;
		this.color = color;
	}

	
	
	public int getColor()
	{
		return color;
	}

	@Override
	public String getName()
	{
		return name;
	}
}
