package mod.unclecat.uc_auramagic.content.block;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.MultiblockTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.JavaHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

// MAYBE: Make an abstraction for MB blocks with the part and the facing properties so they follow the same easily reusable pattern. As you should do as the one pretty book keeps repeating
// TODO: Block to tile entity relation system is wrecked
public abstract class MultiblockBlock extends ModBlock {
    public MultiblockBlock(String name, Properties properties, ItemGroup group,
                           net.minecraft.item.Item.Properties itemProperties) {
        super(name, properties, group, itemProperties);
    }

    public MultiblockBlock(String name, Properties properties) {
        super(name, properties);
    }

    /// WARNING!!! USE "world.setTileEntity" IN MB CREATOR
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return null;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    // TODO: Make the correct way to observe the destroy event (see the 3 overrides below)
//    @SuppressWarnings("unchecked")
//    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        try {
            super.onBlockHarvested(worldIn, pos, state, player);

            MultiblockTileEntity te = (MultiblockTileEntity) worldIn.getTileEntity(pos);

//            te.onDestroyed();
        } catch (Exception e) {
            Auramagic.LOG.error(String.format("Exception while receiving of harvest event in multiblock. State: {}", state));
            e.printStackTrace();
        }
    }

//    @Override
//    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosion) {
//        try {
//            super.onExplosionDestroy(worldIn, pos, explosion);
//
//            MultiblockTileEntity te = (MultiblockTileEntity) worldIn.getTileEntity(pos);
//
//            te.onDestroyed();
//        } catch (Exception e) {
//            Auramagic.LOG.error(String.format("Exception while receiving of destruction by explosion event in multiblock. State: {}", worldIn.getBlockState(pos)));
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState p_176206_3_) {
//        try {
//            super.onPlayerDestroy(worldIn, pos, p_176206_3_);
//
//            MultiblockTileEntity te = (MultiblockTileEntity) worldIn.getTileEntity(pos);
//
//            te.onDestroyed();
//        } catch (Exception e) {
//            Auramagic.LOG.error(String.format("Exception while receiving of destruction by player event in multiblock. State: {}", worldIn.getBlockState(pos)));
//            e.printStackTrace();
//        }
//    }

    // TODO: AAAAAAAAAAAAAAAARARAARARRRRRRRGGGHHHH MAKE THE ONDESTROY GO BEFORE BOTH THE TE AND THE STATE ARE GONE
    @Override
    public void onReplaced(BlockState p_196243_1_, World worldIn, BlockPos pos, BlockState state, boolean p_196243_5_) {
        try {
            MultiblockTileEntity te = (MultiblockTileEntity) worldIn.getTileEntity(pos);
//            te.onDestroyed();
        } catch (Exception e) {
            Auramagic.LOG.error(String.format("Exception while receiving of destruction by player event in multiblock. State: {}", worldIn.getBlockState(pos)));
            e.printStackTrace();
        }
        super.onReplaced(p_196243_1_, worldIn, pos, state, p_196243_5_);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
                                boolean isMoving) {
        try {
            MultiblockTileEntity te = (MultiblockTileEntity) worldIn.getTileEntity(pos);

            if (te == null) return;  // In case the multiblock is being removed. The parts may receive neighbour updates while the master is replacing the parts with something else

            te.onNeighbourChanged(pos);
        } catch (Exception e) {
            Auramagic.LOG.error(String.format("Exception while receiving of neighbour change event in multiblock. State: {}", state));
            e.printStackTrace();
        }
    }
}
