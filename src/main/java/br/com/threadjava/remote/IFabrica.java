package main.java.br.com.threadjava.remote;
import main.java.br.com.threadjava.model.Veiculo;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IFabrica extends Remote {
    Veiculo solicitarVeiculo() throws RemoteException, InterruptedException;
    void registrarLogVenda(int idLoja, int posicaoLoja, Veiculo v) throws RemoteException;
}
