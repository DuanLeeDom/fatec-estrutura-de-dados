import java.time.LocalDate;
import java.util.*;

/**
 * Classe principal que gerencia a árvore genealógica
 */
public class ArvoreGenealogica {
    private List<Pessoa> pessoas;
    private List<Casamento> casamentos;
    private Map<Pessoa, List<Pessoa>> relacionamentosPaisFilhos;
    
    /**
     * Construtor da árvore genealógica
     */
    public ArvoreGenealogica() {
        this.pessoas = new ArrayList<>();
        this.casamentos = new ArrayList<>();
        this.relacionamentosPaisFilhos = new HashMap<>();
    }
    
    /**
     * Adiciona uma nova pessoa à árvore genealógica
     * @param pessoa Pessoa a ser adicionada
     * @return true se adicionada com sucesso
     */
    public boolean adicionarPessoa(Pessoa pessoa) {
        if (pessoas.contains(pessoa)) {
            System.out.println("Erro: Pessoa já existe na árvore genealógica!");
            return false;
        }
        pessoas.add(pessoa);
        relacionamentosPaisFilhos.put(pessoa, new ArrayList<>());
        System.out.println("Pessoa adicionada: " + pessoa.getNome());
        return true;
    }
    
    /**
     * Registra um casamento entre duas pessoas
     * @param pessoa1 Primeira pessoa
     * @param pessoa2 Segunda pessoa
     * @param dataCasamento Data do casamento
     * @return O casamento criado ou null se houver erro
     */
    public Casamento registrarCasamento(Pessoa pessoa1, Pessoa pessoa2, LocalDate dataCasamento) {
        // Validações
        if (!pessoas.contains(pessoa1) || !pessoas.contains(pessoa2)) {
            System.out.println("Erro: Uma ou ambas as pessoas não estão registradas na árvore!");
            return null;
        }
        
        if (pessoa1.equals(pessoa2)) {
            System.out.println("Erro: Uma pessoa não pode casar consigo mesma!");
            return null;
        }
        
        if (pessoa1.estaCasada()) {
            System.out.println("Erro: " + pessoa1.getNome() + " já está casada!");
            return null;
        }
        
        if (pessoa2.estaCasada()) {
            System.out.println("Erro: " + pessoa2.getNome() + " já está casada!");
            return null;
        }
        
        // Criar casamento
        Casamento casamento = new Casamento(pessoa1, pessoa2, dataCasamento);
        pessoa1.setCasamento(casamento);
        pessoa2.setCasamento(casamento);
        casamentos.add(casamento);
        
        System.out.println("Casamento registrado: " + pessoa1.getNome() + " e " + pessoa2.getNome());
        return casamento;
    }
    
