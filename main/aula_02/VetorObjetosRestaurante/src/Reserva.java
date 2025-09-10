public class Reserva {
    private final String cliente;
    // mesa = 0 quando a reserva está na fila de espera (sem mesa atribuída)
    private int mesa;

    public Reserva(String cliente, int mesa) {
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public String getCliente() {
        return cliente;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    @Override
    public String toString() {
        if (mesa > 0) {
            return "Reserva{cliente='" + cliente + "', mesa=" + mesa + "}";
        }
        return "Reserva{cliente='" + cliente + "', EM ESPERA}";
    }
}
