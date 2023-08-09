package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Path planning algorithm using BFS (Breadth First Search) algorithm using CSE222Graph and CSE222Map classes
 */
public class CSE222BFS {
    /**
     * Start and end points of the map
     */
    private int[] start, end;
    /**
     * Path from start to end
     */
    private ArrayList<int[]> path;

    /**
     * Default constructor
     */
    public CSE222BFS() {
        //  Initialize start and end points to -1, -1
        //  Initialize path to an empty arraylist
        path = new ArrayList<>();
        start = new int[]{-1, -1};
        end = new int[]{-1, -1};
    }

    /**
     * Constructor with CSE222Graph parameter
     * Checks if start and end points are valid
     * If valid, finds path from start to end using BFS algorithm
     * If not valid, throws exception
     * If there are no paths from start to end, prints "Path not found!"
     * If there is a path from start to end, prints "Path found!" and distance
     *
     * @param graph CSE222Graph object
     */
    public CSE222BFS(CSE222Graph graph) {
        System.out.println("BFS Algorithm");
        //  Set the start and end points from the graph
        //  If the start and end points are not valid, throw an exception
        try {
            start = graph.getStart();
            start = new int[]{start[1], start[0]};
            end = graph.getEnd();
            end = new int[]{end[1], end[0]};
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return;
        }

        //  Create a 2D array to store the visited points and initialize it to false
        boolean[][] visited = new boolean[graph.getHeight()][graph.getLength()];
        for (int i = 0; i < visited.length; ++i) {
            for (int j = 0; j < visited[i].length; ++j) {
                visited[i][j] = false;
            }
        }
        //  Create a queue to store the points to be visited
        Queue<int[]> pathFinder = new java.util.LinkedList<>();
        //  Create a 2D array to store the parent of each point
        String[][] parent = new String[graph.getHeight()][graph.getLength()];
        //  Add the start point to the queue and mark it as visited
        pathFinder.add(start);
        //  Mark the start point as visited
        visited[start[0]][start[1]] = true;
        boolean flag = false;
        //  While the queue is not empty, get the first point in the queue
        while (!pathFinder.isEmpty()) {
            //  Get the first point in the queue
            int[] current = pathFinder.poll();
            //  If the current point is the end point, break the loop
            if (current[0] == end[0] && current[1] == end[1]) {
                flag = true;
                break;
            }
            //  Get the neighbors of the current point
            ArrayList<int[]> neighbors = graph.getNeighbors(current);
            for (int[] neighbor : neighbors) {
                //  If the neighbor is not visited, add it to the queue and mark it as visited
                if (visited[neighbor[0]][neighbor[1]] == false) {
                    pathFinder.add(neighbor);
                    visited[neighbor[0]][neighbor[1]] = true;
                    parent[neighbor[0]][neighbor[1]] = current[0] + " " + current[1];
                }
            }
        }
        //  If there is a path from start to end, print "Path found!" and distance
        //  Also, find the path from start to end using parent array
        //  If there is no path from start to end, print "Path not found!"
        if (flag) {
            System.out.println("Path found!");
            path = new ArrayList<>();
            findPath(parent);
            System.out.println("Distance: " + (path.size() - 1));
        } else {
            System.out.println("Path not found!");
        }
    }

    /**
     * Finds path from end to start using parent array. Reverses the path and stores it in path arraylist
     *
     * @param parent parent array
     */
    private void findPath(String[][] parent) {
        //  Start from the end point
        int[] current = end;
        //  Add the end point to the path
        path.add(current);
        //  While the current point is not the start point, get the parent of the current point and add it to the path
        while (current[0] != start[0] || current[1] != start[1]) {
            String[] temp = parent[current[0]][current[1]].split(" ");
            current = new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])};
            path.add(current);
        }
        //  Reverse the path
        Collections.reverse(path);
    }

    /**
     * Overridden toString method
     *
     * @return String representation of the path
     */
    public String toString() {
        String result = "";
        for (int[] i : path) {
            result += i[0] + ", " + i[1] + "\n";
        }
        return result;
    }

    /**
     * Draws the path on the image and saves it as filename in png format
     *
     * @param image    BufferedImage object
     * @param filename filename of the image
     * @return BufferedImage object with the path drawn on it
     */
    public BufferedImage drawPath(BufferedImage image, String filename) {
        //  If the path is empty, print "Path not found!" and return the image
        if (path == null || path.size() == 0) {
            System.out.println("Path not found!");
            return image;
        }
        //  Draw the path on the image
        for (int i = 0; i < path.size(); ++i) {
            int[] current = path.get(i);
            image.setRGB(current[1], current[0], Color.RED.getRGB());       //  Set the color of the current point to red
        }
        //  Save the image as filename in png format
        try {
            filename = "src/BFS_" + filename + ".png";
            ImageIO.write(image, "png", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  Return the image
        return image;
    }

}
