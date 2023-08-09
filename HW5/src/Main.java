package src;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");      // Create a root for the tree
        String filename = "src/tree.txt";                                                   // File name
        ReadTxtFile(filename, root);                                                    // Read the file and add the nodes to the tree

        JTree jt = new JTree(root);
        JFrame frame = new JFrame();                                                    // Create a JFrame

        int menuIndex = 0;
        String menuStr;

        while (menuIndex != -1) {
            if (menuIndex == 0 || menuIndex == 5 || menuIndex == 4) {
                jt = new JTree(root);                                                 // Create a JTree from the root
                frame = new JFrame();                                                // Create a JFrame
                frame.add(jt);                                                              // Add the JTree to the JFrame
                frame.setVisible(true);                                                     // Make the JFrame visible
                frame.setSize(300, 600);                                        // Set the size of the JFrame
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                       // Set the default close operation of the JFrame
            }

            System.out.println("1) BFS Algorithm");                                     // Print the menu
            System.out.println("2) DFS Algorithm");
            System.out.println("3) Post Order Traversal Algorithm");
            System.out.println("4) Move Node");
            System.out.println("5) Reload Tree");
            System.out.println("-1) Exit");
            Scanner sc = new Scanner(System.in);
            menuStr = sc.nextLine();

            try {                                                                       // Check if the input is an integer
                menuIndex = Integer.parseInt(menuStr);
            } catch (Exception e) {
                menuIndex = 0;
            }
            switch (menuIndex) {
                case 1:                                                              // Call the BFS Algorithm
                    BFSAlgorithm(root);
                    break;
                case 2:                                                                // Call the DFS Algorithm

                    DFSAlgorithm(root);
                    break;
                case 3:                                                              // Call the Post Order Traversal Algorithm

                    PostOrderTraversalAlgorithm(root);
                    break;
                case 4:                                                            // Call the Move Node Algorithm

                    moveFromJTree(root);
                    frame.setVisible(false);
                    break;
                case 5:                                                              // Reload the tree from the file
                    frame.setVisible(false);
                    root = new DefaultMutableTreeNode("root");
                    ReadTxtFile(filename, root);
                    break;
                case -1:                                                             // Exit the program
                    System.out.println("Exiting...");
                    break;
                default:                                                            // Wrong input
                    System.out.println("Wrong Input!");
                    break;
            }
        }

    }

    /***
     * Read the file and add the nodes to the tree. Calls the addToJtree function to add the nodes to the tree
     * @param filename is the name of the file to be read
     * @param root is the root of the tree
     */
    private static void ReadTxtFile(String filename, DefaultMutableTreeNode root) {
        DynamicArray arr = new DynamicArray();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                arr.add(myReader.nextLine().split(";"));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        addToJtree(root, arr);
    }

    /**
     * Add the nodes to the tree
     *
     * @param root is the root of the tree
     * @param data is the data to be added to the tree
     */
    public static void addToJtree(DefaultMutableTreeNode root, DynamicArray data) {
        for (int i = 0; i < data.getHeightSize(); ++i) {
            DefaultMutableTreeNode temp = root;
            for (int j = 0; j < data.getWidthSize(i); ++j) {
                boolean flag = true;
                for (int k = 0; k < temp.getChildCount(); ++k) {
                    if (temp.getChildAt(k).toString().equals(data.get(i, j))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    temp.add(new DefaultMutableTreeNode(data.get(i, j)));
                }
                temp = (DefaultMutableTreeNode) temp.getChildAt(temp.getChildCount() - 1);
            }
        }
    }

    /***
     * Search for a node in the tree using BFS Algorithm
     * @param root is the root of the tree
     */
    public static void BFSAlgorithm(DefaultMutableTreeNode root) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the node you want to search for");
        String str = sc.nextLine();
        Queue<DefaultMutableTreeNode> q = new LinkedList<>();
        q.add(root);
        System.out.println("Using BFS Algorithm to find " + str + " in the tree...");
        int counter = 0;
        while (!q.isEmpty()) {
            DefaultMutableTreeNode temp = q.poll();
            if (temp.toString().equals(str)) {
                System.out.println("Step " + ++counter + " -> " + temp + "(Found!)");
                return;
            }
            System.out.println("Step " + ++counter + " -> " + temp);
            for (int i = 0; i < temp.getChildCount(); ++i) {
                q.add((DefaultMutableTreeNode) temp.getChildAt(i));
            }
        }
        System.out.println(str + " Not Found!");
    }

    /***
     * Search for a node in the tree using DFS Algorithm
     * @param root is the root of the tree
     */
    public static void DFSAlgorithm(DefaultMutableTreeNode root) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the node you want to search for");
        String str = sc.nextLine();
        System.out.println("Using DFS Algorithm to find " + str + " in the tree...");
        Stack<DefaultMutableTreeNode> s = new Stack<>();
        s.push(root);
        int counter = 0;
        while (!s.isEmpty()) {
            DefaultMutableTreeNode temp = s.pop();
            if (temp.toString().equals(str)) {
                System.out.println("Step " + ++counter + " -> " + temp + "(Found!)");
                return;
            }
            System.out.println("Step " + ++counter + " -> " + temp);
            for (int i = 0; i < temp.getChildCount(); ++i) {
                s.push((DefaultMutableTreeNode) temp.getChildAt(i));
            }
        }
        System.out.println(str + " Not Found!");
    }

    /**
     * Search for a node in the tree using Post Order Traversal Algorithm
     *
     * @param root is the root of the tree
     */
    public static void PostOrderTraversalAlgorithm(DefaultMutableTreeNode root) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the node you want to search for");
        String str = sc.nextLine();
        System.out.println("Using Post Order Traversal Algorithm to find " + str + " in the tree...");
        PostOrderTraversalAlgorithmHelper(root, str, 1);
    }

    /**
     * Helper function for Post Order Traversal Algorithm. Calls itself recursively
     * Checks subtree by subtree
     *
     * @param root  is the root of the tree or subtree
     * @param str   is the node to be searched for
     * @param count is the number of steps taken to find the node
     * @return -1 if the node is found, count otherwise
     */
    public static int PostOrderTraversalAlgorithmHelper(DefaultMutableTreeNode root, String str, int count) {

        for (int i = 0; i < root.getChildCount(); ++i) {
            count = PostOrderTraversalAlgorithmHelper((DefaultMutableTreeNode) root.getChildAt(i), str, count);
        }
        if (count == -1) {
            return -1;
        }
        if (root.toString().equals(str)) {
            System.out.println("Step " + count + " -> " + root + "(Found!)");
            return -1;
        }

        if (!root.isRoot()) {
            System.out.println("Step " + count + " -> " + root);
            return count + 1;
        } else {
            System.out.println("Not Found!");
            return 0;
        }
    }

    /**
     * Search for a node to move and the node to move to. Then move the node to the new node
     *
     * @param root is the root of the tree
     */
    public static void moveFromJTree(DefaultMutableTreeNode root) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the node you want to move");
        String[] source = sc.nextLine().split("->");
        System.out.println("Enter the node you want to move to");
        String[] destination = sc.nextLine().split("->");
        DefaultMutableTreeNode sourceNode = root;
        DefaultMutableTreeNode destinationNode = root;
        for (int i = 0; i < source.length; ++i) {
            boolean flag = true;
            for (int j = 0; j < sourceNode.getChildCount(); ++j) {
                if (sourceNode.getChildAt(j).toString().equals(source[i])) {
                    sourceNode = (DefaultMutableTreeNode) sourceNode.getChildAt(j);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.print("Can't move ");
                for (int j = 0; j < source.length - 1; ++j) {
                    System.out.print(source[j] + "->");
                }
                System.out.println(source[source.length - 1]);
                System.out.println("Because it doesn't exist in the tree");
                return;
            }
        }
	  if (source[0].equals(destination[0])) {
            for (int k = 0; k < source.length - 1; k++)
                System.out.print(source[k] + "->");
            System.out.println(source[source.length - 1] + " has been overwritten!");
        }
        destinationNode = root;
        DefaultMutableTreeNode parentOfSourceNode = (DefaultMutableTreeNode) sourceNode.getParent();
        sourceNode.removeFromParent();
        while (!parentOfSourceNode.isRoot()) {
            if (parentOfSourceNode.getChildCount() == 0) {
                DefaultMutableTreeNode temp = (DefaultMutableTreeNode) parentOfSourceNode.getParent();
                parentOfSourceNode.removeFromParent();
                parentOfSourceNode = temp;
            } else
                break;
            if (parentOfSourceNode.isRoot())
                break;
        }

        for (int i = 0; i < destination.length; ++i) {
            for (int j = 0; j < destinationNode.getChildCount(); ++j) {
                if (destinationNode.getChildAt(j).toString().equals(destination[i])) {
                    destinationNode = (DefaultMutableTreeNode) destinationNode.getChildAt(j);
                    break;
                }
            }
        }
        destinationNode = root;
        for (int i = 0; i < source.length || i < destination.length; ++i) {
            boolean flag = true;
            if (i < destination.length) {
                for (int j = 0; j < destinationNode.getChildCount(); ++j) {
                    if (destinationNode.getChildAt(j).toString().equals(destination[i])) {
                        flag = false;
                        destinationNode = (DefaultMutableTreeNode) destinationNode.getChildAt(j);
                        break;
                    }
                }
                if (flag) {
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(destination[i]);
                    destinationNode.add(newNode);
                    destinationNode = newNode;
                }
            } else {
                if (i == source.length - 1) {
                    for (int j = 0; j < destinationNode.getChildCount(); ++j) {
                        if (destinationNode.getChildAt(j).toString().equals(source[i])) {
                            for(int k=0;k<source.length-1;k++)
                                System.out.print(source[k] + "->");
                            System.out.println(source[source.length - 1] + " has been overwritten!");
                            DefaultMutableTreeNode temp = (DefaultMutableTreeNode) destinationNode.getChildAt(j);
                            temp = sourceNode;
                            flag = false;
                            break;
                        }
                    }
                    if (flag)

                        destinationNode.add(sourceNode);

                } else {
                    for (int j = 0; j < destinationNode.getChildCount(); ++j) {
                        if (destinationNode.getChildAt(j).toString().equals(source[i])) {
                            destinationNode = (DefaultMutableTreeNode) destinationNode.getChildAt(j);
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(source[i]);
                        destinationNode.add(newNode);
                        destinationNode = newNode;
                    }
                }
            }

        }
    }

}