    /**
     * Registra um casamento com data em String
     */
    public Casamento registrarCasamento(Pessoa pessoa1, Pessoa pessoa2, String dataCasamento) {
        return registrarCasamento(pessoa1, pessoa2, 
                                LocalDate.parse(dataCasamento, 
                                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    
    /**
     * Associa um filho a um casal
     * @param filho Filho a ser associado
     * @param pai Um dos pais
     * @param mae O outro pai/mãe
     * @return true se associado com sucesso
     */
    public boolean associarFilho(Pessoa filho, Pessoa pai, Pessoa mae) {
        if (!pessoas.contains(filho) || !pessoas.contains(pai) || !pessoas.contains(mae)) {
            System.out.println("Erro: Uma ou mais pessoas não estão registradas!");
            return false;
        }
        
        Casamento casamentoPais = pai.getCasamento();
        
        if (casamentoPais == null || !casamentoPais.ehConjuge(mae)) {
            System.out.println("Erro: Os pais informados não são casados entre si!");
            return false;
        }
        
        if (filho.estaCasada()) {
            System.out.println("Aviso: O filho já está casado.");
        }
        
        // Adicionar filho ao casamento
        casamentoPais.adicionarFilho(filho);
        
        // Atualizar relacionamentos
        relacionamentosPaisFilhos.get(pai).add(filho);
        relacionamentosPaisFilhos.get(mae).add(filho);
        
        System.out.println("Filho associado: " + filho.getNome() + 
                         " aos pais " + pai.getNome() + " e " + mae.getNome());
        return true;
    }
    
    /**
     * Busca uma pessoa pelo nome
     * @param nome Nome da pessoa
     * @return A pessoa ou null se não encontrada
     */
    public Pessoa buscarPessoa(String nome) {
        for (Pessoa p : pessoas) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Obtém todos os descendentes de uma pessoa
     * @param pessoa Pessoa raiz
     * @return Lista de descendentes
     */
    public List<Pessoa> getDescendentes(Pessoa pessoa) {
        List<Pessoa> descendentes = new ArrayList<>();
        getDescendentesRecursivo(pessoa, descendentes);
        return descendentes;
    }
    
    private void getDescendentesRecursivo(Pessoa pessoa, List<Pessoa> descendentes) {
        List<Pessoa> filhos = relacionamentosPaisFilhos.get(pessoa);
        if (filhos != null) {
            for (Pessoa filho : filhos) {
                if (!descendentes.contains(filho)) {
                    descendentes.add(filho);
                    getDescendentesRecursivo(filho, descendentes);
                }
            }
        }
    }
    
    /**
     * Obtém os ancestrais de uma pessoa
     * @param pessoa Pessoa para buscar ancestrais
     * @return Lista de ancestrais
     */
    public List<Pessoa> getAncestrias(Pessoa pessoa) {
        List<Pessoa> ancestrais = new ArrayList<>();
        List<Pessoa> pais = getPais(pessoa);
        
        for (Pessoa pai : pais) {
            if (!ancestrais.contains(pai)) {
                ancestrais.add(pai);
                ancestrais.addAll(getAncestrias(pai));
            }
        }
        
        return ancestrais;
    }
    
    /**
     * Obtém os pais de uma pessoa
     * @param pessoa Pessoa para buscar os pais
     * @return Lista com os pais (0, 1 ou 2 elementos)
     */
    public List<Pessoa> getPais(Pessoa pessoa) {
        List<Pessoa> pais = new ArrayList<>();
        
        for (Map.Entry<Pessoa, List<Pessoa>> entry : relacionamentosPaisFilhos.entrySet()) {
            if (entry.getValue().contains(pessoa)) {
                pais.add(entry.getKey());
            }
        }
        
        return pais;
    }
    
    /**
     * Exibe a árvore genealógica completa
     */
    public void exibirArvore() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    ÁRVORE GENEALÓGICA COMPLETA");
        System.out.println("=".repeat(80));
        
        // Encontrar pessoas raiz (sem pais)
        List<Pessoa> raizes = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            if (getPais(pessoa).isEmpty()) {
                raizes.add(pessoa);
            }
        }
        
        // Exibir cada raiz e seus descendentes
        for (Pessoa raiz : raizes) {
            exibirSubArvore(raiz, "", true, new HashSet<>());
        }
        
        System.out.println("=".repeat(80) + "\n");
    }
    
    private void exibirSubArvore(Pessoa pessoa, String prefixo, boolean ehUltimo, Set<Pessoa> visitados) {
        if (visitados.contains(pessoa)) {
            return; // Evita loops
        }
        visitados.add(pessoa);
        
        String conector = ehUltimo ? "└── " : "├── ";
        String informacaoCasamento = "";
        
        if (pessoa.estaCasada()) {
            Casamento cas = pessoa.getCasamento();
            Pessoa conjuge = cas.getConjuge(pessoa);
            informacaoCasamento = "casado(a) com " + conjuge.getNome();
        }
        
        System.out.println(prefixo + conector + pessoa + informacaoCasamento);
        
        List<Pessoa> filhos = relacionamentosPaisFilhos.get(pessoa);
        if (filhos != null && !filhos.isEmpty()) {
            String novoPrefixo = prefixo + (ehUltimo ? "    " : "│   ");
            
            for (int i = 0; i < filhos.size(); i++) {
                boolean ehUltimoFilho = (i == filhos.size() - 1);
                exibirSubArvore(filhos.get(i), novoPrefixo, ehUltimoFilho, visitados);
            }
        }
    }
    
    /**
     * Exibe estatísticas da árvore genealógica
     */
    public void exibirEstatisticas() {
        System.out.println("\nESTATÍSTICAS DA ÁRVORE GENEALÓGICA");
        System.out.println("─".repeat(50));
        System.out.println("Total de pessoas: " + pessoas.size());
        System.out.println("Total de casamentos: " + casamentos.size());
        
        int totalFilhos = 0;
        for (Casamento c : casamentos) {
            totalFilhos += c.getNumeroFilhos();
        }
        System.out.println("Total de filhos registrados: " + totalFilhos);
        
        // Maior descendência
        int maiorDescendencia = 0;
        Pessoa pessoaMaiorDescendencia = null;
        
        for (Pessoa p : pessoas) {
            int numDescendentes = getDescendentes(p).size();
            if (numDescendentes > maiorDescendencia) {
                maiorDescendencia = numDescendentes;
                pessoaMaiorDescendencia = p;
            }
        }
        
        if (pessoaMaiorDescendencia != null) {
            System.out.println("Maior descendência: " + pessoaMaiorDescendencia.getNome() + 
                             " com " + maiorDescendencia + " descendentes");
        }
        System.out.println("─".repeat(50) + "\n");
    }
    
    // Getters
    public List<Pessoa> getPessoas() {
        return new ArrayList<>(pessoas);
    }
    
    public List<Casamento> getCasamentos() {
        return new ArrayList<>(casamentos);
    }
}