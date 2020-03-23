package mod.unclecat.uc_auramagic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.unclecat.uc_auramagic.content.ColorHandler;
import mod.unclecat.uc_auramagic.util.helpers.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.common.Mod;

@Mod(Auramagic.MODID)
public class Auramagic
{
	public static final String MODID = "uc_auramagic";
	public static Logger LOG = LogManager.getLogger(MODID);
	
	
	public Auramagic()
	{
		LOG.debug("Hello world!");
		
		Minecraft.getInstance().getItemColors().register(ColorHandler.INSTANCE, ( RegistryHelper.getDynamicColorItems().toArray(new IItemProvider[] { })));
		Minecraft.getInstance().getBlockColors().register(ColorHandler.INSTANCE, ( RegistryHelper.getDynamicColorBlocks().toArray(new Block[] { })));
	}
	
	public static String prefix(String location)
	{
		if (!location.contains(":"))
		{
			return MODID + ":" + location;
		}
		
		return location;
	}
}
