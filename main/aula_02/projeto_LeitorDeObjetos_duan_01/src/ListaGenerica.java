public class ListaGenerica<T> {

    private Object[] lista;
    private int tamanhoAtual = 0;

    public ListaGenerica(int tamanho) {
        lista = new Object[tamanho];
    }

    public void inserir(T elemento) {
        if (tamanhoAtual < lista.length) {
            lista[tamanhoAtual++] = elemento;
        } else {
            System.out.println("Lista cheia!");
        }
    }

    public Object consultar(int indice) {
        if (indice >= 0 && indice < tamanhoAtual) {
            return lista[indice]; // retorna como Object
        } else {
            System.out.println("Índice inválido!");
            return null;
        }
    }

    public int tamanho() {
        return tamanhoAtual;
    }
}