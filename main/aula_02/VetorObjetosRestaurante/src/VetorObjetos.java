import java.util.Random;

public class VetorObjetos {

    // Função auxiliar para gerar um nome aleatório
    private static String gerarNomeAleatorio() {
        String[] nomes = {
                "João", "Maria", "Carlos", "Ana", "Pedro", "Lucas", "Mariana", "Fernanda", "Paulo", "Juliana",
                "Roberto", "Camila", "Leandro", "Renata", "Jefferson", "Luiz", "Julio", "Oliver", "Leticia"
        };
        Random random = new Random();
        return nomes[random.nextInt(nomes.length)];
    }

    public static void main(String[] args) {
        int reservas = 5; 
        int quantidadeEntradas = 3;
        
        SistemaReservas sistema = new SistemaReservas(reservas); // máximo de mesas
        Random random = new Random();

        int entradas = reservas + quantidadeEntradas;

        // Simular várias entradas automáticas
        for (int i = 0; i < entradas; i++) { // tenta 7 reservas
            String nome = gerarNomeAleatorio();
            int mesa = sistema.reservar(nome);
            if (mesa == -1) {
                System.out.println(nome + " foi para a FILA de espera.");
            } else {
                System.out.println(nome + " conseguiu a mesa " + mesa);
            }
        }

        // Mostrar situação inicial
        System.out.println("\n--- Situação inicial ---");
        sistema.imprimirResumo();

        // Cancelar aleatoriamente uma mesa (exemplo)
        int mesaCancelar = random.nextInt(sistema.capacidade()) + 1;
        System.out.println("\nCancelando mesa " + mesaCancelar + "...");
        sistema.cancelar(mesaCancelar);

        // Mostrar após cancelamento
        System.out.println("\n--- Situação após cancelamento ---");
        sistema.imprimirResumo();
    }
}
