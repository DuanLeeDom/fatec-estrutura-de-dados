public class App {
    public static void main(String[] args) throws Exception {
        ListaVet minhaLista = new ListaVet(5);
        minhaLista.inserir("João");
        minhaLista.inserir("Maria");
        minhaLista.inserir("Pedro");
        minhaLista.inserir("Ana");
        minhaLista.inserir("Lucas");    
        minhaLista.inserir("Carla");
        minhaLista.inserir("Roberto");
        minhaLista.imprimir();
        int posicao = minhaLista.buscar("Ana");
        if (posicao == -1){
            System.out.println("Valor não encontrado.");
        }else{
            System.out.println("Consulta -> " + minhaLista.obter(posicao));
        }
        try{
        System.out.println("exclusão -> " + minhaLista.excluir("Pedro"));
        System.out.println("exclusão -> " + minhaLista.excluir(10));
        }catch (IndexOutOfBoundsException e){
         System.out.println(e.getMessage());
        }
        minhaLista.imprimir();


    }
}
