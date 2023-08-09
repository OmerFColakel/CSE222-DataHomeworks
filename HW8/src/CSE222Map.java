package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Stores coordinates and values of a map and converts it to a PNG image
 */
public class CSE222Map {
    /**
     * Stores coordinates and values of the map
     */
    private Map<String, Integer> map;
    /**
     * Height of the map
     */
    private int currentHeight;
    /**
     * Width of the map
     */
    private int width;
    /**
     * Start point of the map
     */
    private int[] start;
    /**
     * End point of the map
     */
    private int[] end;

    /**
     * Default constructor
     */
    public CSE222Map() {
        map = new LinkedHashMap<>();
        currentHeight = 0;
        width = 0;
        start = new int[]{-1, -1};
        end = new int[]{-1, -1};
    }

    /**
     * Sets start and end points of the map
     * @param start start point
     * @param end   end point
     */
    public void setStartAndEnd(int[] start, int[] end) {
        //  Check if the start and end points are valid. They can't be negative and they can't be the same point
        try {
            if (start[0] < 0 || start[1] < 0 || end[0] < 0 || end[1] < 0)
                throw new Exception("Start or end points cannot be negative");
            if (start[0] == end[0] && start[1] == end[1])
                throw new Exception("Start and end points cannot be the same");
            if (map.containsKey(start[0] + "," + start[1]))
                throw new Exception("Start point is invalid: " + map.get(start[0] + "," + start[1]));
            else if (map.containsKey(end[0] + "," + end[1]))
                throw new Exception("End point is invalid: " + map.get(end[0] + "," + end[1]));
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        //  Set the start and end points
        this.start = start;
        this.end = end;
    }

    /**
     * Takes a row of the map and puts it into the map
     * @param value row of the map
     */
    public void put(String[] value) {
        width = value.length;                                                               //  Assumes that all rows have the same length
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals("-1"))                                                      //  If the value is -1, it is to 1
                map.put(currentHeight + "," + i, 1);
            else                                                                            //  Otherwise, it is the value
                map.put(currentHeight + "," + i, Integer.parseInt(value[i]));
        }
        currentHeight++;                                                                    //  Increase the height
    }

    /**
     * Returns the height of the map
     * @return height of the map
     */
    public int getCurrentHeight() {
        return currentHeight;
    }

    /**
     * Returns start point of the map
     * @return start point of the map
     */
    public int[] getStart() {
        return start;
    }

    /**
     * Returns end point of the map
     * @return end point of the map
     */
    public int[] getEnd() {
        return end;
    }

    /**
     * Returns the value of the given coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @return
     */
    public int get(int x, int y) {
        return map.get(x + "," + y);
    }

    /**
     * Returns the width of the map
     * @return
     */
    public int getLength() {
        return width;
    }

    /**
     * Overrides toString method
     * @return string representation of the map
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Start: ").append(Arrays.toString(start)).append("\n");
        result.append("End: ").append(Arrays.toString(end)).append("\n");
        for (int i = 0; i < currentHeight; i++) {
            for (int j = 0; j < currentHeight; j++) {
                result.append(map.get(i + "," + j)).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Converts the map to a PNG image
     * @param filename name of the file
     * @return PNG image of the map
     */
    public BufferedImage convertPNG(String filename) {

        BufferedImage image = new BufferedImage(width, currentHeight, BufferedImage.TYPE_INT_RGB);  // Create a buffered image

        for (int i = 0; i < currentHeight; i++) {                                                   // Set the pixels of the image
            for (int j = 0; j < width; j++) {
                int value = map.get(j + "," + i);                                                   // Get the value of the coordinate
                int rgb = (value == 1) ? Color.DARK_GRAY.getRGB() : Color.LIGHT_GRAY.getRGB();      // Set the color of the coordinate. 1 is dark gray, 0 is light gray
                image.setRGB(i, j, rgb);                                                            // Set the pixel
            }
        }

        try {                                                                                       // Write the image to a PNG file
            filename = "src/Map_" + filename + ".png";
            ImageIO.write(image, "png", new File(filename));
        } catch (IOException e) {                                                                   // Catch the exception if the file cannot be written
            e.printStackTrace();
        }
        return image;
    }
}
