public class ListaVetor {
    private int[] lista;
    private int quantidade;

    public ListaVetor(int tamanho) {
        lista = new int[tamanho];
        quantidade = 0;
    }

    public boolean estaCheia() {
        return quantidade == lista.length;
    }

    public String mostrarAll() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quantidade; i++) {
            sb.append(lista[i] + " ");
        }
        return sb.toString();
    }

    public int obter(int posicao) throws IndexOutOfBoundsException {
        if (posicao >= 0 && posicao < quantidade) {
            return lista[posicao];
        }
        throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
    }

    public void inserir(int info) {
        if (estaCheia()) {
            System.out.println("Lista cheia, não é possível inserir.");
        } else {
            lista[quantidade] = info;
            quantidade++;
        }
    }

    public void ordenarDecrescente() throws IndexOutOfBoundsException {
        try {
            if (quantidade == 0) {
                throw new IndexOutOfBoundsException("A lista esta vazia, nao e possivel ordenar.");
            }

            for (int i = 0; i < quantidade; i++) {
                for (int j = 0; j < quantidade - 1 - i; j++) {
                    if (lista[j] < lista[j + 1]) {
                        int temp = lista[j];
                        lista[j] = lista[j + 1];
                        lista[j + 1] = temp;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }
}
