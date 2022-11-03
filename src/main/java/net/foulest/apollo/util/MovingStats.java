package net.foulest.apollo.util;

import java.io.Serializable;

/**
 * this is mostly stolen from apache commons
 */
public final class MovingStats implements Serializable {

    private final double[] elements;
    private int currentElement;
    private int windowCount;
    private double variance;

    public MovingStats(int size) {
        elements = new double[size];
        variance = size * 2.5;

        // We need to assign the sum to the entire double array
        for (int i = 0, len = elements.length; i < len; i++) {
            elements[i] = size * 2.5 / size;
        }
    }

    public void add(double sum) {
        sum /= elements.length;
        variance -= elements[currentElement];
        variance += sum;

        // Apply the sum to the current element value
        elements[currentElement] = sum;

        // Change our element index so it doesn't idle
        currentElement = (currentElement + 1) % elements.length;
    }

    public double getStdDev(double required) {
        double stdDev = Math.sqrt(variance);

        // The standard deviation is less than the requirement
        if (stdDev < required) {
            // Count it and make sure all match
            if (++windowCount > elements.length) {
                return stdDev;
            }

        } else {
            // The standard deviation is greater than required, reset the count
            if (windowCount > 0) {
                windowCount = 0;
            }

            return required;
        }

        // This should never happen
        return Double.NaN;
    }
}
