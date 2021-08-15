package mod.unclecat.uc_auramagic.content.block.content;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.content.TableTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.BlockHelper;
import mod.unclecat.uc_auramagic.util.helpers.JavaHelper;
import mod.unclecat.uc_auramagic.util.multiblock.HorizontalRotationlessMatcher;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockTable extends ModBlock {
//	public static BooleanProperty NORTH = BooleanProperty.create("north");
//	public static BooleanProperty EAST = BooleanProperty.create("east");
//	public static BooleanProperty SOUTH = BooleanProperty.create("south");
//	public static BooleanProperty WEST = BooleanProperty.create("west");

    VoxelShape SHAPE = BlockHelper.mix(
            0, 13, 0, 16, 16, 16,
            2, 0, 2, 4, 13, 4,
            12, 0, 2, 14, 13, 4,
            2, 0, 12, 4, 13, 14,
            12, 0, 12, 14, 13, 14);


    public BlockTable(String name, Properties properties) {
        super(name, properties.hardnessAndResistance(3.0f), ItemGroupAurmagic.INSTANCE, null);
    }


    public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {
        TableTileEntity te = (TableTileEntity) world.getTileEntity(pos);

        if (rayTrace.getHitVec().y - pos.getY() < 0.999) return ActionResultType.PASS;

        int index = getIndexClicked(rayTrace.getHitVec().x - pos.getX(), rayTrace.getHitVec().z - pos.getZ());

        if (te.getStackInSlot(index).isEmpty()) {
            ItemStack playerStack = player.getHeldItemMainhand();
            te.setInventorySlotContents(index, playerStack.split(1));
            player.setHeldItem(Hand.MAIN_HAND, playerStack);
        } else {
            Block.spawnAsEntity(world, pos.add(0, 1, 0), te.getStackInSlot(index));
            te.setInventorySlotContents(index, ItemStack.EMPTY);
        }

        return ActionResultType.SUCCESS;
    }


    // TODO: Make it connected textures
//	@Override
//	public BlockState getStateForPlacement(BlockState state, Direction facing, BlockState state2, IWorld world,
//			BlockPos pos1, BlockPos pos2, Hand hand)
//	{
//		state = state.with(NORTH, world.getBlockState(pos1.north()).getBlock() != this);
//		state = state.with(WEST, world.getBlockState(pos1.west()).getBlock() != this);
//		state = state.with(EAST, world.getBlockState(pos1.east()).getBlock() != this);
//		state = state.with(SOUTH, world.getBlockState(pos1.south()).getBlock() != this);		
//		
//		return state;
//	}

//	@Override
//	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
//			boolean isMoving)
//	{
//		if (worldIn.getBlockState(fromPos).getBlock() == this) worldIn.setBlockState(pos, getStateForPlacement(state, null, null, worldIn, pos, null, null));
//	}

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        ((TableTileEntity) worldIn.getTileEntity(pos)).spawnDrop(pos);
    }

    @Override
    public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
        ((TableTileEntity) world.getTileEntity(pos)).spawnDrop(pos);
        super.onBlockExploded(state, world, pos, explosion);
    }

    @Override
    public Set<Class<? extends ModTileEntity>> getTileEntityClasses() {
        return new HashSet<Class<? extends ModTileEntity>>() {{
            add(TableTileEntity.class);
        }};
    }

    public static int getIndexClicked(double x, double y) {
        int slotX = (int) (x / 0.33333);
        int slotY = (int) (y / 0.33333);

        return slotX + slotY * 3;
    }


    public static boolean doesItemsMatchByItemShaped(World world, BlockPos pos, Direction direction, Item... requiredItems) {
        TableTileEntity te = (TableTileEntity) world.getTileEntity(pos);

        List<List<Item>> requiredItemsList = HorizontalRotationlessMatcher.rotateTwoDimListOnTopOfBlock(JavaHelper.asTwoDimList(3, requiredItems), direction);

        System.out.println(requiredItemsList.toString());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (te.getStackInSlot(i * 3 + j).getItem() != requiredItemsList.get(i).get(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean doesItemsMatchByPredicateShaped(World world, BlockPos pos, Direction direction, Predicate<ItemStack>... requiredItems) {
        TableTileEntity te = (TableTileEntity) world.getTileEntity(pos);
        boolean ret = true;

        List<List<Predicate<ItemStack>>> requiredItemsList = HorizontalRotationlessMatcher.rotateTwoDimListOnTopOfBlock(JavaHelper.asTwoDimList(3, requiredItems), direction);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!requiredItemsList.get(i).get(j).test(te.getStackInSlot(i * 3 + j))) {
                    ret = false;
                    break;
                }
            }
        }

        return ret;
    }
}





