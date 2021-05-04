package com.Leetcode.InterviewQuestionsHard.Backtracking;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

class TrieNode {
    TrieNode[] childNode = new TrieNode[26];
    String word;
}

class WordSearch2 {
    private static class InputReader {


        public static void main(String[] args) {
            InputReader input = new InputReader(System.in);
            OutputWriter out = new OutputWriter(System.out);
            int m = input.readInt();
            int n = input.readInt();
            char[][] board = new char[m][n];
            for (int i = 0; i < m; i++) {
                board[i] = input.readCharArray(n);
            }
            String[] words = input.readStringArray(3);
            List<String> list = findWords(board, words);
            out.printLine(list);
            out.close();
        }

        private static List<String> findWords(char[][] board, String[] words) {
            List<String> listOfWords = new ArrayList<>();
            TrieNode root = buildTrieNode(words);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    dfs(i, j, board, listOfWords, root);
                }
            }

            return listOfWords;
        }

        private static void dfs(int i, int j, char[][] board, List<String> listOfWords, TrieNode root) {
            char currentChar = board[i][j];
            int index = currentChar - 'a';
            if (currentChar == '1' || root.childNode[index] == null)
                return;
            root = root.childNode[index];
            if (root.word != null) {
                listOfWords.add(root.word);
                root.word = null;
            }
            board[i][j] = '1';
            if (i > 0) {
                dfs(i - 1, j, board, listOfWords, root);
            }
            if (i < board.length - 1) {
                dfs(i + 1, j, board, listOfWords, root);
            }
            if (j > 0) {
                dfs(i, j - 1, board, listOfWords, root);
            }
            if (j < board[0].length - 1) {
                dfs(i, j + 1, board, listOfWords, root);

            }
            board[i][j] = currentChar;

        }

        private static TrieNode buildTrieNode(String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode temp = root;
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (temp.childNode[index] == null) {
                        temp.childNode[index] = new TrieNode();
                    }
                    temp = temp.childNode[index];
                }
                temp.word = word;
            }
            return root;
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

