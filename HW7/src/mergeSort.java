package src;

import java.util.Arrays;

/**
 * A class that sorts a map in ascending order of the values
 */
public class mergeSort {
    /**
     * The original map
     */
    private myMap originalMap;
    /**
     * The sorted map
     */
    private myMap sortedMap;
    /**
     * An auxiliary array
     */
    private String[] aux;

    /**
     * Default constructor
     */
    public mergeSort() {
        originalMap = new myMap();
        sortedMap = new myMap();
    }

    /**
     * Constructor that takes a map as input
     * @param map The map to be sorted
     */
    public mergeSort(myMap map) {
        originalMap = map;
        sortedMap = new myMap();
        aux = originalMap.getKeys();
        try {
            if (aux.length == 0)
                throw new IllegalArgumentException("Map does not contain any words");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        long start = System.nanoTime();
        sort(0, aux.length - 1);
        for (int i = 0; i < aux.length; i++) {
            sortedMap.put(aux[i], originalMap.get(aux[i]));
        }
        System.out.println("Merge Sort Time: " + (System.nanoTime() - start) + " nanoseconds");

    }

    /**
     * Split the array into two halves, sort each half, and merge them into one
     * @param low The lower bound of the array
     * @param high The upper bound of the array
     */
    private void sort(int low, int high)
    {
        if (low < high) {
            int mid = (low + high) / 2;
            sort(low, mid);
            sort(mid + 1, high);
            merge(low, mid, high);
        }
    }

    /**
     * Merge two arrays into one by comparing the first elements of each array
     * @param low The lower bound of the array
     * @param mid The middle index of the array
     * @param high The upper bound of the array
     */
    private void merge(int low, int mid, int high)
    {
        String[] left = Arrays.copyOfRange(aux, low, mid + 1);
        String[] right = Arrays.copyOfRange(aux, mid + 1, high + 1);
        int leftIndex = 0, rightIndex = 0, auxIndex = low;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (originalMap.get(left[leftIndex]).getCount() <= originalMap.get(right[rightIndex]).getCount()) {
                aux[auxIndex] = left[leftIndex];
                leftIndex++;
            } else {
                aux[auxIndex] = right[rightIndex];
                rightIndex++;
            }
            auxIndex++;
        }
        while (leftIndex < left.length) {
            aux[auxIndex] = left[leftIndex];
            leftIndex++;
            auxIndex++;
        }
        while (rightIndex < right.length) {
            aux[auxIndex] = right[rightIndex];
            rightIndex++;
            auxIndex++;
        }

    }

    /**
     * Override the toString method
     * @return String representation of the sorted map
     */
    public String toString() {

        return sortedMap.toString();
    }

}