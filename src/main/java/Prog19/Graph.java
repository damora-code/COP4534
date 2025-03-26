package Prog19;

import Prog18.GraphInterface;
import Prog18.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Implements a Graph. Uses an adjacency matrix to represent the graph.
 *
 * @author Prof. Antonio Hernandez
 */
public class Graph implements GraphInterface {
    int count = 0;
    private int verticesNumber;
    private int[][] matrix; //adjacency matrix

    public Graph() {
        verticesNumber = 5;
        matrix = new int[verticesNumber][verticesNumber];
    }

    public Graph(int n) {
        verticesNumber = n;
        matrix = new int[verticesNumber][verticesNumber];
    }

    /**
     * Instantiates a graph and initializes it with info from a text file.
     *
     * @param filename text file with graph info
     */
    public Graph(String filename) {
        File input = new File(filename);

        Scanner in = null;
        try {
            in = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(0);
        }

        while (in.hasNextLine()) {
            verticesNumber = in.nextInt();
            matrix = new int[verticesNumber][verticesNumber];

            for (int i = 0; i < verticesNumber; i++) {
                for (int j = 0; j < verticesNumber; j++) {
                    matrix[i][j] = in.nextInt();
                }
            }
        }

        in.close();
    }

    public void addEdge(int v, int w) {
        matrix[v][w] = 1;
        matrix[w][v] = 1;
    }

    public void removeEdge(int v, int w) {
        matrix[v][w] = 0;
        matrix[w][v] = 0;
    }

    /**
     * Finds vertices adjacent to a given vertex.
     *
     * @param v given vertex
     * @return list of vertices adjacent to v stored in an array;
     * size of array = number of adjacent vertices
     */
    public int[] findAdjacencyVertices(int v) {
        int[] vert = new int[verticesNumber];
        int total = 0;

        for (int i = 0; i < verticesNumber; i++) {
            if (matrix[v][i] != 0) {
                vert[total] = i;
                total++;
            }
        }

        return Arrays.copyOf(vert, total);
    }

