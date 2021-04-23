import java.util.ArrayList;
import javax.swing.JOptionPane;

public class App {
    static ArrayList<Reserva> Lista = new ArrayList();
    static ArrayList<Reserva> ListaEspera = new ArrayList();

    public static void main(String[] args) throws Exception {
        Menu();
    }

    public static void Menu() {
        String resp;
        while (true) {
            resp = JOptionPane.showInputDialog(
                    "Restaurante SABOR SOFISTICADO\n1. Reservar Mesa\n2. Pesquisar reserva\n3. Imprimir reservas\n4. Imprimir lista de espera\n5.Cancelar reserva\n6.Finalizar");
            switch (resp) {
            case "1":
                ReservarMesa();
                break;
            case "2":
                PesquisarReserva();
                break;
            case "3":
                ImprimirReservas();
                break;
            case "4":
                ImprimirListaDeEspera();
                break;
            case "5":
                CancelarReserva();
                break;
            case "6":
                return;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    public static void ReservarMesa() {
        String nome, cpf;
        boolean ePessoaFisica, PagamentoAVista;
        boolean espera = false;

        if (Lista.size() >= 6) {
            JOptionPane.showMessageDialog(null, "Reservas lotadas! Seu cadastro será registrado na lista de espera.");
            espera = true;
        }

        while (true) {
            nome = JOptionPane.showInputDialog("Informe o nome");
            if (nome == null) {
                JOptionPane.showMessageDialog(null, "Informações inválidas ou não preenchidas!");
            } else {
                break;
            }
        }

        while (true) {
            Object[] itens = { "Pessoa Física", "Pessoa Jurídica" };
            Object tipo = JOptionPane.showInputDialog(null, "Pessoa Física ou Jurídica", null,
                    JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);
            if (tipo == "Pessoa Física") {
                ePessoaFisica = true;
                while (true) {
                    cpf = JOptionPane.showInputDialog("Informe o cpf");
                    if (cpf == null) {
                        JOptionPane.showMessageDialog(null, "Informações inválidas ou não preenchidas!");
                    } else {
                        break;
                    }
                }
                break;
            } else if (tipo == "Pessoa Jurídica") {
                ePessoaFisica = false;
                while (true) {
                    cpf = JOptionPane.showInputDialog("Informe o cnpj");
                    if (cpf == null) {
                        JOptionPane.showMessageDialog(null, "Informações inválidas ou não preenchidas!");
                    } else {
                        break;
                    }
                }
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Informações inválidas ou não preenchidas!");
            }
        }

        while (true) {
            Object[] itens = { "à vista", "parcelado" };
            Object tipo = JOptionPane.showInputDialog(null, "Forma de pagamento?", null,
                    JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);
            if (tipo == "à vista") {
                PagamentoAVista = true;
                break;
            } else if (tipo == "parcelado") {
                PagamentoAVista = false;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Informações inválidas ou não preenchidas!");
            }
        }

        Cliente cliente;
        if (ePessoaFisica) {
            cliente = new PessoaFisica(nome, cpf);
        } else {
            cliente = new PessoaJuridica(nome, cpf);
        }
        Reserva reserva = new Reserva(cliente, PagamentoAVista);

        if (espera) {
            ListaEspera.add(reserva);
            return;
        }
        Lista.add(reserva);

    }

    public static void PesquisarReserva() {
        String cpf = JOptionPane.showInputDialog("Informe o cpf ou cnpj a ser pesquisado");

        for (int i = 0; i < Lista.size(); i++) {
            if (Lista.get(i).getCliente().getCpfCnpj().equals(cpf)) {
                JOptionPane.showMessageDialog(null, Lista.get(i).toString());
                JOptionPane.showMessageDialog(null, "Esta pessoa tem reserva!");
                return;
            }
        }
        for (int i = 0; i < ListaEspera.size(); i++) {
            if (ListaEspera.get(i).getCliente().getCpfCnpj().equals(cpf)) {
                JOptionPane.showMessageDialog(null, ListaEspera.get(i).toString());
                JOptionPane.showMessageDialog(null, "Esta pessoa está na lista de espera!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Pessoa não encontrada...");
    }

    public static void ImprimirReservas() {
        String impressao = "";
        for (int i = 0; i < Lista.size(); i++) {
            impressao += Lista.get(i).toString() + "\n\n";
        }
        JOptionPane.showMessageDialog(null, impressao);
    }

    public static void ImprimirListaDeEspera() {
        String impressao = "";
        for (int i = 0; i < ListaEspera.size(); i++) {
            impressao += ListaEspera.get(i).toString() + "\nPosição na lista de espera: " + (i + 1) + "\n\n";
        }
        JOptionPane.showMessageDialog(null, impressao);
    }

    public static void CancelarReserva() {
        String cpf = JOptionPane.showInputDialog("Informe o cpf ou cnpj a ser removido");

        for (int i = 0; i < Lista.size(); i++) {
            if (Lista.get(i).getCliente().getCpfCnpj().equals(cpf)) {
                int ctz = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Pessoa Encontrada!",
                        JOptionPane.YES_NO_OPTION);
                if (ctz != 0) {
                    JOptionPane.showMessageDialog(null, "Operação cancelada");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Pessoa deletada!");
                Lista.remove(i);

                if (ListaEspera.size() > 0) {
                    Lista.add(ListaEspera.get(0));
                    ListaEspera.remove(0);
                }
                return;
            }
        }
        for (int i = 0; i < ListaEspera.size(); i++) {
            if (ListaEspera.get(i).getCliente().getCpfCnpj().equals(cpf)) {
                int ctz = JOptionPane.showConfirmDialog(null, "Esta reserva está na lista de espera. Tem certeza que quer cancelar?",
                        "Pessoa encontrada!",
                        JOptionPane.YES_NO_OPTION);
                if (ctz != 0) {
                    JOptionPane.showMessageDialog(null, "Operação cancelada");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Pessoa deletada!");
                ListaEspera.remove(i);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Cpf não encontrado...");
    }

}