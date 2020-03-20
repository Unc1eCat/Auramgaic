package mod.unclecat.uc_auramagic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
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
		
		Minecraft.getInstance().getItemColors().register(new IItemColor(), );
	}
}
