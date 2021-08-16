package mod.unclecat.uc_auramagic.util.helpers;

import java.util.*;

import mod.unclecat.uc_auramagic.Auramagic;
import net.minecraft.util.Direction;

public class JavaHelper {
    public static <T> List<List<T>> asTwoDimList(List<List<T>> output, int width, T... vals) {
        int height = vals.length / width;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output.get(i).add(j, vals[i * height + j]);
            }
        }

        return output;
    }

    public static <T> List<List<T>> asTwoDimList(int width, T... vals) {
        List<List<T>> output = new ArrayList<List<T>>();
        int height = vals.length / width;

        for (int i = 0; i < height; i++) {
            output.add(new ArrayList<T>());
            for (int j = 0; j < width; j++) {
                output.get(i).add(vals[i * height + j]);
            }
        }

        return output;
    }

    public static <T> T[][] asTwoDimArray(int width, T... vals) {
        int height = vals.length / width;
        T[][] output = (T[][])new Object[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output[i][j] = (vals[i * height + j]);
            }
        }

        return output;
    }

    public static int randomBetween(Random rand, int min, int max) {
        return min + rand.nextInt(max - min);
    }

    public static double randomBetween(Random rand, double min, double max) {
        return min + rand.nextDouble() * (max - min);
    }

    public static float randomBetween(Random rand, float min, float max) {
        return min + rand.nextFloat() * (max - min);
    }

    public static int randomBetween(int min, int max) {
        return min + Auramagic.RANDOM.nextInt(max - min);
    }

    public static double randomBetween(double min, double max) {
        return min + Auramagic.RANDOM.nextDouble() * (max - min);
    }

    public static float randomBetween(float min, float max) {
        return min + Auramagic.RANDOM.nextFloat() * (max - min);
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        return new HashSet<T>() {{
            addAll(Arrays.asList(elements));
        }};
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

    public static <T> T[][] rotateTwoDimArray(T[][] in) {
        int x = in[0].length;
        int y = in.length;

        T[][] output = (T[][]) new Object[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                output[j][x - i - 1] = in[i][j];
            }
        }

        return output;
    }

    /*
    * Rotates the list so as if the top side of the list was facing north but will face [[dir]]
    */
    @SuppressWarnings("incomplete-switch")
    public static <T> List<List<T>> rotateTwoDimArray(List<List<T>> listIn, List<List<T>> output, Direction dir) {
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

    /*
    * Rotates the list so as if the top side of the list was facing north but will face [[dir]]
    * */
    @SuppressWarnings("incomplete-switch")
    public static <T> List<List<T>> rotateTwoDimList(List<List<T>> listIn, Direction dir) {
        switch (dir) // Yes it's awful (TODO IMPORTANT)
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

        return listIn;
    }

    @SuppressWarnings("incomplete-switch")
    public static <T> T[][] rotateTwoDimArray(T[][] in, Direction dir) {
        switch (dir) // Yes it's awful (TODO IMPORTANT)
        {
            case EAST:
                return rotateTwoDimArray(in);

            case SOUTH:
                in = rotateTwoDimArray(in);
                return rotateTwoDimArray(in);

            case WEST:
                in = rotateTwoDimArray(in);
                in = rotateTwoDimArray(in);
                return rotateTwoDimArray(in);
        }

        return in;
    }

    /*
    Makes 2d list into 1d list
     */
    public static <T> List<T> flattenList(List<List<T>> list)
    {
        ArrayList<T> ret = new ArrayList<T>();

        for (List<T> i : list)
        {
            ret.addAll(i);
        }

        return ret;
    }

    public static <T> T[] flattenArray(T[][] arr)
    {
        T[] ret = (T[]) new Object[arr[0].length * arr.length];

        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[i].length; j++) {
                ret[i * arr.length + j] = arr[i][j];
            }
        }

        return ret;
    }
}
