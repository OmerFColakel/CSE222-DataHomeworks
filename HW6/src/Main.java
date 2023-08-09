package src;

public class Main {
    public static void main(String[] args) {
        Test1();
        Test2();
        Test3();
        Test4();
        //Test5();
        //Test6();
        //Test7();
        Test8();

    }

    /**
     * Test pre-processing the string and creating a map.
     */
    public static void Test1() {
        System.out.println("Test 1:");
        // Preprocess the string
        String input = "java";
        System.out.println("Original String: " + input);
        input = processString(input);
        System.out.println("Preprocessed String: " + input + "\n\n");

        // Create a map
        System.out.println("The original (unsorted) map:");
        myMap myMap = new myMap(input);
        System.out.println(myMap);
    }

    /**
     * Tests pre-processing the string and creating a map
     */
    public static void Test2() {
        System.out.println("\nTest 2:");
        // Preprocess the string
        String input = "abc aba";
        System.out.println("Original String: " + input);
        input = processString(input);
        System.out.println("Preprocessed String: " + input + "\n\n");

        // Create a map
        System.out.println("The original (unsorted) map:");
        myMap myMap = new myMap(input);
        System.out.println(myMap);
    }

    /**
     * Tests pre-processing the string, creating a map, and sorting the map
     */
    public static void Test3() {
        System.out.println("\nTest 3:");
        // Preprocess the string
        String input = "buzzing bees buzz.";
        System.out.println("Original String: " + input);
        input = processString(input);
        System.out.println("Preprocessed String: " + input + "\n\n");

        // Create a map
        System.out.println("The original (unsorted) map:");
        myMap myMap = new myMap(input);
        System.out.println(myMap);

        // Sort the map
        System.out.println("\n\nThe sorted map:");
        mergeSort mergeSort = new mergeSort(myMap);
        System.out.println(mergeSort);
    }

    /**
     * Tests pre-processing the string, creating a map, sorting the map, and searching the map
     */
    public static void Test4() {
        System.out.println("\nTest 4:");
        // Preprocess the string
        String input = "'Hush, hush!' whispered the rushing wind.";
        System.out.println("Original String: " + input);
        input = processString(input);
        System.out.println("Preprocessed String: " + input + "\n\n");

        // Create a map
        System.out.println("The original (unsorted) map:");
        myMap myMap = new myMap(input);
        System.out.println(myMap);

        // Sort the map
        System.out.println("\n\nThe sorted map:");
        mergeSort mergeSort = new mergeSort(myMap);
        System.out.println(mergeSort);
    }

    /**
     * Tests empty input
     */
    private static void Test5() {
        System.out.println("\nTest 5:");
        String input = "";
        input = processString(input);
        System.out.println("Preprocessed String: " + input);
        myMap myMap1 = new myMap(input);
        System.out.println("The original (unsorted) map:");
        System.out.println(myMap1);
    }

    /**
     * Tests input with only spaces
     */
    private static void Test6() {
        System.out.println("\nTest 6:");
        // Preprocess the string
        String input = " ";
        input = processString(input);
        System.out.println("Preprocessed String: " + input);
        myMap myMap = new myMap(input);
        System.out.println("The original (unsorted) map:");
        System.out.println(myMap);
    }

    /**
     * Tests input with multiple spaces
     */
    private static void Test7() {
        System.out.println("\nTest 7:");
        // Preprocess the string
        String input = "a   b ";
        input = processString(input);
        System.out.println("Preprocessed String: " + input);
        myMap myMap = new myMap(input);
        System.out.println("The original (unsorted) map:");
        System.out.println(myMap);
    }

    /**
     * Tests trying to sort an empty map
     */
    private static void Test8()
    {
        System.out.println("\nTest 8:");
        myMap myMap = new myMap();
        System.out.println("The original (unsorted) map:");
        System.out.println(myMap);
        mergeSort mergeSort = new mergeSort(myMap);
        System.out.println("\n\nThe sorted map:");
        System.out.println(mergeSort);
    }

    /**
     * Preprocess the string. Remove all non-alphabetic characters and convert all uppercase letters to lowercase. Leave spaces as is.
     *
     * @param input The string to be preprocessed
     * @return The preprocessed string
     */
    public static String processString(String input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                sb.append((char) (input.charAt(i) + 32));
            } else if (input.charAt(i) == ' ' && i != 0 && input.charAt(i - 1) != ' ')
                sb.append(' ');
            else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }
}