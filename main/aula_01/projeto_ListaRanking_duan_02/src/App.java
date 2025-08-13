import java.util.Random;

public class App {
    public static void main(String[] args) {
        Random gerador = new Random();

        ListaVetor lista = new ListaVetor(15);
        for (int i = 0; i < 10; i++) {
            int numAleatorio = gerador.nextInt(10, 99); // 0 a 99
            lista.inserir(numAleatorio);
        }

        System.out.println("Lista original:");
        System.out.println(lista.mostrarAll());

        try {
            lista.ordenarDecrescente();
            System.out.println("Lista ordenada (maior para menor): ");
            System.out.println(lista.mostrarAll());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
