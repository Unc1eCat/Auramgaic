package mod.unclecat.uc_auramagic.content.block.content;

import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.content.TableTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.BlockHelper;
import mod.unclecat.uc_auramagic.util.helpers.JavaHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class ArmatureBlock extends ModBlock {
//	public static BooleanProperty NORTH = BooleanProperty.create("north");
//	public static BooleanProperty EAST = BooleanProperty.create("east");
//	public static BooleanProperty SOUTH = BooleanProperty.create("south");
//	public static BooleanProperty WEST = BooleanProperty.create("west");

    public static final VoxelShape SHAPE = BlockHelper.mix(
            0, 5, 4, 16, 11, 12, // X
            4, 0, 4, 12, 16, 12, // Y
            4, 5, 0, 12, 11, 16 // Z
            );


    public ArmatureBlock() {
        super("armature", Block.Properties.from(Blocks.IRON_BARS), ItemGroupAurmagic.INSTANCE, null);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }
}





