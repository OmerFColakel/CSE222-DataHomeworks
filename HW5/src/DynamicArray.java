package src;
/**
 * DynamicArray class is a class that stores a 2D array of strings dynamically.
 * It is used to store the data from the input file.
 */
public class DynamicArray {
    private String arr[][] = new String[10][10];
    private int heightSize = 0;
    private int heightCapacity = 10;
    private int widthSize[] = new int[heightCapacity];
    private int widthCapacity[] = new int[10];

    public DynamicArray() {
    }

    /**
     * Adds a new row to the 2D array with the given array of strings.
     *
     * @param strArr the array of strings to be added to the 2D array
     */
    public void add(String[] strArr) {
        if (heightCapacity == heightSize) {
            String[][] temp = new String[heightCapacity * 2][10];
            int[] temp2 = new int[heightCapacity * 2];
            for (int i = 0; i < heightCapacity; ++i) {
                temp[i] = arr[i];
                temp2[i] = widthCapacity[i];
            }
            arr = temp;
            widthCapacity = temp2;
            heightCapacity *= 2;
        }
        widthCapacity[heightSize] = strArr.length;
        arr[heightSize++] = strArr;
    }

    /**
     * Returns a string representation of the 2D array.
     * @return a string representation of the 2D array
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < heightSize; ++i) {
            for (int j = 0; j < widthCapacity[i]; ++j) {
                str.append(arr[i][j]);
                str.append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    /***
     * Returns the string with the given indexes in the 2D array.
     * @param i is the row index
     * @param j is the column index
     * @return the string with the given indexes in the 2D array
     */
    public String get(int i, int j) {
        if (i < 0 || j < 0 || i >= heightSize || j >= widthCapacity[i])
            return "null";
        return arr[i][j];
    }

    /**
     * Returns the number of rows in the 2D array.
     *
     * @return the number of rows in the 2D array
     */

    public int getHeightSize() {
        return heightSize;
    }

    /***
     * Returns the number of columns in the given row.
     * @param i is the row index
     * @return the number of columns in the given row
     */
    public int getWidthSize(int i) {
        if (i < 0 || i >= heightSize)
            throw new ArrayIndexOutOfBoundsException();
        return widthCapacity[i];
    }


}
