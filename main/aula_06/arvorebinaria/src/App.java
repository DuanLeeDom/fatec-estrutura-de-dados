public class App {

    private static ArvoreBi<Dado> minhaArvore;

    public static void main(String[] args) throws Exception {
        minhaArvore = new ArvoreBi<>();
        minhaArvore.inserir(new Dado(50));
        minhaArvore.inserir(new Dado(25));
        minhaArvore.inserir(new Dado(12));
        minhaArvore.inserir(new Dado(40));
        minhaArvore.inserir(new Dado(100));
        minhaArvore.inserir(new Dado(80));
        minhaArvore.inserir(new Dado(60));
        minhaArvore.inserir(new Dado(90));
        minhaArvore.inserir(new Dado(150));

        System.out.println("Exibir in-order");
        System.out.println(minhaArvore.listaInOrder());
        System.out.println();

        System.out.println("Exibir pos_order");
        System.out.println(minhaArvore.listaPosOrder());
        System.out.println();
        System.out.println("Exibir pre-order");
        System.out.println(minhaArvore.listaPreOrder());

        System.out.println("\n Localizar o valor 200");
        System.out.println(minhaArvore.localizar(new Dado(200)));
        System.out.println("No. de buscas " + minhaArvore.getUltimaBusca());
        
    }
}
