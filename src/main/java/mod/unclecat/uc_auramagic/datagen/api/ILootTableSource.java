package mod.unclecat.uc_auramagic.datagen.api;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public interface ILootTableSource
{
    @Nonnull
    public Map<ResourceLocation, LootTable> generateLootTables();
}
