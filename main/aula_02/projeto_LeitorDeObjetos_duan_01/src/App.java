public class App {
    public static void main(String[] args) {
        ListaGenerica<Paciente> minhalista = new ListaGenerica<>(10);

        Paciente p1 = new Paciente("João", 35);
        minhalista.inserir(p1);

        Paciente p2 = new Paciente("Maria", 27);
        minhalista.inserir(p2);

        Paciente p3 = new Paciente("Fernando", 18);
        minhalista.inserir(p3);

        // Limpando referências
        p1 = null;
        p2 = null;
        ListaGenerica<Integer> novaLista = new ListaGenerica<Integer>(20);
        novaLista.inserir(10);
        // System.out.println(minhalista.consultar(1).getNome());
        // System.out.println(minhalista.consultar(1).getIdade());
        System.out.println(minhalista.consultar(1));
    }
}
