package com.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShortestPathWeightedGraph {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int counter = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(input.nextInt(), counter);
            counter++;
        }
        WeightedGraph weightedGraph = new WeightedGraph(n);
        int edges = input.nextInt();
        for (int i = 0; i < edges; i++) {
            int source = input.nextInt();
            int destination = input.nextInt();
            int weight = input.nextInt();
            weightedGraph.addEdge(map.get(source), map.get(destination), weight);
        }
        int a = input.nextInt();
        int b = input.nextInt();
        boolean[] visited = new boolean[n];
        System.out.println(searchShortestPath(map.get(a), map.get(b), weightedGraph, visited, Integer.MAX_VALUE, 0));

    }

    private static int searchShortestPath(int source, int destination, WeightedGraph weightedGraph, boolean[] visited, int min, int i) {
        if (source == destination) {
            return 0;
        }
        visited[source] = true;
        while (i < weightedGraph.adjList[source].size()) {
            if (!visited[weightedGraph.adjList[source].get(i).destination]) {
                int val = searchShortestPath(weightedGraph.adjList[source].get(i).destination, destination, weightedGraph, visited, min, 0);
                if(val<Integer.MAX_VALUE)
                    min = Math.min(min, val+weightedGraph.adjList[source].get(i).weight);
            }
            i++;
        }
        visited[source] = false;

        return min;


    }

}
