package com.practice;

import java.util.LinkedList;
import java.util.Stack;

class Graph {
    int v;
    LinkedList<Integer> list[];

    Graph(int v) {
        this.v = v;
        list = new LinkedList[this.v];
        for (int i = 0; i < v; i++)
            list[i] = new LinkedList<>();
    }

    void addEdge(int a, int b) {
        list[a].add(b);
    }

    void topSort() {
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (visited[i] == false)
                Util(i, stack, visited);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private void Util(int i, Stack<Integer> stack, boolean[] visited) {
        list[i].forEach((n) -> {
            if (visited[n] == false)
                topSortUtil(n, stack, visited);
        });
        topSortUtil(i, stack, visited);
    }

    private void topSortUtil(Integer n, Stack<Integer> stack, boolean[] visited) {
        visited[n] = true;
        list[n].forEach((m) -> {
            if (visited[m] == false)
                topSortUtil(m, stack, visited);
        });
        stack.push(n);
    }
}

public class TopologicalSort {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        // g.addEdge(3, 4);
        g.topSort();
    }
}
