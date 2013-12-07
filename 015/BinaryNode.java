public class BinaryNode<T> {
    private T info;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode(T info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BinaryNode<T> getLeft() { return left; }
    public void setLeft(BinaryNode<T> link) { left = link; }

    public BinaryNode<T> getRight() { return right; }
    public void setRight(BinaryNode<T> link) { right = link; }

    public T getInfo() {
        return info;
    }
}