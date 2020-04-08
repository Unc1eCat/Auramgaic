package mod.unclecat.uc_auramagic.content.block.content;

import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BlockTable extends ModBlock
{
	public BlockTable(String name, Properties properties)
	{
		super(name, properties, ItemGroupAurmagic.INSTANCE, null);
	}
	
	
	public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) 
	{
		ItemStack stack = player.getHeldItemMainhand();
		
		if (stack == null) return ActionResultType.PASS;
		
		
		
		return ActionResultType.SUCCESS;
	}
	
	
	
	
}
