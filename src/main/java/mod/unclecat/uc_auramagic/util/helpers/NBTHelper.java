package mod.unclecat.uc_auramagic.util.helpers;

import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class NBTHelper
{
	public static ListNBT coordinatesToNBT(Vec3i value)
	{
		ListNBT ret = new ListNBT();
		
		ret.add(IntNBT.func_229692_a_(value.getX()));
		ret.add(IntNBT.func_229692_a_(value.getY()));
		ret.add(IntNBT.func_229692_a_(value.getZ()));
		
		return ret;
	}
	public static ListNBT coordinatesToNBT(Vec3d value)
	{
		ListNBT ret = new ListNBT();
		
		ret.add(DoubleNBT.func_229684_a_(value.getX()));
		ret.add(DoubleNBT.func_229684_a_(value.getY()));
		ret.add(DoubleNBT.func_229684_a_(value.getZ()));
		
		return ret;
	}
	
	public static Vec3i nbtToIntCoordinates(ListNBT value)
	{
		return new Vec3i(value.getInt(0), value.getInt(1), value.getInt(2));
	}
	public static Vec3i nbtToDoubleCoordinates(ListNBT value)
	{
		return new Vec3i(value.getDouble(0), value.getDouble(1), value.getDouble(2));
	}
}
