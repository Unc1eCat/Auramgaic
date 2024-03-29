package mod.unclecat.uc_auramagic.content.block.content;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.block.MultiblockBlock;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.content.LoomMasterTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.DisassemblableMasterTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.DisassemblableSlaveTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.MasterLocatingSlaveTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.MultiblockTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.BlockHelper;
import mod.unclecat.uc_auramagic.util.helpers.JavaHelper;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTable;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

public class LoomBlock extends MultiblockBlock {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
    public static final IntegerProperty PART = IntegerProperty.create("part", 0, 3);

    public static VoxelShape[] SHAPES = {
            BlockHelper.mix(
                    1, 0, 1, 4, 2, 4, // leg
                    12, 0, 1, 15, 2, 4, // leg
                    0, 2, 0, 16, 16, 14, // main
                    0, 13, 14, 16, 16, 16 // tabletop
            ),
            BlockHelper.mix(
                    0, 13, 0, 16, 16, 16, // tabletop
                    3, 0, 11, 5, 14, 13, // leg
                    11, 0, 11, 13, 14, 13 // leg
            ),
            BlockHelper.mix(
                    0, 0, 0, 16, 12, 14, // gearbox
                    0, 0, 14, 16, 2, 16 // separator stick
            ),
            BlockHelper.mix(
                    0, 0, 0, 16, 2, 16
            )
            // TODO: ...
    };

    public LoomBlock() {
        super("loom_block", Block.Properties.create(Material.MISCELLANEOUS).sound(SoundType.SCAFFOLDING).hardnessAndResistance(2f, 4f));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return BlockHelper.rotateVoxelShape(state.get(FACING), SHAPES[state.get(PART)]);
    }


    @Override
    public void onBlockPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        super.onBlockPlacedBy(p_180633_1_, p_180633_2_, p_180633_3_, p_180633_4_, p_180633_5_);

//        try {
//            if (p_180633_3_.get(PART) != 0) return;
//            LoomMasterTileEntity te = (LoomMasterTileEntity) p_180633_1_.getTileEntity(p_180633_2_);
//            te.facing = p_180633_3_.get(FACING);
//        } catch (Exception e) {
//            Auramagic.LOG.error(String.format("Exception in onBlockPlacedBy in multiblock. State: {}", p_180633_3_));
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean isNormalCube(BlockState p_220081_1_, IBlockReader p_220081_2_, BlockPos p_220081_3_) {
        return false;
    }

    @Override
    public Set<Class<? extends ModTileEntity>> getTileEntityClasses() {
        return JavaHelper.newHashSet(DisassemblableSlaveTileEntity.class, LoomMasterTileEntity.class);
    }

    @Override
    public Map<ResourceLocation, LootTable> generateLootTables() {
        return null;
    }
}
