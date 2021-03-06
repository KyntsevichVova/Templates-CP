package com.petukhovsky.solve.lib;

import java.io.*;

/**
 * Created by petuh on 1/30/2016.
 */
public class FastScanner {

    final static int BUFFER_SIZE = 65536;

    BufferedReader br;
    char[] buf = new char[BUFFER_SIZE];
    int len = 0;
    int it = 0;
    boolean end = false;

    boolean delim(char c) {
        return c == ' ' || c == '\n' || c == '\r';
    }

    void fillBuffer() {
        try {
            len = br.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ensureBuffer() {
        if (it == len) {
            it = 0;
            fillBuffer();
            if (len == -1) end = true;
        }
    }

    void moveNext() {
        while (!end) {
            ensureBuffer();
            if (!delim(buf[it])) return;
            while (it < len && delim(buf[it])) it++;
        }
    }

    public char nextChar() {
        moveNext();
        if (end) throw new NullPointerException("End was reached");
        return buf[it++];
    }

    public String next() {
        moveNext();
        StringBuilder sb = new StringBuilder();
        while (!end) {
            int l = it;
            while (++it < len && !delim(buf[it]));
            sb.append(buf, l, it - l);
            ensureBuffer();
            if (delim(buf[it])) break;
        }
        return sb.toString();
    }

    public int nextInt() {
        moveNext();
        if (buf[it] == '-') {
            it++;
            return -nextInt();
        }
        if (buf[it] == '+') {
            it++;
            return nextInt();
        }
        int result = 0;
        while (!end) {
            int l = it;
            while (it < len && !delim(buf[it])) {
                result = (result * 10) + buf[it] - '0';
                it++;
            }
            ensureBuffer();
            if (end || delim(buf[it])) break;
        }
        return result;
    }

    public long nextLong() {
        moveNext();
        if (buf[it] == '-') {
            it++;
            return -nextLong();
        }
        if (buf[it] == '+') {
            it++;
            return nextLong();
        }
        long result = 0;
        while (!end) {
            int l = it;
            while (it < len && !delim(buf[it])) {
                result = (result * 10) + buf[it] - '0';
                it++;
            }
            ensureBuffer();
            if (delim(buf[it])) break;
        }
        return result;
    }

    public FastScanner(String file) {
        try {
            br = new BufferedReader(new FileReader(file), BUFFER_SIZE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FastScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is), BUFFER_SIZE);
    }

    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String nextLine() {
        ensureBuffer();
        if (end) return null;
        StringBuilder sb = new StringBuilder();
        while (!end) {
            int l = it;
            while (++it < len && buf[it] != '\n');
            sb.append(buf, l, it - l);
            ensureBuffer();
            if (buf[it] == '\n') break;
        }
        it++;
        return sb.toString();
    }

    public int[] readIntArray(int count) {
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) arr[i] = nextInt();
        return arr;
    }
}

