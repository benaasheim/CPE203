import javafx.scene.Node;

public class BinaryTree
{
    private Node root;

    public BinaryTree(int[] values)
    {
        root = buildTree(values, 0, values.length - 1);
    }
    private Node buildTree(int[] values, int low, int high)
    {
        if (low <= high) {
        int mid = (low + high)/2;
        return new Node(values[mid],
                        buildTree(values, low, mid-1),
                        buildTree(values, mid+1, high));
        }
        return null;
    }
    // Write code to count the occurrences of a value in the tree
    // public version of count
    public int counti(int val) {
        int count = 0;
        return countI(this.root, val, 0);
    }

    // private version of count
    private static int countI(Node cur, int val, int count)
    {
        if (cur == null) {
            return 0;
        }
        if (cur.getValue() == val) {
            count += 1;
        }
        int left = countI(cur.left, val, count);
        int right = countI(cur.right, val, count);

        return (count + left + right);
    }


    // Inner class. Private data can be accessed by outer class
    private class Node
    {
        private int value;
        private Node left, right;

        public Node(int value, Node left, Node right)
        {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public int getValue() { return this.value; }
    }
}