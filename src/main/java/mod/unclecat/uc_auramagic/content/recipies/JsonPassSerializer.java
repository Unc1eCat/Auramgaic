package mod.unclecat.uc_auramagic.content.recipies;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.function.Function;

import com.google.gson.JsonObject;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class JsonPassSerializer<T extends IRecipe<?>> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<T>
{
	public Constructor<T> recipeConstructor;
	
	public JsonPassSerializer(Constructor<T> recipeConstructor)
	{
		this.recipeConstructor = recipeConstructor;
	}
	
	@Override
	public T read(ResourceLocation recipeId, JsonObject json)
	{
		return recipeConstructor.newInstance(recipeId, json);
	}

	@Override
	public T read(ResourceLocation recipeId, PacketBuffer buffer)
	{
		return recipeConstructor.newInstance(recipeId, );
	}

	@Override
	public void write(PacketBuffer buffer, T recipe)
	{
		// TODO Auto-generated method stub
		
	}
	
}
