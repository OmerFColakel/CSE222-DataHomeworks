package src;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class is used to create a graph from a map
 */
public class CSE222Graph {
    /**
     * Adjacency list of every node in the graph
     */
    private Map<String, ArrayList<Edge>> adjacencyList;
    /**
     * Start point of the map
     */
    private int[] start;
    /**
     * End point of the map
     */
    private int[] end;
    /**
     * Height of the map
     */
    private int height;
    /**
     * Length of the map
     */
    private int length;

    /**
     * This method is used to get the neighbors of a node
     *
     * @param current Current node
     * @return Neighbors of the current node
     */
    public ArrayList<int[]> getNeighbors(int[] current) {
        String key = current[0] + " " + current[1];
        //  Find the value of the key
        ArrayList<Edge> neighbors = adjacencyList.get(key);
        ArrayList<int[]> result = new ArrayList<>();
        //  If there is no neighbors, return null
        try {
            // If there is a neighbor which is the end point, return it
            for (int i = 0; i < neighbors.size(); ++i) {
                if (neighbors.get(i).getEdge()[0] == end[0] && neighbors.get(i).getEdge()[1] == end[1]) {
                    result = new ArrayList<>();
                    result.add(neighbors.get(i).getEdge());
                    return result;
                }
            }
            // Else, return all neighbors
            result = new ArrayList<>();
            for (int i = 0; i < neighbors.size(); ++i) {
                result.add(neighbors.get(i).getEdge());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return result;
    }

    /**
     * This method is used to hold an edge
     */
    private class Edge {
        /**
         * Coordinates of the edge
         */
        private int[] edge;

        /**
         * Default constructor
         */
        public Edge() {
            edge = new int[]{-1, -1};
        }

        /**
         * Constructor with parameters
         *
         * @param x X coordinate of the edge
         * @param y Y coordinate of the edge
         */
        public Edge(int x, int y) {
            edge = new int[2];
            edge[0] = x;
            edge[1] = y;
        }

        /**
         * This method is used to get the coordinates of the edge
         *
         * @return Coordinates of the edge
         */
        public int[] getEdge() {
            return edge;
        }
    }

    /**
     * Constructor with a parameter. It creates a graph from a map object
     *
     * @param map Map object
     */
    public CSE222Graph(CSE222Map map) {
        //  Set the start, end, length, width and height of the map
        start = map.getStart();
        end = map.getEnd();
        length = map.getLength();
        height = map.getCurrentHeight();
        //  Create a new adjacency list
        adjacencyList = new java.util.HashMap<>();

        //  Add all nodes to the graph
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < length; ++j) {
                //  If the node is 0, continue
                if (map.get(j, i) == 0) {
                    //  Add the adjacent nodes to the array list
                    ArrayList<Edge> tempEdge = new ArrayList<>();
                    // Bottom right
                    if (i + 1 < map.getCurrentHeight() && j + 1 < map.getLength() && map.get(j + 1, i + 1) == 0)
                        tempEdge.add(new Edge(j + 1, i + 1));
                    // Bottom middle
                    if (i + 1 < map.getCurrentHeight() && map.get(j, i + 1) == 0)
                        tempEdge.add(new Edge(j, i + 1));
                    // Middle right
                    if (j + 1 < map.getLength() && map.get(j + 1, i) == 0)
                        tempEdge.add(new Edge(j + 1, i));
                    // Bottom left
                    if (i + 1 < map.getCurrentHeight() && j - 1 >= 0 && map.get(j - 1, i + 1) == 0)
                        tempEdge.add(new Edge(j - 1, i + 1));
                    // Top right
                    if (i - 1 >= 0 && j + 1 < map.getLength() && map.get(j + 1, i - 1) == 0)
                        tempEdge.add(new Edge(j + 1, i - 1));
                    // Top middle
                    if (i - 1 >= 0 && map.get(j, i - 1) == 0)
                        tempEdge.add(new Edge(j, i - 1));
                    // Middle left
                    if (j - 1 >= 0 && map.get(j - 1, i) == 0)
                        tempEdge.add(new Edge(j - 1, i));
                    // Top left
                    if (i - 1 >= 0 && j - 1 >= 0 && map.get(j - 1, i - 1) == 0)
                        tempEdge.add(new Edge(j - 1, i - 1));
                    //  Add the node to the graph
                    addEdge(j + " " + i, tempEdge);
                }
            }
        }
    }

    /**
     * This method is used to add an edge to the graph with a source node
     *
     * @param source Source node
     * @param edges  Edges of the source node
     */
    public void addEdge(String source, ArrayList<Edge> edges) {
        //  Add the source node and its edges to the graph
        adjacencyList.put(source, edges);
    }

    /**
     * This method is used to get the start point of the map
     * @return Start point of the map
     */
    public int[] getStart() {
        return start;
    }

    /**
     * This method is used to get the end point of the map
     * @return End point of the map
     */
    public int[] getEnd() {
        return end;
    }

    /**
     * This method is used to get the height of the map
     *
     * @return Height of the map
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method is used to get the length of the map
     *
     * @return Length of the map
     */
    public int getLength() {
        return length;
    }

    /**
     * Overridden toString method
     *
     * @return String representation of the graph
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (String v : adjacencyList.keySet()) {
            builder.append(v.toString() + ": ");
            for (Edge w : adjacencyList.get(v)) {
                builder.append(w.getEdge()[0] + "," + w.getEdge()[1] + " ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }
}
