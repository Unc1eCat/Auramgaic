package mod.unclecat.uc_auramagic.content.block;

import com.google.common.collect.Lists;
import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.IMultiblockTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.MasterTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.SlaveTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.JavaHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

// TODO: Block to tile entity relation system is wrecked
public abstract class MultiblockBlock extends ModBlock {
    public MultiblockBlock(String name, Properties properties, ItemGroup group,
                           net.minecraft.item.Item.Properties itemProperties) {
        super(name, properties, group, itemProperties);
    }

    public MultiblockBlock(String name, Properties properties) {
        super(name, properties);
    }


    @Override
    public Set<Class<? extends ModTileEntity>> getTileEntityClasses() {
        return JavaHelper.newHashSet(getMasterTileEntityClass(), getSlaveTileEntityClass());
    }

    public abstract Class<? extends MasterTileEntity> getMasterTileEntityClass();

    public abstract Class<? extends SlaveTileEntity> getSlaveTileEntityClass();

    public abstract boolean willCreateMaster(BlockState state, IBlockReader world);

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        try {
            if (willCreateMaster(state, world)) {
                return ((TileEntityType<? extends TileEntity>) getMasterTileEntityClass().getDeclaredField("TYPE").get(null)).create();

            } else {
                return ((TileEntityType<? extends TileEntity>) getSlaveTileEntityClass().getDeclaredField("TYPE").get(null)).create();

            }
        } catch (Exception e) {
            Auramagic.LOG.error("Could not create tile entity for block " + this.toString());
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Make the correct way to observe the destroy event (see 3 overrides below)
    @SuppressWarnings("unchecked")
    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);

        IMultiblockTileEntity te = (IMultiblockTileEntity) worldIn.getTileEntity(pos);

        te.onDestroyed();
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosion) {
        super.onExplosionDestroy(worldIn, pos, explosion);

        IMultiblockTileEntity te = (IMultiblockTileEntity) worldIn.getTileEntity(pos);

        te.onDestroyed();
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState p_176206_3_) {
        super.onPlayerDestroy(worldIn, pos, p_176206_3_);

        IMultiblockTileEntity te = (IMultiblockTileEntity) worldIn.getTileEntity(pos);

        te.onDestroyed();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
                                boolean isMoving) {
        IMultiblockTileEntity te = (IMultiblockTileEntity) worldIn.getTileEntity(fromPos);

        te.onNeighbourChanged(fromPos);
    }
}
