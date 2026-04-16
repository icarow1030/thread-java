import java.util.concurrent.Semaphore;

public class LojaMain {
    public static void main(String[] args) {
        System.out.println("Iniciando sistema com fábrica, lojas e clientes...");

        Semaphore estoquePecas = new Semaphore(500);
        Semaphore esteiraPecas = new Semaphore(5);

        EsteiraFabrica esteiraPrincipal = new EsteiraFabrica(40);

        Estacao[] estacoes = new Estacao[4];
        for (int i = 0; i < 4; i++) {
            estacoes[i] = new Estacao(i, esteiraPrincipal, estoquePecas, esteiraPecas);
            estacoes[i].iniciarProducao();
        }

        Loja[] lojas = new Loja[3];
        for (int i = 0; i < 3; i++) {
            lojas[i] = new Loja(i, esteiraPrincipal);
            lojas[i].start();
        }

        Cliente[] clientes = new Cliente[20];
        for (int i = 0; i < 20; i++) {
            clientes[i] = new Cliente(i, lojas);
            clientes[i].start();
        }

        System.out.println("Sistema em operação. Pressione Ctrl+C para encerrar.");
    }
}
