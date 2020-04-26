package mod.unclecat.uc_auramagic.datagen.providers;

import java.util.List;

import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.util.helpers.RegistryHelper;
import net.minecraft.world.storage.loot.LootTable;

public class BlockDropLootTableProvider extends ModLootTableProvider
{
	@Override
	protected void fillTablesMap()
	{
		List<ModBlock> list = RegistryHelper.getAllInstancesOf(ModBlock.class);
		
		for (ModBlock i : list)
		{
			List<LootTable> tables = i.generateLootTables();
			
			if (!tables.isEmpty())
			{
				put(i.getLootTable(), tables.get(0));
			}
		}
	}	
}
