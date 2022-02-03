package com.practice;


import java.util.Arrays;
import java.util.LinkedList;

class DjNode {
    int v;
    int dist;

    DjNode(int v, int dist) {
        this.v = v;
        this.dist = dist;
    }
}

class DjGraph {
    int v;
    LinkedList<DjNode>[] list;

    DjGraph(int v) {
        this.v = v;
        list = new LinkedList[v];
        for (int i = 0; i < v; i++)
            list[i] = new LinkedList();
    }
}

public class DijkstraShortestPath {
    public static void main(String[] args) {
        DjGraph ob = new DjGraph(6);
        ob.list[1].add(new DjNode(2, 2));
        ob.list[1].add(new DjNode(3, 2));
        ob.list[2].add(new DjNode(3, 1));
        ob.list[2].add(new DjNode(4, 4));
        ob.list[3].add(new DjNode(4, 1));
        ob.list[3].add(new DjNode(5, 4));
        ob.list[4].add(new DjNode(5, 2));

        int[] dist = new int[6];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[6];
        visited[0] = true;
        shortestPath(1, ob.list, dist, visited);
        for (int i : dist)
            System.out.println(i);
    }

    private static void shortestPath(int start, LinkedList<DjNode>[] list, int[] dist, boolean[] visited) {
        dist[start] = 0;
        for (int i = 1; i < 5; i++) {
            int minSource = getMinDistanceIndex(dist, visited);
            visited[minSource] = true;
            LinkedList<DjNode> paths = list[minSource];
            for (DjNode node : paths) {
                int v = node.v;
                int distance = node.dist;
                if (visited[v] == false && dist[minSource] + distance < dist[v]) {
                    dist[v] = dist[minSource] + distance;
                }
            }
        }
    }

    private static int getMinDistanceIndex(int[] dist, boolean[] visited) {
        int index = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i < dist.length; i++) {
            if (visited[i] == false && minDist > dist[i]) {
                minDist = dist[i];
                index = i;
            }
        }
        return index;
    }
}
