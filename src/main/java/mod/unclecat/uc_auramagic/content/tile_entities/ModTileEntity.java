package mod.unclecat.uc_auramagic.content.tile_entities;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Resource;

public abstract class ModTileEntity extends TileEntity {
    public ModTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public abstract CompoundNBT writeData(CompoundNBT nbt);

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
