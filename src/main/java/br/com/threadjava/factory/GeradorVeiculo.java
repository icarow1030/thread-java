package br.com.threadjava.factory;

import br.com.threadjava.model.Veiculo;

import java.util.concurrent.Semaphore;
public class GeradorVeiculo {
    private static final Semaphore mutexGerador = new Semaphore(1);
    private static int contadorId = 1;
    private static int indexCor = 0;
    private static int indexTipo = 0;

    private static final String[] CORES = {"RED", "GREEN", "BLUE"};
    private static final String[] TIPOS = {"SUV", "SEDAN"};

    public static Veiculo fabricar(int idEstacao, int idFuncionario) throws InterruptedException {
        mutexGerador.acquire();
        try {
            int idAtual = contadorId++;
            String corAtual = CORES[indexCor];
            String tipoAtual = TIPOS[indexTipo];

            indexCor = (indexCor + 1) % 3;
            indexTipo = (indexTipo + 1) % 2;

            return new Veiculo(idAtual, corAtual, tipoAtual, idEstacao, idFuncionario);
        } finally {
            mutexGerador.release();
        }
    }
}
