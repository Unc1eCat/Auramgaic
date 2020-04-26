package mod.unclecat.uc_auramagic;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mod.unclecat.uc_auramagic.content.ColorHandler;
import mod.unclecat.uc_auramagic.content.experience_gem.GemsRecivedByEvents;
import mod.unclecat.uc_auramagic.util.helpers.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod(Auramagic.MODID)
public class Auramagic
{
	public static final String MODID = "uc_auramagic";
	public static Logger LOG = LogManager.getLogger(MODID);
	public static Auramagic INSTANCE = new Auramagic();
	public static Random RANDOM = new Random();
	public static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	
	
	public Auramagic()
	{
		LOG.info("Aurmaagic instance has been created!");
		
		MinecraftForge.EVENT_BUS.addListener(Auramagic::setupCommon);	
		MinecraftForge.EVENT_BUS.addListener(Auramagic::setupClient);
		MinecraftForge.EVENT_BUS.addListener(GemsRecivedByEvents::onBreakBlock);
	}
	
	public static String prefix(String location)
	{
		if (!location.contains(":"))
		{
			return MODID + ":" + location;
		}
		
		return location;
	}
	
	
	public static void setupCommon(FMLServerStartingEvent event)
	{
	}
	
	public static void setupClient(FMLClientSetupEvent event)
	{
		Minecraft.getInstance().getItemColors().register(ColorHandler.INSTANCE, ( RegistryHelper.getDynamicColorItems().toArray(new IItemProvider[] { })));
		Minecraft.getInstance().getBlockColors().register(ColorHandler.INSTANCE, ( RegistryHelper.getDynamicColorBlocks().toArray(new Block[] { })));
	}
}
