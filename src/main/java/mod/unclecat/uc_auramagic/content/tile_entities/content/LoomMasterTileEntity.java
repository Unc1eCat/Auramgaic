package mod.unclecat.uc_auramagic.content.tile_entities.content;

import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.block.content.LoomBlock;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.*;
import mod.unclecat.uc_auramagic.util.helpers.FXHelper;
import mod.unclecat.uc_auramagic.util.helpers.NBTHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class LoomMasterTileEntity extends DisassemblableMasterTileEntity {
    public static TileEntityType<LoomMasterTileEntity> TYPE;

    public Direction facing;

    public LoomMasterTileEntity() {
        super(TYPE);
    }

    public LoomMasterTileEntity(List<AxisAlignedBB> slaveSpots, Direction facing) {
        super(TYPE, slaveSpots);
        this.facing = facing;
    }

    @Override
    public void readData(CompoundNBT nbt) {
        super.readData(nbt);
        facing = Direction.values()[nbt.getInt("facing")];
    }

    @Override
    public CompoundNBT writeData(CompoundNBT nbt) {
        super.writeData(nbt);
        nbt.putInt("facing", facing.ordinal());
        return nbt;
    }

    @Override
    public void onPartDestroyed(MultiblockTileEntity part) {
        disassemble();
    }

    @Override
    public void onPartsNeighbourChanged(MultiblockTileEntity part, BlockPos fromPos) {

    }

    @Override
    public void onDisassembled() {
        BlockPos p = new BlockPos(pos);

        if (world.isRemote) {
            forEachSlave(i ->
                    FXHelper.spawnComplexParticles(new BlockParticleData(ParticleTypes.BLOCK, i.getBlockState()), 15, 25, i.getPos().getX(), 1.0, i.getPos().getY(), 1.0, i.getPos().getZ(), 1.0, 0.8, 1.1, 0.8, 1.1, 0.8, 1.1, world, false));
        } else {
            world.setBlockState(p, Content.WOODEN_TABLE.getDefaultState());
            ((TableTileEntity) world.getTileEntity(p)).setItemStacksRotated(facing, new ItemStack(Blocks.OAK_PLANKS), new ItemStack(Blocks.OAK_PLANKS), new ItemStack(Blocks.OAK_PLANKS),
                    new ItemStack(Blocks.OAK_PLANKS), ItemStack.EMPTY, new ItemStack(Blocks.OAK_PLANKS),
                    new ItemStack(Blocks.OAK_PLANKS), ItemStack.EMPTY, new ItemStack(Blocks.OAK_PLANKS));
            world.removeBlock(p = p.up(), true);
            world.removeBlock(p = p.offset(facing.getOpposite()), true);
            world.setBlockState(p = p.down(), Content.WOODEN_TABLE.getDefaultState());
            ((TableTileEntity) world.getTileEntity(p)).setItemStacksRotated(facing, new ItemStack(Items.STICK), ItemStack.EMPTY, new ItemStack(Items.STICK),
                    new ItemStack(Items.STICK), ItemStack.EMPTY, new ItemStack(Items.STICK),
                    new ItemStack(Items.STICK), new ItemStack(Items.STICK), new ItemStack(Items.STICK));
        }
    }
}
