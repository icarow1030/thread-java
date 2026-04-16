package br.com.threadjava.app;

import br.com.threadjava.factory.Estacao;
import br.com.threadjava.factory.EsteiraFabrica;
import java.util.concurrent.Semaphore;

public class FabricaMain {
    public static void main(String[] args) {
        System.out.println("Iniciando fábrica de veículos...");

        Semaphore estoquePecas = new Semaphore(500);
        Semaphore esteiraPecas = new Semaphore(5);

        EsteiraFabrica esteiraPrincipal = new EsteiraFabrica(40);
        Estacao[] estacoes = new Estacao[4];

        for(int i = 0; i < 4; i++) {
            estacoes[i] = new Estacao(i, esteiraPrincipal, estoquePecas, esteiraPecas);
            estacoes[i].iniciarProducao();
        }

        System.out.println("Fábrica de veículos em operação. Pressione Ctrl+C para encerrar.");
    }
}
