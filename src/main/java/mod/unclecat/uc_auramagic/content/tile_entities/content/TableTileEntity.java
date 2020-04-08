package mod.unclecat.uc_auramagic.content.tile_entities.content;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TableTileEntity extends LockableTileEntity implements IInventory
{
	public NonNullList<ItemStack> inventoryList = NonNullList.withSize(9, ItemStack.EMPTY);
	
	
	
	protected TableTileEntity(TileEntityType<?> typeIn)
	{
		super(typeIn);
	}
	
	

	@Override
	public void clear()
	{
		inventoryList.clear();
	}

	@Override
	public int getSizeInventory()
	{
		return inventoryList.size();
	}

	@Override
	public boolean isEmpty()
	{
		for (ItemStack i : inventoryList)
		{
			if (!i.isEmpty()) return false;
		}
		
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return inventoryList.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		getStackInSlot(index).shrink(count);
		return getStackInSlot(index);
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		inventoryList.set(index, ItemStack.EMPTY);
		return getStackInSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		inventoryList.set(index, stack);
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player)
	{
		return true;
	}

	@Override
	protected ITextComponent getDefaultName()
	{
		return new StringTextComponent("null"); // TODO: Dig into examples
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player)
	{
		return null; // TODO: Complete
	}
	
}
