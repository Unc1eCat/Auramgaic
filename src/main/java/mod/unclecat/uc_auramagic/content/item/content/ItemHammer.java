package mod.unclecat.uc_auramagic.content.item.content;

import java.util.List;

import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.item.ModItem;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class ItemHammer extends ModItem
{
	public ItemHammer()
	{
		super("hammer", new Item.Properties().maxStackSize(1).maxDamage(60));
	}
	
	
	
	@Override
	public ActionResultType onItemUse(ItemUseContext c) // TODO: Fix it and make it work
	{	
		if (c.getFace() == Direction.UP && c.getWorld().getBlockState(c.getPos().up()).getBlock() == Blocks.AIR && c.getWorld().getBlockState(c.getPos()).getBlock() == Blocks.ANVIL)
		{
			List<ItemEntity> items = c.getWorld().getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(c.getPos().up()));
			
			for (ItemEntity i : items)
			{
				if (i.getItem().getItem() == Items.IRON_INGOT)
				{
					Vec3d p = i.getPositionVec();
					
					if (!c.getWorld().isRemote)
					{
						ItemEntity result = new ItemEntity(c.getWorld(), p.x, p.y, p.z, new ItemStack(Content.METAL_PLATE));
						ItemStack s = i.getItem();
						
						c.getItem().damageItem(1, c.getPlayer(), (entity) -> {});
						
						s.shrink(1);
						i.setItem(s);
						
						result.setDefaultPickupDelay();
						c.getWorld().addEntity(result);
					}
					else
					{
						c.getWorld().playSound(c.getPos().getX(), c.getPos().getY(), c.getPos().getZ(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 10.0f, 1.0f, true);
						for (int j = 0; j < 10; j++) c.getWorld().addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.IRON_BLOCK.getDefaultState()), p.x, p.y + 0.3, p.z, 0.0, 0.2, 0.0);
					}
					return ActionResultType.SUCCESS;
				}
			}
		}
		return ActionResultType.PASS;
	}
}
