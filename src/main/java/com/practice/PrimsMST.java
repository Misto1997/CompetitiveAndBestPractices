package com.practice;

import java.util.Arrays;
import java.util.LinkedList;

class MstNode {
    int v;
    int dist;

    MstNode(int v, int dist) {
        this.v = v;
        this.dist = dist;
    }
}

class MstGraph {
    int v;
    LinkedList<MstNode>[] list;

    MstGraph(int v) {
        this.v = v;
        list = new LinkedList[v];
        for (int i = 0; i < v; i++)
            list[i] = new LinkedList();
    }
}
public class PrimsMST {
    public static void main(String[] args) {
        MstGraph ob = new MstGraph(6);
        ob.list[1].add(new MstNode(2, 2));
        ob.list[1].add(new MstNode(3, 2));
        ob.list[2].add(new MstNode(3, 1));
        ob.list[2].add(new MstNode(4, 4));
        ob.list[3].add(new MstNode(4, 1));
        ob.list[3].add(new MstNode(5, 4));
        ob.list[4].add(new MstNode(5, 2));

        int[] dist = new int[6];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[6];
        visited[0] = true;
        shortestPath(1, ob.list, dist, visited);
        for (int i : dist)
            System.out.println(i);
    }

    private static void shortestPath(int start, LinkedList<MstNode>[] list, int[] dist, boolean[] visited) {
        dist[start] = 0;
        for (int i = 1; i < 5; i++) {
            int minSource = getMinDistanceIndex(dist, visited);
            visited[minSource] = true;
            LinkedList<MstNode> paths = list[minSource];
            for (MstNode node : paths) {
                int v = node.v;
                int distance = node.dist;
                if (visited[v] == false &&  distance < dist[v]) {
                    dist[v] =  distance;
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
