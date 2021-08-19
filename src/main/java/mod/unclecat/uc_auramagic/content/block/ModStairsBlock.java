package mod.unclecat.uc_auramagic.content.block;

import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

// TODO: Extend StairsBlock instead and copy ModBlock instead implementing the interface you will extract from ModBlock
public class ModStairsBlock extends ModBlock implements IWaterLoggable {
    public ModStairsBlock(String name, Properties properties, @Nullable ItemGroup group, @Nullable Item.Properties itemProperties, Block modelBlock, @Nullable BlockState modelState) {
        super(name, properties, group, itemProperties);
        this.modelBlock = modelBlock;
        this.modelState = modelState == null ? modelBlock.getDefaultState() : modelState;
    }

    public ModStairsBlock(String name, Properties properties, Block modelBlock, @Nullable BlockState modelState) {
        super(name, properties);
        this.modelBlock = modelBlock;
        this.modelState = modelState == null ? modelBlock.getDefaultState() : modelState;
    }

    public static final VoxelShape AABB_SLAB_TOP;
    public static final VoxelShape AABB_SLAB_BOTTOM;
    public static final VoxelShape NWD_CORNER;
    public static final VoxelShape SWD_CORNER;
    public static final VoxelShape NWU_CORNER;
    public static final VoxelShape SWU_CORNER;
    public static final VoxelShape NED_CORNER;
    public static final VoxelShape SED_CORNER;
    public static final VoxelShape NEU_CORNER;
    public static final VoxelShape SEU_CORNER;
    public static final VoxelShape[] SLAB_TOP_SHAPES;
    public static final VoxelShape[] SLAB_BOTTOM_SHAPES;
    public static final int[] field_196522_K;
    public final Block modelBlock;
    public final BlockState modelState;

    public static VoxelShape[] makeShapes(VoxelShape p_199779_0_, VoxelShape p_199779_1_, VoxelShape p_199779_2_, VoxelShape p_199779_3_, VoxelShape p_199779_4_) {
        return (VoxelShape[]) IntStream.range(0, 16).mapToObj((p_lambda$makeShapes$0_5_) -> {
            return combineShapes(p_lambda$makeShapes$0_5_, p_199779_0_, p_199779_1_, p_199779_2_, p_199779_3_, p_199779_4_);
        }).toArray((p_lambda$makeShapes$1_0_) -> {
            return new VoxelShape[p_lambda$makeShapes$1_0_];
        });
    }

    public static VoxelShape combineShapes(int p_199781_0_, VoxelShape p_199781_1_, VoxelShape p_199781_2_, VoxelShape p_199781_3_, VoxelShape p_199781_4_, VoxelShape p_199781_5_) {
        VoxelShape voxelshape = p_199781_1_;
        if ((p_199781_0_ & 1) != 0) {
            voxelshape = VoxelShapes.or(p_199781_1_, p_199781_2_);
        }

        if ((p_199781_0_ & 2) != 0) {
            voxelshape = VoxelShapes.or(voxelshape, p_199781_3_);
        }

        if ((p_199781_0_ & 4) != 0) {
            voxelshape = VoxelShapes.or(voxelshape, p_199781_4_);
        }

        if ((p_199781_0_ & 8) != 0) {
            voxelshape = VoxelShapes.or(voxelshape, p_199781_5_);
        }

        return voxelshape;
    }

    @Nonnull
    @Override
    public List<IProperty<?>> getExternalProperties() {
        return Lists.newArrayList(
                StairsBlock.FACING,
                StairsBlock.HALF,
                StairsBlock.SHAPE,
                StairsBlock.WATERLOGGED);
    }

    public boolean func_220074_n(BlockState p_220074_1_) {
        return true;
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return (p_220053_1_.get(StairsBlock.HALF) == Half.TOP ? SLAB_TOP_SHAPES : SLAB_BOTTOM_SHAPES)[field_196522_K[this.func_196511_x(p_220053_1_)]];
    }

    private int func_196511_x(BlockState p_196511_1_) {
        return ((StairsShape) p_196511_1_.get(StairsBlock.SHAPE)).ordinal() * 4 + ((Direction) p_196511_1_.get(StairsBlock.FACING)).getHorizontalIndex();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
        this.modelBlock.animateTick(p_180655_1_, p_180655_2_, p_180655_3_, p_180655_4_);
    }

