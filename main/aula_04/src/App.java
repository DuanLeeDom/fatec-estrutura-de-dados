// Exemplo de projeto de lista encadeada

public class App {
    public static void main(String[] args) throws Exception {    
        Node<String> aluno1 = new Node<>("Joao");
        Node<String> aluno2 = new Node<>("Maria");
        aluno1.setProximo(aluno2);
        aluno2.setProximo(new Node<>("Beltrano"));

        aluno2 = null;

        Node<String> atual = aluno1;
        while (atual.getProximo() != null) {
            System.out.println(atual.getInfo());
            atual = atual.getProximo();
        }
        atual.setProximo(new Node<String>("Ugirinho"));

        System.out.println(aluno1.getInfo());
        System.out.println(aluno2.getProximo().getInfo());
    }
}
