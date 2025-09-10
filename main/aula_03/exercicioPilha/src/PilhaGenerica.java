import java.util.Iterator;

public class PilhaGenerica<T> implements Iterable<T> {
    private T[] vetor;
    private int topo;

    public PilhaGenerica(int tamanho) {
        vetor =(T[]) new Object[tamanho];
        topo = -1;
    }

    public boolean estaCheio(){
        return vetor.length == (topo+1);
    }
    public boolean estaVazio(){
        return topo == -1;
    }

    // colocar um elemento na pilha "empilhar" ou "push"
    public void empilhar(T info) throws RuntimeException{
        if (!estaCheio()){
            vetor[++topo] = info;
            // equivale a
            // topo++;
            // vetor[topo] = info;
        }
        else{
            throw new RuntimeException("Pilha Cheia.");
        }
    }

    // remover um elemento da pilha "desempilhar" ou "pop"
    public T desempilha() throws RuntimeException{
        T aux = null;
        if (!estaVazio()){
            aux = vetor[topo--];
            // equivale a
            // aux = vetor[topo];
            // topo--;
        }
        else{
            throw new RuntimeException("Pilha vazia.");
        }
        return aux;
    }

    // exibir o valor em topo "consultar" ou "query"
    public T consultar() throws RuntimeException{
        if (estaVazio()){
            throw new RuntimeException("Pilha vazia.");
        }
        return vetor[topo];   
    }

    @Override
    public Iterator<T> iterator() {
       return new PilhaGenericaIterator<T>(vetor,topo);
    }
/*
    classe auxiliar para manipular a obtençao de elementos
    da pilha sem ter qua acessar o pilha diretamente.
    */
    private  class PilhaGenericaIterator<T> implements Iterator<T>{
        private int indiceCorrente = 0;
        private int quantidade;
        private T[] info;
        public PilhaGenericaIterator(T[] dados, int top) {            
            this.info = dados;
            indiceCorrente = top;
        }

        /*
        Indica se existe elemento ainda não apresentado
        */
        @Override
        public boolean hasNext() {
            return indiceCorrente >= 0 ;
        }

        /*
        Obtem o elemento da lista e posiciona no proximo
        */
        @Override
        public T next() {
            quantidade--;
            T aux = info[indiceCorrente];
            indiceCorrente--;
            return aux ;
            
        }
    }



}
