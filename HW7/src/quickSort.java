package src;

import java.util.ArrayList;

public class quickSort {
    /**
     * The original map
     */
    private myMap originalMap;
    /**
     * The sorted map
     */
    private myMap sortedMap;
    /**
     * an array of keys
     */
    private String[] aux;

    /**
     * Default constructor
     */
    public quickSort() {
        originalMap = new myMap();
        sortedMap = new myMap();
    }

    /**
     * Constructor with a map
     *
     * @param map
     */
    public quickSort(myMap map) {
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
        long startTime = System.nanoTime();
        sort(0, aux.length - 1);
        for (int i = 0; i < aux.length; ++i) {
            sortedMap.put(aux[i], originalMap.get(aux[i]));
        }
        System.out.println("Quick Sort Time: " + (System.nanoTime() - startTime) + " nanoseconds");
    }

    /**
     * Sorts the map
     *
     * @param low  the lower bound
     * @param high the upper bound
     */
    private void sort(int low, int high) {
        if (low >= high)
            return;
        int pivot = low;
        for (int i = low + 1; i <= high; ++i) {
            if (originalMap.get(aux[i]).getCount() <= originalMap.get(aux[low]).getCount()) {
                ++pivot;
                String temp = aux[i];
                aux[i] = aux[pivot];
                aux[pivot] = temp;
            }

        }
        String temp = aux[low];
        aux[low] = aux[pivot];
        aux[pivot] = temp;
        sort(low, pivot - 1);
        sort(pivot + 1, high);
    }

    /**
     * Override the toString method
     *
     * @return String representation of the sorted map
     */
    public String toString() {
        return sortedMap.toString();
    }
}
