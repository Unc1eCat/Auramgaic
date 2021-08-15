package mod.unclecat.uc_auramagic.content.tile_entities.content;

import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.SlaveTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class LoomSlaveTileEntity extends SlaveTileEntity {
    public static TileEntityType<LoomSlaveTileEntity> TYPE;

    public LoomSlaveTileEntity()
    {
        super(TYPE);
    }


}
