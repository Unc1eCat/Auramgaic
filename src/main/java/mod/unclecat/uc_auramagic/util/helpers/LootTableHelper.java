package mod.unclecat.uc_auramagic.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.DynamicLootEntry;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetContents;

public class LootTableHelper
{
	// Copied from https://wiki.mcjty.eu/modding/index.php?title=Tut14_Ep7 and modified by UncleCat
	public static LootTable createStandardTableFromBlock(String name, Block block) {
      LootPool.Builder builder = LootPool.builder()
              .name(name)
              .rolls(ConstantRange.of(1))
              .addEntry(ItemLootEntry.builder(block)
                      .acceptFunction(CopyNbt.func_215881_a(CopyNbt.Source.BLOCK_ENTITY)
                              .func_216055_a("inv", "BlockEntityTag.inv", CopyNbt.Action.REPLACE)
                              .func_216055_a("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE))
                      .acceptFunction(SetContents.func_215920_b()
                              .func_216075_a(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents"))))
              );
      return LootTable.builder().addLootPool(builder).build();
  }
}
