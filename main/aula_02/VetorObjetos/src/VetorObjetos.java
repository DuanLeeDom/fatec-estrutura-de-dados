public class VetorObjetos {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListaGenerica<Paciente> minhalista = new ListaGenerica<Paciente>(10);
        Paciente p1 = new Paciente("joao",35);
        minhalista.inserir(p1);
        Paciente p2 = new Paciente("maria", 27);
        minhalista.inserir(p2);
        p1 = new Paciente("fernando", 18);
        minhalista.inserir(p1);
        p1 = null;
        p2 = null;
        ListaGenerica<Integer> novaLista = new ListaGenerica<Integer>(20);
        novaLista.inserir(10);
        //System.out.println(minhalista.consultar(1).getNome());
        //System.out.println(minhalista.consultar(1).getIdade());
        System.out.println(minhalista.consultar(2));
        System.out.println(minhalista.consultar(1));
        p1 = minhalista.consultar(2);
        p2 = minhalista.consultar(1);
        if(p1.compareTo(p2) > 0){
            System.out.println("p1 mais velho que p2");
        }
        else{
            System.out.println("p1 é mais novo que p2");
        }
                
    }
}
