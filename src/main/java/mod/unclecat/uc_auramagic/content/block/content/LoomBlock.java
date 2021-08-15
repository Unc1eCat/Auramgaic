package mod.unclecat.uc_auramagic.content.block.content;

import mod.unclecat.uc_auramagic.content.block.MultiblockBlock;
import mod.unclecat.uc_auramagic.content.tile_entities.content.LoomMasterTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.content.LoomSlaveTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.MasterTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.SlaveTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.LootTable;

import java.util.Map;

public class LoomBlock extends MultiblockBlock {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
    public static final IntegerProperty PART = IntegerProperty.create("part", 0, 3);

    public LoomBlock() {
        super("loom_block", Block.Properties.create(Material.MISCELLANEOUS).sound(SoundType.SCAFFOLDING).hardnessAndResistance(2f, 4f));
    }

    @Override
    public Class<? extends MasterTileEntity> getMasterTileEntityClass() {
        return LoomMasterTileEntity.class;
    }

    @Override
    public boolean isNormalCube(BlockState p_220081_1_, IBlockReader p_220081_2_, BlockPos p_220081_3_) {
        return false;
    }

    @Override
    public Class<? extends SlaveTileEntity> getSlaveTileEntityClass() {
        return LoomSlaveTileEntity.class;
    }

    @Override
    public boolean willCreateMaster(BlockState state, IBlockReader world) {
        return state.get(PART) == 0;
    }

    @Override
    public Map<ResourceLocation, LootTable> generateLootTables() {
        return null;
    }
}
