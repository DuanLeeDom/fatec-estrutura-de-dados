/**
 * @author Luciano Pelissoli
 */

public class ListaGenerica<T> {
    
    private T[] lista;
    private int quantidade;
    
    public ListaGenerica(int tamanho){
        lista =(T[]) new Object[tamanho];
        quantidade = 0;
    }
    
    public void inserir(T objeto){
        lista[quantidade] = objeto;
        quantidade++;
    }
    
    public T consultar(int posicao){
        return lista[posicao];
    }
    
    
}