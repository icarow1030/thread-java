package main.java.br.com.threadjava.app;


import main.java.br.com.threadjava.client.Cliente;
import main.java.br.com.threadjava.factory.Estacao;
import main.java.br.com.threadjava.factory.EsteiraFabrica;
import main.java.br.com.threadjava.store.Loja;

import java.util.concurrent.Semaphore;

public class LojaMain {
    public static void main(String[] args) {
        System.out.println("Iniciando Lojas e Clientes (Processo Cliente RMI)...");

        Loja[] lojas = new Loja[3];
        for(int i = 0; i < 3; i++) {
            lojas[i] = new Loja(i, "localhost");
            lojas[i].start();
        }

        Cliente[] clientes = new Cliente[20];
        for(int i = 0; i < 20; i++) {
            clientes[i] = new Cliente(i, lojas);
            clientes[i].start();
        }

        System.out.println("Lojas abertas e clientes operando.");
    }
}
