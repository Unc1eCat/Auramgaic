package mod.unclecat.uc_auramagic.content.recipies.content;

import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.experience_gem.EnumExperienceColor;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceShard;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeExperienceBlock extends SpecialRecipe
{
	public static SpecialRecipeSerializer<RecipeExperienceBlock> SERIALIZER = new SpecialRecipeSerializer<>(RecipeExperienceBlock::new);
			//IRecipeSerializer.register(Auramagic.prefix("experience_block"), new SpecialRecipeSerializer<>(RecipeExperienceBlock::new));
	
	
	public RecipeExperienceBlock(ResourceLocation idIn)
	{
		super(idIn);
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn)
	{
		EnumExperienceColor kind = null;
		int j = 0;
		
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			 ItemStack cur = inv.getStackInSlot(i);
			 
			 if (cur.getItem() instanceof ItemExperienceShard)
			 {
				 if (kind == null) kind = ((ItemExperienceShard)cur.getItem()).kind;
				 else if (((ItemExperienceShard)cur.getItem()).kind != kind) return false;
				 else j++;
			 }
			 else return false;
		}
		
		return j == 8;
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv)
	{
		return new ItemStack(((ItemExperienceShard)inv.getStackInSlot(0).getItem()).kind.block);
	}

	@Override
	public boolean canFit(int width, int height)
	{
		return width * height >= 9;
	}

	@Override
	public IRecipeSerializer<?> getSerializer()
	{
		return null;
	}
}
