package TorreHanoi;

import java.util.Stack;

/**
 * Classe que representa uma torre individual no problema da Torre de Hanói
 * Utiliza a estrutura de dados Stack (Pilha) do Java Collections Framework
 */
public class Torre {
    private Stack<Integer> pilha;
    private String nome;
    
    /**
     * Construtor da Torre
     * @param nome Nome identificador da torre (A, B ou C)
     */
    public Torre(String nome) {
        this.nome = nome;
        this.pilha = new Stack<>();
    }
    
    /**
     * Adiciona um disco no topo da torre (push na pilha)
     * @param disco Tamanho do disco a ser adicionado
     * @return true se o disco foi adicionado com sucesso
     * @throws IllegalArgumentException se o movimento for inválido
     */
    public boolean adicionarDisco(int disco) {
        // Valida se o disco pode ser colocado (deve ser menor que o disco no topo)
        if (!pilha.isEmpty() && disco > pilha.peek()) {
            throw new IllegalArgumentException(
                "Movimento inválido: não pode colocar disco maior sobre disco menor!"
            );
        }
        
        return pilha.push(disco) != null;
    }
    
    /**
     * Remove e retorna o disco do topo da torre (pop da pilha)
     * @return O disco removido
     * @throws IllegalStateException se a torre estiver vazia
     */
    public int removerDisco() {
        if (pilha.isEmpty()) {
            throw new IllegalStateException("Torre vazia! Não há discos para remover.");
        }
        
        return pilha.pop();
    }
    
    /**
     * Retorna o disco do topo sem removê-lo (peek da pilha)
     * @return O disco no topo ou -1 se a torre estiver vazia
     */
    public int verTopo() {
        return pilha.isEmpty() ? -1 : pilha.peek();
    }
    
    /**
     * Verifica se a torre está vazia
     * @return true se não houver discos na torre
     */
    public boolean estaVazia() {
        return pilha.isEmpty();
    }
    
    /**
     * Retorna a quantidade de discos na torre
     * @return Número de discos
     */
    public int getTamanho() {
        return pilha.size();
    }
    
    /**
     * Retorna o nome da torre
     * @return Nome da torre
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Retorna a pilha (para visualização)
     * @return Stack de discos
     */
    public Stack<Integer> getPilha() {
        return pilha;
    }
    
    /**
     * Limpa todos os discos da torre
     */
    public void limpar() {
        pilha.clear();
    }
    
    /**
     * Representação textual da torre
     * @return String mostrando os discos da base ao topo
     */
    @Override
    public String toString() {
        if (pilha.isEmpty()) {
            return "[ vazia ]";
        }
        
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < pilha.size(); i++) {
            sb.append(pilha.get(i));
            if (i < pilha.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" ]");
        
        return sb.toString();
    }
    
    /**
     * Verifica se pode receber um determinado disco
     * @param disco Tamanho do disco
     * @return true se o movimento for válido
     */
    public boolean podeReceberDisco(int disco) {
        return pilha.isEmpty() || disco < pilha.peek();
    }
}