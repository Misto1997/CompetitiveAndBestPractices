package com.Leetcode.InterviewQuestionsHard.TreesAndGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {

    private static class InputReader {
        static int[] count;
        public static void main(String[] args) {
            InputReader input = new InputReader(System.in);
            OutputWriter out = new OutputWriter(System.out);
            int n = input.readInt();
            int[] nums = input.readIntArray(n);
            List<Integer> list = countSmaller(nums);
            out.printLine(list);
            out.close();
        }

        private static List<Integer> countSmaller(int[] nums) {
            List<Integer> res = new ArrayList<Integer>();

            count = new int[nums.length];
            int[] indexes = new int[nums.length];
            for(int i = 0; i < nums.length; i++){
                indexes[i] = i;
            }
            mergesort(nums, indexes, 0, nums.length - 1);
            for(int i = 0; i < count.length; i++){
                res.add(count[i]);
            }
            return res;
        }
        private static void mergesort(int[] nums, int[] indexes, int start, int end){
            if(end <= start){
                return;
            }
            int mid = (start + end) / 2;
            mergesort(nums, indexes, start, mid);
            mergesort(nums, indexes, mid + 1, end);

            merge(nums, indexes, start, end);
        }
        private static void merge(int[] nums, int[] indexes, int start, int end){
            int mid = (start + end) / 2;
            int left_index = start;
            int right_index = mid+1;
            int rightcount = 0;
            int[] new_indexes = new int[end - start + 1];

            int sort_index = 0;
            while(left_index <= mid && right_index <= end){
                if(nums[indexes[right_index]] < nums[indexes[left_index]]){
                    new_indexes[sort_index] = indexes[right_index];
                    rightcount++;
                    right_index++;
                }else{
                    new_indexes[sort_index] = indexes[left_index];
                    count[indexes[left_index]] += rightcount;
                    left_index++;
                }
                sort_index++;
            }
            while(left_index <= mid){
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += rightcount;
                left_index++;
                sort_index++;
            }
            while(right_index <= end){
                new_indexes[sort_index++] = indexes[right_index++];
            }
            for(int i = start; i <= end; i++){
                indexes[i] = new_indexes[i - start];
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



