package com.Leetcode.InterviewQuestionsEasy.String;

import java.io.*;
import java.util.InputMismatchException;

public class ImplementStrStr {
    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        String haystack = input.readString();
        String needle = input.readString();
        out.print(getIndexOfNeedle(haystack, needle));
        out.close();
    }

    private static int getIndexOfNeedle(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (haystack.length() < needle.length())
            return -1;
        long haystackHash = generateHash(haystack, needle.length());
        long needleHash = generateHash(needle, needle.length());
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (needleHash == haystackHash && matchNeedleWithHaystack(needle, 0, needle.length() - 1, haystack, i, i + haystack.length() - 1))
                return i;
            if (i < haystack.length() - needle.length())
                haystackHash = regenerateHash(haystack, i, i + needle.length(), haystackHash, needle.length() - 1);
        }
        return -1;
    }

    private static long generateHash(String str, int size) {
        long hashCode = 0;
        int prime = 547;
        for (int i = 0; i < size; i++)
            hashCode += str.charAt(i) * Math.pow(prime, i);
        return hashCode;
    }

    private static long regenerateHash(String haystack, int oldIndex, int newIndex, long oldHaystackHash, int size) {
        long newHashCode = 0;
        int prime = 547;
        newHashCode = (oldHaystackHash - haystack.charAt(oldIndex)) / prime;
        newHashCode += haystack.charAt(newIndex) * Math.pow(prime, size);
        return newHashCode;
    }

    private static boolean matchNeedleWithHaystack(String needle, int s1, int e1, String haystack, int s2, int e2) {
        while (s1 <= e1 && s2 <= e2) {
            if (needle.charAt(s1) != haystack.charAt(s2))
                return false;
            s1++;
            s2++;
        }
        return true;
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

