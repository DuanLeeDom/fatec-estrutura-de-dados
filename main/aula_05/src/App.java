public class App {
    public static void main(String[] args) throws Exception {
        Node<String> aluno1 = new Node<>("Joao");
        Node<String> aluno2 = new Node<>("Maria");
        aluno1.setProximo(aluno2);
        aluno2.setProximo(new Node<String>("Beltrano"));

        aluno2 = null;
        Node<String> atual = aluno1;
        while (atual.getProximo() != null){
                atual = atual.getProximo();
        }
        // insere um novo
        atual.setProximo(new Node<String>("Uguinho"));

        System.out.println(aluno1.getInfo());
        System.out.println(aluno1.getProximo().getInfo());
        System.out.println(aluno1.getProximo().getProximo().getInfo());
        System.out.println(aluno1.getProximo().getProximo().getProximo().getInfo());
    }
}
