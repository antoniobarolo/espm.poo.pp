public class Reserva implements Pagamento {
    private Cliente cliente;
    private boolean PagamentoAVista;

    public Reserva(Cliente cliente, boolean PagamentoAVista) {
        this.cliente = cliente;
        this.PagamentoAVista = PagamentoAVista;
    }

    public Cliente getCliente(){
        return cliente;
    }

    @Override
    public String toString() {
        String pagamento;
        if (PagamentoAVista) {
            pagamento = "à vista";
        } else {
            pagamento = "parcelado";
        }

        return "Tipo do cliente: " + this.cliente.getClass() + "\n" + this.cliente.toString() + "\nPagamento: " + pagamento;
    }

    public double CalcularPagamento(){
        double valor = 3200;
        if (PagamentoAVista){
            valor*=0.9;
        }
        return valor;
    }
}
