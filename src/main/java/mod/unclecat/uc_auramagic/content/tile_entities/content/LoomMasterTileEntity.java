package mod.unclecat.uc_auramagic.content.tile_entities.content;

import jdk.nashorn.internal.ir.Block;
import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.block.content.BlockTable;
import mod.unclecat.uc_auramagic.content.block.content.LoomBlock;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.SlaveStoringMasterTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.SlaveTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.FXHelper;
import mod.unclecat.uc_auramagic.util.helpers.JavaHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class LoomMasterTileEntity extends SlaveStoringMasterTileEntity {
    public static TileEntityType<LoomMasterTileEntity> TYPE;

    public LoomMasterTileEntity() {
        super(TYPE);
    }

    @Override
    public void onDestroyed() {
        onRemoval();
    }

    @Override
    public void onNeighbourChanged(BlockPos fromPos) {

    }

    @Override
    public void onRemoval() {
        BlockPos p = new BlockPos(pos);
        Direction f = getBlockState().get(LoomBlock.FACING);

        if (world.isRemote) {
            forEachSlave(i ->
                    FXHelper.spawnComplexParticles(new BlockParticleData(ParticleTypes.BLOCK, i.getBlockState()), 15, 25, i.getPos().getX(), 1.0, i.getPos().getY(), 1.0, i.getPos().getZ(), 1.0, 0.8, 1.1, 0.8, 1.1, 0.8, 1.1, world, false));
        } else {
            world.setBlockState(p, Content.WOODEN_TABLE.getDefaultState());
            ((TableTileEntity) world.getTileEntity(p)).setItemStacksRotated(f, new ItemStack(Blocks.OAK_WOOD), new ItemStack(Blocks.OAK_WOOD), new ItemStack(Blocks.OAK_WOOD),
                    new ItemStack(Blocks.OAK_WOOD), ItemStack.EMPTY, new ItemStack(Blocks.OAK_WOOD),
                    new ItemStack(Blocks.OAK_WOOD), ItemStack.EMPTY, new ItemStack(Blocks.OAK_WOOD));
        }
    }

    @Override
    public void onSlaveDestroyed(SlaveTileEntity slave) {
        onRemoval();
    }

    @Override
    public void onSlavesNeighbourChanged(SlaveTileEntity slave) {

    }
}
