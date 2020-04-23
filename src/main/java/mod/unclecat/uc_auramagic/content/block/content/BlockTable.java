package mod.unclecat.uc_auramagic.content.block.content;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.content.TableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockTable extends ModBlock
{
	public static BooleanProperty NORTH = BooleanProperty.create("north");
	public static BooleanProperty EAST = BooleanProperty.create("east");
	public static BooleanProperty SOUTH = BooleanProperty.create("south");
	public static BooleanProperty WEST = BooleanProperty.create("west");
	
	
	public BlockTable(String name, Properties properties)
	{
		super(name, properties, ItemGroupAurmagic.INSTANCE, null);
	}
	
	
	public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) 
	{
		TableTileEntity te = (TableTileEntity) world.getTileEntity(pos);
		int index = getIndexClicked(rayTrace.getHitVec());
		
		//if (player.getHeldItemMainhand().getItem()) { } // TODO: Make building process...
		
		if (te.getStackInSlot(index).isEmpty())
		{
			ItemStack playerStack = player.getHeldItemMainhand();
			te.setInventorySlotContents(index, playerStack.split(1));
			player.setHeldItem(Hand.MAIN_HAND, playerStack);
		}
		else
		{
			Block.spawnAsEntity(world, pos.add(0, 1, 0), te.getStackInSlot(index));
			te.setInventorySlotContents(index, ItemStack.EMPTY);
		}
		
		return ActionResultType.SUCCESS;
	}
	
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
	{
		super.onBlockHarvested(worldIn, pos, state, player);
		((TableTileEntity)worldIn.getTileEntity(pos)).spawnDrop(pos);
	}

	@Override
	public Class<? extends ModTileEntity> getTileEntityClass()
	{
		return TableTileEntity.class;
	}
	
	public static int getIndexClicked(Vec3d clickPos)
	{
		Auramagic.LOG.info("CLICKED " + clickPos.toString()); // TODO: Remove
		return 0; // TODO: Do smth
	}
}
