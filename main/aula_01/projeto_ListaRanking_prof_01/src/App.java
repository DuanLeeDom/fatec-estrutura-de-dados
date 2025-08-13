public class App {
    private static ListaR minhaLista = new ListaR(10);
    public static void main(String[] args) throws Exception {
        minhaLista.imprimir();
        minhaLista.adiciona(1);
        minhaLista.imprimir();
        minhaLista.adiciona(20);
        minhaLista.imprimir();
        minhaLista.adiciona(30);        
        minhaLista.imprimir();
        minhaLista.adiciona(40);    
        minhaLista.imprimir();
        minhaLista.adiciona(5);
        minhaLista.imprimir();
    }
}
