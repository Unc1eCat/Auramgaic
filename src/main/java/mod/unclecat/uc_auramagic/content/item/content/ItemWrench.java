package mod.unclecat.uc_auramagic.content.item.content;

import mod.unclecat.uc_auramagic.content.item.ModItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Rotation;

public class ItemWrench extends ModItem
{
	public ItemWrench()
	{
		super("wrench", new Item.Properties().maxStackSize(1).maxDamage(42));
	}
	
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context)
	{
		context.getItem().damageItem(1, context.getPlayer(), (e) -> {});
		context.getWorld().setBlockState(context.getPos(), context.getWorld().getBlockState(context.getPos()).rotate(context.getWorld(), context.getPos(), Rotation.CLOCKWISE_90));
		return ActionResultType.SUCCESS;
	}
}
