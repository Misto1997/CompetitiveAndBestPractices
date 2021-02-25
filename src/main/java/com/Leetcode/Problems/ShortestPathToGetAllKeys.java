package com.Leetcode.Problems;

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathToGetAllKeys {

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int row = input.readInt();
        String[] grid = new String[row];
        for (int i = 0; i < row; i++)
            grid[i] = input.readString();
        System.out.println(shortestPathAllKeys(grid));

        out.close();
    }

    private static int shortestPathAllKeys(String[] grid) {
        int x = -1, y = -1, row = grid.length, col = grid[0].length(), finalState = 0;
        int steps = 0;
        char[][] ar = new char[row][col];
        boolean[][][] visited = new boolean[row][col][65];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ar[i][j] = grid[i].charAt(j);
                if (ar[i][j] == '@') {
                    x = i;
                    y = j;
                }
                if (ar[i][j] >= 'a' && ar[i][j] <= 'f')
                    finalState |= (1 << (ar[i][j] - 'a'));
            }
        }
        int[][] dir = new int[][]{{1, 0, -1, 0}, {0, 1, 0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0});
        visited[x][y][0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur[] = queue.poll();
                if (cur[2] == finalState)
                    return steps;
                for (int i = 0; i < 4; i++) {
                    int newX = cur[0] + dir[0][i];
                    int newY = cur[1] + dir[1][i];
                    int key = cur[2];
                    if (newX < 0 || newX >= row || newY < 0 || newY >= col)
                        continue;
                    if (ar[newX][newY] == '#')
                        continue;
                    if (ar[newX][newY] >= 'A' && ar[newX][newY] <= 'F' && ((key >> ar[newX][newY] - 'A') & 1) == 0)
                        continue;
                    if (ar[newX][newY] >= 'a' && ar[newX][newY] <= 'f') {
                        key |= (1 << (ar[newX][newY] - 'a'));
                    }
                    if (!visited[newX][newY][key]) {
                        queue.add(new int[]{newX, newY, key});
                        visited[newX][newY][key] = true;
                    }
                }
            }
            steps++;

        }
        return -1;
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int[] readIntArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = readInt();
            }
            return a;
        }

        public char[] readCharArray(int n) {
            char a[] = new char[n];
            for (int i = 0; i < n; i++)
                a[i] = readString().charAt(0);
            return a;
        }

        public double[] readDoubleArray(int n) {
            double a[] = new double[n];
            for (int i = 0; i < n; i++) {
                a[i] = readDouble();
            }
            return a;
        }

        public long[] readLongArray(int n) {
            long a[] = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = readLong();
            }
            return a;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public double readDouble() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, readInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public long readLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }

    private static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void flush() {
            writer.flush();
        }
    }
}
