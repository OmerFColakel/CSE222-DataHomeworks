package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //  You can test the program with the following maps or your own maps
        Test("map01");
        Test("map02");
        Test("map03");
        Test("map04");
        Test("map05");
        Test("map06");
        Test("map07");
        Test("map08");
        Test("map09");
        Test("map10");
        Test("map11");
        Test("pisa");
        Test("tokyo");
        Test("triumph");
        Test("vatican");
    }

    /**
     * Creates directories, reads the input file, creates the map, graph, bfs and dijkstra objects, draws the path on the map and writes the outputs to files
     * @param input name of the input file
     */
    public static void Test(String input) {
        new File("Outputs").mkdir();
        new File("Outputs/" + input).mkdir();
        System.out.println("\nTesting " + input + ".txt");
        File file = new File("TextFiles/" + input + ".txt");
        int height = 0;
        int startX = 0, startY = 0, endX, endY;

        CSE222Map map = new CSE222Map();

        try {
            String line;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (height == 0) {
                    startX = Integer.parseInt(line.split(",")[1]);
                    startY = Integer.parseInt(line.split(",")[0]);
                } else if (height == 1) {
                    endX = Integer.parseInt(line.split(",")[1]);
                    endY = Integer.parseInt(line.split(",")[0]);
                    map.setStartAndEnd(new int[]{startX, startY}, new int[]{endX, endY});
                } else {
                    map.put(line.split(","));
                }
                height++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        long startTime = System.nanoTime();
        CSE222Graph graph = new CSE222Graph(map);
        System.out.println("Graph created in " + (System.nanoTime() - startTime) + "ns");
        startTime = System.nanoTime();
        CSE222BFS bfs = new CSE222BFS(graph);
        System.out.println("BFS created in " + (System.nanoTime() - startTime) + "ns");
        startTime = System.nanoTime();
        CSE222Dijkstra dijkstra = new CSE222Dijkstra(graph);
        System.out.println("Dijkstra created in " + (System.nanoTime() - startTime) + "ns");

        BufferedImage image = map.convertPNG(input);
        bfs.drawPath(image, input);
        dijkstra.drawPath(image, input);
        try {
            FileWriter writer = new FileWriter("Outputs/" + input + "/map_" + input + ".txt");
            writer.write(map.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        try {
            FileWriter writer = new FileWriter("Outputs/" + input + "/graph_" + input + ".txt");
            writer.write(graph.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        try {
            FileWriter writer = new FileWriter("Outputs/" + input + "/bfs_" + input + ".txt");
            writer.write(bfs.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        try {
            FileWriter writer = new FileWriter("Outputs/" + input + "/dijkstra_" + input + ".txt");
            writer.write(dijkstra.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }


    }
}