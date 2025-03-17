package Prog18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Implements a Graph. Uses an adjacency matrix to represent the graph.
 * 
 * @author Prof. Antonio Hernandez
 */
public class Graph implements GraphInterface
{
    private int verticesNumber;
    private int[][] matrix; //adjacency matrix
    
    public Graph()
    {
        verticesNumber = 5;
        matrix = new int[verticesNumber][verticesNumber];
    }
    
    public Graph(int n)
    {
        verticesNumber = n;
        matrix = new int[verticesNumber][verticesNumber];
    }

    /**
     * Instantiates a graph and initializes it with info from a text file.
     * 
     * @param filename text file with graph info
     */
    public Graph(String filename)
    {
        File input = new File(filename);

        Scanner in = null;
        try
        {
            in = new Scanner(input);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
            System.exit(0);
        }
        
        while (in.hasNextLine())
        {
            verticesNumber = in.nextInt();
            matrix = new int[verticesNumber][verticesNumber];
            
            for(int i=0; i<verticesNumber; i++)
            {
                for(int j=0; j<verticesNumber; j++)
                {
                    matrix[i][j] = in.nextInt();
                }
            }            
        }
        
        in.close();
    }
    
    public void addEdge(int v, int w)
    {
        matrix[v][w] = 1;
        matrix[w][v] = 1;
    }
    
    public void removeEdge(int v, int w)
    {
        matrix[v][w] = 0;
        matrix[w][v] = 0;        
    }
    
    /**
     * Finds vertices adjacent to a given vertex.
     * 
     * @param v given vertex
     * @return list of vertices adjacent to v stored in an array;
     *          size of array = number of adjacent vertices
     */
    public int[] findAdjacencyVertices(int v)
    {
        int[] vert = new int[verticesNumber];
        int total = 0;
        
        for (int i=0; i<verticesNumber; i++)
        {
            if (matrix[v][i] != 0)
            {
                vert[total] = i;
                total++;
            }
        }
        
        return Arrays.copyOf(vert, total);        
    }
    
    public String toString()
    {
        String s = "";
        
        for (int i=0; i<verticesNumber; i++)
        {
            for (int j=0; j<verticesNumber; j++)
            {
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
    public void BFT(int v)
    {
        boolean[] visited = new boolean[verticesNumber];
        
        for(int i=0; i< verticesNumber; i++)
        {
            visited[i] = false;
        }
        
        Queue vertexQueue = new Queue();
        
        vertexQueue.enqueue(v);
        visited[v] = true; //"visited" means it has been inserted in the queue.
        
        while (!vertexQueue.isEmpty())
        {
            int w = vertexQueue.getFront();
            System.out.print(w + " ");
            vertexQueue.dequeue();
            
            int[] adj = findAdjacencyVertices(w);
            
            for(int u : adj)
            {
                if (!visited[u])
                {
                    vertexQueue.enqueue(u);
                    visited[u] = true;
                }
            }
        }
        
        System.out.println();
    }

    public void DFT(int v) {
        boolean[] visited = new boolean[verticesNumber];

        recursiveDFT(v,visited);

        //some vertices might remain unvisited still
        for(int u =0; u<verticesNumber;u++){
            if (!visited[u]) recursiveDFT(u,visited);
        }
        System.out.println();
    }

    private void recursiveDFT(int v, boolean[] visited)
    {
        visited[v] = true;
        System.out.print(v+" ");

        int[] adj = findAdjacencyVertices(v);

        for (int u:adj)
        {
            if (!visited[u]) recursiveDFT(u,visited);
        }
    }

    /**
     * Returns smallest element in given array d, out of those that have not
     * been visited (see allShortestPaths method).
     *
     * @param visited visited elements
     * @param d       array of distances
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
                    d[u] = d[w] + matrix[w][u];
                    p[u] = w;
                }
            }
        }
    }
}