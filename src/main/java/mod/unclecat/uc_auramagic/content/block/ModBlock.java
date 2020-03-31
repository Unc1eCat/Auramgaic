package mod.unclecat.uc_auramagic.content.block;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.util.helpers.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer.Builder;

public class ModBlock extends Block
{
	public List<IProperty<?>> externalProperties = new ArrayList<IProperty<?>>();
	
	
	
	public ModBlock(String name, Properties properties, @Nullable ItemGroup group, @Nullable Item.Properties itemProperties)
	{
		super(properties);
		setRegistryName(Auramagic.prefix(name));
		
		Content.additionalGameObjects.add(new BlockItem(this, (itemProperties == null ? new Item.Properties().group(group) : itemProperties.group(group))).setRegistryName(this.getRegistryName()));
	}
	
	public ModBlock(String name, Properties properties)
	{
		super(properties);	
		setRegistryName(Auramagic.prefix(name));
	}
	
	public void addExternalProperty(IProperty<?> property)
	{
		externalProperties.add(property);
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder)
	{
		builder.add(BlockHelper.getPropertiesFromBlock(this).toArray(new IProperty<?>[] { }));
	}
	
	public <T extends Comparable<T>> void setDefaultValue(IProperty<T> property, T value)
	{
		setDefaultState(getDefaultState().with(property, value));
	}
}
