
public class ListaR {
    private int[] valores;
    private int ultimo;

    public ListaR(int i) {
        valores = new int[i];
        ultimo = i-1;
    }

    public void adiciona(int i) {
        if (valores[ultimo] < i) {
            valores[ultimo] = i;
            for (int anterior = ultimo-1; anterior >= 0; anterior--) {
                int atual = anterior + 1;
                if (valores[anterior] < valores[atual]) {
                    int temp = valores[anterior];
                    valores[anterior] = valores[atual];
                    valores[atual] = temp;
                }
                else{
                    break;
                }
            }
        }
    }

    public void imprimir() {
        for (int i : valores) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
