public class SistemaReservas {

    private final Reserva[] mesas;           // índice 0 => mesa 1
    private final FilaGenerica<Reserva> espera;

    public SistemaReservas(int maxMesas) {
        if (maxMesas <= 0) throw new IllegalArgumentException("maxMesas deve ser > 0");
        this.mesas = new Reserva[maxMesas];
        this.espera = new FilaGenerica<>(maxMesas); // inicial
    }

    public int capacidade() {
        return mesas.length;
    }

    public boolean estaReservada(int numeroMesa) {
        validarMesa(numeroMesa);
        return mesas[numeroMesa - 1] != null;
    }

    public int proximaMesaLivre() {
        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i] == null) return i + 1; // número da mesa
        }
        return -1; // não há mesa livre
    }

    /**
     * Tenta reservar para 'cliente'.
     * @return número da mesa se sentar imediatamente; -1 se foi para a fila.
     */
    public int reservar(String cliente) {
        if (cliente == null || cliente.isBlank()) {
            throw new IllegalArgumentException("Cliente inválido");
        }
        int livre = proximaMesaLivre();
        if (livre != -1) {
            mesas[livre - 1] = new Reserva(cliente, livre);
            return livre;
        }
        // sem mesa -> vai para fila
        espera.enfileirar(new Reserva(cliente, 0));
        return -1;
    }

    /**
     * Cancela a reserva da mesa e, se houver fila, aloca o primeiro da fila nesta mesa.
     * @return true se havia reserva e foi cancelada; false se já estava livre.
     */
    public boolean cancelar(int numeroMesa) {
        validarMesa(numeroMesa);
        int idx = numeroMesa - 1;
        if (mesas[idx] == null) return false;

        // libera a mesa
        mesas[idx] = null;

        // chama o primeiro da fila, se houver
        if (!espera.vazia()) {
            Reserva r = espera.desenfileirar();
            r.setMesa(numeroMesa);
            mesas[idx] = r;
        }
        return true;
    }

    public void imprimirResumo() {
        System.out.println("=== Mesas (" + capacidade() + ") ===");
        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i] == null) {
                System.out.println("Mesa " + (i + 1) + ": LIVRE");
            } else {
                System.out.println("Mesa " + (i + 1) + ": " + mesas[i]);
            }
        }
        System.out.println("=== Fila de Espera (tamanho=" + espera.tamanho() + ") ===");
        // Para visualizar a fila sem expor a estrutura interna, podemos desalocar em cópia:
        // (aqui, apenas um aviso; em produção, criar um iterador da fila seria o ideal)
    }

    private void validarMesa(int numeroMesa) {
        if (numeroMesa < 1 || numeroMesa > mesas.length) {
            throw new IndexOutOfBoundsException("Mesa inválida: " + numeroMesa);
        }
    }
}
