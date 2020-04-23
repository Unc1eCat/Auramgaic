package mod.unclecat.uc_auramagic.content.tile_entities;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public abstract class TileEntityListInventory extends ModTileEntity implements IInventory
{
	public NonNullList<ItemStack> inventoryList = NonNullList.withSize(getListSize(), ItemStack.EMPTY);
		
	
	public TileEntityListInventory(TileEntityType<?> tileEntityTypeIn)
	{
		super(tileEntityTypeIn);
	}
	
	
	protected abstract int getListSize();
	
	@Override
	public CompoundNBT writeData(CompoundNBT nbt)
	{
		return ItemStackHelper.saveAllItems(nbt, inventoryList);
	}
	
	@Override
	public void readData(CompoundNBT nbt)
	{
		ItemStackHelper.loadAllItems(nbt, inventoryList);
	}
	
	
	public void spawnDrop(BlockPos pos)
	{
		inventoryList.forEach((stack) -> { Block.spawnAsEntity(world, pos, stack); });
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
}
