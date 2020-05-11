package mod.unclecat.uc_auramagic.content.multiblock.creators.instrument_work;


import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.block.content.BlockTable;
import mod.unclecat.uc_auramagic.content.multiblock.IMultiblockCreationTrigger;
import mod.unclecat.uc_auramagic.content.multiblock.IMultiblockCreator;
import mod.unclecat.uc_auramagic.util.multiblock.HorizontalRotationlessMatcher;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class LoomCreator implements IMultiblockCreator
{	
	@Override
	public boolean matches(IMultiblockCreationTrigger trigger)
	{
		InstrumentWorkMultiblockCreationTrigger t = ((InstrumentWorkMultiblockCreationTrigger)trigger);
		
		if (t.matchesClickedSequenceByItem(Content.HAMMER, Content.HAMMER, Content.HAMMER))
		{
			HorizontalRotationlessMatcher matcher = new HorizontalRotationlessMatcher(t.clickedPos, t.player.world);
			World w = t.player.world;
			
			matcher
			.matchBlock(Content.WOODEN_TABLE, Content.WOODEN_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK).north().matchBlock(Content.WOODEN_TABLE)
			.south().match((pos, dir) -> { 
				if (w.getBlockState(pos).getBlock() == Content.WOODEN_TABLE) return BlockTable.doesItemsMatchByItemShaped(w, pos, dir,
					Items.STICK, Items.AIR, Items.STICK,
					Items.STICK, Items.AIR, Items.STICK,
					Items.STICK, Items.STICK, Items.STICK); else return true; 
				})
			.north().match((pos, dir) -> { 
				return BlockTable.doesItemsMatchByItemShaped(w, pos, dir, 
					Items.OAK_PLANKS, Items.OAK_PLANKS, Items.OAK_PLANKS,
					Items.OAK_PLANKS, Items.AIR, Items.OAK_PLANKS,
					Items.OAK_PLANKS, Items.AIR, Items.OAK_PLANKS); 
				});
			
			return matcher.getMatches();
		}
		
		return false;
	}
	
	@Override
	public void create(IMultiblockCreationTrigger trigger)
	{
		InstrumentWorkMultiblockCreationTrigger t = ((InstrumentWorkMultiblockCreationTrigger)trigger);
		
		if (t.equalsCllickedSequenceByItem(Content.HAMMER, Content.HAMMER, Content.HAMMER))
		{
			// TODOIMPORTANT: Here loom goes.....
		}
	}
}
