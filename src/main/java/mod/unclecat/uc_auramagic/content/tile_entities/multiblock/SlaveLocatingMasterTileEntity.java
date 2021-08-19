package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.NBTHelper;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public abstract class SlaveLocatingMasterTileEntity extends MasterTileEntity {

    /// This list represents a 3D area within which all slaves of this master are situated, and none of them can be not within the area.
    /// The area can cover blocks other than slaves and the master
    /// A slave is not allowed to be in two spots simultaneously
    protected List<AxisAlignedBB> slaveSpotsPosition;

    public SlaveLocatingMasterTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public SlaveLocatingMasterTileEntity(TileEntityType<?> type, List<AxisAlignedBB> slaveSpotsPosition) {
        super(type);
        this.slaveSpotsPosition = slaveSpotsPosition;
        this.slaveSpotsPosition = slaveSpotsPosition;
    }

    @Override
    public CompoundNBT writeData(CompoundNBT nbt) {
        ListNBT list = new ListNBT();

        for (AxisAlignedBB i : slaveSpotsPosition) {
            list.add(0, NBTHelper.coordinatesToNBT(new Vec3i(i.minX, i.minY, i.minZ)));
            list.add(0, NBTHelper.coordinatesToNBT(new Vec3i(i.maxX, i.maxY, i.maxZ)));
        }

        nbt.put("slaves", list);

        return nbt;
    }

    @Override
    public void readData(CompoundNBT nbt) {
        ListNBT list = (ListNBT) nbt.get("slaves");

        slaveSpotsPosition = new ArrayList<>();

        for (int i = 0; i < list.size(); i += 2) {
            slaveSpotsPosition.add(new AxisAlignedBB(new BlockPos(NBTHelper.nbtToIntCoordinates(list.getList(i))), new BlockPos(NBTHelper.nbtToIntCoordinates(list.getList(i + 1)))));
        }
    }

    @Override
    public boolean isInTheSameMultiblock(MultiblockTileEntity te) {
        return ((MasterLocatingSlaveTileEntity)te).masterPos.equals(pos);
    }

    @Override
    public void forEachSlave(Consumer<SlaveTileEntity> consumer) {
        for (AxisAlignedBB i : slaveSpotsPosition) forEachSlaveInSpot(i, consumer);
    }

    // TODO!!!!!!: Make it find all slaves in a spot only by one random pos from spot
    public void forEachSlaveInSpot(AxisAlignedBB spot, Consumer<SlaveTileEntity> consumer) {
        BlockPos pos = new BlockPos(spot.minX, spot.minY, spot.minZ);
        BlockPos endPos = new BlockPos(spot.maxX, spot.maxY, spot.maxZ);

        for (; pos.getX() <= endPos.getX(); pos = pos.add(1, 0, 0)) {
            for (; pos.getY() <= endPos.getY(); pos = pos.add(0, 1, 0)) {
                for (; pos.getZ() <= endPos.getZ(); pos = pos.add(0, 0, 1)) {
                    TileEntity te = world.getTileEntity(pos);

                    if (te != null && te instanceof SlaveTileEntity && this.isInTheSameMultiblock((MultiblockTileEntity) te)) {
                        consumer.accept((SlaveTileEntity) te);
                    }
                }
                pos = new BlockPos(pos.getX(), pos.getY(), spot.minZ);
            }
            pos = new BlockPos(pos.getX(), spot.minX, spot.minZ);
        }
    }
}
