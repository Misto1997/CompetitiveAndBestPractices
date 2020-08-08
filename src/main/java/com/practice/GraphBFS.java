package com.practice;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphBFS {
    public static void main(String[] args) {
        Graph1 g = new Graph1(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        BFS(g);
    }

    private static void BFS(Graph1 g) {
        boolean visited[] = new boolean[g.v];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < g.v; ++i)
            if (visited[i] == false)
                BFSUtil(i, visited, queue, g);
    }

    private static void BFSUtil(int edge, boolean visited[], LinkedList<Integer> queue, Graph1 g) {
        visited[edge] = true;
        queue.add(edge);
        while (queue.size() != 0) {
            edge = queue.poll();
            System.out.print(edge + " ");
            Iterator<Integer> i = g.list[edge].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }


}
