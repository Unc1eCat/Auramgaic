package mod.unclecat.uc_auramagic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;

@Mod(Auramagic.MODID)
public class Auramagic
{
	public static final String MODID = "uc_auramagic";
	public static Logger LOG = LogManager.getLogger(MODID);
	
	
	public Auramagic()
	{
		LOG.debug("Hello world!");
	}
}
