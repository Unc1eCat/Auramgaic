package mod.unclecat.uc_auramagic.util.multiblock;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import mod.unclecat.uc_auramagic.Auramagic;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

// To understand what it does and its benefit just read its code
// Briefly: allows you to iterate through some area of blocks as if it would be looking to the north. 
// Allows you to abstract away from the fact that the structure may be faced different sides
// I HATE SPATIAL THINKING AFTER WRITING THIS CLASS
// It's beautiful
@SuppressWarnings("unchecked")
public class HorizontalRotationlessPosition<T extends HorizontalRotationlessPosition<T>> {
    protected BlockPos currentPosNorth;
    protected BlockPos currentPosSouth;
    protected BlockPos currentPosWest;
    protected BlockPos currentPosEast;
    protected BlockPos beginningPos;


    public HorizontalRotationlessPosition(BlockPos beginning) {
        currentPosNorth = new BlockPos(beginning);
        currentPosSouth = new BlockPos(beginning);
        currentPosWest = new BlockPos(beginning);
        currentPosEast = new BlockPos(beginning);
        beginningPos = new BlockPos(beginning);
    }

    public HorizontalRotationlessPosition(HorizontalRotationlessPosition<?> copyFrom) {
        currentPosNorth = new BlockPos(copyFrom.currentPosNorth);
        currentPosSouth = new BlockPos(copyFrom.currentPosNorth);
        currentPosWest = new BlockPos(copyFrom.currentPosNorth);
        currentPosEast = new BlockPos(copyFrom.currentPosNorth);
    }


    public T alongX(int r) {
        currentPosNorth = currentPosNorth.add(r, 0, 0);
        currentPosSouth = currentPosSouth.add(-r, 0, 0);
        currentPosEast = currentPosEast.add(0, 0, r);
        currentPosWest = currentPosWest.add(0, 0, -r);

        return (T) this;
    }

    public T alongY(int r) {
        currentPosNorth = currentPosNorth.add(0, r, 0);
        currentPosSouth = currentPosSouth.add(0, r, 0);
        currentPosEast = currentPosEast.add(0, r, 0);
        currentPosWest = currentPosWest.add(0, r, 0);

        return (T) this;
    }

    public T alongZ(int r) {
        currentPosNorth = currentPosNorth.add(0, 0, r);
        currentPosSouth = currentPosSouth.add(0, 0, -r);
        currentPosEast = currentPosEast.add(-r, 0, 0);
        currentPosWest = currentPosWest.add(r, 0, 0);

        return (T) this;
    }

    public T move(BlockPos pos) {
        alongX(pos.getX());
        alongY(pos.getY());
        alongZ(pos.getZ());

        return (T) this;
    }


    public T jumpToCopy(BlockPos pos, T copyTo) {
        copyTo.set(currentPosNorth, currentPosSouth, currentPosEast, currentPosWest);

        copyTo.move(pos);

        return copyTo;
    }

    public <U extends HorizontalRotationlessPosition<?>> T copyAndContinue(BlockPos pos, U copyTo) {
        copyTo.set(currentPosNorth, currentPosSouth, currentPosEast, currentPosWest);

        copyTo.move(pos);

        return (T) this;
    }

    public T jumpToCopy(T copyTo) {
        copyTo.set(currentPosNorth, currentPosSouth, currentPosEast, currentPosWest);

        return copyTo;
    }

    public <U extends HorizontalRotationlessPosition<?>> T copyAndContinue(U copyTo) {
        copyTo.set(currentPosNorth, currentPosSouth, currentPosEast, currentPosWest);

        return (T) this;
    }

    public T up() {
        alongY(1);

        return (T) this;
    }

    public T down() {
        alongY(-1);

        return (T) this;
    }

    public T north() {
        alongZ(-1);

        System.out.println(currentPosNorth);

        return (T) this;
    }

    public T south() {
        alongZ(1);

        return (T) this;
    }

    public T west() {
        alongX(-1);

        return (T) this;
    }

    public T east() {
        alongX(1);

        return (T) this;
    }


    public T up(int amount) {
        alongY(amount);

        return (T) this;
    }

    public T down(int amount) {
        alongY(-amount);

        return (T) this;
    }

    public T north(int amount) {
        alongZ(-amount);

        return (T) this;
    }

    public T south(int amount) {
        alongZ(amount);

        return (T) this;
    }

    public T west(int amount) {
        alongX(-amount);

        return (T) this;
    }

    public T east(int amount) {
        alongX(amount);

        return (T) this;
    }


    public T offset(Direction dir) {
        offset(dir, 1);

        return (T) this;
    }

    public T offset(Direction dir, int amount) {
        switch (dir) {
            case DOWN:
                down(amount);
                break;

            case EAST:
                east(amount);
                break;

            case NORTH:
                north(amount);
                break;

            case SOUTH:
                south(amount);
                break;

            case UP:
                up(amount);
                break;

            case WEST:
                west(amount);
                break;
        }

        return (T) this;
    }


    public T runOnPositions(Consumer<BlockPos> consumer) {
        consumer.accept(currentPosNorth);
        consumer.accept(currentPosSouth);
        consumer.accept(currentPosWest);
        consumer.accept(currentPosEast);

        return (T) this;
    }

    public T runForPositions(Consumer<BlockPos> north, Consumer<BlockPos> south, Consumer<BlockPos> west, Consumer<BlockPos> east) {
        north.accept(currentPosNorth);
        south.accept(currentPosSouth);
        west.accept(currentPosWest);
        east.accept(currentPosEast);

        return (T) this;
    }

    public T runWithPositions(BiConsumer<BlockPos, Direction> consumer) {
        consumer.accept(currentPosNorth, Direction.NORTH);
        consumer.accept(currentPosSouth, Direction.SOUTH);
        consumer.accept(currentPosWest, Direction.EAST);
        consumer.accept(currentPosEast, Direction.WEST);

        return (T) this;
    }


    public T set(BlockPos north, BlockPos south, BlockPos east, BlockPos west) {
        currentPosNorth = north;
        currentPosSouth = south;
        currentPosEast = east;
        currentPosWest = west;

        return (T) this;
    }

    public T set(BlockPos pos) {
        currentPosNorth = pos;
        currentPosSouth = pos;
        currentPosEast = pos;
        currentPosWest = pos;

        return (T) this;
    }

    /*
     * UP and DOWN sets all positions
     */
    public T set(BlockPos pos, Direction dir) {
        switch (dir) {
            case EAST:
                currentPosEast = pos;
                break;

            case NORTH:
                currentPosNorth = pos;
                break;

            case SOUTH:
                currentPosSouth = pos;
                break;

            case WEST:
                currentPosWest = pos;
                break;

            default:
                set(pos);
                break;
        }

        return (T) this;
    }

    public BlockPos getNorth() {
        return currentPosNorth;
    }

    public BlockPos getSouth() {
        return currentPosSouth;
    }

    public BlockPos getEast() {
        return currentPosEast;
    }

    public BlockPos getWest() {
        return currentPosWest;
    }

    public BlockPos getRelativePosition() {
        return currentPosNorth.subtract(beginningPos);
    }


    public T setBeginning(BlockPos pos) {
        beginningPos = pos;
        BlockPos relative = getRelativePosition();
        set(pos);
        move(relative);

        return (T) this;
    }

    public BlockPos getBeginningPos() {
        return beginningPos;
    }
}
