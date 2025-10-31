package TorreHanoi;

import java.util.Scanner;

/**
 * Classe principal para executar o jogo Torre de Hanoi
 * Responsavel pela interface com o usuario e controle do fluxo do jogo
 */
public class TorreDeHanoiMain {
    private static Scanner scanner = new Scanner(System.in);
    private static JogoHanoi jogo;
    
    public static void main(String[] args) {
        exibirBanner();
        
        try {
            // Solicita numero de discos
            int numeroDiscos = solicitarNumeroDiscos();
            
            // Inicializa o jogo
            jogo = new JogoHanoi(numeroDiscos);
            
            System.out.println("\n[OK] Jogo iniciado com " + numeroDiscos + " disco(s)!");
            System.out.println("[INFO] Objetivo: Mover todos os discos da Torre A para a Torre C");
            System.out.println("[INFO] Regra: Disco maior nunca pode ficar sobre disco menor\n");
            
            // Exibe estado inicial
            jogo.exibirVisual();
            
            // Loop principal do jogo
            executarJogo();
            
        } catch (Exception e) {
            System.err.println("\n[ERRO] " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Exibe o banner inicial do jogo
     */
    private static void exibirBanner() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                  TORRE DE HANOI");
        System.out.println("          Implementacao com Estrutura de Dados");
        System.out.println("                 (usando Stack/Pilha)");
        System.out.println("=".repeat(60) + "\n");
    }
    
    /**
     * Solicita ao usuario o numero de discos
     * @return Numero de discos escolhido
     */
    private static int solicitarNumeroDiscos() {
        int discos = 0;
        boolean valido = false;
        
        while (!valido) {
            System.out.print("Quantos discos deseja usar? (recomendado: 3-7): ");
            try {
                String input = scanner.nextLine().trim();
                discos = Integer.parseInt(input);
                
                if (discos < 1) {
                    System.out.println("[ERRO] Numero deve ser maior que zero!");
                } else if (discos > 10) {
                    System.out.println("[AVISO] Mais de 10 discos pode ser muito dificil!");
                    System.out.print("Deseja continuar? (S/N): ");
                    String confirma = scanner.nextLine().trim().toUpperCase();
                    if (confirma.equals("S") || confirma.equals("SIM")) {
                        valido = true;
                    }
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Entrada invalida! Digite um numero.");
            }
        }
        
        return discos;
    }
    
    /**
     * Executa o loop principal do jogo
     */
    private static void executarJogo() {
        boolean jogando = true;
        
        while (jogando) {
            // Verifica se o jogo foi completado
            if (jogo.jogoCompleto()) {
                exibirVitoria();
                jogando = !perguntarJogarNovamente();
                continue;
            }
            
            // Solicita movimento
            String comando = solicitarComando();
            
            // Processa comando
            switch (comando.toUpperCase()) {
                case "SAIR":
                case "S":
                    System.out.println("\nObrigado por jogar! Ate a proxima!");
                    jogando = false;
                    break;
                    
                case "REINICIAR":
                case "R":
                    jogo.reiniciar();
                    System.out.println("\n[INFO] Jogo reiniciado!");
                    jogo.exibirVisual();
                    break;
                    
                case "AJUDA":
                case "H":
                    exibirAjuda();
                    break;
                    
                default:
                    processarMovimento(comando);
                    break;
            }
        }
    }
    
    /**
     * Solicita um comando ao usuario
     * @return Comando digitado
     */
    private static String solicitarComando() {
        System.out.print("\nSeu movimento (ex: AB, origem->destino): ");
        return scanner.nextLine().trim();
    }
    
    /**
     * Processa um movimento do jogador
     * @param comando Comando no formato "AB" (de A para B)
     */
    private static void processarMovimento(String comando) {
        comando = comando.toUpperCase().replaceAll("\\s+", "");
        
        if (comando.length() != 2) {
            System.out.println("[ERRO] Formato invalido! Use: AB (de A para B)");
            System.out.println("Ou digite 'AJUDA' para ver os comandos");
            return;
        }
        
        String origem = String.valueOf(comando.charAt(0));
        String destino = String.valueOf(comando.charAt(1));
        
        // Valida se sao letras validas
        if (!origem.matches("[ABC]") || !destino.matches("[ABC]")) {
            System.out.println("[ERRO] Torres invalidas! Use apenas A, B ou C");
            return;
        }
        
        // Realiza o movimento
        boolean sucesso = jogo.moverDisco(origem, destino);
        
        if (sucesso) {
            jogo.exibirVisual();
        }
    }
    
    /**
     * Exibe mensagem de vitoria
     */
    private static void exibirVitoria() {
        System.out.println("\n" + "*".repeat(60));
        System.out.println("           PARABENS! VOCE COMPLETOU O JOGO!");
        System.out.println("*".repeat(60));
        
        jogo.exibirVisual();
        
        int movimentos = jogo.getMovimentos();
        int minimo = jogo.getMovimentosMinimos();
        
        System.out.println("ESTATISTICAS:");
        System.out.println("   - Movimentos realizados: " + movimentos);
        System.out.println("   - Movimentos minimos: " + minimo);
        
        if (movimentos == minimo) {
            System.out.println("   [PERFEITO] Voce resolveu no numero minimo de movimentos!");
        } else {
            int diferenca = movimentos - minimo;
            System.out.println("   [INFO] Voce usou " + diferenca + " movimento(s) a mais.");
            System.out.println("   Tente novamente para alcançar o recorde!");
        }
        
        System.out.println("*".repeat(60) + "\n");
    }
    
    /**
     * Pergunta se o jogador deseja jogar novamente
     * @return true se deseja jogar novamente
     */
    private static boolean perguntarJogarNovamente() {
        System.out.print("Deseja jogar novamente? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();
        
        if (resposta.equals("S") || resposta.equals("SIM")) {
            System.out.print("   Mesmo numero de discos? (S/N): ");
            String mesmoNumero = scanner.nextLine().trim().toUpperCase();
            
            if (mesmoNumero.equals("S") || mesmoNumero.equals("SIM")) {
                jogo.reiniciar();
            } else {
                int novosDiscos = solicitarNumeroDiscos();
                jogo = new JogoHanoi(novosDiscos);
            }
            
            System.out.println("\n[INFO] Novo jogo iniciado!");
            jogo.exibirVisual();
            return false;
        }
        
        System.out.println("\nObrigado por jogar! Ate a proxima!");
        return true;
    }
    
    /**
     * Exibe a tela de ajuda
     */
    private static void exibirAjuda() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                        AJUDA");
        System.out.println("=".repeat(60));
        System.out.println("\nOBJETIVO:");
        System.out.println("   Mover todos os discos da Torre A para a Torre C");
        
        System.out.println("\nREGRAS:");
        System.out.println("   - Apenas um disco pode ser movido por vez");
        System.out.println("   - Um disco maior nunca pode ficar sobre um disco menor");
        System.out.println("   - Voce pode usar a Torre B como auxiliar");
        
        System.out.println("\nCOMANDOS:");
        System.out.println("   - AB      : Move disco da Torre A para Torre B");
        System.out.println("   - AC      : Move disco da Torre A para Torre C");
        System.out.println("   - BC      : Move disco da Torre B para Torre C");
        System.out.println("   - (e todas as outras combinacoes)");
        System.out.println("\n   - AJUDA   : Exibe esta ajuda");
        System.out.println("   - REINICIAR : Reinicia o jogo atual");
        System.out.println("   - SAIR    : Encerra o jogo");
        
        System.out.println("\nDICA:");
        System.out.println("   O numero minimo de movimentos e 2^n - 1");
        System.out.println("   (onde n e o numero de discos)");
        
        System.out.println("\n" + "=".repeat(60) + "\n");
    }
}