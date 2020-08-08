package com.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DirectedGraphCycle {
    private final int vertices;
    private final List<List<Integer>> adjacent;

    public DirectedGraphCycle(int vertices) {
        this.vertices = vertices;
        adjacent = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++)
            adjacent.add(new LinkedList<>());
    }

    public static void main(String[] args) {
        DirectedGraphCycle graph = new DirectedGraphCycle(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        if (graph.CycleCheck())
            System.out.println("Cycle detected in graph");
        else
            System.out.println("No cycle detected in graph");
    }

    private boolean CycleCheckUtil(int i, boolean[] visited,
                                   boolean[] alreadyVisitedNode) {
        if (alreadyVisitedNode[i])
            return true;
        if (visited[i])
            return false;
        visited[i] = true;
        alreadyVisitedNode[i] = true;
        List<Integer> connectedNodes = adjacent.get(i);
        for (Integer c : connectedNodes)
            if (CycleCheckUtil(c, visited, alreadyVisitedNode))
                return true;
        alreadyVisitedNode[i] = false;
        return false;
    }

    private void addEdge(int source, int destination) {
        adjacent.get(source).add(destination);
    }

    private boolean CycleCheck() {
        boolean[] visited = new boolean[vertices];
        boolean[] alreadyVisitedNode = new boolean[vertices];
        for (int i = 0; i < vertices; i++)
            if (CycleCheckUtil(i, visited, alreadyVisitedNode))
                return true;
        return false;
    }
}
