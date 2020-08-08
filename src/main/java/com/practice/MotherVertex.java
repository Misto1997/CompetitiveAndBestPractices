package com.practice;

import java.util.Arrays;
import java.util.Iterator;

public class MotherVertex {
    public static void main(String[] args) {
        Graph1 g = new Graph1(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        DFS(g);
    }

    private static void DFS(Graph1 g) {
        boolean visited[] = new boolean[g.v];
        int motherVertex = Integer.MIN_VALUE;
        for (int i = 0; i < g.v; ++i) {
            if (visited[i] == false) {
                DFSUtil(i, visited, g);
                motherVertex = i;
            }
        }
        checkMotherVertex(motherVertex, visited, g);

    }

    private static void checkMotherVertex(int motherVertex, boolean[] visited, Graph1 g) {
        Arrays.fill(visited, false);
        DFSUtil(motherVertex, visited, g);
        for (int i = 0; i < g.v; i++) {
            if (visited[i] == false) {
                System.out.println("No mother vertex available");
                return;
            }
        }
        System.out.println("Mother Vertex:" + motherVertex);
    }

    private static void DFSUtil(int edge, boolean visited[], Graph1 g) {
        visited[edge] = true;
        System.out.print(edge + " ");
        Iterator<Integer> i = g.list[edge].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited, g);
        }
    }


}
