package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import java.util.List;
import java.util.function.Consumer;

import mod.unclecat.uc_auramagic.util.helpers.NBTHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBTType;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public abstract class SlaveStoringMasterTileEntity extends MasterTileEntity implements ISlaveProvidingTileEntity {

    /// This list represents a 3D area within which all slaves of this master are situated, and none of them can be not within the area.
    /// The area can cover blocks other than slaves and the master
    /// A slave is not allowed to be in two spots simultaneously
    protected List<AxisAlignedBB> slaveSpotsPosition;

    public SlaveStoringMasterTileEntity(TileEntityType<?> type) {
        super(type);
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

        for (int i = 0; i < list.size(); i += 2) {
            slaveSpotsPosition.add(new AxisAlignedBB(new BlockPos(NBTHelper.nbtToIntCoordinates(list.getList(i))), new BlockPos(NBTHelper.nbtToIntCoordinates(list.getList(i + 1)))));
        }
    }


    @Override
    public <T extends SlaveTileEntity> void forEachSlave(Consumer<T> consumer) {
        for (AxisAlignedBB i : slaveSpotsPosition) forEachSlaveInSpot(i, consumer);
    }


    // TODO!!!!!!: Make it find all slaves in a spot only by one random pos from spot
    @SuppressWarnings("unchecked")
    public <T extends SlaveTileEntity> void forEachSlaveInSpot(AxisAlignedBB spot, Consumer<T> consumer) {
        BlockPos pos = new BlockPos(spot.minX, spot.minY, spot.minZ);
        BlockPos endPos = new BlockPos(spot.maxX, spot.maxY, spot.maxZ);

        for (; pos.getX() <= endPos.getX(); pos.add(1, 0, 0)) {
            for (; pos.getY() <= endPos.getY(); pos.add(0, 1, 0)) {
                for (; pos.getZ() <= endPos.getZ(); pos.add(0, 0, 1)) {
                    TileEntity te = world.getTileEntity(pos);

                    if (te != null && te instanceof SlaveTileEntity && this.isItsSlave((SlaveTileEntity) te)) {
                        consumer.accept((T) te);
                    }
                }
                pos = new BlockPos(pos.getX(), pos.getY(), spot.minZ);
            }
            pos = new BlockPos(pos.getX(), spot.minX, spot.minZ);
        }
    }
}
