package main.java.br.com.threadjava.factory;

import java.util.concurrent.Semaphore;
public class Estacao {
    private int idEstacao;
    private Funcionario[] funcionarios;
    private Semaphore[] ferramentas;

    public Estacao(int idEstacao, EsteiraFabrica esteira, Semaphore estoquePecas, Semaphore esteiraPecas) {
        this.idEstacao = idEstacao;
        this.funcionarios = new Funcionario[5];
        this.ferramentas = new Semaphore[5];

        for(int i = 0; i < 5; i++) {
            ferramentas[i] = new Semaphore(1);
        }

        for(int i = 0; i < 5; i++) {
            Semaphore esq = ferramentas[i];
            Semaphore dir = ferramentas[(i + 1) % 5];
            funcionarios[i] = new Funcionario(i, idEstacao, esq, dir, esteira, estoquePecas, esteiraPecas);
        }
    }

    public void iniciarProducao() {
        for(Funcionario f : funcionarios) {
            f.start();
        }
    }
}
