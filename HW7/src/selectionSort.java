package src;

public class selectionSort {
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
    public selectionSort() {
        originalMap = new myMap();
        sortedMap = new myMap();
    }

    /**
     * Constructor with a map
     * @param map The map to be sorted
     */
    public selectionSort(myMap map) {
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
        System.out.println("Selection Sort: " + (System.nanoTime() - startTime) + " nanoseconds");

    }

    /**
     * Sort the map using selection sort
     */
    private void sort() {

        for (int i = 0; i < aux.length; ++i) {
            int min = originalMap.get(aux[i]).getCount();
            int indexOfMin = i;
            for (int j = i + 1; j < aux.length; ++j) {
                if (min > originalMap.get(aux[j]).getCount()) {
                    min = originalMap.get(aux[j]).getCount();
                    indexOfMin = j;
                }
            }
            String temp = aux[i];
            aux[i] = aux[indexOfMin];
            aux[indexOfMin] = temp;
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
