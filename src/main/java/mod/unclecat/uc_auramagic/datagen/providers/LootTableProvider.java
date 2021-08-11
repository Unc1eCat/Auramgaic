package mod.unclecat.uc_auramagic.datagen.providers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.datagen.DataGenerators;
import mod.unclecat.uc_auramagic.datagen.api.ILootTableSource;
import mod.unclecat.uc_auramagic.util.helpers.RegistryHelper;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;

public class LootTableProvider implements IDataProvider {
    @Override
    public void act(DirectoryCache cache) {
        Map<ResourceLocation, LootTable> map = RegistryHelper.getAllInstancesOf(ILootTableSource.class).stream().map(i -> i.generateLootTables())
                .reduce(new HashMap<ResourceLocation, LootTable>(), (a, i) -> {
                    a.putAll(i);
                    return a;
                });

        for (Map.Entry<ResourceLocation, LootTable> i : map.entrySet()) {
            Path path = DataGenerators.generator.getOutputFolder().resolve("data/" + i.getKey().getNamespace() + "/loot_tables/" + i.getKey().getPath() + ".json");

            try {
                IDataProvider.save(Auramagic.GSON, cache, LootTableManager.toJson(i.getValue()), path);
            } catch (IOException e) {
                Auramagic.LOG.error("Could not write loot table for location {}", i.getKey().toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
