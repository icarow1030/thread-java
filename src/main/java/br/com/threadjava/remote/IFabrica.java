package br.com.threadjava.remote;
import br.com.threadjava.model.Veiculo;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IFabrica extends Remote {
    Veiculo solicitarVeiculo() throws RemoteException, InterruptedException;
    void registrarLogVenda(int idLoja, int posicaoLoja, Veiculo v) throws RemoteException;
}
