package br.com.threadjava.app;

import br.com.threadjava.service.LojaServico;
import br.com.threadjava.store.Loja;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LojaMain {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando Lojas (Processo RMI)...");

            String hostFabrica = args.length > 0 ? args[0] : "localhost";

            Registry registry = LocateRegistry.createRegistry(1098);

            Loja[] lojas = new Loja[3];

            for (int i = 0; i < 3; i++) {
                lojas[i] = new Loja(i, hostFabrica);
                LojaServico servico = new LojaServico(i, lojas[i].getEsteiraLocal());
                String nomeServico = "ServicoLoja" + i;
                registry.rebind(nomeServico, servico);
                lojas[i].start();
                System.out.println("Loja " + i + " registrada no RMI como '" + nomeServico + "' na porta 1098.");
            }

            System.out.println("Todas as lojas estão operando e aguardando clientes.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}