import java.util.ArrayList;
import java.util.List;

public class Cliente extends Thread {
    private int idCliente;
    private Loja[] lojas;
    private List<Veiculo> garagem;

    public Cliente(int idCliente, Loja[] lojas) {
        this.idCliente = idCliente;
        this.lojas = lojas;
        this.garagem = new ArrayList<>();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public List<Veiculo> getGaragem() {
        return garagem;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) (Math.random() * 2000 + 500));

                int lojaEscolhida = (int) (Math.random() * lojas.length);
                Loja loja = lojas[lojaEscolhida];

                int veiculoParaComprar = (int) (Math.random() * 3) + 1;

                for (int i = 0; i < veiculoParaComprar; i++) {
                    try {
                        Veiculo veiculo = loja.getEsteira().remover();
                        if (veiculo != null) {
                            garagem.add(veiculo);
                            System.out.println("LOG VENDA CLIENTE: Cliente " + idCliente + " comprou " + veiculo);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Cliente " + idCliente + " aguardando veículo disponível na loja " + lojaEscolhida);
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
