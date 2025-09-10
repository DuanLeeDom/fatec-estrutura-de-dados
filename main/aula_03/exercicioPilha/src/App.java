import java.util.Iterator;

public class App {

    static private PilhaGenerica<Integer> ordenado, auxiliar;
    public static void main(String[] args) throws Exception {
        ordenado = new PilhaGenerica<>(10);
        auxiliar = new PilhaGenerica<>(10);
        System.out.println("Processando os empilhamentos");
        for (int i = 1 ; i <=10 ; i++){
            int valor = (int) (Math.random()*1000);
            System.out.println("Empilhando o valor: " + valor);
            insere(ordenado,auxiliar,valor);
        }
        exibir();
    }

    private static void exibir() {
       System.out.println("\nApresentando o resultado.");
       for (Iterator<Integer> x = ordenado.iterator();x.hasNext();){
            System.out.println(x.next());
       }
        
   }
    private static void insere(PilhaGenerica<Integer> principal, PilhaGenerica<Integer> aux, int valor) {
        try{
            if (!principal.estaVazio()){
                while (principal.consultar() < valor){
                    aux.empilhar(principal.desempilha());
                }
            }
        }
        catch(RuntimeException e){

        }
        principal.empilhar(valor);
        try {
            while (!aux.estaVazio()){
                principal.empilhar(aux.desempilha());
            }
        } catch (Exception e) {
            
        }        
    }
}
