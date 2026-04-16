package br.com.threadjava.client;

import br.com.threadjava.model.Veiculo;
import br.com.threadjava.remote.ILoja;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Thread {
    private int idCliente;
    private ILoja[] lojas;
    private List<Veiculo> garagem;

    public Cliente(int idCliente, ILoja[] lojas) {
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

                int indiceEscolhido = (int) (Math.random() * lojas.length);
                ILoja lojaEscolhida = lojas[indiceEscolhido];

                int quantidadeParaComprar = (int) (Math.random() * 3) + 1;

                for (int i = 0; i < quantidadeParaComprar; i++) {
                    try {
                        Veiculo veiculo = lojaEscolhida.comprarVeiculo(idCliente);
                        if (veiculo != null) {
                            garagem.add(veiculo);
                            System.out.println("Cliente " + idCliente + " comprou: " + veiculo + " | Garagem: "
                                    + garagem.size() + " veículos");
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Cliente " + idCliente + " aguardando veículo disponível.");
                        Thread.currentThread().interrupt();
                        return;
                    } catch (RemoteException e) {
                        System.err.println("Cliente " + idCliente + " erro RMI ao conectar na loja: " + e.getMessage());
                        break;
                    } catch (Exception e) {
                        System.err.println("Cliente " + idCliente + " erro desconhecido: " + e.getMessage());
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}