package com.codechef;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;

class ModularEquation {
    private static class InputReader {


        public static void main(String[] args) {
            InputReader input = new InputReader(System.in);
            OutputWriter out = new OutputWriter(System.out);
            int[] dp = calculateNoOFactors();
            int t = input.readInt();
            while (t-- > 0) {
                int[] ar = dp.clone();
                int n = input.readInt();
                int m = input.readInt();
                out.printLine(getPair2(n, m));
                out.printLine(getPair6(n, m));
                //out.printLine(getPair4(n, m));
            }
            out.close();
        }

        private static long getPair2(int n, int m) {
            long count = 0;
            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if ((m % i) % j == (m % j) % i) {
                        count++;
                    }
                }
            }
            return count;
        }

        private static long getPair5(int n, int m, int[] ar1) {
            long count = 0;
            long oCount = 1;

            for (int i = m > n ? m : n; i >= 2; i--) {
                if (i <= (m > n ? n : m)) {
                    count += ar1[m - m % i];
                    oCount += 1;
                }
                ar1[m - m % i] -= 1;
            }
            if (n > m) {
                for (int i = n; i > m; i--) {
                    count += oCount;
                    oCount += 1;
                }
            }
            return count;
        }

        private static long getPair6(int n, int m) {
            long count = 0;
            long[] ar = new long[n + 1];
            Arrays.fill(ar, 1);
            for (int i = 2; i <= n; i++) {
                int num = m % i;
                count += ar[num];
                for (int j = num; j <= n; j += i) {
                    ar[j]++;
                }
            }
            return count;
        }

        private static long getPair4(int n, int m) {
            long count = 0;
            long[] ar = new long[m + 1];

            for (int i = 2; i <= n; i++) {
                count += ar[m - m % i] + 1;
                int num = (m / 2) + (i - (m / 2) % i);
                ar[0] += 1;
                for (int j = num; j <= m - m % i; j += i) {
                    ar[j] += 1;
                }
            }
            return count;
        }

        private static int[] calculateNoOFactors() {
            int[] ar = new int[500001];
            for (int i = 2; i < 500001; i++) {
                for (int j = i; j < 500001; j += i)
                    ar[j] += 1;
            }
            return ar;
        }

        private static long getPair3(int n, int m) {
            long count = n - 1;
            int size = n;
            int size1 = n;
            if (n > m) {
                int gNumCount = n - m;
                count += (gNumCount * (gNumCount - 1)) / 2;
                count += gNumCount * (m - 1);
                size = m;
                size1 = m / 2;
            }

            for (int i = 2; i <= size1; i++) {
                for (int j = i + 1; j <= size; j++) {
                    if ((m % i) % j == (m % j) % i) {
                        System.out.println(i + " " + j);
                        count++;
                    }
                }
            }
            return count;
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

