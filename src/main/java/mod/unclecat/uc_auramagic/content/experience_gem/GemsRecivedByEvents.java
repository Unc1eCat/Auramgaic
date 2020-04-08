package mod.unclecat.uc_auramagic.content.experience_gem;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.Content;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

//@EventBusSubscriber(modid = Auramagic.MODID, bus = EventBusSubscriber.Bus.MOD)
public class GemsRecivedByEvents
{
	//@SubscribeEvent
	public static void onBreakBlock(BreakEvent event) // TODO: Add amount dropped dependency from player capabilities
	{
		if (event.getPlayer() != null && Auramagic.RANDOM.nextInt(50) == 0 &&  event.getWorld() instanceof World && !event.getPlayer().isCreative())
		{
			if (!event.getState().isNormalCube(event.getWorld(), event.getPos()) && Auramagic.RANDOM.nextInt(3) < 2) return;
			Block.spawnAsEntity((World) event.getWorld(), event.getPos(), new ItemStack(Content.EXPERIENCE_GEM_EARTH, 1)); // TODO: Give distraction gem instead
		}
	}
	
	public static void onCrafted(ItemCraftedEvent event)
	{
		
	}
}
