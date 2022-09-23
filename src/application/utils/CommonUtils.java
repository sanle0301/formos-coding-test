package application.utils;

import java.util.Random;
import java.util.Scanner;

public class CommonUtils {

    /**
     * Prevents initialize a new instance
     */
    private CommonUtils() {

    }

    /**
     * Random a double number
     * 
     * @param min
     * @param max
     * @return a double number (min <= number < max)
     */
    public static double randomDouble(double min, double max) {
        return new Random().nextDouble() * (max - min) + min;
    }

    public static double randomDoubleWithRound(double min, double max, int round) {
        long factor = (long) Math.pow(10, round);
        return Math.round(randomDouble(min, max) * factor) / factor;
    }

    public static int getInputAsInt(Scanner sc) {
        int key = Integer.MIN_VALUE;

        try {
            key = sc.nextInt();
        } catch (Exception e) {
            // Not handle here.
        }

        return key;
    }
}
