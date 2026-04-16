package br.com.threadjava.remote;

import br.com.threadjava.model.Veiculo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILoja extends Remote {
    Veiculo comprarVeiculo(int idCliente) throws RemoteException, InterruptedException;
    int getIdLoja() throws RemoteException;
}