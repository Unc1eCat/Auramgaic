package mod.unclecat.uc_auramagic.content.block;

import com.google.common.collect.Lists;
import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.datagen.providers.ItemModelsProvider;
import mod.unclecat.uc_auramagic.util.helpers.BlockHelper;
import mod.unclecat.uc_auramagic.util.helpers.LootTableHelper;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.*;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.storage.loot.LootTable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class ModSlabBlock extends ModBlock implements IWaterLoggable {
    public static final VoxelShape BOTTOM_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    public static final VoxelShape TOP_SHAPE = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public ModSlabBlock(String name, Properties properties, @Nullable ItemGroup group, @Nullable Item.Properties itemProperties) {
        super(name, properties, group, itemProperties);
    }

    public ModSlabBlock(String name, Properties properties) {
        super(name, properties);
    }

    @Nonnull
    @Override
    public List<IProperty<?>> getExternalProperties() {

        return Lists.newArrayList(
                SlabBlock.TYPE,
                BlockStateProperties.WATERLOGGED);
    }

    public boolean func_220074_n(BlockState p_220074_1_) {
        return p_220074_1_.get(SlabBlock.TYPE) != SlabType.DOUBLE;
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        SlabType lvt_5_1_ = (SlabType) p_220053_1_.get(SlabBlock.TYPE);
        switch (lvt_5_1_) {
            case DOUBLE:
                return VoxelShapes.fullCube();
            case TOP:
                return TOP_SHAPE;
            default:
                return BOTTOM_SHAPE;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockPos lvt_2_1_ = p_196258_1_.getPos();
        BlockState lvt_3_1_ = p_196258_1_.getWorld().getBlockState(lvt_2_1_);
        if (lvt_3_1_.getBlock() == this) {
            return (BlockState) ((BlockState) lvt_3_1_.with(SlabBlock.TYPE, SlabType.DOUBLE)).with(SlabBlock.WATERLOGGED, false);
        } else {
            IFluidState lvt_4_1_ = p_196258_1_.getWorld().getFluidState(lvt_2_1_);
            BlockState lvt_5_1_ = (BlockState) ((BlockState) this.getDefaultState().with(SlabBlock.TYPE, SlabType.BOTTOM)).with(SlabBlock.WATERLOGGED, lvt_4_1_.getFluid() == Fluids.WATER);
            Direction lvt_6_1_ = p_196258_1_.getFace();
            return lvt_6_1_ != Direction.DOWN && (lvt_6_1_ == Direction.UP || !(p_196258_1_.getHitVec().y - (double) lvt_2_1_.getY() > 0.5D)) ? lvt_5_1_ : (BlockState) lvt_5_1_.with(SlabBlock.TYPE, SlabType.TOP);
        }
    }

    public boolean isReplaceable(BlockState p_196253_1_, BlockItemUseContext p_196253_2_) {
        ItemStack lvt_3_1_ = p_196253_2_.getItem();
        SlabType lvt_4_1_ = (SlabType) p_196253_1_.get(SlabBlock.TYPE);
        if (lvt_4_1_ != SlabType.DOUBLE && lvt_3_1_.getItem() == this.asItem()) {
            if (p_196253_2_.replacingClickedOnBlock()) {
                boolean lvt_5_1_ = p_196253_2_.getHitVec().y - (double) p_196253_2_.getPos().getY() > 0.5D;
                Direction lvt_6_1_ = p_196253_2_.getFace();
                if (lvt_4_1_ == SlabType.BOTTOM) {
                    return lvt_6_1_ == Direction.UP || lvt_5_1_ && lvt_6_1_.getAxis().isHorizontal();
                } else {
                    return lvt_6_1_ == Direction.DOWN || !lvt_5_1_ && lvt_6_1_.getAxis().isHorizontal();
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public IFluidState getFluidState(BlockState p_204507_1_) {
        return (Boolean) p_204507_1_.get(SlabBlock.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    public boolean receiveFluid(IWorld p_204509_1_, BlockPos p_204509_2_, BlockState p_204509_3_, IFluidState p_204509_4_) {
        return p_204509_3_.get(SlabBlock.TYPE) != SlabType.DOUBLE ? IWaterLoggable.super.receiveFluid(p_204509_1_, p_204509_2_, p_204509_3_, p_204509_4_) : false;
    }

    public boolean canContainFluid(IBlockReader p_204510_1_, BlockPos p_204510_2_, BlockState p_204510_3_, Fluid p_204510_4_) {
        return p_204510_3_.get(SlabBlock.TYPE) != SlabType.DOUBLE ? IWaterLoggable.super.canContainFluid(p_204510_1_, p_204510_2_, p_204510_3_, p_204510_4_) : false;
    }

    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if ((Boolean) p_196271_1_.get(SlabBlock.WATERLOGGED)) {
            p_196271_4_.getPendingFluidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickRate(p_196271_4_));
        }

        return super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    public boolean allowsMovement(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        switch (p_196266_4_) {
            case LAND:
                return false;
            case WATER:
                return p_196266_2_.getFluidState(p_196266_3_).isTagged(FluidTags.WATER);
            case AIR:
                return false;
            default:
                return false;
        }
    }
}
