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
        world = copyFrom.world;
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
        if (northMatches) northMatches = world.getBlockState(currentPosNorth).getBlock() == block;
        if (southMatches) southMatches = world.getBlockState(currentPosSouth).getBlock() == block;
        if (eastMatches) eastMatches = world.getBlockState(currentPosEast).getBlock() == block;
        if (westMatches) westMatches = world.getBlockState(currentPosWest).getBlock() == block;

        return this;
    }

    public HorizontalRotationlessMatcher matchBlock(Block... blocks) {
        boolean n = false, s = false, w = false, e = false;

        for (Block i : blocks) {
            if (!n) n = world.getBlockState(currentPosNorth).getBlock() == i;
            if (!s) s = world.getBlockState(currentPosSouth).getBlock() == i;
            if (!w) w = world.getBlockState(currentPosWest).getBlock() == i;
            if (!e) e = world.getBlockState(currentPosEast).getBlock() == i;
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

    /*
     * Rotates list parameter 90 degrees clockwise
     */
    public static <T> List<List<T>> rotateTwoDimList(List<List<T>> listIn, List<List<T>> output) {
        int x = listIn.get(0).size();
        int y = listIn.size();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                output.get(j).add(x - i - 1, listIn.get(i).get(j));
            }
        }

        return output;
    }

    /*
     * Rotates list parameter 90 degrees clockwise
     */
    public static <T> List<List<T>> rotateTwoDimList(List<List<T>> listIn) {
        int x = listIn.get(0).size();
        int y = listIn.size();

        List<List<T>> output = new ArrayList<List<T>>(x);

        for (int i = 0; i < x; i++) {
            output.add(new ArrayList<T>(y));
            for (int j = 0; j < y; j++) {
                output.get(i).add(null);
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                output.get(j).set(x - i - 1, listIn.get(i).get(j));
            }
        }

        return output;
    }

    @SuppressWarnings("incomplete-switch")
    public static <T> List<List<T>> rotateTwoDimListOnTopOfBlock(List<List<T>> listIn, List<List<T>> output, Direction dir) {
        switch (dir) {
            case EAST:
                rotateTwoDimList(listIn, output);
                break;

            case SOUTH:
                rotateTwoDimList(listIn, output);
                rotateTwoDimList(listIn, output);
                break;

            case WEST:
                rotateTwoDimList(listIn, output);
                rotateTwoDimList(listIn, output);
                rotateTwoDimList(listIn, output);
                break;
        }

        return output;
    }

    @SuppressWarnings("incomplete-switch")
    public static <T> List<List<T>> rotateTwoDimListOnTopOfBlock(List<List<T>> listIn, Direction dir) {
        switch (dir) // Yes it's awful (TODOIMPORTANT)
        {
            case EAST:
                return rotateTwoDimList(listIn);

            case SOUTH:
                listIn = rotateTwoDimList(listIn);
                return rotateTwoDimList(listIn);

            case WEST:
                listIn = rotateTwoDimList(listIn);
                listIn = rotateTwoDimList(listIn);
                return rotateTwoDimList(listIn);
        }

        return null;
    }
}
