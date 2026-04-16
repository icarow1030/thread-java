package br.com.threadjava.factory;

import br.com.threadjava.model.Veiculo;
import java.util.concurrent.Semaphore;
public class EsteiraFabrica {
    private Veiculo[] buffer;
    private int capacidade;
    private int in;
    private int out;

    private Semaphore mutex;
    private Semaphore espacosVazios;
    private Semaphore veiculosProntos;

    public EsteiraFabrica(int capacidade) {
        this.capacidade = capacidade;
        this.buffer = new Veiculo[capacidade];
        this.in = 0;
        this.out = 0;

        this.mutex = new Semaphore(1);
        this.espacosVazios = new Semaphore(capacidade);
        this.veiculosProntos = new Semaphore(0);
    }

    public void inserir(Veiculo veiculo) throws InterruptedException {
        espacosVazios.acquire();
        mutex.acquire();

        try {
            buffer[in] = veiculo;
            veiculo.setPosicaoEsteiraFabrica(in);
            System.out.println("LOG PRODUCAO: " + veiculo);
            in = (in + 1) % capacidade;
        } finally {
            mutex.release();
            veiculosProntos.release();
        }
    }

    public Veiculo remover() throws InterruptedException {
        veiculosProntos.acquire();
        mutex.acquire();

        try {
            Veiculo veiculo = buffer[out];
            buffer[out] = null;
            out = (out + 1) % capacidade;
            return veiculo;
        } finally {
            mutex.release();
            espacosVazios.release();
        }
    }
}
