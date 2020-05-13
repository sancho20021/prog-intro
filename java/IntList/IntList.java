package IntList;

import java.util.Arrays;

public class IntList {
    private int[] a;
    private int size;

    public IntList() {
        a = new int[1];
        size = 0;
    }

    public IntList(int a1) {
        a = new int[1];
        size = 1;
        a[0] = a1;
    }

    public IntList(int a1, int a2) {
        a = new int[2];
        size = 2;
        a[0] = a1;
        a[1] = a2;
    }

    public int size() {
        return size;
    }

    private void enlarge() {
        if (size >= a.length)
            a = Arrays.copyOf(a, a.length * 2);
    }

    private void reduce() {
        if (a.length / 4 > size)
            a = Arrays.copyOf(a, a.length / 2);
    }

    public void add(int x) {
        enlarge();
        a[size++] = x;
    }

    public int get(int i) {
        if (i < size && i >= 0)
            return a[i];
        else
            throw new ArrayIndexOutOfBoundsException("index " + i + " is out of bounds");
    }

    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            if (a[i] == x) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            s.append(a[i]).append(" ");
        }
        s.append(a[size - 1]);
        return s.toString();
    }
}
