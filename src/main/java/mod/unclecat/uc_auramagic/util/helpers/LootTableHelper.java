package mod.unclecat.uc_auramagic.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetContents;

public class LootTableHelper {
    // Copied from https://github.com/VazkiiMods/Botania/blob/master/src/main/java/vazkii/botania/data/BlockLootProvider.java and modified by UncleCat
    public static LootTable createStandardTableFromBlock(String name, Block block) {
        return LootTable.builder().setParameterSet(LootParameterSets.BLOCK).addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block))
                .acceptCondition(SurvivesExplosion.builder())).build();
    }
}
