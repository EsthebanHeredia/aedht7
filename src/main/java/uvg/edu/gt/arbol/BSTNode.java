package uvg.edu.gt.arbol;

public class BSTNode<E> {
    protected E data;
    protected BSTNode<E> left;
    protected BSTNode<E> right;

    public BSTNode(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public E getData() {
        return data;
    }

    public BSTNode<E> getLeft() {
        return left;
    }

    public BSTNode<E> getRight() {
        return right;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setLeft(BSTNode<E> left) {
        this.left = left;
    }

    public void setRight(BSTNode<E> right) {
        this.right = right;
    }
}