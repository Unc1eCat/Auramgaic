package mod.unclecat.uc_auramagic.content.tile_entities;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.Resource;

/// If some of subclasses of this are actually used in world(not abstract or such) and have this line in them:
/// public static TileEntityType<X> TYPE;
/// Where X is the name of the class this line is in
/// Than it will be set to the actual type of the TE. It's optional
public abstract class ModTileEntity extends TileEntity {
    public ModTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @OverridingMethodsMustInvokeSuper
    public abstract CompoundNBT writeData(CompoundNBT nbt);

    @OverridingMethodsMustInvokeSuper
    public abstract void readData(CompoundNBT nbt);

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return writeData(super.write(compound));
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        readData(compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        readData(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return writeData(super.getUpdateTag());
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, -999, writeData(new CompoundNBT()));
    }

    @SuppressWarnings("unchecked")
    public static <T extends ModTileEntity, R extends TileEntityRenderer<T>> Class<R> getTERClass(Class<T> te) {
        Class<?>[] classes = te.getDeclaredClasses();

        for (Class<?> i : classes) {
            if (i.getSimpleName().contentEquals("ThisTER") && TileEntityRenderer.class.isAssignableFrom(i)) {
                return (Class<R>) i;
            }
        }

        return null;
    }
}
