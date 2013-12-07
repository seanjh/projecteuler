public class BinaryTree<T> {
    private BinaryNode<T> root;

    public BinaryTree(T info) {
        root = new BinaryNode<T>(info);
    }

    public int totalLeaves() {
        return -1;
    }

    public boolean isEmpty() {
    // Returns true if this BinaryTree is empty; otherwise, returns false.
        return root == null;
    }

    public BinaryNode<T> getRoot() {
        return root;
    }
}