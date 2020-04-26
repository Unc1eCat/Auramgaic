package mod.unclecat.uc_auramagic.util.helpers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import mod.unclecat.uc_auramagic.content.block.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.state.IProperty;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class BlockHelper
{
	public static List<IProperty<?>> getPropertiesFromBlock(ModBlock block)
	{
 		Field[] fields = block.getClass().getDeclaredFields();
		List<IProperty<?>> ret = new ArrayList<IProperty<?>>();
		
		for (Field i : fields)
		{
			try
			{
				if (i.get(block) instanceof IProperty<?>)
				{
					ret.add((IProperty<?>) i.get(block));
				}
			} 
			catch (Exception e)
			{
				
			}
		}
		
		if (block.externalProperties != null) ret.addAll(block.externalProperties);
		
		return ret;
	}
	
	public static VoxelShape combineAndSimplify(IBooleanFunction function, VoxelShape... voxelShapes)
	{	
		for (int i = 1; i < voxelShapes.length; i++)
		{
			voxelShapes[0] = VoxelShapes.combine(voxelShapes[0], voxelShapes[i], function);
		}
		return voxelShapes[0].simplify();
	}
	
	public static VoxelShape mix(VoxelShape... voxelShapes)
	{	
		return combineAndSimplify(IBooleanFunction.OR, voxelShapes);
	}
	
	public static VoxelShape mix(double... points)
	{	
		VoxelShape ret = VoxelShapes.empty();
		
		for (int i = 0; i < points.length; i += 6)
		{
			ret = VoxelShapes.combineAndSimplify(Block.makeCuboidShape(points[i], points[i + 1], points[i + 2], points[i + 3], points[i + 4], points[i + 5]), ret, IBooleanFunction.OR);
		}
		
		return ret;
	}
}
