package TorreHanoi;

/**
 * Classe que representa o jogo Torre de Hanoi
 * Gerencia as tres torres e a logica de movimentacao dos discos
 */
public class JogoHanoi {
    private Torre torreA;
    private Torre torreB;
    private Torre torreC;
    private int numeroDiscos;
    private int movimentos;
    
    /**
     * Construtor do jogo
     * @param numeroDiscos Quantidade de discos para o jogo
     */
    public JogoHanoi(int numeroDiscos) {
        if (numeroDiscos < 1) {
            throw new IllegalArgumentException("Numero de discos deve ser maior que zero!");
        }
        
        this.numeroDiscos = numeroDiscos;
        this.movimentos = 0;
        this.torreA = new Torre("A");
        this.torreB = new Torre("B");
        this.torreC = new Torre("C");
        
        inicializarJogo();
    }
    
    /**
     * Inicializa o jogo colocando todos os discos na torre A
     * Os discos sao empilhados do maior (na base) ao menor (no topo)
     */
    private void inicializarJogo() {
        // Adiciona discos do maior para o menor (base para topo)
        for (int i = numeroDiscos; i >= 1; i--) {
            torreA.adicionarDisco(i);
        }
    }
    
    /**
     * Reinicia o jogo, voltando ao estado inicial
     */
    public void reiniciar() {
        torreA.limpar();
        torreB.limpar();
        torreC.limpar();
        movimentos = 0;
        inicializarJogo();
    }
    
    /**
     * Realiza um movimento de um disco de uma torre para outra
     * @param origem Nome da torre de origem (A, B ou C)
     * @param destino Nome da torre de destino (A, B ou C)
     * @return true se o movimento foi realizado com sucesso
     */
    public boolean moverDisco(String origem, String destino) {
        origem = origem.toUpperCase();
        destino = destino.toUpperCase();
        
        // Valida se as torres sao diferentes
        if (origem.equals(destino)) {
            System.out.println("[ERRO] Torre de origem e destino nao podem ser iguais!");
            return false;
        }
        
        Torre torreOrigem = obterTorre(origem);
        Torre torreDestino = obterTorre(destino);
        
        if (torreOrigem == null || torreDestino == null) {
            System.out.println("[ERRO] Torre invalida! Use A, B ou C.");
            return false;
        }
        
        // Verifica se a torre de origem esta vazia
        if (torreOrigem.estaVazia()) {
            System.out.println("[ERRO] Movimento invalido: Torre " + origem + " esta vazia!");
            return false;
        }
        
        // Verifica se o movimento e valido (disco menor sobre disco maior)
        int disco = torreOrigem.verTopo();
        if (!torreDestino.podeReceberDisco(disco)) {
            System.out.println("[ERRO] Movimento invalido: Nao pode colocar disco maior sobre disco menor!");
            System.out.println("Disco a mover: " + disco + " | Topo do destino: " + torreDestino.verTopo());
            return false;
        }
        
        // Realiza o movimento
        try {
            disco = torreOrigem.removerDisco();
            torreDestino.adicionarDisco(disco);
            movimentos++;
            System.out.println("[OK] Disco " + disco + " movido de " + origem + " para " + destino);
            return true;
        } catch (Exception e) {
            System.out.println("[ERRO] Erro ao mover disco: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtem uma torre pelo nome
     * @param nome Nome da torre (A, B ou C)
     * @return A torre correspondente ou null se invalido
     */
    private Torre obterTorre(String nome) {
        switch (nome.toUpperCase()) {
            case "A":
                return torreA;
            case "B":
                return torreB;
            case "C":
                return torreC;
            default:
                return null;
        }
    }
    
    /**
     * Verifica se o jogo foi completado
     * @return true se todos os discos estao na torre C
     */
    public boolean jogoCompleto() {
        return torreC.getTamanho() == numeroDiscos;
    }
    
    /**
     * Retorna o numero de movimentos realizados
     * @return Quantidade de movimentos
     */
    public int getMovimentos() {
        return movimentos;
    }
    
    /**
     * Calcula o numero minimo de movimentos para resolver o jogo
     * @return Numero minimo de movimentos (2^n - 1)
     */
    public int getMovimentosMinimos() {
        return (int) Math.pow(2, numeroDiscos) - 1;
    }
    
    /**
     * Retorna o numero de discos do jogo
     * @return Quantidade de discos
     */
    public int getNumeroDiscos() {
        return numeroDiscos;
    }
    
    // Getters para as torres
    public Torre getTorreA() {
        return torreA;
    }
    
    public Torre getTorreB() {
        return torreB;
    }
    
    public Torre getTorreC() {
        return torreC;
    }
    
    /**
     * Retorna o estado atual do jogo em formato texto
     * @return String com o estado das tres torres
     */
    public String getEstadoJogo() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(50)).append("\n");
        sb.append("Torre A: ").append(torreA.toString()).append("\n");
        sb.append("Torre B: ").append(torreB.toString()).append("\n");
        sb.append("Torre C: ").append(torreC.toString()).append("\n");
        sb.append("=".repeat(50)).append("\n");
        sb.append("Movimentos: ").append(movimentos);
        sb.append(" | Minimo: ").append(getMovimentosMinimos()).append("\n");
        return sb.toString();
    }
    
    /**
     * Exibe o estado atual do jogo no console
     */
    public void exibirEstado() {
        System.out.println(getEstadoJogo());
    }
    
    /**
     * Exibe representacao visual das torres
     */
    public void exibirVisual() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    TORRE DE HANOI");
        System.out.println("=".repeat(60));
        
        // Encontra a altura maxima
        int alturaMax = Math.max(torreA.getTamanho(), 
                        Math.max(torreB.getTamanho(), torreC.getTamanho()));
        
        // Se todas vazias, mostra altura do jogo
        if (alturaMax == 0) {
            alturaMax = numeroDiscos;
        }
        
        // Imprime de cima para baixo
        for (int nivel = alturaMax - 1; nivel >= 0; nivel--) {
            String discoA = obterDiscoPorNivel(torreA, nivel);
            String discoB = obterDiscoPorNivel(torreB, nivel);
            String discoC = obterDiscoPorNivel(torreC, nivel);
            
            System.out.printf("  %s    %s    %s%n", discoA, discoB, discoC);
        }
        
        System.out.println("  ===    ===    ===");
        System.out.println("   A      B      C");
        System.out.println("-".repeat(60));
        System.out.printf("Movimentos: %d | Minimo: %d%n", movimentos, getMovimentosMinimos());
        System.out.println("=".repeat(60) + "\n");
    }
    
    /**
     * Obtem representacao visual do disco em determinado nivel
     */
    private String obterDiscoPorNivel(Torre torre, int nivel) {
        if (nivel >= torre.getTamanho()) {
            return " | ";
        }
        
        int disco = torre.getPilha().get(nivel);
        return String.format("[%d]", disco);
    }
}