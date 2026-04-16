package main.java.br.com.threadjava.service;
import main.java.br.com.threadjava.factory.EsteiraFabrica;
import main.java.br.com.threadjava.model.Veiculo;
import main.java.br.com.threadjava.remote.IFabrica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class FabricaServico extends UnicastRemoteObject implements IFabrica {
    private EsteiraFabrica esteira;

    public FabricaServico(EsteiraFabrica esteira) throws RemoteException {
        super();
        this.esteira = esteira;
    }
    @Override
    public Veiculo solicitarVeiculo() throws RemoteException, InterruptedException {
        return esteira.remover();
    }

    @Override
    public void registrarLogVenda(int idLoja, int posicaoLoja, Veiculo v) throws RemoteException {
        System.out.println("LOG VENDA PARA LOJA: " + v + " | Id Loja: " + idLoja + " | Posicao Esteira Loja: " + posicaoLoja);
    }
}
