package mod.unclecat.uc_auramagic.content.recipies;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.gson.JsonObject;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class JsonPassSerializer<T extends IRecipe<?>> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<T>
{
	public BiFunction<ResourceLocation, JsonObject, T> recipeConstructor;
	
	public JsonPassSerializer(BiFunction<ResourceLocation, JsonObject, T> recipeConstructor)
	{
		this.recipeConstructor = recipeConstructor;
	}
	
	@Override
	public T read(ResourceLocation recipeId, JsonObject json)
	{
		return recipeConstructor.apply(recipeId, json);
	}

	@Override
	public T read(ResourceLocation recipeId, PacketBuffer buffer)
	{
		return recipeConstructor.apply(recipeId, null);
	}

	@Override
	public void write(PacketBuffer buffer, T recipe)
	{
		// TODO Auto-generated method stub
		
	}
	
}
