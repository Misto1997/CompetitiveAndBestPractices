package com.Leetcode.InterviewQuestionsMedium.TreeAndGraph;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    traverseCell(grid, i, j);
                }
            }

        return count;
    }

    private void traverseCell(char[][] grid, int i, int j) {
        grid[i][j] = 'a';
        if (i > 0) {
            if (grid[i - 1][j] == '1')
                traverseCell(grid, i - 1, j);
        }
        if (j > 0) {
            if (grid[i][j - 1] == '1')
                traverseCell(grid, i, j - 1);
        }
        if (i < grid.length - 1) {
            if (grid[i + 1][j] == '1')
                traverseCell(grid, i + 1, j);
        }
        if (j < grid[0].length - 1) {
            if (grid[i][j + 1] == '1')
                traverseCell(grid, i, j + 1);
        }
    }
}
