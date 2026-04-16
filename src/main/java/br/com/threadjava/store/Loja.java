
package main.java.br.com.threadjava.store;


import main.java.br.com.threadjava.factory.EsteiraFabrica;
import main.java.br.com.threadjava.model.Veiculo;
import main.java.br.com.threadjava.remote.IFabrica;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Loja extends Thread {
    private int idLoja;
    private EsteiraLoja esteiraLocal;
    private IFabrica fabricaRemota;

    public Loja(int idLoja, String hostFabrica) {
        this.idLoja = idLoja;
        this.esteiraLocal = new EsteiraLoja(40);
        try {
            Registry registry = LocateRegistry.getRegistry(hostFabrica, 1099);
            this.fabricaRemota = (IFabrica) registry.lookup("ServicoFabrica");
        } catch(Exception e) {
            System.err.println("Loja " + idLoja + " não conseguiu conectar à fábrica: " + e.getMessage());
        }
    }

    public int getIdLoja() {
        return idLoja;
    }

    public EsteiraLoja getEsteiraLocal() {
        return esteiraLocal;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Veiculo v = fabricaRemota.solicitarVeiculo();
                if(v != null) {
                    int pos = esteiraLocal.inserir(v);
                    fabricaRemota.registrarLogVenda(idLoja, pos, v);
                }
                Thread.sleep(100);
            }
        } catch(Exception e) {
            System.err.println("Conexão com a fábrica perdida na loja " + idLoja);
        }
    }
}

