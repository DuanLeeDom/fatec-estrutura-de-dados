/*
 * Operações principais:
 *  - Inserir
 *  - Consultar
 *  - Remover
 *
 * Inserir pode ocorrer em:
 *  - Início
 *  - Meio
 *  - Fim
 *
 * Remover pode ocorrer em:
 *  - Início
 *  - Meio
 *  - Fim
 *
 * Controle da estrutura:
 *  - Referência para o primeiro elemento (início)
 *  - Referência para o último elemento (fim)
 *  - Quantidade total de elementos
 */

public class ListaEncadeada<T> {
    private Node<T> inicio;
    private Node<T> fim;
    private int elementos;

    public ListaEncadeada() {
        inicio = null;
        fim = null;
        elementos = 0;
    }

    public boolean estaVazia() {
        return elementos == 0;
    }

    public int tamanho() {
        return elementos;
    }

    // Inserir no início
    public void inserirInicio(T conteudo) {
        Node<T> novo = new Node<>(conteudo);
        if (estaVazia()) {
            inicio = novo;
            fim = novo;
        } else {
            novo.setProximo(inicio);
            inicio = novo;
        }
        elementos++;
    }

    // Inserir no fim
    public void inserirFim(T conteudo) {
        Node<T> novo = new Node<>(conteudo);
        if (estaVazia()) {
            inicio = novo;
            fim = novo;
        } else {
            fim.setProximo(novo);
            fim = novo;
        }
        elementos++;
    }

    // Inserir em posição específica (meio)
    public void inserirMeio(T conteudo, int posicao) {
        if ((posicao < 1) || (posicao > elementos + 1)) {
            throw new IndexOutOfBoundsException("Posição impossível");
        }

        if (posicao == 1) {
            inserirInicio(conteudo);
        } else if (posicao == elementos + 1) {
            inserirFim(conteudo);
        } else {
            Node<T> novo = new Node<>(conteudo);
            Node<T> atual = inicio;
            for (int i = 1; i < (posicao - 1); i++) {
                atual = atual.getProximo();
            }
            novo.setProximo(atual.getProximo());
            atual.setProximo(novo);
            elementos++;
        }
    }

    public T remover(int posicao) {
        if (estaVazia()) {
            throw new IllegalStateException("A lista está vazia.");
        }
        if (posicao < 1 || posicao > tamanho()) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        T conteudo;

        // Remover do início
        if (posicao == 1) {
            conteudo = inicio.getInfo();
            inicio = inicio.getProximo();

            if (inicio == null) {
                fim = null; // corrigido
            }
        }
        // Remover no meio ou fim
        else {
            Node<T> atual = inicio;
            for (int i = 1; i < (posicao - 1); i++) {
                atual = atual.getProximo();
            }

            if (atual.getProximo() == fim) {
                fim = atual; // corrigido
            }

            conteudo = atual.getProximo().getInfo();
            atual.setProximo(atual.getProximo().getProximo());
        }
        elementos--;
        return conteudo;
    }

    // Atalho: insere no fim por padrão
    public void inserir(T conteudo) {
        inserirFim(conteudo);
    }
}