package mod.unclecat.uc_auramagic.content;

import java.util.ArrayList;
import java.util.List;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.block.content.BlockExperienceBlock;
import mod.unclecat.uc_auramagic.content.experience_gem.EnumExperienceColor;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceGem;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceShard;
import mod.unclecat.uc_auramagic.util.helpers.RegistryHelper;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Auramagic.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Content
{
	public static List<Object> additionalGameObjects = new ArrayList<Object>();
	
	
	public static ItemExperienceGem EXPERIENCE_GEM_FIRE = new ItemExperienceGem(EnumExperienceColor.FIRE);
	public static ItemExperienceGem EXPERIENCE_GEM_AIR = new ItemExperienceGem(EnumExperienceColor.AIR);
	public static ItemExperienceGem EXPERIENCE_GEM_EARTH = new ItemExperienceGem(EnumExperienceColor.EARTH);
	public static ItemExperienceGem EXPERIENCE_GEM_AQUA = new ItemExperienceGem(EnumExperienceColor.AQUA);
	public static ItemExperienceShard EXPERIENCE_SHARD_FIRE = new ItemExperienceShard(EnumExperienceColor.FIRE);
	public static ItemExperienceShard EXPERIENCE_SHARD_AIR = new ItemExperienceShard(EnumExperienceColor.AIR);
	public static ItemExperienceShard EXPERIENCE_SHARD_EARTH = new ItemExperienceShard(EnumExperienceColor.EARTH);
	public static ItemExperienceShard EXPERIENCE_SHARD_AQUA = new ItemExperienceShard(EnumExperienceColor.AQUA);
	public static BlockExperienceBlock EXPERIENCE_BLOCK_FIRE = new BlockExperienceBlock(EnumExperienceColor.FIRE);
	public static BlockExperienceBlock EXPERIENCE_BLOCK_AIR = new BlockExperienceBlock(EnumExperienceColor.AIR);
	public static BlockExperienceBlock EXPERIENCE_BLOCK_EARTH = new BlockExperienceBlock(EnumExperienceColor.EARTH);
	public static BlockExperienceBlock EXPERIENCE_BLOCK_AQUA = new BlockExperienceBlock(EnumExperienceColor.AQUA);
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		RegistryHelper.registerAllViaEvent(event);
		RegistryHelper.registerAllFromAdditionalsGameObjects(event);
	}
	
//	@SubscribeEvent
//	public static void registerModels(ModelRegistryEvent event) 
//	{
//		List<IModelRegisterer> list = RegistryHelper.getAllInstancesOf(IModelRegisterer.class);
//		
//		for (IModelRegisterer i : list)
//		{
//			i.registerModel();
//		}
//	}
}
