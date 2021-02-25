package com.hackerrank;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class BobMaze {
    private static int min=Integer.MAX_VALUE;

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int x = input.readInt();
        int y = input.readInt();
        List<List<Integer>> list = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                int kk=input.readInt();
                l.add(kk);
            }
            list.add(new ArrayList<>(l));
        }
        System.out.println(minMoves(list, x, y));

        out.close();
    }

    public static int minMoves(List<List<Integer>> maze, int x, int y) {
        int i=0,j=0;
        int coinCount=0;
        int row=maze.size();
        int column=maze.get(0).size();
        int[][] ar=new int[row][column];
        boolean[][] visited=new boolean[row][column];

        for(List<Integer> list:maze){
            for(int val:list){
                ar[i][j]=val;
                j++;
            }
            i++;
            j=0;
        }
        for(i=0;i<row;i++){
            for(j=0;j<column;j++){
                if(ar[i][j]==2)
                    coinCount++;

            }
        }
        allSortedPathSolution(ar,visited,0,0,x,y,row,column,coinCount,0);

        if(min!=Integer.MAX_VALUE)
            return min;
        else
            return -1;

    }

    private static void allSortedPathSolution(int[][] ar,boolean[][] visited,int bobX,int bobY,int AliceX,int AliceY,int row,int column,int coinCount,int shortestPath){
        if(bobX<0 || bobX>=row || bobY<0 || bobY >=column || ar[bobX][bobY]==1 || visited[bobX][bobY]==true)
            return;

        if(bobX==AliceX && bobY==AliceY && coinCount==0){
            min=Integer.min(min,shortestPath);
            return;
        }
        if(ar[bobX][bobY]==2)
            coinCount--;
        visited[bobX][bobY]=true;

        allSortedPathSolution(ar,visited,bobX+1,bobY,AliceX,AliceY,row,column,coinCount,shortestPath+1);
        allSortedPathSolution(ar,visited,bobX,bobY+1,AliceX,AliceY,row,column,coinCount,shortestPath+1);
        allSortedPathSolution(ar,visited,bobX,bobY-1,AliceX,AliceY,row,column,coinCount,shortestPath+1);
        allSortedPathSolution(ar,visited,bobX-1,bobY,AliceX,AliceY,row,column,coinCount,shortestPath+1);
        visited[bobX][bobY]=false;
    }


  /*  private static int minMoves(int[][] ar, int x, int y) {
        int min
    }*/

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
