package com.codechef;

import java.io.*;
import java.util.InputMismatchException;

class TicTacToe {
    private static class InputReader {


        public static void main(String[] args) {
            InputReader input = new InputReader(System.in);
            OutputWriter out = new OutputWriter(System.out);
            int t = input.readInt();
            while (t-- > 0) {
                char[][] blocks = new char[3][3];
                for (int i = 0; i < 3; i++) {
                    blocks[i] = input.readString().toCharArray();
                }
                out.printLine(checkBlocks(blocks));
            }
            out.close();
        }

        private static int checkBlocks(char[][] blocks) {
            int xCount = 0;
            int oCount = 0;
            int _Count = 0;
            int xWinCount = 0;
            int oWinCount = 0;

            for (int i = 0; i < 3; i++) {
                int localXCount = 0;
                int localOCount = 0;

                for (int j = 0; j < 3; j++) {
                    if (blocks[i][j] == 'X')
                        localXCount++;
                    else if (blocks[i][j] == 'O')
                        localOCount++;
                    else
                        _Count++;
                }

                xCount += localXCount;
                oCount += localOCount;
                if (localXCount == 3) {
                    xWinCount += 1;
                } else if (localOCount == 3) {
                    oWinCount += 1;
                }
                localXCount = 0;
                localOCount = 0;

                for (int j = 0; j < 3; j++) {
                    if (blocks[j][i] == 'X')
                        localXCount++;
                    else if (blocks[j][i] == 'O')
                        localOCount++;
                }

                if (localXCount == 3)
                    xWinCount += 1;
                else if (localOCount == 3)
                    oWinCount += 1;
            }

            if (blocks[0][0] == blocks[1][1] && blocks[1][1] == blocks[2][2]) {
                if (blocks[0][0] == 'X')
                    xWinCount += 1;
                else if (blocks[0][0] == 'O')
                    oWinCount += 1;
            }

            if (blocks[0][2] == blocks[1][1] && blocks[1][1] == blocks[2][0]) {
                if (blocks[0][2] == 'X')
                    xWinCount += 1;
                else if (blocks[0][2] == 'O')
                    oWinCount += 1;
            }

            if ((xWinCount > 0 && oWinCount > 0) || xCount - oCount > 1 || oCount - xCount > 0 || (oWinCount > 0 && xCount > oCount) || (xWinCount > 0 && xCount <= oCount))
                return 3;

            if (xWinCount > 0 || oWinCount > 0 || _Count == 0)
                return 1;

            return 2;
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

