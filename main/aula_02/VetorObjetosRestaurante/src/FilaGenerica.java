public class FilaGenerica<T> {

    private T[] itens;
    private int frente;  // índice do primeiro
    private int tras;    // posição de inserção
    private int tamanho;

    @SuppressWarnings("unchecked")
    public FilaGenerica(int capacidadeInicial) {
        if (capacidadeInicial <= 0) capacidadeInicial = 1;
        itens = (T[]) new Object[capacidadeInicial];
        frente = 0;
        tras = 0;
        tamanho = 0;
    }

    public void enfileirar(T elemento) {
        if (tamanho == itens.length) {
            aumentarCapacidade();
        }
        itens[tras] = elemento;
        tras = (tras + 1) % itens.length;
        tamanho++;
    }

    public T desenfileirar() {
        if (vazia()) {
            throw new IllegalStateException("Fila vazia");
        }
        T elem = itens[frente];
        itens[frente] = null;
        frente = (frente + 1) % itens.length;
        tamanho--;
        return elem;
    }

    public boolean vazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    @SuppressWarnings("unchecked")
    private void aumentarCapacidade() {
        T[] nova = (T[]) new Object[itens.length * 2];
        // copia os elementos na ordem lógica
        for (int i = 0; i < tamanho; i++) {
            nova[i] = itens[(frente + i) % itens.length];
        }
        itens = nova;
        frente = 0;
        tras = tamanho;
    }
}
