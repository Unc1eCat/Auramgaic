package mod.unclecat.uc_auramagic.content.block;

import javax.annotation.Nullable;

import mod.unclecat.uc_auramagic.content.Content;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;

public class ModBlock extends Block
{
	public ModBlock(String name, Properties properties, boolean hasItem, @Nullable Item.Properties itemProperties)
	{
		super(properties);
		
		if (hasItem)
		{
			Content.additionalGameObjects.add(new BlockItem(this, (itemProperties == null ? new Item.Properties() : itemProperties)));
		}
	}
	
	@Override
	public StateContainer<Block, BlockState> getStateContainer()
	{
		return new StateContainer<Block, BlockState>(null, null, null)
	}
}
