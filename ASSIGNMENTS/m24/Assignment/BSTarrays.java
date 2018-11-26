import java.util.*;
class BSTArrayImplementation<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int[] size;
    private int[] rootLeft;
    private int[] rootRight;

    BSTArrayImplementation(int size) {
        keys = (Key[]) new Comparable[size];
        values = (Value[]) new Object[size];
        this.size = new int[size];
        rootLeft = new int[size];
        rootRight = new int[size];
        for (int i = 0; i < size; i++) {
            rootLeft[i] = -1;
            rootRight[i] = -1;
        }
    }

    public int size() {
        return size(0);
    }

    private int size(int index) {
        if (index == -1) {
            return 0;
        }

        return size[index];
    }

    public Key min() {
        if (size() == 0) {
            System.out.println("empty BST");
        }
        int minIndex = min(0);
        return keys[minIndex];
    }

    private int min(int index) {
        if (rootLeft[index] == -1) {
            return index;
        }
        return min(rootLeft[index]);
    }

    public Value get(Key key) {
        return get(0, key);
    }

    private Value get(int index, Key key) {
        if (index == -1 || keys[index] == null) {
            return null;
        }
        int compare = key.compareTo(keys[index]);
        if (compare < 0) {
            return get(rootLeft[index], key);
        } else if (compare > 0) {
            return get(rootRight[index], key);
        } else {
            return values[index];
        }
    }

    public void put(Key key, Value value) {
        if (size() == keys.length) {
            System.out.println("BST is full");
            return;
        }
        put(0, key, value);
    }

    private int put(int index, Key key, Value value) {
        if (index == -1 || keys[index] == null) {
            int nextIndex = size();
            keys[nextIndex] = key;
            values[nextIndex] = value;
            size[nextIndex] = 1;
            // size += 1;
            return nextIndex;
        }

        int compare = key.compareTo(keys[index]);

        if (compare < 0) {
            rootLeft[index] = put(rootLeft[index], key, value);
        } else if (compare > 0) {
            rootRight[index] = put(rootRight[index], key, value);
        } else {
            values[index] = value;
        }

        size[index] = size(rootLeft[index]) + 1 + size(rootRight[index]);
        return index;
    }

    public void delete(Key key) {
        int rootIndex = delete(0, key);
    }

    private int delete(int index, Key key) {
        if (index == -1 || keys[index] == null) {
            return -1;
        }

        int compare = key.compareTo(keys[index]);
        if (compare < 0) {
            int leftIndex = delete(rootLeft[index], key);
            rootLeft[index] = leftIndex;
        } else if (compare > 0) {
            int rightIndex = delete(rootRight[index], key);
            rootRight[index] = rightIndex;
        } else {
            keys[index] = null;
            values[index] = null;
            size[index] = 0;

            if (rootLeft[index] == -1) {
                int rightLinkIndex = rootRight[index];
                rootRight[index] = -1;
                return rightLinkIndex;
            } else if (rootRight[index] == -1) {
                int leftLinkIndex = rootLeft[index];
                rootLeft[index] = -1;
                return leftLinkIndex;
            } else {
                int temp = min(rootRight[index]);
                rootRight[temp] = deleteMin(rootRight[index], false);
                rootLeft[temp] = rootLeft[index];
                rootRight[index] = -1;
                rootLeft[index] = -1;
                index = temp;
            }
        }
        size[index] = size(rootLeft[index]) + 1 + size(rootRight[index]);
        return index;
    }

    public void deleteMin() {
        int rootIndex = deleteMin(0, true);
    }
    private int deleteMin(int index, boolean setKeyNull) {
        if (index == -1 || keys[index] == null) {
            return -1;
        }

        int leftIndex = deleteMin(rootLeft[index], setKeyNull);
        rootLeft[index] = leftIndex;

        size[index] = size(rootLeft[index]) + 1 + size(rootRight[index]);
        return index;
    }
}

class BSTarrays {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        BSTArrayImplementation<Integer, String> arrayImp = new BSTArrayImplementation<>(n);
        Stopwatch swobj = new Stopwatch();
        while (n > 0) {
            String s = scan.nextLine();
            String[] tokens = s.split(" ");
            switch (tokens[0]) {
            case"put":
                arrayImp.put(Integer.parseInt(tokens[1]), tokens[2]);
                // System.out.println(swobj.elapsedTime());
                break;
            case"get":
                System.out.println(arrayImp.get(Integer.parseInt(tokens[1])));
                //System.out.println(swobj.elapsedTime());
                break;
            case"delete":
                arrayImp.delete(Integer.parseInt(tokens[1]));
                //System.out.println(swobj.elapsedTime());
            }
            n--;
        }
        System.out.println(swobj.elapsedTime());
    }
}