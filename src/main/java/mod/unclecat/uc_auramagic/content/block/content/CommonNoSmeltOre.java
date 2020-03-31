package mod.unclecat.uc_auramagic.content.block.content;

import java.util.Arrays;
import java.util.List;

import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.storage.loot.LootContext.Builder;
import net.minecraftforge.common.ToolType;

public class CommonNoSmeltOre extends ModBlock
{
	public int dropExp;
	public ItemStack dropStack;
	public int dropCountRange = 0;
	
	
	
	public CommonNoSmeltOre(String name, float hardnessAndResistence, int harvestLevel, ItemStack dropStack, int dropExp, int dropCountRange)
	{
		super(name, Block.Properties.from(Blocks.COAL_ORE).hardnessAndResistance(hardnessAndResistence).harvestTool(ToolType.PICKAXE).harvestLevel(harvestLevel), ItemGroupAurmagic.INSTANCE, null);
		this.dropStack = dropStack;
		this.dropExp = dropExp;
		this.dropCountRange = dropCountRange;
	}
	
	@Override
	public List<ItemStack> getDrops(BlockState state, Builder builder)
	{
		ItemStack out = dropStack.copy();
		out.setCount(dropStack.getCount() + RANDOM.nextInt(dropCountRange));
		return Arrays.asList(out);
	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch)
	{
		return silktouch == 0 ? dropExp : 0;
	}
}
