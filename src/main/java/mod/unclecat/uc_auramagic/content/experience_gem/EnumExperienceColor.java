package mod.unclecat.uc_auramagic.content.experience_gem;

import mod.unclecat.uc_auramagic.content.ColorHandler.IDynamicColor;
import mod.unclecat.uc_auramagic.util.game_objects.IKind;

public enum EnumExperienceColor implements IKind, IDynamicColor
{
	FIRE("fire", 0xFF1A00),
	AIR("air", 0xFCFD98),
	EARTH("earth", 0x3BDD22),
	AQUA("aqua", 0x15BDEE);
	// TODO: Complete
	

	
	String name;
	int colorMultiplier;
		

	
	private EnumExperienceColor(String name, int colorMultiplier)
	{
		this.name = name;
		this.colorMultiplier = colorMultiplier;
	}

	
	
	public int getColorMultiplier()
	{
		return colorMultiplier;
	}

	@Override
	public String getName()
	{
		return name;
	}
}
