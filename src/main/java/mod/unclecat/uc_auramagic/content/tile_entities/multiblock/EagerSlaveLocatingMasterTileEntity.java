package mod.unclecat.uc_auramagic.content.tile_entities.multiblock;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/// Doesn't work :(
@Deprecated
public abstract class EagerSlaveLocatingMasterTileEntity extends LazySlaveLocatingMasterTileEntity {

    protected List<SlaveTileEntity> slaves = new ArrayList<SlaveTileEntity>();

    public EagerSlaveLocatingMasterTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public EagerSlaveLocatingMasterTileEntity(TileEntityType<?> type, List<AxisAlignedBB> slaveSpotsPosition) {
        super(type, slaveSpotsPosition);

        for (AxisAlignedBB i : slaveSpotsPosition)
        {
            forEachSlaveInSpot(i, slaves::add);
        }
    }

    @Override
    public void readData(CompoundNBT nbt) {
        super.readData(nbt);

        for (AxisAlignedBB i : slaveSpotsPosition)
        {
            forEachSlaveInSpot(i, slaves::add);
        }
    }

    @Override
    public void forEachSlave(Consumer<SlaveTileEntity> consumer) {
        slaves.forEach(consumer);
    }
}
