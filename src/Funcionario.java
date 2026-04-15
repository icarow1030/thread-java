
import java.util.concurrent.Semaphore;
public class Funcionario extends Thread {
    private int idFuncionario;
    private int idEstacao;
    private Semaphore ferramentaEsquerda;
    private Semaphore ferramentaDireita;
    private EsteiraFabrica esteira;
    private Semaphore estoquePecas;
    private Semaphore esteiraPecas;

    public Funcionario(int idFuncionario, int idEstacao, Semaphore ferramentaEsquerda, Semaphore ferramentaDireita, EsteiraFabrica esteira, Semaphore estoquePecas, Semaphore esteiraPecas) {
        this.idFuncionario = idFuncionario;
        this.idEstacao = idEstacao;
        this.ferramentaEsquerda = ferramentaEsquerda;
        this.ferramentaDireita = ferramentaDireita;
        this.esteira = esteira;
        this.estoquePecas = estoquePecas;
        this.esteiraPecas = esteiraPecas;
    }

    @Override
    public void run() {
        try {
            while(true) {
                esteiraPecas.acquire();
                if(!estoquePecas.tryAcquire()) {
                    esteiraPecas.release();
                    System.out.println("Estoque esgotado. Funcionário " + idFuncionario + " da estação " + idEstacao + " aguardando reposição.");
                    break;
                }

                Thread.sleep((long) (Math.random() * 200));

                esteiraPecas.release();

                if(idFuncionario == 4) {
                    ferramentaDireita.acquire();
                    ferramentaEsquerda.acquire();
                } else {
                    ferramentaEsquerda.acquire();
                    ferramentaDireita.acquire();
                }
                Thread.sleep((long) (Math.random() * 500));
                Veiculo novoVeiculo = GeradorVeiculo.fabricar(idEstacao, idFuncionario);
                ferramentaEsquerda.release();
                ferramentaDireita.release();
                esteira.inserir(novoVeiculo);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
