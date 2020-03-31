package mod.unclecat.uc_auramagic.content;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.block.content.BlockExperienceBlock;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceGem;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceShard;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;

@Deprecated
public class ColorHandler implements IItemColor, IBlockColor
{
	public static ColorHandler INSTANCE = new ColorHandler();
	
	
	/*
	 * Item
	 */
	@Override
	public int getColor(ItemStack stack, int color)
	{
		return color;
	}
		

	/*
	 * Block
	 */
	@Override
	public int getColor(BlockState state, ILightReader light, BlockPos pos, int color)
	{
		return color;
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
	 * Indicates that its supertypes must be handled by color handler
	 */
	public static interface IDynamicColor { }
	
	/*
	 * Indicates that object of field annotated with this annotation must be handled by color handler
	 */
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface DynamicColor { }

}
