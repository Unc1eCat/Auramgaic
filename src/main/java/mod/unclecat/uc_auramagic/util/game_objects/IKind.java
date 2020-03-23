package mod.unclecat.uc_auramagic.util.game_objects;

import net.minecraft.util.IStringSerializable;

public interface IKind extends IStringSerializable
{
	/*
	 * Modifies original name of a game object
	 */
	default public String appendToName(String name)
	{
		return name + "_" + getName();
	}
}