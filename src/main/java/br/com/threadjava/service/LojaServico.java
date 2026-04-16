package br.com.threadjava.service;

import br.com.threadjava.model.Veiculo;
import br.com.threadjava.remote.ILoja;
import br.com.threadjava.store.EsteiraLoja;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LojaServico extends UnicastRemoteObject implements ILoja {
    private int idLoja;
    private EsteiraLoja esteiraLocal;

    public LojaServico(int idLoja, EsteiraLoja esteiraLocal) throws RemoteException {
        super();
        this.idLoja = idLoja;
        this.esteiraLocal = esteiraLocal;
    }

    @Override
    public Veiculo comprarVeiculo(int idCliente) throws RemoteException, InterruptedException {
        return esteiraLocal.venderParaCliente(idCliente, idLoja);
    }

    @Override
    public int getIdLoja() throws RemoteException {
        return idLoja;
    }
}