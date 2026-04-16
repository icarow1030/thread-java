
package br.com.threadjava.store;

import br.com.threadjava.factory.EsteiraFabrica;
import br.com.threadjava.model.Veiculo;

public class Loja extends Thread {
    private int idLoja;
    private EsteiraLoja esteira;
    private EsteiraFabrica esteiraPrincipal;
    private int veiculosSolicitados;

    public Loja(int idLoja, EsteiraFabrica esteiraPrincipal) {
        this.idLoja = idLoja;
        this.esteiraPrincipal = esteiraPrincipal;
        this.esteira = new EsteiraLoja(40);
        this.veiculosSolicitados = 0;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public EsteiraLoja getEsteira() {
        return esteira;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Veiculo veiculo = esteiraPrincipal.remover();
                if (veiculo != null) {
                    esteira.inserir(veiculo);
                    System.out.println("LOG VENDA LOJA " + idLoja + ": " + veiculo);
                    veiculosSolicitados++;
                }
                Thread.sleep((long) (Math.random() * 100));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

