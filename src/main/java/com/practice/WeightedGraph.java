package com.practice;

import java.util.LinkedList;

class Edge {
    int destination;
    int weight;

    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class WeightedGraph {
    int v;
    LinkedList<Edge>[] adjList;

    WeightedGraph(int v) {
        this.v = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adjList[i] = new LinkedList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        adjList[source].add(new Edge(destination, weight));
    }
}
