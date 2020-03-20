package mod.unclecat.uc_auramagic.content;

import java.lang.reflect.Field;

import mod.unclecat.uc_auramagic.Auramagic;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;

public class ColorHandler implements IItemColor, IBlockColor
{
	/*
	 * Item
	 */
	@Override
	public int getColor(ItemStack stack, int color)
	{
		return 0;
	}
		

	/*
	 * Block
	 */
	@Override
	public int getColor(BlockState state, ILightReader lightReader, BlockPos pos, int color)
	{
		return 0;
	} 
	
	
	
	public static boolean mustHandle(Field field)
	{
		try
		{
			if (field.get(null) instanceof IDynamicColor || field.isAnnotationPresent(DynamicColor.class))
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			Auramagic.LOG.error("Could not check object " + field.getName() + " for dynamic color.");
			return false;
		}
	}
	public static boolean mustHandle(Object object)
	{
		try
		{
			if (object instanceof IDynamicColor)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			Auramagic.LOG.error("Could not check object of class " + object.getClass().getName() + " for dynamic color.");
			return false;
		}
	}
	
	/*
	 * Indicates that its supertypes must be handled by item color handler
	 */
	public static interface IDynamicColor { }
	
	/*
	 * Indicates that its supertypes must be handled by item color handler but annotation
	 */
	@interface DynamicColor { }

}
