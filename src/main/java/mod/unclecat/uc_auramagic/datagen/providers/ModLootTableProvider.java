package mod.unclecat.uc_auramagic.datagen.providers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.datagen.DataGenerators;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;

public abstract class ModLootTableProvider implements IDataProvider
{
	private Map<ResourceLocation, LootTable> lootTables = new HashMap<>(); 
	
	
	protected abstract void fillTablesMap();
	
	@Override
	public void act(DirectoryCache cache)
	{
		fillTablesMap();
		
		for (Map.Entry<ResourceLocation, LootTable> entry : lootTables.entrySet())
		{
			Path path = DataGenerators.generator.getOutputFolder().resolve("data/" + entry.getKey().getNamespace() + "/loot_tables/" + entry.getKey().getPath() + ".json");
			
			try
			{
				IDataProvider.save(Auramagic.GSON, cache, LootTableManager.toJson(entry.getValue()), path);
			} 
			catch (IOException e)
			{
				Auramagic.LOG.error("Could not write loot table for location {}", entry.getKey().toString());
				e.printStackTrace();
			}
		}
	}
	
	
	protected void put(ResourceLocation location, LootTable value)
	{
		if (lootTables.put(location, value) != null)
		{
			Auramagic.LOG.error("Duplicate loot tables for location {}", location.toString());
		}
	}
	
	@Override
	public String getName()
	{
		return this.getClass().getSimpleName();
	}
	
	
	
	public static interface IGeneratedLootTableProvider
	{
		@Nonnull
		public List<LootTable> generateLootTables();
	}
}
