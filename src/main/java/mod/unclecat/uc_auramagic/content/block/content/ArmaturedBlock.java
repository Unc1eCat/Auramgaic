package mod.unclecat.uc_auramagic.content.block.content;

import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.util.helpers.LootTableHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTable;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArmaturedBlock extends ModBlock {
    public final Block drop;

    public ArmaturedBlock(String name, Properties properties, Block drop, @Nullable ItemGroup group, @Nullable Item.Properties itemProperties) {
        super(name, properties, group, itemProperties);
        this.drop = drop;
    }

    public ArmaturedBlock(String name, Properties properties, Block drop) {
        super(name, properties);
        this.drop = drop;
    }

    @Override
    public void onBlockHarvested(World w, BlockPos p, BlockState s, PlayerEntity player) {
        super.onBlockHarvested(w, p, s, player);
        w.setBlockState(p, Content.ARMATURE.getDefaultState());
    }

    @Override
    public void onPlayerDestroy(IWorld w, BlockPos p, BlockState p_176206_3_) {
        super.onPlayerDestroy(w, p, p_176206_3_);
        w.setBlockState(p, Content.ARMATURE.getDefaultState(), 3);
    }

    @Override
    public Map<ResourceLocation, LootTable> generateLootTables() {
        Map<ResourceLocation, LootTable> ret = new HashMap<ResourceLocation, LootTable>();
        ret.put(new ResourceLocation(getRegistryName().getNamespace(), "blocks/" + getRegistryName().getPath()), LootTableHelper.createStandardTableFromBlock(drop.getRegistryName().toString(), this));
        return ret;
    }
}
