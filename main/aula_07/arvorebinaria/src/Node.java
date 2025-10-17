public class Node<T> {
    private T info;
    private Node<T> direita;
    private Node<T> esquerda;
    public Node(T info) {
        this.info = info;
        direita = null;
        esquerda = null;
    }
    public T getInfo() {
        return info;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public Node<T> getDireita() {
        return direita;
    }
    public void setDireita(Node<T> direita) {
        this.direita = direita;
    }
    public Node<T> getEsquerda() {
        return esquerda;
    }
    public void setEsquerda(Node<T> esquerda) {
        this.esquerda = esquerda;
    }

}
