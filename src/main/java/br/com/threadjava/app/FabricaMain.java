package br.com.threadjava.app;

import br.com.threadjava.factory.Estacao;
import br.com.threadjava.factory.EsteiraFabrica;
import br.com.threadjava.remote.IFabrica;
import br.com.threadjava.service.FabricaServico;

import java.util.concurrent.Semaphore;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FabricaMain {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando infraestruturas da fábrica...");

            Semaphore estoquePecas = new Semaphore(500);
            Semaphore esteiraPecas = new Semaphore(5);
            EsteiraFabrica esteiraPrincipal = new EsteiraFabrica(40);

            for(int i = 0; i < 4; i++) {
                new Estacao(i, esteiraPrincipal, estoquePecas, esteiraPecas).iniciarProducao();
            }

            IFabrica stub = new FabricaServico(esteiraPrincipal);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ServicoFabrica", stub);
            System.out.println("Fábrica distribuída pronta no porto 1099.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
