package mod.unclecat.uc_auramagic.content.block;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.multiblock.MultiblockTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.JavaHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
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
    // TODO: AAAAAAAAAAAAAAAARARAARARRRRRRRGGGHHHH MAKE THE ONDESTROY GO BEFORE BOTH THE TE AND THE STATE ARE GONE

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
