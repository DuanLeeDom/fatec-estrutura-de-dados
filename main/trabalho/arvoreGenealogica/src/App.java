// import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Criar instância da árvore genealógica
        ArvoreGenealogica arvore = new ArvoreGenealogica();

        System.out.println("SISTEMA DE ÁRVORE GENEALÓGICA");
        System.out.println("=".repeat(80));

        // ==== GERAÇÃO 1: Bisavós ====
        System.out.println("\nAdicionando Geração 1 (Bisavós)...");
        Pessoa joaoSilva = new Pessoa("João Silva", "15/03/1940");
        Pessoa mariaSilva = new Pessoa("Maria Silva", "22/07/1942");
        Pessoa pedroSantos = new Pessoa("Pedro Santos", "10/01/1938");
        Pessoa anaSantos = new Pessoa("Ana Santos", "05/12/1941");

        arvore.adicionarPessoa(joaoSilva);
        arvore.adicionarPessoa(mariaSilva);
        arvore.adicionarPessoa(pedroSantos);
        arvore.adicionarPessoa(anaSantos);

        // ==== CASAMENTOS GERAÇÃO 1 ====
        System.out.println("\nRegistrando casamentos da Geração 1...");
        arvore.registrarCasamento(joaoSilva, mariaSilva, "20/06/1960");
        arvore.registrarCasamento(pedroSantos, anaSantos, "15/08/1962");

        // ==== GERAÇÃO 2: Avós ====
        System.out.println("\nAdicionando Geração 2 (Avós)...");
        Pessoa carlosSilva = new Pessoa("Carlos Silva", "12/04/1965");
        Pessoa luciaOliveira = new Pessoa("Lúcia Oliveira", "28/09/1967");
        Pessoa robertaSantos = new Pessoa("Roberta Santos", "03/11/1963");
        Pessoa fernandoLima = new Pessoa("Fernando Lima", "17/02/1961");

        arvore.adicionarPessoa(carlosSilva);
        arvore.adicionarPessoa(luciaOliveira);
        arvore.adicionarPessoa(robertaSantos);
        arvore.adicionarPessoa(fernandoLima);

        // ==== ASSOCIAR FILHOS GERAÇÃO 2 ====
        System.out.println("\nAssociando filhos à Geração 1...");
        arvore.associarFilho(carlosSilva, joaoSilva, mariaSilva);
        arvore.associarFilho(robertaSantos, pedroSantos, anaSantos);

        // ==== CASAMENTOS GERAÇÃO 2 ====
        System.out.println("\nRegistrando casamentos da Geração 2...");
        arvore.registrarCasamento(carlosSilva, luciaOliveira, "10/12/1988");
        arvore.registrarCasamento(robertaSantos, fernandoLima, "25/05/1985");

        // ==== GERAÇÃO 3: Pais ====
        System.out.println("\nAdicionando Geração 3 (Pais)...");
        Pessoa marcosLima = new Pessoa("Marcos Lima", "08/07/1990");
        Pessoa julianaSilva = new Pessoa("Juliana Silva", "14/03/1992");
        Pessoa rafaelSilva = new Pessoa("Rafael Silva", "30/11/1989");

        arvore.adicionarPessoa(marcosLima);
        arvore.adicionarPessoa(julianaSilva);
        arvore.adicionarPessoa(rafaelSilva);

        // ==== ASSOCIAR FILHOS GERAÇÃO 3 ====
        System.out.println("\nAssociando filhos à Geração 2...");
        arvore.associarFilho(marcosLima, robertaSantos, fernandoLima);
        arvore.associarFilho(julianaSilva, carlosSilva, luciaOliveira);
        arvore.associarFilho(rafaelSilva, carlosSilva, luciaOliveira);

        // ==== CASAMENTOS GERAÇÃO 3 ====
        System.out.println("\nRegistrando casamentos da Geração 3...");
        arvore.registrarCasamento(marcosLima, julianaSilva, "18/02/2015");

        // ==== GERAÇÃO 4: Filhos ====
        System.out.println("\nAdicionando Geração 4 (Filhos atuais)...");
        Pessoa gabrielLima = new Pessoa("Gabriel Lima", "22/09/2016");
        Pessoa sophiaLima = new Pessoa("Sophia Lima", "05/05/2018");
        Pessoa miguelLima = new Pessoa("Miguel Lima", "11/01/2021");

        arvore.adicionarPessoa(gabrielLima);
        arvore.adicionarPessoa(sophiaLima);
        arvore.adicionarPessoa(miguelLima);

        // ==== ASSOCIAR FILHOS GERAÇÃO 4 ====
        System.out.println("\nAssociando filhos à Geração 3...");
        arvore.associarFilho(gabrielLima, marcosLima, julianaSilva);
        arvore.associarFilho(sophiaLima, marcosLima, julianaSilva);
        arvore.associarFilho(miguelLima, marcosLima, julianaSilva);

        // ==== EXIBIR ÁRVORE COMPLETA ====
        arvore.exibirArvore();

        // ==== EXIBIR ESTATÍSTICAS ====
        arvore.exibirEstatisticas();

        // ==== TESTES DE CONSULTA ====
        System.out.println("\nTESTES DE CONSULTA");
        System.out.println("=".repeat(80));

        // Buscar pessoa
        System.out.println("\n1. Buscando pessoa 'Gabriel Lima':");
        Pessoa gabriel = arvore.buscarPessoa("Gabriel Lima");
        if (gabriel != null) {
            System.out.println("Encontrado: " + gabriel);
        }

        // Consultar pais
        System.out.println("\n2. Pais de Gabriel Lima:");
        var pais = arvore.getPais(gabriel);
        for (Pessoa pai : pais) {
            System.out.println("   - " + pai.getNome());
        }

        // Consultar ancestrais
        System.out.println("\n3. Ancestrais de Gabriel Lima:");
        var ancestrais = arvore.getAncestrias(gabriel);
        for (Pessoa ancestral : ancestrais) {
            System.out.println("   - " + ancestral.getNome());
        }

        // Consultar descendentes
        System.out.println("\n4. Descendentes de João Silva:");
        var descendentes = arvore.getDescendentes(joaoSilva);
        System.out.println("   Total: " + descendentes.size() + " descendentes");
        for (Pessoa desc : descendentes) {
            System.out.println("   - " + desc.getNome());
        }

        System.out.println("");

        // VALIDAÇÕES E TESTES DE ERRO SE ESTÃO FUNCIONANDO
        System.out.println("\nTESTES DE VALIDAÇÃO E ERRO");
        System.out.println("=".repeat(80));

        System.out.println("\n1. Tentando adicionar pessoa duplicada:");
        arvore.adicionarPessoa(gabriel);

        System.out.println("\n2. Tentando casar pessoa já casada:");
        Pessoa novaPessoa = new Pessoa("Teste", "01/01/2000");
        arvore.adicionarPessoa(novaPessoa);
        arvore.registrarCasamento(marcosLima, novaPessoa, "01/01/2020");

        System.out.println("\n3. Tentando associar filho a casal não casado:");
        Pessoa filho = new Pessoa("Filho Teste", "01/01/2022");
        arvore.adicionarPessoa(filho);
        arvore.associarFilho(filho, rafaelSilva, novaPessoa);

        System.out.println("\n\nDemonstração Finalizada!");
        System.out.println("=".repeat(80));
    }
}
