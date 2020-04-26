package mod.unclecat.uc_auramagic.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.content.block.content.BlockExperienceBlock;
import mod.unclecat.uc_auramagic.content.block.content.BlockTable;
import mod.unclecat.uc_auramagic.content.block.content.CommonNoSmeltOre;
import mod.unclecat.uc_auramagic.content.experience_gem.EnumExperienceColor;
import mod.unclecat.uc_auramagic.content.item.ModItem;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceGem;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceShard;
import mod.unclecat.uc_auramagic.content.recipies.content.RecipeExperienceBlock;
import mod.unclecat.uc_auramagic.content.recipies.content.RecipeExperienceShard;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Auramagic.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Content
{
	public static List<Object> additionalGameObjects = new ArrayList<Object>();
	
	
	/***** ITEMS ******/
	// Colored experience items
	public static ItemExperienceGem EXPERIENCE_GEM_FIRE = new ItemExperienceGem(EnumExperienceColor.FIRE);
	public static ItemExperienceGem EXPERIENCE_GEM_AIR = new ItemExperienceGem(EnumExperienceColor.AIR);
	public static ItemExperienceGem EXPERIENCE_GEM_EARTH = new ItemExperienceGem(EnumExperienceColor.EARTH);
	public static ItemExperienceGem EXPERIENCE_GEM_AQUA = new ItemExperienceGem(EnumExperienceColor.AQUA);
	public static ItemExperienceShard EXPERIENCE_SHARD_FIRE = new ItemExperienceShard(EnumExperienceColor.FIRE);
	public static ItemExperienceShard EXPERIENCE_SHARD_AIR = new ItemExperienceShard(EnumExperienceColor.AIR);
	public static ItemExperienceShard EXPERIENCE_SHARD_EARTH = new ItemExperienceShard(EnumExperienceColor.EARTH);
	public static ItemExperienceShard EXPERIENCE_SHARD_AQUA = new ItemExperienceShard(EnumExperienceColor.AQUA);
	public static BlockExperienceBlock EXPERIENCE_BLOCK_FIRE = new BlockExperienceBlock(EnumExperienceColor.FIRE);
	public static BlockExperienceBlock EXPERIENCE_BLOCK_AIR = new BlockExperienceBlock(EnumExperienceColor.AIR);
	public static BlockExperienceBlock EXPERIENCE_BLOCK_EARTH = new BlockExperienceBlock(EnumExperienceColor.EARTH);
	public static BlockExperienceBlock EXPERIENCE_BLOCK_AQUA = new BlockExperienceBlock(EnumExperienceColor.AQUA);
	
	// Gems
	public static ModItem RUBY = new ModItem("ruby", new Item.Properties());
	public static ModItem SAPPHIRE = new ModItem("sapphire", new Item.Properties());
	public static ModItem TOPAZ = new ModItem("topaz", new Item.Properties());
	public static ModItem AMBER = new ModItem("amber", new Item.Properties());
	public static ModItem AMETHYST = new ModItem("amethyst", new Item.Properties());
	
	
	
	/***** BLOCKS ******/
	// Gem ores
	public static CommonNoSmeltOre RUBY_ORE = new CommonNoSmeltOre("ruby_ore", 3.0F, 2, new ItemStack(RUBY, 1), 3, 3);
	public static CommonNoSmeltOre SAPPHIRE_ORE = new CommonNoSmeltOre("sapphire_ore", 3.0F, 2, new ItemStack(SAPPHIRE, 1), 3, 3);
	public static CommonNoSmeltOre TOPAZ_ORE = new CommonNoSmeltOre("topaz_ore", 3.0F, 2, new ItemStack(TOPAZ, 1), 3, 3);
	public static CommonNoSmeltOre AMBER_ORE = new CommonNoSmeltOre("amber_ore", 3.0F, 2, new ItemStack(AMBER, 1), 3, 3);
	public static CommonNoSmeltOre AMETHYST_ORE = new CommonNoSmeltOre("amethyst_ore", 3.0F, 2, new ItemStack(AMETHYST, 1), 3, 3);
	
	// Etc.
	public static BlockTable WOODEN_TABLE = new BlockTable("wooden_table", Block.Properties.from(Blocks.OAK_PLANKS).harvestTool(ToolType.AXE).harvestLevel(1));
	public static BlockTable STONE_TABLE = new BlockTable("stone_table", Block.Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1));
	
	
	
	/***** RECIPIES *****/
	public static SpecialRecipeSerializer<RecipeExperienceShard> RECIPE_EXPERIENCE_SHARD = 
			IRecipeSerializer.register(Auramagic.prefix("experience_shard"), new SpecialRecipeSerializer<RecipeExperienceShard>(RecipeExperienceShard::new));
	public static SpecialRecipeSerializer<RecipeExperienceBlock> RECIPE_EXPERIENCE_BLOCK = 
			IRecipeSerializer.register(Auramagic.prefix("experience_block"), new SpecialRecipeSerializer<RecipeExperienceBlock>(RecipeExperienceBlock::new));
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		RegistryHelper.registerAllViaEvent(event);
		RegistryHelper.registerAllFromAdditionalsGameObjects(event);
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		RegistryHelper.registerAllViaEvent(event);
		RegistryHelper.registerAllFromAdditionalsGameObjects(event);
	}
	
	@SubscribeEvent
	public static void registerRecipieSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event)
	{
		RegistryHelper.registerAllViaEvent(event);
		RegistryHelper.registerAllFromAdditionalsGameObjects(event);
	}
	
	// Don't bully me for shitcode in the method pls. I'll probably change it. Sry for long game loading
	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event)
	{
		List<ModBlock> list = RegistryHelper.getAllInstancesOf(ModBlock.class);
		Map<Class<? extends ModTileEntity>, List<Block>> types = new HashMap<Class<? extends ModTileEntity>, List<Block>>();
		
		for (ModBlock i : list)
		{
			Class<? extends ModTileEntity> te = i.getTileEntityClass();
			if (te == null) continue;
			List<Block> validBlocks = types.get(te);
			
			if (validBlocks == null)
			{
				validBlocks = new ArrayList<Block>();
				types.put(te, validBlocks);
			}
			
			validBlocks.add(i);
		}
		
		Set<Entry<Class<? extends ModTileEntity>, List<Block>>> set = types.entrySet();
		
		for (Entry<Class<? extends ModTileEntity>, List<Block>> i : set)
		{
			RegistryHelper.registerTileEntityTypeFromMapEntry(i, event.getRegistry());
		}
	}
}
