package mod.unclecat.uc_auramagic.content.experience_gem;

import mod.unclecat.uc_auramagic.util.game_objects.IKind;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public enum ExperienceGems implements IKind<ItemStack>
{
	FIRE("fire"),
	AIR("air"),
	EARTH("earth"),
	AQUA("aqua");
	// TODO: Complete
	

	
	
	ResourceLocation name;

	
	private ExperienceGems(String name)
	{
		this.name = new ResourceLocation(name);
	}


	@Override
	public String appendToName(ItemStack gameObject, String name)
	{
		return name + this.name.getPath();
	}
}
