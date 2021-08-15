package mod.unclecat.uc_auramagic.content.multiblock.creators.instrument_work;


import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.block.content.BlockTable;
import mod.unclecat.uc_auramagic.content.block.content.LoomBlock;
import mod.unclecat.uc_auramagic.content.multiblock.IMultiblockCreationTrigger;
import mod.unclecat.uc_auramagic.content.multiblock.IMultiblockCreator;
import mod.unclecat.uc_auramagic.util.multiblock.HorizontalRotationlessMatcher;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LoomCreator implements IMultiblockCreator {
    @Override
    public boolean handleCreationTrigger(IMultiblockCreationTrigger trigger) {
        if (!(trigger instanceof InstrumentWorkMultiblockCreationTrigger)) return false;

        InstrumentWorkMultiblockCreationTrigger t = ((InstrumentWorkMultiblockCreationTrigger) trigger);

        if (t.matchesClickedSequenceByItem(Content.HAMMER, Content.HAMMER, Content.HAMMER)) {
            HorizontalRotationlessMatcher matcher = new HorizontalRotationlessMatcher(t.clickedPos, t.player.world);
            World w = t.player.world;

            matcher
                    .matchBlock(Content.WOODEN_TABLE, Content.WOODEN_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK).north().matchBlock(Content.WOODEN_TABLE)
                    .south().match((pos, dir) -> {
                if (w.getBlockState(pos).getBlock() == Content.WOODEN_TABLE) return BlockTable.doesItemsMatchByItemShaped(w, pos, dir,
                        Items.STICK, Items.AIR, Items.STICK,
                        Items.STICK, Items.AIR, Items.STICK,
                        Items.STICK, Items.STICK, Items.STICK);
                else return true;
            })
                    .north().match((pos, dir) -> {
                return BlockTable.doesItemsMatchByItemShaped(w, pos, dir,
                        Items.OAK_PLANKS, Items.OAK_PLANKS, Items.OAK_PLANKS,
                        Items.OAK_PLANKS, Items.AIR, Items.OAK_PLANKS,
                        Items.OAK_PLANKS, Items.AIR, Items.OAK_PLANKS);
            });

            if (matcher.getMatches()) {
                if (t.equalsClickedSequenceByItem(Content.HAMMER, Content.HAMMER, Content.HAMMER)) {
                    if (!w.isRemote) {
                        Direction direction = matcher.getMatchedDirections().iterator().next();
                        BlockPos p = t.clickedPos;

                        w.setBlockState(p, Content.LOOM_BLOCK.getDefaultState().with(LoomBlock.PART, 1).with(LoomBlock.FACING, direction));
                        w.setBlockState(p = p.up(), Content.LOOM_BLOCK.getDefaultState().with(LoomBlock.PART, 3).with(LoomBlock.FACING, direction));
                        w.setBlockState(p = p.offset(direction), Content.LOOM_BLOCK.getDefaultState().with(LoomBlock.PART, 2).with(LoomBlock.FACING, direction));
                        w.setBlockState(p = p.down(), Content.LOOM_BLOCK.getDefaultState().with(LoomBlock.PART, 0).with(LoomBlock.FACING, direction));
                    }
                }

                t.wrong = false;
                return true;
            }
        }

        return false;
    }
}
