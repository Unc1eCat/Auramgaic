package mod.unclecat.uc_auramagic.content;

import java.util.ArrayList;
import java.util.List;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.item.ModItem;
import mod.unclecat.uc_auramagic.util.helpers.RegistryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Auramagic.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Content
{
	public static List<Object> additionalGameObjects = new ArrayList<Object>();
	
	
	public static ModItem FIRE_GEM_SHARD_BLOCK = new ModItem("fire", ItemGroup.MATERIALS, new Properties());
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		RegistryHelper.registerAllViaEvent(event);
		RegistryHelper.registerAllFromAdditionalsGameObjects(event);
	}
}
