package mod.unclecat.uc_auramagic.content.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.datagen.providers.ModLootTableProvider.IGeneratedLootTableProvider;
import mod.unclecat.uc_auramagic.util.helpers.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;

public class ModBlock extends Block implements IGeneratedLootTableProvider
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
	
	public Class<? extends ModTileEntity> getTileEntityClass()
	{
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		try
		{
			return ((TileEntityType<? extends TileEntity>) getTileEntityClass().getDeclaredField("TYPE").get(null)).create();
		} 
		catch (Exception e)
		{
			Auramagic.LOG.error("Could not create tile entity for block " + this.toString());
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return getTileEntityClass() != null;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
	{
		return Arrays.asList(new ItemStack(Item.getItemFromBlock(this))); // Frick loot tables. Loot tables suck. Only hardcoded stuff. Yey deprecated featuresssssssssssssssss
	}
	
	@Override
	public List<LootTable> generateLootTables()
	{
		return Collections.emptyList(); //Arrays.asList(LootTableHelper.createStandardTableFromBlock(getRegistryName().toString(), this));
	}
}
