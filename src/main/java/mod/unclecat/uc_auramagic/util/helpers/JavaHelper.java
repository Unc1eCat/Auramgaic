package mod.unclecat.uc_auramagic.util.helpers;

import java.util.*;

import mod.unclecat.uc_auramagic.Auramagic;

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
}
