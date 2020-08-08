package com.practice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class DeleteCutGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int ar[][] = new int[m][2];
        graph g = new graph(n);
        for (int i = 0; i < m; i++) {
            ar[i][0] = sc.nextInt();
            ar[i][1] = sc.nextInt();
            g.addEdge(ar[i][0] - 1, ar[i][1] - 1);
        }
        // g.printGraph();
        int num = 3;
        for (int i = 0; i < m; i++) {
            num = checkOnRemovingEdge(g, ar[i][0] - 1, ar[i][1] - 1);
            if (num == 1) {
                System.out.println(1 + " " + 0);
                break;
            } else
                continue;
        }
        if (num == 3)
            System.out.println(0 + " " + 0);
        if (num == 0)
            System.out.println(0 + " " + 1);

    }

    private static int checkOnRemovingEdge(graph g, int a, int b) {
        g.list[a].remove(Integer.toString(b));
        g.list[b].remove(Integer.toString(a));
        int flag = isConnected(g, a);
        g.addEdge(a, b);
        return flag;
    }

    private static int isConnected(graph g, int a) {
        boolean visited[] = new boolean[g.v];
        for (int i = 0; i < g.v; i++) {
            visited[i] = false;
        }
        int count = bfs(g, visited, a, 0);
        for (int i = 0; i < g.v; i++) {
            if (visited[i] == false) {
                if (count == g.v - count && count % 2 == 0)
                    return 1;
                else
                    return 0;
            }
        }
        return 3;
    }

    private static int bfs(graph g, boolean[] visited, int a, int count) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[a] = true;
        queue.add(a);
        while (queue.size() != 0) {
            a = queue.poll();
            count++;
            Iterator<String> i = g.list[a].listIterator();
            while (i.hasNext()) {
                int n = Integer.parseInt(i.next());
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }

            }
        }
        return count;
    }
}

class graph {
    int v;
    LinkedList<String> list[];

    graph(int v) {
        this.v = v;
        list = new LinkedList[v];
        for (int i = 0; i < v; i++)
            list[i] = new LinkedList<>();

    }

    void addEdge(int src, int des) {
        list[src].add(Integer.toString(des));
        list[des].add(Integer.toString(src));
    }

    /* void printGraph() {*/
    /*     for (int v = 0; v < this.v; v++) {*/
    /*         System.out.println("Adjacency list of vertex " + v);*/
    /*         System.out.print("head");*/
    /*         for (Integer pCrawl : list[v]) {*/
    /*             System.out.print(" -> " + pCrawl);*/
    /*         }*/
    /*         System.out.println("\n");*/
    /*     }*/
    /* }*/
}
