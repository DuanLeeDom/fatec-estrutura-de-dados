public class Paciente {
    private String nome;
    private int idade;

    public Paciente(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setIdade(int novaIdade) {
        this.idade = novaIdade;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdade() {
        return this.idade;
    }
}