    public String toString() {
        String s = "";

        for (int i = 0; i < verticesNumber; i++) {
            for (int j = 0; j < verticesNumber; j++) {
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }

        return s;
    }

    /**
     * Starting at the given vertex, prints the graph vertices using a
     * breadth-first traversal.
     *
     * @param v given vertex
     */
    public void BFT(int v) {
        boolean[] visited = new boolean[verticesNumber];

        for (int i = 0; i < verticesNumber; i++) {
            visited[i] = false;
        }

        Queue vertexQueue = new Queue();

        vertexQueue.enqueue(v);
        visited[v] = true; //"visited" means it has been inserted in the queue.

        while (!vertexQueue.isEmpty()) {
            int w = vertexQueue.getFront();
            System.out.print(w + " ");
            vertexQueue.dequeue();

            int[] adj = findAdjacencyVertices(w);

            for (int u : adj) {
                if (!visited[u]) {
                    vertexQueue.enqueue(u);
                    visited[u] = true;
                }
            }
        }

        System.out.println();
    }

    public void DFT(int v) {
        boolean[] visited = new boolean[verticesNumber];

        recursiveDFT(v, visited);

        //some vertices might remain unvisited still
        for (int u = 0; u < verticesNumber; u++) {
            if (!visited[u]) recursiveDFT(u, visited);
        }
        System.out.println();
    }

    private void recursiveDFT(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        int[] adj = findAdjacencyVertices(v);

        for (int u : adj) {
            if (!visited[u]) recursiveDFT(u, visited);
        }
    }

    /**
     * Returns the smallest element in given array d, out of those that have not
     * been visited (see allShortestPaths method).
     *
     * @param visited  visited elements
     * @param distance array of distances
     * @return index of smallest element in d
     */

    private int minDistance(boolean[] visited, int[] distance) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < verticesNumber; i++) {
            if (!visited[i]) {
                if (distance[i] <= min) {
                    min = distance[i];
                    index = i;
                }
            }
        }
        return index;
    }

    /**
     * Calculates the shortest paths from a given vertex to all vertices.
     *
     * @param p paths (p[i] contains previous vertex in the shortest path from v)
     * @param d distances (d[i] contains the shortest distance from v)
     * @param v given vertex
     */
    public void allShortestPaths(int[] p, int[] d, int v) {
        boolean[] visited = new boolean[verticesNumber];

        for (int i = 0; i < verticesNumber; i++) {
            visited[i] = false; //not yet visited
            p[i] = -1; //previous vertex is unknown
            d[i] = Integer.MAX_VALUE; // d[i] = INFINITY
        }
        d[v] = 0;

        for (int i = 0; i < verticesNumber - 1; i++) {
            int w = minDistance(visited, d);
            visited[w] = true;

            int[] adj = findAdjacencyVertices(w);
            for (int u : adj) {
                if (!visited[u]) {
                    if (d[w] + matrix[w][u] < d[u]) {
                        d[u] = d[w] + matrix[w][u];
                        p[u] = w;
                    }
                }
            }


        }

    }

    /**
     * Returns shortest path between given source and target vertices.
     *
     * @param s source vertex
     * @param t target vertex
     * @param p paths (p[total] contains previous vertex in the shortest path from source vertex)
     * @return shortest path stored in array; s is the first and t the last
     */
    public int[] getPath(int s, int t, int[] p) {
        int[] shortestPath = new int[p.length];

        int current = t;
        int total = 0;
        while (current != s) {
            shortestPath[total] = current;
            current = p[current];
            total++;
        }
        shortestPath[total++] = s;
        shortestPath = Arrays.copyOf(shortestPath, total);

        // reverses array
        for (int i = 0; i < total / 2; i++) {
            int temp = shortestPath[i];
            shortestPath[i] = shortestPath[total - 1 - i];
            shortestPath[total - 1 - i] = temp;
        }

        return shortestPath;
    }

    /**
     * Print all permutations with values in [0, n-1].
     */
    public void printPermutations(int n) {
        int[] a = new int[n];
        printPermutations(a, 0);
    }

    /**
     * Recursive algorithm.
     *
     * @param a array partially filled with permutation
     * @param k index of current element in permutation
     */
    private void printPermutations(int[] a, int k) {
        if (k == a.length) {
            printArray(a);
        } else {
            ArrayList<Integer> Sk = constructCandidateSet(a, k);
            for (int s : Sk) {
                a[k] = s;
                printPermutations(a, k + 1);
            }
        }
    }

    /**
     * Construct candidate set (set will contain elements not used
     * in locations [0, k-1] of array a).
     */
    private ArrayList<Integer> constructCandidateSet(int[] a, int k) {
        ArrayList<Integer> candidates = new ArrayList<>();
        boolean[] b = new boolean[a.length];

        for (int i = 0; i < k; i++) {
            b[a[i]] = true;
        }

        for (int i = 0; i < a.length; i++) {
            if (!b[i]) candidates.add(i);
        }

        return candidates;
    }

    /**
     * Prints array a
     */
    private void printArray(int[] a) {

        System.out.printf("%4d: ", ++count);
        for (int v : a) {
            System.out.print(v + " ");
        }

        System.out.println();
    }

    /**
     * Finds the shortest route that visits every vertex
     * exactly once and returns to the starting point.
     * Uses exhaustive search.
     *
     * @param shortestRoute array with the shortest path (return value)
     * @return shortest distance
     */
    public int TSP_exhaustiveSearch(int[] shortestRoute) {
        // initialize shortestRoute
        for (int i = 0; i < verticesNumber; i++) {
            shortestRoute[i] = i;
        }

        int[] a = new int[verticesNumber];
        TSP_exhaustiveSearch(shortestRoute, a, 0);

        return totalDistance(shortestRoute);
    }

    /**
     * Calculates distance of given route.
     *
     * @param a route
     * @return distance of route
     */
    int totalDistance(int[] a) {
        int n = verticesNumber;
        // add weights of all edges in the path
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            int weight = matrix[a[i]][a[(i + 1) % n]];
            totalWeight += weight;
        }
        return totalWeight;
    }

    /**
     * Recursive algorithm.
     *
     * @param a array partially filled with permutation
     * @param k index of current element in permutation
     */
    private void TSP_exhaustiveSearch(int[] shortestRoute, int[] a, int k) {
        if (k == a.length) {
            if (totalDistance(a) < totalDistance(shortestRoute)) {
                System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
            }
            // System.out.print(totalDistance(a) + " ");
            // printArray(a);
        } else {
            ArrayList<Integer> Sk = constructCandidateSet(a, k);
            for (int s : Sk) {
                a[k] = s;
                TSP_exhaustiveSearch(shortestRoute, a, k + 1);
            }
        }
    }

    public void randomPermutation(int[] a) {
        for (int i = 0; i < a.length; i++)
            a[i] = i;

        Random rnd = new Random();

        for (int i = a.length - 1; i > 0; i--) {
            //generates a random index in [0, i]
            int randomLocation = rnd.nextInt(i + 1);

            if (randomLocation != i) {
                //swp a[i] and a[randomLocation]

                int temp = a[i];
                a[i] = a[randomLocation];
                a[randomLocation] = temp;
            }
        }
    }

    public int TSP_randomSampling(int[] shortestRoute) {
        int numberOfSamples = 10;
        int bestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfSamples; i++) {
            int[] a = new int[verticesNumber];
            randomPermutation(a);
            int currentDistance = totalDistance(a);
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
                System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
            }
        }
        return bestDistance;
    }
}