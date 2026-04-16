package br.com.threadjava.app;

import br.com.threadjava.client.Cliente;
import br.com.threadjava.remote.ILoja;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteMain {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando Clientes (Processo RMI)...");

            String hostLojas = args.length > 0 ? args[0] : "localhost";

            Registry registry = LocateRegistry.getRegistry(hostLojas, 1098);

            ILoja[] lojasRemotas = new ILoja[3];
            for (int i = 0; i < 3; i++) {
                String nomeServico = "ServicoLoja" + i;
                lojasRemotas[i] = (ILoja) registry.lookup(nomeServico);
                System.out.println("Cliente conectado à " + nomeServico);
            }

            Cliente[] clientes = new Cliente[20];
            for (int i = 0; i < 20; i++) {
                clientes[i] = new Cliente(i, lojasRemotas);
                clientes[i].start();
            }

            System.out.println("20 clientes operando via RMI.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}