import java.util.Objects;

/**
 * @author Luciano Pelissoli
 */

public class Paciente implements Comparable<Paciente>
{
    private String nome;
    private int idade;
    
    public Paciente( String nome, int idade){
        this.nome = nome;
        this.idade = idade;    
    }
    
    public void setNome(String novoNome){
        this.nome = novoNome;
    }
    public void setIdade (int novaIdade){
        this.idade = novaIdade;
    }
    public String getNome(){
        return this.nome;
    }
    public int getIdade(){
        return this.idade;
    }

    @Override
    public String toString() {
        return "Paciente{" + "nome=" + nome + ", idade=" + idade + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        return Objects.equals(this.nome, other.nome);
    }

    @Override
    public int compareTo(Paciente paciente) {
        if ((paciente == null) || getClass() != paciente.getClass())
            throw new RuntimeException("Objeto inválido");
        
        return this.idade - paciente.idade;
    }   
}