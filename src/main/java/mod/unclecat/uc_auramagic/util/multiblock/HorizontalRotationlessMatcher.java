package mod.unclecat.uc_auramagic.util.multiblock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// You still must click on a specific block of the structure but not any block of the structure
public class HorizontalRotationlessMatcher extends HorizontalRotationlessPosition<HorizontalRotationlessMatcher> {
    protected World world;
    protected boolean northMatches = true;
    protected boolean southMatches = true;
    protected boolean eastMatches = true;
    protected boolean westMatches = true;

    public HorizontalRotationlessMatcher(BlockPos beginning, World world) {
        super(beginning);
        this.world = world;
    }

    public HorizontalRotationlessMatcher(HorizontalRotationlessMatcher copyFrom) {
        super(copyFrom);
        world = copyFrom.getWorld();
        northMatches = copyFrom.northMatches;
        southMatches = copyFrom.southMatches;
        westMatches = copyFrom.westMatches;
        eastMatches = copyFrom.eastMatches;
    }


    public HorizontalRotationlessMatcher merge(HorizontalRotationlessMatcher mergeWith) {
        northMatches = northMatches && mergeWith.northMatches;
        southMatches = southMatches && mergeWith.southMatches;
        westMatches = westMatches && mergeWith.westMatches;
        eastMatches = eastMatches && mergeWith.eastMatches;

        return this;
    }


    public HorizontalRotationlessMatcher match(BiPredicate<BlockPos, Direction> predicate) {
        if (northMatches) northMatches = predicate.test(currentPosNorth, Direction.NORTH);
        if (southMatches) southMatches = predicate.test(currentPosSouth, Direction.SOUTH);
        if (eastMatches) eastMatches = predicate.test(currentPosEast, Direction.EAST);
        if (westMatches) westMatches = predicate.test(currentPosWest, Direction.WEST);

        return this;
    }

    public HorizontalRotationlessMatcher match(Predicate<BlockPos> predicate) {
        if (northMatches) northMatches = predicate.test(currentPosNorth);
        if (southMatches) southMatches = predicate.test(currentPosSouth);
        if (eastMatches) eastMatches = predicate.test(currentPosEast);
        if (westMatches) westMatches = predicate.test(currentPosWest);

        return this;
    }

    public <M extends HorizontalRotationlessPosition<M>> HorizontalRotationlessMatcher matchAt(BlockPos offset, Predicate<BlockPos> predicate) {
        HorizontalRotationlessPosition<M> copy = new HorizontalRotationlessPosition<M>(this);

        copy.move(offset);

        if (northMatches) northMatches = predicate.test(copy.currentPosNorth);
        if (southMatches) southMatches = predicate.test(copy.currentPosSouth);
        if (eastMatches) eastMatches = predicate.test(copy.currentPosEast);
        if (westMatches) westMatches = predicate.test(copy.currentPosWest);

        return this;
    }

    public <M extends HorizontalRotationlessPosition<M>> HorizontalRotationlessMatcher matchAt(BlockPos offset, BiPredicate<BlockPos, Direction> predicate) {
        HorizontalRotationlessPosition<M> copy = new HorizontalRotationlessPosition<M>(this);

        copy.move(offset);

        if (northMatches) northMatches = predicate.test(copy.currentPosNorth, Direction.NORTH);
        if (southMatches) southMatches = predicate.test(copy.currentPosSouth, Direction.SOUTH);
        if (eastMatches) eastMatches = predicate.test(copy.currentPosEast, Direction.EAST);
        if (westMatches) westMatches = predicate.test(copy.currentPosWest, Direction.WEST);

        return this;
    }


    public HorizontalRotationlessMatcher matchBlock(Block block) {
        if (northMatches) northMatches = getWorld().getBlockState(currentPosNorth).getBlock() == block;
        if (southMatches) southMatches = getWorld().getBlockState(currentPosSouth).getBlock() == block;
        if (eastMatches) eastMatches = getWorld().getBlockState(currentPosEast).getBlock() == block;
        if (westMatches) westMatches = getWorld().getBlockState(currentPosWest).getBlock() == block;

        return this;
    }

    public HorizontalRotationlessMatcher matchBlock(Block... blocks) {
        boolean n = false, s = false, w = false, e = false;

        for (Block i : blocks) {
            if (!n) n = getWorld().getBlockState(currentPosNorth).getBlock() == i;
            if (!s) s = getWorld().getBlockState(currentPosSouth).getBlock() == i;
            if (!w) w = getWorld().getBlockState(currentPosWest).getBlock() == i;
            if (!e) e = getWorld().getBlockState(currentPosEast).getBlock() == i;
        }

        if (northMatches) northMatches = n;
        if (southMatches) southMatches = s;
        if (westMatches) westMatches = w;
        if (eastMatches) eastMatches = e;

        return this;
    }


    public boolean getNorthMatches() {
        return northMatches;
    }

    public HorizontalRotationlessMatcher setNorthMatches(boolean northMatches) {
        this.northMatches = northMatches;

        return this;
    }

    public boolean getSouthMatches() {
        return southMatches;
    }

    public HorizontalRotationlessMatcher setSouthMatches(boolean southMatches) {
        this.southMatches = southMatches;

        return this;
    }

    public boolean getEastMatches() {
        return eastMatches;
    }

    public HorizontalRotationlessMatcher setEastMatches(boolean eastMatches) {
        this.eastMatches = eastMatches;

        return this;
    }

    public boolean getWestMatches() {
        return westMatches;
    }

    public HorizontalRotationlessMatcher setWestMatches(boolean westMatches) {
        this.westMatches = westMatches;

        return this;
    }


    public HorizontalRotationlessMatcher setMatches(boolean val) {
        northMatches = southMatches = eastMatches = westMatches = val;

        return this;
    }

    public boolean getMatches() {
        return northMatches || southMatches || eastMatches || westMatches;
    }

    public Set<Direction> getMatchedDirections() {
        Set<Direction> ret = new HashSet<>();

        if (northMatches) {
            ret.add(Direction.NORTH);
        }
        if (southMatches) {
            ret.add(Direction.SOUTH);
        }
        if (westMatches) {
            ret.add(Direction.WEST);
        }
        if (eastMatches) {
            ret.add(Direction.EAST);
        }

        return ret;
    }

    public World getWorld() {
        return world;
    }
}
