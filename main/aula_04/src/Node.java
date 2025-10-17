public class Node<T> {
    private final T info;
    private Node<T> proximo;

    public Node (T info) {
        this.info = info;
        proximo = null;
    }

    public T getInfo() {
        return info;
    }

    public Node<T> getProximo() {
        return proximo;
    }

    public void setProximo(Node<T> seguinte) {
        proximo = seguinte;
    }
}
