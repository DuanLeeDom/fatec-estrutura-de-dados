import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa uma pessoa na árvore genealógica
 */
public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private Casamento casamento;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    /**
     * Construtor da classe Pessoa
     * @param nome Nome completo da pessoa
     * @param dataNascimento Data de nascimento
     */
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.casamento = null;
    }
    
    /**
     * Construtor alternativo que aceita data como String
     * @param nome Nome completo da pessoa
     * @param dataNascimento Data no formato dd/MM/yyyy
     */
    public Pessoa(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
        this.casamento = null;
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public Casamento getCasamento() {
        return casamento;
    }
    
    public void setCasamento(Casamento casamento) {
        this.casamento = casamento;
    }
    
    /**
     * Verifica se a pessoa está casada
     * @return true se estiver casada, false caso contrário
     */
    public boolean estaCasada() {
        return this.casamento != null;
    }
    
    /**
     * Calcula a idade aproximada da pessoa
     * @return idade em anos
     */
    public int getIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }
    
    @Override
    public String toString() {
        return String.format("%s (nascido em %s, %d anos)", 
                           nome, 
                           dataNascimento.format(formatter),
                           getIdade());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pessoa pessoa = (Pessoa) obj;
        return nome.equals(pessoa.nome) && dataNascimento.equals(pessoa.dataNascimento);
    }
    
    @Override
    public int hashCode() {
        return nome.hashCode() + dataNascimento.hashCode();
    }
}