import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um casamento entre duas pessoas
 */
public class Casamento {
    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private LocalDate dataCasamento;
    private List<Pessoa> filhos;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    /**
     * Construtor do casamento
     * @param pessoa1 Primeira pessoa do casal
     * @param pessoa2 Segunda pessoa do casal
     * @param dataCasamento Data do casamento
     */
    public Casamento(Pessoa pessoa1, Pessoa pessoa2, LocalDate dataCasamento) {
        this.pessoa1 = pessoa1;
        this.pessoa2 = pessoa2;
        this.dataCasamento = dataCasamento;
        this.filhos = new ArrayList<>();
    }
    
    /**
     * Construtor alternativo que aceita data como String
     */
    public Casamento(Pessoa pessoa1, Pessoa pessoa2, String dataCasamento) {
        this.pessoa1 = pessoa1;
        this.pessoa2 = pessoa2;
        this.dataCasamento = LocalDate.parse(dataCasamento, formatter);
        this.filhos = new ArrayList<>();
    }
    
    // Getters e Setters
    public Pessoa getPessoa1() {
        return pessoa1;
    }
    
    public Pessoa getPessoa2() {
        return pessoa2;
    }
    
    public LocalDate getDataCasamento() {
        return dataCasamento;
    }
    
    public List<Pessoa> getFilhos() {
        return new ArrayList<>(filhos); // Retorna cópia para evitar modificações externas
    }
    
    /**
     * Adiciona um filho ao casamento
     * @param filho Filho a ser adicionado
     * @return true se adicionado com sucesso, false se já existir
     */
    public boolean adicionarFilho(Pessoa filho) {
        if (filhos.contains(filho)) {
            return false;
        }
        return filhos.add(filho);
    }
    
    /**
     * Remove um filho do casamento
     * @param filho Filho a ser removido
     * @return true se removido com sucesso
     */
    public boolean removerFilho(Pessoa filho) {
        return filhos.remove(filho);
    }
    
    /**
     * Obtém o número de filhos do casamento
     * @return quantidade de filhos
     */
    public int getNumeroFilhos() {
        return filhos.size();
    }
    
    /**
     * Verifica se uma pessoa faz parte deste casamento
     * @param pessoa Pessoa a verificar
     * @return true se for cônjuge
     */
    public boolean ehConjuge(Pessoa pessoa) {
        return pessoa.equals(pessoa1) || pessoa.equals(pessoa2);
    }
    
    /**
     * Obtém o cônjuge de uma determinada pessoa
     * @param pessoa Pessoa para buscar o cônjuge
     * @return O cônjuge ou null se a pessoa não fizer parte do casamento
     */
    public Pessoa getConjuge(Pessoa pessoa) {
        if (pessoa.equals(pessoa1)) {
            return pessoa2;
        } else if (pessoa.equals(pessoa2)) {
            return pessoa1;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return String.format("Casamento entre %s e %s em %s (%d filho(s))",
                           pessoa1.getNome(),
                           pessoa2.getNome(),
                           dataCasamento.format(formatter),
                           filhos.size());
    }
}