    public void onBlockClicked(BlockState p_196270_1_, World p_196270_2_, BlockPos p_196270_3_, PlayerEntity p_196270_4_) {
        this.modelState.onBlockClicked(p_196270_2_, p_196270_3_, p_196270_4_);
    }

    public void onPlayerDestroy(IWorld p_176206_1_, BlockPos p_176206_2_, BlockState p_176206_3_) {
        this.modelBlock.onPlayerDestroy(p_176206_1_, p_176206_2_, p_176206_3_);
    }

    public float getExplosionResistance() {
        return this.modelBlock.getExplosionResistance();
    }

    public int tickRate(IWorldReader p_149738_1_) {
        return this.modelBlock.tickRate(p_149738_1_);
    }

    public void onBlockAdded(BlockState p_220082_1_, World p_220082_2_, BlockPos p_220082_3_, BlockState p_220082_4_, boolean p_220082_5_) {
        if (p_220082_1_.getBlock() != p_220082_1_.getBlock()) {
            this.modelState.neighborChanged(p_220082_2_, p_220082_3_, Blocks.AIR, p_220082_3_, false);
            this.modelBlock.onBlockAdded(this.modelState, p_220082_2_, p_220082_3_, p_220082_4_, false);
        }

    }

    public void onReplaced(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
        if (p_196243_1_.getBlock() != p_196243_4_.getBlock()) {
            this.modelState.onReplaced(p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
        }

    }

    public void onEntityWalk(World p_176199_1_, BlockPos p_176199_2_, Entity p_176199_3_) {
        this.modelBlock.onEntityWalk(p_176199_1_, p_176199_2_, p_176199_3_);
    }

    public void func_225534_a_(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        this.modelBlock.func_225534_a_(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
    }

    public ActionResultType func_225533_a_(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        return this.modelState.func_227031_a_(p_225533_2_, p_225533_4_, p_225533_5_, p_225533_6_);
    }

    public void onExplosionDestroy(World p_180652_1_, BlockPos p_180652_2_, Explosion p_180652_3_) {
        this.modelBlock.onExplosionDestroy(p_180652_1_, p_180652_2_, p_180652_3_);
    }

    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        Direction direction = p_196258_1_.getFace();
        BlockPos blockpos = p_196258_1_.getPos();
        IFluidState ifluidstate = p_196258_1_.getWorld().getFluidState(blockpos);
        BlockState blockstate = (BlockState) ((BlockState) ((BlockState) this.getDefaultState().with(StairsBlock.FACING, p_196258_1_.getPlacementHorizontalFacing())).with(StairsBlock.HALF, direction == Direction.DOWN || direction != Direction.UP && p_196258_1_.getHitVec().y - (double) blockpos.getY() > 0.5D ? Half.TOP : Half.BOTTOM)).with(StairsBlock.WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
        return (BlockState) blockstate.with(StairsBlock.SHAPE, getShapeProperty(blockstate, p_196258_1_.getWorld(), blockpos));
    }

    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if ((Boolean) p_196271_1_.get(StairsBlock.WATERLOGGED)) {
            p_196271_4_.getPendingFluidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickRate(p_196271_4_));
        }

        return p_196271_2_.getAxis().isHorizontal() ? (BlockState) p_196271_1_.with(StairsBlock.SHAPE, getShapeProperty(p_196271_1_, p_196271_4_, p_196271_5_)) : super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    private static StairsShape getShapeProperty(BlockState p_208064_0_, IBlockReader p_208064_1_, BlockPos p_208064_2_) {
        Direction direction = (Direction) p_208064_0_.get(StairsBlock.FACING);
        BlockState blockstate = p_208064_1_.getBlockState(p_208064_2_.offset(direction));
        if (isBlockStairs(blockstate) && p_208064_0_.get(StairsBlock.HALF) == blockstate.get(StairsBlock.HALF)) {
            Direction direction1 = (Direction) blockstate.get(StairsBlock.FACING);
            if (direction1.getAxis() != ((Direction) p_208064_0_.get(StairsBlock.FACING)).getAxis() && isDifferentStairs(p_208064_0_, p_208064_1_, p_208064_2_, direction1.getOpposite())) {
                if (direction1 == direction.rotateYCCW()) {
                    return StairsShape.OUTER_LEFT;
                }

                return StairsShape.OUTER_RIGHT;
            }
        }

        BlockState blockstate1 = p_208064_1_.getBlockState(p_208064_2_.offset(direction.getOpposite()));
        if (isBlockStairs(blockstate1) && p_208064_0_.get(StairsBlock.HALF) == blockstate1.get(StairsBlock.HALF)) {
            Direction direction2 = (Direction) blockstate1.get(StairsBlock.FACING);
            if (direction2.getAxis() != ((Direction) p_208064_0_.get(StairsBlock.FACING)).getAxis() && isDifferentStairs(p_208064_0_, p_208064_1_, p_208064_2_, direction2)) {
                if (direction2 == direction.rotateYCCW()) {
                    return StairsShape.INNER_LEFT;
                }

                return StairsShape.INNER_RIGHT;
            }
        }

        return StairsShape.STRAIGHT;
    }

    private static boolean isDifferentStairs(BlockState p_185704_0_, IBlockReader p_185704_1_, BlockPos p_185704_2_, Direction p_185704_3_) {
        BlockState blockstate = p_185704_1_.getBlockState(p_185704_2_.offset(p_185704_3_));
        return !isBlockStairs(blockstate) || blockstate.get(StairsBlock.FACING) != p_185704_0_.get(StairsBlock.FACING) || blockstate.get(StairsBlock.HALF) != p_185704_0_.get(StairsBlock.HALF);
    }

    public static boolean isBlockStairs(BlockState p_185709_0_) {
        return p_185709_0_.getBlock() instanceof ModStairsBlock || p_185709_0_.getBlock() instanceof StairsBlock;
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return (BlockState) p_185499_1_.with(StairsBlock.FACING, p_185499_2_.rotate((Direction) p_185499_1_.get(StairsBlock.FACING)));
    }

    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        Direction direction = (Direction) p_185471_1_.get(StairsBlock.FACING);
        StairsShape stairsshape = (StairsShape) p_185471_1_.get(StairsBlock.SHAPE);
        switch (p_185471_2_) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {
                    switch (stairsshape) {
                        case INNER_LEFT:
                            return (BlockState) p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(StairsBlock.SHAPE, StairsShape.INNER_RIGHT);
                        case INNER_RIGHT:
                            return (BlockState) p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(StairsBlock.SHAPE, StairsShape.INNER_LEFT);
                        case OUTER_LEFT:
                            return (BlockState) p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return (BlockState) p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT);
                        default:
                            return p_185471_1_.rotate(Rotation.CLOCKWISE_180);
                    }
                }
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {
                    switch (stairsshape) {
                        case INNER_LEFT:
                            return (BlockState) p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(StairsBlock.SHAPE, StairsShape.INNER_LEFT);
                        case INNER_RIGHT:
                            return (BlockState) p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(StairsBlock.SHAPE, StairsShape.INNER_RIGHT);
                        case OUTER_LEFT:
                            return (BlockState) p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return (BlockState) p_185471_1_.rotate(Rotation.CLOCKWISE_180).with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT);
                        case STRAIGHT:
                            return p_185471_1_.rotate(Rotation.CLOCKWISE_180);
                    }
                }
        }

        return super.mirror(p_185471_1_, p_185471_2_);
    }

    public IFluidState getFluidState(BlockState p_204507_1_) {
        return (Boolean) p_204507_1_.get(StairsBlock.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    public boolean allowsMovement(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }

    static {
        AABB_SLAB_TOP = ModSlabBlock.TOP_SHAPE;
        AABB_SLAB_BOTTOM = ModSlabBlock.BOTTOM_SHAPE;
        NWD_CORNER = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
        SWD_CORNER = Block.makeCuboidShape(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
        NWU_CORNER = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D);
        SWU_CORNER = Block.makeCuboidShape(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D);
        NED_CORNER = Block.makeCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
        SED_CORNER = Block.makeCuboidShape(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
        NEU_CORNER = Block.makeCuboidShape(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D);
        SEU_CORNER = Block.makeCuboidShape(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
        SLAB_TOP_SHAPES = makeShapes(AABB_SLAB_TOP, NWD_CORNER, NED_CORNER, SWD_CORNER, SED_CORNER);
        SLAB_BOTTOM_SHAPES = makeShapes(AABB_SLAB_BOTTOM, NWU_CORNER, NEU_CORNER, SWU_CORNER, SEU_CORNER);
        field_196522_K = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};
    }
}
