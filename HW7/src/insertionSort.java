package src;

public class insertionSort {
    /**
     * The original map
     */
    private myMap originalMap;
    /**
     * The sorted map
     */
    private myMap sortedMap;
    /**
     * an array of aux
     */
    private String[] aux;

    /**
     * Default constructor
     */
    public insertionSort() {
        originalMap = new myMap();
        sortedMap = new myMap();
    }

    /**
     * Constructor that takes a map as input
     *
     * @param map The map to be sorted
     */
    public insertionSort(myMap map) {
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
        sort();
        for (int i = 0; i < aux.length; ++i) {
            sortedMap.put(aux[i], originalMap.get(aux[i]));
        }
        System.out.println("Insertion Sort Time: " + (System.nanoTime() - startTime) + " nanoseconds");
    }

    /**
     * Sort the map using insertion sort
     */
    private void sort() {
        for (int i = 1; i < aux.length; ++i) {
            String temp = aux[i];
            int j = i - 1;
            while (j >= 0 && originalMap.get(aux[j]).getCount() > originalMap.get(temp).getCount()) {
                aux[j + 1] = aux[j];
                --j;
            }
            aux[j + 1] = temp;
        }

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
