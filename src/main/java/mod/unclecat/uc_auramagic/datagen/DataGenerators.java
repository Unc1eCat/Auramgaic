package mod.unclecat.uc_auramagic.datagen;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.datagen.providers.BlockDropLootTableProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = Auramagic.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	public static DataGenerator generator;
	
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		generator = event.getGenerator();
		generator.addProvider(new BlockDropLootTableProvider());
	}
}
