package com.practice;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Stack;


class Call {
    long a[] = {1, 1, 1, 1};
    long b[] = {1, 1, 1, 1};
}

public class ReadyBitwise {
    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = input.readInt();
        long inverse = power(4, 998244351);
        long mod = 998244353;
        while (t-- > 0) {
            String str = input.readString();
            Stack<Character> operator = new Stack<>();
            Stack<Call> operand = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(')
                    continue;
                else {
                    if (str.charAt(i) == '^' || str.charAt(i) == '&' || str.charAt(i) == '|') {
                        operator.push(str.charAt(i));
                    } else {
                        if (str.charAt(i) == ')') {
                            Call cal1 = operand.peek();
                            operand.pop();
                            Call cal2 = operand.peek();
                            operand.pop();
                            char op = operator.peek();
                            operator.pop();
                            Call cal3 = new Call();
                            if (op == '^') {
                                cal3.a[0] = ((cal1.a[1] * cal2.a[1]) % mod
                                        + (cal1.a[0] * cal2.a[0]) % mod
                                        + (cal1.a[2] * cal2.a[2]) % mod
                                        + (cal1.a[3] * cal2.a[3]) % mod) % mod;

                                cal3.a[1] = ((cal1.a[1] * cal2.a[0]) % mod
                                        + (cal1.a[0] * cal2.a[1]) % mod
                                        + (cal1.a[2] * cal2.a[3]) % mod
                                        + (cal1.a[3] * cal2.a[2]) % mod) % mod;

                                cal3.a[2] = ((cal1.a[1] * cal2.a[3]) % mod
                                        + (cal1.a[0] * cal2.a[2]) % mod
                                        + (cal1.a[2] * cal2.a[0]) % mod
                                        + (cal1.a[3] * cal2.a[1]) % mod) % mod;

                                cal3.a[3] = ((cal1.a[1] * cal2.a[2]) % mod
                                        + (cal1.a[0] * cal2.a[3]) % mod
                                        + (cal1.a[2] * cal2.a[1]) % mod
                                        + (cal1.a[3] * cal2.a[0]) % mod) % mod;

                                long temp = cal2.b[0] + cal1.b[0];


                                cal3.b[0] = cal3.b[1] = cal3.b[2] = cal3.b[3] = temp;
                                operand.push(cal3);
                            }
                            if (op == '|') {
                                cal3.a[0] = (cal1.a[0] * cal2.a[0]) % mod;

                                cal3.a[1] = ((cal1.a[0] * cal2.a[1]) % mod
                                        + (cal1.a[1] * cal2.a[0]) % mod
                                        + (cal1.a[1] * cal2.a[1]) % mod
                                        + (cal1.a[1] * cal2.a[2]) % mod
                                        + (cal1.a[1] * cal2.a[3]) % mod
                                        + (cal1.a[2] * cal2.a[1]) % mod
                                        + (cal1.a[2] * cal2.a[3]) % mod
                                        + (cal1.a[3] * cal2.a[1]) % mod
                                        + (cal1.a[3] * cal2.a[2]) % mod) % mod;

                                cal3.a[2] = ((cal1.a[0] * cal2.a[2]) % mod
                                        + (cal1.a[2] * cal2.a[0]) % mod
                                        + (cal1.a[2] * cal2.a[2]) % mod) % mod;

                                cal3.a[3] = ((cal1.a[0] * cal2.a[3]) % mod
                                        + (cal1.a[3] * cal2.a[0]) % mod
                                        + (cal1.a[3] * cal2.a[3]) % mod) % mod;
                                long temp = cal2.b[0] + cal1.b[0];

                                cal3.b[0] = cal3.b[1] = cal3.b[2] = cal3.b[3] = temp;
                                operand.push(cal3);

                            }
                            if (op == '&') {

                                cal3.a[1] = (cal1.a[1] * cal2.a[1]) % mod;

                                cal3.a[0] = ((cal1.a[1] * cal2.a[0]) % mod
                                        + (cal1.a[0] * cal2.a[1]) % mod
                                        + (cal1.a[0] * cal2.a[0]) % mod
                                        + (cal1.a[0] * cal2.a[2]) % mod
                                        + (cal1.a[0] * cal2.a[3]) % mod
                                        + (cal1.a[2] * cal2.a[0]) % mod
                                        + (cal1.a[2] * cal2.a[3]) % mod
                                        + (cal1.a[3] * cal2.a[0]) % mod
                                        + (cal1.a[3] * cal2.a[2]) % mod) % mod;

                                cal3.a[2] = ((cal1.a[1] * cal2.a[2]) % mod
                                        + (cal1.a[2] * cal2.a[1]) % mod
                                        + (cal1.a[2] * cal2.a[2]) % mod) % mod;

                                cal3.a[3] = ((cal1.a[1] * cal2.a[3]) % mod
                                        + (cal1.a[3] * cal2.a[1]) % mod
                                        + (cal1.a[3] * cal2.a[3]) % mod) % mod;
                                long temp = cal2.b[0] + cal1.b[0];

                                cal3.b[0] = cal3.b[1] = cal3.b[2] = cal3.b[3] = temp;
                                operand.push(cal3);

                            }
                        } else {
                            if (str.charAt(i) == '#') {
                                Call cal4 = new Call();
                                operand.push(cal4);
                            }
                        }
                    }
                }
            }
            Call result = operand.peek();

            long res1 = ((result.a[0] % mod) * (power(inverse, result.b[0])) % mod) % mod;
            long res2 = ((result.a[1] % mod) * (power(inverse, result.b[0])) % mod) % mod;

            long res3 = ((result.a[2] % mod) * (power(inverse, result.b[0])) % mod) % mod;
            long res4 = ((result.a[3] % mod) * (power(inverse, result.b[0])) % mod) % mod;

            out.printLine(res1 + " " + res2 + " " + res3 + " " + res4);
        }
        out.close();
    }

    private static long power(long base, long exp) {
        base %= 998244353;
        long val = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) val = (val * base) % 998244353;
            base = (base * base) % 998244353;
            exp >>= 1;
        }
        return val;
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

