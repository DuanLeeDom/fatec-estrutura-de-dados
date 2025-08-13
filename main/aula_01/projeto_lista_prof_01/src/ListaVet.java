public class ListaVet {
    private String[] aLista; // nome da lista
    private int quantidade; // quantidade de elementos na lista

    public ListaVet(int tamanho) { // construtor que recebe o tamanho da lista
        aLista = new String[tamanho];
        quantidade = 0;
    }

    private boolean estaCheia() { // verifica se a lista está cheia
        return quantidade == aLista.length;
    }

    public void inserir(String info){
        if (estaCheia()) {
            System.out.println("Lista cheia, não é possível inserir.");
        } else {
            aLista[quantidade] = info;
            quantidade++;
        }
    }

    public void imprimir() {
        for(int i = 0 ; i < quantidade; i++){
            System.out.println(aLista[i]);
        }
    }

    public int buscar(String info){
        for(int i = 0; i < quantidade; i++){
            if(aLista[i].equals(info)){
                return i; // retorna a posição do elemento encontrado
            }
        }
        return -1;
    }
    public String obter(int posicao) 
           throws IndexOutOfBoundsException {
        if( posicao >= 0 && posicao < quantidade) {
            return aLista[posicao]; // retorna o elemento na posição especificada
        }
        throw new IndexOutOfBoundsException(
                 "Posição inválida: " + posicao);
    }

    public String excluir(String info){
        int posicao;
        String retorno;
        try{
            posicao = buscar(info);
            retorno = excluir(posicao); // chama o método excluir passando a posição encontrada
            return retorno; // retorna o elemento excluído
            // if (posicao != -1){
            //     retorno =  aLista[posicao];
            //     for (int i = posicao; i < quantidade-1; i++){
            //         aLista[i] = aLista[i + 1]; // desloca os elementos para a esquerda
            //     }
            //     aLista[quantidade - 1] = null; // limpa o último elemento
            //     quantidade--;
            //     return retorno; // retorna o elemento excluído
            // }
        }catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException(e.getMessage());
                    
        }
    
    }

    public String excluir(int posicao){
        String retorno;
        if (posicao >= 0 && posicao < quantidade){
            retorno = aLista[posicao];
            for (int i = posicao; i < quantidade - 1; i++){
                aLista[i] = aLista[i + 1]; // desloca os elementos para a esquerda
            }
            aLista[quantidade - 1] = null; // limpa o último elemento
            quantidade--;
            return retorno; // retorna o elemento excluído
        }
        else{
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }
        
    }
}
