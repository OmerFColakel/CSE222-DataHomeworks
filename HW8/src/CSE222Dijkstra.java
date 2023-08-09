package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Path finding algorithm using Dijkstra's algorithm to find the shortest path between two points on a map
 */
public class CSE222Dijkstra {
    /**
     * The start point of the path
     */
    private int[] start;
    /**
     * The end point of the path
     */
    private int[] end;
    /**
     * The path from the start to the end point
     */
    private ArrayList<int[]> path;

    /**
     * Default constructor
     */
    public CSE222Dijkstra() {
        start = new int[]{-1, -1};
        end = new int[]{-1, -1};
        path = new ArrayList<>();
    }

    /**
     * Constructor that takes a CSE222Graph object
     *
     * @param graph The graph to find the path on
     */
    public CSE222Dijkstra(CSE222Graph graph) {
        System.out.println("Dijkstra's Algorithm");
        //  Get the start and end points from the graph
        start = graph.getStart();
        end = graph.getEnd();

        try {
            start = new int[]{start[1], start[0]};
            end = new int[]{end[1], end[0]};
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return;
        }
        boolean flag = false;
        //  Create a 2D array to store the distances from the start point to each point on the map and initialize it to infinity
        int[][] distances = new int[graph.getHeight()][graph.getLength()];
        for (int i = 0; i < graph.getHeight(); ++i) {
            for (int j = 0; j < graph.getLength(); ++j) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        //  Set the distance from the start point to itself to 0
        distances[start[0]][start[1]] = 0;
        //  Create a queue to store the points to visit
        Queue<int[]> queue = new java.util.LinkedList<>();
        //  Add the start point to the queue
        queue.add(start);
        //  While the queue is not empty there are still points to visit). This will loop until all points have been visited
        while (!queue.isEmpty()) {
            //  Get the next point to visit
            int[] current = queue.poll();
            //  Get the neighbors of the current point
            ArrayList<int[]> neighbors = graph.getNeighbors(current);
            for (int i = 0; i < neighbors.size(); ++i) {
                //  For each neighbor, if the distance from the start point to the neighbor is infinity,
                //  set the distance to the distance from the start point to the current point + 1
                //  and add the neighbor to the queue
                int[] neighbor = neighbors.get(i);
                if (distances[neighbor[0]][neighbor[1]] == Integer.MAX_VALUE) {
                    distances[neighbor[0]][neighbor[1]] = distances[current[0]][current[1]] + 1;
                    queue.add(neighbor);
                }
            }
        }

        path = new ArrayList<>();
        ArrayList<int[]> temp = new ArrayList<>();
        temp.add(end);
        //  Loop until the start point is reached.
        //  This will loop until the path is found or there is no path.
        //  If there is no path, the loop will break and the path will not be found and the user will be notified
        while (true) {
            int[] current = temp.get(temp.size() - 1);
            if (current[0] == start[0] && current[1] == start[1]) {
                flag = true;
                break;
            }
            ArrayList<int[]> neighbors = graph.getNeighbors(current);
            boolean pathAvailable = false;
            for (int i = 0; i < neighbors.size(); ++i) {
                int[] neighbor = neighbors.get(i);
                if (distances[neighbor[0]][neighbor[1]] == distances[current[0]][current[1]] - 1) {
                    temp.add(neighbor);
                    pathAvailable = true;
                    break;
                }
            }
            if (!pathAvailable) {
                flag = false;
                break;
            }
        }
        if (!flag) {
            System.out.println("No path found");
            return;
        }
        System.out.println("Path found");
        //  Reverse the path so that it goes from the start point to the end point
        for (int i = temp.size() - 1; i >= 0; --i) {
            path.add(temp.get(i));
        }
        //  Print the distance from the start point to the end point
        System.out.println("Distance: " + (path.size() - 1));
    }

    /**
     * Overridden toString method
     *
     * @return
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < path.size(); ++i) {
            result += path.get(i)[0] + "," + path.get(i)[1] + "\n";
        }
        return result;
    }

    /**
     * Draws the path on the image
     *
     * @param image    The image to draw the path on
     * @param filename The name of the file to save the image as
     * @return The image with the path drawn on it
     */
    public BufferedImage drawPath(BufferedImage image, String filename) {
        //  If there is no path, notify the user and return the image
        if(path == null || path.size() == 0) {
            System.out.println("No path found");
            return image;
        }
        //  Draw the path on the image and save it as a png file with the given filename and return the image
        for (int i = 0; i < path.size(); ++i) {
            int[] current = path.get(i);
            image.setRGB(current[1], current[0], Color.GREEN.getRGB());     // Set the color of the current point to green
        }
        try {
            filename = "src/Dijkstra_" + filename + ".png";
            ImageIO.write(image, "png", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


}
