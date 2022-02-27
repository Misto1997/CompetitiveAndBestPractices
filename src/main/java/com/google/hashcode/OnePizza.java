package com.google.hashcode;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class ClientChoice {
    List<String> likeIngredients;
    List<String> dislikeIngredients;

    ClientChoice() {
        likeIngredients = new ArrayList<>();
        dislikeIngredients = new ArrayList<>();
    }
}

class PreferenceDetails {
    int count;
    List<Integer> clientList;

    PreferenceDetails() {
        clientList = new ArrayList<>();
    }
}

public class OnePizza {
    private static class InputReader {

        public static void main(String[] args) {
            InputReader input = new InputReader(System.in);
            OutputWriter out = new OutputWriter(System.out);
            int noOfPotentialClient = input.readInt();
            ClientChoice[] clientArray = new ClientChoice[noOfPotentialClient];
            for (int i = 0; i < noOfPotentialClient; i++) {
                clientArray[i] = new ClientChoice();
                clientArray[i].likeIngredients = Arrays.asList(input.readSpaceString().split(" "));
                clientArray[i].dislikeIngredients = Arrays.asList(input.readSpaceString().split(" "));
            }
            out.printLine(findIngredients(clientArray));

            out.close();
        }

        private static String findIngredients(ClientChoice[] clientArray) {
            Map<String, PreferenceDetails> likesMap = new HashMap<>();
            Map<String, PreferenceDetails> disLikesMap = new HashMap<>();

            setLikesAndDisLikes(likesMap, disLikesMap, clientArray);
            Map<String, PreferenceDetails> sortedDisLikesMap = disLikesMap.entrySet().stream().sorted(Comparator.comparingInt(i -> i.getValue().count)).collect(Collectors.toMap(Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1, LinkedHashMap::new));
            findSuitableIngredients(clientArray, likesMap, sortedDisLikesMap);

            return getFormattedAnswer(likesMap);
        }

        private static void findSuitableIngredients(ClientChoice[] clientArray, Map<String, PreferenceDetails> likesMap, Map<String, PreferenceDetails> sortedDisLikesMap) {
            for (Map.Entry<String, PreferenceDetails> entry : sortedDisLikesMap.entrySet()) {
                if (likesMap.containsKey(entry.getKey())) {
                    List<Integer> list;
                    if (likesMap.get(entry.getKey()).count <= entry.getValue().count) {
                        list = likesMap.get(entry.getKey()).clientList;
                        likesMap.remove(entry.getKey());
                    } else {
                        list = sortedDisLikesMap.get(entry.getKey()).clientList;
                        //sortedDisLikesMap.remove(entry.getKey());
                    }
                    clearPreferences(list, clientArray, likesMap, sortedDisLikesMap);
                }
            }
        }

        private static void clearPreferences(List<Integer> list, ClientChoice[] clientArray, Map<String, PreferenceDetails> likesMap, Map<String, PreferenceDetails> sortedDisLikesMap) {
            for (Integer i : list) {
                ClientChoice ob = clientArray[i];
                for (String choice : ob.likeIngredients) {
                    if (likesMap.containsKey(choice)) {
                        PreferenceDetails oo = likesMap.get(choice);
                        oo.count--;
                        if (oo.count <= 0)
                            likesMap.remove(choice);
                        else
                            oo.clientList.remove(Integer.valueOf(i));
                    }
                }
                for (String choice : ob.dislikeIngredients) {
                    if (sortedDisLikesMap.containsKey(choice)) {
                        PreferenceDetails oo = sortedDisLikesMap.get(choice);
                        oo.count--;
                       /* if (oo.count <= 0)
                            sortedDisLikesMap.remove(choice);
                        else*/
                            oo.clientList.remove(Integer.valueOf(i));
                    }
                }
            }
        }

        private static void setLikesAndDisLikes(Map<String, PreferenceDetails> likesMap, Map<String, PreferenceDetails> disLikesMap, ClientChoice[] clientArray) {
            for (ClientChoice client : clientArray) {
                for (int i = 1; i < client.likeIngredients.size(); i++) {
                    if (!likesMap.containsKey(client.likeIngredients.get(i))) {
                        likesMap.put(client.likeIngredients.get(i), new PreferenceDetails());
                    }
                    PreferenceDetails ob = likesMap.get(client.likeIngredients.get(i));
                    ob.count++;
                    ob.clientList.add(i);
                }
                for (int i = 1; i < client.dislikeIngredients.size(); i++) {
                    if (!disLikesMap.containsKey(client.dislikeIngredients.get(i))) {
                        disLikesMap.put(client.dislikeIngredients.get(i), new PreferenceDetails());
                    }
                    PreferenceDetails ob = disLikesMap.get(client.dislikeIngredients.get(i));
                    ob.count++;
                    ob.clientList.add(i);
                }
            }
        }

        private static String getFormattedAnswer(Map<String, PreferenceDetails> likesMap) {
            StringJoiner ans = new StringJoiner(" ");
            ans.add(String.valueOf(likesMap.size()));
            for (String ingredient : likesMap.keySet()) {
                ans.add(ingredient);
            }
            return ans.toString();
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
