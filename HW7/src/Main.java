package src;

public class Main {
    public static void main(String[] args) {

        //  Declare and initialize the input strings
        String sorted = "abbccddddeeeeeffffffggggggghhhhhhhhiiiiiiiiijjjjjjjjjjkkkkkkkkkkklllllllllllmmmmmmmmmmmmmnnnnnnnnnnnnnnooooooooooooooopppppppppppppppprrrrrrrrrrrrrrrrrssssssssssssssssssttttttttttttttttttt";
        String reverseSorted = "tttttttttttttttttttssssssssssssssssssrrrrrrrrrrrrrrrrrppppppppppppppppooooooooooooooonnnnnnnnnnnnnnmmmmmmmmmmmmmlllllllllllkkkkkkkkkkkjjjjjjjjjjiiiiiiiiihhhhhhhhgggggggffffffeeeeeddddccbba";
        String random = "jlekllrsftjstrrpsmjskirhnsporfogcbrsnjsmrtmorhmtttnotkrlhpsmltthrktotjonfnbnespdflehaskighjmjngrhooimsgmllisgodjtotoippssnppkslprkngnmkhicmprtfiesrpnrknoosmjpotlirogitestmrkpltmpdtpfnkdjpn";
        String bestForQuick = "jjjjjjjjjjeeeeebbaccddddffffffggggggghhhhhhhhiiiiiiiiioooooooooooooookkkkkkkkkkklllllllllllmmmmmmmmmmmmmnnnnnnnnnnnnnnpppppppppppppppprrrrrrrrrrrrrrrrrssssssssssssssssssttttttttttttttttttt";

        //  Map the input strings to myMap objects
        myMap sortedMap = new myMap(sorted);
        myMap reverseSortedMap = new myMap(reverseSorted);
        myMap randomMap = new myMap(random);

        // Bubble Sort
        System.out.println("Bubble Sort");
        System.out.println("Best Case:");
        testBubbleSort(sortedMap);
        System.out.println("Worst Case:");
        testBubbleSort(reverseSortedMap);
        System.out.println("Average Case:");
        testBubbleSort(randomMap);

        // Insertion Sort
        System.out.println("Insertion Sort");
        System.out.println("Best Case:");
        testInsertionSort(sortedMap);
        System.out.println("Worst Case:");
        testInsertionSort(reverseSortedMap);
        System.out.println("Average Case:");
        testInsertionSort(randomMap);

        // Selection Sort
        System.out.println("Selection Sort");
        System.out.println("Sorted:");
        testSelectionSort(sortedMap);
        System.out.println("Reverse Sorted:");
        testSelectionSort(reverseSortedMap);
        System.out.println("Average Case:");
        testSelectionSort(randomMap);

        // Merge Sort
        System.out.println("Merge Sort");
        System.out.println("Sorted:");
        testMergeSort(sortedMap);
        System.out.println("Reverse Sorted:");
        testMergeSort(reverseSortedMap);
        System.out.println("Average Case:");
        testMergeSort(randomMap);

        // Quick Sort
        System.out.println("Quick Sort");
        System.out.println("Best Case:");
        testQuickSort(new myMap(bestForQuick));
        System.out.println("Worst Case:");
        testQuickSort(reverseSortedMap);
        System.out.println("Average Case:");
        testQuickSort(randomMap);


    }

    /**
     * Test the bubble sort algorithm with a myMap object
     * @param map the myMap object to be sorted
     */
    public static void testBubbleSort(myMap map) {
        bubbleSort bs = new bubbleSort(map);
        System.out.println(bs);
    }

    /**
     * Test the insertion sort algorithm with a myMap object
     * @param map the myMap object to be sorted
     */
    public static void testInsertionSort(myMap map) {
        insertionSort is = new insertionSort(map);
        System.out.println(is);
    }

    /**
     * Test the selection sort algorithm with a myMap object
     * @param map the myMap object to be sorted
     */
    public static void testSelectionSort(myMap map) {
        selectionSort ss = new selectionSort(map);
        System.out.println(ss);
    }

    /**
     * Test the merge sort algorithm with a myMap object
     * @param map the myMap object to be sorted
     */
    public static void testMergeSort(myMap map) {
        mergeSort ms = new mergeSort(map);
        System.out.println(ms);
    }

    /**
     * Test the quick sort algorithm with a myMap object
     * @param map the myMap object to be sorted
     */
    public static void testQuickSort(myMap map) {
        quickSort qs = new quickSort(map);
        System.out.println(qs);
    }


}

