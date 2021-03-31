package com.Leetcode.InterviewQuestionsHard.TreesAndGraph;

import java.io.*;
import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        String beginWord = input.readString();
        String endWord = input.readString();
        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            wordList.add(input.readString());
        }
        out.printLine(ladderLength(beginWord, endWord, wordList));
        out.close();
    }

    private static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set1 = new HashSet<>(wordList);
        Set<String> set2 = new HashSet<>(wordList);
        if (!set1.contains(endWord))
            return 0;
        Queue<String> fromStart = new LinkedList<>();
        fromStart.offer(beginWord);
        Queue<String> fromEnd = new LinkedList<>();
        fromEnd.offer(endWord);
        Map<String, Boolean> map = new HashMap<>();
        int counter = 2;
        while (!fromStart.isEmpty() && !fromEnd.isEmpty()) {
            int size = fromStart.size();
            for (int i = 0; i < size; i++) {
                char[] charArray = fromStart.poll().toCharArray();
                for (int j = 0; j < charArray.length; j++) {
                    char originalChar = charArray[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == originalChar)
                            continue;
                        charArray[j] = k;
                        if (String.valueOf(charArray).equals(endWord))
                            return counter;
                        if (set1.contains(String.valueOf(charArray))) {
                            if (map.getOrDefault(String.valueOf(charArray), false) == true)
                                return counter;
                            set1.remove(String.valueOf(charArray));
                            map.put(String.valueOf(charArray), true);
                            fromStart.add(String.valueOf(charArray));
                        }
                    }
                    charArray[j] = originalChar;
                }
            }
            counter++;
            size = fromEnd.size();
            for (int i = 0; i < size; i++) {
                char[] charArray = fromEnd.poll().toCharArray();
                for (int j = 0; j < charArray.length; j++) {
                    char originalChar = charArray[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == originalChar)
                            continue;
                        charArray[j] = k;
                        if (String.valueOf(charArray).equals(beginWord))
                            return counter;
                        if (set2.contains(String.valueOf(charArray))) {
                            if (map.getOrDefault(String.valueOf(charArray), false) == true)
                                return counter;
                            set2.remove(String.valueOf(charArray));
                            map.put(String.valueOf(charArray), true);
                            fromEnd.add(String.valueOf(charArray));
                        }
                    }
                    charArray[j] = originalChar;
                }
            }
            counter++;
        }
        return 0;
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
