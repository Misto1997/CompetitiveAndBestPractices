package com.Leetcode.InterviewQuestionsHard.TreesAndGraph;

import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Stack;

class CourseSchedule2 {
    private static class InputReader {

        public static void main(String[] args) {
            InputReader input = new InputReader(System.in);
            OutputWriter out = new OutputWriter(System.out);
            int numCourses = input.readInt();
            int[][] prerequisites = new int[3][2];
            for (int i = 0; i < prerequisites.length; i++)
                prerequisites[i] = input.readIntArray(2);

            int[] ar = findOrder(numCourses, prerequisites);
            for (int i : ar) {
                out.print(i + " ");
            }
            out.close();
        }

        private static int[] findOrder(int numCourses, int[][] prerequisites) {
            Graph graph = new Graph(numCourses);
            insertElement(graph, prerequisites);
            Stack<Integer> stack = new Stack<>();
            if (isCyclePresent(graph, stack))
                return new int[0];
            int[] ar = new int[stack.size()];
            for (int i = 0; i < ar.length; i++)
                ar[i] = stack.pop();
            return ar;
        }

        private static boolean isCyclePresent(Graph graph, Stack<Integer> stack1) {
            boolean[] visited = new boolean[graph.v];
            boolean[] stack = new boolean[graph.v];
            for (int i = 0; i < graph.v; i++) {
                if (checkForCycle(i, graph, visited, stack, stack1))
                    return true;
            }
            return false;
        }

        private static boolean checkForCycle(int i, Graph graph, boolean[] visited, boolean[] stack, Stack<Integer> stack1) {
            if (stack[i])
                return true;
            if (visited[i])
                return false;
            visited[i] = true;
            stack[i] = true;
            List<Integer> nodesToBeVisited = graph.list.get(i);
            for (Integer nodeIndex : nodesToBeVisited) {
                if (checkForCycle(nodeIndex, graph, visited, stack, stack1))
                    return true;
            }
            stack1.push(i);
            stack[i] = false;
            return false;
        }

        private static void insertElement(Graph graph, int[][] prerequisites) {
            for (int i = 0; i < prerequisites.length; i++) {
                graph.list.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }
        }

        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public int[] readIntArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = readInt();
            }
            return a;
        }

        public String[] readStringArray(int n) {
            String a[] = new String[n];
            for (int i = 0; i < n; i++) {
                a[i] = readString();
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

        public InputReader(InputStream stream) {
            this.stream = stream;
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

        public String readSpaceString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (c != '\n');
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

