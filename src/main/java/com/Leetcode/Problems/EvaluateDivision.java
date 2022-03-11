package com.Leetcode.Problems;

import java.io.*;
import java.util.*;

public class EvaluateDivision {
    private static class InputReader {

        public static void main(String[] args) {
            InputReader input = new InputReader(System.in);
            OutputWriter out = new OutputWriter(System.out);
            int noOfEquations = input.readInt();
            List<List<String>> equations = new ArrayList<>();
            for (int i = 0; i < noOfEquations; i++) {
                List<String> list = new ArrayList<>();
                list.add(input.readString());
                list.add(input.readString());
                equations.add(list);
            }
            double[] values = input.readDoubleArray(noOfEquations);
            int noOfQueries = input.readInt();
            List<List<String>> queries = new ArrayList<>();
            for (int i = 0; i < noOfQueries; i++) {
                List<String> list = new ArrayList<>();
                list.add(input.readString());
                list.add(input.readString());
                queries.add(list);
            }

            double[] result = calcEquation(equations, values, queries);
            out.print(Arrays.asList(result));

            out.close();
        }

        private static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            Map<String, Map<String, Double>> graphRepresentation = new HashMap<>();
            createGraphRepresentation(graphRepresentation, equations, values);
            double[] result = new double[queries.size()];
            findResult(queries, result, graphRepresentation);
            return result;
        }

        private static void findResult(List<List<String>> queries, double[] result, Map<String, Map<String, Double>> graphRepresentation) {
            for (int i = 0; i < queries.size(); i++) {
                result[i] = getResult(queries.get(i).get(0), queries.get(i).get(1), new HashSet<>(), graphRepresentation);
            }
        }

        private static double getResult(String u, String v, Set<String> visited, Map<String, Map<String, Double>> graphRepresentation) {
            if (graphRepresentation.containsKey(u)) {
                if (graphRepresentation.get(u).containsKey(v)) {
                    return graphRepresentation.get(u).get(v);
                } else {
                    visited.add(u);
                    for (Map.Entry<String, Double> entry : graphRepresentation.get(u).entrySet()) {
                        if (!visited.contains(entry.getKey())) {
                            double val = getResult(entry.getKey(), v, visited, graphRepresentation);
                            if (val != -1.0) {
                                return val * entry.getValue();
                            }
                        }
                    }
                }
            }
            return -1.0;
        }

        private static void createGraphRepresentation(Map<String, Map<String, Double>> graphRepresentation, List<List<String>> equations, double[] values) {
            for (int i = 0; i < values.length; i++) {
                String u = equations.get(i).get(0);
                String v = equations.get(i).get(1);
                graphRepresentation.putIfAbsent(u, new HashMap<>());
                graphRepresentation.get(u).put(v, values[i]);
                graphRepresentation.putIfAbsent(v, new HashMap<>());
                graphRepresentation.get(v).put(u, 1 / values[i]);
            }
        }


        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = readInt();
            }
            return a;
        }

        public String[] readStringArray(int n) {
            String[] a = new String[n];
            for (int i = 0; i < n; i++) {
                a[i] = readString();
            }
            return a;
        }

        public char[] readCharArray(int n) {
            char[] a = new char[n];
            for (int i = 0; i < n; i++)
                a[i] = readString().charAt(0);
            return a;
        }

        public double[] readDoubleArray(int n) {
            double[] a = new double[n];
            for (int i = 0; i < n; i++) {
                a[i] = readDouble();
            }
            return a;
        }

        public long[] readLongArray(int n) {
            long[] a = new long[n];
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
            boolean isSpaceChar(int ch);
        }
    }

    private static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
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
