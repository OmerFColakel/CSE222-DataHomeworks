package src;
public class bubbleSort {
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
    public bubbleSort() {
        originalMap = new myMap();
        sortedMap = new myMap();
    }

    /**
     * Constructor that takes a map as input
     *
     * @param map The map to be sorted
     */
    public bubbleSort(myMap map) {
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
        System.out.println("Bubble Sort Time: " + (System.nanoTime() - startTime) + " nanoseconds");
    }

    /**
     * Sorts the map using bubble sort
     */
    private void sort() {
        int indexOfLastUnsortedElement = aux.length - 1;
        boolean swapped = false;
        do {
            swapped = false;
            int i = 0;
            for (; i < indexOfLastUnsortedElement; ++i) {
                if (originalMap.get(aux[i]).getCount() > originalMap.get(aux[i + 1]).getCount()) {
                    String temp = aux[i];
                    aux[i] = aux[i + 1];
                    aux[i + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped)
                --indexOfLastUnsortedElement;
        } while (swapped);

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
