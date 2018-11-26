import java.util.*;
class BSTreeArray<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int[] size;
    private int[] left;
    private int[] right;
    BSTreeArray(int size) {
        keys = (Key[]) new Comparable[size];
        values = (Value[]) new Object[size];
        this.size = new int[size];
        left = new int[size];
        right = new int[size];
        for (int i = 0; i < size; i++) {
            left[i] = -1;
            right[i] = -1;
        }
    }
    public int size() {
        return size(0);
    }
    private int size(int i) {
        if (i == -1) {
            return 0;
        }
        return size[i];
    }
    public void put(Key key, Value value) {
        if (size() == keys.length) {
            System.out.println("Full");
            return;
        }
        put(0, key, value);
    }
    private int put(int i, Key key, Value value) {
        if (i == -1 || keys[i] == null) {
            int i1 = size();
            keys[i1] = key;
            values[i1] = value;
            size[i1] = 1;
            return i1;
        }
        int a = key.compareTo(keys[i]);
        if (a < 0) {
            left[i] = put(left[i], key, value);
        } else if (a > 0) {
            right[i] = put(right[i], key, value);
        } else {
            values[i] = value;
        }
        size[i] = size(left[i]) + 1 + size(right[i]);
        return i;
    }
    public Key min() {
        if (size() == 0) {
            System.out.println("Empty");
        }
        int i = min(0);
        return keys[i];
    }
    private int min(int i) {
        if (left[i] == -1) {
            return i;
        }
        return min(left[i]);
    }
    public Value get(Key key) {
        return get(0, key);
    }
    private Value get(int i, Key key) {
        if (i == -1 || keys[i] == null) {
            return null;
        }
        int a = key.compareTo(keys[i]);
        if (a < 0) {
            return get(left[i], key);
        } else if (a > 0) {
            return get(right[i], key);
        } else {
            return values[i];
        }
    }
    public void delete(Key key) {
        int i = delete(0, key);
    }
    private int delete(int i, Key key) {
        if (i == -1 || keys[i] == null) {
            return -1;
        }
        int a = key.compareTo(keys[i]);
        if (a < 0) {
            int i1 = delete(left[i], key);
            left[i] = i1;
        } else if (a > 0) {
            int i2 = delete(right[i], key);
            right[i] = i2;
        } else {
            keys[i] = null;
            values[i] = null;
            size[i] = 0;
            if (left[i] == -1) {
                int i3 = right[i];
                right[i] = -1;
                return i3;
            } else if (right[i] == -1) {
                int i4 = left[i];
                left[i] = -1;
                return i4;
            } else {
                int temp = min(right[i]);
                right[temp] = deleteMin(right[i], false);
                left[temp] = left[i];
                right[i] = -1;
                left[i] = -1;
                i = temp;
            }
        }
        size[i] = size(left[i]) + 1 + size(right[i]);
        return i;
    }
    public void deleteMin() {
        int i = deleteMin(0, true);
    }
    private int deleteMin(int i, boolean setKeyNull) {
        if (i == -1 || keys[i] == null) {
            return -1;
        }
        int i1 = deleteMin(left[i], setKeyNull);
        left[i] = i1;
        size[i] = size(left[i]) + 1 + size(right[i]);
        return i;
    }
}
class BSTarrays {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        BSTreeArray<Integer, String> bstarray = new BSTreeArray<>(num);
        Stopwatch sw = new Stopwatch();
        while (num > 0) {
            String str1 = scan.nextLine();
            String[] tokens = str1.split(" ");
            switch (tokens[0]) {
            case"put":
                bstarray.put(Integer.parseInt(tokens[1]), tokens[2]);
                // System.out.println(sw.elapsedTime());
                break;
            case"get":
                System.out.println(bstarray.get(Integer.parseInt(tokens[1])));
                //System.out.println(sw.elapsedTime());
                break;
            case"delete":
                bstarray.delete(Integer.parseInt(tokens[1]));
                //System.out.println(sw.elapsedTime());
            }
            num--;
        }
        System.out.println(sw.elapsedTime());
    }
}