package com.practice;

import java.io.*;
import java.util.InputMismatchException;

public class MySegmentTree {

    public int[] constructTree(int ar[], int n) {
        int power = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int size = 2 * (int) (Math.pow(2, power) - 1);
        int[] segTree = new int[size];
        for (int i = 0; i < size; i++)
            segTree[i] = Integer.MAX_VALUE;
        constructMinSegmentTree(segTree, ar, 0, n - 1, 0);
        return segTree;
    }

    public void constructMinSegmentTree(int segTree[], int ar[], int low, int high, int pos) {
        if (low == high) {
            segTree[pos] = ar[low];
            return;
        }
        int mid = (low + high) / 2;
        constructMinSegmentTree(segTree, ar, low, mid, 2 * pos + 1);
        constructMinSegmentTree(segTree, ar, mid + 1, high, 2 * pos + 2);
        segTree[pos] = Math.min(segTree[2 * pos + 1], segTree[2 * pos + 2]);
    }

    public void updateLazy(int[] segTree, int[] lazyTree, int start, int end, int low, int high, int pos, int val) {
        if (low > high)
            return;
        if (lazyTree[pos] != 0) {
            segTree[pos] += lazyTree[pos];
            if (low != high) {
                lazyTree[2 * pos + 1] += lazyTree[pos];
                lazyTree[2 * pos + 2] += lazyTree[pos];
            }
            lazyTree[pos] = 0;
        }
        if (start > high || end < low)
            return;
        if (start <= low && end >= high) {
            segTree[pos] += val;
            if (low != high) {
                lazyTree[2 * pos + 1] += val;
                lazyTree[2 * pos + 2] += val;
            }
            return;
        }
        int mid = (low + high) / 2;
        updateLazy(segTree, lazyTree, start, end, low, mid, 2 * pos + 1, val);
        updateLazy(segTree, lazyTree, start, end, mid + 1, high, 2 * pos + 2, val);
        segTree[pos] = Math.min(segTree[2 * pos + 1], segTree[2 * pos + 2]);

    }

    public int rangeQuery(int[] segTree, int[] lazyTree, int start, int end, int low, int high, int pos) {
        if (low > high)
            return Integer.MAX_VALUE;
        if (lazyTree[pos] != 0) {
            segTree[pos] += lazyTree[pos];
            if (low != high) {
                lazyTree[2 * pos + 1] += lazyTree[pos];
                lazyTree[2 * pos + 2] += lazyTree[pos];
            }
            lazyTree[pos] = 0;
        }
        if (start > high || end < low)
            return Integer.MAX_VALUE;
        if (start <= low && end >= high)
            return segTree[pos];
        int mid = (low + high) / 2;
        return Math.min(rangeQuery(segTree, lazyTree, start, end, low, mid, 2 * pos + 1),
                rangeQuery(segTree, lazyTree, start, end, mid + 1, high, 2 * pos + 2));
    }

}


class SegCall {
    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = input.readInt();
        int[] ar = input.readIntArray(n);
        MySegmentTree tree = new MySegmentTree();
        int[] segTree = tree.constructTree(ar, n);
        int[] lazyTree = new int[segTree.length];
        int t = input.readInt();
        while (t-- > 0) {
            int start = input.readInt();
            int end = input.readInt();
            System.out.println(tree.rangeQuery(segTree, lazyTree, start, end, 0, n - 1, 0));
            tree.updateLazy(segTree, lazyTree, start, end, 0, n - 1, 0, 10);
            System.out.println(tree.rangeQuery(segTree, lazyTree, start, end, 0, n - 1, 0));
            out.printLine();
        }
        out.close();
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
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = readInt();
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
            boolean isSpaceChar(int ch);
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

